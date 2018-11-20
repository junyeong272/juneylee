#include "ADT_heap.h"

int main(){
	HEAP* heap = create_heap(10);
	if(!insertHeap(heap,5))
		printf("tree insertion error\n");
	if(!insertHeap(heap,15))
		printf("tree insertion error\n");
	if(!insertHeap(heap,3))
		printf("tree insertion error\n");
	if(!insertHeap(heap,7))
		printf("tree insertion error\n");
	if(!insertHeap(heap,23))
		printf("tree insertion error\n");
	if(!insertHeap(heap,9))
		printf("tree insertion error\n");
	if(!insertHeap(heap,40))
		printf("tree insertion error\n");
	if(!insertHeap(heap,8))
		printf("tree insertion error\n");


	printf("root: %d\n", heap->array[0]);
	printf("size: %d\n", heap->size);
	printf("last: %d\n", heap->array[heap->last]);
	printf("max_size: %d\n", heap->max_size);
	for(int i=0;i<heap->size;i++){
		printf("%d ", heap->array[i]);
	}
	printf("\n");
	
	if(!deleteHeap(heap))
		printf("tree deletion error\n");
	printf("delete data: %d\n",heap->array[heap->last+1]);

	for(int i=0;i<heap->size;i++){
		printf("%d ", heap->array[i]);
	}
	printf("\n");
}