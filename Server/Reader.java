package Server;

import bd.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;

public class Reader implements Runnable {
private int i;
private Usuario user;
private List <Usuario> us;

private Socket socket;
private List<Socket> sl;
private List<PrintWriter> ls;

private Sala sala;
private List<Sala> salas;

private InputStreamReader reader;
private BufferedReader buffer;
	
	public Reader (Socket s, List<PrintWriter> ls, List<Socket> sl, List<Usuario> us, List<Sala> salas) throws IOException {
		socket = s;
		this.salas = salas;
		this.ls = ls;
		this.sl = sl;
		this.us = us;
		reader = new InputStreamReader(socket.getInputStream());
		buffer = new BufferedReader(reader);
	}
	
	public void setUsuario(Usuario user) {
		this.user = user;
	}
	
	public void run() {
		try {
			String[] split;
			String usuario;
			int idSala;
			while (true) {
			String mensaje = buffer.readLine();
			if(mensaje != null) {
				split = mensaje.split("\\|"); // Nombre|NumeroDeSala
				
				usuario = split[0];
				user = buscarUsuarioConectado(usuario);
				idSala = Integer.parseInt(split[1]);
				sala = buscarSala(idSala);
				if(sala == null){ //Si la sala no esta creada se crea
					sala = new Sala(user, idSala);
					user.setSala(sala);
				} else if(sala != null && user.getSala() == null){ //Si la sala esta creada y el jugador no pertenece a la sala
					user.setSala(sala);
					sala.anyadirUsuario(user);
				}
				
				switch(split[2]) { // Comando
				case "msg": // Nombre|NumeroDeSala|msg|"Mensaje"
					for(PrintWriter pw : ls ) {
						pw.println(user.getNombre() + ": " + split[3]);
					}
					System.out.println(split[3]);
					break;
				case "msgsala": // Nombre|NumeroDeSala|msgsala|"Mensaje"
					i = 0;
					for(PrintWriter pw: ls ) {
					  if(us.get(i).getSala() != null && user.getSala() != null) {
						if(us.get(i).getSala().getId() == user.getSala().getId()) {
							pw.println(user.getNombre() + ": " + split[3]);
						}
						i++;
					}
				}
					System.out.println(user.getNombre() + ": " + split[3]);
					break;
				case "null":
					break;
				}
				
				
			System.out.println("Usuario: " + usuario + ", " + "Sala: " + idSala);
			} else {
				break;
			}
		 }
			
			
			
			
			
			
			
			
			
			
			
		} catch (SocketException e1) {
			//e1.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			if(socket != null) {
				 int index = sl.indexOf(socket);
				 if(index > -1) {
				 sl.remove(index);
				 ls.remove(index);
				 us.remove(index);
				 System.out.println("Desconectado:" + socket.getInetAddress());
				
				 
				 //Comprobar el numero de conexiones activas
				 for(Socket s : sl) {
						System.out.println(s.getInetAddress());
					}
					int i = 0;
					for(PrintWriter pw1 : ls) {
						i++;
					} System.out.println(i);
				 
				 try {
					socket.close();
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				 }
			}
	}
}
	public String recibirMensaje() throws IOException {
		return buffer.readLine();
	}
	
	public Usuario buscarUsuarioConectado(String user) {
		for(Usuario use : us) {
			if(use.getNombre().equalsIgnoreCase(user)) {
				return use;
			}
		}
		return null;
	}
	
	public Sala buscarSala(int id) {
		for(Sala sala : salas) {
			if(sala.getId() == id) {
				return sala;
			}
		}
		return null;
	}
	
}
