#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <linux/unistd.h>

#define THREAD_NUM 5
#define LOOP_COUNT 100000

int count = 0;

void *t_function()
{
	int i;
	for(i = 0; i < LOOP_COUNT; i++) {
		count++;
	}
}

int main(void)
{
	int i;
	int pid, status;
	pthread_t p_thread[THREAD_NUM];

	for(i = 0; i < THREAD_NUM; i++) {
		if((pid = pthread_create(&p_thread[i], NULL, t_function, NULL, NULL, NULL, NULL, NULL) < 0)) {
			perror("thread create error");
			exit(1);
		}
	}

	for(i = 0; i < THREAD_NUM; i++) {
		pthread_join(p_thread[i], (void **)&status);
	}

	printf("[Process] Final value : %d\n", count);

	return 0;
}
