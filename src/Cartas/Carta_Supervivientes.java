package Cartas;

import java.util.ArrayList;
import java.util.List;

import Excepciones.EquipableException;

public class Carta_Supervivientes extends Carta implements Comparable<Carta_Supervivientes>{

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int influencia;
	private int ataque;
	private int busqueda;
	private String nombre;
	
	//HABILIDADES PASIVAS
	private boolean tirarAlMover;
	private boolean tirarAlMatar;
	private boolean movido = false;
	private boolean usada;	//PARA LA PASIVA
	private int buscarDoble;
	private int vaciaVertedero;
	private int heridasParaMatar;
	private boolean pasivaDeAtaque;
	private boolean pasivaDeBusqueda;
	private boolean guarda;
	private boolean perro;
	/*
	 * ESTE ATRIBUTO INDICA LA LOCALIZACIÓN DONDE EL SUPERVIVIENTE BUSCA DOBLE
	 * PARA SUPERVIVIENTES SIN HABILIDAD DE BÚSQUEDA ESTE VALOR VALE -1
	 * PARA EL RESTO, TENDRÁ UN NÚMERO ENTRE 0 Y 5 QUE INDICA LA LOCALIZACION
	 */
	
	private List<Integer> equipamiento;	//puede que no sea necesario despues
	private List<Boolean> usado;
	private int heridas;
	private int congelamiento;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Carta_Supervivientes(int id, int ataque, int busqueda, int influencia, String nombre) {
		super(id);
		this.ataque = ataque;
		this.busqueda = busqueda;
		this.nombre = nombre;
		
		this.heridas = 0;
		this.congelamiento = 0;
		this.influencia = influencia;
		this.equipamiento = new ArrayList<>();
		this.usado = new ArrayList<>();
		
		tirarAlMatar = true;
		tirarAlMover = true;
		usada = false;
		movido = false;
		buscarDoble = -1;
		vaciaVertedero = 3;
		heridasParaMatar = 3;
		pasivaDeAtaque = false;
		pasivaDeBusqueda = false;
		guarda = false;
		usada = false;
		perro = false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODOS DE EQUIPAMIENTO
	public void equipar(int idCarta) {
		equipamiento.add(idCarta);
		usado.add(false);
	}
	
	public List<Integer> getEquipamiento(){
		return equipamiento;
	}
	
	public boolean tieneEquipado(int id) {
		return equipamiento.contains(id);
	}
	
	public boolean usado(int id) {
		return usado.get(equipamiento.indexOf(id));
	}
	
	public void usar(int id) {
			usado.set(equipamiento.indexOf(id), true);
	}
	
	//GETTERS Y SETTERS	
	
	//NECESARIOS EN INICSUPERVIVIENTES
	public boolean getPasivaDeBusqueda() {
		return pasivaDeBusqueda;
	}
	
	public void setPasivaDeBusqueda(boolean val) {
		pasivaDeBusqueda = val;
	}
	
	public boolean getPasivaDeAtaque() {
		return pasivaDeAtaque;
	}
	
	public void setPasivaDeAtaque(boolean s) {
		pasivaDeAtaque = s;
	}
	
	public int doble() {
		return buscarDoble;
	}
	
	public void setTirarMover(boolean t) {
		tirarAlMover = t;
	}
	
	public void setLoc(int i) {
		buscarDoble = i;
	}
	
	public void setTirarMatar(boolean t) {
		tirarAlMatar = t;
	}
	
	public void setUsado(boolean t) {
		usada = t;
	}
	
	public void resetEquipables() {
		for(int i = 0; i < usado.size(); i++) {
			usado.set(i, false);
		}
	}
	
	public boolean getUsado(){
		return usada;
	}
	
	///////////////////////////////////////////////////////////////////////
	public boolean getPerro() {
		return perro;
	}
	
	public void setPerro(boolean b) {
		perro = b;
	}
	
	public void setGuarda(boolean b) {
		guarda = b;
	}
	
	public boolean getGuarda() {
		return guarda;
	}
	
	public void setVertedero(int v) {
		vaciaVertedero = v;
	}
	
	public void curarHerida() {
		//PRIORIZA CURAR UNA HERIDA POR CONGELAMIENTO
		if(congelamiento != 0) {
			congelamiento--;
		}else {
			heridas--;
		}
	}
	
	public int getVertedero() {
		int aux;
		if(usada) {
			aux = 3;
		}else {
			aux = vaciaVertedero;
		}
		return aux;
	}
	
	public boolean getMovido() {
		return movido;
	}
	
	public void setMovido(boolean movido) {
		this.movido = movido;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean tiraAlAtacar() {
		return tirarAlMatar;
	}
	
	public boolean tiraAlMoverse() {
		return tirarAlMover;
	}
	
	public int getInfluencia() {
		return influencia;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public int getBusqueda() {
		return busqueda;
	}
	
	public int getHeridas() {
		return this.heridas;
	}
	
	public int getCongelamiento() {
		return congelamiento;
	}
	
	public void setHeridasParaMatar(int cant) {
		heridasParaMatar = cant;
	}
	
	//METODOS DE SALUD DEL PERSONAJE
	public void congelamiento() {
		if (this.congelamiento != 0) {
			this.heridas++;
		}
	}
	
	public void recibirHerida(boolean esCongelamiento) {
		if (esCongelamiento && !guarda) {
			this.congelamiento++;
		}else {
			this.heridas++;
		}
	}
	
	public boolean estaMuerto() {
		boolean res = false;
		if (this.heridas + this.congelamiento >= heridasParaMatar) {
			res = true;
		}
		return res;
	}
	
	//METODOS DE CLASE
	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Carta_Supervivientes) && (this.getId() == ((Carta_Supervivientes) arg0).getId()) && this.hashCode() == ((Carta_Supervivientes)arg0).hashCode();
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(super.getId());
	}

	@Override
	public int compareTo(Carta_Supervivientes o) {
		int salida = 0;
		
		if(this.influencia < o.influencia) {
			salida = -1;
		}else if(this.influencia > o.influencia) {
			salida = 1;
		}
		
		return salida;
	}
	
}
