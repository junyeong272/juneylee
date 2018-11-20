help:
	@echo "make help"
	@echo "make all"

all: ADT_llist.c ADT_tree.c main_llist.c
	g++ -o main_llist ADT_tree.c ADT_llist.c main_llist.c -pg

run: all
	./main_llist
	gprof main_llist gmon.out > profile_llist.txt

clean:
	rm *.o *.out profile_llist.txt


