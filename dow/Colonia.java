package dow;

import java.util.LinkedList;
import java.util.List;

public class Colonia extends Localizacion {
	
	//Atributos.
	private int moral;
	private Carta_Objetivo_Principal cartaObjetivoPrincipal;
	private Carta_Crisis ronda;
	private int rondasRestantes;
	private int tokensDeHambre;
	private int vertedero;
	private List<List<CasillasZombie>> puertas;
	private int inutiles;
	private List<Carta_Objeto> crisis;
	private int numJugadores;
	
	//Constructor/es.
	public Colonia(Carta_Objetivo_Principal c, int rondas, int moral, int jugadores) {
		super("Colonia.", null, 24, 3);		
		this.moral = moral;
		this.cartaObjetivoPrincipal = c;
		this.rondasRestantes = rondas;
		this.tokensDeHambre = 0;		
		this.vertedero = 0;
		//conjuntoCasillasZombie es una lista tamaño 6 cuyos elementos son listas de 3 casillas zombie.
		this.puertas = new LinkedList<List<CasillasZombie>>();
		int i = 0;
		while (i < 6) {
			this.puertas.add(super.getCasillasZombie());
			i++;
		}
		this.inutiles = 0;
		this.numJugadores = jugadores;
	}
	
	//Método/s.
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
