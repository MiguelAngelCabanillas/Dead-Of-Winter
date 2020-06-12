package Server;

import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal {

	public static final String IP_SERVER = "25.51.116.27";

	public static final int PUERTO_SERVER = 12975;
	
	public ServidorPrincipal( ){
		try {
		ServerSocket server_socket = new ServerSocket(PUERTO_SERVER);	// Iniciamos el Socket que va a recibir las peticiones en el puerto 12975
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Sala> salas = new ArrayList<Sala>();
		
		boolean encontrado = false;
		System.out.println("Servidor abierto"); 
		
		while(true) {
			System.out.println("Listo para recibir petición");
			System.out.println(usuarios.size());
			Socket peticion = server_socket.accept();  // Cuando llega una peticion, se acepta y se crea como un Socket
			//peticion.setSoTimeout(30000);
			
			System.out.println("Servidor conectado a " + peticion.getInetAddress());
			//Recibir mensajes del cliente
			
			Reader reader = new Reader(usuarios, salas); // Crea un Reader (Clase que procesa los mensajes via servidor)
			Usuario usuario = new Usuario("", new ClientReader(peticion));
			reader.setUsuario(usuario);
			String nombre = reader.recibirMensaje();
			
			for(Usuario user : usuarios){
				if(user.getClientReader().getSocket().getInetAddress().equals(peticion.getInetAddress()) || user.getNombre().equalsIgnoreCase(nombre)) {
				usuario.getClientReader().close();
				System.out.println("Error en la conexión. Otra instancia ha iniciado sesión con la misma IP.");
				continue;
				}
			}
			
			usuario.setNombre(nombre); 
			System.out.println(usuario.getNombre());
			
			usuarios.add(usuario);
			
			Thread thread = new Thread(reader); // Crea un hilo para ese reader específico
			thread.start();
			}
		} catch(Exception e) {e.printStackTrace();}}
	
	public static void main(String[] args) {
		ServidorPrincipal server = new ServidorPrincipal();
	}
}

