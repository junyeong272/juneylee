#include <stdio.h>
#include <stdlib.h>
#include "ADT_stack.h"

STACK* create_stack(){
	STACK* stack = (STACK*)malloc(sizeof(STACK));
	if(!stack)
		return NULL;

	stack->count = 0;
	stack->top = NULL;

	return stack;
}

bool push(STACK* stack, void* in){
	STACK_NODE* node = (STACK_NODE*)malloc(sizeof(STACK_NODE));
	if(node == NULL)
		return false;

	node->data_ptr = in;
	node->link = stack->top; // new node's next is previous top
	stack->top = node;
	(stack->count)++;

	return true;
}
void* pop(STACK* stack){
	if(stack->count==0)
		return NULL;
	else{
		STACK_NODE* temp = stack->top;
		void* data_out = stack->top->data_ptr;
		stack->top = stack->top->link;
		free(temp);
		(stack->count)--;

		return data_out;
	}
}