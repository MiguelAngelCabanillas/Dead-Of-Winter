package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Partida.Principal;

public class Sala {
	private int id;
	List<Usuario> usuarios = new ArrayList<Usuario>();
	Usuario host;
	Principal partida;
	boolean puedeEntrar;
	
	public Sala(Usuario usuario, int id) {
		usuarios.add(usuario);
		host = usuario;
		this.id = id;
		puedeEntrar = true;
	}
	// Devuelve true si se ha añadido el usuario y false si no se ha podido añadir
	public boolean anyadirUsuario(Usuario usuario) {
		if(puedeEntrar) {
		usuarios.add(usuario);
			if(usuarios.size() == 5) {
				puedeEntrar = false;
			}
			return true;
		} 
		return false;
	}
	
	public void iniciarPartida() throws IOException {
		host.hacerPeticionAlServidor(host.getNombre() + ": He empezado la partida numero" + id);
	}
	
	public Usuario getHost() {
		return host;
	}
	
	public void setHost(Usuario host) {
		this.host = host;
	}

	public int getId() {
		return id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setPartida(Principal p) {
		this.partida = p;
	}

	
	public void enviarAUsuariosDeLaSala(String peticion) throws IOException {
		for(Usuario usuario : usuarios) {
			usuario.hacerPeticionAlServidor(peticion);
		}
	}
	
	
}
