package dow;

public class Carta_Objeto extends Carta {

	//Atributo/s.
	private String localizacion;
	private String tipo;
	private int cantidad;
	
	//Constructor/es.
	public Carta_Objeto(int id, String nombre, String desc, String loc, String t, int c) {
		super(id, nombre, desc);
		localizacion = loc;
		tipo = t;
		cantidad = c;
	}

	//Método/s.
	public String getLocalizacion() {
		return localizacion;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Carta_Objeto) {
			Carta_Objeto c = (Carta_Objeto) o;
			res = this.getId() == c.getId();
		}
		return res;
	}
	
	public int hashCode() {
		return Integer.hashCode(this.getId());
	}
	
}
