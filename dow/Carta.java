//PARA NADA VERSIÓN DEFINITIVA
package dow;

public class Carta {

	private int ID;
	private String Nombre;
	//hay que saber como vamos a implementas las imagenes de las cartas
	//private imagen;
	private String descripcion;
	
	public Carta(int id, String nombre, String desc) {
		ID = id;
		Nombre = nombre;
		descripcion = desc;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getNombre() {
		return Nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}
