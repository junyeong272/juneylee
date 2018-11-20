#include "ADT_avl_tree.h"
#include <stdlib.h>
#include <stdio.h>

#define sample_NUM 10000000

int main(){
	int* M = (int*)malloc(sizeof(int)*sample_NUM);

	int search_num = sample_NUM;

	AVL_TREE* new_avl = create_avl_tree();

	for(int i=sample_NUM-1;i>=0;i--){
		*M = sample_NUM-i;
		AVL_insert(new_avl, *M);
		M++;
	}

	int height = 1;
	int data;

	while(new_avl->root->right != NULL){
		new_avl->root = new_avl->root->right;
		data = new_avl->root->data;
		height++;
	}
	printf("height(max): %d\n",height);
	printf("largest data: %d\n",data);

	return 0;

}
