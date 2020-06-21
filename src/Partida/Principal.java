package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import Cartas.Carta;
import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

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
	private String muertos = "";
	private Crisis crisisActual;
	
	//ESTOS SOLO SON LOS DADOS QUE SE DAN AL JUGADOR AL INICIO DE RONDA
	private String[] dados;
	
	//OTRAS VARIABLES
	private boolean libroDados = false;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Principal(int objetivo) {
		this.objetivo = new Objetivo_Principal(objetivo-1);
		moral = this.objetivo.getMoral();
		rondasRestantes = this.objetivo.getRondas();
		
		supervivientes = new InicSupervivientes();
		enPartida = new PriorityQueue<>();
		
		vertedero = 0;
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
			jugadores.add(new Jugador(i, mazoJugador, tablero, objetivo, supervivientes));
		}
		
		//SE TIRAN LOS DADOS
		dados = new String[numJugadores];
	}
	
	public void inicDados() {
		int i = 0;
		for(Jugador j : jugadores) {
			dados[i] = j.getDados().resetDados(j.getMazoSuperviviente().size());
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
	public void ConfirmarCarta(int idCarta) {
		jugadorActual.confirmarCarta(idCarta);
	}
	
	public String hacerRuido() throws BuscarException {
		return jugadorActual.hacerRuido();
	}
	
	public String atacar(int idSuperviviente) throws MatarException, DadoException {
		return jugadorActual.atacar(idSuperviviente);
	}
	
	public String mover(int idSuperviviente, int localizacion) throws MoverException {	
		return Integer.toString(idSuperviviente) + "|" + 
	Integer.toString(localizacion) + "|" + 
				jugadorActual.mover(idSuperviviente, localizacion);
	}
	
	public String buscar(int idSuperviviente) throws BuscarException, DadoException {
		jugadorActual.resetBuffer();
		return jugadorActual.buscar(idSuperviviente);
	}
	
	public String vaciarVertedero(int sup) throws VertederoException, DadoException {
		if(vertedero == 0) {
			throw new VertederoException("El vertedero ya está vacío");
		}
		
		Carta_Supervivientes aux = supervivientes.getSuperviviente(sup);
		int dado = -1;
		
		if(tablero.getColonia().esta(aux)) {
			dado = jugadorActual.valorDado(1);
			jugadorActual.getDados().usar(dado);
			
			vertedero -= aux.getVertedero();
			if(aux.getId() == 106) {
				aux.setUsado(true);
			}
			
			if(vertedero < 0) {
				vertedero = 0;
			}
		}else {
			throw new VertederoException("No estas en la colonia");
		}
		
		//RESETEAMOS LOS DADOS
		
		return Integer.toString(vertedero) + "|" + Integer.toString(dado);
	}
	
	//NECESITA SABER QUIEN USA LA HABILIDAD Y SOBRE QUIEN. MANDAR BASURA SI NO SE USA SOBRE NADIE
	public String usarHabilidad(int idSup, int idObjetivo, int idDado) throws HabilidadException, MatarException, DadoException, BuscarException {
		String salida = "";
		if(supervivientes.getSuperviviente(idSup).getUsado()) {
			throw new HabilidadException("Ya has usado tu habilidad");
		}
		
		switch(idSup) {
		case 102 : {	//ABOGADA: SE NECESITA LA ID DE UN SUPERVIVIENTE DEL JUGADOR O A CAMBIAR POR ID DE JUGADOR Y DEVUELVE UNA ID DE CARTA
			List<Carta> aux = jugadores.get((jugadorActual.getId() + 1) % jugadores.size()).getMazoObjetos();
			if(aux.size() > 0) {
				salida += aux.get(r.nextInt(aux.size())).getId();
			}else {
				throw new HabilidadException("El jugador no tiene cartas");
			}
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 107 :{//ALCALDE: NECESITA LA ID DEL DADO A AUMENTAR
			if(jugadorActual.getDados().getDados().size() > 0) {
				jugadorActual.getDados().aumentarDado(idDado);
				supervivientes.getSuperviviente(idSup).setUsado(true);
				salida += idDado + "|" + jugadorActual.getDados().getValor(idDado);
			}else {
				throw new HabilidadException("No tienes dados");
			}
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 110 : {	//SHERIFF: HACE UN ATAQUE DOBLE
			supervivientes.getSuperviviente(idSup).setPasivaDeAtaque(true);
			salida += jugadorActual.atacar(idSup);
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 112 : {	//QUIMICO: ELIMINA UNA CARTA DE MEDICINA PARA MATAR 3 ZOMBIES
			supervivientes.getSuperviviente(idSup).setPasivaDeAtaque(true);
			int carta = jugadorActual.eliminarCarta(3);
			if(carta == -1) {
				throw new HabilidadException("No tienes medicina");
			}
			salida += 3 + "|" + jugadorActual.atacar(idSup);
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 113 : {	//SANTA: MUERE PERO SUBE LA MORAL EN LUGAR DE BAJARLA
			supervivientes.getSuperviviente(idSup).recibirHerida(false);
			supervivientes.getSuperviviente(idSup).recibirHerida(false);
			supervivientes.getSuperviviente(idSup).recibirHerida(false);
			actualizarSupervivientesActual();
			
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() + 2);
			salida += 113;
		}
		break;
		case 114 : {//BOMBERO: BUSCA 4 CARTAS Y SI UNA ES DE EVENTO SE LA QUEDA. DEVUELVE EL SUPERVIVIENTE Y LA POSICION EN LA COLONIA AL IGUAL QUE EN BUSCAR
			supervivientes.getSuperviviente(idSup).setPasivaDeBusqueda(true);
			salida += jugadorActual.buscar(idSup); 
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 115 : {	//PIRATA: ROBA UNA CARTA DE UN JUGADOR
			List<Carta> mazoJugador = getJugConSup(idObjetivo).getMazoObjetos();
			if(mazoJugador.size() > 0) {
				Carta aux = mazoJugador.remove(r.nextInt(mazoJugador.size()));
				jugadorActual.getMazoObjetos().add(aux);
				salida += aux.getId() + "|" + getJugConSup(idObjetivo).getId();
			}else {
				throw new HabilidadException("El jugador no tiene cartas");
			}
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 117 : {	//PSQUIATRA: TIRA UN DADO OTRA VEZ
			if(jugadorActual.getDados().getDados().size() == 0) {
				throw new DadoException("No tienes dados");
			}
			
			jugadorActual.getDados().resetUnDado(idDado);
			salida += idDado + "|" + jugadorActual.getDados().getValor(idDado);
			supervivientes.getSuperviviente(idSup).setUsado(true);

		}
		break;
		case 122 : {//COCINERA: GASTA UN DADO CON 4 O MAS PARA PONER 2 FICHAS DE ALIMENTO EN LA RESERVA
			int dado = jugadorActual.valorDado(4);
			if(dado == -1) {
				throw new DadoException("Tus dados son muy pequeños");
			}
			if(jugadorActual.localizacion(supervivientes.getSuperviviente(idSup)).getId() != 6) {
				throw new HabilidadException("No estás en la colonia");
			}
			comida+=2;
			jugadorActual.getDados().usar(dado);
			
			salida += dado;
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 123 : {//PROFESORA: REALIZA UN ATAQUE EN EL COLEGIO QUE GASTA UN DADO CON UN 1 O MAYOR
			if(jugadorActual.localizacion(supervivientes.getSuperviviente(idSup)).getId() != 2) {
				throw new HabilidadException("No estas en el colegio");
			}
			salida += jugadorActual.atacar(idSup);
			supervivientes.getSuperviviente(idSup).setUsado(true);
			supervivientes.getSuperviviente(idSup).setPasivaDeAtaque(true);
		}
		break;
		case 125 : {//DOCTORA: CURA UNA HERIDA SIN COSTE
			supervivientes.getSuperviviente(idObjetivo).curarHerida();
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		case 127 : {//PILOTO: PUEDE MIRAR UNA CARTA DEL MAZO
			Carta aux = jugadorActual.localizacion(supervivientes.getSuperviviente(idSup)).cogerCarta();
			salida += aux.getId();
			jugadorActual.localizacion(supervivientes.getSuperviviente(idSup)).getMazo().anyadirAlPrincipio(aux);
			supervivientes.getSuperviviente(idSup).setUsado(true);
		}
		break;
		default : throw new HabilidadException("La habilidad de este superviviente es pasiva");
		}
		
		moral = tablero.getColonia().getMoral();
		System.out.println("SalidaPartida " + salida);
		
		return salida;
	}
	
	public String aportarCrisis(int id) {
		int idJug = jugadorActual.getId();
        crisisActual.anyadir(id,idJug);
        jugadorActual.eliminarCarta(id);
        return crisisActual.getContribJug();
    }
	
	public String ponerBarricada(int sup) throws BarricadaException, DadoException {
		String msg = jugadorActual.barricada(sup);
		return msg;
	}
	
	public String usarCarta(int idCarta, int idSup) throws HeridaException, GasolinaException, DadoException, EquipableException {
		String salida = "";
		
			//COMIDA
		switch (idCarta) {
		case 0: comida += 1;
		jugadorActual.eliminarCarta(idCarta);
		vertedero++;
			break;
		case 1 : comida += 2;
		jugadorActual.eliminarCarta(idCarta);
		vertedero++;
			break;
		case 2 : comida += 3;
		jugadorActual.eliminarCarta(idCarta);
		vertedero++;
			break;
		case 3 : {
			//MEDICINA
			Carta_Supervivientes aux = supervivientes.getSuperviviente(idSup);
			aux.curarHerida();
			jugadorActual.eliminarCarta(idCarta);
			vertedero++;
		}
			break;
			//TRASTOS
		case 4 : {
			int idDado = jugadorActual.valorDado(1);
			jugadorActual.getDados().resetUnDado(idDado);
			jugadorActual.eliminarCarta(idCarta);
			vertedero++;
			salida += idDado + "|" + jugadorActual.getDados().getValor(idDado);
		}
			break;
			//GASOLINA
		case 5 : {
			if(!jugadorActual.getGasolina()) {
				jugadorActual.setGasolina(true);
				jugadorActual.eliminarCarta(idCarta);
				vertedero++;
			}else {
				throw new GasolinaException("Ya has usado gasolina");
			}
		}
			break;
		case 19 : 
			if(!libroDados) {
				throw new EquipableException("Ya has usado el libro esta ronda");
			}
			int idDado = jugadorActual.valorDado(1);
			jugadorActual.getDados().resetUnDado(idDado);
			salida += idDado + "|" + jugadorActual.getDados().getValor(idDado);
			break;
			//SI ES CUAQUIERA DE LOS DEMÁS OBJETOS, LO EQUIPAMOS

		default : {
			supervivientes.getSuperviviente(idSup).equipar(idCarta);
			jugadorActual.eliminarCarta(idCarta);
		}
		}

		return salida;
	}
	
	//RECIBO LA ID DEL DADO I DEVUELVO LA ID DEL DADO Y EL NUEVO VALOR
	public String gastarComida(int dado) throws DadoException {
		String salida = "";
		if(comida == 0) {
			throw new DadoException("No hay comida en la colonia");
		}else if(jugadorActual.getDados().getValor(dado) == 6) {
			throw new DadoException("El dado ya es máximo");
		}else {
			salida += dado + "|" + jugadorActual.getDados().aumentarDado(dado);
		}
		return salida;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS PARA EL SERVIDOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	//Set crisis para tests
    public void setCrisis(Crisis crisis) {
        this.crisisActual = crisis;
    }
    
    public int getCrisisActualId() { //Devuelve el id de la crisis
        return crisisActual.getId();
    }

    public Crisis getCrisisActual() { //Devuelve la crisis actual
        return crisisActual;
    }
	
	public String getInutiles(){
		return Integer.toString(tablero.getColonia().getInutiles());
	}
	
	public String getMuertos() {
		return muertos;
	}
	
	public String getNombre(int id) {
		return supervivientes.getSuperviviente(id).getNombre();
	}
	
	public String getHeridas(int id) {
		Carta_Supervivientes sup = supervivientes.getSuperviviente(id);
		return Integer.toString(sup.getHeridas()) + "|" + Integer.toString(sup.getCongelamiento());
	}
	
	public void addSuperviviente(int idJug, int idSup) {
		enPartida.add(supervivientes.getPersonaje(idSup));
		jugadores.get(idJug).addSuperviviente(supervivientes.getPersonajes().remove(idSup));
	}
	
	//INICIA LOS SUPERVIVIENTES EN LA COLONIA
	public void inicSupervivientesEnColonia() {
		for(Jugador j : jugadores) {
			tablero.getColonia().anyadirSupervivientes(j.getMazoSuperviviente().get(0));
			tablero.getColonia().anyadirSupervivientes(j.getMazoSuperviviente().get(1));
		}
	}
	
	//INICIA LOS JUGADORES
	public void inicPartida(int numJugadores) {
		inicMazos();
		inicTablero(numJugadores);
		inicJugadores(numJugadores);
		crisis = new InicCrisis(numJugadores);
		
		crisisActual = crisis.getCrisis();

		jugadorActual = jugadores.get(0);
	}
	
	//RESETEA LAS HABILIDADES DEL ACTUAL Y PASA AL SIGUIENTE
	public void pasaTurno(int id) {
		//RESETEAMOS HABILIDAD Y MOVIMIENTO
		jugadorActual.resetHab();
		jugadorActual.setGasolina(false);
		//RESETEAMOS EL BUFFER DE CARTAS
		jugadorActual.resetBuffer();
		jugadorActual = jugadores.get(id);
		inicTurno();
		resetDados();
	}
	
	public String pasaRonda() {
		//ACTUALIZAMOS LOS ZOMBIES DE CADA LOCALIZACIÓN
		String datos = actualizarTablero();
		
		muertos = actualizarTodosSupervivientes();
		
		System.out.println(crisisActual.pasada() + " " + crisisActual.sobra());
		
		if(vertedero >= 10) {
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 1);
		}
		
		//LA COMIDA QUE SE GASTA ES IGUAL A LA MITAD DE LOS SUPERVIVIENTES DE LA COLONIA REDONDEANDO HACIA ARRIBA
		int comidaGastada = (int) Math.round(((double) tablero.getColonia().getSupervivientes().size() + (double) (23 - tablero.getColonia().getInutiles())) / 2);
		System.out.println("Comida: " + comida + "\n Gastada: " + comidaGastada);
		if(comida >= comidaGastada) {
			comida -= comidaGastada;
		}else {
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - (hambre + 1));
			hambre++;
		}
		crisisActual = crisis.getCrisis();
		
		rondasRestantes--;
		moral = tablero.getColonia().getMoral();
		System.out.println("Moral :" + moral);
		return datos;
	}
	
	public String resultadoCrisis() {
		StringBuilder sB = new StringBuilder();
		if(crisisActual.sobra()) {
			sB.append("sobra");
		}else if(crisisActual.pasada()) {
			sB.append("pasada");
		}else {
			sB.append("fallo");
		}
		System.out.println("Numero de aportaciones = " + crisisActual.getActuales() + "\nNumero de aportaciones necesarias = " + crisisActual.getNecesarias());
		return sB.toString();
	}
	
	public String cartasContrib() {
		int[] donaciones = crisisActual.getDonaciones();
		
		if(donaciones[0] == -1) {
			return null;
		} else {
			StringBuilder sB = new StringBuilder();
			int i = 0;
			while(donaciones[i] != -1) {
				sB.append(donaciones[i]+"|");
				i++;
			}
			
			sB.replace(sB.length()-1, sB.length(), "");
			
			return sB.toString();
		}
	}
	
	public String getMismaLoc(int idSUp) {
		Map<Integer, Carta_Supervivientes> aux = jugadorActual.localizacion(supervivientes.getSuperviviente(idSUp)).getSupervivientes();
		String salida = "";
		for(int i = 0; i < 20; i++) {
			if(aux.get(i) != null && (aux.get(i).getHeridas() + aux.get(i).getCongelamiento()) > 0) {
				salida += "|" + aux.get(i).getId();
			}
		}
		return salida;
	}
	
	//COMPROBAMOS EL OBJETIVO PRINCIPAL, LA MORAL Y EL TURNO
	
	//PUEDE QUE HAYA QUE ELIMINAR ESTA FUNCION
	public Jugador getJugador(int id) {
		return jugadores.get(id);
	}
	
	public List<Jugador> getJugadores(){
		return this.jugadores;
	}
	
	public Jugador getJugadorActual() {
		return jugadorActual;
	}
	
	public String inicTurno() {
		return actualizarSupervivientesActual();
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
	
	public int getVertedero() {
		return vertedero;
	}
	
	public int getHambre() {
		return hambre;
	}
	
	public int getComida() {
		return comida;
	}
	
	public String getVecinos() throws HeridaException {
		String msg = "";
		for(Carta_Supervivientes sup : buscarSuperviviente()) {
			msg += sup.getId() + "|";
		}
		
		if(msg.contentEquals("")) {
			throw new HeridaException("No hay ningún superviviente herido");
		}
		
		return msg;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS AUXILIARES
	///////////////////////////////////////////////////////////////////////////////////////////////
	private Jugador getJugConSup(int superviviente) {
		int i = 0; boolean encontrado = false; Jugador salida = null;
		Carta_Supervivientes personaje = supervivientes.getSuperviviente(superviviente);
		while(!encontrado && i < jugadores.size()) {
			if(jugadores.get(i).getMazoSuperviviente().contains(personaje)) {
				salida = jugadores.get(i);
			}
			i++;
		}
		return salida;
	}
	
	private List<Carta_Supervivientes> buscarSuperviviente(){
		List<Carta_Supervivientes> supDelJugador = jugadorActual.getMazoSuperviviente();
		List<Carta_Supervivientes> salida = new ArrayList<>();
		
		for(Jugador j : jugadores) {
			for(Carta_Supervivientes sup : j.getMazoSuperviviente()) {
				for(Carta_Supervivientes supJug : supDelJugador) {
					if(j.localizacion(sup).equals(jugadorActual.localizacion(supJug)) && sup.getHeridas() + sup.getCongelamiento() != 0) {
						salida.add(sup);
					}
				}
			}
		}
		
		return salida;
	}
	
	private String actualizarTablero() {
		String aux = "";
		if(!crisisActual.pasada()) {
			fallo();
		}
		
		
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
		if(com[0].contentEquals("0")) {
			muertos += Integer.parseInt(com[1]);
		}
		if(sup[0].contentEquals("0")) {
			muertos += Integer.parseInt(sup[1]);
		}
		if(col[0].contentEquals("0")) {
			muertos += Integer.parseInt(col[1]);
		}
		if(gas[0].contentEquals("0")) {
			muertos += Integer.parseInt(gas[1]);
		}
		if(hos[0].contentEquals("0")) {
			muertos += Integer.parseInt(hos[1]);
		}
		if(bib[0].contentEquals("0")) {
			muertos += Integer.parseInt(bib[1]);
		}
		if(coll[0].contentEquals("0")) {
			muertos += Integer.parseInt(coll[1]);
		}
		
		return aux;
	}
	
	private void resetDados() {
		jugadorActual.getDados().resetDados(jugadorActual.getMazoSuperviviente().size());
		List<Integer> d = jugadorActual.getDados().getDados();
		dados[jugadorActual.getId()] = "";
		
		for(int i = 0; i < d.size(); i++) {
			if(i != 0) {
				dados[jugadorActual.getId()] += "|";
			}
			dados[jugadorActual.getId()] += d.get(i);
		}
	}
	
	public String actualizarSupervivientesActual() {
		String muertos = "";
		List<Carta_Supervivientes> aux = jugadorActual.getMazoSuperviviente();
		
		//MATAMOS A LOS SUPERVIVIENTES CON TRES HERIDAS
		Carta_Supervivientes personaje;
		int i = 0;
		
		while(i < aux.size()) {
			personaje = aux.get(i);
			if(personaje.estaMuerto()) {
				jugadorActual.matar();
				muertos += personaje.getId() + "|";
			}else {
				i++;
			}
		}
		
		if(muertos == "") {
			muertos = null;
		}
		
		return muertos;
	}
	
	private String actualizarTodosSupervivientes() {
		String muertos = "";
		List<Carta_Supervivientes> aux;
		
		for(Jugador j : jugadores) {
			aux = j.getMazoSuperviviente();
			
			//ACTUALIZAMOS HERIDAS POR CONGELAMIENTO
			for(Carta_Supervivientes personaje : aux) {
				personaje.congelamiento();
			}
			
			//MATAMOS A LOS SUPERVIVIENTES CON TRES HERIDAS
			Carta_Supervivientes personaje;
			int i = 0;
			
			while(i < aux.size()) {
				personaje = aux.get(i);
				if(personaje.estaMuerto()) {
					if(muertos != "") {
						muertos += "|";
					}
					muertos += + j.getId() + "|" + personaje.getId();
				}
				i++;
			}
			j.matar();
		}
		
		return muertos;
	}
	
	private void fallo() {
		switch(crisisActual.getId()) {
		case 300 : {
			PriorityQueue<Carta_Supervivientes> aux = new PriorityQueue<Carta_Supervivientes>();
			aux.addAll(this.enPartida);
			//LOS 5 SUPERVIVIENTES CON MAS INFLUENCIA RECIBEN UNA HERIDA POR CONGELACION
			int i = 0;
			while (i < 5 && i < aux.size()) {
				aux.poll().recibirHerida(false);
				i++;
			}
			
			actualizarTodosSupervivientes();
		}
		break;
		case 301 : {
			
			//SE BAJA LA MORAL Y SE AÑADE TOKEN DE HAMBRE
			hambre++;
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 1);
		}
		break;
		case 302 : {
			
			//SE BAJA LA MORAL Y SE AÑADE UNA HERIDA A CADA SUPERVIVIENTE
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 1);
			for(Carta_Supervivientes sup : enPartida) {
				sup.recibirHerida(false);
			}
			
			actualizarTodosSupervivientes();
		}
		break;
		case 303 : {
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 2);
		}
		break;
		case 304 : {
			tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 2);
		}
		break;
		case 305 : {
			//SE AÑADEN 3 ZOMBIES EN BIBLIOTECA Y EN SUPERMERCADO
			tablero.getBiblioteca().addZombiesExternos(3);
			
			tablero.getSupermercado().addZombiesExternos(3);
		}
		break;
		case 306 : {
			
			//SE AÑADEN 6 SUPERVIVIENTES A LA COLONIA Y UNO EN CADA LOCALIZACION
			tablero.getBiblioteca().addZombiesExternos(1);
			tablero.getColegio().addZombiesExternos(1);
			tablero.getComisaria().addZombiesExternos(1);
			tablero.getGasolinera().addZombiesExternos(1);
			tablero.getHospital().addZombiesExternos(1);
			tablero.getSupermercado().addZombiesExternos(1);
			
			
			//TODO CAMBIAR EL AÑADIR ZOMBIES PARA QUE SEAN EN RELOJ
			tablero.getColonia().addZombiesExternos(6);
		}
		break;
		case 307 : {
			//TODO
		}
		break;
		}
		moral = tablero.getColonia().getMoral();
	}
	
	public PriorityQueue<Carta_Supervivientes> getSupervivientes(){
		return enPartida;
	}
	
	//ESTE METODO SE COMPRUEBA CADA VEZ QUE EL JUGADOR REALIZA UNA ACCIÓN PARA MONITOREAR EL PROGRESO DEL OBJETIVO
	//PUEDE QUE SE HAGA DESDE SERVIDOR Y NO SIRVA
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
