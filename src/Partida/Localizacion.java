package Partida;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;
import Excepciones.matarException;


public class Localizacion {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATOS PARA LA INTERFAZ
	private String nombre;
	
	//DATOS PARA EL MOVIMIENTO
	private List<CasillasZombie> casillasZombie;
	private PriorityQueue <Carta_Supervivientes> supervivientes;
	private int maximoNumSupervivientes;
	
	//DATOS PARA LA BUSQUEDA
	private Mazo mazo;
	private int tokensDeRuido;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Localizacion(String n, Mazo m, int max, int numCasillasZombie) {
		this.nombre = n;
		
		inicCasillasZombie(numCasillasZombie);
		this.supervivientes = new PriorityQueue<Carta_Supervivientes>();
		this.maximoNumSupervivientes = max;
		
		this.mazo = m;
		this.tokensDeRuido = 0;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODO DEL CONSTRUCTOR
	public void inicCasillasZombie(int numCasillasZombie) {
		this.casillasZombie = new LinkedList<CasillasZombie>();
		
		for(int i = 0; i < numCasillasZombie; i++)  {
			this.casillasZombie.add(new CasillasZombie());
		}
	}
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	
	public List<CasillasZombie> getCasillasZombie() {
		return this.casillasZombie;
	}

	public PriorityQueue<Carta_Supervivientes> getSupervivientes() {
		return supervivientes;
	}
	
	public int getMaximo() {
		return this.maximoNumSupervivientes;
	}
	
	public Mazo getMazo() {
		return mazo;
	}

	public int getTokensDeRuido() {
		return tokensDeRuido;
	}
	
	public void setSupervivientes(PriorityQueue<Carta_Supervivientes> supervivientes) {
		this.supervivientes = supervivientes;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public void setTokensDeRuido(int tokensDeRuido) {
		this.tokensDeRuido = tokensDeRuido;
	}
	
	//METODOS DE RONDA
	public void actualizarCasillasZombiePasoDeRonda() {
		int numZombies = this.supervivientes.size();

		for(CasillasZombie hueco : casillasZombie) {
			if(numZombies > 0 && !hueco.getHayZombie() && !hueco.getHayBarricada()) {
				hueco.setHayZombie(true);
				numZombies--;
			}
		}

		for(CasillasZombie hueco : casillasZombie) {
			if(numZombies > 0 && hueco.getHayBarricada()) {
				hueco.setHayBarricada(false);
				numZombies--;
			}
		}
		
		//SE MATA A LOS SUPERVIVIENTES SOBRANTES
		while(numZombies > 0) {
			Carta_Supervivientes muerto = this.supervivientes.poll();;
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			numZombies--;
		}
	}
	
	//METODOS DE CONTROL
	public boolean esta(Carta_Supervivientes personaje) {
		return supervivientes.contains(personaje);
	}
	
	//METODOS INTERFAZ ENTRE JUGADOR Y CASILLASZOMBIE
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
	
	public void ponerBarricada() {
		int i = 0;
		boolean barricadaPuesta = false;
		while (i < this.casillasZombie.size() && barricadaPuesta == false) {
			if (!this.casillasZombie.get(i).getHayBarricada() && !this.casillasZombie.get(i).getHayZombie()) {
				this.casillasZombie.get(i).setHayBarricada(true);
				barricadaPuesta = true;
			}
			i++;
		}
	}
	
	//METODOS DE BUSQUEDA
	public Carta_Objeto cogerCarta() {
		return mazo.getCarta();
	}
	
	//METODOS DE MOVIMIENTO
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
	
	//METODOS DE CLASE
	@Override
	public boolean equals(Object o) {
		return (o instanceof Localizacion) && (this.getNombre().contentEquals(((Localizacion)o).getNombre())) && (this.hashCode() == ((Localizacion)o).hashCode());
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
}
