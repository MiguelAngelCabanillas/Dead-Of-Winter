//PARA NADA VERSI�N DEFINITIVA
package dow;

import java.util.LinkedList;
import java.util.List;

public class Localizacion {
	//Atributos
	private String nombre;
	private List <Carta_Supervivientes> supervivientes;
	private Mazo mazo;
	private CasillasZombie zombies;
	
//	/*El siguiente es el atributo que representa las entradas. Que sea lista de Carta no me gusta. 
//	  En principio, en una entrada puede haber s�lo una de tres:
//		1) Zombie
//		2) Barricada
//		3) Nada
//	Quiz� ser�a interesante crear una clase Zombie y una Barricada herederas de Carta, como viene en las reglas.
//	Otra soluci�n ser�a implementarlo con una lista de enteros donde 0 represente nada, 1 un zombie y 2 una barricada, de manera
//	que el resto de m�todos que interact�en con las entradas "conozcan" qu� significa cada cosa.
//	���CONSULTAR CON NIETO!!!
//	*/
//	private List <Carta> entradas;
	
	private int tokensDeRuido;
	
	//Constructor/es
	public Localizacion(String n, Mazo m, int c, int z) {
		this.nombre = n;
		this.mazo = m;
		this.supervivientes = new LinkedList<Carta_Supervivientes>();
		this.entradas = new LinkedList<Carta>();
		this.tokensDeRuido = 0;
		this.zombies = new CasillasZombie(c, z);
	}

	//M�todos
	public String getNombre() {
		return nombre;
	}

	public List<Carta_Supervivientes> getSupervivientes() {
		return supervivientes;
	}

	public void setSupervivientes(List<Carta_Supervivientes> supervivientes) {
		this.supervivientes = supervivientes;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public List<Carta> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Carta> entradas) {
		this.entradas = entradas;
	}

	public int getTokensDeRuido() {
		return tokensDeRuido;
	}

	public void setTokensDeRuido(int tokensDeRuido) {
		this.tokensDeRuido = tokensDeRuido;
	}
	
	//ACCIONES DE MODIFICACION
	
	public void matar() throws matarException {
		zombies.matarZombie();
	}
	
}
