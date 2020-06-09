package Partida;

public class Tablero {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Localizacion comisaria;
	private Localizacion supermercado;
	private Localizacion colegio;
	private Localizacion gasolinera;
	private Localizacion hospital;
	private Localizacion biblioteca;
	private Colonia colonia;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Tablero(int jugadores, Mazo mComisaria, Mazo mSupermercado, Mazo mColegio, Mazo mGasolinera,
			Mazo mHospital, Mazo mBiblioteca, Objetivo_Principal objetivoPrincipal) {
		hospital = new Localizacion("Hospital.", mHospital, 4, 4);
		colegio = new Localizacion("Colegio", mColegio, 4, 4);
		comisaria = new Localizacion("Comisar�a.", mComisaria, 3, 4);
		supermercado= new Localizacion("Supermercado.", mSupermercado, 3, 4);
		gasolinera = new Localizacion("Gasolinera.", mGasolinera, 2, 4);
		biblioteca = new Localizacion("Biblioteca.", mBiblioteca, 3, 4);
		colonia = new Colonia(objetivoPrincipal, jugadores);
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
