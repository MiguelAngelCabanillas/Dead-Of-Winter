package Server;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import Gui.*;


public class ClientReader implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FrameSala sala;

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
				case "chat":
					sala.actualizaChat(splitedmsg[1]);
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

}