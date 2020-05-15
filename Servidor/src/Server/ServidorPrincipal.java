package Server;

import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal {
	{
	try {
	ServerSocket server_socket = new ServerSocket(12975);	// Iniciamos el Socket que va a recibir las peticiones en el puerto 12975
	List<Socket> peticiones = new ArrayList<Socket>();	// Iniciamos la lista que va a tener los usuarios activos
	List<PrintWriter> envioAUsuarios = new ArrayList<PrintWriter>(); // Iniciamos la lista con todos los outputs de la conexionç
	boolean encontrado = false;
	System.out.println("Servidor abierto"); 
	
	while(true) {
		
		for(Socket s : peticiones) {
			System.out.println(s.getInetAddress());
		}
		int i = 0;
		for(PrintWriter pw1 : envioAUsuarios) {
			i++;
		} System.out.println(i);
		
		System.out.println("Listo para recibir petición");
		Socket peticion = server_socket.accept();  // Cuando llega una peticion, se acepta y se crea como un Socket
		
		int j = 0;
		while(j < peticiones.size()){
			if(peticiones.get(j).getInetAddress().equals(peticion.getInetAddress())) {
				envioAUsuarios.get(j).println("Error: Sesión duplicada");
			envioAUsuarios.get(j).close();
			envioAUsuarios.remove(j);
			peticiones.get(j).close();
			peticiones.remove(j);
			j--;
			}
			j++;
		}
		j = 0;
		
//		if(peticion.isClosed()) {
//			continue;
//		}
		peticiones.add(peticion); // Se añade la petición de conexión a la lista de usuarios activos
		
		// Mandar mensajes al cliente
		PrintWriter pw = new PrintWriter(peticion.getOutputStream(), true); // Crea el output de la conexión (para mandar mensajes al cliente)
		envioAUsuarios.add(pw); // Se añade a la lista el PrintWriter (para luego poder enviar output a todos)
		pw.println("Conectado al Servidor"); // Se envia el mensaje al usuario de Conectado al Servidor 
		pw.flush();
		
		System.out.println("Servidor conectado a " + peticion.getInetAddress());
		//Recibir mensajes del cliente
		
		Reader reader = new Reader(peticion, envioAUsuarios, peticiones); // Crea un Reader (Clase que procesa los mensajes via servidor)
		Thread thread = new Thread(reader); // Crea un hilo para ese reader específico
		thread.start();
		}
	}
	
	catch(Exception e) { e.printStackTrace();}
	
	
}
	public static void main(String[] args) {
		ServidorPrincipal server = new ServidorPrincipal();
	}
}

