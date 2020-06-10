package Server;

import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal {

	public static final String IP_SERVER = "25.53.178.73";

	public static final int PUERTO_SERVER = 12975;
	
	public ServidorPrincipal( ){
		try {
		ServerSocket server_socket = new ServerSocket(PUERTO_SERVER);	// Iniciamos el Socket que va a recibir las peticiones en el puerto 12975
		List<Socket> peticiones = new ArrayList<Socket>();	// Iniciamos la lista que va a tener los usuarios activos
		List<PrintWriter> envioAUsuarios = new ArrayList<PrintWriter>(); // Iniciamos la lista con todos los outputs de la conexion
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Sala> salas = new ArrayList<Sala>();
		
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
			
			System.out.println("Listo para recibir petici�n");
			Socket peticion = server_socket.accept();  // Cuando llega una peticion, se acepta y se crea como un Socket
			//peticion.setSoTimeout(30000);
			int j = 0;
			while(j < peticiones.size()){
				if(peticiones.get(j).getInetAddress().equals(peticion.getInetAddress())) {
				//envioAUsuarios.get(j).println("Error: Sesi�n duplicada");
				envioAUsuarios.get(j).close();
				envioAUsuarios.remove(j);
				peticiones.get(j).close();
				peticiones.remove(j);
				System.out.println("Error en la conexi�n. Otra instancia ha iniciado sesi�n con la misma IP.");
				j--;
				}
				j++;
			}
			j = 0;
			
	//		if(peticion.isClosed()) {
	//			continue;
	//		}
			
			peticiones.add(peticion); // Se a�ade la petici�n de conexi�n a la lista de usuarios activos
			
			// Mandar mensajes al cliente
			PrintWriter pw = new PrintWriter(peticion.getOutputStream(), true); // Crea el output de la conexi�n (para mandar mensajes al cliente)
			envioAUsuarios.add(pw); // Se a�ade a la lista el PrintWriter (para luego poder enviar output a todos)
			
			
			System.out.println("Servidor conectado a " + peticion.getInetAddress());
			//Recibir mensajes del cliente
			
			Reader reader = new Reader(peticion, envioAUsuarios, peticiones, usuarios, salas); // Crea un Reader (Clase que procesa los mensajes via servidor)
			String usuarioNombre = reader.recibirMensaje();
			System.out.println(usuarioNombre);
			Usuario usuario = new Usuario(usuarioNombre, new ClientReader(peticion));
			usuarios.add(usuario);
			reader.setUsuario(usuario);
			Thread thread = new Thread(reader); // Crea un hilo para ese reader espec�fico
			thread.start();
			}
		} catch(Exception e) { e.printStackTrace();}
		
	
}
	public static void main(String[] args) {
		ServidorPrincipal server = new ServidorPrincipal();
	}
}

