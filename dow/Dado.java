//Gómez 14/05

package dow;

import java.util.concurrent.ThreadLocalRandom;

public class Dado {
	
	//Atributos
	private final int caras = 6;
	private int valor;
	private boolean haSidoTirado;
	
	//Constructor/es
	public Dado() {
		this.haSidoTirado = false;
	}
	
	//Métodos
	public void tirarDado() throws dadoException {
		if (this.haSidoTirado == true) {
			throw new dadoException("Este dado ya ha sido utilizado.");
		} else {
			this.haSidoTirado = true;
			this.valor = ThreadLocalRandom.current().nextInt(1,this.caras + 1);
		}
	}
	
	public int getValor() {
		return valor;
	}
	
	public boolean getHaSidoTirado() {
		return this.haSidoTirado;
	}

	public void setHaSidoTirado(boolean t) {
		this.haSidoTirado = t;
	}
	
}
