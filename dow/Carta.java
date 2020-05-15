package dow;

public class Carta {

	//Atributo/s.
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
	
	//Método/s.
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
