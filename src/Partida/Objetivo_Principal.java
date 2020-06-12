package Partida;

import java.util.Random;

public class Objetivo_Principal {

	private int objetivo;
	private boolean estado = false;
	private Random r = new Random();
	private int rondas;
	private int moral;
	private int[] vacios;
	
	private int contadorObjetivo = 0;	//SERÁ UN CONTADOR DE ZOMBIES O DE MAZOS VACÍOS
	
	public Objetivo_Principal(int objetivo) {
		this.objetivo = objetivo;
		
		if(objetivo == 0) {
			moral = 6;
			rondas = 6;
		}else {
			moral = 5;
			rondas = 6;
			vacios = new int[6];
		}
	}
	
	public void actualizar(int loc) {
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
			if(vacios[loc] == 0) {
				contadorObjetivo++;
				if(contadorObjetivo >= 2) {
					estado = true;
				}
				vacios[loc] = 1;
			}
		}
		break;
		}
	}
	
	public boolean completado() {
		return estado;
	}
	
	public int getId() {
		return objetivo;
	}
	
	public int getMoral() {
		return moral;
	}
	
	public int getRondas() {
		return rondas;
	}
}
