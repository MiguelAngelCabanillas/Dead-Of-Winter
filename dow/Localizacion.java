//PARA NADA VERSIÓN DEFINITIVA
package dow;

import java.util.LinkedList;
import java.util.List;

public class Localizacion {
	//Atributos
	private String nombre;
	private List <Carta_Supervivientes> supervivientes;
	private Mazo mazo;
	private CasillasZombie zombies;
	
	private int tokensDeRuido;
	
	//Constructor/es
	public Localizacion(String n, Mazo m, int c, int z) {
		this.nombre = n;
		this.mazo = m;
		this.supervivientes = new LinkedList<Carta_Supervivientes>();
		this.tokensDeRuido = 0;
		this.zombies = new CasillasZombie(c, z);
	}

	//Métodos
	public String getNombre() {
		return nombre;
	}

	public List<Carta_Supervivientes> getSupervivientes() {
		return supervivientes;
	}

	public void setSupervivientes(List<Carta_Supervivientes> supervivientes) {
		this.supervivientes = supervivientes;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public int getTokensDeRuido() {
		return tokensDeRuido;
	}

	public void setTokensDeRuido(int tokensDeRuido) {
		this.tokensDeRuido = tokensDeRuido;
	}
	
	//ACCIONES DE MODIFICACION
	
	public void matar() throws matarException {
		zombies.matarZombie();
	}
	
	public void barricada() {
		zombies.añadirBarricada();
	}
	
	public Carta_Objeto cogerCarta() {
		return mazo.getCarta();
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
