package lp;
import java.util.Scanner;
public final class Cthonia extends ninja{
	Scanner opcion;
	private int random = 0;
	/*SUPER*/
	public Cthonia() {
		super();
		crearNinja();
	}
	/*CONSTRUCTOR*/
	protected void crearNinja() {
		jutsu_1="Freedori";
		jutsu_2="Punteringan";
		jutsu_3="Malloc Tensei";

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
		if(op == 1) {
			System.out.println("Usas tu Jutsu Freedori.");
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(op == 2) {
			System.out.println("Usas tu Jutsu Punteringan.");
			return (2*Nivel_Jutsu);
		}
		else if(op == 3) {
			System.out.println("Usas tu Malloc Tensei.");
			return (3*pj.getNivel());
		}
		return 0;
	}
	/*IMPRIME EL MENU DE JUTSUS*/
	public void getMenu() {
		System.out.println(
				"\n\n-----------JUTSUS-----------\n"
				+"|(1)Freedori               |\n"
				+"|--------------------------|\n"
				+"|(2)Punteringan            |\n"
				+"|--------------------------|\n"
				+"|(3)Malloc Tensei          |\n"
				+"----------------------------\n"
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
			System.out.printf("%s usa su Jutsu Freedori.", pj.getNombre());
			return (pj.getNivel()*Nivel_Jutsu);
		}
		else if(random == 2) {
			System.out.printf("%s usa su Jutsu Punteringan.", pj.getNombre());
			return (2*Nivel_Jutsu);
		}
		else if(random == 3) {
			System.out.printf("%s usa su Malloc Tensei.", pj.getNombre());
			return (3*pj.getNivel());
		}
		return 0;
	}

}
