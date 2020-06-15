package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

public class Jugador {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATOS PARA LA INTERFAZ
	private int id;
	
	//DATOS DEL JUGADOR
	private List<Carta_Supervivientes> mazoSuperviviente;
	private List<Carta> mazoObjeto;
	private Dado dados;
	private DadoDeRiesgo riesgo;
	private Objetivo_Principal objetivo;
	
	//DATOS DE CONTROL
	private Tablero tablero;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Jugador(int id, List<Carta> mazoJugador, Tablero t, Objetivo_Principal o) {
		this.id = id;
		
		this.mazoSuperviviente = new ArrayList<Carta_Supervivientes>();
		this.mazoObjeto  = mazoJugador;
		this.dados = new Dado();
		
		this.riesgo = new DadoDeRiesgo();
		
		this.objetivo = o;
		
		tablero = t;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//GETTERS Y SETTERS
	public Dado getDados() {
		return this.dados;
	}
	
	public int getId () {
		return this.id;
	}
	
	public void setId (int i) {
		this.id = i;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	
	public List<Carta_Supervivientes> getMazoSuperviviente() {
		return this.mazoSuperviviente;
	}
	
	//METODOS DE RONDA
	public String tirarDados() {
		String salida = ""; int i = 0;
		dados.resetDados(this.mazoSuperviviente.size());
		
		for(int d : dados.getDados()) {
			if(i != 0) {
				salida += "|";
			}
			i++;
			salida += d;
		}
		
		return salida;
	}
//	
//	public void anyadirDados() {
//		while(dados.size() <= mazoSuperviviente.size()) {
//			this.dados.add(new Dado());
//		}
//	}
	
	public void matar() {
		for (int i = 0; i < this.mazoSuperviviente.size(); i++) {
			if (this.mazoSuperviviente.get(i).estaMuerto()) {
				this.mazoSuperviviente.remove(i);
			}
		}
	}
	
	//METODO PARA COMPROBAR SI HAY DADOS
	public int valorDado(int valor) {
		int menor = -1; int i = 0; int sal = -1;
		
		for(int d : dados.getDados()) {
			if(d >= valor) {
				if(menor == -1 || menor > d) {
					menor = d;
					sal = i;
				}
			}
			i++;
		}
		
		return sal;
	}
	
	//METODO PARA SABER EL INDICE DE UN SUPERVIVIENTE
	private int indice(Carta_Supervivientes carta) {
		int indice = -1;
		int i = 0;
		
		while(indice == -1 && i < mazoSuperviviente.size()) {
			if (carta.equals(mazoSuperviviente.get(i))) {
				indice = i;
			} else {
				i++;
			}
		}
		
		return indice;
	}
	
	//RESTABLECE LAS HABILIDADES YA USADAS
	public void resetHab() {
		for(Carta_Supervivientes sup : mazoSuperviviente) {
			sup.setUsado(false);
			sup.setMovido(false);
		}
	}
	
	//DEVUELVE LA CARTA DE SUPERVIVIENTE CORRESPONDIENTE A LA ID
	private Carta_Supervivientes getSupConId(int id) {
		Carta_Supervivientes salida = null;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < mazoSuperviviente.size()) {
			Carta_Supervivientes aux = mazoSuperviviente.get(i);
			if(aux.getId() == id) {
				salida = aux;
				encontrado = true;
			}else {
				i++;
			}
		}
		
		return salida;
	}
	
	//METODO PARA APLICAR UNA HERIDA
	public void herir(Carta_Supervivientes carta, boolean hielo) {
		mazoSuperviviente.get(indice(carta)).recibirHerida(hielo);
	}
	
	//DEVUELVE LA LOCALIZACION DEL PERSONAJE
	private Localizacion localizacion(Carta_Supervivientes personaje) {
		Localizacion l = null;
		
		if (tablero.getBiblioteca().esta(personaje)) {
			l = tablero.getBiblioteca();
			
		} else if(tablero.getColegio().esta(personaje)) {
			l = tablero.getColegio();
			
		} else if(tablero.getColonia().esta(personaje)) {
			l = tablero.getColonia();
			
		} else if(tablero.getComisaria().esta(personaje)) {
			l = tablero.getComisaria();
			
		} else if(tablero.getGasolinera().esta(personaje)) {
			l = tablero.getGasolinera();
			
		} else if(tablero.getHospital().esta(personaje)) {
			l = tablero.getHospital();
			
		} else {
			l = tablero.getSupermercado();
			
		}
		
		return l;
	}
	
	//AÑADE UN SUPERVIVIENTE AL JUGADOR

	public void addSuperviviente(Carta_Supervivientes personaje) {
		mazoSuperviviente.add(personaje);
	}
	
	//DEVUELVE EL SUPERVIVIENTE QUE EL JUGADOR TIENE EN LA LOCALIZACION
	private Carta_Supervivientes comprobarLocalizacion(Localizacion l) {
		boolean esta = false;
		Carta_Supervivientes personaje = null;
		int i = 0;
		
		while (!esta && i < mazoSuperviviente.size()) {
			if (l.esta(mazoSuperviviente.get(i))) {
				personaje = mazoSuperviviente.get(i);
				esta = true;
			} else {
				i++;
			}
		}
		
		return personaje;
	}
	
	//TRADUCCIÓN DE LA ID DE LOCALIZACION
	private Localizacion getLocalizacion(int id) {
		Localizacion sitio = null;
		
		switch(id) {
		case 0 : sitio = tablero.getComisaria();
			break;
		case 1 : sitio = tablero.getSupermercado();
			break;
		case 2 : sitio = tablero.getColegio();
			break;
		case 3 : sitio = tablero.getGasolinera();
			break;
		case 4 : sitio = tablero.getHospital();
			break;
		case 5 : sitio = tablero.getBiblioteca();
			break;
		default : sitio = tablero.getColonia();
		}
		
		return sitio;
	}
	 
	//REALIZA UNA TIRADA DEL DADO DE RIESGO Y REGULA LAS ACCIONES POSTERIORES A ESTA
	private int tiradaRiesgo(Carta_Supervivientes personaje) {
		int dado = riesgo.tirarDado();
		int salida = 0;
		
		if (dado > 5 && dado <= 8) {
			personaje.recibirHerida(false);
			salida = 1;
		} else if (dado > 8 && dado <= 10){
			personaje.recibirHerida(true);
			salida = 2;
		} else if (dado == 11) {
			localizacion(personaje).eliminarSuperviviente(personaje);
			mazoSuperviviente.remove(personaje);
			salida = 3;
		}
		
		return salida;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS DEL TURNO DEL JUGADOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//ESTE METODO ELIMINA UN ZOMBIE O SUPERVIVIENTE DE LA LOCALIZACION DONDE SE ENCUENTRA EL SUPERVIVIENTE
	public int atacar(int personaje) {
		Carta_Supervivientes superviviente = getSupConId(personaje);
		
		//USAMOS EL MENOR DADO POSIBLE
		dados.usar(superviviente.getAtaque());
		if(superviviente.tiraAlAtacar()) {
			tiradaRiesgo(superviviente);
		}
		
		if(objetivo.getId() == 0) {
			objetivo.actualizar(0);
		}
		
		return localizacion(superviviente).matarZombie();
	}
	
//	public int atacarPersona(int personaje) {
//		return valorDado(getSupConId(personaje).getAtaque()).usar();
//	}
	
	public String barricada(int id) throws BarricadaException {
		Carta_Supervivientes personaje = getSupConId(id);
		Localizacion loc = localizacion(personaje);
		int dado = valorDado(1);
		String res = loc.ponerBarricada();
		
		if(dado == -1) {
			res = null;
		}
		
		dados.usar(dado);
		
		String salida = "";
		if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
			salida += Integer.toString(loc.getId()) + "|";
		}
		 salida += res + "|" + Integer.toString(dado);
		
		return salida;
	}
	
	public String buscar(int id) {
		Carta_Supervivientes personaje = getSupConId(id);
		int dado = valorDado(personaje.getAtaque());
		String salida = "";
		Carta aux;
		
		//SI HAY DADO Y EL SUPERVIVIENTE NO ESTA EN LA COLONIA
		if(dado != -1 && !getLocalizacion(6).equals(localizacion(personaje))) {
			if(localizacion(personaje).getMazo().vacio()) {
				salida = null;
			}else {
				aux = localizacion(personaje).cogerCarta();
				salida += aux.getId();
				mazoObjeto.add((Carta) aux);
				
				//SI EL PERSONAJE BUSCA DOBLE EN LA LOCALIZACIÓN
				if(!personaje.getUsado() && (getLocalizacion(personaje.doble()).equals(localizacion(personaje)) || personaje.doble() == 7)) {	//VALE 7 SOLO PARA EL DIRECTOR
					aux = localizacion(personaje).cogerCarta();
					salida += "|" + aux.getId();
					mazoObjeto.add((Carta) aux);
					personaje.setUsado(true);
				}
			}
		}
		
		if(objetivo.getId() == 1 && localizacion(personaje).getMazo().vacio()) {
			objetivo.actualizar(localizacion(personaje).getId());
		}
		
		return salida;
	}
	
	public String mover(int id, int l) throws MoverException {
		Carta_Supervivientes personaje = getSupConId(id);
		Localizacion lugar = getLocalizacion(l);
		int dado = 0;
		int posicion = -1;
		
		if(!personaje.getMovido()) {
			//INTENTA MOVER SI HAY CASILLAS LIBRE Y SI EL PERSONAJE NO ESTA YA EN ESE LUGAR
			if(lugar.getSupervivientes().size() < lugar.getMaximo() && !lugar.getSupervivientes().containsValue(personaje)) {
				localizacion(personaje).irse(personaje);
				posicion = lugar.llegar(personaje);
				
				if(personaje.tiraAlMoverse()) {
					dado = tiradaRiesgo(personaje);
				}
				personaje.setMovido(true);
			}
		}else {
			throw new MoverException("Solo puedes moverte una vez por ronda");
		}
		
		if(posicion == -1) {
			throw new MoverException("Ya estás en esta posición");
		}
		
		return Integer.toString(posicion) + "|" + Integer.toString(dado);
	}
	
	public void votacion() {
		//QUEDA PARA LA SIGUIENTE ENTREGA
	}
	
	/*HAY QUE COMPROBAR MAS ADELANTE QUE SE INCREMENTE LA COMIDA POR EL USO DE UNA CARTA O PORQUE SE AÑADA POR 
	 * UN METODO EXTERNO COMO UNA CARTA DE ENCRUCIJADA. EL METODO AÑADE POR DEFECTO UNO DE COMIDA. HABRA QUE CAMBIAR
	 * SI DECIDIMOS IMPLEMENTAR CARTAS CON VALORES DISTINTOS
	 */
	//TODO
//	public void anyadirComida(Carta_Objeto objeto) {
//		if(objeto != null) {
//			mazoObjeto.remove(objeto);
//		}
//		
//		tablero.getColonia().setTokensDeHambre(tablero.getColonia().getTokensDeHambre() + 1);
//	}
	
	public boolean anyadirCrisis(int id) {
		boolean tiene = false;
		Carta carta = new Carta_Objeto(id, 0, 0);
		
		if(mazoObjeto.contains(carta)) {
			mazoObjeto.remove(carta);
		}
		
		return tiene;
	}
	
	public void darCarta() {
		//POR DETERMINAR
	}
	
	public void pedirCarta() {
		//POR DETERMINAR
	}
	
	//POR IMPLEMENTAR. HABLAR SOBRE ESTO MAÑANA
	public void usarObjeto(Carta_Objeto carta) {
		
	
		
		mazoObjeto.remove(carta);
	}
	
	public void equiparObjeto(Carta_Objeto carta, Carta_Supervivientes personaje) {
		personaje.equipar(carta);
		mazoObjeto.remove(carta);
	}
}
