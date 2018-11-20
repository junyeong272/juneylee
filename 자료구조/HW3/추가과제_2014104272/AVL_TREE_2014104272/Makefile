help:
	@echo "make help"
	@echo "make all"

all: ADT_llist.c ADT_avl_tree.c main_avl_tree.c
	g++ -o main_avl_tree ADT_avl_tree.c ADT_llist.c main_avl_tree.c -pg


all2: ADT_avl_tree.c test.c
	g++ -o test ADT_avl_tree.c test.c 


run: all
	./main_avl_tree
	gprof main_avl_tree gmon.out > profile_avl_tree.txt

run2:all2
	./test

clean:
	rm *.o *.out profile_avl_tree.txt


