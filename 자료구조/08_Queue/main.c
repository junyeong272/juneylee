// ADT QUEUE
#include <stdio.h>
#include "ADT_queue.h"
#include "chart.h"

#define REVERSE 0 // reverse

int main() {

	// new type definition
	typedef struct {
		char name[20];
		int score;
	} STD;

	// prepare 5 data
	STD student[5] = {
		{"James", 95},
		{"Yoosoo", 87},
		{"Paul", 93},
		{"Peter", 76},
		{"Park", 100},
	};

#ifndef REVERSE
	generate_chart_header();
	//                    me                my boss            my score
	generate_chart_node(student[0].name, student[0].name, student[0].score); 
	generate_chart_node(student[1].name, student[0].name, student[1].score); 
	generate_chart_node(student[2].name, student[1].name, student[2].score); 
	generate_chart_node(student[3].name, student[2].name, student[3].score); 
	generate_chart_node(student[4].name, student[3].name, student[4].score); 
	generate_chart_footer();
#else
	//Queue Creation
	QUEUE* queue;
	queue = create_queue();

	//enqueue them
	int i;
	for(i=0;i<sizeof(student)/sizeof(student[0]);i++){
		enqueue(queue,&student[i]);
	}

	//pop them
	STD* boss = NULL;
	STD* std;
	generate_chart_header();
	while(queue->count!=0){
		if(boss==NULL){ 
			std = (STD*)dequeue(queue);
			boss = std;
		}else{
			boss = std;
			std = (STD*)dequeue(queue);
		}
		generate_chart_node(std->name,boss->name,std->score);

	}
	generate_chart_footer();

#endif

    return 0;
}

