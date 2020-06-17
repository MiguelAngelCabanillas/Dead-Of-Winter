package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static Socket socket;
	
	public Cliente() {
		
	}
	
	
	public Socket getSocket() {
		return socket;
	}

public static void main(String[] args) {
	while(true) {
	try {
		conectar();

		
	} catch (UnknownHostException err) {
		err.printStackTrace();
	} catch (IOException e ) {
		try {
			conectar();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

public static void conectar() throws UnknownHostException, IOException, InterruptedException {
	System.out.println("Conectando al Servidor...");
	Socket socket = new Socket("25.51.116.27", 12975);
	ClientReader cr;
	
	String a = "a";
	while(true) {
		cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");cr = new ClientReader(new Socket("25.51.116.27", 12975));
		cr.hacerPeticionAlServidor(a + "a");
	    cr = new ClientReader(socket);
		a = a + "a";
		cr.hacerPeticionAlServidor(a + "|-1");
		
		
	}
}
}


