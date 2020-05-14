package dow;
public class Carta_Objetivo_Principal extends Carta {

	private String Objetivo;
	
	public Carta_Objetivo_Principal(int id, String nombre, String desc, String objetivo) {
		super(id, nombre, desc);
		Objetivo = objetivo;
	}
	
	public String getObjetivo() {
		return Objetivo;
	}
	
}
