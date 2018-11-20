#include <stdio.h>
#include "ADT_stack.h"

int main(){
	STACK* stack1;
	STACK* stack2;

	stack1 = create_stack();
	stack2 = create_stack();

	if(stack1 == NULL){
		printf("error on creating stack\n");
		return -1;
	}else
		printf("creating stack1\n");

	int a=10;
	int b=20;

	if(!push(stack1,&b))
		printf("error on pushing data %d\n",b);
	else
		printf("success ok on pushing data %d\n",b);
	
	return 0;
}