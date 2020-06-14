package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dado {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int caras = 6;
	private int cantidad;
	private Random r = new Random();
	private List<Integer> dados;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Dado() {
		dados = new ArrayList<>();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String resetDados(int supervivientes) {
		String sal = "";
		cantidad = supervivientes + 1;
		int val = 0;
		
		for(int i = 0; i < cantidad; i++) {
			if(i != 0) {
				sal += "|";
			}
			
			val = r.nextInt(6) + 1;
			dados.add(val);
			sal += val;
		}
		
		return sal;
	}
	
	public int getValor(int id) {
		return dados.get(id);
	}
	
	public int usar(int id) {
		cantidad--;
		int val = dados.indexOf(id);
		dados.remove(id);
		return val;
	}
	
	public List<Integer> getDados(){
		return dados;
	}
}
