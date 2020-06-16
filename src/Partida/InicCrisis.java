package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InicCrisis {

	private List<Crisis> crisis = new ArrayList<>();
	private Random r = new Random();
	
	public InicCrisis(int numJug) {
		inicCrisis(numJug);
	}
	
	private void inicCrisis(int numJug) {
		Crisis aux;

		for(int i = 0; i < 8; i ++) {
			aux = new Crisis(i+300, numJug);
			crisis.add(aux);
		}
		
	}
	
	public Crisis getCrisis() {
		return crisis.remove(r.nextInt(crisis.size()));
	}
	
}
