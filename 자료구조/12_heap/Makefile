ADT_heap.o: ADT_heap.c
	g++ -c ADT_heap.c

main.o: main.c
	g++ -c main.c

all: ADT_heap.o main.o
	g++ -o heap main.o ADT_heap.o

run: all
	./heap

clean:
	rm -rf heap main.o ADT_heap.o