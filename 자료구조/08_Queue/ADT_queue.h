#ifndef ADT_QUEUE
#define ADT_QUEUE

#include <stdio.h>
#include <stdlib.h>

//Queue Node
typedef struct node{
	void* data_ptr;
	struct node* next;
}QUEUE_NODE;

//QUEUE
typedef struct {
	int count;
	QUEUE_NODE* front;
	QUEUE_NODE* rear;
}QUEUE;

//Operations
QUEUE* create_queue();
bool enqueue(QUEUE* queue, void* in);
void* dequeue(QUEUE* queue);
void* queue_hook_front(QUEUE* queue);
void* queue_hook_rear(QUEUE* queue);

#endif