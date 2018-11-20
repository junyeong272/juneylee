help:
	@echo "make help"
	@echo "make all"

ADT_llist.o:ADT_llist.c
	g++ -c ADT_llist.c

main.o:main.c
	g++ -c main.c

all:main.o ADT_llist.o
	g++ -o path main.o ADT_llist.o 


run: all
	./path
	g++ -o create_map create_map.c
	./create_map
	firefox gps.html


clean:
	rm *.o *.in *.swp *.html path create_map

