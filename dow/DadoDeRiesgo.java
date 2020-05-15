package dow;

import java.util.concurrent.ThreadLocalRandom;

public class DadoDeRiesgo {
	
	//Atributo/s.
	private final int caras = 12;
	
	//Constructor/es.
	public DadoDeRiesgo() {}
	
	//Método/s.
	public int tirarDado() {
		return ThreadLocalRandom.current().nextInt(1,this.caras + 1);
	}
	
}
