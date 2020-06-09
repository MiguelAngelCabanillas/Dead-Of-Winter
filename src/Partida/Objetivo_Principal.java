package Partida;

import java.util.Random;

public class Objetivo_Principal {

	private int objetivo;
	private boolean estado = false;
	private Random r = new Random();
	
	private int contadorObjetivo = 0;	//SERÁ UN CONTADOR DE ZOMBIES O DE MAZOS VACÍOS
	
	public Objetivo_Principal(int objetivo) {
		this.objetivo = objetivo;
	}
	
	public void actualizar() {
		switch(objetivo) {
		case 0 : {		//MISIÓN DE MATAR 25 ZOMBIES (OBTENER 25 FICHAS CON 50% DE ACIERTO)
			int aux = r.nextInt(6);
			
			if(aux >= 3) {
				contadorObjetivo++;
			}
			
			//COMPROBAMOS SI YA HEMOS MATADO LOS ZOMBIES SUFICIENTES
			if(contadorObjetivo >= 25) {
				estado = true;
			}
		}
		break;
		case 1 : {	//MISIÓN DE VACIAR 2 MAZOS
			contadorObjetivo++;

			if(contadorObjetivo >= 2) {
				estado = true;
			}
		}
		break;
		}
	}
	
	public boolean completado() {
		return estado;
	}
}
