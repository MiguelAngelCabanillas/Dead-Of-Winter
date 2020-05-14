//PARA NADA VERSIÓN DEFINITIVA
package dow;

public class Tablero {
	//Atributos
	private static Localizacion hospital;
	private static Localizacion colegio;
	private static Localizacion comisaria;
	private static Localizacion supermercado;
	private static Localizacion gasolinera;
	private static Localizacion biblioteca;
	private static Colonia colonia;
	
	//Constructor/es
	public Tablero() {
		//Aquí inicializariamos las localizaciones y los jugadores (personajes y cartas).
		//Aquí sólo se inicializan las condiciones necesarias para comenzar la partida. La clase Partida es la que se encarga
		//del transcurso de la misma, de manera que esta modificará Tablero (que a su vez modifica Localización)
		//a medida que transcurran los turnos.
	}
	
	//Métodos
	public static Colonia getColonia() {
		return colonia;
	}

	public static Localizacion getHospital() {
		return hospital;
	}

	public static Localizacion getColegio() {
		return colegio;
	}

	public static Localizacion getComisaria() {
		return comisaria;
	}

	public static Localizacion getSupermercado() {
		return supermercado;
	}

	public static Localizacion getGasolinera() {
		return gasolinera;
	}

	public static Localizacion getBiblioteca() {
		return biblioteca;
	}
	
}
