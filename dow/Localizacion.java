//Gómez 14/05

package dow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Localizacion {
	
	//Atributo/s.
	private String nombre;
	private List <Carta_Supervivientes> supervivientes;
	private int maximoNumSupervivientes;
	private Mazo mazo;
	private List<CasillasZombie> casillasZombie;
	private int tokensDeRuido;
	
	//Constructor/es.
	public Localizacion(String n, Mazo m, int max, int numCasillasZombie) {
		this.nombre = n;
		this.supervivientes = new LinkedList<Carta_Supervivientes>();
		this.maximoNumSupervivientes = max;
		this.mazo = m;
		this.casillasZombie = new LinkedList<CasillasZombie>();
		int i = 0;
		while (i != numCasillasZombie)  {
			this.casillasZombie.add(new CasillasZombie());
			i++;
		}
		this.tokensDeRuido = 0;
	}

	//Método/s.
	public String getNombre() {
		return nombre;
	}

	public List<Carta_Supervivientes> getSupervivientes() {
		return supervivientes;
	}
	
	public int getMaximo() {
		return this.maximoNumSupervivientes;
	}
	
	public Mazo getMazo() {
		return mazo;
	}
	
	public List<CasillasZombie> getCasillasZombie() {
		return this.casillasZombie;
	}
	
	public int getTokensDeRuido() {
		return tokensDeRuido;
	}
	
	public void setSupervivientes(List<Carta_Supervivientes> supervivientes) {
		this.supervivientes = supervivientes;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public void setTokensDeRuido(int tokensDeRuido) {
		this.tokensDeRuido = tokensDeRuido;
	}
	
	//TERMINAR
	public int [] menorInfluencia(int num) {
		return null;
	}
	
	public void actualizarCasillasZombiePasoDeRonda() {
		int numZombies = this.supervivientes.size();
		int i = 0;
		
		while (i < this.casillasZombie.size() && numZombies != 0) {
			 if (!this.casillasZombie.get(i).getHayZombie() && !this.casillasZombie.get(i).getHayBarricada()) {
					this.casillasZombie.get(i).setHayZombie(true);
					numZombies--;
				}
			 i++;
		}
		
		i = 0;
		while (i < this.casillasZombie.size() && numZombies != 0) {
			if (this.casillasZombie.get(i).getHayBarricada()) {
				this.casillasZombie.get(i).setHayBarricada(false);
				numZombies--;
			}
			i++;
		}
	
		if (numZombies > 0) {
			int [] indices = this.menorInfluencia(numZombies);
			i = 0;
			while (i < indices.length) {
				this.supervivientes.get(indices[i]).setHeridas(3);
				i++;
			}
		}
	}
	
	public void matarZombie() throws matarException {
		int i = 0;
		boolean zombieMuerto = false;
		while (i < this.casillasZombie.size() && !zombieMuerto) {
			if (this.casillasZombie.get(i).getHayZombie()) {
				this.casillasZombie.get(i).setHayZombie(false);
				zombieMuerto = true;
			}
			i++;
		}
	}
	
	public void ponerBarricada() throws barricadaException {
		int i = 0;
		boolean barricadaPuesta = false;
		while (i < this.casillasZombie.size() && !barricadaPuesta) {
			if (!this.casillasZombie.get(i).getHayBarricada() && !this.casillasZombie.get(i).getHayZombie()) {
				this.casillasZombie.get(i).setHayBarricada(true);
				barricadaPuesta = true;
			}
			i++;
		}
	}
	
	public Carta_Objeto cogerCarta() {
		return mazo.getCarta();
	}
	
	public boolean llegar(Carta_Supervivientes personaje) {
		boolean mover = false;
		
		if (supervivientes.size() < maximoNumSupervivientes) {
			supervivientes.add(personaje);
			mover = true;
		}
		
		return mover;
	}
	
	public void irse(Carta_Supervivientes personaje) {
		supervivientes.remove(personaje);
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Localizacion) && (this.getNombre().contentEquals(((Localizacion)o).getNombre())) && (this.hashCode() == ((Localizacion)o).hashCode());
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
}
