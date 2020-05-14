package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientReader implements Runnable {
	private Socket socket;
	private PrintWriter out;
	
	public ClientReader (Socket s) throws IOException {
		socket = s;
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

}
