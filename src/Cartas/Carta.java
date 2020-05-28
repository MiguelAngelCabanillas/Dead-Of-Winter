package Cartas;

public class Carta {

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int id;
	private String nombre;
	//private imagen;
	private String descripcion;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Carta(int id, String nombre, String desc) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = desc;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
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
