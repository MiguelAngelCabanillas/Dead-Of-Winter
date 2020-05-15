package Server;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Usuario{

	private String nombre;
	private Socket conexion;
	private ClientReader conector;
	
	public Usuario(String nombre, Socket conexion) {
		this.nombre = nombre;
		this.conexion = conexion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Socket getConexion() {
		return conexion;
	}

	public void setConexion(Socket conexion) {
		this.conexion = conexion;
	}
	
	public void hacerPeticionAlServidor(String peticion) throws IOException {
		if(conector == null) {
			conector = new ClientReader(conexion);
		}
		
		conector.hacerPeticionAlServidor(peticion);
	}
	
	public void desconectar() throws IOException {
		conexion.close();
	}
	
}
