#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int last;
	int size;
	int* array;
	int max_size;
}HEAP;

HEAP* create_heap(int max);
void reheapUp(HEAP* heap, int newNode);
void reheapDown(HEAP* heap, int root);
bool insertHeap(HEAP* heap, int data);
bool deleteHeap(HEAP* heap);