package lp;
import java.util.Scanner;

public class jugador implements personaje{

	private int vida;
	private int vidamax;
	private String aldea;
	private String nombre;
	private int ataque;
	private String objeto;
	private String rango;
	private int exp=0;
	private String barraexp;
	private int danoObj=0;
	private int nivel;
	private int ener;
	private String barraener;
	private int oro;
	private Scanner opcion;
	private Scanner opcion2;
	private int op;
	private int op2;
	private boolean flag=true;

	private ninja ninya;
	/* CONSTRUCTOR DE LA CLASE JUGADOR DONDE SE INICIALIZA LOS VALORES POR DEFECTO*/
	public jugador(int n1,String ald1,String name1,int atk1,String obj1,String r1, int level, int life) {
		vida = life;
		nivel = level;
		asignarVida(n1);
		asignarNinja(ald1);
		asignarNombre(name1);
		asignarAtaqueFisico();
		asignarObjeto(obj1);
		asignarRango(r1);
		asignarNivel(nivel);
		asignarExperiencia(0);
		asignarEnergia(300);
		asignarOro(0);
		asignarVida(vida);
		asignarVidaMax();
	}
	/* ASIGNA LA VIDA DEL JUGADOR*/
	public void asignarVida(int life) {
		vida=life;
	}
	/* ASIGNA LA VIDA MÁXIMA DEL JUGADOR*/
	public void asignarVidaMax() {
		vidamax=vida + nivel;
		vida=vidamax;
	}
	/* CREA UN NINJA DEPENDIENDO DE LA ALDEA*/
	public void asignarNinja(String ald) {
		aldea=ald;
		if(aldea=="Pythonia") {
			ninya = new Pythonia();
		}
		if(aldea=="Cthonia") {
			ninya = new Cthonia();
		}
		if(aldea=="Javania") {
			ninya = new Javania();
		}
		if(aldea=="Schemia") {
			ninya = new Schemia();
		}
		if(aldea=="Prolonia") {
			ninya = new Prolonia();
		}
	}
	/* ASIGNA EL NOMBRE DEL JUGADOR*/
	public void asignarNombre(String name) {
		nombre=name;
	}
	/* ASIGNA EL ATAQUE FISICO DEL JUGADOR*/
	public void asignarAtaqueFisico(){
		ataque=3+nivel+danoObj;
	}
	/* ASIGNA EL DAÑO DEL OBJETO DEL JUGADOR*/
	public void asignarObjeto(String obj) {
		objeto=obj;
		if(objeto=="Shuriken" || objeto=="Kunai" || objeto=="Sello Explosivo") {
			danoObj=2;
		}
		if(objeto=="Fuma Shuriken" || objeto=="Senbon") {
			danoObj=3;
		}
		if(objeto=="Guadanas" || objeto=="Espada") {
			danoObj=4;
		}
		if(objeto=="Fierro con clavos") {
			danoObj=5;
		}
		if(objeto=="") {
			danoObj=0;
		}
	}
	/* ASIGNA EL RANGO DEL JUGADOR*/
	public void asignarRango(String r) {
		rango=r;
	}
	/* ASIGNA EL RANGO DE MANERA MANUAL DEL JUGADOR*/
	public void asignarRango() {
		if(nivel == 3) {
			asignarRango("Fornin");
		}
		else if(nivel == 7) {
			asignarRango("Whilenin");
		}
	}
	/* REALIZA UN ATAQUE FISICO O JUTSU DEL JUGADOR SOBRE EL ENEMIGO*/
	public void realizarAtaque(enemigo malo ,jugador pj){
		getMenuPelea();
		opcion = new Scanner(System.in);
		op = opcion.nextInt();
		if(op == 1) {
			System.out.printf("Golpeas con tu %s\n", objeto);
			malo.asignarVida(malo.getVida()-pj.getAtaqueFisico());
		}
		else if(op == 2) {
			malo.asignarVida(malo.getVida()-getNinja().usarJutsu(pj));
		}
	}
	/* REALIZA UN ATAQUE FISICO O JUTSU DEL JUGADOR SOBRE EL ENEMIGO, PARA DOS ENEMIGOS*/
	public void realizarAtaque2(enemigo malo, enemigo malo2 ,jugador pj){
		flag=true;
		while(flag) {
			System.out.println("\n |(1)"+malo.getNombre()+ "|  |(2)"+malo2.getNombre()+ "|\n");
			opcion2 = new Scanner(System.in);
			op2 = opcion2.nextInt();
			if (op2 ==1 && malo.getVida() <=0) {
				System.out.printf("No puedes pegarle a un muerto\n");
			}
			else if(op2 == 1 && malo.getVida()>0) flag = false;
			else if(op2 == 2 && malo2.getVida()<=0) {
				System.out.printf("No puedes pegarle a un muerto\n");
			}
			else if(op2 == 2 && malo2.getVida()>0) flag = false;
		}

		getMenuPelea();
		opcion = new Scanner(System.in);
		op = opcion.nextInt();
		if(op2 == 1) {
			if(op == 1) {
			System.out.printf("Golpeas con tu %s\n", objeto);
			malo.asignarVida(malo.getVida()-pj.getAtaqueFisico());
			}
			if(op == 2) {
				malo.asignarVida(malo.getVida()-getNinja().usarJutsu(pj));
			}
		}
		if(op2 == 2) {
			if(op == 1) {
			System.out.printf("Golpeas con tu %s\n", objeto);
			malo2.asignarVida(malo2.getVida()-pj.getAtaqueFisico());
			}
			if(op == 2) {
				malo2.asignarVida(malo2.getVida()-getNinja().usarJutsu(pj));
			}
		}
	}
	/* ASIGNA EL NIVEL DEL JUGADOR*/
	public void asignarNivel(int lvl){
		nivel=lvl;
	}
	/* ASIGNA LA EXPERIENCIA DEL JUGADOR Y CREA LA BARRA DE EXP*/
	public void asignarExperiencia(int exp1){
		exp+=exp1;
		if(exp >= 100) {
			exp-=100;
			nivel+=1;
		}
		barraexp="|";
		int i=0;
		while(i<20) {
			if(exp>=(i+1)*5)barraexp+="#";
			else barraexp+=".";
			i+=1;
		}
		barraexp+="|";
	}

	/* ASIGNA LA ENERGIA DEL JUGADOR*/
	public void asignarEnergia(int energia){
		ener=energia;
		barraener="|";
		int i=0;
		while(i<60) {
			if(energia>=(i+1)*5)barraener+="#";
			else barraener+=".";
			i+=1;
		}
		barraener+="|";
	}
	/* ASIGNA EL ORO DEL JUGADOR*/

	public void asignarOro(int o) {
		oro=o;
	}

	//########################################################################//

	/* RETORNA EL NOMBRE DEL JUGADOR*/
	public String getNombre() {
		return nombre;
	}

	/* RETORNA LA VIDA DEL JUGADOR*/
	public int getVida() {
		return vida;
	}

	/* RETORNA LA VIDA MÁXIMA DEL JUGADOR*/
	public int getVidaMax() {
		return vidamax;
	}

	/* RETORNA EL NINJA QUE SE CREO EN ASIGNARNINJA*/
	public ninja getNinja() {
		return ninya;
	}

	/* RETORNA EL ATAQUE FISICO DEL JUGADOR*/
	public int getAtaqueFisico() {
		asignarAtaqueFisico();
		return ataque;
	}

	/* RETORNA EL OBJETO DEL JUGADOR*/
	public String getObjeto() {
		return objeto;
	}

	/* RETORNA EL RANGO DEL JUGADOR*/
	public String getRango() {
		return rango;
	}

	/* RETORNA EL NIVEL DEL JUGADOR*/
	public int getNivel() {
		return nivel;
	}

	/* RETORNA LA EXPERIENCIA DEL JUGADOR*/
	public int getExp() {
		return exp;
	}

	/* RETORNA LA BARRA DE EXPERIENCIA DEL JUGADOR*/
	public String getBarraexp() {
		return barraexp;
	}

	/* RETORNA LA ENERGIA DEL JUGADOR*/
	public int getEner() {
		return ener;
	}

	/* RETORNA LA BARRA DE ENERGIA DEL JUGADOR*/
	public String getBarraener() {
		return barraener;
	}

	/* RETORNA LA ALDEA DEL JUGADOR*/
	public String getAldea() {
		return aldea;
	}
	/* PRINTEA EL MENU DEL JUEGO*/
	public void getMenu() {
		System.out.println("\nNombre: " +nombre+ "  Aldea: " +aldea+ "  Rango: "+rango
		+"\nVida: "  +vida+"/"+vidamax+"  Oro: "+oro+"  Nivel: "+nivel
		+"\nExperiencia: "+barraexp
		+"\nEnergia: "+barraener
		+"\n\n------------MENU------------\n"
		+"|(1)Realizar Mision        |\n"
		+"|--------------------------|\n"
		+"|(2)Obtener Caja           |\n"
		+"|--------------------------|\n"
		+"|(3)Salir                  |\n"
		+"----------------------------\n"
		+"\nElige numero de tu opcion: ");
	}
	/* PRINTEA EL MENU DE LOS KAGES*/
	public void getMenuKage() {
		System.out.println("\nNombre: " +nombre+ "  Aldea: " +aldea+ "  Rango: "+rango
		+"\nVida: "  +vida+"/"+vidamax+"  Oro: "+oro+"  Nivel: "+nivel
		+"\nExperiencia: "+barraexp
		+"\nEnergia: "+barraener
		+"\n\n----------------MISIONES----------------\n"
		+"|(1)Mision Tipo D (Ifnin)               |\n"
		+"|---------------------------------------|\n"
		+"|(2)Mision Tipo C (Ifnin & Fornins)     |\n"
		+"|---------------------------------------|\n"
		+"|(3)Mision Tipo B (Fornins)             |\n"
		+"|---------------------------------------|\n"
		+"|(4)Mision Tipo A (Whilenins)           |\n"
		+"|---------------------------------------|\n"
		+"|(5)Mision Tipo S (Whilenins & Kages)   |\n"
		+"|---------------------------------------|\n"
		+"|---------------------------------------|\n"
		+"|(6)Convertirse en Kage                 |\n"
		+"|---------------------------------------|\n"
		+"\nElige numero de tu opcion: ");
	}
	/* PRINTEA EL MENU DE LAS MISIONES*/
	public void getMenuMision() {
		System.out.println("\nNombre: " +nombre+ "  Aldea: " +aldea+ "  Rango: "+rango
		+"\nVida: "  +vida+"/"+vidamax+"  Oro: "+oro+"  Nivel: "+nivel
		+"\nExperiencia: "+barraexp
		+"\nEnergia: "+barraener
		+"\n\n----------------MISIONES----------------\n"
		+"|(1)Mision Tipo D (Ifnin)               |\n"
		+"|---------------------------------------|\n"
		+"|(2)Mision Tipo C (Ifnin & Fornins)     |\n"
		+"|---------------------------------------|\n"
		+"|(3)Mision Tipo B (Fornins)             |\n"
		+"|---------------------------------------|\n"
		+"|(4)Mision Tipo A (Whilenins)           |\n"
		+"|---------------------------------------|\n"
		+"|(5)Mision Tipo S (Whilenins & Kages)   |\n"
		+"|---------------------------------------|\n"
		+"\nElige numero de tu opcion: ");
	}
	/* PRINTEA EL MENU DE PELEAS*/
	public void getMenuPelea() {
		System.out.println("\n |(1)Ataque Fisico| |(2)Usar Jutsu| \n");
	}

	/* RETORNA EL ORO DEL JUGADOR*/
	public int getOro() {
		return oro;
	}
	/* PRINTEA EL MENU DE MISION TERMINADA*/
	public void getMenuMisionTerminada(int exp, int oro) {
		System.out.println("\nCUMPLISTE LA MISION CON EXITO\n"
				+ "Experiencia + "+exp+"  Oro + "+oro);
	}


}
