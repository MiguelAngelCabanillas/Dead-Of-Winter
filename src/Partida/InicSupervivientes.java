package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta_Supervivientes;

public class InicSupervivientes {

	List<Carta_Supervivientes> cartas = new ArrayList<>();
	
	public InicSupervivientes() {
		inicCartas();
	}
	
	private void inicCartas() {
		
		//BIBLIOTECARIA (100)
		Carta_Supervivientes biblio = new Carta_Supervivientes(100, 5, 4, 46);
		biblio.setLoc(5);
		cartas.add(biblio);	
		
		
		
		//NINJA (124)
		Carta_Supervivientes ninja = new Carta_Supervivientes(124, 2, 4, 30);
		ninja.setTirarMatar(false);
		cartas.add(ninja);
		
		
		
		
		
		
	}
}
