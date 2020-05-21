package dow.Partida;

import java.util.Stack;
import dow.Cartas.Carta_Objeto;

public class Mazo {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Stack<Carta_Objeto> Mazo = new Stack<>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Mazo(Stack<Carta_Objeto> mazo) {
		Mazo = mazo;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Carta_Objeto getCarta() {
		return Mazo.pop();
	}
	
	public boolean vacio() {
		return Mazo.isEmpty();
	}
	
}