package lp;

/* YA SE COMENTARON LAS FUNCIONES EN LAS FUNCIONES HIJOS*/
public abstract class ninja {

	protected String jutsu_3;

	protected String jutsu_2;

	protected String jutsu_1;

	protected int Nivel_Jutsu;

	protected abstract void crearNinja();

	public abstract void asignarNivelJutsus(jugador pj);
	public abstract void asignarNivelJutsus(enemigo pj);
	public abstract int usarJutsu(jugador pj);
	public abstract int usarJutsu(enemigo pj);
	public abstract void getMenu();
}
