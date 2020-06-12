package Partida;

public class Crisis {

	private int id;
	
	private int tipo1;
	private int tipo2;
	private int tipo3;
	
	private int cantidad;
	private int actuales;
	
	private boolean pasada;
	private boolean sobra;
	
	private int [] donaciones;
	
	public Crisis(int id, int jugadores) {
		this.id = id;
		cantidad = jugadores;
		
		donaciones = new int[jugadores];
		
		inic();
	}
	//TODO
	
	private void inic() {
		switch(id) {
		case 300 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 301 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 302 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 303 : {
			tipo1 = 5; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 304 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 305 : {
			tipo1 = 3; tipo2 = 4; tipo3 = -1;
		}
		break;
		case 306 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 307 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		}
	}
	
	public void ayadir(int id) {
		if(tipo1 == id || tipo2 == id || tipo3 == id) {
			actuales++;
		}else {
			actuales--;
		}
		
		if(cantidad == actuales) {
			pasada = true;
		}
		
		if(cantidad + 2 == actuales) {
			sobra = true;
		}
	}
	
	public boolean pasada() {
		return pasada;
	}
	
	public boolean sobra() {
		return sobra;
	}
	
	public int getId() {
		return id;
	}
}