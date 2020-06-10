package Server;

import BD.*;
import Partida.Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Reader implements Runnable {

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
				if(split[1].length() > 10) {
					continue;
				}
					idSala = Integer.parseInt(split[1]);
				if(idSala == -1) {
					idSala = getIdSalaLibre();
					
				}
				
				
				sala = buscarSala(idSala);
				if(sala == null && user.getSala() == null){ //Si la sala no esta creada y el usuario no esta en ninguna se crea
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
				 if(user.getSala() != null) {
					 if(!user.getSala().getMuteados().contains(user)) {
						if(split[3].equalsIgnoreCase("gg ez")) {
							Random rand = new Random();
							String msgTonto = "";
							switch(rand.nextInt(11)) {
							case 0: msgTonto = "¡Mamá no quiero irme a dormir todavia! Ups, chat equivocado.";
									break;
							case 1: msgTonto = "¡Ha sido una partida maravillosa! ¡Amor para todos!";
									break;
							case 2: msgTonto = "Me gusta meterme el dedo en la nariz.";
									break;
							case 3: msgTonto = "Hacer pipi en la ducha es maravilloso.";
									break;
							case 4: msgTonto = "Yo... Te amo, cásate conmigo porfa...";
									break;
							case 5: msgTonto = "Mi mamá dice que la gente de mi edad no debería chuparse el dedo.";
									break;
							case 6: msgTonto = "Soy muy, muy pequeño...Abrazadme...";
									break;
							case 7: msgTonto = "Ahora mismo estoy intentando superar un problemilla de inseguridad, pero gracias a todos por jugar conmigo.";
									break;
							case 8: msgTonto = "¡Por el honor y la gloria! ¡Hurra, compañeros!";
									break;
							case 9: msgTonto = "Ya debería estar en la cama, no se lo digáis a mi mamá.";
									break;
							case 10: msgTonto ="Os deseo lo mejor.";
									break;
							}
							System.out.println(user.getNombre() + ": " + msgTonto);
							user.enviarALaSala("chat|" + user.getNombre() + ": " + msgTonto);
							break;
						} else if(split[3].charAt(0) == '/') { // Comandos
							String[] splitSplit = split[3].split(" ");
							switch(splitSplit[0].substring(1)) {
							case "secreto" : 
								if(splitSplit[1] != null) {user.hacerPeticionAlServidor("secreto|" + splitSplit[1]);
									System.out.println(user.getNombre() + " ha cambiado su objetivo secreto a " + splitSplit[1]);
								}
									break;
							case "mute" : 
								if(user.getSala().getHost().equals(user)) {
								if(splitSplit[1] != null) {
									Usuario usuarioMute = buscarUsuarioConectado(splitSplit[1]);
									if(usuarioMute != null) {
										if(user.getSala().getId() == usuarioMute.getSala().getId()) {
										user.getSala().mutear(usuarioMute);
										System.out.println(splitSplit[1] + " ha sido muteado " + splitSplit[1]);
										user.enviarALaSala("chat|" + splitSplit[1] + " ha sido muteado " + splitSplit[1]);
										}
									}
								}
							} else {
								user.hacerPeticionAlServidor("chat|Solo el host puede realizar esta acción");
							}
							break;
							case "unmute":
								if(user.getSala().getHost().equals(user)) {
								if(splitSplit[1] != null) {
									Usuario usuarioMute = buscarUsuarioConectado(splitSplit[1]);
									if(usuarioMute != null) {
										if(user.getSala().getId() == usuarioMute.getSala().getId()) {
										user.getSala().desmutear(usuarioMute);
										System.out.println(splitSplit[1] + " ha sido desmuteado.");
										user.enviarALaSala("chat|" + splitSplit[1] + " ha sido desmuteado.");
										}
									}
								}
							} else {
								user.hacerPeticionAlServidor("chat|Solo el host puede realizar esta acción");
							}
							break;
							case "kick":
								if(user.getSala().getHost().equals(user)) {
								if(splitSplit[1] != null) {
									Usuario usuarioKick = buscarUsuarioConectado(splitSplit[1]);
									if(usuarioKick != null) {
										if(usuarioKick != null) {
										if(user.getSala().getId() == usuarioKick.getSala().getId()) {
										usuarioKick.hacerPeticionAlServidor("exit|sala");
										System.out.println(splitSplit[1] + " ha sido kickeado.");
										user.enviarALaSala("chat|" + splitSplit[1] + " ha sido kickeado.");
										}
										}
									}
							}
							} else {
								user.hacerPeticionAlServidor("chat|Solo el host puede realizar esta acción");
							}
							break;
							case "list":
								if(user.getSala() != null) {
								String a = "[";
								for(int i = 0; i < user.getSala().getUsuarios().size()-1; i++) {
									a += user.getSala().getUsuarios().get(i).getNombre() + ", ";
								}
								a += user.getSala().getUsuarios().get(user.getSala().getUsuarios().size()-1).getNombre() + "]";
								user.hacerPeticionAlServidor("chat|" + a);
								System.out.println(a);
								}
							break;
							default:
								user.hacerPeticionAlServidor("\nchat|Comando incorrecto. /mute, /unmute, /kick");
//								user.hacerPeticionAlServidor("chat|/unmute jugador --> Desmutea al jugador proporcionado");
//								//user.hacerPeticionAlServidor("chat|/secreto id --> Te pone el objetivo secreto id");
//								user.hacerPeticionAlServidor("chat|/list --> Muestra los usuarios conectados");
//								user.hacerPeticionAlServidor("chat|----------------------------------------------");
							
							}
							break;
						}
					user.enviarALaSala("chat|" + user.getNombre() + ": " + split[3]);
					System.out.println("Enviado mensaje a " + user.getNombre());
					System.out.println(user.getNombre() + ": " + split[3].trim());
					}
				 }
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
					  } else if(split[3].equalsIgnoreCase("tablero")) { // Inicializar partida
						 
						  String mensInit = "initSup";
						  String mensIds = "ids";
					   if(user.getSala().getHost().getNombre().equals(user.getNombre())) {
						  user.getSala().setPartida(new Principal(Integer.parseInt(split[4])));
						  user.getSala().getPartida().inicPartida(user.getSala().getUsuarios().size());
						  List<Integer> objetivosSecretos = new ArrayList<>(); 
						  for(int i = 200; i < 213; i++) {
							  objetivosSecretos.add(i);
						  }
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  /// Objetivos secretos
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  
						  Random rand = new Random();
						  int n;
						  for(int i = 0; i < user.getSala().getUsuarios().size(); i++){
							  n = rand.nextInt(12-i);
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("secreto|" + objetivosSecretos.get(n));
							  System.out.println("Enviado al usuario " + user.getSala().getUsuarios().get(i).getNombre() + " el objetivo secreto " + objetivosSecretos.get(n));
							  objetivosSecretos.remove(n);
						  }
						  
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  /// Supervivientes
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  
						  List<Integer> sups = new ArrayList<>();
						  for(int i = 100; i < 125; i++) {
							  sups.add(i);
						  }
						  
						  Collections.shuffle(sups);
						  
						  
						  
						  user.getSala().shuffleUsuarios();
						  for(int i = 0; i < (user.getSala().getUsuarios().size()*2); i++) {
							  mensInit += "|" + sups.get(i);
							  
							  if(i%2 == 0) {
								  mensIds += "|" + user.getSala().getUsuarios().get(i/2).getNombre(); // ids|FranMJ|Umbra|zunk|NiemaJ|Miguel1995
							  }
							 
							//  user.getSala().getUsuarios().get((int)i/2).getJugador().addSuperviviente(sups.get(i));
							  System.out.println((int)i/2 + ", " + sups.get(i));
						  }
						  System.out.println(mensIds);
					   }
					   
					   user.enviarALaSala("exit|tablero|" + split[4]);
					   
					   if(user.getSala().getHost().getNombre().equals(user.getNombre())) {					   
						  for(int i = 0; i < user.getSala().getUsuarios().size(); i++) {  
						//	  user.setJugador(user.getSala().getPartida().getJugador(i)); // Se asigna un jugador para cada usuario
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor(mensIds);
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor(mensInit + "|" + i);
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("initCartas|" + user.getSala().getPartida().getIdCartas(i));
							  //ENVIA LOS DADOS A CADA JUGADOR//
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("newRound|" + 1/*user.getSala().getPartida().getRondasRestantes()*/ + "|" + user.getSala().getPartida().getDados(i));
							  
							  System.out.println(mensInit);
							  System.out.println("initCartas|" + user.getSala().getPartida().getIdCartas(i));
							  System.out.println("newRound|" + 1/*user.getSala().getPartida().getRondasRestantes()*/ + "|" + user.getSala().getPartida().getDados(i));
						  }
					   }
						  
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  /// Cartas iniciales
						  //////////////////////////////////////////////////////////////////////////////////////////////////
						  
						  
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
