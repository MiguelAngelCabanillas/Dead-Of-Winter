package dow;
public class Carta_Objeto extends Carta {

	private String localizacion;
	private String tipo;
	private int cantidad;
	
	public Carta_Objeto(int id, String nombre, String desc, String loc, String t, int c) {
		super(id, nombre, desc);
		localizacion = loc;
		tipo = t;
		cantidad = c;
	}

	public String getLocalizacion() {
		return localizacion;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
}
