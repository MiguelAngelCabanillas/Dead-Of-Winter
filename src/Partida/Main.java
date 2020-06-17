package Partida;

import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

public class Main {

	public static void main(String[] args) throws MoverException, BarricadaException, VertederoException {
		Principal p = new Principal(1);
		
		p.inicPartida(3);
		//System.out.println(p.getIdCartas(0));
		//System.out.println(p.getIdCartas(1));
		//System.out.println(p.getIdCartas(2));
		
		p.addSuperviviente(0, 103);
		p.addSuperviviente(0, 116);
		p.addSuperviviente(1, 117);
		p.addSuperviviente(1, 124);
		p.addSuperviviente(2, 119);
		p.addSuperviviente(2, 110);
		p.inicSupervivientesEnColonia();
		p.inicDados();
		p.pasaTurno(0);
		
		/*
		- jug 0 mover 103 loc 0 pos 0 1 herida
		- jug 0 bar 103 loc 0 pos 1
		- jug 0 mover 116 loc 5 pos 0 1 herida
		- jug 0 bar 116 loc 5 pos 1
		- jug 0 bar 116 loc 5 pos 2
		--------------------------------------------
		- jug 1 mover 117 loc 3 pos 0
		- jug 1 mover 124 loc 4 pos 0 
		- jug 1 bar 124 loc 4 pos 1
		- jug 1 bar 117 loc 3 pos 1
		- jug 1 bar 124 loc 4 pos 2
		--------------------------------------------
		- jug 2 mover 119 loc 0 pos 1
		- jug 2 mover 110 loc 3 pos 1 1 congelamiento
		- jug 2 bar 119 (loc 0 pos 2) -> loc 6 puerta 0 pos 0
		*/
		
		//try {
//			p.mover(103, 0);
//			p.ponerBarricada(103);
//			p.mover(116, 5);
//			p.ponerBarricada(116);
//			p.ponerBarricada(116);
//			p.pasaTurno(1);
//			p.mover(117, 3);
//			p.mover(124, 4);
//			p.ponerBarricada(124);
//			p.ponerBarricada(117);
//			p.ponerBarricada(124);
//			p.pasaTurno(2);
//			p.mover(119, 0);
//			p.mover(110, 3);
//			p.ponerBarricada(119);
			p.pasaRonda();
			p.pasaRonda();
			p.pasaRonda();
			p.pasaRonda();
			p.pasaRonda();
			p.pasaRonda();
//		} catch (DadoException e) {
//			System.err.println(e.getMessage());
//		}
		
		
//		try {
//		System.out.println(p.getNombre(103));
//		p.ponerBarricada(103);
//		p.mover(103, 2);
//		p.buscar(103);
//		p.atacar(103);
//		p.ponerBarricada(105);
//		p.pasaTurno(1);
//		
//		p.mover(104, 3);
//		p.ponerBarricada(104);
//		p.mover(102, 4);
//		p.buscar(102);
//		p.atacar(102);
//		p.pasaTurno(0);
//		
//		p.pasaRonda();
//		} catch (BuscarException e) {
//			System.err.println(e.getMessage());
//		}catch (MatarException e) {
//			System.err.println(e.getMessage());
//		}catch(DadoException e) {
//			System.err.println(e.getMessage());
//		}
		
		

	}

}
