//Gómez 14/05

package dow;

import java.util.Stack;

public class Mazo {
	
	//Atributos.
	private Stack<Carta_Objeto> Mazo = new Stack<>();
	
	//Constructor/es.
	public Mazo(Stack<Carta_Objeto> mazo) {
		Mazo = mazo;
	}
	
	//Métodos.
	public Carta_Objeto getCarta() {
		return Mazo.pop();
	}
	
	public boolean vacio() {
		return Mazo.isEmpty();
	}
	
}
