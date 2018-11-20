#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

#define CHILD_NUM 10
#define LOOP_CNT 100

int count = 0;

int main()
{
	int i, j;
	pid_t pid;
	pid_t ppid;

	ppid = getpid();

	for(i = 0; i < CHILD_NUM; i++)
	{
		if (ppid == getpid())
			pid = fork();
	}

	switch(pid)
	{
		case -1:
		{
			printf("fork error!\n");
			exit(0);
		}

		case 0:
		{
			for(j = 0; j < LOOP_CNT; j++) {
				count++;
				printf("[Child] count = %3d, PID = %d\n", count, getpid());
				usleep(1000);
			}
			break;
		}

		default:
		{
			int stat_val;
            pid_t child_pid;
            for(i = 0; i < CHILD_NUM; i++)
				child_pid = wait(&stat_val);
			printf("[Parent] final value : %d\n", count);

			break;
		}
	}

	return 0;
}
