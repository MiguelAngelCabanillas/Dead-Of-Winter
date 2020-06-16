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
	private String nombre;
	
	//HABILIDADES PASIVAS
	private boolean tirarAlMover;
	private boolean tirarAlMatar;
	private boolean movido = false;
	private boolean usada;
	private int buscarDoble;
	private int vaciaVertedero;
	/*
	 * ESTE ATRIBUTO INDICA LA LOCALIZACIÓN DONDE EL SUPERVIVIENTE BUSCA DOBLE
	 * PARA SUPERVIVIENTES SIN HABILIDAD DE BÚSQUEDA ESTE VALOR VALE -1
	 * PARA EL RESTO, TENDRÁ UN NÚMERO ENTRE 0 Y 5 QUE INDICA LA LOCALIZACION
	 */
	
	private List<Carta_Objeto> equipamiento;	//puede que no sea necesario despues
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
		
		tirarAlMatar = true;
		tirarAlMover = true;
		usada = false;
		movido = false;
		buscarDoble = -1;
		vaciaVertedero = 3;
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
	
	//NECESARIOS EN INICSUPERVIVIENTES
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
		usada = true;
	}
	
	public boolean getUsado(){
		return usada;
	}
	
	///////////////////////////////////////////////////////////////////////
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
	
	//METODOS DE SALUD DEL PERSONAJE
	public void congelamiento() {
		if (this.congelamiento != 0) {
			this.heridas++;
		}
	}
	
	public void recibirHerida(boolean esCongelamiento) {
		
		if (esCongelamiento) {
			this.congelamiento++;
		}else {
			this.heridas++;
		}
	}
	
	public boolean estaMuerto() {
		boolean res = false;
		if (this.heridas + this.congelamiento >= 3) {
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
