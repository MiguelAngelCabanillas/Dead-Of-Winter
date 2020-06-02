package Cartas;

public class Carta_Encrucijada extends Carta {

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String condicion;
	private String efecto;
	private boolean votacion;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Carta_Encrucijada(int id, String condicion, String efecto, boolean votacion) {
		super(id);
		this.condicion = condicion;
		this.efecto = efecto;
		this.votacion = votacion;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
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
