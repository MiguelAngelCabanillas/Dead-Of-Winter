package Partida;

import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

public class Main {

	public static void main(String[] args) throws MoverException, BarricadaException, VertederoException {
		Principal p = new Principal(1);
		
		p.inicPartida(1);
		//System.out.println(p.getIdCartas(0));
		//System.out.println(p.getIdCartas(1));
		//System.out.println(p.getIdCartas(2));
		
		p.addSuperviviente(0, 103);
		p.addSuperviviente(0, 105);
		p.addSuperviviente(0, 102);
		p.addSuperviviente(0, 104);
		p.inicSupervivientesEnColonia();
		p.inicDados();
		
		//ZHHH
		//ZZZZ
		//ZHBZ
		//ZZBZ
		//ZZHZ
		
		p.mover(103, 2);
		p.mover(105, 2);
		p.mover(102, 2);
		
		System.out.println(p.pasaRonda());
		System.out.println(p.pasaRonda());
		
		
//		System.out.println(p.pasaRonda());
//		System.out.println(p.pasaRonda());
//		System.out.println(p.pasaRonda());
//		System.out.println(p.pasaRonda());
//		p.ponerBarricada(103);
//		System.out.println(p.pasaRonda());
	}

}
