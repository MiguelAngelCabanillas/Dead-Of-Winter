//Gómez 14/05

package dow;

import java.util.concurrent.ThreadLocalRandom;

public class DadoDeRiesgo {
	
	//Atributos.
	private final int caras = 12;
	
	//Constructor/es.
	public DadoDeRiesgo() {}
	
	//Métodos.
	public int tirarDado() {
		return ThreadLocalRandom.current().nextInt(1,this.caras + 1);
	}
	
}
