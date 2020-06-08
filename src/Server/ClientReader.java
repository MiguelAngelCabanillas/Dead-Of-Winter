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
                    sala.actIdSala(Integer.parseInt(split[1]));
                    break;
                case "nusuarios":
                    sala.actNumJugadores(Integer.parseInt(split[1]));
                    break;
                case "chat":
                	if(sala != null) {
                    sala.actualizaChat(split[1]);
                	} else {
                	tablero.actualizaChat(split[1]);
                	}
                    break;
                case "exit":
                	try {
						if(split[1] != null) { // Inicializar
							
							
							sala.avanzarATablero(Integer.parseInt(split[2]));
						}
						acceso.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                	break;
                case "host" :
                	sala.setIsHost(true);
                	break;
                case "asignar": // asignar|id|id|id|id...
                	/*int i = 1;
                	while(split[i] != null) {
                	 principal.asignarpersonajespolla();
                	}*/
                	break;
                	
                case "mover": // mover|superviviente|origen|destino --- Superviviente es una ID única para cada tipo de superviviente
                	

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
