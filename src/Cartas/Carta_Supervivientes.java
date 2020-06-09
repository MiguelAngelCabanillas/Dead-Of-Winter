package Cartas;

import java.util.ArrayList;
import java.util.List;

public class Carta_Supervivientes extends Carta implements Comparable<Carta_Supervivientes>{

	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int influencia;
	private int ataque;
	private int busqueda;
	
	//HABILIDADES PASIVAS
	private boolean tirarAlMover;
	private boolean tirarAlMatar;
	
	private int buscarDoble;
	/*
	 * ESTE ATRIBUTO INDICA LA LOCALIZACIÓN DONDE EL SUPERVIVIENTE BUSCA DOBLE
	 * PARA SUPERVIVIENTES SIN HABILIDAD DE BÚSQUEDA ESTE VALOR VALE -1
	 * PARA EL RESTO, TENDRÁ UN NÚMERO ENTRE 0 Y 5 QUE INDICA LA LOCALIZACION
	 */
	
	private List<Carta_Objeto> equipamiento;	//puede que no sea necesario despues
	private int heridas;
	private boolean congelamiento;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Carta_Supervivientes(int id, int ataque, int busqueda, int influencia) {
		super(id);
		this.ataque = ataque;
		this.busqueda = busqueda;
		this.heridas = 0;
		this.congelamiento = false;
		this.influencia = influencia;
		this.equipamiento = new ArrayList<>();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//METODOS DE EQUIPAMIENTO
	public void equipar(Carta_Objeto carta) {
		equipamiento.add(carta);
	}
	
	public List<Carta_Objeto> getEquipamiento(){
		return equipamiento;
		
	}
	
	//GETTERS Y SETTERS	
	public int doble() {
		return buscarDoble;
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
	
	//METODOS DE SALUD DEL PERSONAJE
	public void congelamiento() {
		if (this.congelamiento == true) {
			this.heridas++;
		}
	}
	
	public void recibirHerida(boolean esCongelamiento) {
		this.heridas++;
		if (esCongelamiento) {
			this.congelamiento = true;
		}
	}
	
	public boolean estaMuerto() {
		boolean res = false;
		if (this.heridas >= 3) {
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
