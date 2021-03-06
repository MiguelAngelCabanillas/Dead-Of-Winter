package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Partida.Principal;

public class Sala {
	private int id;
	List<Usuario> usuarios = new ArrayList<Usuario>();
	Usuario host;
	Principal partida;
	private List<Usuario> muted;
	private boolean puedeEntrar;
	private int contTurnos; //variable que indica los turnos restantes para que cambie la ronda
	
	public Sala(Usuario usuario, int id) {
		usuarios.add(usuario);
		host = usuario;
		this.muted = new ArrayList<>();
		this.id = id;
		puedeEntrar = true;
	}
	// Devuelve true si se ha a�adido el usuario y false si no se ha podido a�adir
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
		puedeEntrar = false;
	}
	
	public Principal getPartida() {
		return partida;
	}

	public void mutear(Usuario user) {
		muted.add(user);
	}
	
	public void desmutear(Usuario user) {
		muted.remove(user);
	}
	
	public List<Usuario> getMuteados(){
		return muted;
	}
	
	public void shuffleUsuarios(){
		Collections.shuffle(usuarios);
	}
	
	public void enviarAUsuariosDeLaSala(String peticion) throws IOException {
		for(Usuario usuario : usuarios) {
			usuario.hacerPeticionAlServidor(peticion);
		}
	}
	
	public int getContTurnos() {
		return contTurnos;
	}
	
	
	public void setContTurnos(int c) {
		contTurnos = c;
	}
	
	public boolean getPuedeEntrar() {
		return puedeEntrar;
	}
	
	public void setPuedeEntrar(boolean puede) {
		this.puedeEntrar = puede;
	}
}
