package Partida;

import java.util.LinkedList;
import java.util.List;
import Cartas.Carta_Objeto;

public class Partida {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS QUE EL PROGRAMA NECESITA DESDE FUERA
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//Misma longitud.
	public static String [] nombres;
	public static List<Carta_Objeto> [] mazoObjetosJugadores;
	
	public static Mazo mazoHospital;
	public static Mazo mazoColegio;
	public static Mazo mazoComisaria;
	public static Mazo mazoSupermercado;
	public static Mazo mazoGasolinera;
	public static Mazo mazoBiblioteca;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////MAIN
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String [] args) {
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		
//		Tablero tablero = new Tablero(nombres.length, mazoHospital, mazoColegio, mazoComisaria,
//				mazoSupermercado, mazoGasolinera, mazoBiblioteca);
		
		for (int i = 0; i < nombres.length; ++i) {
			//jugadores.add(new Jugador(i, mazoObjetosJugadores[i], tablero));
		}
		
		int current = 0;
		//while (tablero.getColonia().getMoral() != 0 ) {6
			//Jugador jugador = jugadores.get(current);
			/*switch donde se llame a los métodos de la instancia jugador en función del botón presionado.*/
			
			if (current == nombres.length - 1) {
				current = 0;
			} else {
				++current;
			}
		//}

	}
}
