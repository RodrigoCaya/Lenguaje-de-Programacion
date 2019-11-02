package lp;
/* YA SE COMENTARON LAS FUNCIONES EN LAS FUNCIONES HIJOS*/
public interface personaje {

	public void asignarVida(int n);

	public void asignarNinja(String aldea);

	public void asignarNombre(String nombre);

	public void asignarAtaqueFisico();

	public void asignarObjeto(String obj);

	public void asignarRango(String rango);

	public void realizarAtaque(enemigo malo ,jugador pj);

	public void asignarNivel(int lvl);
}
