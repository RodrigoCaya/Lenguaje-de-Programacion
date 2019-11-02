hijo(rorrito,tomc,tumc).
hijo(pantufla,tomc,tumc).
hijo(aedo,pantufla,lucio).
hijo(gabo,fludd,smash).
hijo(caya,smash,zelda).
hijo(almendra,aedo,gabo).
hijo(diosito,aedo,gabo).
hijo(axel,almendra,diosito).

hermano_aux(X,Y):-hijo(X,P,M),hijo(Y,P,M),not(X=Y).
hermano(X,Y):- findall(V,hermano_aux(X,V),Y).

hijos_aux(X,Y):-hijo(H,X,_),append([],H,Y).
hijos_aux(X,Y):-hijo(H,_,X),append([],H,Y).
hijos(X,Y):- findall(V,hijos_aux(X,V),Y).

padres_aux(X,Y):-hijo(X,Y1,Y2),append([Y1],[Y2],Y).
padres(X,Y):- findall(V,padres_aux(X,V),L),
    flatten(L,Y).

respuesta(X,Y):- padres(X,Y1), hermano(X,Y2), hijos(X,Y3), append([Y1],[Y2],R12), append(R12,[Y3],Y).