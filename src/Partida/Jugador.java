package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;
import Excepciones.dadoException;
import Excepciones.localizacionException;
import Excepciones.matarException;

public class Jugador {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATOS PARA LA INTERFAZ
	private int id;
	
	//DATOS DEL JUGADOR
	private List<Carta_Supervivientes> mazoSuperviviente;
	private List<Carta_Objeto> mazoObjeto;
	private List<Dado> dados;
	private DadoDeRiesgo riesgo;
	
	//DATOS DE CONTROL
	private static Tablero tablero;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Jugador(int id, List<Carta_Objeto> mazo, Tablero t) {
		this.id = id;
		
		this.mazoSuperviviente = new ArrayList<Carta_Supervivientes>();
		this.mazoObjeto  = mazo;
		this.dados = new ArrayList<Dado>();
		this.riesgo = new DadoDeRiesgo();
		
		tablero = t;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//GETTERS Y SETTERS
	public List<Dado> getDados() {
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
	public void anyadirDados() {
		for (int i = 0; i < this.mazoSuperviviente.size(); ++i) {
			this.dados.add(new Dado());
		}
	}
	
	public void matar() {
		for (int i = 0; i < this.mazoSuperviviente.size(); i++) {
			if (this.mazoSuperviviente.get(i).estaMuerto()) {
				this.mazoSuperviviente.remove(i);
			}
		}
	}
	
	//METODO PARA COMPROBAR SI HAY DADOS
	public boolean hayValorDisponible(int valor) {
		int id = -1, dado = 1, i = 0;
		
		for (Dado d : dados) {
			if (d.getHaSidoTirado() && d.getValor() >= valor && dado <= d.getValor()) {
				dado = d.getValor();
				id = i;
			}
			i++;
		}
		
		return id != -1;
	}
	
	//METODO PARA SABER EL INDICE DE UN SUPERVIVIENTE
	public int indice(Carta_Supervivientes carta) {
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
	
	//METODO PARA APLICAR UNA HERIDA
	public void herir(Carta_Supervivientes carta) {
		mazoSuperviviente.get(indice(carta)).recibirHerida(false);
	}
	
	//DEVUELVE LA LOCALIZACION DEL PERSONAJE
	public Localizacion localizacion(Carta_Supervivientes personaje) {
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
	public void addSuperviviente(int id) {
	
	}
	
	//DEVUELVE EL SUPERVIVIENTE QUE EL JUGADOR TIENE EN LA LOCALIZACION
	public Carta_Supervivientes comprobarLocalizacion(Localizacion l) {
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
	
	public Localizacion getLocalizacion(int id) {
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
		}
		
		return sitio;
	}
	 
	//REALIZA UNA TIRADA DEL DADO DE RIESGO Y REGULA LAS ACCIONES POSTERIORES A ESTA
	public void tiradaRiesgo(Carta_Supervivientes personaje) {
		int dado = riesgo.tirarDado();
		
		if (dado > 5 && dado <= 8) {
			personaje.recibirHerida(false);
		} else if (dado > 8 && dado <= 10){
			personaje.recibirHerida(true);
		} else if (dado == 11) {
			mazoSuperviviente.remove(personaje);
		}
	}
	
	//METODOS DEL TURNO DEL JUGADOR
	
	//ESTE METODO ELIMINA UN ZOMBIE O SUPERVIVIENTE DE LA LOCALIZACION DONDE SE ENCUENTRA EL SUPERVIVIENTE
	public void atacar(Carta_Supervivientes personaje, Carta_Supervivientes objetivo, Jugador enemigo) throws matarException, dadoException {
		if(objetivo == null) {	//ATACAR ZOMBIES
			if(hayValorDisponible(personaje.getAtaque())) {
				this.localizacion(personaje).matarZombie();
				this.tiradaRiesgo(personaje);
			} else {
				throw new dadoException();
			}
		} else {
			
			if (hayValorDisponible(personaje.getAtaque())) {
				Dado tirada = new Dado();
				tirada.tirarDado();
				
				if (tirada.getValor() >= objetivo.getAtaque()) {
					enemigo.herir(objetivo);
				}
			}
		}
	}
	
	public void barricada(Localizacion l) throws localizacionException {
		if(comprobarLocalizacion(l) != null) {
			l.ponerBarricada();
		}else {
			throw new localizacionException("No estas en la localizacion");
		}
	}
	
	public void buscar(Localizacion l) {
		Carta_Supervivientes personaje = comprobarLocalizacion(l);
		if(hayValorDisponible(personaje.getBusqueda()) && personaje != null) {
			mazoObjeto.add((Carta_Objeto) l.cogerCarta());
		}
	}
	
	public int mover(Carta_Supervivientes personaje, int l) {
		Localizacion lugar = getLocalizacion(l);
		int posicion = lugar.getPimeraValida();
		
		//INTENTA MOVER SI HAY CASILLAS LIBRE Y SI EL PERSONAJE NO ESTA YA EN ESE LUGAR
		if(posicion != -1 && lugar.llegar(personaje) && !lugar.getSupervivientes().containsValue(personaje)) {
			localizacion(personaje).irse(personaje);;
			lugar.llegar(personaje);
			this.tiradaRiesgo(personaje);
		}
		
		return posicion;
	}
	
	public void votacion() {
		//QUEDA PARA LA SIGUIENTE ENTREGA
	}
	
	/*HAY QUE COMPROBAR MAS ADELANTE QUE SE INCREMENTE LA COMIDA POR EL USO DE UNA CARTA O PORQUE SE AÑADA POR 
	 * UN METODO EXTERNO COMO UNA CARTA DE ENCRUCIJADA. EL METODO AÑADE POR DEFECTO UNO DE COMIDA. HABRA QUE CAMBIAR
	 * SI DECIDIMOS IMPLEMENTAR CARTAS CON VALORES DISTINTOS
	 */
	public void anyadirComida(Carta_Objeto objeto) {
		if(objeto != null) {
			mazoObjeto.remove(objeto);
		}
		
		tablero.getColonia().setTokensDeHambre(tablero.getColonia().getTokensDeHambre() + 1);
	}
	
	public void anyadirCrisis(Carta_Objeto carta) {
		tablero.getColonia().anyadirCrisis(carta);
		mazoObjeto.remove(carta);
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
