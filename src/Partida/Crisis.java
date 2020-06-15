package Partida;

import java.util.ArrayList;
import java.util.List;

public class Crisis {

	private int id;
	
	private int tipo1;
	private int tipo2;
	private int tipo3;
	
	private int cantidad;
	private int actuales;
	
	private boolean pasada;
	private boolean sobra;
	
	private int [] donaciones;
	
	private int[] contribJug;
	
	private final int TAM_DONACIONES = 15;

    public Crisis(int id, int jugadores) {
        this.id = id;
        cantidad = jugadores;

        donaciones = new int[TAM_DONACIONES];

        //Inicializo el array a -1
        for(int i = 0; i < TAM_DONACIONES; i++) {
            donaciones[i] = -1;
        }

        contribJug = new int[jugadores];
        
        inic();
    }
	//TODO
	
	private void inic() {
		switch(id) {
		case 300 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 301 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 302 : {
			tipo1 = 3; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 303 : {
			tipo1 = 5; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 304 : {
			tipo1 = 0; tipo2 = 1; tipo3 = 2;
		}
		break;
		case 305 : {
			tipo1 = 3; tipo2 = 4; tipo3 = -1;
		}
		break;
		case 306 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		case 307 : {
			tipo1 = 4; tipo2 = -1; tipo3 = -1;
		}
		break;
		}
	}
	
    public void anyadir(int id, int idJug) {
        if(tipo1 == id || tipo2 == id || tipo3 == id) {
            actuales++;
        }else {
            actuales--;
        }
        //Busca la poscion en el array distinto -1 y añade la carta
        int i = 0;
        boolean anyadida = false;
        while(i < donaciones.length && !anyadida) {
            if(donaciones[i] == -1) {
                donaciones[i] = id;
                contribJug[idJug]++;
                anyadida = true;
            }
            i++;
        }
    }

    public boolean pasada() {
        if(cantidad <= actuales) {
            return true;
        }else {
            return false;
        }
    }

    public boolean sobra() {
        if((cantidad + 2) <= actuales) {
            return true;
        }else {
            return false;
        }
    }
	
	public int getId() {
		return id;
	}
	
	public int[] getDonaciones() {
		return donaciones;
	}
	
	public String getContribJug() {
		StringBuilder sB = new StringBuilder();
		for(int i=0;i<contribJug.length-1;i++) {
			sB.append(contribJug[i]+"|");
		}
		sB.append(contribJug[contribJug.length-1]);
		return sB.toString();
	}
}