#include "ADT_llist.h"
#include "ADT_avl_tree.h"
#include <stdlib.h>
#include <stdio.h>

#define sample_NUM 10000000

int compare1(void* x, void* y){
	return *((int*)x) - *((int*)y);
}

void print1(void* x){
	int* xp = (int*)x;
	printf(" - int data %d\n", *xp);
}

int main(){
	FILE* fin = fopen("sample.txt","r");
	int* N = (int*)malloc(sizeof(int)*sample_NUM);
	int* M = (int*)malloc(sizeof(int)*sample_NUM);

	int i, iter, cmp_result;
	int search_num = sample_NUM-1;
	T_NODE* search;

	LLIST* new_llist = create_list(compare1,print1);
	AVL_TREE* new_avl = create_avl_tree();

	for(i=0;i<sample_NUM;i++){
		*M = sample_NUM - i;
		fscanf(fin, "%d", N);
		//add_node_at(new_llist, 0, M);
		AVL_insert(new_avl, *N);
		N++;
		M++;
	}
	search = search_avl(new_avl->root,search_num);
	printf("search node = %d\n", search->data);

	fclose(fin);
	return 0;

}
