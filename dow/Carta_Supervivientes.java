package dow;

import java.util.ArrayList;
import java.util.List;

public class Carta_Supervivientes extends Carta{

	private int influencia;
	private int ataque;
	private int busqueda;
	private String habilidad;
	private Localizacion lugar;
	private List<Carta_Objeto> equipamiento;
	
	private int heridas;
	private boolean congelamiento;

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
	
	//METODOS
	public void equipar(Carta_Objeto carta) {
		equipamiento.add(carta);
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
		if (this.heridas == 3) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Carta_Supervivientes) && (this.getNombre() == ((Carta_Supervivientes) arg0).getNombre()) && this.hashCode() == ((Localizacion)arg0).hashCode();
	}
	
	public int hashCode() {
		return getNombre().hashCode();
	}
	

}
