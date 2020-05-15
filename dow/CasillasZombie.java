//Gómez 14/05

package dow;

public class CasillasZombie {

	//Atributo/s
	private boolean hayZombie;
	private boolean hayBarricada;
	
	//Constructor/es
	public CasillasZombie() {
		this.hayZombie = false;
		this.hayBarricada = false;
	}

	//Método/s
	public boolean getHayZombie() {
		return hayZombie;
	}

	public void setHayZombie(boolean b) {
		this.hayZombie = b;
	}

	public boolean getHayBarricada() {
		return hayBarricada;
	}

	public void setHayBarricada(boolean b) {
		this.hayBarricada = b;
	}

	/*
	public boolean anyadirZombies(int n) {
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
      
	}*/
	
}
