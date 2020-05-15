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
	private List<List<CasillasZombie>> conjuntoCasillasZombie;
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
		this.conjuntoCasillasZombie = new LinkedList<List<CasillasZombie>>();
		int i = 0;
		while (i < 6) {
			this.conjuntoCasillasZombie.add(super.getCasillasZombie());
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
			if (this.conjuntoCasillasZombie.get(puerta).get(i).getHayZombie()) {
				this.conjuntoCasillasZombie.get(puerta).get(i).setHayZombie(false);
				muerto = true;
			}
			i++;
		}
	}
	
	public void barricada(int puerta) throws barricadaException {
		int i = 0;
		boolean puesta = false;
		while (i < 3 && !puesta) {
			if (!this.conjuntoCasillasZombie.get(puerta).get(i).getHayBarricada() &&
				!this.conjuntoCasillasZombie.get(puerta).get(i).getHayZombie() && !puesta) {
				this.conjuntoCasillasZombie.get(puerta).get(i).setHayBarricada(true);
				puesta = true;
			}
			i++;
		}
	}
	
	//TERMINAR.
	@Override
	public void actualizarCasillasZombiePasoDeRonda() {
	}
	
	public void matarSuperviviente() {
		//Implementar método menorInfluencia.
		Carta_Supervivientes muerto = super.menorInfluencia(1);
		
		while (!muerto.estaMuerto()) {
			muerto.recibirHerida(false);
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
