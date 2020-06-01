package Server;

import BD.*;
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
				if(idSala == -1) {
					idSala = getIdSalaLibre();
					
				}
				sala = buscarSala(idSala);
				if(sala == null){ //Si la sala no esta creada se crea
					sala = new Sala(user, idSala);
					user.setSala(sala);
					salas.add(sala);
					System.out.println("Creada sala " + idSala + " con " + user.getNombre() + " como host");
					sala.enviarAUsuariosDeLaSala("nusuarios|" + sala.getUsuarios().size());
					user.hacerPeticionAlServidor("chat|" + user.getNombre() + " ha entrado a la sala.");
				} else if(sala != null && user.getSala() == null){ //Si la sala esta creada y el jugador no pertenece a la sala
//					user.hacerPeticionAlServidor("nusuarios|" + sala.getUsuarios().size());
//					user.hacerPeticionAlServidor("id|" + sala.getId());
					user.setSala(sala);
					sala.anyadirUsuario(user);
					
					user.getSala().enviarAUsuariosDeLaSala("nusuarios|" + user.getSala().getUsuarios().size());
					System.out.println("Enviado numero de usuarios de sala a " + user.getNombre() + " porque se acaba de unir.");
					user.getSala().enviarAUsuariosDeLaSala("chat|" + user.getNombre() + " ha entrado a la sala.");
					System.out.println("Enviado que acaba de entrar a " + user.getNombre() + " porque se acaba de unir");
				}
				
				if(split.length >= 3) {
				switch(split[2]) { // Comando
				case "msg": // Nombre|NumeroDeSala|msg|"Mensaje"
					for(PrintWriter pw : ls ) {
						pw.println(user.getNombre() + ": " + split[3]);
					}
					System.out.println(split[3]);
					break;
				case "msgsala": // Nombre|NumeroDeSala|msgsala|"Mensaje"
					user.enviarALaSala("chat|" + user.getNombre() + ": " + split[3]);
					System.out.println("Enviado mensaje a " + user.getNombre());
					System.out.println(user.getNombre() + ": " + split[3].trim());
					break;
//					i = 0;
//					for(PrintWriter pw: ls ) {
//					  if(us.get(i).getSala() != null && user.getSala() != null) {
//						if(us.get(i).getSala().getId() == user.getSala().getId()) {
//							pw.println(user.getNombre() + ": " + split[3]);
//						}
//						i++;
//					  }
				case "exit":	 // usuario|sala|exit|tablero|objetivo o usuario|sala|exit|sala
					  if(split[3].equalsIgnoreCase("sala")) {
						 salirDeSala(user, user.getSala());
					  } else if(split[3].equalsIgnoreCase("tablero")) {
						  user.enviarALaSala("exit|tablero|" + split[4]);
						  
					  }
					 break;
				case "host":
					if(user.getSala().getHost().equals(user)) {
						user.hacerPeticionAlServidor("host|");
					}
					break;
					
				case "idsala":
					 user.hacerPeticionAlServidor("idsala|" + user.getSala().getId());
					 System.out.println("Enviado id de sala a " + user.getNombre());
					 break;
				case "nusuarios":
					user.hacerPeticionAlServidor("nusuarios|" + user.getSala().getUsuarios().size());
					System.out.println("Enviado numero de usuarios de sala a " + user.getNombre());
				}
			 }
			if(user.getSala()!=null) {
			System.out.println("Usuario: " + usuario + ", " + "Sala: " + user.getSala().getId());
			} else {
			System.out.println("Usuario: " + usuario + ", " + "Sala: null");
			}
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
				 if(us.get(index).getSala() != null) {
				 try {
					salirDeSala(us.get(index), us.get(index).getSala());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
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
	
	public int getIdSalaLibre() { // Todo: hacer que la lista este ordenada o F	
	 if(!salas.isEmpty()) {
		int anterior = salas.get(0).getId();
		if(anterior == 1) {
		for(Sala sala : salas) {
			if(sala.getId() - anterior > 1) {
					return anterior + 1;
			}
			anterior = sala.getId();
		}
	  return anterior + 1;
	  } 
	 }
	 return 1;
//	if(salas.size() > 0) {
//	 for(int i = 0; i < salas.size(); i++) {
//		 if(salas.get(i).getId() != (i + 1)) {
//			 return i + 1; // 0 es 1, 1 es 2, 2 es 3. Size seria 3, hemos llegado a la ultima, entonces seria size + 1 = 4
//		 }
//	 }
//	 return salas.get(salas.size()+1).getId();
//	} 
//	return 1;
	}
	
	public void salirDeSala(Usuario usuario, Sala sala) throws IOException {
	if(sala.getUsuarios().size() == 1) {
		sala.getUsuarios().remove(0);
		salas.remove(sala);
		usuario.setSala(null);
		System.out.println("La sala " + sala.getId() + " ha sido borrada");
	} else if (sala.getUsuarios().size() > 1 && sala.getHost().getNombre().equals(usuario.getNombre())){
		sala.getUsuarios().remove(usuario);
		usuario.enviarALaSala("chat|El host de la sala " + sala.getId() + " ha cambiado de " + usuario.getNombre() + " a " + sala.getUsuarios().get(0).getNombre());
		sala.enviarAUsuariosDeLaSala("nusuarios|" + sala.getUsuarios().size());
		usuario.setSala(null);
		sala.setHost(sala.getUsuarios().get(0));
		
		System.out.println("El host de la sala " + sala.getId() + " ha cambiado de " + usuario.getNombre() + " a " + sala.getUsuarios().get(0).getNombre());
	} else {
		sala.getUsuarios().remove(usuario);
		usuario.enviarALaSala("chat|" + usuario.getNombre() + " ha salido de la sala " + sala.getId());
		sala.enviarAUsuariosDeLaSala("nusuarios|" + sala.getUsuarios().size());
		usuario.setSala(null);
	}
	System.out.println(usuario.getNombre() + " ha salido de la sala " + sala.getId());
  }
}
