package dow;

public class CasillasZombie {

	private int casillas;
	private int zombies;
	private int barricadas;
	
	public CasillasZombie(int c, int z) {
		casillas = c;
		zombies = z;
		barricadas = 0;
	}
	
	public boolean añadirZombies(int n) {
		int meter = n;
		while(casillas > 0 && meter > 0) {
			if(barricadas > 0) {
				barricadas--;
				meter--;
			}else {
				meter--;
				casillas--;
				zombies++;
			}
		}
		
		return meter == 0;
	}
	
	public boolean añadirBarricada() throws barricadaException {
		boolean hecho = false;
		if(casillas > 0) {
			barricadas++;
			casillas--;
			hecho = true;
		}else {
			throw new barricadaException("No hay espacio");
		}
		
		return hecho;
	}
	
	//puede no ser necesario
	public int getCasillas() {
		return casillas;
	}
	
	public void matarZombie() throws matarException {
		if(zombies > 0) {
			zombies--;
		}else {
			throw new matarException("No hay zombies");
		}

	}
}
