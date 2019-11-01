all: navidad

navidad: polinomio.o navidad.o main.o
	gcc -g polinomio.o navidad.o main.o -o tarea.out -Wall

polinomio.o: polinomio.c polinomio.h
	gcc -c polinomio.c -Wall

main.o: main.c
	gcc -c main.c -Wall

navidad.o: navidad.c navidad.h
	gcc -c navidad.c -Wall

borrar:
	rm navidad.o polinomio.o main.o tarea.out regalos.txt
