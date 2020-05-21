package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sala {
	private int id;
	List<Usuario> usuarios = new ArrayList<Usuario>();
	Usuario host;
	String passw;
	
	public Sala(Usuario usuario, int id) {
		usuarios.add(usuario);
		host = usuario;
		this.id = id;
	}
	
	public void anyadirUsuario(Usuario usuario) {
		usuarios.add(usuario);
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

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public void enviarAUsuariosDeLaSala(String peticion) throws IOException {
		for(Usuario usuario : usuarios) {
			
		}
	}
	
	
}
