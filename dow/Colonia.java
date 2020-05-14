//PARA NADA VERSIÓN DEFINITIVA
package dow;

import java.util.List;

public class Colonia extends Localizacion {
	//Atributos
	private int moral;
	private Carta_Objetivo_Principal cartaObjetivoPrincipal;
	private Carta_Crisis ronda;
	private int rondasRestantes;
	private int tokensDeHambre;
	private int vertedero;		//el vertedero solo es un contador
	
	private List <Carta_Supervivientes> supervivientes;
	private int maximo;
	private CasillasZombie[] zombies;
	private int inutiles;
	private List<Carta_Objeto> crisis;
	private int jugadores;
	
	//Constructor/es
	public Colonia(Carta_Objetivo_Principal c, int rondas, int moral, int max, int jugadores) {
		super("Colonia", null, 0, 0, 24);		
		
		this.moral = moral;
		this.cartaObjetivoPrincipal = c;
		this.rondasRestantes = rondas;
		this.tokensDeHambre = 0;		//se empieza por defecto sin comida
		this.vertedero = 0;
		this.zombies = new CasillasZombie[6];
		this.maximo = 3;
		this.inutiles = 0;
		this.jugadores = jugadores;
	}

	//METODOS REESCRITOS
	
	public void matar(int puerta) throws matarException {
		zombies[puerta].matarZombie();
	}
	
	public void barricada(int puerta) throws barricadaException {
		zombies[puerta].añadirBarricada();
	}
	
	@Override
	public void pasaRonda() {
		int colocar = (inutiles + supervivientes.size()) / 2;
		int noColocados = 0;
		
		for(int i = 0; i < colocar; i++) {
			if(!zombies[i%6].añadirZombies(1)) {
				noColocados++;
			}
		}
		
		//SE MATA A UN JUGADOR POR CADA ZOMBIE QUE NO SE HA COLOCADO
		for(int i = 0; i < noColocados; i++) {
			matarSuperviviente();
		}
		
		
	}
	
	//METODOS
	public Carta_Supervivientes menorInfluencia() {
		int indice = 0;
		
		for(int i = 0; i < supervivientes.size(); i++) {
			if(supervivientes.get(indice).getInfluencia() > supervivientes.get(i).getInfluencia()) {
				indice = i;
			}
		}
		
		return supervivientes.get(indice);
	}
	
	public void matarSuperviviente() {
		Carta_Supervivientes muerto = menorInfluencia();
		
		while(!muerto.estaMuerto()) {
			muerto.recibirHerida(false);
		}
	}
	
	public int pasarCrisis() {
		int contador = 0;
		String objeto = ronda.getObjeto();
		
		for(Carta_Objeto carta : crisis) {
			if(objeto.contentEquals(carta.getTipo())) {
				contador++;
			}else {
				contador--;
			}
		}
		
		return contador/jugadores;
	}
	
	public void añadirCrisis(Carta_Objeto carta) {
		crisis.add(carta);
	}
	
	public int getMoral() {
		return moral;
	}

	//puede que no haga falta
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

	//puede que no haga falta
	public void setRondasRestantes(int rondasRestantes) {
		this.rondasRestantes = rondasRestantes;
	}

	public int getTokensDeHambre() {
		return tokensDeHambre;
	}

	//puede que no haga falta
	public void setTokensDeHambre(int tokensDeHambre) {
		this.tokensDeHambre = tokensDeHambre;
	}

	public int getVertedero() {
		return vertedero;
	}

	//puede que no haga falta
	public void setVertedero(int vertedero) {
		this.vertedero = vertedero;
	}
}
