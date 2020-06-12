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
	
	public Crisis(int id, int jugadores) {
		this.id = id;
		cantidad = jugadores;
		
		inic();
	}
	
	private void inic() {
		switch(id) {
		case 0 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 1 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 2 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 3 : {
			tipo1 = 5; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 4 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 5 : {
			tipo1 = 3; tipo2 = 4; tipo3 = -1;
		}
		break;
		case 6 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 7 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		}
		
		if(cantidad == actuales) {
			pasada = true;
		}
		
		if(cantidad + 2 == actuales) {
			sobra = true;
		}
	}
	
	public void ayadir(int id) {
		if(tipo1 == id || tipo2 == id || tipo3 == id) {
			actuales++;
		}else {
			actuales--;
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