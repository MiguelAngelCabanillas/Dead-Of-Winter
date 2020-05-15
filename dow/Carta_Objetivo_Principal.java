package dow;

public class Carta_Objetivo_Principal extends Carta {

	//Atributos.
	private String objetivo;
	
	//Constructor/es.
	public Carta_Objetivo_Principal(int id, String nombre, String desc, String objetivo) {
		super(id, nombre, desc);
		this.objetivo = objetivo;
	}
	
	//Método/s.
	public String getObjetivo() {
		return this.objetivo;
	}
	
}
