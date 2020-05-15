package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private Socket socket;
	
	public Cliente() {
		try {
			System.out.println("Conectando al Servidor...");
			this.socket = new Socket("25.51.116.27", 12975);
			
			Thread thread = new Thread(new ClientReader(socket));
			thread.start();
		} catch (UnknownHostException err) {
			err.printStackTrace();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
}


