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
	boolean puedeEntrar;
	private int cuchillo; //variable que indica por donde va el cuchillo de la partida
	
	public Sala(Usuario usuario, int id) {
		usuarios.add(usuario);
		host = usuario;
		this.muted = new ArrayList<>();
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
	
	public int getCuchillo() {
		return cuchillo;
	}
	
	
	public void setCuchillo(int c) {
		cuchillo = c;
	}
}
