//PARA NADA VERSIÓN DEFINITIVA
package dow;

import java.util.*;

public class Jugador {
	//Atributos
	private String nombre;	//para identificar al jugador
	private int id;		//lo usa partida
	private List<Carta_Supervivientes> mazoSuperviviente;
	private List<Carta_Objeto> mazoObjeto;
	private boolean primerJugador;
	private List<Dado> dados;
	private DadoDeRiesgo riesgo;
	
	//Constructor/es
	public Jugador(List<Carta_Objeto> mazo, int id, boolean primero) {
		this.mazoSuperviviente = new LinkedList<Carta_Supervivientes>();
		this.mazoObjeto  = mazo;
		riesgo = new DadoDeRiesgo();
		this.id = id;
		primerJugador = primero;
	}
	
	//Métodos
	public String getNombre() {
		return this.nombre;
	}
	
	//puede no ser necesario
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public int getId () {
		return this.id;
	}
	
	//puede no ser necesario
	public void setId (int i) {
		this.id = i;
	}
	
	public boolean hayDado(int valor) {
		int id = -1, dado = 1, i = 0;
		
		for(Dado d : dados) {
			if(d.valor() >= valor && dado <= d.valor()) {
				dado = d.valor();
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
			if(carta.equals(mazoSuperviviente.get(i))){
				indice = i;
			}else {
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
		
		for(Carta_Supervivientes c : mazoSuperviviente) {
			if(c.getLugar().equals(l)) {
				esta = true;
			}
		}
		
		return esta;
	}
	 
	//ACCIONES DEL JUGADOR
	
	public void atacar(Carta_Supervivientes personaje, boolean persona, Carta_Supervivientes objetivo, Jugador enemigo) throws matarException, dadoException {
		if(!persona) {	//ATACAR ZOMBIES
			if(hayDado(personaje.getAtaque())) {
				personaje.getLugar().matar();
				int dado = riesgo.tirarDado();
				
				if(dado > 5 && dado <= 8) {
					personaje.recibirHerida(false);
				}else if(dado > 8 && dado <= 10){
					personaje.recibirHerida(true);
				}else if(dado == 11) {
					mazoSuperviviente.remove(personaje);
				}
			}else {
				throw new dadoException();
			}
		}else {
			
			if(hayDado(personaje.getAtaque())) {
				Dado tirada = new Dado();
				tirada.tirarDado();
				
				if(tirada.valor() >= objetivo.getAtaque()) {
					enemigo.herir(objetivo);
				}
			}
		}
	}
	
	public void barricada(Localizacion l) throws localizacionException {
		if(comprobarLocalizacion(l)) {
			l.barricada();
		}else {
			throw new localizacionException("No estas en la localizacion");
		}
	}
	
	public void buscar(Carta_Supervivientes personaje) {
		if(hayDado(personaje.getBusqueda())) {
			mazoObjeto.add(personaje.getLugar())
		}
	}
	
	public void mover() {
		
	}
	
	public void votacion() {
		
	}
	
	public void añadirComida() {
		
	}
	
	public void añadirCrisis() {
		
	}
	
	public void darCarta() {
		
	}
	
	public void pedirCarta() {
		
	}
	
	public void usarObjeto() {
		
	}
	
	public void equiparObjeto() {
		
	}
}
