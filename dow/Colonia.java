//PARA NADA VERSIÓN DEFINITIVA
package dow;

public class Colonia extends Localizacion {
	//Atributos
	private int moral;
	private Carta_Objetivo_Principal cartaObjetivoPrincipal;
	private int rondasRestantes;
	private int tokensDeHambre;
	private int vertedero;		//el vertedero solo es un contador
	
	//Constructor/es
	public Colonia(Carta_Objetivo_Principal c, int rondas, int moral) {	//he modificado el constructor
		super("Colonia.", null);
		this.moral = moral;
		this.cartaObjetivoPrincipal = c;
		this.rondasRestantes = rondas;
		this.tokensDeHambre = 0;		//se empieza por defecto sin comida
		this.vertedero = 0;
	}

	//Métodos
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
