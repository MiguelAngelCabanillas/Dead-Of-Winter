package dow_v2.Partida;

import java.util.ArrayList;
import java.util.List;

import dow_v2.Cartas.Carta_Crisis;
import dow_v2.Cartas.Carta_Objetivo_Principal;
import dow_v2.Cartas.Carta_Objeto;
import dow_v2.Cartas.Carta_Supervivientes;
import dow_v2.Excepciones.barricadaException;
import dow_v2.Excepciones.matarException;

public class Colonia extends Localizacion {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//CONTADORES DE INTERFAZ
	private int moral;
	private int rondasRestantes;
	private int tokensDeHambre;
	private int vertedero;
	
	//OBJETIVOS
	private Carta_Objetivo_Principal cartaObjetivoPrincipal;
	private List<Carta_Objeto> crisis;
	private Carta_Crisis ronda;		//se inicializa al comienzo de la ronda
	
	//DATOS PARA MOVIMIENTO
	private List<List<CasillasZombie>> puertas;
	
	//DATOS DE CONTROL
	private int inutiles;
	private int numJugadores;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Colonia(Carta_Objetivo_Principal c, int rondas, int moral, int jugadores) {
		super("Colonia.", null, 24, 3);	
		
		this.moral = moral;
		this.rondasRestantes = rondas;
		this.tokensDeHambre = 0;		
		this.vertedero = 0;
		
		this.cartaObjetivoPrincipal = c;
		this.crisis = new ArrayList<>();
		
		inicCasillasZombie();
		
		this.inutiles = 0;
		this.numJugadores = jugadores;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODO DEL CONSTRUCTOR
	public void inicCasillasZombie() {
		this.puertas = new ArrayList<List<CasillasZombie>>();
		
		for(int i = 0; i < 6; i++) {
			List<CasillasZombie> aux = new ArrayList<CasillasZombie>();
			
			for(int j = 0; j < 3; j++)  {
				aux.add(new CasillasZombie());
			}
		}
	}
	
	//METODOS DE INTERFAZ ENTRE EL USUARIO Y CASILLASZOMBIE
	public void matar(int puerta) throws matarException {
		int i = 0;
		boolean muerto = false;
		while (i < 3 && !muerto) {
			if (this.puertas.get(puerta).get(i).getHayZombie()) {
				this.puertas.get(puerta).get(i).setHayZombie(false);
				muerto = true;
			}
			i++;
		}
	}
	
	public void barricada(int puerta) throws barricadaException {
		int i = 0;
		boolean puesta = false;
		while (i < 3 && !puesta) {
			if (!this.puertas.get(puerta).get(i).getHayBarricada() &&
				!this.puertas.get(puerta).get(i).getHayZombie() && !puesta) {
				this.puertas.get(puerta).get(i).setHayBarricada(true);
				puesta = true;
			}
			i++;
		}
	}
	
	//METODOS DE RONDA
	
	public void pasarRonda(Carta_Crisis crisis) {
		//A IMPLEMENTAR CUANDO ESTE LA CLASE PARTIDA
	}
	
	@Override
	public void actualizarCasillasZombiePasoDeRonda() {
		int numZombies = (this.getSupervivientes().size() + inutiles) / 2;
		int noColocados = 0;
		
		for(int j = 0; j < numZombies; j++) {
			List<CasillasZombie> puerta = puertas.get(j%6);
			boolean colocado = false;
			int i = 0;
			
			while(!colocado && i < puerta.size()) {
				CasillasZombie aux = puerta.get(i);
				if(!aux.getHayZombie()) {
					aux.setHayZombie(true);
					colocado = true;
				}else {
					i++;
				}
			}
			
			if(!colocado) {
				noColocados++;
			}
		}
		
		//MATAMOS A LOS JUGADORES NECESARIOS
		while(noColocados > 0) {
			Carta_Supervivientes muerto = super.getSupervivientes().poll();;
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			noColocados--;
		}
	}
	
	public int pasarCrisis() {
		int contador = 0;
		Carta_Objeto objeto = ronda.getObjeto();
		
		for (Carta_Objeto carta : crisis) {
			if (objeto.getTipo().equals(carta.getTipo())) {
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
	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}

	public Carta_Objetivo_Principal getCartaObjetivoPrincipal() {
		return cartaObjetivoPrincipal;
	}

	public void setCartaObjetivoPrincipal(Carta_Objetivo_Principal cartaObjetivoPrincipal) {
		this.cartaObjetivoPrincipal = cartaObjetivoPrincipal;
	}

	public int getRondasRestantes() {
		return rondasRestantes;
	}

	public void setRondasRestantes(int rondasRestantes) {
		this.rondasRestantes = rondasRestantes;
	}
	
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
}
