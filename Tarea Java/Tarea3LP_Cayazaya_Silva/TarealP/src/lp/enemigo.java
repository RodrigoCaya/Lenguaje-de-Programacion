package lp;


public class enemigo implements personaje {
	private int random = 0;
	private int vida;
	private int vidamax;
	private String aldea;
	private String nombre;
	private int ataque;
	private String objeto;
	private String rango;
	private int danoObj=0;
	private ninja ninya;
	private int nivel;
	/* CONSTRUCTOR DE LA CLASE ENEMIGO DONDE SE ASIGNAN LOS NINJAS DE CADA ALDEA*/
	public enemigo(int n1,String ald1,String name1,int atk1,String obj1,String r1, int level, int life) {
		this.random = (int) (Math.random() * 5) + 1;
		if(random == 1) {
			asignarNinja("Pythonia");
		}
		else if(random == 2) {
			asignarNinja("Cthonia");
		}
		else if(random == 3) {
			asignarNinja("Javania");
		}
		else if(random == 4) {
			asignarNinja("Schemia");
		}
		else if(random == 5) {
			asignarNinja("Prolonia");
		}
		asignarVida(n1);
		/*ver nombres a usar*/
		nivel = level;
		vida = life;
		asignarNombre(name1);
		asignarAtaqueFisico();
		asignarObjeto(obj1);
		asignarRango(r1);
		asignarNivel(nivel);
		asignarVida(vida);
		asignarVidaMax();
	}
	/* ASIGNA LA VIDA DEL ENEMIGO*/
	public void asignarVida(int life) {
		vida=life;
	}
	/* ASIGNA LA VIDA MÁXIMA DEL ENEMIGO*/
	public void asignarVidaMax() {
		vida = 100;
		vidamax=vida + nivel;
		vida=vidamax;
	}
	/* CREA UN NINJA ENEMIGO DEPENDIENDO DE LA ALDEA*/
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
	/* ASIGNA EL NOMBRE DEL ENEMIGO*/
	public void asignarNombre(String name) {
		nombre=name;
	}
	/* ASIGNA EL ATAQUE FISICO DEL ENEMIGO*/
	public void asignarAtaqueFisico(){
		ataque=3+nivel+danoObj;
	}
	/* ASIGNA EL DAÑO DEL OBJETO DEL ENEMIGO*/
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
	/* ASIGNA EL RANGO DEL ENEMIGO*/
	public void asignarRango(String r) {
		rango=r;
	}
	/* REALIZA UN ATAQUE FISICO O JUTSU DEL ENEMIGO SOBRE EL JUGADOR*/
	public void realizarAtaque(enemigo malo ,jugador pj) {
		this.random = (int) (Math.random() * 2) + 1;
		if(random == 1) {
			System.out.println("El enemigo te hace un ataque fisico");
			pj.asignarVida(pj.getVida()-malo.getAtaqueFisico());
		}
		else if(random == 2) {
			pj.asignarVida(pj.getVida()-getNinja().usarJutsu(malo));
		}
	}
	/* ASIGNA EL NIVEL DEL ENEMIGO*/
	public void asignarNivel(int lvl){
		nivel=lvl;
	}


	//########################################################################//
	/* RETORNA EL NOMBRE DEL ENEMIGO*/
	public String getNombre() {
		return nombre;
	}
	/* RETORNA LA VIDA DEL ENEMIGO*/
	public int getVida() {
		return vida;
	}
	/* RETORNA LA VIDA MÁXIMA DEL ENEMIGO*/
	public int getVidaMax() {
		return vidamax;
	}
	/* RETORNA EL ENEMIGO NINJA QUE SE CREO EN ASIGNARNINJA*/
	public ninja getNinja() {
		return ninya;
	}
	/* RETORNA EL ATAQUE FISICO DEL ENEMIGO*/
	public int getAtaqueFisico() {
		asignarAtaqueFisico();
		return ataque;
	}
	/* RETORNA EL OBJETO DEL ENEMIGO*/
	public String getObjeto() {
		return objeto;
	}
	/* RETORNA EL RANGO DEL ENEMIGO*/
	public String getRango() {
		return rango;
	}
	/* RETORNA EL NIVEL DEL ENEMIGO*/
	public int getNivel() {
		return nivel;
	}
}
