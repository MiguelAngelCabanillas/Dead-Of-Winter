//VERSIÓN SEMI-DEFINITIVA
package dow;

import java.util.concurrent.ThreadLocalRandom;

public class Dado {
	//Atributos
	private final int caras = 6;
	private int valor;
	private boolean haSidoTirado;
	private boolean usado;
	
	//Constructor/es
	public Dado() {
		this.haSidoTirado = false;
		usado = false;
	}
	
	//Métodos
	public void tirarDado() throws dadoException {
		if (this.haSidoTirado == true) {
			throw new dadoException("Este dado ya ha sido utilizado.");
		} else {
			this.haSidoTirado = true;
			valor = ThreadLocalRandom.current().nextInt(1,this.caras + 1);
		}
	}
	
	public void usarDado() {
		usado = true;
	}
	
	public int valor() {
		return valor;
	}
	
	public boolean estaUsado() {
		return usado;
	}

	public void setHaSidoTirado(boolean t) {
		this.haSidoTirado = t;
	}
}
