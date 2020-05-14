//VERSIÓN SEMI-DEFINITIVA
package dow;

import java.util.Stack;

public class Mazo {

	private Stack<Carta_Objeto> Mazo = new Stack<>();
	
	public Mazo(Stack<Carta_Objeto> mazo) {
		Mazo = mazo;
	}
	
	public Carta_Objeto getCarta() {
		return Mazo.pop();
	}
	
	public boolean vacio() {
		return Mazo.isEmpty();
	}
	
}
