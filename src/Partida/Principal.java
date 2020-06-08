package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Cartas.Carta;
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
		inicMazos();
		inicJugadores(numJugadores);
		inicSupervivientes();
		inicTablero(numJugadores);
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
	
	private void inicTablero(int numJugadores) {
		tablero = new Tablero(numJugadores, comisaria, supermercado, colegio, gasolinera, hospital, biblioteca, null);
	}
	
	private void inicMazos() {
		comisaria = new Mazo(iniCComisaria());
		supermercado = new Mazo(inicSupermercado());
		colegio = new Mazo(inicColegio());
		gasolinera = new Mazo(inicGasolinera());
		hospital = new Mazo(inicHospital());
		biblioteca = new Mazo(inicBiblioteca());
		mazoInicial = inicInicial();
	}
	
	private void inicSupervivientes() {
		supervivientes = new ArrayList<>();
		
		for(int i = 0; i < 24; i++) {
			supervivientes.add(i);
		}
	}
	
	//METODOS DE INICIO DE LA PARTIDA
	
	//INICIA EL MAZO INICIAL
	private List<Carta_Objeto> inicInicial() {
		List<Carta_Objeto> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5, 5};
		/*comida = 4;
		medicina = 2;
		trastos = 6;
		gasolina = 8;
		supervivientes = 2;
		equipables = 8;
		 */
		 
		int i = 0;
		
		while(i < 25){
			int aux = r.nextInt(25);
			if(cartas[aux] != -1) {
				mazo.add(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO COMISARÍA
	private Stack<Carta> iniCComisaria() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5};
		/*comida = 4;
		medicina = 2;
		trastos = 6;
		gasolina = 8;
		supervivientes = 2;
		equipables = 8;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO SUPERMERCADO
	private Stack<Carta> inicSupermercado() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 ,2, 2, 2, 2, 2, 2, 4, 4};
		/*comida = 12;
		medicina = 9;
		trastos = 7;
		gasolina = 0;
		supervivientes = 2;
		equipables = 0;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO COLEGIO
	private Stack<Carta> inicColegio() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 4, 5, 5};
		/*comida = 11;
		medicina = 9;
		trastos = 6;
		gasolina = 0;
		supervivientes = 2;
		equipables = 2;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO GASOLINERA
	private Stack<Carta> inicGasolinera() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5};
		/*comida = 8;
		medicina = 6;
		trastos = 0;
		gasolina = 11;
		supervivientes = 2;
		equipables = 3;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO HOSPITAL
	private Stack<Carta> inicHospital() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5};
		/*comida = 6;
		medicina = 10;
		trastos = 4;
		gasolina = 8;
		supervivientes = 2;
		equipables = 0;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO BIBLIOTECA
	private Stack<Carta> inicBiblioteca() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5};
		/*comida = 8;
		medicina = 0;
		trastos = 6;
		gasolina = 10;
		supervivientes = 2;
		equipables = 4;
		 */
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(0, cartas[aux], r.nextInt(3) + 1)); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA LOS JUGADORES
	public int[] inicPartida(int numJugadores) {
		int[] sup = new int[numJugadores * 2];
		
		for(int i = 0; i < numJugadores*2; i+=2) {
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
