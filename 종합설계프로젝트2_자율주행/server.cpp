#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>
#include <pigpio.h>
using namespace std;

#define DC 13
#define SERVO 18
#define DESIRED_DEGREE 0
#define CENTER 275 // 275
#define Kp 1
#define Ki 0.0000001


float left_signal = 1;
float stop = 0;
float degree = 0;
float error = 0;
float prev_error = 0;
float error_sum = 0;
float value_p = 0;
float value_i = 0;
float value_d = 0;
float I_err = 0;
float D_err = 0;
float value = 0;
int control = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

float pid_saturate(float input,float min,float max);


void forward(){
	gpioPWM(DC,240);
	time_sleep(1);
	gpioPWM(DC,250);
	time_sleep(1);
}

int pid_function(){
	while(1){
	if(degree == 490 || degree == 500 || degree == 510 || degree == 520 || degree == 530){
		continue;
	}

	error = DESIRED_DEGREE - degree;
	error_sum = error_sum + error;
	value_p = error * Kp;
	value_i = error_sum * Ki;
	value = value_p;
	value = pid_saturate(value,-30,30);
	control = CENTER + (int)(value);
	return control;
	}
}
	
float pid_saturate(float input,float min,float max){
	float saturated_value = input;

	if(input >= -1 && input <= 1)
		saturated_value = 0;

	if(input > max)
		saturated_value = max;
	else if(input < min)
		saturated_value = min;
	return saturated_value;
}

void *t_function(void *arg){
	time_sleep(4);
	gpioPWM(SERVO,CENTER);
	time_sleep(0.1);
	forward();
	while(1){
		if((degree == 500 || degree == 490) && left_signal == 1){
			if(degree == 500){
				printf("**********LEFT_1**********\n");
				printf("**********LEFT_1**********\n");
				gpioPWM(SERVO,274);//slow(11.30) -> 4.4 , 6.2 , 2.8
				time_sleep(2.6); 			// 3.7	,,,,,, 4 down
				gpioPWM(SERVO,235);			// 5.1 / 4.2 / 3.5	
				time_sleep(3.7); 		 
				gpioPWM(SERVO,276);
				time_sleep(1.5); //2.2 2.5
			}
			else{
				printf("**********LEFT_111**********\n");
				printf("**********LEFT_111**********\n");
				gpioPWM(SERVO,276);		//4.4 down
				time_sleep(3); 			// 6.2 / 4.5 / 3.3
				gpioPWM(SERVO,235);	
				time_sleep(3.7); 	
				gpioPWM(SERVO,276);
				time_sleep(1.5); 
			}
			left_signal = 2;
			error_sum = 0;
		}
		else if((degree == 510) && left_signal == 2){
			printf("*********LEFT_2***********\n");
			printf("*********LEFT_2***********\n");
			gpioPWM(SERVO,CENTER);
			time_sleep(1);
			gpioPWM(SERVO,250); //slow(11.30) -> 6.2 / 0.5
			time_sleep(5.1); //5.5
			gpioPWM(SERVO,CENTER);
			time_sleep(0.5);
			left_signal = 3;
			error_sum = 0;
		}
		else if((degree == 520) && left_signal == 3){
			printf("************ST**************\n");
			printf("************ST**************\n");
			gpioPWM(SERVO,265);
			time_sleep(1.2); //1 1.2 2
			gpioPWM(SERVO,278);
			time_sleep(1); //1 1.2 2
			left_signal = 4;
		}
		else if(degree == 520 && left_signal == 4){
			printf("*********LEFT_3***********\n");
			printf("*********LEFT_3***********\n");
			
			gpioPWM(SERVO,276);
			time_sleep(0.4); // 1.2 1.6
			gpioPWM(SERVO,240); //slow(11.30) -> 5.2 / 2.5
			time_sleep(3.8); //3.5 4 4.6
			gpioPWM(SERVO,275);
			time_sleep(1); // 1.2 1.6
			
			left_signal = 5;
			error_sum = 0;
		}
		else if(degree == 530 && left_signal == 5){
			printf("*********chance***********\n");
			printf("*********chance***********\n");
			
			gpioPWM(SERVO,276);
			time_sleep(0.4); // 1.2 1.6
			gpioPWM(SERVO,240); //slow(11.30) -> 5.2 / 2.5
			time_sleep(3.8); //3.5 4 4.6
			gpioPWM(SERVO,275);
			time_sleep(1); // 1.2 1.6
			
			error_sum = 0;
		}
		else if(degree == 700){
			printf("*********right_back***********\n");
			printf("*********right_back***********\n");
			gpioPWM(DC,228);
			time_sleep(0.1);
			gpioPWM(SERVO,325);
			time_sleep(2);
			gpioPWM(SERVO,255);
			forward();
			gpioPWM(SERVO,255);
			time_sleep(1); //1.2
			if(left_signal == 2){
				gpioPWM(SERVO,277);
				time_sleep(4);
			}else{
				gpioPWM(SERVO,275);
				time_sleep(0.3);
			}
			error_sum = 0;

		}
		else if(degree == 600){
			printf("*********left_back***********\n");
			printf("*********left_back***********\n");
			gpioPWM(DC,228);
			time_sleep(0.1);
			gpioPWM(SERVO,225);
			time_sleep(2);
			gpioPWM(SERVO,290);
			forward();
			gpioPWM(SERVO,290);
			time_sleep(1); //1.2
			gpioPWM(SERVO,275);
			time_sleep(0.3);
			error_sum = 0;
		}
		
	
		gpioPWM(SERVO,pid_function());
		time_sleep(0.001);
			
	}
}

void error_handling(char *message);
void *receiver(void *arg)
{

	int serv_sock;
	int clnt_sock;
	char buf[30];
	char temp[30];

	struct sockaddr_in serv_addr;
	struct sockaddr_in clnt_addr;
	socklen_t clnt_addr_size;

	serv_sock = socket(PF_INET, SOCK_STREAM, 0);
	if(serv_sock == -1)
	{
		error_handling("socket() error");
	}

	memset(&serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_addr.sin_port = htons(8080);

	if(bind(serv_sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr))==-1)
	{
		error_handling("bind() error");
	}

	if(listen(serv_sock, 5) == -1)
		error_handling("listen() error");
	clnt_addr_size = sizeof(clnt_addr);
	clnt_sock = accept(serv_sock, (struct sockaddr*)&clnt_addr, &clnt_addr_size);
	if(clnt_sock == -1)
		error_handling("accept() error");
	while(1)
	{
		read(clnt_sock, buf, sizeof(buf));
		degree = atof(buf);
//		printf("%f\n",degree);
		sprintf(temp,"%f",left_signal);
		write(clnt_sock,temp, sizeof(temp));
	}
	
}
int main(int argc, char *argv[])
{
	int res;
	pthread_t thread;
	void *thread_result;
	res = pthread_create(&thread, NULL, receiver, NULL);
	if(res !=0){
		perror("Thread creation failed");
		exit(EXIT_FAILURE);
	}

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
	while(1);

	return 0;
}

void error_handling(char *message)
{
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}
