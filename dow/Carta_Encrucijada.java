package dow;
public class Carta_Encrucijada extends Carta{

	private String Condicion;
	private String Efecto;
	private boolean Votacion;
	
	
	public Carta_Encrucijada(int id, String nombre, String desc, String condicion, String efecto, boolean votacion) {
		super(id, nombre, desc);
		Condicion = condicion;
		Efecto = efecto;
		Votacion = votacion;
	}
	
	public String getCondicion() {
		return Condicion;
	}
	
	public String getEfecto() {
		return Efecto;
	}
	
	public boolean getVotacion() {
		return Votacion;
	}

}
