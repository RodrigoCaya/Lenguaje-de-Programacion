package lp;

import java.util.Scanner;

public class Prolonia extends ninja{
	Scanner opcion;
	private int random = 0;
	/*SUPER*/
	public Prolonia() {
		super();
		crearNinja();
	}
	/*CONSTRUCTOR*/
	protected void crearNinja() {
		jutsu_1="Confusion Falso Verdadero";
		jutsu_2="Querykugan";
		jutsu_3="Gran Explosion de Backtracking";
	}

	/*ASIGNA EL NIVEL DE JUTSU AL JUGADOR*/
	public void asignarNivelJutsus(jugador pj) {
		if(pj.getRango()=="Ifnin") {
			Nivel_Jutsu=1;
		}
		if(pj.getRango()=="Fornin") {
			Nivel_Jutsu=2;
		}
		if(pj.getRango()=="Whilenin") {
			Nivel_Jutsu=3;
		}
		if(pj.getRango()=="Kage") {
			Nivel_Jutsu=4;
		}
	}

	/*PERMITE USAR UN JUTSU*/
	public int usarJutsu(jugador pj) {
		opcion = new Scanner(System.in);
		getMenu();
		asignarNivelJutsus(pj);
		int op = opcion.nextInt();
		//menu
		if(op == 1) {
			System.out.println("Usas tu Jutsu Confusion Falso Verdadero.");
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(op == 2) {
			System.out.println("Usas tu Jutsu Querykugan.");
			return (2*Nivel_Jutsu);
		}
		else if(op == 3) {
			System.out.println("Usas tu Gran Explosion de Backtracking.");
			return (3*pj.getNivel());
		}
		return 0;
	}
	/*IMPRIME EL MENU DE JUTSUS*/
	public void getMenu() {
		System.out.println(
				"\n\n---------------JUTSUS----------------\n"
				+"|(1)Confusion Falso Verdadero       |\n"
				+"|-----------------------------------|\n"
				+"|(2)Querykugan                      |\n"
				+"|-----------------------------------|\n"
				+"|(3)Gran Explosion de Backtracking  |\n"
				+"-------------------------------------\n"
				+"\nElige numero de tu opcion: ");
	}
	/*ASIGNA EL NIVEL DE JUTSU AL ENEMIGO*/
	public void asignarNivelJutsus(enemigo pj) {
		if(pj.getRango()=="Ifnin") {
			Nivel_Jutsu=1;
		}
		if(pj.getRango()=="Fornin") {
			Nivel_Jutsu=2;
		}
		if(pj.getRango()=="Whilenin") {
			Nivel_Jutsu=3;
		}

	}
	/*PERMITE A UN ENEMIGO HACER JUTSUS*/
	public int usarJutsu(enemigo pj) {
		this.random = (int) (Math.random() * 3) + 1;
		if(random == 1) {
			System.out.printf("%s usa su Jutsu Confusion Falso Verdadero.", pj.getNombre());
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(random == 2) {
			System.out.printf("%s usa su Jutsu Querykugan.", pj.getNombre());
			return (2*Nivel_Jutsu);
		}
		else if(random == 3) {
			System.out.printf("%s usa su Gran Explosion de Backtracking.", pj.getNombre());
			return (3*pj.getNivel());
		}
		return 0;
	}
}
