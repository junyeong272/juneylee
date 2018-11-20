#include <stdio.h>
#include <stdlib.h>

#define LH 1
#define EH 0
#define RH -1

typedef struct node{
	int data;
	struct node* left;
	struct node* right;
	int balance;
}T_NODE;

typedef struct{
	int count;
	T_NODE* root;
}AVL_TREE;

AVL_TREE* create_avl_tree();
T_NODE* find_smallest_node(T_NODE* root);
T_NODE* find_largest_node(T_NODE* root);
T_NODE* search_avl(T_NODE* root, int key);
T_NODE* add_avl(T_NODE* root,int data);
T_NODE* rotate_left(T_NODE* root);
T_NODE* rotate_right(T_NODE* root);
T_NODE* balance_left(T_NODE* root,bool* taller);
T_NODE* balance_right(T_NODE* root,bool* taller);
T_NODE* insert_rotate(T_NODE* root, T_NODE* new_node, bool* taller);
bool AVL_insert(AVL_TREE* tree, int data);

T_NODE* delete_avl(T_NODE* root, int data, bool* success);
bool AVL_delete(AVL_TREE* tree, int data);
void traverse_preorder(T_NODE* root);
void traverse_postorder(T_NODE* root);
void traverse_inorder(T_NODE* root);
void AVL_print(AVL_TREE* tree, int method);
