package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta_Crisis;
import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;

public class Colonia extends Localizacion {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	

	//OBJETIVOS
	private Objetivo_Principal ObjetivoPrincipal;			//es posible que esto sea eliminado	
	
	//DATOS PARA MOVIMIENTO
	private List<List<CasillasZombie>> puertas;
	
	//DATOS DE CONTROL
	private int inutiles;
	private int numJugadores;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Colonia(Objetivo_Principal c, int jugadores) {
		super(null, 24, 3, 6, 0);	
		
		this.ObjetivoPrincipal = c;
		
		inicCasillasZombie();
		
		this.inutiles = 23;
		this.numJugadores = jugadores;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODO DEL CONSTRUCTOR
	private void inicCasillasZombie() {
		this.puertas = new ArrayList<List<CasillasZombie>>();
		
		for (int i = 0; i < 6; i++) {
			List<CasillasZombie> aux = new ArrayList<CasillasZombie>();
			
			for (int j = 0; j < 3; j++) {
				aux.add(new CasillasZombie());
			}
			
			this.puertas.add(aux);
		}
		
		if(ObjetivoPrincipal.getId() == 1) {
			this.puertas.get(0).get(0).setHayZombie(true);
			this.puertas.get(1).get(0).setHayZombie(true);
		}
	}
	
	public int anyadirSupervivientes(Carta_Supervivientes personaje) {
		int aux = super.getPimeraValida();
		super.getSupervivientes().put(aux, personaje);
		return aux;
	}
	
	public int anyadirInutiles() {
		super.getSupervivientes().put(inutiles, null);
		inutiles--;
		
		return inutiles + 1;
	}
	
	//METODOS DE INTERFAZ ENTRE EL USUARIO Y CASILLASZOMBIE
	public void matar(int puerta) {
		int i = 0;
		boolean muerto = false;
		while (i < 3 && !muerto) {
			if (this.puertas.get(puerta).get(i).getHayZombie()) {
				this.puertas.get(puerta).get(i).setHayZombie(false);
				muerto = true;
			}
			++i;
		}
	}
	
	public void barricada(int puerta) {
		int i = 0;
		boolean puesta = false;
		while (i < 3 && !puesta) {
			if (!this.puertas.get(puerta).get(i).getHayBarricada() &&
				!this.puertas.get(puerta).get(i).getHayZombie() && !puesta) {
				this.puertas.get(puerta).get(i).setHayBarricada(true);
				puesta = true;
			}
			++i;
		}
	}
	
	//METODOS DE RONDA
	
	public void pasarRonda(Carta_Crisis crisis) {
		//A IMPLEMENTAR CUANDO ESTE LA CLASE PARTIDA
	}
	
	@Override
	public String anyadirZombie() {
		boolean colocado = false;
		int[] posiciones = new int[6];
		int i = 0;
		int j;
		List<CasillasZombie> aux;
		
		while(!colocado && i < 6) {
			aux = puertas.get(i);
			CasillasZombie aux2;
			j = 0;
			while(!colocado && j < 3) {
				aux2 = aux.get(j);
				if(!aux2.getHayBarricada() && !aux2.getHayZombie()){
					aux2.setHayZombie(true);
					colocado = true;
					posiciones[i]++;
				}
			}
		}
		
		//SI NO CABEN, SE MATA A UN SUPERVIVIENTE
		if(!colocado) {
			eliminarSuperviviente(super.menorInfluencia());
		}
		
		return Integer.toString(posiciones[0]) + Integer.toString(posiciones[1]) + Integer.toString(posiciones[2]) + Integer.toString(posiciones[3]) + Integer.toString(posiciones[4])+ 
				Integer.toString(posiciones[5]);
	}
	
	@Override
	public String [] actualizarCasillasZombiePasoDeRonda() {
		int numZombies = (int) Math.round((double) (this.getSupervivientes().size() + (23 - inutiles)) / 2);
		int noColocados = 0;
		int [] colocados = new int[6];
		boolean colocado;
		List<CasillasZombie> puerta;
		int puertaActual;
		
		for(int j = 0; j < numZombies; j++) {
			puertaActual = j % 6;
			puerta = puertas.get(puertaActual);
			colocado = false;
			int i = 0;
			
			//INTENTA COLOCAR EN CASILLAS VACÍAS
			while(!colocado && i < puerta.size()) {
				CasillasZombie aux = puerta.get(i);
				if (!aux.getHayZombie()) {
					aux.setHayZombie(true);
					colocado = true;
					colocados[puertaActual]++;
				} else {
					i++;
				}
			}
			
			//INTENTA COLOCAR EN BARRICADAS
			i = 0;
			while (!colocado && i < puerta.size()) {
				CasillasZombie aux = puerta.get(i);
				if (aux.getHayBarricada()) {
					aux.setHayBarricada(false);
				} else {
					i++;
				}
			}
			
			//INTENTA COLOCAR EN LAS CASILLAS VACÍAS QUE QUEDEN DE QUITAR BARRICADAS
			i = 0;
			while (!colocado && i < puerta.size()) {
				CasillasZombie aux = puerta.get(i);
				if (!aux.getHayZombie()) {
					aux.setHayZombie(true);
					colocado = true;
					colocados[puertaActual]++;
				} else {
					i++;
				}
			}
			
			if (!colocado) {
				noColocados++;
			}
		}
		
		int muertos = noColocados;
		
		//MATAMOS A LOS JUGADORES NECESARIOS
		while(noColocados > 0) {
			Carta_Supervivientes muerto = super.getSupervivientes().get(getPosicion(menorInfluencia()));
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			noColocados--;
		}
		
		//DEVOLVEMOS EL NUMERO DE ZOMBIES COLOCADOS
		String [] aux = new String[2];
		aux[0] = "";
		aux[1] = "";
		
		for(int i = 0; i < colocados.length; i++) {
			if(i != 0) {
				aux[0] += "|";
			}
			aux[0] += colocados[i]; 
		}
		
		aux [1] = Integer.toString(muertos);
		
		return aux;
	}
	
	//GETTERS Y SETTERS
	
	public List<List<CasillasZombie>> getPuertas() {
		return this.puertas;
	}
}
