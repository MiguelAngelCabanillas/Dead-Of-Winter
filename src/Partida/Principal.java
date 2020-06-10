package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Cartas.Carta;
import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;

public class Principal {

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int moral;
	private int rondasRestantes;
	
	private List<Jugador> jugadores = new ArrayList<>();
	private Tablero tablero;
	private Objetivo_Principal objetivo;
	private List<Integer> supervivientes;
	
	private Random r = new Random();
	
	private List<Carta_Objeto> mazoInicial;
	
	private Mazo comisaria;
	private Mazo supermercado;
	private Mazo colegio;
	private Mazo gasolinera;
	private Mazo hospital;
	private Mazo biblioteca;
	
	//DATOS PARA EL SERVIDOR
	private String [] idCartas;
	private Jugador jugadorActual;
	private boolean finalBueno = false;
	private int muertos = 0;
	private String[] dados;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Principal(int objetivo) {
		this.objetivo = new Objetivo_Principal(objetivo);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODOS DEL CONSTRUCTOR

	
	private void inicJugadores(int numJugadores) {
		
		int cartas = numJugadores * 5;
		List<Carta_Objeto> mazoJugador;
		idCartas = new String[cartas];
		idCartas[0] = "";
		idCartas[1] = "";
		idCartas[2] = "";
		idCartas[3] = "";
		idCartas[4] = "";
		
		for(int i = 0; i < numJugadores; i++) {
			mazoJugador = new ArrayList<>();
			for(int j = 0; j < 5; j++) {
				if(j != 0) {
					idCartas[i] += "|";
				}
				
				Carta_Objeto aux = mazoInicial.remove(r.nextInt(mazoInicial.size()));
				
				idCartas[i] += aux.getId();
						
				mazoJugador.add(aux);
			}
			jugadores.add(new Jugador(i, mazoJugador, tablero));
		}
		int i = 0;
		
		for(Jugador j : jugadores) {
			//ELIMINAMOS LOS SUPERVIVIENTES MUERTOS AL ACTUALIZAR LOS ZOMBIES
			dados[i] = j.tirarDados();
			i++;
		}
	}
	
	private void inicTablero(int numJugadores) {
		tablero = new Tablero(numJugadores, comisaria, supermercado, colegio, gasolinera, hospital, biblioteca, objetivo);
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
	
	/*
	 * comida -> 0
	 * medicina -> 1
	 * trastos -> 2
	 * gasolina -> 3
	 * supervivientes -> 4
	 * equipables -> 5
	 */
	
	//INICIA EL MAZO INICIAL
	private List<Carta_Objeto> inicInicial() {
		List<Carta_Objeto> mazo = new ArrayList<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
		 
		int i = 0;
		
		while(i < 25){
			int aux = r.nextInt(25);
			if(cartas[aux] != -1) {
				mazo.add(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO COMISARÍA
	private Stack<Carta> iniCComisaria() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 8, 8, 8, 9, 9, 10, 10, 10};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO SUPERMERCADO
	private Stack<Carta> inicSupermercado() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 7, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 ,2, 2, 2, 2, 2, 2, 4, 5};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO COLEGIO
	private Stack<Carta> inicColegio() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 6, 6, 6, 7, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 5, 11, 12};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO GASOLINERA
	private Stack<Carta> inicGasolinera() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 6, 6, 7, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 8, 10};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO HOSPITAL
	private Stack<Carta> inicHospital() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 6, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	//INICIA MAZO BIBLIOTECA
	private Stack<Carta> inicBiblioteca() {
		Stack<Carta> mazo = new Stack<>();
		int [] cartas = {0, 0, 0, 0, 0, 6, 6, 6, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 13, 14, 15, 16, 17, 18};
		 
		int i = 0;
		
		while(i < 30){
			int aux = r.nextInt(30);
			if(cartas[aux] != -1) {
				mazo.push(new Carta_Objeto(cartas[aux], cartas[aux], cartas[aux])); 
				cartas[aux] = -1; 
				i++;
			}
		}
		
		return mazo;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS DE JUGADOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	public int atacar(int idSuperviviente) {
		return jugadorActual.atacar(idSuperviviente);
	}
	
	public int mover(int idSuperviviente, int localizacion) {	
		return jugadorActual.mover(idSuperviviente, localizacion);
	}
	
	public String buscar(int idJugador) {
		return jugadorActual.buscar(idJugador);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS PARA EL SERVIDOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	//INICIA LOS JUGADORES
	public void inicPartida(int numJugadores) {
		inicMazos();
		inicJugadores(numJugadores);
		inicSupervivientes();
		inicTablero(numJugadores);
	}
	
	public void pasaTurno(int id) {
		jugadorActual = jugadores.get(id);
	}
	
	public String pasaRonda() {
		//ACTUALIZAMOS LOS ZOMBIES DE CADA LOCALIZACIÓN
		String datos = actualizarTablero();
		int i = 0;
		
		for(Jugador j : jugadores) {
			//ELIMINAMOS LOS SUPERVIVIENTES MUERTOS AL ACTUALIZAR LOS ZOMBIES
			j.matar();
			dados[i] = j.tirarDados();
			i++;
		}
		
		return datos;
	}
	
	//COMPROBAMOS EL OBJETIVO PRINCIPAL, LA MORAL Y EL TURNO
	
	//PUEDE QUE HAYA QUE ELIMINAR ESTA FUNCION
	public Jugador getJugador(int id) {
		return jugadores.get(id);
	}
	
	public void inicTurno() {
		actualizarSupervivientesActual();
	}
	
	public String getIdCartas (int jugador) {
		return idCartas[jugador];
	}
	
	public String getDados (int jugador) {
		return dados[jugador];
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS AUXILIARES
	///////////////////////////////////////////////////////////////////////////////////////////////
	private String actualizarTablero() {
		String aux = "";
		
		String[] com = tablero.getComisaria().actualizarCasillasZombiePasoDeRonda();
		String[] sup = tablero.getSupermercado().actualizarCasillasZombiePasoDeRonda();
		String[] col = tablero.getColegio().actualizarCasillasZombiePasoDeRonda();
		String[] gas = tablero.getGasolinera().actualizarCasillasZombiePasoDeRonda();
		String[] hos = tablero.getHospital().actualizarCasillasZombiePasoDeRonda();
		String[] bib = tablero.getBiblioteca().actualizarCasillasZombiePasoDeRonda();
		String[] coll = tablero.getColonia().actualizarCasillasZombiePasoDeRonda();
		
		aux += com[0];
		aux += "|" + sup[0];
		aux += "|" + col[0];
		aux += "|" + gas[0];
		aux += "|" + hos[0];
		aux += "|" + bib[0];
		aux += "|" + coll[0];
		
		//ACTUALIZAMOS LOS MUERTOS EN LA PARTIDA
		muertos += Integer.parseInt(com[1]);
		muertos += Integer.parseInt(sup[1]);
		muertos += Integer.parseInt(col[1]);
		muertos += Integer.parseInt(gas[1]);
		muertos += Integer.parseInt(hos[1]);
		muertos += Integer.parseInt(bib[1]);
		muertos += Integer.parseInt(coll[1]);
		
		return aux;
	}
	
	private String actualizarSupervivientesActual() {
		String muertos = "";
		List<Carta_Supervivientes> aux = jugadorActual.getMazoSuperviviente();
		
		//ACTUALIZAMOS HERIDAS POR CONGELAMIENTO
		for(Carta_Supervivientes personaje : aux) {
			personaje.congelamiento();
		}
		
		//MATAMOS A LOS SUPERVIVIENTES CON TRES HERIDAS
		for(Carta_Supervivientes personaje : aux) {
			if(personaje.estaMuerto()) {
				muertos += personaje.getId() + "|";
				aux.remove(personaje);
			}
		}
		
		return muertos;
	}
	
	
	
	//ESTE METODO SE COMPRUEBA CADA VEZ QUE EL JUGADOR REALIZA UNA ACCIÓN PARA MONITOREAR EL PROGRESO DEL OBJETIVO
	private boolean terminaPartida() {
		boolean estado;
		if(objetivo.completado()) {	//SE CUMPLE EL OBJETIVO
			estado = true;
			finalBueno = true;
		}else if(rondasRestantes == 0) {	//SE HAN ACABADO LAS RONDAS
			estado = true;
			finalBueno = false;
		}else if(moral <= 0) {	//LA MORAL ES 0
			estado = true;
			finalBueno = false;
		}else {
			estado = false;
		}
		
		return estado;
	}
	
}
