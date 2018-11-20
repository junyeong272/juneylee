#include "ADT_heap.h"

HEAP* create_heap(int max){
	HEAP* heap = (HEAP*)malloc(sizeof(HEAP));
	heap->last = -1;
	heap->size = 0;
	heap->max_size = max;
	heap->array = (int*)malloc(sizeof(int)*max);
	return heap;
}

void reheapUp(HEAP* heap, int newNode){
	int parent;
	int backup;

	if(newNode != 0){
		parent = (newNode-1)/2;
		if(heap->array[newNode] > heap->array[parent]){
			backup = heap->array[parent];
			heap->array[parent] = heap->array[newNode];
			heap->array[newNode] = backup;
			reheapUp(heap, parent);
		}
	}
}

void reheapDown(HEAP* heap, int root){
	int backup;
	int left;
	int leftData;
	int right;
	int rightData;
	int largeSub;
	int last;

	last = heap->last;
	left = 2*root+1;
	right = 2*root+2;

	if(last >= left){
		leftData = heap->array[left];
		if(last >= right)
			rightData = heap->array[right];
		else
			rightData = -1;

		if( (rightData == -1) || (leftData > rightData))
			largeSub = left;
		else
			largeSub = right;
	
		if(heap->array[root] < heap->array[largeSub]){
			backup = heap->array[root];
			heap->array[root] = heap->array[largeSub];
			heap->array[largeSub] = backup;
			reheapDown(heap,largeSub);
		}
	}
}

bool insertHeap(HEAP* heap, int data){
	if(heap->size == 0){
		heap->size = 1;
		heap->last = 0;
		heap->array[heap->last] = data;
		return true;
	}
	if(heap->last == (heap->max_size)-1)
		return false;
	heap->size++;
	heap->last++;
	heap->array[heap->last]=data;
	reheapUp(heap,heap->last);
	return true;
}

bool deleteHeap(HEAP* heap){
	if(heap->size==0)
		return false;
	int backup = heap->array[0];
	heap->array[0] = heap->array[heap->last];
	heap->array[heap->last] = backup;
	(heap->last)--;
	(heap->size)--;
	reheapDown(heap,0);
	return true;
}