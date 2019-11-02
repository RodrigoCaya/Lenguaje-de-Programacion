package lp;

import java.util.Scanner;

public class Schemia extends ninja{
	Scanner opcion;
	private int random = 0;
	/*SUPER*/
	public Schemia() {
		super();
		crearNinja();
	}
	/*CONSTRUCTOR*/
	protected void crearNinja() {
		jutsu_1="Parentesiyomi Infinito";
		jutsu_2="Jutsu Recursion de Fuego";
		jutsu_3="Letsano";
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
			System.out.println("Usas tu Jutsu Parentesiyomi Infinito.");
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(op == 2) {
			System.out.println("Usas tu Jutsu Recursion de Fuego.");
			return (2*Nivel_Jutsu);
		}
		else if(op == 3) {
			System.out.println("Usas tu Letsano.");
			return (3*pj.getNivel());
		}
		return 0;
	}
	/*IMPRIME EL MENU DE JUTSUS*/
	public void getMenu() {
		System.out.println(
				"\n\n-------------JUTSUS-------------\n"
				+"|(1)Parentesiyomi Infinito     |\n"
				+"|------------------------------|\n"
				+"|(2)Jutsu Recursion de Fuego   |\n"
				+"|------------------------------|\n"
				+"|(3)Letsano                    |\n"
				+"--------------------------------\n"
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
			System.out.printf("%s usa su Jutsu Parentesiyomi Infinito.", pj.getNombre());
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(random == 2) {
			System.out.printf("%s usa su Jutsu Recursion de Fuego.", pj.getNombre());
			return (2*Nivel_Jutsu);
		}
		else if(random == 3) {
			System.out.printf("%s usa su Letsano.", pj.getNombre());
			return (3*pj.getNivel());
		}
		return 0;
	}
}
