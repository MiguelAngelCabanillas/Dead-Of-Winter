package Partida;

import java.util.List;
import java.util.Map;

import Cartas.Carta_Supervivientes;

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
		comisaria = new Localizacion(mComisaria, 3, 4, 0);
		supermercado= new Localizacion(mSupermercado, 3, 4, 1);
		colegio = new Localizacion(mColegio, 4, 4, 2);
		gasolinera = new Localizacion(mGasolinera, 2, 4, 3);
		hospital = new Localizacion(mHospital, 4, 4, 4);
		biblioteca = new Localizacion(mBiblioteca, 3, 4, 5);
		colonia = new Colonia(objetivoPrincipal, jugadores);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////	
	
	//GETTERS Y SETTERS
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
