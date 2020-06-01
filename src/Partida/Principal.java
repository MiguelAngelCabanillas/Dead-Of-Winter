package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Cartas.Carta_Objetivo_Principal;
import Cartas.Carta_Objeto;

public class Principal {

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private List<Jugador> jugadores = new ArrayList<>();
	private Tablero tablero;
	private Carta_Objetivo_Principal objetivo;
	private List<Integer> supervivientes;
	
	private Random r = new Random();
	
	private List<Carta_Objeto> mazoInicial;
	
	private Mazo comisaria;
	private Mazo supermercado;
	private Mazo colegio;
	private Mazo gasolinera;
	private Mazo hospital;
	private Mazo biblioteca;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Principal(int numJugadores, int objetivo) {
		//inicTablero(numJugadores);
		//inicMazos();
		//inicJugadores(numJugadores);
		//inicSupervivientes();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODOS DEL CONSTRUCTOR
	private void inicJugadores(int numJugadores) {
		
		int cartas = 25 / numJugadores;
		List<Carta_Objeto> mazoJugador;
		
		for(int i = 0; i < numJugadores; i++) {
			mazoJugador = new ArrayList<>();
			for(int j = 0; j < cartas; j++) {
				mazoJugador.add(mazoInicial.remove(r.nextInt(mazoInicial.size())));
			}
			jugadores.add(new Jugador(i, mazoJugador, tablero));
		}
	}
	
	public void inicTablero(int numJugadores) {
		tablero = new Tablero(numJugadores, comisaria, supermercado, colegio, gasolinera, hospital, biblioteca, null);
	}
	
	public void inicMazos() {
		
	}
	
	public void inicSupervivientes() {
		supervivientes = new ArrayList<>();
		
		for(int i = 0; i < 24; i++) {
			supervivientes.add(i);
		}
	}
	
	//METODOS DE INICIO DE LA PARTIDA
	
	public int[] inicPartida(int numJugadores) {
		int[] sup = new int[numJugadores * 2];
		
		for(int i = 0; i < numJugadores; i+=2) {
			sup[i] = r.nextInt(supervivientes.size());
			supervivientes.remove(sup[i]);
			jugadores.get(i%numJugadores).addSuperviviente(sup[i]);
			
			sup[i+1] = r.nextInt(supervivientes.size());
			supervivientes.remove(sup[i+1]);
			jugadores.get(i%numJugadores).addSuperviviente(sup[i+1]);
		}
		
		return sup;
	}
	
	public Jugador getJugador(int id) {
		return jugadores.get(id);
	}
}
