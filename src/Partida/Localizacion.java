package Partida;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Cartas.Carta;
import Cartas.Carta_Supervivientes;

public class Localizacion {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATOS PARA LA INTERFAZ
	private int id;
	
	//DATOS PARA EL MOVIMIENTO
	private List<CasillasZombie> casillasZombie;
	private Map<Integer, Carta_Supervivientes> supervivientes;
	private int maximoNumSupervivientes;
	
	//DATOS PARA LA BUSQUEDA
	private Mazo mazo;
	private int tokensDeRuido;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Localizacion(Mazo m, int max, int numCasillasZombie, int id, int zombies) {
		this.id = id;
		
		inicCasillasZombie(numCasillasZombie);
		this.supervivientes = new TreeMap<Integer, Carta_Supervivientes>();
		this.maximoNumSupervivientes = max;
		
		this.mazo = m;
		this.tokensDeRuido = 0;
		

		for(int i = 0; i < zombies; i++) {
			anyadirZombie();
		}
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
	public int getId() {
		return id;
	}
	
	public List<CasillasZombie> getCasillasZombie() {
		return this.casillasZombie;
	}

	public Map<Integer, Carta_Supervivientes> getSupervivientes() {
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
	
	public void setSupervivientes(Map<Integer, Carta_Supervivientes> supervivientes) {
		this.supervivientes = supervivientes;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public void setTokensDeRuido(int tokensDeRuido) {
		this.tokensDeRuido = tokensDeRuido;
	}
	
	//METODOS DE RONDA
	public String [] actualizarCasillasZombiePasoDeRonda() {
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
		
		int muertos = numZombies;
		//SE MATA A LOS SUPERVIVIENTES SOBRANTES
		while(numZombies > 0) {
			Carta_Supervivientes muerto = this.supervivientes.get(getPosicion(menorInfluencia()));
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			muerto.recibirHerida(false);
			numZombies--;
		}
		
		//DEVOLVEMOS EL NÚMERO DE ZOMBIES COLOCADOS
		String [] aux = new String[2];
		aux[0] = Integer.toString(this.supervivientes.size());
		aux[1] = Integer.toString(muertos);
		
		return aux;
	}
	
	public String anyadirZombie() {
		String casillas = "";
		boolean colocado = false;
		CasillasZombie aux;
		int i = 0;
		
		//INTENTA COLOCARLO EN UN HUECO
		while(!colocado && i < casillasZombie.size()) {
			aux = casillasZombie.get(i);
			if(!aux.getHayZombie()  && !aux.getHayBarricada()) {
				aux.setHayZombie(true);
				casillas += i;
				colocado = true;
				
			}
			i++;
		}

		i = 0;
		
		//INTENTA COLOCARLO EN UNA BARRICADA
		while(!colocado && i < casillasZombie.size()) {
			aux = casillasZombie.get(i);
			if(!aux.getHayZombie()  && !aux.getHayBarricada()) {
				aux.setHayZombie(true);
				casillas += i;
				colocado = true;
			}
			i++;
		}
		
		if(!colocado) {
			menorInfluencia().recibirHerida(false);
			menorInfluencia().recibirHerida(false);
			menorInfluencia().recibirHerida(false);
		}
		
		return casillas;
	}
	
	//METODOS DE CONTROL
	public void eliminarSuperviviente(Carta_Supervivientes personaje) {
		boolean encontrado = false; int i = 0;
		while(!encontrado) {
			Carta_Supervivientes aux = supervivientes.get(i);
			if(aux.equals(personaje)) {
				supervivientes.remove(i);
				encontrado = true;
			}else {
				i++;
			}
		}
	}
	
	public boolean esta(Carta_Supervivientes personaje) {
		return supervivientes.containsValue(personaje);
	}
	
	public int getPimeraValida() {
		int i = 0;
		boolean encontrado = false;
		
		while(i < supervivientes.size() && !encontrado) {
			if(!supervivientes.containsKey(i)) {
				encontrado = true;
			}else {
				i++;
			}
		}
		
		return i;
	}
	
	public int getPosicion(Carta_Supervivientes personaje) {
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < supervivientes.size()) {
			if(supervivientes.get(i) != null && supervivientes.get(i).equals(personaje)) {
				encontrado = true;
			}else {
				i++;
			}
		}
		
		return i;
	}
	
	//METODO PARA BUSCAR AL SUPERVIVIENTE CON MENOS INFLUENCIA
	public Carta_Supervivientes menorInfluencia() {
		Carta_Supervivientes aux = supervivientes.get(0);
		
		for(int i = 0; i < supervivientes.size(); i++) {
			if(aux.compareTo(supervivientes.get(i)) > 1) {
				aux = supervivientes.get(i);
			}
		}
		
		return aux;
	}
	
	//METODOS INTERFAZ ENTRE JUGADOR Y CASILLASZOMBIE
	
	//ESTABA MAL (i++ y NO i-1).
	public int matarZombie() {
		int i = casillasZombie.size();
		boolean zombieMuerto = false;
		
		while (!zombieMuerto && i >= 0) {
			if (this.casillasZombie.get(i-1).getHayZombie()) {
				this.casillasZombie.get(i-1).setHayZombie(false);
				zombieMuerto = true;
			} else {
				i--;
			}
		}
		
		return i;
	}
	
	public int ponerBarricada() {
		int i = 0;
		boolean barricadaPuesta = false;
		while (!barricadaPuesta && i < this.casillasZombie.size()) {
			if (!this.casillasZombie.get(i).getHayBarricada() && !this.casillasZombie.get(i).getHayZombie()) {
				this.casillasZombie.get(i).setHayBarricada(true);
				barricadaPuesta = true;
			}else {
				i++;
			}
		}
		
		if(!barricadaPuesta) {
			i = -1;
		}
		
		return i;
	}
	
	//METODOS DE BUSQUEDA
	public Carta cogerCarta() {
		return mazo.getCarta();
	}
	
	//METODOS DE MOVIMIENTO
	public int llegar(Carta_Supervivientes personaje) {
		int mover = -1;
		
		if (supervivientes.size() < maximoNumSupervivientes) {
			mover = getPimeraValida();
			supervivientes.put(mover, personaje);
		}
		
		return mover;
	}
	
	public void irse(Carta_Supervivientes personaje) {
		supervivientes.remove(getPosicion(personaje));
	}
	
	//METODOS DE CLASE
	@Override
	public boolean equals(Object o) {
		return (o instanceof Localizacion) && (this.getId() == ((Localizacion)o).getId()) && (this.hashCode() == ((Localizacion)o).hashCode());
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}
