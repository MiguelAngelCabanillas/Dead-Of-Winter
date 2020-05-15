package Server;

import bd.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;

public class Reader implements Runnable {
private Socket socket;
private List<PrintWriter> ls;
private List<Socket> sl;
	
	public Reader (Socket s, List<PrintWriter> ls, List<Socket> sl) {
		socket = s;
		this.ls = ls;
		this.sl = sl;
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
		} catch (SocketException
			    e1) {
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
		 }catch (IOException e) {
			e.printStackTrace();
		}
	}
}
