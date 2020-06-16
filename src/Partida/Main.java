package Partida;

import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

public class Main {

	public static void main(String[] args) throws MoverException, BarricadaException, VertederoException {
		Principal p = new Principal(1);
		
		p.inicPartida(2);
		//System.out.println(p.getIdCartas(0));
		//System.out.println(p.getIdCartas(1));
		//System.out.println(p.getIdCartas(2));
		
		p.addSuperviviente(0, 103);
		p.addSuperviviente(0, 105);
		p.addSuperviviente(1, 102);
		p.addSuperviviente(1, 104);
		p.inicSupervivientesEnColonia();
		p.inicDados();
		p.pasaTurno(0);
		try {
		p.getNombre(103);
		p.ponerBarricada(103);
		p.mover(103, 2);
		p.buscar(103);
		p.atacar(103);
		p.ponerBarricada(105);
		p.pasaTurno(1);
		
		p.mover(104, 3);
		p.ponerBarricada(104);
		p.mover(102, 4);
		p.buscar(102);
		p.atacar(102);
		p.pasaTurno(0);
		
		p.pasaRonda();
		} catch (BuscarException e) {
			System.err.println(e.getMessage());
		}catch (MatarException e) {
			System.err.println(e.getMessage());
		}catch(DadoException e) {
			System.err.println(e.getMessage());
		}
		
		

	}

}
