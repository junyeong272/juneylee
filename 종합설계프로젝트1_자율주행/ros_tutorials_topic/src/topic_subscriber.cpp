#include "ros/ros.h"
#include "ros_tutorials_topic/Message1.h"
#include <pthread.h>
#include <pigpio.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <algorithm>
using namespace std;

#define DC 17
#define SERVO 18
#define DESIRED_DEGREE 0
#define CENTER 270 // 275
#define Kp 2.2

int stop = 0;
float degree = 0;

float pid_saturate(float input,float min,float max);

void forward(){
	gpioPWM(DC,240);
	time_sleep(1);
//	gpioPWM(DC,251);
//	time_sleep(1);
	gpioPWM(DC,250);
	time_sleep(1);
}

int pid_function(){
	float error;
	float value;
	int control;

	error = DESIRED_DEGREE - degree;
	value = error*Kp;
	value = pid_saturate(value,-45,45);
	control = CENTER + (int)(value);
	return control;
}

float pid_saturate(float input,float min,float max){
	float saturated_value = input;

	if(input > -3.5 && input < 3.5)
		saturated_value = 0;

	if(input > max)
		saturated_value = max;
	else if(input < min)
		saturated_value = min;
	return saturated_value;
}


void msgCallback(const ros_tutorials_topic::Message1::ConstPtr& msg)
{
	stop = msg->stop;
	degree = msg->degree;
	ROS_INFO("servo_degree=%d, degree = %f",pid_function(),degree*Kp);
}

void *t_function(void *arg){
	int restart = 0;
	forward();
	while(1){
		if(stop == 1){
			restart = 1;
			ROS_INFO("stop");
			gpioPWM(DC,240);
			time_sleep(3);
			continue;
		}
		if(restart == 1){
			restart = 0;
			forward();
		}
		gpioPWM(DC,250);

		gpioPWM(SERVO,pid_function());	//PID control 
		time_sleep(0.001);
	}
}

int main(int argc, char **argv)
{
	ros::init(argc,argv,"topic_subscriber");
	ros::NodeHandle nh;

	if(gpioInitialise()<0){
			return -1;
	}

	//DC set
	gpioSetMode(DC, PI_OUTPUT);
	gpioSetPWMfrequency(DC, 40);
	gpioSetPWMrange(DC,4000);
	
	//servo set
	gpioSetMode(SERVO,PI_OUTPUT);
	gpioSetPWMfrequency(SERVO,50);
	gpioSetPWMrange(SERVO,3600);
	
	pthread_t pid_t;
	int pid;
	pid=pthread_create(&pid_t,NULL,t_function,NULL);
	if(pid !=0){
		perror("Thread creation failed");
		exit(EXIT_FAILURE);
	}

	ros::Subscriber ros_tutorial_sub = nh.subscribe("degree_msg",10,msgCallback);
	ros::spin();

	return 0;
}

