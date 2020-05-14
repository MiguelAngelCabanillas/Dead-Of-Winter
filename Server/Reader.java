package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Reader implements Runnable {
private Socket socket;
private List<PrintWriter> ls;
	
	public Reader (Socket s, List<PrintWriter> ls) {
		socket = s;
		this.ls = ls;
	}
	
	public void run() {
		try {
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer = new BufferedReader(reader);
			
			while (true) {
			String mensaje = buffer.readLine();
			if(mensaje != null) {
				for(PrintWriter pw : ls) {
					pw.println(mensaje);
					pw.flush();
				}
				System.out.println(mensaje);
			} else {
				continue;
			}
		 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
