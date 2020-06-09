package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import Gui.FrameSala;
import Gui.FrameTablero;

public class ClientReader implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FrameSala sala;
    private FrameTablero tablero;
    private Semaphore acceso = new Semaphore(0);
    private int idJug;

    public ClientReader (Socket s) throws IOException {
        socket = s;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            while (true) {
                String msgllegada = in.readLine();
                if(msgllegada == null) {
                    continue;
                }
                String[] split = msgllegada.split("\\|");
                switch (split[0]) {
                
                case "idsala":
                    if(sala != null) sala.actIdSala(Integer.parseInt(split[1]));
                    break;
                    
                case "nusuarios":
                    if(sala != null) sala.actNumJugadores(Integer.parseInt(split[1]));
                    break;
                    
                case "chat":
                	if(split[1] != null)
	                	if(sala != null) {
	                    sala.actualizaChat(split[1]);
	                	} else  if (tablero != null){
	                	tablero.actualizaChat(split[1]);
	                	}
                    break;
                    
                case "exit":
                	try {
						if(split[1].equals("tablero")) { // Inicializar
							
							
							sala.avanzarATablero(Integer.parseInt(split[2]));
						} else {
							sala.salirSala();
							sala = null;
						}
						acceso.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                	break;
                	
                case "host" :
                	sala.setIsHost(true);
                	break;
                	
                case "asignar": // asignar|idJug|idSup|posValCol|idSup|posValCol|idSup|posValCol...
                	int i = 2;
                	idJug = Integer.parseInt(split[1]);
                	int idSup,posValCol;
                	while(split[i] != null) {
                		idSup = Integer.parseInt(split[i]);
                		posValCol = Integer.parseInt(split[i+1]);
                		tablero.addSupJug(idJug,idSup); //a�adimos superviviente a la lista del jugador al que se le asigna
                		tablero.anyadirSuperviviente(idSup, posValCol); //a�adimos superviviente al tablero
                		i = i+2;
                	}
                	break;
                	
                case "mover": // mover|superviviente|destino|posicionValida --- Superviviente es una ID �nica para cada tipo de superviviente
                	System.out.println("Mover superviviente " + split[1] + " a la localizacion " + split[2] + ", posicion " + split[3]);
                	tablero.moverSuperviviente(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                	break;
                	
                case "secreto": // secreto|id -- ID �nica para cada objetivo secreto
                	System.out.println("Objetivo Secreto: " + split[1]);
                	FrameTablero.setObjetivoSecreto(Integer.parseInt(split[1]));
                	break;
                	
                case "initSup": //initSup|sup1Jug1|sup2Jug1|sup1Jug2|sup2Jug2|...
                	int sup1,sup2;
                	int pos = 0;
                	for(int id = 0; id < split.length/2 - 1; id++) {
                		sup1 = Integer.parseInt(split[2*id+1]);
                		sup2 = Integer.parseInt(split[2*id+2]);
                		System.out.println("Jugador " + id +": " + sup1 + " " + sup2);
                		//IMPRESION EN JFRAME
                		tablero.anyadirSuperviviente(sup1, pos);pos++;
                		tablero.anyadirSuperviviente(sup2, pos);pos++;
                		//A�ADIMOS SUPERVIVIENTES A LA LISTA
                		tablero.addSupJug(id,sup1);
                		tablero.addSupJug(id,sup2);
                	}
                	break;
                	
                case "initCartas": //initCartas|idJug|idCarta|idCarta|idCarta|idCarta....
                	int j = 2, idCarta;
                	idJug = Integer.parseInt(split[1]);
                	while(split[j] != null) {
                		idCarta = Integer.parseInt(split[j]);
                		tablero.addCartaJug(idJug,idCarta); //a�adimos cartas a la lista del jugador al que se le asigna
                		j++;
                	}
                	break;
                	
                default:
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void hacerPeticionAlServidor(String peticion) throws IOException {
        out.println(peticion);
        out.flush();
    }

    public String recibirMensaje() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
    public void setSala(FrameSala sala) {
        this.sala = sala;
    }
    
    public void setTablero(FrameTablero tablero) {
    	this.tablero = tablero;
    }
    
    public Semaphore getSemaphore() {
    	return acceso;
    }

}
