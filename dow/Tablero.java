//Gómez 14/05
package dow;

public class Tablero {
	
	//Atributos.
	private static Localizacion hospital;
	private static Localizacion colegio;
	private static Localizacion comisaria;
	private static Localizacion supermercado;
	private static Localizacion gasolinera;
	private static Localizacion biblioteca;
	private static Colonia colonia;
	
	//Constructor/es.
	public Tablero() {
		//Donde pone null habría que pasarle los mazos BARAJADOS.
		//En colonia los atributos dependerán de la carta de objetivo principal. Sería interesante
		//Pasarle a tablero los objetivos principales.
		hospital = new Localizacion("Hospital.", null, 4, 4);
		colegio = new Localizacion("Colegio", null, 4, 4);
		comisaria = new Localizacion("Comisaría.", null, 3, 4);
		supermercado= new Localizacion("Supermercado.", null, 3, 4);
		gasolinera = new Localizacion("Gasolinera.", null, 2, 4);
		biblioteca = new Localizacion("Biblioteca.", null, 3, 4);
		colonia = new Colonia(null, null, null, null);
	}
	
	//Métodos.
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
