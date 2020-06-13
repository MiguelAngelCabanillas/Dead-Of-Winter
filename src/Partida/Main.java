package Partida;

public class Main {

	public static void main(String[] args) {
		Principal p = new Principal(0);
		
		p.inicPartida(3);

		System.out.println(p.getIdCartas(0));
		System.out.println(p.getIdCartas(1));
		System.out.println(p.getIdCartas(2));
	}

}
