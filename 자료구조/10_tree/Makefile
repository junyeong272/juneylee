ADT_tree.o: ADT_tree.c
	g++ -c ADT_tree.c

main.o: main.c
	g++ -c main.c

all: ADT_tree.o main.o
	g++ -o tree main.o ADT_tree.o 


run: all
	./tree
	
clean:
	rm -rf tree main.o ADT_tree.o

