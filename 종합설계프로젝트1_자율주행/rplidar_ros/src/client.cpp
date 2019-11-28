#include "ros/ros.h"
#include "sensor_msgs/LaserScan.h"
#include "rplidar_ros/Message1.h"
#include <cmath>

#define RAD2DEG(x) ((x)*180./M_PI)
ros::Publisher second_pub;

float dist_120 = 0;
float dist_150 = 0;
float dist_210 = 0;
float dist_240 = 0;1
float dist_180 = 99;


float cal_degree(float dist_120, float dist_150, float dist_210,float dist_240);


void scanCallback(const sensor_msgs::LaserScan::ConstPtr& scan)
{
	ros::NodeHandle nh2;
	second_pub = nh2.advertise<rplidar_ros::Message1>("degree_msg",10);
	rplidar_ros::Message1 msg;
    int count = scan->scan_time / scan->time_increment;
    int stop = 0;

	for(int i = 0; i < count; i++) {
        float degree = RAD2DEG(scan->angle_min + scan->angle_increment * i);
		if(scan->ranges[i]>=0 && scan->ranges[i]<=12){
			if(degree>119.9 && degree < 120.1){
				dist_120 = scan->ranges[i];
			}
			else if(degree > 149.9 && degree < 150.1){
				dist_150 = scan->ranges[i];
			}
			else if(degree > 179.8 || degree < -179.8 ){
				dist_180 = scan->ranges[i];
			}
			else if(degree > -150.1 && degree < -149.9){
				dist_210 = scan->ranges[i];
			}
			else if(degree > -120.1 && degree < -119.9){
				dist_240 = scan->ranges[i];
			}
		}
	}
	
	if(dist_150 == 0 || dist_120 ==0 || dist_210==0 || dist_240 == 0)
		return;
	
	if(dist_180 < 2.3  && dist_150 < 2.5 && dist_210 < 2.5){
		stop = 1;
	}
	else{
		stop = 0;
		dist_180 = 99;
	}

	msg.stop = stop;
	msg.degree = cal_degree(dist_120, dist_150, dist_210, dist_240);	
	second_pub.publish(msg);
	
}

float cal_degree(float dist_120, float dist_150,float dist_210,float dist_240)
{
	float ang30;
	float ang60;
	float temp;
	float result;

	ang30 = dist_240 - dist_120;
	ang60 = dist_210 - dist_150;

	if(abs(ang30) > abs(ang60))
		temp = ang60;
	else
		temp = ang30;

	result = temp * 10;
	return result;
}



int main(int argc, char **argv)
{
    ros::init(argc, argv, "rplidar_node_client");
    ros::NodeHandle n;

    ros::Subscriber sub = n.subscribe<sensor_msgs::LaserScan>("/scan", 10, scanCallback);

    ros::spin();

    return 0;
}
