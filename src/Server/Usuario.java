package Server;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Partida.Jugador;

public class Usuario{

	private String nombre;
	private ClientReader conector;
	private Sala sala;
	private Jugador jugador;
	private Clip musica;
	AudioInputStream musicInputStream;
	
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
	
	public void activarMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		activarMusica("music/intro.wav");
	}
	
	public void activarMusica(String ruta) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(!musica.isOpen()) {
			musicInputStream = AudioSystem.getAudioInputStream(new File(ruta).getAbsoluteFile());
			musica= AudioSystem.getClip();
			musica.open(musicInputStream);
		}
		musica.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void quitarMusica() { 
		musica.close();
	}
	
	public void pararMusica() {
		if(musica.isActive()) {
			musica.stop();
		} else {
			musica.start();
		}
	}
	
	public void setMusica(Clip musica) {
		this.musica = musica;
	}
}
