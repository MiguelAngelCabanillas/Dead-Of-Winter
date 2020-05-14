//VERSIÓN SEMI-DEFINITIVA

package dow;

import java.util.concurrent.ThreadLocalRandom;

public class DadoDeRiesgo {
	private final int caras = 12;
	
	public DadoDeRiesgo() {}
	
	public int tirarDado() {
		return ThreadLocalRandom.current().nextInt(1,this.caras + 1);
	}
}
