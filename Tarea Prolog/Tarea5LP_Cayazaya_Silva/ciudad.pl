camino(greenpath,canyon,10).
camino(canyon,garden,12).
camino(garden,fungal,16).
camino(canyon,fungal,3).
camino(greenpath,crossroad,4).
camino(crossroad,fungal,5).
camino(crossroad,dirtmouth,1).
camino(crystal_peak,dirtmouth,5).
camino(crystal_peak,resting_grounds,8).
camino(resting_grounds,blue_lake,3).
camino(crossroad,blue_lake,2).
camino(resting_grounds,edge,14).
camino(edge,hive,3).
camino(edge,waterways,6).
camino(waterways,city_of_tears,7).
camino(waterways,fungal,10).
camino(fungal,deepnest,10).
camino(deepnest,beast_den,8).
camino(crossroad,city_of_tears,4).
camino(resting_grounds,city_of_tears,3).

/*C = ciudad2 T = tiempo entre ciudades*/
ida_vuelta(Ciudad,C,T):-camino(Ciudad,C,T);camino(C,Ciudad,T).

/*R1 = camino que se va formando*/
path(C,Tiempo,Res, R1):- Tiempo < 0,delete(R1,C,Res),!.

path(Ciudad,Tiempo,Res,R1):-
    ida_vuelta(Ciudad,C,T),
    Tiempo_aux is (Tiempo - T), append(R1,[C],Raux),
    path(C,Tiempo_aux,Res,Raux).

/*Res = Resultado*/
alcanzables(Ciudad,Tiempo,Res):-
    R1 = [Ciudad],
    findall(V,path(Ciudad,Tiempo,V,R1),L),
    flatten(L,L1),
    list_to_set(L1,Res).



