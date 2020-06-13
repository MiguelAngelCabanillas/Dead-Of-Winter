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
<<<<<<< HEAD
		for(int i = 0; i < 8; i ++) {
			aux = new Crisis(i+100, numJug);
=======
		for(int i = 300; i < 308; i ++) {
			aux = new Crisis(i, numJug);
>>>>>>> c43fcf3cb78b2ce8cb1122e84b5ff352bc8b1217
			crisis.add(aux);
		}
	}
	
	public Crisis getCrisis() {
		return crisis.remove(r.nextInt(crisis.size()));
	}
	
}
