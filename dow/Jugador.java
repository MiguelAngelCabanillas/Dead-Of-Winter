package dow;

import java.util.*;

public class Jugador {
	
	//Atributos.
	private String nombre;
	private int id;		
	private List<Carta_Supervivientes> mazoSuperviviente;
	private List<Carta_Objeto> mazoObjeto;
	private boolean primerJugador;
	private List<Dado> dados;
	private DadoDeRiesgo riesgo;
	private Colonia colonia;
	
	//Constructor/es.
	public Jugador(List<Carta_Objeto> mazo, int id, boolean primero, Colonia col) {
		this.mazoSuperviviente = new LinkedList<Carta_Supervivientes>();
		this.mazoObjeto  = mazo;
		//riesgo = new DadoDeRiesgo();
		this.id = id;
		primerJugador = primero;
		colonia = col;
	}
	
	//Métodos.
	public void matar() {
		for(Carta_Supervivientes personaje : mazoSuperviviente) {
			if(personaje.estaMuerto()) {
				mazoSuperviviente.remove(personaje);
			}
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public int getId () {
		return this.id;
	}
	
	public void setId (int i) {
		this.id = i;
	}
	
	public boolean hayDado(int valor) {
		int id = -1, dado = 1, i = 0;
		
		for (Dado d : dados) {
			if (d.getValor() >= valor && dado <= d.getValor()) {
				dado = d.getValor();
				id = i;
			}
			i++;
		}
		
		return id != -1;
	}
	
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
	
	public void herir(Carta_Supervivientes carta) {
		mazoSuperviviente.get(indice(carta)).recibirHerida(false);
	}
	
	public boolean comprobarLocalizacion(Localizacion l) {
		boolean esta = false;
		
		for (Carta_Supervivientes c : mazoSuperviviente) {
			if (c.getLugar().equals(l)) {
				esta = true;
			}
		}
		
		return esta;
	}
	 
	public void atacar(Carta_Supervivientes personaje, boolean persona, Carta_Supervivientes objetivo, Jugador enemigo) throws matarException, dadoException {
		if(!persona) {	//ATACAR ZOMBIES
			if(hayDado(personaje.getAtaque())) {
				personaje.getLugar().matarZombie();
				int dado = riesgo.tirarDado();
				
				if(dado > 5 && dado <= 8) {
					personaje.recibirHerida(false);
				} else if(dado > 8 && dado <= 10){
					personaje.recibirHerida(true);
				} else if(dado == 11) {
					mazoSuperviviente.remove(personaje);
				}
			} else {
				throw new dadoException();
			}
		} else {
			
			if (hayDado(personaje.getAtaque())) {
				Dado tirada = new Dado();
				tirada.tirarDado();
				
				if (tirada.getValor() >= objetivo.getAtaque()) {
					enemigo.herir(objetivo);
				}
			}
		}
	}
	
	public void barricada(Localizacion l) throws localizacionException, barricadaException {
		if(comprobarLocalizacion(l)) {
			l.ponerBarricada();
		}else {
			throw new localizacionException("No estas en la localizacion");
		}
	}
	
	public void buscar(Carta_Supervivientes personaje) {
		if(hayDado(personaje.getBusqueda())) {
			mazoObjeto.add(personaje.getLugar().cogerCarta());
		}
	}
	
	public void mover(Carta_Supervivientes personaje, Localizacion lugar) {
		if(lugar.llegar(personaje)) {
			personaje.getLugar().irse(personaje);
			personaje.setLugar(lugar);
		}
	}
	
	public void votacion() {
		//Votación para exiliar.
	}
	
	/*HAY QUE COMPROBAR MAS ADELANTE QUE SE INCREMENTE LA COMIDA POR EL USO DE UNA CARTA O PORQUE SE AÑADA POR 
	 * UN METODO EXTERNO COMO UNA CARTA DE ENCRUCIJADA
	 */
	public void anyadirComida(Carta_Objeto objeto, int cantidad) {
		if(objeto != null) {
			mazoObjeto.remove(objeto);
		}
		
		colonia.setTokensDeHambre(colonia.getTokensDeHambre() + cantidad);
	}
	
	public void anyadirCrisis(Carta_Objeto carta) {
		colonia.anyadirCrisis(carta);
		mazoObjeto.remove(carta);
	}
	
	public void darCarta() {
		//POR DETERMINAR
	}
	
	public void pedirCarta() {
		//POR DETERMINAR
	}
	
	public void usarObjeto(Carta_Objeto carta) {
		//
		mazoObjeto.remove(carta);
	}
	
	public void equiparObjeto(Carta_Objeto carta, Carta_Supervivientes personaje) {
		personaje.equipar(carta);
	}
}
