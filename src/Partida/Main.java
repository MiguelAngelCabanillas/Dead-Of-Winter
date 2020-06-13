package Partida;

import Cartas.Carta_Supervivientes;

public class Main {

	public static void main(String[] args) {
		Principal p = new Principal(0);
		
		p.inicPartida(2);

		//System.out.println(p.getIdCartas(0));
		//System.out.println(p.getIdCartas(1));
		//System.out.println(p.getIdCartas(2));
		
		p.addSuperviviente(0, 103);
		p.addSuperviviente(0, 105);
		
		p.addSuperviviente(1, 102);
		p.addSuperviviente(1, 104);
		
		p.inicSupervivientesEnColonia();
		
		
		p.mover(103, 2);
		p.mover(105, 0);
		
		p.ponerBarricada(103);
		p.ponerBarricada(103);
		p.ponerBarricada(103);
		p.ponerBarricada(103);
		
		p.ponerBarricada(105);
		p.ponerBarricada(105);
		p.ponerBarricada(105);
		p.ponerBarricada(105);
	}

}