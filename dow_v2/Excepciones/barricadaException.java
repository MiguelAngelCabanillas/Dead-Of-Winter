package dow_v2.Excepciones;

public class barricadaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	//Constructor/es.
	public barricadaException() {
		super();
	}
	
	public barricadaException(String msg) {
		super(msg);
	}
	
}