#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "polinomio.h"

/*
INPUTS:
-Poli: Arreglo que contiene enteros coeficientes, donde cada posicion equivale al exponente.
-n: El valor en el cual sera evaluado.
DESCRIPCION: Función la cual evalua el polinimio con un entero n.
OUTPUT:
-suma: el valor del polinomio evaluado.
*/
int evalpol(polinomio* poli,int n){
    int i,j,suma=0,multiplicacion;
    for(i=0;i<50;i++){
        multiplicacion=1;
        for(j=0;j<i;j++){
            multiplicacion*=n;
        }
        multiplicacion*=poli->coeficiente[i];
        suma+=multiplicacion;
    }
    return suma;
}

/*
INPUTS:
-Poli: Arreglo que contiene enteros coeficientes, donde cada posicion equivale al exponente.
DESCRIPCION: Función la cual integrará el polinimio.
OUTPUT: Nada.
*/
void integrar(polinomio* poli){
    int i,temp,temp2;
    float division;
    for(i=0;i<50;i++){
        if(i==0){
            temp=poli->coeficiente[i+1];
            division= (float)poli->coeficiente[i]/((float)i+1);
            poli->coeficiente[i+1]=division;
            poli->coeficiente[i]=0;
        }
        else{
            temp2=poli->coeficiente[i+1];
            division= (float)temp/((float)i+1);
            poli->coeficiente[i+1]=division;
            temp=temp2;
        }
    }
    return;
}

/*
INPUTS:
-Poli: Arreglo que contiene enteros coeficientes, donde cada posicion equivale al exponente.
DESCRIPCION: Función la cual derivará el polinimio.
OUTPUT: Nada.
*/
void derivar(polinomio* poli){
    int i;
    for(i=0;i<50;i++){
        poli->coeficiente[i]=(poli->coeficiente[i+1])*(i+1);
    }
    return;
}

/*
INPUTS:
- : Función derivar o integrar.
-poli: Polinomio que se desea ser integrado o derivado.
DESCRIPCION: Función la cual aplicara la función derivar o integrar.
OUTPUT:
poli: Polinomio ya integrado o derivado.
*/
void* accion(void (*deit)(polinomio*),polinomio* poli){
  deit(poli);
  return (void *)poli;
}
