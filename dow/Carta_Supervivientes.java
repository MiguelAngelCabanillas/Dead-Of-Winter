package dow;

import java.util.ArrayList;
import java.util.List;

public class Carta_Supervivientes extends Carta implements Comparable<Carta_Supervivientes>{

	//Atributo/s.
	private int influencia;
	private int ataque;
	private int busqueda;
	private String habilidad;
	private Localizacion lugar;
	private List<Carta_Objeto> equipamiento;
	private int heridas;
	private boolean congelamiento;
	
	//Constructor/es.
	public Carta_Supervivientes(int id, String nombre, String desc, int ataque, int busqueda, String habilidad, int influencia) {
		super(id, nombre, desc);
		this.ataque = ataque;
		this.busqueda = busqueda;
		this.habilidad = habilidad;
		this.heridas = 0;
		this.congelamiento = false;
		this.lugar = null;
		this.influencia = influencia;
		this.equipamiento = new ArrayList<>();
	}
	
	//Método/s.
	public void equipar(Carta_Objeto carta) {
		equipamiento.add(carta);
	}
	
	public void setHeridas(int num) {
		this.heridas = num;
	}
	
	public List<Carta_Objeto> getEquipamiento(){
		return equipamiento;
	}
	
	public Localizacion getLugar() {
		return lugar;
	}
	
	public void setLugar(Localizacion lugar) {
		this.lugar = lugar;
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

	public String getHabilidad() {
		return habilidad;
	}
	
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
		if (this.heridas >= 3) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Carta_Supervivientes) && (this.getNombre() == ((Carta_Supervivientes) arg0).getNombre()) && this.hashCode() == ((Localizacion)arg0).hashCode();
	}
	
	@Override
	public int hashCode() {
		return getNombre().hashCode();
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
