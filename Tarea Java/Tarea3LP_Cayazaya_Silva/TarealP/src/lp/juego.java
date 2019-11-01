package lp;
import java.util.Scanner;
import java.util.ArrayList;

public class juego {
	static Scanner lootbox;
	public static void main(String[] args) {
		System.out.println("~~BIENVENIDO AL MUNDO DE LARUTO PUZUMAKI~~\n");
		Scanner name = new Scanner(System.in);
		System.out.println("Elige tu nombre de personaje: ");
		String nombre=name.nextLine();

		System.out.println("\n |(1)PYTHONIA| |(2)CTHONIA| |(3)JAVANIA| |(4)SCHEMIA| |(5)PROLONIA|\n");

		Scanner ald = new Scanner(System.in);
		System.out.println("Elige el numero tu aldea: ");
		int aldeatemp=ald.nextInt();

		String aldea="";
		if(aldeatemp==1) aldea="Pythonia";
		if(aldeatemp==2) aldea="Cthonia";
		if(aldeatemp==3) aldea="Javania";
		if(aldeatemp==4) aldea="Schemia";
		if(aldeatemp==5) aldea="Prolonia";
		int opmision=0;
		int vida=100;
		int nivel=1;
		int oro;
		int rndm;
		int atk=3;
		String obj="Puno";
		String rango="Ifnin";
		jugador pj = new jugador(vida,aldea,nombre,atk,obj,rango,nivel,vida);
		pj.getNinja().asignarNivelJutsus(pj);
		enemigo pythoniakage = new enemigo(vida,"Pythonia","Pykage Comic San",3,"Fierro con Clavos","Kage",15,vida);
		enemigo cthoniakage = new enemigo(vida,"Chtonia","Ckage Papa Noel",3,"Fierro con Clavos","Kage",15,vida);
		enemigo javaniakage = new enemigo(vida,"Javania","Javakage Pepe",3,"Fierro con Clavos","Kage",15,vida);
		enemigo schemiakage = new enemigo(vida,"Pythonia","Schekage Alabarlaalabarda Sama",3,"Fierro con Clavos","Kage",15,vida);
		enemigo proloniakage = new enemigo(vida,"Pythonia","Prokage Porlosbor Des",3,"Fierro con Clavos","Kage",15,vida);
		enemigo ifnin1 = new enemigo(vida,aldea,"Jean Franco Zarate",atk,obj,rango,0,vida);
		enemigo ifnin2 = new enemigo(vida,aldea,"Sebastian Tomichin",atk,obj,rango,0,vida);
		enemigo ifnin3 = new enemigo(vida,aldea,"Etyel Kramona",atk,obj,rango,0,vida);
		enemigo ifnin4 = new enemigo(vida,aldea,"Murio chupo",atk,obj,rango,0,vida);
		enemigo ifnin5 = new enemigo(vida,aldea,"Cual chupo",atk,obj,rango,0,vida);
		enemigo ifnin6 = new enemigo(vida,aldea,"Esta",atk,obj,rango,0,vida);
		ifnin1.getNinja().asignarNivelJutsus(ifnin1);
		ifnin2.getNinja().asignarNivelJutsus(ifnin2);
		ifnin3.getNinja().asignarNivelJutsus(ifnin3);
		ifnin4.getNinja().asignarNivelJutsus(ifnin4);
		ifnin5.getNinja().asignarNivelJutsus(ifnin5);
		ifnin6.getNinja().asignarNivelJutsus(ifnin5);
		ArrayList<enemigo> enemigos = new ArrayList<enemigo>();
		enemigos.add(ifnin1);
		enemigos.add(ifnin2);
		enemigos.add(ifnin3);
		enemigos.add(ifnin4);
		enemigos.add(ifnin5);
		int turno=1;
		boolean flag=true;
		boolean flag2=true;
		boolean flag3=true;
		Scanner opcionMision = new Scanner(System.in);
		Scanner opcion = new Scanner(System.in);
		int op=0;
		int rndm2;
		int rndm3;
		int c1=0,c2=0,c3=0,c4=0,c5=0;
		while(op!=3) {
			pj.asignarRango();
			pj.getMenu();
			op=opcion.nextInt();
			if(op==2) {
				Fortuna(pj);
			}
			if(op==1) {
				if(pj.getNivel() < 11 || pj.getRango() == "Kage") {
					pj.getMenuMision();
					opmision=opcionMision.nextInt();
					if (opmision==1) {//ACA SE HACE LA MISION DE RANGO D
						if(pj.getRango()!="Ifnin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							oro =  (int) (Math.random() *5) + 5;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(1);
							malo.asignarRango("Ifnin");
							malo.asignarVidaMax();
							flag = true;
							rndm2 = (int) (Math.random() * 100) + 1;
							if(rndm2 > 50) {
								while(flag) {//PELEA CON 1 ENEMIGO
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());

										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										//System.out.printf("\nHaz recuperado 10 de energia y ganado %s de oro\n", oro);
										pj.asignarExperiencia(10);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(10, oro);
										turno=1;
										flag = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {//NO HAY PELEA
								//oro =  (int) (Math.random() *5) + 5;
								//System.out.printf("\nObtienes %s de oro de la mision\n", oro);

								pj.asignarExperiencia(10);
								pj.asignarOro(pj.getOro()+oro);
								System.out.println("\nNo te encontraste con ningun enemigo");
								pj.getMenuMisionTerminada(10, oro);
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==2) {//ACA SE HACE LA MISION DE RANGO C
						if(pj.getRango()!="Ifnin" && pj.getRango()!="Fornin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							oro =  (int) (Math.random() *5) + 5;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(4);
							malo.asignarRango("Fornin");
							malo.getNinja().asignarNivelJutsus(malo);
							malo.asignarVidaMax();

							flag = true;
							while(flag) {//PELEA CON 1 ENEMIGO

								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
									pj.realizarAtaque(malo, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo.realizarAtaque(malo, pj);
									turno = 1;
								}
								if(malo.getVida() <= 0) {
									pj.asignarExperiencia(10);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(10, oro);
									turno=1;
									flag = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									pj.asignarVida(pj.getVidaMax());
								}
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==3) {//ACA SE HACE LA MISION DE RANGO B
						if(pj.getRango()!="Fornin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *30) + 50;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(6);
							malo.asignarRango("Fornin");
							malo.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm);
							malo2.asignarNivel(6);
							malo2.asignarRango("Fornin");
							malo2.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm);
							malo3.asignarNivel(6);
							malo3.asignarRango("Fornin");
							malo3.asignarVidaMax();

							flag = true;
							flag2 = true;
							if(rndm2>50) {
								while(flag) {
									if(turno == 1) {//PELEA CON 1 ENEMIGO
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tu oponente!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								while(flag) {
									if(turno == 1) {//PELEA CON 2 ENEMIGOS
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");

										}
										else {
											System.out.println("Vida del oponente 2: " +malo2.getVida());
										}
										pj.realizarAtaque2(malo,malo2, pj);
										turno = 2;
									}
									if(turno == 2 && malo.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 3;
									}
									if(malo.getVida()<=0) {
										System.out.println("\nxdddddd");
										turno = 3;
									}
									if(turno == 3 && malo2.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo2.realizarAtaque(malo2, pj);
										turno = 1;
									}
									if(malo2.getVida()<=0) {
										turno = 1;
									}
									if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tus oponentes!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							while(flag2) {
								if(turno == 1) {//PELEA CON 1 ENEMIGO
									System.out.println("\nHa aparecido un nuevo rival!!");
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo3.getNombre()+": " +malo3.getVida());
									pj.realizarAtaque(malo3, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo3.realizarAtaque(malo3, pj);
									turno = 1;
								}
								if(malo3.getVida() <= 0) {
									pj.asignarExperiencia(45);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(45, oro);
									turno=1;
									flag2 = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag2 = false;
									pj.asignarVida(pj.getVidaMax());
								}
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==4) {//ACA SE HACE LA MISION DE RANGO A
						if(pj.getRango()!="Whilenin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							rndm3 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *20) + 100;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(8);
							malo.asignarRango("Whilenin");
							malo.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm);
							malo2.asignarNivel(8);
							malo2.asignarRango("Whilenin");
							malo2.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm);
							malo3.asignarNivel(8);
							malo3.asignarRango("Whilenin");
							malo3.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c4=rndm;
								if(c1!=c4 && c2 != c4 && c3!=c4) flag = false;
							}
							enemigo malo4 = enemigos.get(rndm);
							malo4.asignarNivel(8);
							malo4.asignarRango("Whilenin");
							malo4.asignarVidaMax();

							flag = true;
							flag2 = true;
							if(rndm2>50) {
								while(flag) {//PELEA CON 1 ENEMIGO
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tu oponente!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								while(flag) {//PELEA CON 2 ENEMIGOS
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");
										}
										else {
											System.out.println("Vida del oponente 2: " +malo2.getVida());
										}
										pj.realizarAtaque2(malo,malo2, pj);
										turno = 2;
									}
									if(turno == 2 && malo.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 3;
									}
									if(malo.getVida()<=0) {
										turno = 3;
									}
									if(turno == 3 && malo2.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo2.realizarAtaque(malo2, pj);
										turno = 1;
									}
									if(malo2.getVida()<=0) {
										turno = 1;
									}
									if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tus oponentes!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							if(rndm3>50) {
								while(flag2) {//PELEA CON 1 ENEMIGO
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo3.getNombre()+": " +malo3.getVida());
										pj.realizarAtaque(malo3, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo3.realizarAtaque(malo3, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										pj.asignarExperiencia(70);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(70, oro);
										turno=1;
										flag2 = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								while(flag2) {//PELEA CON 2 ENEMIGOS
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo3.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");
										}
										else {
											System.out.println("Vida del oponente 2: " +malo4.getVida());
										}
										pj.realizarAtaque2(malo3,malo4, pj);
										turno = 2;
									}
									if(turno == 2 && malo3.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo3.realizarAtaque(malo3, pj);
										turno = 3;
									}
									if(malo3.getVida()<=0) {
										turno = 3;
									}
									if(turno == 3 && malo4.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo4.realizarAtaque(malo4, pj);
										turno = 1;
									}
									if(malo4.getVida()<=0) {
										turno = 1;
									}
									if(malo3.getVida() <= 0 && malo4.getVida() <= 0 ) {
										pj.asignarExperiencia(70);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(70, oro);
										turno=1;
										flag2 = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
						}


					}
					//---------------------------------------------------------------
					if (opmision==5) {//ACA SE HACE LA MISION DE RANGO S
						if(pj.getRango()!="Whilenin" && pj.getRango()!="Kage") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							rndm3 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *30) + 130;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm-1);
							malo.asignarNivel(10);
							malo.asignarRango("Whilenin");
							malo.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm-1);
							malo2.asignarNivel(10);
							malo2.asignarRango("Whilenin");
							malo2.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm-1);
							malo3.asignarNivel(10);
							malo3.asignarRango("Whilenin");
							malo3.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c4=rndm;
								if(c1!=c4 && c2 != c4 && c3!=c4) flag = false;
							}
							enemigo malo4 = enemigos.get(rndm-1);
							malo4.asignarNivel(10);
							malo4.asignarRango("Whilenin");
							malo4.asignarVidaMax();


							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 5) + 1;
								c5=rndm;
								if(c1!=c5 && c2 != c5 && c3!=c5 && c4!=c5) flag = false;
							}
							enemigo malo5 = enemigos.get(rndm-1);
							malo5.asignarNivel(10);
							malo5.asignarRango("Whilenin");
							malo5.asignarVidaMax();
							flag = true;
							flag2 = true;
							flag3 = true;
							while(flag) {//PELEA CON 2 ENEMIGOS
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									if(malo.getVida()<=0) {
										System.out.println("Vida del oponente 1: 0");
									}
									else {
										System.out.println("Vida del oponente 1: " +malo.getVida());
									}
									if(malo2.getVida()<=0) {
										System.out.println("Vida del oponente 2: 0");
									}
									else {
										System.out.println("Vida del oponente 2: " +malo2.getVida());
									}
									pj.realizarAtaque2(malo,malo2, pj);
									turno = 2;
								}
								if(turno == 2 && malo.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo.realizarAtaque(malo, pj);
									turno = 3;
								}
								if(malo.getVida()<=0) {
									turno = 3;
								}
								if(turno == 3 && malo2.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo2.realizarAtaque(malo2, pj);
									turno = 1;
								}
								if(malo2.getVida()<=0) {
									turno = 1;
								}
								if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
									turno=1;
									flag = false;
									System.out.println("\nVenciste a tus oponentes!!\n");
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3=false;
									pj.asignarVida(pj.getVidaMax());
								}
							}

							while(flag2) {//PELEA CON 2 ENEMIGOS
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									if(malo3.getVida()<=0) {
										System.out.println("Vida del oponente 1: 0");
									}
									else {
										System.out.println("Vida del oponente 1: " +malo3.getVida());
									}
									if(malo4.getVida()<=0) {
										System.out.println("Vida del oponente 2: 0");
									}
									else {
										System.out.println("Vida del oponente 2: " +malo4.getVida());
									}
									pj.realizarAtaque2(malo3,malo4, pj);
									turno = 2;
								}
								if(turno == 2 && malo3.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo3.realizarAtaque(malo3, pj);
									turno = 3;
								}
								if(malo3.getVida()<=0) {
									turno = 3;
								}
								if(turno == 3 && malo4.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo4.realizarAtaque(malo4, pj);
									turno = 1;
								}
								if(malo4.getVida()<=0) {
									turno = 1;
								}
								if(malo3.getVida() <= 0 && malo4.getVida() <= 0 ) {
									turno=1;
									flag2 = false;
									System.out.println("\nVenciste a tus oponentes!!\n");
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3=false;
									pj.asignarVida(pj.getVidaMax());
								}
							}

							while(flag3) {//PELEA CON 1 ENEMIGO
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo5.getNombre()+": " +malo5.getVida());
									pj.realizarAtaque(malo5, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo5.realizarAtaque(malo5, pj);
									turno = 1;
								}
								if(malo5.getVida() <= 0) {
									pj.asignarExperiencia(100);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(100, oro);
									turno=1;
									flag3 = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3 = false;
									pj.asignarVida(pj.getVidaMax());
								}
							}
						}
					}

				}
				else if(pj.getNivel() >= 11 && pj.getRango() != "Kage"){ // EL MISMO MENU QUE EL ANTERIOR SOLO QUE INCLUYE LA MISION PARA CONVERTIRSE EN KAGE
					pj.getMenuKage();
					opmision=opcionMision.nextInt();
					if (opmision==1) {
						if(pj.getRango()!="Ifnin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							oro =  (int) (Math.random() *5) + 5;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(1);
							malo.asignarRango("Ifnin");
							malo.asignarVidaMax();
							flag = true;
							rndm2 = (int) (Math.random() * 100) + 1;
							if(rndm2 > 50) {
								while(flag) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vidaaaa: " +pj.getVida());

										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										//System.out.printf("\nHaz recuperado 10 de energia y ganado %s de oro\n", oro);
										pj.asignarExperiencia(10);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(10, oro);
										turno=1;
										flag = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								//oro =  (int) (Math.random() *5) + 5;
								//System.out.printf("\nObtienes %s de oro de la mision\n", oro);

								pj.asignarExperiencia(10);
								pj.asignarOro(pj.getOro()+oro);
								System.out.println("\nNo te encontraste con ningun enemigo");
								pj.getMenuMisionTerminada(10, oro);
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==2) {
						if(pj.getRango()!="Ifnin" && pj.getRango()!="Fornin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							oro =  (int) (Math.random() *5) + 5;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(4);
							malo.asignarRango("Fornin");
							malo.getNinja().asignarNivelJutsus(malo);
							malo.asignarVidaMax();

							flag = true;
							while(flag) {

								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
									pj.realizarAtaque(malo, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo.realizarAtaque(malo, pj);
									turno = 1;
								}
								if(malo.getVida() <= 0) {
									pj.asignarExperiencia(10);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(10, oro);
									turno=1;
									flag = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									pj.asignarVida(pj.getVidaMax());
								}
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==3) {
						if(pj.getRango()!="Fornin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *30) + 50;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(6);
							malo.asignarRango("Fornin");
							if(malo.getVidaMax() == 0) {
								malo.asignarVidaMax();
							}
							malo.asignarVida(malo.getVidaMax());

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm);
							malo2.asignarNivel(6);
							malo2.asignarRango("Fornin");
							if(malo2.getVidaMax() == 0) {
								malo2.asignarVidaMax();
							}
							malo2.asignarVida(malo.getVidaMax());

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm);
							malo3.asignarNivel(6);
							malo3.asignarRango("Fornin");
							if(malo3.getVidaMax() == 0) {
								malo.asignarVidaMax();
							}
							malo3.asignarVida(malo.getVidaMax());

							flag = true;
							flag2 = true;
							if(rndm2>50) {
								while(flag) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tu oponente!!\n");
										malo.asignarVida(malo.getVidaMax());
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
										malo.asignarVida(malo.getVidaMax());
									}
								}
							}
							else {
								while(flag) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");

										}
										else {
											System.out.println("Vida del oponente 2: " +malo2.getVida());
										}
										pj.realizarAtaque2(malo,malo2, pj);
										turno = 2;
									}
									if(turno == 2 && malo.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 3;
									}
									if(malo.getVida()<=0) {
										System.out.println("\nxdddddd");
										turno = 3;
									}
									if(turno == 3 && malo2.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo2.realizarAtaque(malo2, pj);
										turno = 1;
									}
									if(malo2.getVida()<=0) {
										turno = 1;
									}
									if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tus oponentes!!\n");
										malo.asignarVida(malo.getVidaMax());
										malo2.asignarVida(malo.getVidaMax());
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
										malo.asignarVida(malo.getVidaMax());
										malo2.asignarVida(malo.getVidaMax());
									}
								}
							}
							while(flag2) {
								if(turno == 1) {
									System.out.println("\nHa aparecido un nuevo rival!!");
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo3.getNombre()+": " +malo3.getVida());
									pj.realizarAtaque(malo3, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo3.realizarAtaque(malo3, pj);
									turno = 1;
								}
								if(malo3.getVida() <= 0) {
									pj.asignarExperiencia(45);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(45, oro);
									malo3.asignarVida(malo.getVidaMax());
									turno=1;
									flag2 = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag2 = false;
									pj.asignarVida(pj.getVidaMax());
									malo3.asignarVida(malo.getVidaMax());
								}
							}
						}
					}
					//---------------------------------------------------------------
					if (opmision==4) {
						if(pj.getRango()!="Whilenin") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							rndm3 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *20) + 100;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm);
							malo.asignarNivel(8);
							malo.asignarRango("Whilenin");
							malo.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm);
							malo2.asignarNivel(8);
							malo2.asignarRango("Whilenin");
							malo2.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm);
							malo3.asignarNivel(8);
							malo3.asignarRango("Whilenin");
							malo3.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 5) + 1;
								c4=rndm;
								if(c1!=c4 && c2 != c4 && c3!=c4) flag = false;
							}
							enemigo malo4 = enemigos.get(rndm);
							malo4.asignarNivel(8);
							malo4.asignarRango("Whilenin");
							malo4.asignarVidaMax();

							flag = true;
							flag2 = true;
							if(rndm2>50) {
								while(flag) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
										pj.realizarAtaque(malo, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tu oponente!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								while(flag) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");
										}
										else {
											System.out.println("Vida del oponente 2: " +malo2.getVida());
										}
										pj.realizarAtaque2(malo,malo2, pj);
										turno = 2;
									}
									if(turno == 2 && malo.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo.realizarAtaque(malo, pj);
										turno = 3;
									}
									if(malo.getVida()<=0) {
										turno = 3;
									}
									if(turno == 3 && malo2.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo2.realizarAtaque(malo2, pj);
										turno = 1;
									}
									if(malo2.getVida()<=0) {
										turno = 1;
									}
									if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
										turno=1;
										flag = false;
										System.out.println("\nVenciste a tus oponentes!!\n");
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							if(rndm3>50) {
								while(flag2) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										System.out.println("Vida de "+malo3.getNombre()+": " +malo3.getVida());
										pj.realizarAtaque(malo3, pj);
										turno = 2;
									}
									else if(turno == 2) {
										System.out.println("Es el turno del enemigo.");
										malo3.realizarAtaque(malo3, pj);
										turno = 1;
									}
									if(malo.getVida() <= 0) {
										pj.asignarExperiencia(70);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(70, oro);
										turno=1;
										flag2 = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
							else {
								while(flag2) {
									if(turno == 1) {
										System.out.println("\nEs tu turno, que deseas hacer?");
										System.out.println("Tu vida: " +pj.getVida());
										if(malo.getVida()<=0) {
											System.out.println("Vida del oponente 1: 0");
										}
										else {
											System.out.println("Vida del oponente 1: " +malo3.getVida());
										}
										if(malo2.getVida()<=0) {
											System.out.println("Vida del oponente 2: 0");
										}
										else {
											System.out.println("Vida del oponente 2: " +malo4.getVida());
										}
										pj.realizarAtaque2(malo3,malo4, pj);
										turno = 2;
									}
									if(turno == 2 && malo3.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo3.realizarAtaque(malo3, pj);
										turno = 3;
									}
									if(malo3.getVida()<=0) {
										turno = 3;
									}
									if(turno == 3 && malo4.getVida() > 0) {
										System.out.println("Es el turno del enemigo.");
										malo4.realizarAtaque(malo4, pj);
										turno = 1;
									}
									if(malo4.getVida()<=0) {
										turno = 1;
									}
									if(malo3.getVida() <= 0 && malo4.getVida() <= 0 ) {
										pj.asignarExperiencia(70);
										pj.asignarOro(pj.getOro()+oro);
										pj.asignarVida(pj.getVidaMax());
										pj.getMenuMisionTerminada(70, oro);
										turno=1;
										flag2 = false;
									}
									if(pj.getVida() <= 0) {
										System.out.println("\nDurante la batalla te han dejado inconsciente\n");
										turno=1;
										flag = false;
										flag2 = false;
										pj.asignarVida(pj.getVidaMax());
									}
								}
							}
						}


					}
					//---------------------------------------------------------------
					if (opmision==5) {
						if(pj.getRango()!="Whilenin" && pj.getRango()!="Kage") {
							System.out.println("\nNO TIENES EL RANGO ADECUADO");
							pj.getMenuMision();
							opmision=opcionMision.nextInt();
						}
						else {
							pj.asignarEnergia(pj.getEner()-5);
							rndm = (int) (Math.random() * 4) + 1;
							rndm2 = (int) (Math.random() * 100) + 1;
							rndm3 = (int) (Math.random() * 100) + 1;
							oro =  (int) (Math.random() *30) + 130;
							c1=0;
							c2=0;
							c3=0;
							c4=0;

							c1=rndm;
							enemigo malo = enemigos.get(rndm-1);
							malo.asignarNivel(10);
							malo.asignarRango("Whilenin");
							malo.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c2=rndm;
								if(c1!=c2) flag = false;
							}
							enemigo malo2 = enemigos.get(rndm-1);
							malo2.asignarNivel(10);
							malo2.asignarRango("Whilenin");
							malo2.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c3=rndm;
								if(c1!=c3 && c2 != c3) flag = false;
							}
							enemigo malo3 = enemigos.get(rndm-1);
							malo3.asignarNivel(10);
							malo3.asignarRango("Whilenin");
							malo3.asignarVidaMax();

							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 4) + 1;
								c4=rndm;
								if(c1!=c4 && c2 != c4 && c3!=c4) flag = false;
							}
							enemigo malo4 = enemigos.get(rndm-1);
							malo4.asignarNivel(10);
							malo4.asignarRango("Whilenin");
							malo4.asignarVidaMax();


							flag = true;
							while(flag) {
								rndm = (int) (Math.random() * 5) + 1;
								c5=rndm;
								if(c1!=c5 && c2 != c5 && c3!=c5 && c4!=c5) flag = false;
							}
							enemigo malo5 = enemigos.get(rndm-1);
							malo5.asignarNivel(10);
							malo5.asignarRango("Whilenin");
							malo5.asignarVidaMax();
							flag = true;
							flag2 = true;
							flag3 = true;
							while(flag) {
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									if(malo.getVida()<=0) {
										System.out.println("Vida del oponente 1: 0");
									}
									else {
										System.out.println("Vida del oponente 1: " +malo.getVida());
									}
									if(malo2.getVida()<=0) {
										System.out.println("Vida del oponente 2: 0");
									}
									else {
										System.out.println("Vida del oponente 2: " +malo2.getVida());
									}
									pj.realizarAtaque2(malo,malo2, pj);
									turno = 2;
								}
								if(turno == 2 && malo.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo.realizarAtaque(malo, pj);
									turno = 3;
								}
								if(malo.getVida()<=0) {
									turno = 3;
								}
								if(turno == 3 && malo2.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo2.realizarAtaque(malo2, pj);
									turno = 1;
								}
								if(malo2.getVida()<=0) {
									turno = 1;
								}
								if(malo.getVida() <= 0 && malo2.getVida() <= 0 ) {
									turno=1;
									flag = false;
									System.out.println("\nVenciste a tus oponentes!!\n");
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3=false;
									pj.asignarVida(pj.getVidaMax());
								}
							}

							while(flag2) {
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									if(malo3.getVida()<=0) {
										System.out.println("Vida del oponente 1: 0");
									}
									else {
										System.out.println("Vida del oponente 1: " +malo3.getVida());
									}
									if(malo4.getVida()<=0) {
										System.out.println("Vida del oponente 2: 0");
									}
									else {
										System.out.println("Vida del oponente 2: " +malo4.getVida());
									}
									pj.realizarAtaque2(malo3,malo4, pj);
									turno = 2;
								}
								if(turno == 2 && malo3.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo3.realizarAtaque(malo3, pj);
									turno = 3;
								}
								if(malo3.getVida()<=0) {
									turno = 3;
								}
								if(turno == 3 && malo4.getVida() > 0) {
									System.out.println("Es el turno del enemigo.");
									malo4.realizarAtaque(malo4, pj);
									turno = 1;
								}
								if(malo4.getVida()<=0) {
									turno = 1;
								}
								if(malo3.getVida() <= 0 && malo4.getVida() <= 0 ) {
									turno=1;
									flag2 = false;
									System.out.println("\nVenciste a tus oponentes!!\n");
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3=false;
									pj.asignarVida(pj.getVidaMax());
								}
							}

							while(flag3) {
								if(turno == 1) {
									System.out.println("\nEs tu turno, que deseas hacer?");
									System.out.println("Tu vida: " +pj.getVida());
									System.out.println("Vida de "+malo5.getNombre()+": " +malo5.getVida());
									pj.realizarAtaque(malo5, pj);
									turno = 2;
								}
								else if(turno == 2) {
									System.out.println("Es el turno del enemigo.");
									malo5.realizarAtaque(malo5, pj);
									turno = 1;
								}
								if(malo5.getVida() <= 0) {
									pj.asignarExperiencia(100);
									pj.asignarOro(pj.getOro()+oro);
									pj.asignarVida(pj.getVidaMax());
									pj.getMenuMisionTerminada(100, oro);
									turno=1;
									flag3 = false;
								}
								if(pj.getVida() <= 0) {
									System.out.println("\nDurante la batalla te han dejado inconsciente\n");
									turno=1;
									flag = false;
									flag2 = false;
									flag3 = false;
									pj.asignarVida(pj.getVidaMax());
								}
							}
						}
					}
					if(opmision == 6) {//PELEA CONTRA EL KAGE
						pj.asignarEnergia(pj.getEner()-5);
						enemigo malo;
						if(pj.getAldea() == "Pythonia") {
							 malo = pythoniakage;
						}
						else if(pj.getAldea() == "Cthonia") {
							 malo = cthoniakage;
						}
						else if(pj.getAldea() == "Javania") {
							 malo = javaniakage;
						}
						else if(pj.getAldea() == "Schemia") {
							 malo = schemiakage;
						}
						else {
							 malo = proloniakage;
						}
						flag = true;
						while(flag) {

							if(turno == 1) {
								System.out.println("\nEs tu turno, que deseas hacer?");
								System.out.println("Tu vida: " +pj.getVida());
								System.out.println("Vida de "+malo.getNombre()+": " +malo.getVida());
								pj.realizarAtaque(malo, pj);
								turno = 2;
							}
							else if(turno == 2) {
								System.out.println("Es el turno del enemigo.");
								malo.realizarAtaque(malo, pj);
								turno = 1;
							}
							if(malo.getVida() <= 0) {
								pj.asignarExperiencia(10);
								pj.asignarVida(pj.getVidaMax());
								pj.asignarRango("Kage");
								System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								System.out.println("\nFelicitaciones, eres el nuevo Kage de "+pj.getAldea());
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
								turno=1;
								flag = false;
							}
							if(pj.getVida() <= 0) {
								System.out.println("\nDurante la batalla te han dejado inconsciente\n");
								turno=1;
								flag = false;
								pj.asignarVida(pj.getVidaMax());
							}
						}
					}

				}
			}

		}

		if(op==3) {
			if(pj.getRango() != "Kage") {
				System.out.println("\nEl juego ha terminado, tu rango maximo fue "+pj.getRango());
			}
			else {
				System.out.println("\nFelicitaciones, lograste ser el Kage de "+pj.getAldea());
			}
			name.close();
			ald.close();
			opcionMision.close();
			opcion.close();
		}
	}
	private static void Fortuna(jugador pj) {//METODO FORTUNA (CAJA)
		lootbox = new Scanner(System.in);
		if(pj.getOro() < 300) {
			System.out.println("\nNO TIENES LA CANTIDAD DE ORO SUFICIENTE (300)");

		}
		else if(pj.getOro() >=300) {
			pj.asignarOro(pj.getOro()-300);
			int opcion = 0;
			int random = (int) (Math.random() * 100) + 1;
			int random2 = 0;
			if(random <= 50){
				random2 = (int) (Math.random() * 3) + 1;
				if(random2 == 1) {
					System.out.println("\nAbres la caja y te encuentras con un Shuriken (+2 al ataque fisico)\n");
					System.out.println("\nQuieres equipar este Shuriken? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Shuriken");
					}

				}
				else if(random2 == 2) {
					System.out.println("\nAbres la caja y te encuentras con un Kunai (+2 al ataque fisico)\n");
					System.out.println("\nQuieres equipar este Kunai? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Kunai");
					}
				}
				else if(random2 == 3) {
					System.out.println("\nAbres la caja y te encuentras con un Sello Explosivo (+2 al ataque fisico)\n");
					System.out.println("\nQuieres equipar este Sello Explosivo? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Sello Explosivo");
					}
				}
			}
			else if(random > 50 && random <= 80){
				random2 = (int) (Math.random() * 2) + 1;
				if(random2 == 1) {
					System.out.println("\nAbres la caja y te encuentras con un Fuma Shuriken (+3 al ataque fisico)\n");
					System.out.println("\nQuieres equipar este Fuma Shuriken? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Fuma Shuriken");
					}
				}
				else if(random2 == 2) {
					System.out.println("\nAbres la caja y te encuentras con un Senbon (+3 al ataque fisico)\n");
					System.out.println("\nQuieres equipar este Senbon? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Senbon");
					}
				}
			}
			else if(random > 80 && random <= 95){
				random2 = (int) (Math.random() * 2) + 1;
				if(random2 == 1) {
					System.out.println("\nAbres la caja y te encuentras con unas Guadanas (+4 al ataque fisico)\n");
					System.out.println("\nQuieres equipar estas Guadanas? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Guadanas");
					}
				}
				else if(random2 == 2) {
					System.out.println("\nAbres la caja y te encuentras con una Espada (+4 al ataque fisico)\n");
					System.out.println("\nQuieres equipar esta Espada? (1)SI | (2)NO\n");
					if(lootbox.hasNextInt()) opcion = lootbox.nextInt();
					if(opcion == 1) {
						pj.asignarObjeto("Espada");
					}
				}
			}
			else if(random > 95 && random <= 100){
				System.out.println("\nAbres la caja y te encuentras con un Fierro con Clavos (+5 al ataque fisico)\n");
				System.out.println("\nQuieres equipar este Fierro con Clavos? (1)SI | (2)NO\n");
				if(lootbox.hasNextInt()) opcion = lootbox.nextInt();

				if(opcion == 1) {
					pj.asignarObjeto("Fierro con Clavos");
				}
			}
		}
	}
}
