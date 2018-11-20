#include "ADT_tree.h"

AVL_TREE* create_avl_tree(){
	AVL_TREE* tree = (AVL_TREE*)malloc(sizeof(AVL_TREE));
	tree->count = 0;
	tree->root = NULL;
	return tree;
}

T_NODE* find_smallest_node(T_NODE* root){
	if(root->left == NULL)
		return root;
	else
		return find_smallest_node(root->left);
}

T_NODE* find_largest_node(T_NODE* root){
	if(root->right == NULL)
		return root;
	else
		return find_largest_node(root->right);
}

T_NODE* search_avl(T_NODE* root, int key){
	if(root == NULL)
		return NULL;
	if(key < (root->data))
		return search_avl(root->left,key);
	else if(key > (root->data))
		return search_avl(root->right,key);
	else
		return root;
}

T_NODE* add_avl(T_NODE* root,int data){
	if(root==NULL){
		T_NODE* newPtr = (T_NODE*)malloc(sizeof(T_NODE));
		if(!newPtr)
			return NULL;
		newPtr->right = NULL;
		newPtr->left = NULL;
		newPtr->data = data;
		return newPtr;
	}
	if(data < (root->data)){
		root->left = add_avl(root->left,data);
		return root;
	}else{
		root->right = add_avl(root->right,data);
		return root;
	}
}

T_NODE* rotate_left(T_NODE* root){
	T_NODE* new_root = root->right;
	root->right = new_root->left;
	new_root->left = root;
	return new_root;
}

T_NODE* rotate_right(T_NODE* root){
	T_NODE* new_root = root->left;
	root->left = new_root->right;
	new_root->right = root;
	return new_root;
}

T_NODE* balance_left(T_NODE* root,bool* taller){
	T_NODE* right_tree;
	T_NODE* left_tree;

	left_tree = root->left;
	switch(left_tree->balance){
		case LH:
			root->balance = EH;
			left_tree->balance = EH;
			root = rotate_right(root);
			*taller = false;
			break;
		case EH:
			exit(0);
		case RH:
			right_tree = left_tree->right;
			switch(right_tree->balance){
				case LH:
					root->balance = RH;
					left_tree->balance = EH;
					break;
				case EH:
					root->balance = EH;
					left_tree->balance = EH;
					break;
				case RH:
					root->balance = EH;
					left_tree->balance = LH;
					break;
			}
			right_tree->balance = EH;
			root->left = rotate_left(left_tree);
			root = rotate_right(root);
			*taller = false;
			break;
	}
	return root;
}

T_NODE* balance_right(T_NODE* root, bool* taller){
	T_NODE* right_tree;
	T_NODE* left_tree;

	right_tree = root->right;
	switch(right_tree->balance){
		case LH:
			left_tree = right_tree->left;
			switch(left_tree->balance){
				case LH:
					root->balance = EH;
					right_tree->balance = RH;
					break;
				case EH:
					root->balance = EH;
					right_tree->balance = EH;
				case RH:
					root->balance = LH;
					right_tree->balance = EH;
					break;
			}
			left_tree->balance = EH;
			root->right = rotate_right(right_tree);
			root = rotate_left(root);
			*taller = false;
			break;
		case EH:
			exit(0);
		case RH:
			root->balance = EH;
			right_tree->balance = EH;
			root = rotate_left(root);
			*taller = false;
			break;
	}
	return root;
}

T_NODE* insert_rotate(T_NODE* root, T_NODE* new_node, bool* taller){
	if(root==NULL){
		root = new_node;
		*taller = true;
		return root;
	}
	if((new_node->data) < (root->data)){
		root->left = insert_rotate(root->left,new_node,taller);
		if(*taller){
			switch(root->balance){
				case LH:
					root=balance_left(root,taller);
					break;
				case EH:
					root->balance = LH;
					break;
				case RH:
					root->balance = EH;
					*taller=false;
					break;
			}
		}
		return root;
	}else{
		root->right = insert_rotate(root->right,new_node,taller);
		if(*taller){
			switch(root->balance){
				case LH:
					root->balance = EH;
					*taller = false;
					break;
				case EH:
					root->balance = RH;
					break;
				case RH:
					root = balance_right(root,taller);
					break;
			}
		}
		return root;
	}
	return root;
}

bool AVL_insert(AVL_TREE* tree, int data){
	T_NODE* new_node = (T_NODE*)malloc(sizeof(T_NODE));
	bool taller;
	if(!new_node)
		return false;
	new_node->balance = EH;
	new_node->right = NULL;
	new_node->left = NULL;
	new_node->data = data;

	tree->root = insert_rotate(tree->root, new_node, &taller);
	(tree->count)++;
	return true;	
}

T_NODE* delete_avl(T_NODE* root, int data, bool* success){
	T_NODE* new_root;
	T_NODE* deleted_node;
	T_NODE* search;

	if(root==NULL){
		*success = false;
		return NULL;
	}
	if(data < (root->data))
		root->left = delete_avl(root->left,data,success);
	else if(data > (root->data))
		root->right = delete_avl(root->right,data,success);
	else{
		deleted_node = root;
		if(root->left==NULL){
			new_root = root->right;
			free(deleted_node);
			*success = true;
			return new_root;
		}else{
			if(root->right == NULL){
				new_root = root->left;
				free(deleted_node);
				*success = true;
				return new_root;
			}else{
				search = root->left;
				while(search->right != NULL)
					search = search->right;
				root->data = search->data;
				root->left = delete_avl(root->left, search->data, success);
			}
		}
	}
	return root;
}

bool AVL_delete(AVL_TREE* tree, int data){
	bool success;
	T_NODE* newRoot = delete_avl(tree->root,data,&success);
	if(success){
		tree->root = newRoot;
		(tree->count)--;
		if(tree->count==0)
			tree->root = NULL;
	}
	return success;
}


void traverse_preorder(T_NODE* root){
	if(root != NULL){
		printf("%d ", root->data);
		traverse_preorder(root->left);
		traverse_preorder(root->right);
	}
}

void traverse_postorder(T_NODE* root){
	if(root != NULL){
		traverse_postorder(root->left);
		traverse_postorder(root->right);
		printf("%d ", root->data);
	}
}

void traverse_inorder(T_NODE* root){
	if(root != NULL){
		traverse_inorder(root->left);
		printf("%d ", root->data);
		traverse_inorder(root->right);
	}
}

void AVL_print(AVL_TREE* tree, int method){
	printf("AVL_TREE\n");
	printf("size : %d\n", tree->count);
	printf("data : ");

	if(method == 0)
		traverse_preorder(tree->root);
	else if(method==1)
		traverse_inorder(tree->root);
	else if(method==2)
		traverse_postorder(tree->root);
	else
		printf("type error\n");

	printf("\n");
}