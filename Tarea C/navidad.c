#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "navidad.h"
#include "polinomio.h"

typedef struct{
  int tipoDeDecodificacion;
  void *carta;
}pedido;

typedef struct{
  void* Nombre;
  void* Regalo;
}regalos;

int XDDDDDDDD;


/*
INPUTS:
- : Funcion codigoUno, codigoDos, codigoTres o codigoCuatro.
-carta: Linea de texto del archivo cartas.txt
DESCRIPCION: Función en la cual se le entregara el tipo de código utilizado para decodiﬁcar la carta.
OUTPUT:
-carta: Regalo.
*/
void* decodificacion(void* (*codigo)(char*), char* carta){
  return (void*)codigo(carta);
}

/*
INPUTS:
-carta: Linea de texto del archivo cartas.txt
DESCRIPCION: Función de decodiﬁcación donde la carta esta escrita utilizando el código numero 1.
OUTPUT:
-regalo: El nombre (string) del regalo.
*/
void* codigoUno(char* carta){
    int Bool,n;
    char nombre[40],letra,*regalo;
    sscanf(carta,"%s %d %d %c",nombre,&n,&Bool,&letra);
    if(n>=5){
        if(Bool==0){
            if(letra=='A'){
                regalo="Tumanji";
            }
            if(letra=='B'){
                regalo="Comunopoly";
            }
            if(letra=='C'){
                regalo="Caracoles Y Dragones";
            }
            if(letra=='D'){
                regalo="ADIVINAKIEM";
            }
            if(letra=='E'){
                regalo="No Sabi Na";
            }
            if(letra=='F'){
                regalo="Dibuja Bien Porfa";
            }
            if(letra=='G'){
                regalo="PiumPium";
            }
        }
        else{
            if(letra=='A'){
                regalo="ContraAtaque: Ofensiva Global";
            }
            if(letra=='B'){
                regalo="Monstruos de Bolsillo";
            }
            if(letra=='C'){
                regalo="Mantengan la calma y nadie explotara";
            }
            if(letra=='D'){
                regalo="Pato Juego";
            }
            if(letra=='E'){
                regalo="Almas negras: Edicion Preparate para morir";
            }
            if(letra=='F'){
                regalo="HOLA HOLA! Club de literatura";
            }
            if(letra=='G'){
                regalo="Equipo Fortaleza 2";
            }
        }
    }
    else{
        regalo="Carbon";
    }
    return (void*)regalo;
}

/*
INPUTS:
-carta: Linea de texto del archivo cartas.txt
DESCRIPCION: Función de decodiﬁcación donde la carta esta escrita utilizando el código numero 2.
OUTPUT:
-regalo: El nombre (string) del regalo.
*/
void* codigoDos(char* carta){
    int n,Bool,i,temporal;
    char nombre[40],letra1,letra2,*regalo;
    sscanf(carta,"%d %s",&temporal,nombre);
    Bool=temporal%10;
    if(Bool<0){
        Bool=-Bool;
    }
    n=temporal/10;
    for(i=0;i<60;i++){
        if(i!=0){
            if(nombre[i]=='A' || nombre[i]=='B' || nombre[i]=='C'){
                letra1=nombre[i];
                letra2=nombre[i+1];
                break;
            }
        }
    }
    if(n<5){
        if(Bool==0){
            if(letra1=='A' && letra2=='A'){
                regalo="LP Homework for dummies";
            }
            if(letra1=='A' && letra2=='B'){
                regalo="Heartless";
            }
            if(letra1=='A' && letra2=='C'){
                regalo="Fapelusho";
            }
            if(letra1=='B' && letra2=='A'){
                regalo="Fairy Tales of Tomc";
            }
            if(letra1=='B' && letra2=='B'){
                regalo="A Sad World";
            }
            if(letra1=='B' && letra2=='C'){
                regalo="Game of Pointers";
            }
            if(letra1=='C' && letra2=='A'){
                regalo="The Lord of The Ravs: The Fellowship of the LDS";
            }
            if(letra1=='C' && letra2=='B'){
                regalo="The Chronicles of Cthonia";
            }
            if(letra1=='C' && letra2=='C'){
                regalo="The end of Sansano";
            }
        }
        else{
            if(letra1=='A' && letra2=='A'){
                regalo="Informatic Wars: LP Strikes back";
            }
            if(letra1=='A' && letra2=='B'){
                regalo="Mate: You can (not) RAV";
            }
            if(letra1=='A' && letra2=='C'){
                regalo="How to train your mechon 3";
            }
            if(letra1=='B' && letra2=='A'){
                regalo="Mechon The Movie: I choose you!";
            }
            if(letra1=='B' && letra2=='B'){
                regalo="Prolog: Endgame";
            }
            if(letra1=='B' && letra2=='C'){
                regalo="Ifception";
            }
            if(letra1=='C' && letra2=='A'){
                regalo="Program Ravsody";
            }
            if(letra1=='C' && letra2=='B'){
                regalo="SantaMaria: The last RAVbender";
            }
            if(letra1=='C' && letra2=='C'){
                regalo="LP forever";
            }
        }
    }
    else{
        regalo="Carbon";
    }
    return (void*)regalo;
}

/*
INPUTS:
-carta: Linea de texto del archivo cartas.txt
DESCRIPCION: Función de decodiﬁcación donde la carta esta escrita utilizando el código numero 3.
OUTPUT:
-Regalo: El nombre (string) del regalo.
*/
void* codigoTres(char *carta){
  int entero;
  char caracter;
  char nombre[32];
  int booleano;
  char* Regalo;
  if(sscanf(carta, "%d%c %s %d",&entero,&caracter,nombre,&booleano) > 0){
    if(booleano == 0){
      switch(entero){
        case 0:{
          if(caracter == 'A'){
            Regalo = "Pastelito de Chimuelo";
            return (void *)Regalo;
          }
          else if(caracter == 'B'){
            Regalo = "Pastelito de Amor, compasion y ternura";
            return (void *)Regalo;
          }
          else if(caracter == 'C'){
            Regalo = "Pastelito de Calcentin con rombosman";
            return (void *)Regalo;
          }
          else if(caracter == 'D'){
            Regalo = "Pastelito de Tecojobichi Sensei y Kunashgi";
            return (void *)Regalo;
          }
        }
        case 1:{
          if(caracter == 'A'){
            Regalo = "Video prohibido de Chimuelo";
            return (void *)Regalo;
          }
          else if(caracter == 'B'){
            Regalo = "Video prohibido de Amor, compasion y ternura";
            return (void *)Regalo;
          }
          else if(caracter == 'C'){
            Regalo = "Video prohibido de Calcentin con rombosman";
            return (void *)Regalo;
          }
          else if(caracter == 'D'){
            Regalo = "Video prohibido de Tecojobichi Sensei y Kunashgi";
            return (void *)Regalo;
          }
        }
        case 2:{
          if(caracter == 'A'){
            Regalo = "Gigante pedazo de Chimuelo";
            return (void *)Regalo;
          }
          else if(caracter == 'B'){
            Regalo = "Gigante pedazo de Amor, compasion y ternura";
            return (void *)Regalo;
          }
          else if(caracter == 'C'){
            Regalo = "Gigante pedazo de Calcentin con rombosman";
            return (void *)Regalo;
          }
          else if(caracter == 'D'){
            Regalo = "Gigante pedazo de Tecojobichi Sensei y Kunashgi";
            return (void *)Regalo;
          }
        }
        case 3:{
          if(caracter == 'A'){
            Regalo = "Almuerzo con Chimuelo";
            return (void *)Regalo;
          }
          else if(caracter == 'B'){
            Regalo = "Almuerzo con Amor, compasion y ternura";
            return (void *)Regalo;
          }
          else if(caracter == 'C'){
            Regalo = "Almuerzo con Calcentin con rombosman";
            return (void *)Regalo;
          }
          else if(caracter == 'D'){
            Regalo = "Almuerzo con Tecojobichi Sensei y Kunashgi";
            return (void *)Regalo;
          }
        }
      }
    }
    else{
      Regalo = "Carbon";
      return (void *)Regalo;
    }
  }
  else if(sscanf(carta, "%c%d %s %d",&caracter,&entero,nombre,&booleano) > 0){
    if(booleano == 0){
      switch(caracter){
        case 'A':{
          if(entero == 0){
            Regalo = "Arma de Ponys";
            return (void *)Regalo;
          }
          else if(entero == 1){
            Regalo = "Arma de Ricardo Milos";
            return (void *)Regalo;
          }
          else if(entero == 2){
            Regalo = "Arma de Lagrimas de Sansanos";
            return (void *)Regalo;
          }
          else if(entero == 3){
            Regalo = "Arma de Gah, el supermodelo noruego";
            return (void *)Regalo;
          }
        }
        case 'B':{
          if(entero == 0){
            Regalo = "Vaca Lechera de Ponys";
            return (void *)Regalo;
          }
          else if(entero == 1){
            Regalo = "Vaca Lechera de Ricardo Milos";
            return (void *)Regalo;
          }
          else if(entero == 2){
            Regalo = "Vaca Lechera de Lagrimas de Sansanos";
            return (void *)Regalo;
          }
          else if(entero == 3){
            Regalo = "Vaca Lechera de Gah, el supermodelo noruego";
            return (void *)Regalo;
          }
        }
        case 'C':{
          if(entero == 0){
            Regalo = "Figura tamano real de Ponys";
            return (void *)Regalo;
          }
          else if(entero == 1){
            Regalo = "Figura tamano real de Ricardo Milos";
            return (void *)Regalo;
          }
          else if(entero == 2){
            Regalo = "Figura tamano real de Lagrimas de Sansanos";
            return (void *)Regalo;
          }
          else if(entero == 3){
            Regalo = "Figura tamano real de Gah, el supermodelo noruego";
            return (void *)Regalo;
          }
        }
        case 'D':{
          if(entero == 0){
            Regalo = "Invitacion al cabaret de Ponys";
            return (void *)Regalo;
          }
          else if(entero == 1){
            Regalo = "Invitacion al cabaret de Ricardo Milos";
            return (void *)Regalo;
          }
          else if(entero == 2){
            Regalo = "Invitacion al cabaret de Lagrimas de Sansanos";
            return (void *)Regalo;
          }
          else if(entero == 3){
            Regalo = "Invitacion al cabaret de Gah, el supermodelo noruego";
            return (void *)Regalo;
          }
        }
      }
    }
    else{
      Regalo = "Carbon";
      return (void *)Regalo;
    }
  }
  return 0;
}

/*
INPUTS:
-carta: Linea de texto del archivo cartas.txt
DESCRIPCION: Función de decodiﬁcación donde la carta esta escrita utilizando el código numero 4.
OUTPUT:
-Regalo: El nombre (string) del regalo.
*/
void* codigoCuatro(char *carta){
  int entero;
  int n;
  int contador = 0;
  char nombre[32];
  char polii[100];
  char resto[100];
  int valoruno;
  int valordos;
  int valortres;
  int booleano;
  char* Regalo;
  strcpy(resto, carta);
  if(sscanf(carta, "%d %d %d %100[^\n]",&booleano,&entero,&n,resto) > 0){
    polinomio* poli = (polinomio *)calloc(1, sizeof(polinomio)*n);
    while(contador != n){
      sscanf(resto, "%f %100[^\n]", &poli->coeficiente[contador], polii);
      strcpy(resto, polii);
      contador = contador+1;
    }
    strcpy(nombre, polii);
    valoruno = evalpol(poli, entero);
    if(booleano == 0){
      accion(integrar, poli);
    }
    else if(booleano == 1){
      accion(derivar, poli);
    }
    valordos = evalpol(poli, entero);
    free(poli);
    valortres = (valordos*valoruno)%10;
    switch(valortres){
      case 0:{
        Regalo = "Tarea de Java";
        return (void *)Regalo;
      }
      case 1:{
        Regalo = "Control de GameCircle";
        return (void *)Regalo;
      }
      case 2:{
        Regalo = "Exploding Doggos";
        return (void *)Regalo;
      }
      case 3:{
        Regalo = "Coleccion de los hombres musculosos";
        return (void *)Regalo;
      }
      case 4:{
        Regalo = "Telefono de ultima generacion PEAR";
        return (void *)Regalo;
      }
      case 5:{
        Regalo = "Caja Misteriosa";
        return (void *)Regalo;
      }
      case 6:{
        Regalo = "F.L.U.D.D";
        return (void *)Regalo;
      }
      case 7:{
        Regalo = "Mechon";
        return (void *)Regalo;
      }
      case 8:{
        Regalo = "Un sueno";
        return (void *)Regalo;
      }
      case 9:{
        Regalo = "Un 100 en la tarea de C";
        return (void *)Regalo;
      }
    }
  }
  return 0;
}

/*
INPUTS:
-cartas: Archivo tipo FILE*.
DESCRIPCION: Función en la donde se leeran las cartas y se escribirán los regalos pedidos en las cartas del archivo.
OUTPUT: Nada.
*/
void creacionRegalos(FILE *cartas){
    cartas = fopen("cartas.txt", "r");
    FILE* salida = fopen("regalos.txt", "w");
    int lineas, i = 0, j, basura;
    char nombre[32], nombre2[32] = "", basura2[2];
    char *ret;
    fscanf(cartas, "%d", &lineas);
    pedido* pedidos = (pedido *)calloc(1, sizeof(pedido)*lineas);
    char* Regalo;
    while(i != lineas){
        pedidos[i].carta = calloc(1, sizeof(char *)*50);
        fscanf(cartas, "%d\n", &pedidos[i].tipoDeDecodificacion);
        fgets ((char*)pedidos[i].carta, 100000, cartas);
        if(pedidos[i].tipoDeDecodificacion == 1){
            sscanf((char*)pedidos[i].carta, "%s", nombre);
            Regalo = decodificacion(codigoUno,pedidos[i].carta);
            fprintf(salida,"%s %s\n", nombre, Regalo);
        }
        else if(pedidos[i].tipoDeDecodificacion == 2){
          sscanf((char*)pedidos[i].carta, "%d %s",&basura,nombre);
          for (j=0;j<60;j++){
              if(nombre[j]=='A' || nombre[j]=='B' || nombre[j]=='C'){
                  if(j!=0){
                      break;
                  }
              }
              nombre2[j]=nombre[j];
          }
          Regalo = decodificacion(codigoDos,pedidos[i].carta);
          fprintf(salida,"%s %s\n", nombre2, Regalo);
        }
        else if(pedidos[i].tipoDeDecodificacion == 3){
            sscanf((char*)pedidos[i].carta, "%s %s",basura2,nombre);
            Regalo = decodificacion(codigoTres,pedidos[i].carta);
            fprintf(salida,"%s %s\n", nombre, Regalo);
        }
        else if(pedidos[i].tipoDeDecodificacion == 4){
          ret = strrchr((char*)pedidos[i].carta, ' ');
          sscanf(ret, " %s", nombre);
          Regalo = decodificacion(codigoCuatro,pedidos[i].carta);
          fprintf(salida,"%s %s\n", nombre, Regalo);
        }

        i++;
    }
    i=0;
    while(i != lineas){
        free(pedidos[i].carta);
        i++;
    }
    free(pedidos);
    fclose(salida);
    fclose(cartas);
    return;
}
