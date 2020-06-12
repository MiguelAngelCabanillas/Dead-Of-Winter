package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
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
	private int comida;
	private int hambre;
	private int vertedero;
	
	private List<Jugador> jugadores = new ArrayList<>();
	private Tablero tablero;
	private Objetivo_Principal objetivo;
	
	private Random r = new Random();
	
	private List<Carta_Objeto> mazoInicial;
	
	private Mazo comisaria;
	private Mazo supermercado;
	private Mazo colegio;
	private Mazo gasolinera;
	private Mazo hospital;
	private Mazo biblioteca;
	
	private InicSupervivientes supervivientes;
	private InicCrisis crisis;
	private PriorityQueue<Carta_Supervivientes> enPartida;
	
	//DATOS PARA EL SERVIDOR
	private String [] idCartas;
	private Jugador jugadorActual;
	private boolean finalBueno = false;
	private int muertos = 0;
	private Crisis crisisActual;
	private String[] dados;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Principal(int objetivo) {
		this.objetivo = new Objetivo_Principal(objetivo);
		moral = this.objetivo.getMoral();
		rondasRestantes = this.objetivo.getRondas();
		
		supervivientes = new InicSupervivientes();
		crisis = new InicCrisis(jugadores.size());
		enPartida = new PriorityQueue<>();
		
		crisisActual = crisis.getCrisis();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODOS DEL CONSTRUCTOR

	
	private void inicJugadores(int numJugadores) {
		
		int cartas = numJugadores * 5;
		List<Carta> mazoJugador;
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
			jugadores.add(new Jugador(i, mazoJugador, tablero, objetivo));
		}
		int i = 0;
		
		//SE TIRAN LOS DADOS
		dados = new String[numJugadores];
		
		for(Jugador j : jugadores) {
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
	
	//METODOS DE INICIO DE LA PARTIDA
	
	//INICIA EL MAZO INICIAL
	private List<Carta_Objeto> inicInicial() {
		List<Carta_Objeto> mazo = new ArrayList<>();
		int [] cartas = {0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5};
		 
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
		int [] cartas = {0, 0, 0, 0, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 8, 10, 10, 10, 9, 9, 11, 11, 11};
		 
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
		int [] cartas = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4 ,4, 4, 4, 4, 4, 4, 7, 8};
		 
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
		int [] cartas = {0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 6, 7, 12, 13};
		 
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
		int [] cartas = {0, 0, 0, 0, 0, 1, 1, 2, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 7, 8, 10, 11};
		 
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
		int [] cartas = {0, 0, 0, 0, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 7, 8};
		 
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
		int [] cartas = {0, 0, 0, 0, 0, 1, 1, 1, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 8, 14, 15, 16, 17, 18, 21};
		 
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
	
	public String usarPasiva() {
		return null;
	}
	
	public boolean aportarCrisis(int id) {
		boolean estado = false;
		if(jugadorActual.anyadirCrisis(id)) {
			crisisActual.ayadir(id);
			
			estado = true;
		}
		return estado;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS PARA EL SERVIDOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	//INICIA LOS JUGADORES
	public void inicPartida(int numJugadores) {
		inicMazos();
		inicJugadores(numJugadores);
		inicTablero(numJugadores);
	}
	
	//RESETEA LAS HABILIDADES DEL ACTUAL Y PASA AL SIGUIENTE
	public void pasaTurno(int id) {
		jugadorActual.resetHab();
		jugadorActual = jugadores.get(id);
		inicTurno();
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
		
		if(!crisisActual.pasada()) {
			fallo();
		}else if(crisisActual.sobra()) {
			moral++;
		}
		
		rondasRestantes--;
		
		return datos;
	}
	
	//TODO queda que Roldán diga como me pasa los datos
	public void supervivientesIniciales() {
		
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
	
	public int getRonda() {
		return rondasRestantes;
	}
	
	public int getMoral() {
		return moral;
	}	

	public int getRondasRestantes() {
		return rondasRestantes;
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
	
	private String actualizarTodosSupervivientes() {
		String muertos = "";
		List<Carta_Supervivientes> aux;
		
		for(Jugador j : jugadores) {
			aux = j.getMazoSuperviviente();
			
			//MATAMOS A LOS SUPERVIVIENTES CON TRES HERIDAS
			for(Carta_Supervivientes personaje : aux) {
				if(personaje.estaMuerto()) {
					muertos += personaje.getId() + "|";
					aux.remove(personaje);
				}
			}
		}
		
		return muertos;
	}
	
	private String fallo() {
		String datos = null;
		
		switch(crisisActual.getId()) {
		case 0 : {
			
			//LOS 5 SUPERVIVIENTES CON MAS INFLUENCIA RECIBEN UNA HERIDA POR CONGELACION
			for(int i = 0; i < 5; i++) {
				enPartida.peek().recibirHerida(true);
			}
		}
		break;
		case 1 : {
			
			//SE BAJA LA MORAL Y SE AÑADE TOKEN DE HAMBRE
			hambre++;
			moral--;
		}
		break;
		case 2 : {
			
			//SE BAJA LA MORAL Y SE AÑADE UNA HERIDA A CADA SUPERVIVIENTE
			moral--;
			for(Carta_Supervivientes sup : enPartida) {
				sup.recibirHerida(false);
			}
			
			actualizarTodosSupervivientes();
		}
		break;
		case 3 : {
			moral -=2;
		}
		break;
		case 4 : {
			moral -=2;
		}
		break;
		case 5 : {
			
			//SE AÑADEN 3 ZOMBIES EN BIBLIOTECA Y EN SUPERMERCADO
			tablero.getBiblioteca().anyadirZombie();
			tablero.getBiblioteca().anyadirZombie();
			tablero.getBiblioteca().anyadirZombie();
			
			tablero.getSupermercado().anyadirZombie();
			tablero.getSupermercado().anyadirZombie();
			tablero.getSupermercado().anyadirZombie();
		}
		break;
		case 6 : {
			
			//SE AÑADEN 6 SUPERVIVIENTES A LA COLONIA Y UNO EN CADA LOCALIZACION
			tablero.getBiblioteca().anyadirZombie();
			tablero.getColegio().anyadirZombie();
			tablero.getComisaria().anyadirZombie();
			tablero.getGasolinera().anyadirZombie();
			tablero.getHospital().anyadirZombie();
			tablero.getSupermercado().anyadirZombie();
			
			tablero.getColonia().anyadirZombie();
			tablero.getColonia().anyadirZombie();
			tablero.getColonia().anyadirZombie();
			tablero.getColonia().anyadirZombie();
			tablero.getColonia().anyadirZombie();
			tablero.getColonia().anyadirZombie();
		}
		break;
		case 7 : {
			//TODO
		}
		break;
		}
		
		return datos;
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
