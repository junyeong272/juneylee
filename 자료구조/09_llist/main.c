#include "ADT_llist.h"

#define SORT 0 //sort

#define D_SIZE 8

int compare1(void* x, void* y) {
    return *((int*)x) - *((int*)y);
}

void print1(void* x)
{
    int* xp = (int*)x;
    printf(" - int data %d\n", *xp);
}

void print2(void* x) //print for double data 
{
    double* xp = (double*)x;
    printf(" - double data %lf\n", *xp);
}

typedef struct point{
    double x;
    double y;
} POINT;

void sort_list(LLIST* list,void* data);
void sort_list1(LLIST* list, void* data);

int main() {
    FILE* fnode = fopen("node.in", "w");
    FILE* flink = fopen("link.in", "w");

    LLIST* list = create_list(compare1, print2);
    int i;

    POINT path[D_SIZE] = {
        {35.885663, 128.6142},
        {35.888741, 128.609344},
        {35.887057, 128.610213},
        {35.886988, 128.611832},
        {35.890204, 128.609753},
        {35.892413, 128.609242},
        {35.891764, 128.609924},
        {35.887085, 128.609089},
    };

#ifndef SORT

    for(i = 0; i < D_SIZE; i++) {
        fprintf(fnode, "%lf %lf\n", path[i].x, path[i].y);
    }
    for(i = 0; i < D_SIZE-1; i++) {
        fprintf(flink, "%lf %lf %lf %lf\n", path[i].x, path[i].y, path[i+1].x, path[i+1].y);
    }

#else

    for(i=0;i<D_SIZE;i++){
    	if(!add_node_at(list, list->count, &path[i]))
    		printf("data insertion failed on list!\n");
    	else
    		printf("data insertion ok on list!\n");
    }
    
    sort_list(list,path);
    //sort_list1(list,path);

    for(i = 0; i < D_SIZE; i++) {
    	POINT* p = (POINT*)get_data_at(list,i);
        fprintf(fnode, "%lf %lf\n", p->x, p->y);
    }
    for(i = 0; i < D_SIZE-1; i++) {
    	POINT* p1 = (POINT*)get_data_at(list,i);
    	POINT* p2 = (POINT*)get_data_at(list,i+1);
        fprintf(flink, "%lf %lf %lf %lf\n",p1->x, p1->y, p2->x, p2->y);
    }


#endif

   return 0;
}

void sort_list(LLIST* list,void* data){
	int i, j;
	void* standard;
	void* compare;
	void* check;

	POINT* path = (POINT*)data;

	for(i=0;i<(list->count);i++){
		check = get_data_at(list,i);
		if(check != &path[i]){
			printf("sort_list Error: data mismatch\n");
			break;
		}
		for(i=0;i<(list->count)-1;i++){
			for(j = i+1; j<(list->count); j++){
				standard = get_data_at(list,i);
				compare = get_data_at(list,j);
				if(((POINT*)standard)->x > ((POINT*)compare)->x){
					del_node_at(list,j);
					add_node_at(list,j,standard);
					del_node_at(list,i);
					add_node_at(list,i,compare);
				}
			}
		}
	}
	//print_all(list,list->front); //check sort
}

void sort_list1(LLIST* list,void* data){
	int i,j;

	POINT temp;
	POINT* path = (POINT*)data;

	for(i=0;i<(list->count)-1;i++){
		for(j = i+1; j<(list->count); j++){
			if(path[i].x > path[j].x){
				temp = path[i];
				path[i] = path[j];
				path[j] = temp;
			}
		}
	}
	//print_all(list,list->front); //check sort
}


