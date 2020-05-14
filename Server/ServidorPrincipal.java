package Server;

import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal {
	{
	try {
	ServerSocket server_socket = new ServerSocket(12975);
	List<Socket> peticiones = new ArrayList<Socket>();
	List<PrintWriter> envioAUsuarios = new ArrayList<PrintWriter>();
	System.out.println("Servidor abierto");
	
	while(true) {
		System.out.println("Listo para recibir petición");
		Socket peticion = server_socket.accept();
		peticiones.add(peticion);
		
		// Mandar mensajes al cliente
		PrintWriter pw = new PrintWriter(peticion.getOutputStream(), true);
		envioAUsuarios.add(pw);
		pw.println("Conectado al Servidor");
		pw.flush();
		
		System.out.println("Servidor conectado a " + peticion.getInetAddress());
		//Recibir mensajes del cliente
		
		Reader reader = new Reader(peticion, envioAUsuarios);
		Thread thread = new Thread(reader);
		thread.start();
		
	}
	
	
	}catch(Exception e) { e.printStackTrace();}
	
	
}
	public static void main(String[] args) {
		ServidorPrincipal server = new ServidorPrincipal();
	}
}

