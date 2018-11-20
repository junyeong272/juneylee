#include "ADT_tree.h"
#include <stdio.h>
#include <stdlib.h>

#define PRE_ORDER 0
#define IN_ORDER 1
#define POST_ORDER 2

int main(){
	BST_TREE* tree = create_bst_tree();
	T_NODE* search;

	if(!BST_insert(tree,10))
		printf("tree insertion error\n");
	if(!BST_insert(tree,5))
		printf("tree insertion error\n");
	if(!BST_insert(tree,14))
		printf("tree insertion error\n");
	if(!BST_insert(tree,7))
		printf("tree insertion error\n");
	if(!BST_insert(tree,22))
		printf("tree insertion error\n");

	search = find_smallest_node(tree->root);
	printf("smallest: %d\n", search->data);

	search = find_largest_node(tree->root);
	printf("largest: %d\n", search->data);

	search = search_bst(tree->root,7);
	if(search != NULL)
		printf("searched node: %d\n",search->data);
	else
		printf("not found\n");

	BST_print(tree,IN_ORDER);
	if(!BST_delete(tree,14))
		printf("failed on deleting\n");

	BST_print(tree,IN_ORDER);
	return 0;

}