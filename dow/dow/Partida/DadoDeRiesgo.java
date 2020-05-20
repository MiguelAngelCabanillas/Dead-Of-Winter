package dow.Partida;

import java.util.concurrent.ThreadLocalRandom;

public class DadoDeRiesgo {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int caras = 12;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public DadoDeRiesgo() {}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public int tirarDado() {
		return ThreadLocalRandom.current().nextInt(1,this.caras + 1);
	}
	
}
