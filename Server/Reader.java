package Server;

import bd.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;

public class Reader implements Runnable {
private Socket socket;
private List<PrintWriter> ls;
private List<Socket> sl;
private InputStreamReader reader;
private BufferedReader buffer;
	
	public Reader (Socket s, List<PrintWriter> ls, List<Socket> sl) throws IOException {
		socket = s;
		this.ls = ls;
		this.sl = sl;
		reader = new InputStreamReader(socket.getInputStream());
		buffer = new BufferedReader(reader);
	}
	
	public void run() {
		try {
			while (true) {
			String mensaje = buffer.readLine();
			if(mensaje != null) {
				for(PrintWriter pw : ls) {
					pw.println(mensaje);
					pw.flush();
				}
				System.out.println(mensaje);
			} else {
				break;
			}
		 }
		} catch (SocketException e1) {
			//e1.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			if(socket != null) {
				 int index = sl.indexOf(socket);
				 if(index > -1) {
				 sl.remove(index);
				 ls.remove(index);
				 System.out.println("Desconectado:" + socket.getInetAddress());
				
				 
				 //Comprobar el numero de conexiones activas
				 for(Socket s : sl) {
						System.out.println(s.getInetAddress());
					}
					int i = 0;
					for(PrintWriter pw1 : ls) {
						i++;
					} System.out.println(i);
				 
				 try {
					socket.close();
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				 }
			}
	}
}
	public String recibirMensaje() throws IOException {
		return buffer.readLine();
	}
}
