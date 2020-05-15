//Gómez 14/05
package dow_v2.Partida;

import dow_v2.Cartas.Carta_Objetivo_Principal;

public class Tablero {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Localizacion hospital;
	private Localizacion colegio;
	private Localizacion comisaria;
	private Localizacion supermercado;
	private Localizacion gasolinera;
	private Localizacion biblioteca;
	private Colonia colonia;
	
	private Carta_Objetivo_Principal objetivo;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Tablero(int jugadores) {
		hospital = new Localizacion("Hospital.", null, 4, 4);
		colegio = new Localizacion("Colegio", null, 4, 4);
		comisaria = new Localizacion("Comisaría.", null, 3, 4);
		supermercado= new Localizacion("Supermercado.", null, 3, 4);
		gasolinera = new Localizacion("Gasolinera.", null, 2, 4);
		biblioteca = new Localizacion("Biblioteca.", null, 3, 4);
		colonia = new Colonia(objetivo, 5, 5, jugadores);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Colonia getColonia() {
		return colonia;
	}

	public Localizacion getHospital() {
		return hospital;
	}

	public Localizacion getColegio() {
		return colegio;
	}

	public Localizacion getComisaria() {
		return comisaria;
	}

	public Localizacion getSupermercado() {
		return supermercado;
	}

	public Localizacion getGasolinera() {
		return gasolinera;
	}

	public Localizacion getBiblioteca() {
		return biblioteca;
	}
	
}
