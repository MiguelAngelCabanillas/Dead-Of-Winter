//Gómez 14/05

package dow;

public class Carta_Crisis extends Carta{
	
	//Atributo/s.
	private Carta_Objeto objeto;
	private String efecto;
	private String opcional;
	
	//Constructor/es.
	public Carta_Crisis(int id, String nombre, String desc, Carta_Objeto objeto, String efecto, String op) {
		super(id, nombre, desc);
		this.objeto = objeto;
		this.efecto = efecto;
		this.opcional = op;
	}	
	
	//Método/s.
	public Carta_Objeto getObjeto() {
		return this.objeto;
	}
	
	public String getEfecto() {
		return this.efecto;
	}

	
	public String getOpcional() {
		return opcional;
	}


}
