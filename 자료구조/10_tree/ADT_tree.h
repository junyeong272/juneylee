#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int data;
	struct node* left;
	struct node* right;
}T_NODE;

typedef struct{
	int count;
	T_NODE* root;
}BST_TREE;

BST_TREE* create_bst_tree();
T_NODE* find_smallest_node(T_NODE* root);
T_NODE* find_largest_node(T_NODE* root);
T_NODE* search_bst(T_NODE* root, int key);
T_NODE* add_bst(T_NODE* root,int data);
bool BST_insert(BST_TREE* tree, int data);

T_NODE* delete_bst(T_NODE* root, int data, bool* success);
bool BST_delete(BST_TREE* tree, int data);
void traverse_preorder(T_NODE* root);
void traverse_postorder(T_NODE* root);
void traverse_inorder(T_NODE* root);
void BST_print(BST_TREE* tree, int method);
