#include "ros/ros.h"
#include "sensor_msgs/LaserScan.h"
#include <cmath>
#include <string.h>
#include <algorithm>
#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define RAD2DEG(x) ((x)*180./M_PI)
ros::Publisher second_pub;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

float left_signal = 0;
float dist_315 = 0;
float dist_120 = 0;
float dist_150 = 0;
float dist_210 = 0;
float dist_240 = 0;
float dist_180 = 10;

float dist_135 = 0;
float dist_225 = 0;

float check_f = 0;
float check_l = 0;
float check_r = 0;
int count_l = 0;
int count_r = 0;

float cal_degree();

void *sender(void *arg)
{
	int sock;
	struct sockaddr_in serv_addr;
	char message[30];
	char tem[30];
	int str_len;
	float cal_result;

	sock = socket(PF_INET, SOCK_STREAM, 0);
	
	memset(&serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr("192.168.137.218");
	serv_addr.sin_port = htons(8080);

	connect(sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	while(1)
	{
		cal_result = cal_degree();	
		sprintf(message,"%f", cal_result);
		write(sock, message, sizeof(message));
		read(sock, tem, sizeof(tem));
		left_signal = atof(tem);
//		printf("%f\n",left_signal);
	}
}
		
void scanCallback(const sensor_msgs::LaserScan::ConstPtr& scan)
{
    int count = scan->scan_time / scan->time_increment;
  
    for(int i = 0; i < count; i++) {
        float degree = RAD2DEG(scan->angle_min + scan->angle_increment * i);
        if(scan->ranges[i]>=0 && scan->ranges[i]<=10){
			if(degree >= 129.9 && degree <= 130.1){
				dist_120 = scan->ranges[i];
	   		}
	    	else if(degree >= 139.9 && degree <= 140.1){
				dist_135 = scan->ranges[i];
		    }
	   		else if(degree >= 159.9 && degree <= 160.1){
				dist_150 = scan->ranges[i];
	    	}
	    	else if(degree >= 179.8 || degree <= -179.8){
				dist_180 = scan->ranges[i];
	    	}
	    	else if(degree >= -160.1 && degree <= -159.9){
				dist_210 = scan->ranges[i];
	    	}	
	    	else if(degree >= -140.1 && degree <= -139.9){
				dist_225 = scan->ranges[i];
	    	}
	    	else if(degree >= -130.1 && degree <= -129.9){
				dist_240 = scan->ranges[i];
	    	}else if(degree >= -45.1 && degree <= -44.9){
				dist_315 = scan->ranges[i];
			}
		}
    }
    //ROS_INFO(" 120=%f | 135=%f | 150=%f", dist_120,dist_135,dist_150);
}

float cal_degree()
{
	float ang30;
	float ang60;
	float ang45;
	float temp;
	float result;
	
	if((dist_180 <= 1.5 || dist_180 >= 9) && (dist_150 <= 0.5 || dist_135 <= 0.5) &&(int)(dist_180*100) == (int)(check_f*100) && (int)(dist_150*100) == (int)(check_r*100)){
		count_r = count_r + 1;
		if(count_r >= 100){
			count_r = 0;
			return 700;
		}
	}else
		count_r = 0;
	
	if((dist_180 <= 1.5 || dist_180 >= 9) && (dist_210 <= 0.5 || dist_225 <= 0.5) && (int)(dist_180*100) == (int)(check_f*100) && (int)(dist_210*100) == (int)(check_l*100)){
		count_l = count_l + 1;
		if(count_l >= 130){
			count_l = 0;
			return 600;
		}
	}else
		count_l = 0;


	
	if(dist_180 != check_f)
		check_f = dist_180;
	if(dist_150 != check_r)
		check_r = dist_150;
	if(dist_210 != check_l)
		check_l = dist_210;


	if( left_signal == 1 && dist_150 >= 3.1 && dist_135 >= 3.1  && dist_120 >= 2.9){
		if(dist_225 <= 1.7){
			printf("*******right_empty(490)*********\n");	
			return 490;
		}else{
			printf("*******right_empty(500)*********\n");	
			return 500;
		}
	}
	else if( left_signal == 2 && dist_315 >= 4.5 && dist_240 >= 3.6 && dist_225 >= 3.6 && dist_210 >= 3.6){
		printf("*******left_empty(510)*********\n");	
		return 510;
	}
	else if( (left_signal == 1 || left_signal >2) && dist_240 >= 3.7 && dist_225 >= 3.6 && dist_210 >= 3.5){
		if(left_signal == 5 && dist_180 >= 3){
			printf("****chance (530)****\n");
			return 530;
		}else{
			printf("*******left_empty(520)*********\n");
			return 520;
		}
	}


    ang30 = dist_240 - dist_120;
    ang60 = dist_210 - dist_150;
    ang45 = dist_225 - dist_135;
	
	if(abs(ang30) >= abs(ang60)){
		if(abs(ang60) >= abs(ang45))
	  		temp = ang45;
		else
	   		temp = ang60;
    }
    else{
		if(abs(ang30) >= abs(ang45))
	    	temp = ang45;
		else
	    	temp = ang30;
    }

    result = temp * 10;
    return result;

}

int main(int argc, char **argv)
{
    ros::init(argc, argv, "rplidar_node_client");
    ros::NodeHandle n;
	
    ros::Subscriber sub = n.subscribe<sensor_msgs::LaserScan>("/scan", 1000, scanCallback);
	int res;
	pthread_t thread;
	res = pthread_create(&thread, NULL, sender, NULL);
	if(res !=0)
	{
		perror("Thread creation failed");
		exit(EXIT_FAILURE);
	}
    ros::spin();

    return 0;
}
