package dow;
public class Carta_Objeto extends Carta {

	private String localizacion;
	private String tipo;
	
	public Carta_Objeto(int id, String nombre, String desc, String loc, String t) {
		super(id, nombre, desc);
		localizacion = loc;
		tipo = t;
	}

	public String getLocalizacion() {
		return localizacion;
	}
	
	public String getTipo() {
		return tipo;
	}
	
}
