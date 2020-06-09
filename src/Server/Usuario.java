package Server;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import Partida.Jugador;

public class Usuario{

	private String nombre;
	private ClientReader conector;
	private Sala sala;
	private Jugador jugador;
	
	public Usuario(String nombre, ClientReader conector) {
		this.nombre = nombre;
		this.conector = conector;
	}

	public String getNombre() {
		return nombre;
	}

	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public ClientReader getClientReader() {
		return conector;
	}
	
	public void hacerPeticionAlServidor(String peticion) throws IOException {
		conector.hacerPeticionAlServidor(peticion);
	}
	
	public String recibirMensajeDelServidor() throws IOException {
		return conector.recibirMensaje();
	}
	
	public void enviarALaSala(String peticion) throws IOException {
		sala.enviarAUsuariosDeLaSala(peticion);
	}
}
