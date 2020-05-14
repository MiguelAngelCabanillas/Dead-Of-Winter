package dow;
public class Carta_Crisis extends Carta{

	private String Objeto;
	private String Efecto;
	
	public Carta_Crisis(int id, String nombre, String desc, String objeto, String efecto) {
		super(id, nombre, desc);
		Objeto = objeto;
		Efecto = efecto;
	}	

	public String getObjeto() {
		return Objeto;
	}
	
	public String getEfecto() {
		return Efecto;
	}
}
