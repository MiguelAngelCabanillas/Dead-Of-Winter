//G�mez 14/05

package dow;

public class Carta {

	//Atributos
	private int id;
	private String nombre;
	//private imagen;
	private String descripcion;
	
	//Constructor/es.
	public Carta(int id, String nombre, String desc) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
	}
	
	//M�todo/s
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
}
