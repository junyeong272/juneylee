#include "ADT_queue.h"

QUEUE* create_queue(){
	QUEUE* queue = (QUEUE*)malloc(sizeof(queue));
	if(!queue)
		return NULL;

	queue->count = 0;
	queue->front = NULL;
	queue->rear = NULL;

	return queue;
}

bool enqueue(QUEUE* queue, void* in){
	QUEUE_NODE* node = (QUEUE_NODE*)malloc(sizeof(QUEUE_NODE));
	if(node == NULL)
		return false;

	node->data_ptr = in;
	node->next = NULL;
	
	if(queue->count==0)
		queue->front = node;
	else
		queue->rear->next = node;

	(queue->count)++;
	queue->rear = node;

	return true;
}
void* dequeue(QUEUE* queue){
	if(queue->count==0)
		return NULL;
	else{
		QUEUE_NODE* deleted_node = queue->front;
		void* item_ptr = queue->front->data_ptr;
		if(queue->count==1){
			queue->front = NULL;
			queue->rear = NULL;
		}else{
			queue->front = queue->front->next;
		}
		free(deleted_node);
		(queue->count)--;

		return item_ptr;
	}
}
void* queue_hook_front(QUEUE* queue){
	if(queue->count==0)
		return NULL;
	else
		return queue->front->data_ptr;
}
void* queue_hook_rear(QUEUE* queue){
	if(queue->count==0)
		return NULL;
	else
		return queue->rear->data_ptr;
}
void destroy_queue(QUEUE* queue){
	if(queue != NULL){
		while(queue->count!=0){
			free(queue->front->data_ptr);
			dequeue(queue);
		}
	}
}
