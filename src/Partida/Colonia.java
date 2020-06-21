package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta_Crisis;
import Cartas.Carta_Objeto;
import Cartas.Carta_Supervivientes;

public class Colonia extends Localizacion {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	

	//OBJETIVOS
	private Objetivo_Principal ObjetivoPrincipal;			//es posible que esto sea eliminado	
	
	//DATOS PARA MOVIMIENTO
	private List<List<CasillasZombie>> puertas;
	
	//DATOS DE CONTROL
	private int inutiles;
	private int numJugadores;
	private int zombiesExternos;
	private int moral;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Colonia(Objetivo_Principal c, int jugadores) {
		super(null, 24, 3, 6, 0);	
		
		this.ObjetivoPrincipal = c;
		
		inicCasillasZombie();
		zombiesExternos = 0;
		
		this.inutiles = 23;
		this.numJugadores = jugadores;
		this.moral = c.getMoral();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODO DEL CONSTRUCTOR
	private void inicCasillasZombie() {
		this.puertas = new ArrayList<List<CasillasZombie>>();
		
		for (int i = 0; i < 6; i++) {
			List<CasillasZombie> aux = new ArrayList<CasillasZombie>();
			
			for (int j = 0; j < 3; j++) {
				aux.add(new CasillasZombie());
			}
			
			this.puertas.add(aux);
		}
		
		if(ObjetivoPrincipal.getId() == 1) {
			this.puertas.get(0).get(0).setHayZombie(true);
			this.puertas.get(1).get(0).setHayZombie(true);
		}
	}
	
	public int anyadirSupervivientes(Carta_Supervivientes personaje) {
		int aux = super.getPimeraValida();
		super.getSupervivientes().put(aux, personaje);
		return aux;
	}
	
	public int anyadirInutiles() {
		inutiles--;
		
		return inutiles + 1;
	}
	
	//METODOS DE INTERFAZ ENTRE EL USUARIO Y CASILLASZOMBIE
	public void matar(int puerta) {
		int i = 0;
		boolean muerto = false;
		while (i < 3 && !muerto) {
			if (this.puertas.get(puerta).get(i).getHayZombie()) {
				this.puertas.get(puerta).get(i).setHayZombie(false);
				muerto = true;
			}
			++i;
		}
	}
	
	public void barricada(int puerta) {
		int i = 0;
		boolean puesta = false;
		while (i < 3 && !puesta) {
			if (!this.puertas.get(puerta).get(i).getHayBarricada() &&
				!this.puertas.get(puerta).get(i).getHayZombie() && !puesta) {
				this.puertas.get(puerta).get(i).setHayBarricada(true);
				puesta = true;
			}
			++i;
		}
	}
	
	//METODOS DE RONDA
	
	public void pasarRonda(Carta_Crisis crisis) {
		//A IMPLEMENTAR CUANDO ESTE LA CLASE PARTIDA
	}
	
	@Override
	public String ponerBarricada() throws BarricadaException {
		int i = 0;
		int j = 0;
		boolean barricadaPuesta = false;
		while (!barricadaPuesta && i < this.puertas.size()) {
			List<CasillasZombie> casillasZombie = puertas.get(i);
			j = 0;
			while(!barricadaPuesta && j < casillasZombie.size()) {
				if (casillasZombie.get(j).getHayBarricada() || casillasZombie.get(j).getHayZombie()) {
					j++;
				}else {
					casillasZombie.get(j).setHayBarricada(true);
					barricadaPuesta = true;
				}
			}
			i++;
		}
		
		if(!barricadaPuesta) {
			throw new BarricadaException("No hay casillas disponibles");
		}
		
		return Integer.toString(i-1+6) + "|" + Integer.toString(j);
	}
	
//	@Override
//	public String anyadirZombie() {
//		boolean colocado = false;
//		int[] posiciones = new int[6];
//		int i = 0;
//		int j;
//		List<CasillasZombie> aux;
//		
//		
//		while(!colocado && i < 6) {
//			aux = puertas.get(i);
//			CasillasZombie aux2;
//			j = 0;
//			while(!colocado && j < 3) {
//				aux2 = aux.get(j);
//				if(!aux2.getHayBarricada() && !aux2.getHayZombie()){
//					aux2.setHayZombie(true);
//					colocado = true;
//					posiciones[i]++;
//				}
//				j++;
//			}
//		}
//		
//		//SI NO CABEN, SE MATA A UN SUPERVIVIENTE
//		if(!colocado) {
//			eliminarSuperviviente(super.menorInfluencia());
//		}
//		
//		return Integer.toString(posiciones[0]) + Integer.toString(posiciones[1]) + Integer.toString(posiciones[2]) + Integer.toString(posiciones[3]) + Integer.toString(posiciones[4])+ 
//				Integer.toString(posiciones[5]);
//	}
	
	@Override
	public String [] actualizarCasillasZombiePasoDeRonda() {
		int numZombies = (int) Math.round(((double) this.getSupervivientes().size() + (double) (23 - inutiles)) / 2) + zombiesExternos;
		int noColocados = 0;
		String [] colocados = new String[6];
		boolean colocado;
		List<CasillasZombie> puerta;
		int puertaActual;
		String [] salida = new String[2];
		for(int i = 0; i < colocados.length; i++) {
			colocados[i] = "";
		}
		
		for(int j = 0; j < numZombies; j++) {
			puertaActual = j % 6;
			puerta = puertas.get(puertaActual);
			colocado = false;
			int i = 0;
			CasillasZombie aux;
			
			//INTENTA COLOCAR EN CASILLAS VACÍAS
			while(!colocado && i < puerta.size()) {
				aux = puerta.get(i);
				if (!aux.getHayZombie() && !aux.getHayBarricada()) {
					aux.setHayZombie(true);
					colocado = true;
					colocados[puertaActual] += i;
				} else {
					i++;
				}
			}
			
			//INTENTA COLOCAR EN BARRICADAS
			i = 0;
			while (!colocado && i < puerta.size()) {
				aux = puerta.get(i);
				if (aux.getHayBarricada()) {
					aux.setHayBarricada(false);
					colocado = true;
					colocados[puertaActual] += (i + 4);
				} else {
					i++;
				}
			}
			
			//INTENTA COLOCAR EN LAS CASILLAS VACÍAS QUE QUEDEN DE QUITAR BARRICADAS
			i = 0;
			while (!colocado && i < puerta.size()) {
				aux = puerta.get(i);
				if (!aux.getHayZombie() && !aux.getHayBarricada()) {
					aux.setHayZombie(true);
					colocado = true;
					colocados[puertaActual] += i;
				} else {
					i++;
				}
			}
			
			if (!colocado) {
				noColocados++;
			}
		}
		
		int muertos = noColocados;
		Carta_Supervivientes muerto;
		
		//MATAMOS A LOS JUGADORES NECESARIOS
		while(noColocados > 0) {
			muerto = super.menorInfluencia();
			if(muerto != null) {
				muerto.recibirHerida(false);
				muerto.recibirHerida(false);
				muerto.recibirHerida(false);
			}
			noColocados--;
		}
		
		//DEVOLVEMOS EL NUMERO DE ZOMBIES COLOCADOS
		salida[0] = "";
		salida[1] = "";
		
		for(int i = 0; i < colocados.length; i++) {
			if(i != 0) {
				salida[0] += "|";
			}
			if(colocados[i] != "") {
				salida[0] += colocados[i]; 
			}
		}
		zombiesExternos = 0;
		salida [1] = Integer.toString(muertos);
		
		return salida;
	}
	
	@Override
	//POR AHORA MATA EL PRIMER ZOMBIE QUE PILLA
	public String matarZombie() throws MatarException {
		boolean matado = false;
		int i = 0, j = 0;
		List<CasillasZombie> puerta;
		
		while(!matado && i < puertas.size()) {
			puerta = puertas.get(i);
			CasillasZombie casilla;
			j = puerta.size() - 1;
			while(!matado && j >= 0) {
				casilla = puerta.get(j);
				if(casilla.getHayZombie()) {
					casilla.setHayZombie(false);
					matado = true;
				}else {
					j--;
				}
			}
			if(!matado) {
				i++;
			}
		}
		
		if(!matado) {
			throw new MatarException("No hay zombies en esa posicion");
		}
		
		//DEVUELVE LA PUERTA Y LA CASILLA EN LA QUE SE HA COLOCADO
		return Integer.toString(i + 6) + "|" + Integer.toString(j);
	}
	
	public void addZombiesExternos(int num) {
		zombiesExternos+= num;
	}
	
	//GETTERS Y SETTERS
	
	public List<List<CasillasZombie>> getPuertas() {
		return this.puertas;
	}
	
	public int getInutiles() {
		return inutiles + 1;
	}
	
	public int getMoral() {
		return moral;
	}
	
	public void setMoral(int val) {
		moral = val;
	}
}
