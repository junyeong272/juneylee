chart.o: chart.c
	g++ -c chart.c

ADT_queue.o:ADT_queue.c
	g++ -c ADT_queue.c

main.o:main.c
	g++ -c main.c

all:main.o ADT_queue.o chart.o
	g++ -o queue main.o ADT_queue.o chart.o
	
run:
	./queue > chart.html

clean:
	rm -rf *.o queue