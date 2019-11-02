import re
import colorama
import os
from colorama import Fore

boolPROCEDURE = False
boolENDPROCEDURE = False
listavacia=[]

archivo = open("pseudocode.txt","r")###cambiar los nombres xdddd
salida = open("temporal.txt", "w")###cambiar los nombreees XDdxdd
salidafinal = open("pseudocodigo.txt","w")


##FUNCION:
####TRASPASA LOS DATOS DEL ARCHIVO pseudocodigo.txt AL ALRCHIVO TEMPORAL.TXT####
def SalidaaTemporal():
    salida=open("temporal.txt","w")
    salidafinal=open("pseudocodigo.txt","r")
    for linea in salidafinal:
        linea = linea.rstrip('\n')
        salida.write(linea+'\n')
    salida.close()
    salidafinal.close()


##INPUT:
####Listadecosasbuenas: Lista con la posicion de las lineas que estan buenas
##FUNCION:
####SOBREESCRIBE EN EL ARCHIVO pseudocodigo.txt LAS LINEAS BUENAS CON #BUENO, PARA GUIAR
def cosasbuenas(Listadecosasbuenas):
    salida=open("temporal.txt","r")
    salidafinal=open("pseudocodigo.txt","w")
    i=0
    for linea in salida:
        i+=1
        linea = linea.rstrip('\n')
        if(i in Listadecosasbuenas):
            salidafinal.write(linea+ " #BUENO\n")
        else:
            salidafinal.write(linea+'\n')
    salida.close()
    salidafinal.close()

##INPUTS:
####ListaDeLineasPorCod= lista que contiene listas por cada programa, que contiene tuplas
#### de la forma (linea,posicion)
##FUNCION:
####ELIMINA LAS LINEAS QUE TIENEN #ERROR
##OUTPUT:
####los mismos inputs pero modificados
def arreglarlista(ListaDeLineasPorCod):
    salidafinal=open("pseudocodigo.txt","r")
    for lines in salidafinal:
        for cod in ListaDeLineasPorCod:
            for linea in cod:
                if(linea[0] in lines):
                    if("#ERROR" in lines):
                        cod.remove(linea)
                    if(cod==listavacia):
                        ListaDeLineasPorCod.remove(cod)
    return ListaDeLineasPorCod

#------------------IF------------------#

##INPUTS:
####linea: una linea de la ListaDeLineasPorCod
####contador: posicion de la linea en el pseudocode.txt
####L: lista a la cual se le agregan parámetros
##FUNCION:
####BUSCA IFS,ELSES Y ENDIFS EN LAS LINEAS Y LUEGO SE LE AGREGA A L
##OUTPUTS:
####L: L contiene tuplas, 1=IF 2=ELSE 3=ENDIF y el segundo valor es su posicion
def reif(linea,contador,L):#contador del numero de la linea
    patron = re.compile("^(IF)\s+(.)*(THEN)\s*$")
    patrondos = re.compile("^(ELSE)\s*$")
    patrontres = re.compile("^END\s+IF\s*$")
    operador = patron.match(linea)
    operadordos = patrondos.match(linea)
    operadortres = patrontres.match(linea)
    if(operador!=None):
        L.append((1,contador))
    if(operadordos!=None):
        L.append((2,contador))
    if(operadortres!=None):
        L.append((3,contador))
    return L


##INPUT:
####cod: lista que contiene tuplaS de ifs o whiles con su respectiva posicion
##FUNCION:
####BUSCA EL UTLIMO IF O WHILE POR POSICION
##OUTPUT:
####linea2: es el ultimo if o while por posicion
def Buscarultimoif(cod):
    max=0
    for linea in cod:
        if(linea[1]>max and linea[0]==1):
            max=linea[1]
    for linea2 in cod:
        if(linea2[1]==max):
            return linea2
    return None

##INPUTS:
####cod: lista que contiene tuplaS de ifs/elses/endifs o whiles/endwhiles con su respectiva posicion
####iff: es la tupla (1,posicion) del while o if
##FUNCION:
####BUSCA EL ELSE O END WHILE MAS CERCA (HACIA ABAJO EN EL ARCHIVO) A LA VARIABLE iff
##OUTPUT:
####linea: es el else o end while mas cercano a iff
def Buscarelsemascercano(cod,iff):
    for linea in cod:
        if (linea[1]>iff[1] and linea[0]==2):
            return linea
    return None

##INPUTS:
####cod: lista que contiene tuplaS de ifs/elses/endifs o whiles/endwhiles con su respectiva posicion
####elsee: es la tupla (1,posicion) del else
##FUNCION:
####BUSCA EL END IF MAS CERCA (HACIA ABAJO EN EL ARCHIVO) A LA VARIABLE ELSEE
##OUTPUT:
####linea: es el end if mas cercano a elsee
def Buscarendifmascercano(cod,elsee):
    for linea in cod:
        if (linea[1]>elsee[1] and linea[0]==3):
            return linea
    return None

##INPUTS:
####iff: es la tupla (1,posicion) del if
####elsee: es la tupla (1,posicion) del else
####endif: es la tupla (1,posicion) del end if
####cod: lista que contiene tuplaS de ifs/elses/endifs o whiles/endwhiles con su respectiva posicion
##FUNCION:
####BUSCA ALGUN ELSE QUE SE ENCUENTRE ENTREMEDIO DEL TRIO DE IF/ELSE/ENDIF BUENO
##OUTPUT:
####L: lista con todos los else "colados"
def entremedioifs(iff,elsee,endif,cod):
    L=[]
    for linea in cod:
        if(linea[1]>iff[1] and linea[1]<endif[1]):
            if(linea!=elsee):
                L.append(linea)
    return L

##INPUTS:
####ListaDeIFsPorCod= lista con tuplas que contiene ifs,elses,endifs con su posicion
####trash: lista auxiliar con tuplas que contiene ifs,elses,endifs con su posicion y su posicion de codigo
##FUNCION:
####REVISA EL CASO ESPECIAL EN EL QUE EXISTA UN ELSE QUE NO TENGA NADA ESCRITO
#OUTPUT:
####los mismos inputs pero modificados
def ultimoelse(ListaDeIFsPorCod,trash):
    j=0
    for cod in ListaDeIFsPorCod:
        j+=1
        contadorelse=0
        contadorendif=0
        for a in cod:
            if(a[0]==2 and a[1]>contadorelse):
                contadorelse=a[1]
            if(a[0]==3 and a[1]>contadorendif):
                contadorendif=a[1]
            if(contadorelse+1 == contadorendif):
                tuplaaux=(2,contadorelse)####################3
                if(tuplaaux in cod):###########################
                    trash.append((2,contadorelse,j))
                    cod.remove((2,contadorelse))
        tamano=len(cod)
        if(tamano!=0):
            if(cod[tamano-1][0]==2):
                trash.append((cod[tamano-1][0],cod[tamano-1][1],j))
                cod.remove(cod[tamano-1])
    return (ListaDeIFsPorCod,trash)
##INPUTS:
####ListaDeIFsPorCod= lista con tuplas que contiene ifs,elses,endifs con su posicion
####Listadecosasbuenas: Lista con la posicion de las lineas que estan buenas
##FUNCION:
####ESTA ES LA FUNCION IMPORTANTE, MEZCLA TODAS LAS OTRAS FUNCIONES PARA QUE SOLO
####QUEDEN IFS/ELSES/ENDIFS MALOS EN LA ListaDeIFsPorCod
#OUTPUT:
####los mismos inputs pero modificados
def reif2(ListaDeIFsPorCod,Listadecosasbuenas):
    trash=[]
    j=0
    (ListaDeIFsPorCod,trash)=ultimoelse(ListaDeIFsPorCod,trash)
    for cod in ListaDeIFsPorCod:
        i=0
        j+=1
        largo=len(cod)
        while (i<largo):
            i+=1
            iff=Buscarultimoif(cod)
            if (iff != None and cod!=listavacia):
                elsee=Buscarelsemascercano(cod,iff)
                if (elsee!=None):
                    endif=Buscarendifmascercano(cod,elsee)
                    if (endif!=None):
                        L=entremedioifs(iff,elsee,endif,cod)
                        if (L!=None):
                            for basuraa in L:
                                trash.append((basuraa[0],basuraa[1],j))
                                cod.remove(basuraa)
                        Listadecosasbuenas.append(iff[1])
                        Listadecosasbuenas.append(elsee[1])
                        Listadecosasbuenas.append(endif[1])
                        cod.remove(iff)
                        cod.remove(elsee)
                        cod.remove(endif)
                    else:
                        trash.append((2,elsee[1],j))
                        cod.remove((2,elsee[1]))
                else:
                    trash.append((1,iff[1],j))
                    cod.remove((1,iff[1]))
    if(trash!=None):
        for lineatrash in trash:
            k=0
            for codigo in ListaDeIFsPorCod:
                k+=1
                if(k==lineatrash[2]):
                    codigo.append((lineatrash[0],lineatrash[1]))
    return (ListaDeIFsPorCod,Listadecosasbuenas)

##INPUTS:
####ListaDeIFsPorCod= lista con tuplas que contiene ifs,elses,endifs con su posicion
##FUNCION:
####SOBREESCRIBE EN EL ARCHIVO pseudocodigo.txt LAS LINEAS DE ListaDeIFsPorCod CON #ERROR
def actualizar(ListaDeIFsPorCod):
    salida=open("temporal.txt","r")
    salidafinal=open("pseudocodigo.txt","w")
    for cod in ListaDeIFsPorCod:
        if (cod!=listavacia):
            for lines in cod:
                kontador=0
                for linea in salida:
                    linea = linea.rstrip('\n')
                    kontador+=1
                    if(kontador==lines[1]):
                        if(" #ERROR" not in linea):
                            salidafinal.write(linea+ " #ERROR\n")
                    else:
                        salidafinal.write(linea+'\n')
                salida.seek(0,0)
                salidafinal.seek(0,0)
                SalidaaTemporal()
    salida.close()
    salidafinal.close()

#------------------IF------------------#

#------------------PROCEDURE------------------#

##INPUTS:
####Lnumlinea: posiciones de los procedures y endprocedures malos
##FUNCION:
####SOBREESCRIBE EN EL ARCHIVO pseudocodigo.txt LAS LINEAS DE Lnumlinea CON #ERROR
def Arreglar2(Lnumlinea):
    salidafinal = open("pseudocodigo.txt", "r")###cambiar los nombreees XDdxd
    salida = open("temporal.txt", "w")###cambiar los nombreees XDdxdd
    c=0
    for lines in salidafinal:
        lines = lines.rstrip('\n')
        salida.write(lines+'\n')
    salidafinal.close()
    salida.close()
    salidafinal = open("pseudocodigo.txt", "w")###cambiar los nombreees XDdxd
    salida = open("temporal.txt", "r")###cambiar los nombreees XDdxdd
    for lines in salida:
        c+=1
        lines = lines.rstrip('\n')
        if(c in Lnumlinea):
            if(" #ERROR" not in lines):
                salidafinal.write(lines+' #ERROR\n')
        else:
            salidafinal.write(lines+'\n')
    salida.close()
    salidafinal.close()

##INPUTS:
####contp: posicion del ultimo procedure
####La: lista A que contiene tuplas con las lineas entre procedures, junto con su posicion
##FUNCION:
####REVISA EL CASO ESPECIAL EN EL QUE EL ULTIMO PROCEDURE NO TENGA END PROCEDURE
def Arreglar(contp,La):
    salida = open("temporal.txt", "r")###cambiar los nombreees XDdxdd
    c=0
    for lines in salida:
        c+=1
        lines = lines.rstrip('\n')
        if(boolPROCEDURE==True and boolENDPROCEDURE==False):
            if(c<contp):
                salidafinal.write(lines+'\n')
            else:
                if(" #ERROR" not in lines):
                    salidafinal.write(lines+' #ERROR\n')
                    for cod in La:
                        for li in cod:
                            if(lines in li[0]):
                                cod.remove(li)
        else:
            salidafinal.write(lines+'\n')
    salida.close()

##INPUTS:
####linea: linea del pseudocode.txt
##FUNCION:
####ESCRIBE EN EL ARCHIVO TEMPORAL.TXT CON #ERROR
##OUTPUT:
####RETORNA 1 SI ESCRIBE CORRECTAMENTE LA LINEA, 0 SI NO LO HACE
def Basura(linea):
    if(" #ERROR" not in linea):
        if(salida.write(linea+' #ERROR\n')):
            return 1
        else:
            return 0

#------------------PROCEDURE------------------#

#------------------BOOLEANOS------------------#

##INPUTS:
####linea: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A BOOLEANOS
##OUTPUT:
####RETORNA TRUE SI ES BOOLEANO Y FALSE SI NO LO ES
def booleanos(linea):
    patron = re.compile("^(.+)\s+(AND|OR|EQUAL TO|DIFFERENT TO)\s+(.+)$")
    patrondos = re.compile("^(NO)\s+(.+)$")
    variable = re.compile("^(1|0|[a-zA-Z][a-zA-Z0-9]+)+$")
    patrontres = re.compile("^(GREATER|SMALLER)\s+([0-9]+|[a-zA-Z][a-zA-Z0-9]+)\s+THAN\s+([0-9]+|[a-zA-Z][a-zA-Z0-9]+)\s*$")
    operadordos = variable.match(linea)
    operador = patron.match(linea)
    operadortres = patrondos.match(linea)
    operadorcuatro = patrontres.match(linea)
    entro = 0
    izquierdo = 0
    derecho = 0
    if(operador != None and entro == 0):
        entro = 1
        if(booleanos(operador.group(1)) != False):
            izquierdo = 1
        if(booleanos(operador.group(3)) != False):
            derecho = 1
        if(izquierdo == 1 and derecho == 1):
            derecho = 0
            izquierdo = 0
            return True
        else:
            return False
    elif(operadordos != None and entro == 0):
        entro = 1
        return True
    elif(operadortres != None and entro == 0):
        entro = 1
        if(booleanos(operadortres.group(2)) != False):
            return True
        else:
            return False
    elif(operadorcuatro != None and entro == 0):
        entro = 1
        return True
    else:
        return False

#------------------BOOLEANOS------------------#

#------------------MATEMATICOS------------------#

##INPUTS:
####linea: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A MATEMATICOS
##OUTPUT:
####RETORNA TRUE SI ES MATEMATICO Y FALSE SI NO LO ES
def matematicos(linea):
    patron = re.compile("^(SUM|SUBSTRACT|MULTIPLY|DIVIDE)\s+(.+)\s+TO\s+(.+)$")
    patrondos = re.compile("^(SUM|SUBSTRACT|MULTIPLY|DIVIDE)\s+(.+)\s+TO\s+(.+)$")
    patrontres = re.compile("^(SUM|SUBSTRACT|MULTIPLY|DIVIDE)\s+(\w+)\s+TO\s+(SUM\s+.+|SUBSTRACT\s+.+|MULTIPLY\s+.+|DIVIDE\s+.+)$")
    patroncuatro = re.compile("^(SUM|SUBSTRACT|MULTIPLY|DIVIDE)\s+(.+)\s+TO\s+(SUM\s+.+|SUBSTRACT\s+.+|MULTIPLY\s+.+|DIVIDE\s+.+)$")
    variable = re.compile("^([a-zA-Z0-9]+)$")
    operador = patron.match(linea)
    operadordos = patrondos.match(linea)
    operadortres = patrontres.match(linea)
    operadorcuatro = patroncuatro.match(linea)
    izquierdo = 0
    derecho = 0
    entro = 0

    if(operadortres != None and entro != 1):
        entro = 1
        if(matematicos((operadortres.group(2))) != False):
            izquierdo = 1
        if(matematicos((operadortres.group(3))) != False):
            derecho = 1
        if(variable.match(operadortres.group(2)) != None and operadortres.group(2) != "SUM" and operadortres.group(2) != "SUBSTRACT" or operadortres.group(2) != "MULTIPLY" and operadortres.group(2) != "DIVIDE" and izquierdo == 0):
            izquierdo = 1
        if(variable.match(operadortres.group(3)) != None and operadortres.group(3) != "SUM" and operadortres.group(3) != "SUBSTRACT" or operadortres.group(3) != "MULTIPLY" and operadortres.group(3) != "DIVIDE" and derecho == 0):
            derecho = 1
        if(izquierdo == 1 and derecho == 1):
            return True
        else:
            return False

    elif(operadorcuatro != None and entro != 1):
        entro = 1
        if(matematicos((operadorcuatro.group(2))) != False):
            izquierdo = 1
        if(matematicos((operadorcuatro.group(3))) != False):
            derecho = 1
        if(variable.match(operadorcuatro.group(2)) != None and operadorcuatro.group(2) != "SUM" and operadorcuatro.group(2) != "SUBSTRACT" and operadorcuatro.group(2) != "MULTIPLY" and operadorcuatro.group(2) != "DIVIDE" and izquierdo == 0):
            izquierdo = 1
        if(variable.match(operadorcuatro.group(3)) != None and operadorcuatro.group(3) != "SUM" and operadorcuatro.group(3) != "SUBSTRACT" and operadorcuatro.group(3) != "MULTIPLY" and operadorcuatro.group(3) != "DIVIDE"and derecho == 0):
            derecho = 1
        if(izquierdo == 1 and derecho == 1):
            return True
        else:
            return False
    elif(operador != None and entro != 1):
        entro = 1
        if(matematicos((operador.group(2))) != False):
            izquierdo = 1
        if(matematicos((operador.group(3))) != False):
            derecho = 1
        if(variable.match(operador.group(2)) != None and operador.group(2) != "SUM" and operador.group(2) != "SUBSTRACT" and operador.group(2) != "MULTIPLY" and operador.group(2) != "DIVIDE" and izquierdo == 0):
            izquierdo = 1
        if(variable.match(operador.group(3)) != None and operador.group(3) != "SUM" and operador.group(3) != "SUBSTRACT" and operador.group(3) != "MULTIPLY" and operador.group(3) != "DIVIDE" and derecho == 0):
            derecho = 1
        if(izquierdo == 1 and derecho == 1):
            return True
        else:
            return False
    elif(operadordos != None and entro != 1):
        entro = 1
        if(matematicos((operadordos.group(2))) != False):
            izquierdo = 1
        if(matematicos((operadordos.group(3))) != False):
            derecho = 1
        if(variable.match(operadordos.group(2)) != None and (operadordos.group(2) != "SUM" and operadordos.group(2) != "SUBSTRACT" and operadordos.group(2) != "MULTIPLY" and operadordos.group(2) != "DIVIDE") and izquierdo == 0):
            izquierdo = 1
        if(variable.match(operadordos.group(3)) != None and (operadordos.group(3) != "SUM" and operadordos.group(3) != "SUBSTRACT" and operadordos.group(3) != "MULTIPLY" and operadordos.group(3) != "DIVIDE") and derecho == 0):
            derecho = 1
        if(izquierdo == 1 and derecho == 1):
            return True
        else:
            return False
    else:
        return False

#------------------MATEMATICOS------------------#

#------------------RE FACILES------------------#

##INPUTS:
####cadena: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A PRINT
##OUTPUT:
####RETORNA TRUE SI ES PRINT Y FALSE SI NO LO ES
def reprint(cadena):
    patron = re.compile("^(PRINT)\s+(.*)\s*$")
    patron2 = re.compile('^(PRINT)\s+("\w+")$')
    entro = 0
    if(patron2.match(cadena) != None and entro == 0):
        entro = 1
        return True
    elif(patron.match(cadena) != None and entro == 0):
        entro = 1
        if(matematicos(patron.match(cadena).group(2)) == True):
            return True
        elif(booleanos(patron.match(cadena).group(2)) == True):
            return True
        else:
            return False
    else:
        return False

##INPUTS:
####cadena: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A READ
##OUTPUT:
####RETORNA 1 SI ES READ Y NADA SI NO LO ES
def reread(cadena):
    patron=re.compile("(READ)\s+([a-zA-Z][a-zA-Z0-9]+)")
    a=patron.fullmatch(cadena)
    if(a!=None):
        return 1

##INPUTS:
####cadena: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A INITIALIZE
##OUTPUT:
####RETORNA 1 SI ES INITIALIZE Y NADA SI NO LO ES
def reinitialize(cadena):
    patron=re.compile("(INITIALIZE)\s+([a-zA-Z][a-zA-Z0-9]+)")
    a=patron.fullmatch(cadena)
    if(a!=None):
        return 1

##INPUTS:
####cadena: linea del pseudocode.txt
##FUNCION:
####REVISA SI LA LINEA CORRESPONDE A SET
##OUTPUT:
####RETORNA TRUE SI ES SET Y RETORNA FALSE SI NO LO ES
def set(cadena):
    patron = re.compile("^(SET)\s+([a-zA-Z][a-zA-Z0-9]+)\s+TO\s+(.+)\s*$")
    variable = re.compile("([0-9]+)")
    operador = patron.match(cadena)
    if(operador != None):
        if(variable.match(operador.group(3)) != None):
            return True
        elif(booleanos(operador.group(3))):
            return True
        elif(matematicos(operador.group(3))):
            return True

        else:
            return False
    else:
        return False

#------------------RE FACILES------------------#

#------------------WHILE------------------#

##INPUTS:
####linea: linea del pseudocode.txt entremedio de los procedure
####contador: posicion de la linea en el pseudocode.txt
####L: lista inicialmente vacia
##FUNCION:
####AGREGA A L LOS WHILES(1) Y END WHILES(2) CON SUS POSICIONES, POR CODIGO
##OUTPUT:
####L: lista con whiles(1) y end whiles(2) con sus posiciones, por codigo
def rewhilee(linea,contador,L):#### ANALOGO DE LA FUNCION REIF
    reWHILE = re.compile(r'^WHILE\s+(\w+)\s*$')
    reENDWHILE = re.compile(r'^END\s+WHILE\s*$')
    operador = reWHILE.match(linea)
    operadordos = reENDWHILE.match(linea)
    if(operador!=None):
        L.append((1,contador))
    if(operadordos!=None):
        L.append((2,contador))
    return L

##INPUTS:
####ListaDeWhilesPorCod: lista con tuplas que contiene whiles y end whiles con su posicion
####Listadecosasbuenas: Lista con la posicion de las lineas que estan buenas
##FUNCION:
####ESTA ES LA FUNCION IMPORTANTE, MEZCLA TODAS LAS OTRAS FUNCIONES PARA QUE SOLO
####QUEDEN WHILES/ENDWHILES MALOS EN LA ListaDeWhilesPorCod
##OUTPUT:
####los mismos inputs pero modificados
def rewhile2(ListaDeWhilesPorCod,Listadecosasbuenas):#### ANOLOGO DE LA FUNCION REIF2
    trash=[]
    j=0
    for cod in ListaDeWhilesPorCod:
        tamano=len(cod)
        j+=1
        i=0
        while(i<tamano):
            i+=1
            if(cod!=listavacia):
                whilee=Buscarultimoif(cod)
            if(whilee != None and cod!=listavacia):
                endwhile=Buscarelsemascercano(cod,whilee)
                if(endwhile!=None):
                    largo=len(cod)
                    if(whilee[1]+1==endwhile[1] or whilee[1]==cod[largo-1][1]):
                        trash.append((1,whilee[1],j))
                        trash.append((2,endwhile[1],j))
                        cod.remove((1,whilee[1]))
                        cod.remove((2,endwhile[1]))
                    else:
                        Listadecosasbuenas.append(whilee[1])
                        Listadecosasbuenas.append(endwhile[1])
                        cod.remove((1,whilee[1]))
                        cod.remove((2,endwhile[1]))
                else:
                    trash.append((1,whilee[1],j))
                    cod.remove((1,whilee[1]))
    if(trash!=None):
        for lineatrash in trash:
            k=0
            for codigo in ListaDeWhilesPorCod:
                k+=1
                if(k==lineatrash[2]):
                    codigo.append((lineatrash[0],lineatrash[1]))
    return (ListaDeWhilesPorCod,Listadecosasbuenas)

#------------------WHILE------------------#


## PARTE PRINCIPAL, BUSCA LOS PROCEDURES CORRECTOS
## ADEMAS, ESCRIBE EN TEMPORAL EL RESTO DE LINEAS QUE SERAN LUEGO ANALIZADAS
rePROCEDURE = re.compile(r'^PROCEDURE\s+(\w+)\s*$')
reENDPROCEDURE = re.compile(r'^END\s+PROCEDURE\s*$')
ProblemaProcedure = re.compile(r'^PROCEDURE\s+(SUM)\s*$')
contador=0
contp=0
conte=0
unoantes = 0
Listaaux=[]
Lnumlinea=[]
L=[]
La=[]#lista de listas de lineas entremedio de los procedures
Lb=[]#lista de lineas entremedio del procedure
Listadecosasbuenas=[]
for linea in archivo:#SACAMOS LAS LINEAS DEL pseudocode.txt PARA PONERLAS EN LISTAS
    contador+=1
    linea = linea.rstrip('\n')
    procedure=rePROCEDURE.match(linea)
    endprocedure=reENDPROCEDURE.match(linea)
    problema1 = ProblemaProcedure.match(linea)
    if(problema1 == None):
        if(procedure != None):
            salida.write(linea+'\n')
            La.append(Lb)
            Lb=[]
            if(boolPROCEDURE==True):
                Lnumlinea.append(contp)
                Listaaux.append((contp,contador))
                contp=contador
            if(boolPROCEDURE==False and boolENDPROCEDURE == False):
                contp = contador
                boolPROCEDURE = True
            if(contp+1 == conte):
                Lnumlinea.append(contp)
                Lnumlinea.append(conte)
        elif(endprocedure != None):
            if(boolENDPROCEDURE == False and boolPROCEDURE == True):
                salida.write(linea+'\n')
                boolPROCEDURE=False
                conte = contador
            elif(boolENDPROCEDURE == False and boolPROCEDURE == False):
                Basura(linea)
            if(contp+1 == conte):
                Lnumlinea.append(contp)
                Lnumlinea.append(conte)
        elif(endprocedure == None and boolPROCEDURE == True):
            Lb.append((linea,contador))
            salida.write(linea+'\n')
        else:
            Basura(linea)
    else:
        salida.write(linea+' #ERROR\n')
archivo.close()
salida.close()
La.append(Lb)



if(Listaaux!=listavacia):#AGREGA CASOS MALOS A Lnumlinea
    for helplistaaux in Listaaux:
        i=helplistaaux[0]
        while(i<helplistaaux[1]-1):
            i+=1
            Lnumlinea.append(i)

if(contp == 0 or conte==0):#SI NO EXISTEN PROCEDURES PRINTEA "NO HAY PROGRAMA"
    if(salidafinal.write("NO HAY PROGRAMA")):
        exit(0)
else:
    Arreglar(contp,La)

salidafinal.close()
Arreglar2(Lnumlinea)
SalidaaTemporal()

#------------------IF------------------#

ListaDeIFsPorCod=[]#LISTA DE LAS POSICIONES RELACIONADAS CON EL IF
ListaDeLineasPorCod=[]#LISTA DE CADA LINEA, SEPARADAS POR PROGRAMAS
Laux=[]
for lineaLa in La:
    if (lineaLa!=listavacia):#ESTE FOR Y LA FUNCION FUERA DEL FOR SON PARA CREAR LA LISTADELINEASPORCOD
        Laux=[]
        for lineaLa2 in lineaLa:
            Laux.append(lineaLa2)
        ListaDeLineasPorCod.append(Laux)

ListaDeLineasPorCod=arreglarlista(ListaDeLineasPorCod)

for aa in ListaDeLineasPorCod:#CREA LA LISTADEIFSPORCOD
    if (aa!=listavacia):
        L=[]
        for bb in aa:
            L=reif(bb[0],bb[1],L)
        if(L!=listavacia):
            ListaDeIFsPorCod.append(L)
        if(L==listavacia):
            ListaDeIFsPorCod.append(L)
(ListaDeIFsPorCod,Listadecosasbuenas)=reif2(ListaDeIFsPorCod,Listadecosasbuenas)#EVALUA LA LISTADEIFSPORCOD
actualizar(ListaDeIFsPorCod)#ARREGLA LA LISTADEIFSPORCOD

#------------------IF------------------#

#------------------WHILE------------------#

L=[]
ListaDeWhilesPorCod=[]#LISTA DE LAS POSICIONES RELACIONADAS CON EL WHILE
for lineaa in ListaDeLineasPorCod:#CREA LA LISTADEWHILESPORCOD
    if(lineaa!=listavacia):
        L=[]
        for lineaa2 in lineaa:
            L=rewhilee(lineaa2[0],lineaa2[1],L)
        if(L!=listavacia):
            ListaDeWhilesPorCod.append(L)
    if(L==listavacia):
        ListaDeWhilesPorCod.append(L)

(ListaDeWhilesPorCod,Listadecosasbuenas)=rewhile2(ListaDeWhilesPorCod,Listadecosasbuenas)#EVALUA LA LISTADEWHILESPORCOD
actualizar(ListaDeWhilesPorCod)#ARREGLA LA LISTADEWHILESPORCOD

cosasbuenas(Listadecosasbuenas)#IMPRIME COMO #BUENO LO QUE ESTA BUENO, DESPUES LO ARREGLAMOS, ERA SOLO PARA GUIARNOS
SalidaaTemporal()
#------------------WHILE------------------#

##ACA SE EJECUTA TODO LO QUE ES LA TRADUCCION DE LAS PALABRAS(SE REVISA SI LAS LINEAS SON CORRECTAS ANTES DE TRADUCIR)
##TRADUCE Y ESCRIBE EN EL ARCHIVO DE SALIDA FINAL, BORRA EL ARCHIVO TEMPORAL
patronset = re.compile("^(SET)\s+([a-zA-Z][a-zA-Z0-9]+)\s+(TO)\s+(.+)$")
salida = open("temporal.txt", "r")###cambiar los nombreees XDdxdd
salidafinal=open("pseudocodigo.txt", "w")###cambiar los nombreees XDdxdd
salida.seek(0,0)
print(Fore.BLACK+"----------------")
for prueba in salida:
    linea = prueba.rstrip('\n')
    patron = re.compile("^(\w+)$")
    if(patron.match(linea)):
        salidafinal.write(linea+' #ERROR\n')
        print(Fore.RED+linea+' #ERROR')
    elif(booleanos(linea)):
        if("AND" in linea):
            linea = re.sub("AND", "Y", linea)
        if("OR" in linea):
            linea = re.sub("OR", "O", linea)
        if("EQUAL TO" in linea):
            linea = re.sub("EQUAL TO", "IGUAL A", linea)
        if("DIFFERENT TO" in linea):
            linea = re.sub("DIFFERENT TO", "DIFERENTE A", linea)
        if("GREATER" in linea):
            linea = re.sub("GREATER", "MAYOR", linea)
        if("SMALLER" in linea):
            linea = re.sub("SMALLER", "MENOR", linea)
        linea = re.sub("THAN", "QUE", linea)
        print(Fore.GREEN+linea)
        salidafinal.write(linea+'\n')
    elif(matematicos(linea)):
        if("SUBSTRACT" in linea):
            linea = re.sub("SUBSTRACT", "RESTA", linea)
        if("SUM" in linea):
            linea = re.sub("SUM", "SUMA", linea)
        if("MULTIPLY" in linea):
            linea = re.sub("MULTIPLY", "MULTIPLICA", linea)
        if("DIVIDE" in linea):
            linea = re.sub("DIVIDE", "DIVIDE", linea)
        linea = re.sub("TO", "A", linea)
        print(Fore.GREEN+linea)
        salidafinal.write(linea+'\n')
    elif(reprint(linea)):
        if("PRINT" in linea):
            linea = re.sub("PRINT", "IMPRIME", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif(reread(linea)):
        if("READ" in linea):
            linea = re.sub("READ", "LEER", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif(reinitialize(linea)):
        if("INITIALIZE" in linea):
            linea = re.sub("INITIALIZE", "INICIALIZA", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif(set(linea)):
        setear = patronset.match(linea)
        if(setear):
            cadenatemporal = setear.group(1)+" "+setear.group(2)+" "+setear.group(3)

            cadenatemporal2 = setear.group(4)
            cadenatemporal = re.sub(setear.group(1), "FIJA", cadenatemporal)
            cadenatemporal = re.sub(setear.group(3), "EN", cadenatemporal)

            if(patron.match(cadenatemporal2)):
                salidafinal.write(cadenatemporal+" "+cadenatemporal2+' #ERROR\n')
                print(Fore.RED+linea+' #ERROR')
            elif(booleanos(cadenatemporal2)):
                if("AND" in cadenatemporal2):
                    cadenatemporal2 = re.sub("AND", "Y", cadenatemporal2)
                if("OR" in cadenatemporal2):
                    cadenatemporal2 = re.sub("OR", "O", cadenatemporal2)
                if("EQUAL TO" in cadenatemporal2):
                    cadenatemporal2 = re.sub("EQUAL TO", "IGUAL A", cadenatemporal2)
                if("DIFFERENT TO" in cadenatemporal2):
                    cadenatemporal2 = re.sub("DIFFERENT TO", "DIFERENTE A", cadenatemporal2)
                if("GREATER" in cadenatemporal2):
                    cadenatemporal2 = re.sub("GREATER", "MAYOR", cadenatemporal2)
                if("SMALLER" in cadenatemporal2):
                    cadenatemporal2 = re.sub("SMALLER", "MENOR", cadenatemporal2)
                    cadenatemporal2 = re.sub("THAN", "QUE", cadenatemporal2)
            elif(matematicos(cadenatemporal2)):
                if("SUBSTRACT" in cadenatemporal2):
                    cadenatemporal2 = re.sub("SUBSTRACT", "RESTA", cadenatemporal2)
                if("SUM" in cadenatemporal2):
                    cadenatemporal2 = re.sub("SUM", "SUMA", cadenatemporal2)
                if("MULTIPLY" in cadenatemporal2):
                    cadenatemporal2 = re.sub("MULTIPLY", "MULTIPLICA", cadenatemporal2)
                if("DIVIDE" in cadenatemporal2):
                    cadenatemporal2 = re.sub("DIVIDE", "DIVIDE", cadenatemporal2)
                cadenatemporal2 = re.sub("TO", "A", cadenatemporal2)
        linea=cadenatemporal+" "+cadenatemporal2
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif("#BUENO" in linea):
        if("IF" in linea):
            linea = re.sub("IF", "SI", linea)
        if("THEN" in linea):
            linea = re.sub("THEN", "ENTONCES", linea)
        if("ELSE" in linea):
            linea = re.sub("ELSE", "SINO", linea)
        if("END" in linea):
            if("WHILE" in linea):
                linea = re.sub("WHILE", "MIENTRAS", linea)
            linea = re.sub("END", "FIN", linea)
        if("WHILE" in linea):
            linea = re.sub("WHILE", "MIENTRAS QUE", linea)
        linea = re.sub(" #BUENO", "", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif("PROCEDURE" in linea and "#ERROR" not in linea and " PROCEDURE" not in linea):
        linea = re.sub("PROCEDURE", "PROCEDIMIENTO", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif("PROCEDURE" in linea and "#ERROR" not in linea and " END PROCEDURE" not in linea):
        linea = re.sub("END PROCEDURE", "FIN PROCEDIMIENTO", linea)
        salidafinal.write(linea+'\n')
        print(Fore.GREEN+linea)
    elif("#ERROR" in linea):
        if(linea != " #ERROR"):
            salidafinal.write(linea+'\n')
            print(Fore.RED+linea)
    else:
        if(linea != ""):
            salidafinal.write(linea+' #ERROR\n')
            print(Fore.RED+linea+' #ERROR')

print(Fore.BLACK+"----------------")
salida.close()
salidafinal.close()
os.remove("temporal.txt")#BORRAMOS EL ARCHIVO TEMPORAL
