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
	
	//CONTADORES DE INTERFAZ
	private int tokensDeHambre;
	private int vertedero;
	
	//OBJETIVOS
	private Objetivo_Principal ObjetivoPrincipal;			//es posible que esto sea eliminado
	private List<Carta_Objeto> crisis;
	private Carta_Crisis ronda;		
	
	//DATOS PARA MOVIMIENTO
	private List<List<CasillasZombie>> puertas;
	
	//DATOS DE CONTROL
	private int inutiles;
	private int numJugadores;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Colonia(Objetivo_Principal c, int jugadores) {
		super("Colonia.", null, 24, 3);	
		
		this.tokensDeHambre = 0;		
		this.vertedero = 0;
		
		this.ObjetivoPrincipal = c;
		this.crisis = new ArrayList<>();
		
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
	}
	
	public int anyadirSupervivientes(int i) {
		int aux = super.getPimeraValida();
		//super.getSupervivientes().put(aux, new Carta_Supervivientes(i));
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
	public String [] actualizarCasillasZombiePasoDeRonda() {
		int numZombies = (int) Math.round((double) (this.getSupervivientes().size() + inutiles) / 2);
		int noColocados = 0;
		int [] colocados = new int[6];
		
		for(int j = 0; j < numZombies; j++) {
			int puertaActual = j % 6;
			List<CasillasZombie> puerta = puertas.get(puertaActual);
			boolean colocado = false;
			int i = 0;
			
			//INTENTA COLOCAR EN CASILLAS VAC�AS
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
			
			//INTENTA COLOCAR EN LAS CASILLAS VAC�AS QUE QUEDEN DE QUITAR BARRICADAS
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
		for(int i = 0; i < colocados.length; i++) {
			if(i != 0) {
				aux[0] += "|";
			}
			aux[0] += colocados[i]; 
		}
		
		aux [1] = Integer.toString(muertos);
		
		return aux;
	}
	
	public int pasarCrisis() {
		int contador = 0;
		Carta_Objeto objeto = ronda.getObjeto();
		
		for (Carta_Objeto carta : crisis) {
			if (objeto.getTipo() == (carta.getTipo())) {
				contador++;
			} else {
				contador--;
			}
		}
		return contador/numJugadores;
	}
	
	public void anyadirCrisis(Carta_Objeto carta) {
		crisis.add(carta);
	}
	
	//GETTERS Y SETTERS
	public void setCrisis(Carta_Crisis crisis) {
		this.ronda = crisis;
	}

	public int getTokensDeHambre() {
		return tokensDeHambre;
	}

	public void setTokensDeHambre(int tokensDeHambre) {
		this.tokensDeHambre = tokensDeHambre;
	}

	public int getVertedero() {
		return vertedero;
	}

	public void setVertedero(int vertedero) {
		this.vertedero = vertedero;
	}
	
	public List<List<CasillasZombie>> getPuertas() {
		return this.puertas;
	}
}
