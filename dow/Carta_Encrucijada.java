package dow;

public class Carta_Encrucijada extends Carta {

	//Atributos.
	private String condicion;
	private String efecto;
	private boolean votacion;
	
	//Constructor/es.
	public Carta_Encrucijada(int id, String nombre, String desc, String condicion, String efecto, boolean votacion) {
		super(id, nombre, desc);
		this.condicion = condicion;
		this.efecto = efecto;
		this.votacion = votacion;
	}
	
	//Método/s.
	public String getCondicion() {
		return this.condicion;
	}
	
	public String getEfecto() {
		return this.efecto;
	}
	
	public boolean getVotacion() {
		return this.votacion;
	}

}
