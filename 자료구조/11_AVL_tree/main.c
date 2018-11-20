#include "ADT_tree.h"

#define PRE_ORDER 0
#define IN_ORDER 1
#define POST_ORDER 2

int main(){
	AVL_TREE* tree = create_avl_tree();
	T_NODE* search;
	if(!AVL_insert(tree,10))
		printf("tree insertion error\n");
	if(!AVL_insert(tree,15))
		printf("tree insertion error\n");
	if(!AVL_insert(tree,5))
		printf("tree insertion error\n");
	if(!AVL_insert(tree,7))
		printf("tree insertion error\n");
	if(!AVL_insert(tree,12))
		printf("tree insertion error\n");
	if(!AVL_insert(tree,14))
		printf("tree insertion error\n");

	search = find_smallest_node(tree->root);
	printf("smallest: %d\n", search->data);

	search = find_largest_node(tree->root);
	printf("largest: %d\n", search->data);

	search = search_avl(tree->root,18);
	if(search != NULL)
		printf("searched node: %d\nright: %d\n",search->data,search->right->data);
	else
		printf("not found\n");

	AVL_print(tree,IN_ORDER);

	printf("root: %d\n",tree->root->data);

	if(!AVL_delete(tree,14))
		printf("failed on deleting\n");
	printf("root: %d\n",tree->root->data);

	AVL_print(tree,IN_ORDER);
	return 0;

}