package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ClientReader implements Runnable {
<<<<<<< HEAD
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientReader (Socket s) throws IOException {
		socket = s;
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() {
		try {
			while (true) {
				String recibido = in.readLine();
				if(recibido == null) {
					continue;
				}
				System.out.println("Mensaje del servidor: "+recibido);
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

}
=======
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FrameSala sala;
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
                String[] splitedmsg = msgllegada.split("\\|");
                switch (splitedmsg[0]) {
                case "idsala":
                    sala.actIdSala(Integer.parseInt(splitedmsg[1]));
                    break;
                case "nusuarios":
                    sala.actNumJugadores(Integer.parseInt(splitedmsg[1]));
                    break;
                case "chat":
                    sala.actualizaChat(splitedmsg[1]);
                    break;
                case "exitsala":
                	try {
						acceso.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
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
    public Semaphore getSemaphore() {
    	return acceso;
    }

}
>>>>>>> bda90ce4c82f69ff17738fb2e910b1ff9a328a9f
