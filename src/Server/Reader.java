package Server;

import BD.*;
import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;
import Excepciones.ServerException;
import Partida.BarricadaException;
import Partida.BuscarException;
import Partida.DadoException;
import Partida.HabilidadException;
import Partida.MatarException;
import Partida.Principal;
import Partida.VertederoException;

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
private int id;
private List <Usuario> us;

private Sala sala;
private List<Sala> salas;

private InputStreamReader reader;
private BufferedReader buffer;


	
	public Reader (List<Usuario> us, List<Sala> salas) throws IOException {
		this.salas = salas;
		this.us = us;

	}
	
	public void setUsuario(Usuario user) throws IOException {
		this.user = user;
		reader = new InputStreamReader(user.getClientReader().getSocket().getInputStream());
		buffer = new BufferedReader(reader);
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
					sala.enviarAUsuariosDeLaSala("nusuarios|Numero de jugadores: " + user.getSala().getUsuarios().size());
					user.hacerPeticionAlServidor("chat|" + user.getNombre() + " ha entrado a la sala.");
				} else if(sala != null && user.getSala() == null){ //Si la sala esta creada y el jugador no pertenece a la sala
//					user.hacerPeticionAlServidor("nusuarios|" + sala.getUsuarios().size());
//					user.hacerPeticionAlServidor("id|" + sala.getId());
					if(sala.getPuedeEntrar() == false) {
						user.hacerPeticionAlServidor("nusuarios|Error: Sala llena");
					} else {
					user.setSala(sala);
					sala.anyadirUsuario(user);
					//
					user.getSala().enviarAUsuariosDeLaSala("nusuarios|Numero de jugadores: " + user.getSala().getUsuarios().size());
					System.out.println("Enviado numero de usuarios de sala a " + user.getNombre() + " porque se acaba de unir.");
					user.getSala().enviarAUsuariosDeLaSala("chat|" + user.getNombre() + " ha entrado a la sala.");
					System.out.println("Enviado que acaba de entrar a " + user.getNombre() + " porque se acaba de unir");
					}
				}
				
				if(split.length >= 3) {
				switch(split[2]) { // Comando
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
							case "addSup":
								if(user.getSala() != null) {
								if(user.getJugador().getSupConId(Integer.parseInt(splitSplit[1])) == null) {
									user.getSala().getPartida().addSuperviviente(user.getJugador().getId(), Integer.parseInt(splitSplit[1]));
									user.hacerPeticionAlServidor("asignar|" + user.getJugador().getId() + "|" + splitSplit[1] + "|10");
									user.hacerPeticionAlServidor("chat|Añadido superviviente solicitado: " + splitSplit[1]);
								}
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
						  if(user.getSala() != null) {
						  /*if(user.getSala().getUsuarios().size() < 2) {
							  user.hacerPeticionAlServidor("error|Hacen falta al menos dos jugadores para iniciar la partida");
							  break;
						  }*/
						  
						  String mensInit = "initSup";
						  String mensIds = "ids";
					   if(user.getSala().getHost().getNombre().equals(user.getNombre())) {
						  user.getSala().setPartida(new Principal(Integer.parseInt(split[4])));
						  user.getSala().setContTurnos(user.getSala().getUsuarios().size());
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
						  for(int i = 100; i < 131; i++) {
							  sups.add(i);
						  }
						  
						  Collections.shuffle(sups);
						  
						  
						  
						  user.getSala().shuffleUsuarios();
						  for(int i = 0; i < (user.getSala().getUsuarios().size()*2); i++) {
							  mensInit += "|" + sups.get(i);
							  
							  
							  if(i%2 == 0) {
								  mensIds += "|" + user.getSala().getUsuarios().get(i/2).getNombre(); // ids|FranMJ|Umbra|zunk|NiemaJ|Miguel1995
								  user.getSala().getUsuarios().get((int)i/2).setJugador(user.getSala().getPartida().getJugador((int)i/2)); // Se asigna un jugador para cada usuario
								  
							  }
							  
							  user.getSala().getPartida().addSuperviviente((int)i/2, sups.get(i));
							 
							//  user.getSala().getUsuarios().get((int)i/2).getJugador().addSuperviviente(sups.get(i));
							  System.out.println((int)i/2 + ", " + sups.get(i));
						  }
						  user.getSala().getPartida().inicSupervivientesEnColonia();
						  user.getSala().getPartida().inicDados();
						  System.out.println(mensIds);
						  
						   user.enviarALaSala("exit|tablero|" + split[4]);
						   
						  for(int i = 0; i < user.getSala().getUsuarios().size(); i++) {  
							  
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor(mensIds);
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor(mensInit + "|" + i);
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("initCartas|" + user.getSala().getPartida().getIdCartas(i));
							  //ENVIA LOS DADOS A CADA JUGADOR//

							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("newRound|" + user.getSala().getPartida().getRondasRestantes() + "|" + user.getSala().getPartida().getCrisisActualId() + "|" + user.getSala().getPartida().getDados(i));
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("moral|" + user.getSala().getPartida().getMoral());
							  user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("vertedero|" + user.getSala().getPartida().getVertedero());
							  
							  for(Usuario usu : user.getSala().getUsuarios()){
								  System.out.println(user.getJugador().getMazoSuperviviente().size() + " " + user.getNombre());
							  }
							  System.out.println(mensInit);
							  
							  
							  
							  System.out.println("initCartas|" + user.getSala().getPartida().getIdCartas(i));
							  System.out.println("newRound|" + user.getSala().getPartida().getRondasRestantes() + "|" + user.getSala().getPartida().getCrisisActualId() + "|" + user.getSala().getPartida().getDados(i));
						  }
						  id = user.getJugador().getId();
						  user.getSala().getUsuarios().get(0).hacerPeticionAlServidor("tuturno");
						  user.enviarALaSala("chat|Turno de " + user.getSala().getUsuarios().get(0).getNombre());
						  
					   }
						  }
					}
					 break;
				case "finturno": //finturno|idJugAnterior
					pasarTurnoORonda(Integer.parseInt(split[3]));
					break;
				case "mover":
					try {
						String comando = user.getSala().getPartida().mover(Integer.parseInt(split[3]), Integer.parseInt(split[4])); 
						String[] splitsplit = comando.split("\\|");
						System.out.println(comando);
						System.out.println(splitsplit[3]);
						switch(splitsplit[3]) {
						case "0": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(splitsplit[0])) + " no ha recibido heridas" );
							break;
						case "1": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(splitsplit[0])) + " ha recibido una herida normal" );
									user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
								break;
						case "2": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(splitsplit[0])) + " ha recibido una herida por congelación" );
									user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
							break;
						case "3": user.enviarALaSala("chat|[" + user.getNombre() + "] "  + user.getSala().getPartida().getNombre(Integer.parseInt(splitsplit[0])) + " ha muerto..." );
									user.enviarALaSala("rmSup|" + user.getJugador().getId() + "|" + splitsplit[0]);
									System.out.println(user.getNombre() + " ha llamado esta función");
									int moral = user.getSala().getPartida().getMoral();
									if( moral <= 0 ) {
										user.enviarALaSala("finpartida");
									} else {
										user.enviarALaSala("moral|" +  user.getSala().getPartida().getMoral());
									}
							break;
						}
						
						user.enviarALaSala("mover|"+ comando); //mover|idSup|idloc|posic|dadoRiesgo
					// sup, loc, cas, dado
					} catch(MoverException e){
						try {
							user.hacerPeticionAlServidor("error|" + e.getMessage() );
							
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
						
					
					break;
				case "barricada":
					try {
						System.out.println("barricada|"+split[3]);
						String com;

						com = user.getSala().getPartida().ponerBarricada(Integer.parseInt(split[3]));
						System.out.println("Valor devuelto por barricada " + com);
						String[] spl = com.split("\\|"); //loc|pos|dadoaquitar
						if(spl[0].equals("null") || spl[1].equals("null")) {
							user.hacerPeticionAlServidor("error|No puedes poner esa barricada");
						} else {
							user.enviarALaSala("addBarricada|" + spl[0] + "|" + spl[1]);
							if(Integer.parseInt(spl[2]) != -1) {
								user.hacerPeticionAlServidor("rmDado|" + spl[2]);
							}
							user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha puesto una barricada.");
						}
					} catch(BarricadaException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage() );
					} catch (DadoException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					break;
				case "vaciar": //vaciar|idSup (vaciar vertedero)
					try {
						String c = user.getSala().getPartida().vaciarVertedero(Integer.parseInt(split[3]));
						System.out.println("Vertedero: " + c);
						String[] sp = c.split("\\|");
						user.enviarALaSala("vertedero|" + sp[0]);
						user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha vaciado el vertedero." );
						user.hacerPeticionAlServidor("rmDado|" + sp[1]);
					} catch (VertederoException| DadoException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage() );
					} 
					break;
				case "aportarCrisis":
					System.out.println("Aportar: " + split[3]);
					String contribuciones = user.getSala().getPartida().aportarCrisis(Integer.parseInt(split[3]));
					user.hacerPeticionAlServidor("rmCarta|" + split[3]);
					user.enviarALaSala("updtCartas|" + user.getJugador().getId() + "|-1");   //updtCartas|idJug|-1 --> resta una carta
					user.enviarALaSala("numAportaciones|" + contribuciones); //numAportaciones|aportacionesjug0|aportacionesjug1....
					user.enviarALaSala("chat|" + user.getNombre() + " ha aportado a la crisis");
					break;
				case "buscar": //buscar|idSup
					try {
						String cartasEnc = user.getSala().getPartida().buscar(Integer.parseInt(split[3])); //si es evento idSup|posColonia|...|dado
						System.out.println("Buscar: " + cartasEnc); //cartas|cartas|nCartas|dado
																	//carta|ncarta|dado
						String[] enc = cartasEnc.split("\\|");
						if(Integer.parseInt(enc[0]) >= 100) { //evento
							user.enviarALaSala("asignar|" + user.getJugador().getId() + "|" +  enc[0] + "|" + enc[1]);
							user.enviarALaSala("evento|" + enc[2]);
							user.enviarALaSala("addSupInd|" + user.getSala().getPartida().getInutiles());
							user.enviarALaSala("chat|" + user.getNombre() + " ha encontrado supervivientes.");
						} else {
							int indNCartas = enc.length - 2;
							String cEnc = "";
							int i = 0;
							while(i < indNCartas) {
								cEnc += "|" + enc[i];
								i++;
							}
							cEnc += "|" + enc[i];
							user.hacerPeticionAlServidor("cartasEncont" + cEnc);
							System.out.println("cartasEncont" + cEnc);
						}
						
						user.hacerPeticionAlServidor("rmDado|" + enc[enc.length-1]);
						
					} catch (BuscarException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());					
					} catch (DadoException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());					
					}
					break;
				case "ruido":
					String rui = "";
					try {

						rui = user.getSala().getPartida().hacerRuido(); //si hay evento idSup|posColonia|loc|posValFicha
																		//si no cartas|loc|posValFicha
						String [] cRui = rui.split("\\|");
						if(Integer.parseInt(cRui[0]) >= 100) {
							user.enviarALaSala("asignar|" + user.getJugador().getId() + "|" +  cRui[0] + "|" + cRui[1]);
							user.enviarALaSala("evento|" + cRui[2]);
							user.enviarALaSala("addSupInd|" + user.getSala().getPartida().getInutiles());
							user.enviarALaSala("chat|" + user.getNombre() + " ha encontrado supervivientes.");
						} else {
							String carts = "";
							int c = 0;
							while( c < cRui.length - 2) {
								carts += "|" + cRui[c];
								c++;
							}
						
							user.hacerPeticionAlServidor("hacerRuido" + carts);
						}
						user.enviarALaSala("addFichRuido|" + cRui[cRui.length - 2] + "|" + cRui[cRui.length - 1]);
						user.enviarALaSala("chat|" + user.getNombre() + " ha hecho ruido.");
						
					} catch (BuscarException e1) {
						user.hacerPeticionAlServidor("error|" + e1.getMessage());
					}
					break;
				case "confirmarCarta": //confirmarCarta|carta1|carta2...
					int i = 3;
					while(i < split.length) {
						user.getSala().getPartida().ConfirmarCarta(Integer.parseInt(split[i]));
						user.hacerPeticionAlServidor("addCarta|" + split[i]);
						user.enviarALaSala("updtCartas|" + user.getJugador().getId() + "|1");
						i++;
					}
					break;
				case "atacar": //atacar|idSup
					try {
						String atc = user.getSala().getPartida().atacar(Integer.parseInt(split[3]));
						System.out.println("Atacar: " + atc);
						//atc ===> loc|pos|dado|dadoRiesgo
						String[] atcspl = atc.split("\\|");
						user.enviarALaSala("rmZombie|" + atcspl[0] + "|" + atcspl[1]);
						user.hacerPeticionAlServidor("rmDado|" + atcspl[2]);
						user.enviarALaSala("tiradaRiesgo|" + atcspl[3]);
						switch(atcspl[3]) {
							case "0": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " no ha recibido heridas" );
							break;
							case "1": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida normal" );
										user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
										System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
							break;
							case "2": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida por congelación" );
										user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
										System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
							break;
							case "3": user.enviarALaSala("chat|[" + user.getNombre() + "] "  + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha muerto..." );
								user.enviarALaSala("rmSup|" + user.getJugador().getId() + "|" + split[3]);
								int moral = user.getSala().getPartida().getMoral();
								if( moral <= 0 ) {
									user.enviarALaSala("finpartida");
								} else {
									user.enviarALaSala("moral|" +  user.getSala().getPartida().getMoral());
								}
							break;
						}
					} catch (MatarException | DadoException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					break;
				case "usarCarta":					//usarCarta|idCarta|idSup
					System.out.println("hay Cosas");
					System.out.println("usarCarta|" + split[3] + "|" + split[4]);
					try {
						String sal = "";
						int idCarta = Integer.parseInt(split[3]);
						if(idCarta == 3) {									//MEDICINA
							sal = user.getSala().getPartida().getVecinos();
							user.hacerPeticionAlServidor("medicina|" + sal);
						} else {
							sal = user.getSala().getPartida().usarCarta(idCarta, Integer.parseInt(split[4]));
							if( idCarta <= 2 ) {																//COMIDA
								user.enviarALaSala("fichasComida|" + user.getSala().getPartida().getComida());
							} else if(idCarta == 4 || idCarta == 19) { 											//TRASTOS O LIBRO
								user.hacerPeticionAlServidor("setDado|" + sal);
							} else if(idCarta >= 9 && idCarta != 19) { 											//EQUIPABLES
								user.hacerPeticionAlServidor("equipar|" + split[4] + "|" + idCarta); //equipar|idSup|idCarta
								System.out.println("equipa");
							}
						}
						
						if(idCarta != 19) {
							user.hacerPeticionAlServidor("rmCarta|" + idCarta);
							user.enviarALaSala("updtCartas|" + user.getJugador().getId() + "|-1");
							user.enviarALaSala("vertedero|" + user.getSala().getPartida().getVertedero());
						}
						
					}catch (Exception e){
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					break;
				case "medicina": //medicina|idSup curar a un superviviente
					try {
						user.getSala().getPartida().usarCarta(3, Integer.parseInt(split[3]));
						user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
						user.enviarALaSala("chat|" + user.getNombre() +  " ha usado una medicina en " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])));
					}catch (Exception e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					break;
				case "gastarComida": //gastarComida|idDado
					try {
						String st = user.getSala().getPartida().gastarComida(Integer.parseInt(split[3]));
						user.hacerPeticionAlServidor("setDado|" + st);
						user.enviarALaSala("fichasComida|" + user.getSala().getPartida().getComida());
						user.enviarALaSala("chat|" + user.getNombre() + " ha gastado comida");
					} catch (DadoException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					break;
				case "usarHabilidad": 			//usarHabilidad|idSup|objetivo|dado
					int idSup = Integer.parseInt(split[3]);
					int idObjetivo = Integer.parseInt(split[4]);
					int idDado = Integer.parseInt(split[5]);
					System.out.println("USAR HABILIDAD IDSUP: " + idSup + ", IDOBJ: " + idObjetivo + ", IDDADO: " + idDado);
					try {
						
						String salida = user.getSala().getPartida().usarHabilidad(idSup, idObjetivo, idDado);
						user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(idSup) + " ha usado su habilidad" );
						System.out.println("Salida de USAR HABILIDAD: " + salida);
						
						switch(idSup) {
							case 102: {
								//ve una carta del jugador objetivo
								user.hacerPeticionAlServidor("verCarta|" + salida);
								user.hacerPeticionAlServidor("error|La carta es de " + user.getSala().getUsuarios().get((user.getJugador().getId() + 1) % user.getSala().getUsuarios().size()).getNombre());
							}
								break;
							case 107: 
								//modifica un dado
								user.hacerPeticionAlServidor("setDado|" + salida);
								break;
							case 110: {
								//zombies muertos
								String[] arr = salida.split("\\|"); //loc|pos|loc...|dado|riesgo
								String zomb = "";
								int con = 0;
								
								while(con < arr.length - 2) {
									zomb += "|" + arr[con] + "|" + arr[con+1];
									con += 2;
								}
								
								user.enviarALaSala("rmZombie" + zomb);
								user.hacerPeticionAlServidor("rmDado|" + arr[arr.length - 2]);
								user.enviarALaSala("tiradaRiesgo|" + arr[arr.length - 1]);
								switch(arr[arr.length - 1]) {
									case "0": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " no ha recibido heridas" );
									break;
									case "1": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida normal" );
												user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
												System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									break;
									case "2": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida por congelación" );
												user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
												System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									break;
									case "3": user.enviarALaSala("chat|[" + user.getNombre() + "] "  + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha muerto..." );
										user.enviarALaSala("rmSup|" + user.getJugador().getId() + "|" + split[3]);
										int moral = user.getSala().getPartida().getMoral();
										if( moral <= 0 ) {
											user.enviarALaSala("finpartida");
										} else {
											user.enviarALaSala("moral|" +  user.getSala().getPartida().getMoral());
										}
									break;
								}
								
							}
								break;
							case 112: {
								//id carta eliminada y zombies muertos
								String[] arr = salida.split("\\|"); //idCarta|loc|pos|loc...|dado|riesgo
								user.hacerPeticionAlServidor("rmCarta|" + arr[0]);
								user.enviarALaSala("updtCartas|" + user.getJugador().getId() + "|-1");
								String zomb = "";
								int con = 1;
								
								while(con < arr.length - 2) {
									zomb += "|" + arr[con] + "|" + arr[con+1];
									con += 2;
								}
								
								user.enviarALaSala("rmZombie" + zomb);
								user.hacerPeticionAlServidor("rmDado|" + arr[arr.length - 2]);
							}
								break;
							case 113:
								//eliminar santa claus, actualizar moral
								user.enviarALaSala("rmSup|" + user.getJugador().getId() + "|" + salida);
								user.enviarALaSala("moral|" + user.getSala().getPartida().getMoral());
								break;
							case 114: {
								//si no es cadena vacia, carta de evento
								if(salida.equals("")) {
									user.hacerPeticionAlServidor("error|No se ha encontrado nada");
								} else {
									String[] sp = salida.split("\\|");
									user.enviarALaSala("asignar|" + user.getJugador().getId() + "|" +  sp[0] + "|" + sp[1]);
									user.enviarALaSala("evento|" + sp[2]);
									user.enviarALaSala("addSupInd|" + user.getSala().getPartida().getInutiles());
									user.enviarALaSala("chat|" + user.getNombre() + " ha encontrado supervivientes.");
								}
							}
								break;
							case 115: {
								//roba una carta
								String[] sp = salida.split("\\|"); //idCarta|jugadorRobado
								int idOtro = Integer.parseInt(sp[1]);
								user.hacerPeticionAlServidor("addCarta|" + sp[0]);
								user.getSala().getUsuarios().get(idOtro).hacerPeticionAlServidor("rmCarta|" + sp[0]);
								user.enviarALaSala("updtCartas|" + user.getJugador().getId() + "|1");
								user.enviarALaSala("updtCartas|" + sp[1] + "|-1");
								user.getSala().getUsuarios().get(idOtro).hacerPeticionAlServidor("error|" + user.getNombre() + " te ha robado una carta");
								user.hacerPeticionAlServidor("error|Robaste una carta de " + user.getSala().getUsuarios().get(idOtro).getNombre());
							}	
								break;
							case 117: //vuelve a tirar un dado
								user.hacerPeticionAlServidor("setDado|" + salida);
								break;
							case 122:
								//gasta un dado
								user.hacerPeticionAlServidor("rmDado|" + salida);
								break;
							case 123: {
								//igual que atacar
								String[] arr = salida.split("\\|"); //loc|pos|loc...|dado|riesgo
								String zomb = "";
								int con = 0;
								
								while(con < arr.length - 2) {
									zomb += "|" + arr[con] + "|" + arr[con+1];
									con += 2;
								}
								
								user.enviarALaSala("rmZombie" + zomb);
								user.hacerPeticionAlServidor("rmDado|" + arr[arr.length - 2]);
								user.enviarALaSala("tiradaRiesgo|" + arr[arr.length - 1]);
								switch(arr[arr.length - 1]) {
									case "0": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " no ha recibido heridas" );
									break;
									case "1": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida normal" );
												user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
												System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									break;
									case "2": user.enviarALaSala("chat|[" + user.getNombre() + "] " + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha recibido una herida por congelación" );
												user.enviarALaSala("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
												System.out.println("heridas|" + split[3] + "|" + user.getSala().getPartida().getHeridas(Integer.parseInt(split[3])));
									break;
									case "3": user.enviarALaSala("chat|[" + user.getNombre() + "] "  + user.getSala().getPartida().getNombre(Integer.parseInt(split[3])) + " ha muerto..." );
										user.enviarALaSala("rmSup|" + user.getJugador().getId() + "|" + split[3]);
										int moral = user.getSala().getPartida().getMoral();
										if( moral <= 0 ) {
											user.enviarALaSala("finpartida");
										} else {
											user.enviarALaSala("moral|" +  user.getSala().getPartida().getMoral());
										}
									break;
								}
							}
								break;
							case 125: {
								//cura a un superviviente
								
								
							}
								break;
							case 127: {
								//ve una carta
								
							}
								break;
						}
					} catch (HabilidadException | MatarException | DadoException | BuscarException e) {
						user.hacerPeticionAlServidor("error|" + e.getMessage());
					}
					
					break;
				case "newRound": // Me hace falta la crisis
					for(Usuario usario : user.getSala().getUsuarios()) {
						usario.hacerPeticionAlServidor("newRound|" + user.getSala().getPartida().getRonda() + "|"  + user.getSala().getPartida().getCrisisActualId() + "|" + user.getSala().getPartida().getDados(usario.getJugador().getId()));
					}
					break;
				case "moral":
					user.enviarALaSala("moral|" + user.getSala().getPartida().getMoral());
					break;
				case "heridas":
					int id = Integer.parseInt(split[3]);
					user.hacerPeticionAlServidor("heridas|" + user.getSala().getPartida().getHeridas(id));
					System.out.println("heridas|" + user.getSala().getPartida().getHeridas(id));
					System.out.println(split[3]);
					break;
				case "host":
					if(user.getSala() != null) {
					if(user.getSala().getHost().equals(user)) {
						user.hacerPeticionAlServidor("host|");
					}
					}
					break;	
				case "idsala":
					if(user.getSala() != null) {
					 user.hacerPeticionAlServidor("idsala|" + user.getSala().getId());
					 System.out.println("Enviado id de sala a " + user.getNombre());
					}
					 break;
				case "nusuarios":
					if(user.getSala() != null) {
					user.hacerPeticionAlServidor("nusuarios|Numero de jugadores: " + user.getSala().getUsuarios().size());
					System.out.println("Enviado numero de usuarios de sala a " + user.getNombre());
					}
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
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			finally {
		
			if(user != null) {
				 if(user.getSala() != null) {
				 try {
					salirDeSala(user, user.getSala());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
				 us.remove(user);
				 
				 System.out.println("Desconectado:" + user.getClientReader().getSocket().getInetAddress() + "(" + user.getNombre() + ")");
				
				 
				 //Comprobar el numero de conexiones activas
				 for(Usuario vodkausuario : us) {
						System.out.println(vodkausuario.getClientReader().getSocket().getInetAddress());
					}
				 System.out.println(us.size());
				 
				 try {
					user.getClientReader().close();
				    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	
	public void salirDeSala(Usuario usuario, Sala sala) throws IOException, InterruptedException {
		if(sala != null) {
	if(sala.getUsuarios().size() == 1) {
		sala.getUsuarios().remove(0);
		salas.remove(sala);
		usuario.setSala(null);
		System.out.println("La sala " + sala.getId() + " ha sido borrada");
	} else if (sala.getUsuarios().size() > 1 && sala.getHost().getNombre().equals(usuario.getNombre())){
		
		if(sala != null && sala.getPartida() != null) {
			if(sala.getPartida().getJugadorActual().getId() == usuario.getJugador().getId()) {
				pasarTurnoORonda(usuario.getJugador().getId());
			}
			sala.getUsuarios().remove(user);
			sala.getPartida().getJugadores().remove(usuario.getJugador());
		}
		
		sala.getUsuarios().remove(usuario);
		usuario.enviarALaSala("chat|El host de la sala " + sala.getId() + " ha cambiado de " + usuario.getNombre() + " a " + sala.getUsuarios().get(0).getNombre());
		sala.enviarAUsuariosDeLaSala("nusuarios|Numero de jugadores: " + sala.getUsuarios().size());
		usuario.setSala(null);
		sala.setHost(sala.getUsuarios().get(0));
		sala.setPuedeEntrar(true);
		
		
		
		System.out.println("El host de la sala " + sala.getId() + " ha cambiado de " + usuario.getNombre() + " a " + sala.getUsuarios().get(0).getNombre());
	} else {
		
		if(sala != null && sala.getPartida() != null) {
			if(sala.getPartida().getJugadorActual().getId() == usuario.getJugador().getId()) {
				pasarTurnoORonda(usuario.getJugador().getId());
			}
			sala.getUsuarios().remove(user);
			sala.getPartida().getJugadores().remove(usuario.getJugador());
		}
		
		sala.getUsuarios().remove(usuario);
		usuario.enviarALaSala("chat|" + usuario.getNombre() + " ha salido de la sala " + sala.getId());
		sala.enviarAUsuariosDeLaSala("nusuarios|Numero de jugadores: " + sala.getUsuarios().size());
		usuario.setSala(null);
		sala.setPuedeEntrar(true);
		
		
	}
	System.out.println(usuario.getNombre() + " ha salido de la sala " + sala.getId());
	
//	if(sala != null && sala.getPartida() != null) {
//		if(sala.getPartida().getJugadorActual().getId() == usuario.getJugador().getId()) {
//			pasarTurnoORonda(usuario.getJugador().getId());
//		}
//		sala.getUsuarios().remove(user);
//		sala.getPartida().getJugadores().remove(usuario.getJugador());
//	}
		}
  }
	
	public void pasarTurnoORonda(int idAnterior) throws IOException, InterruptedException {
		int cont = user.getSala().getContTurnos();
		cont--;
		user.getSala().setContTurnos(cont);
		if(cont == 0) { //////////////////////////////////////////ACABA UNA RONDA
			String contribuciones = user.getSala().getPartida().cartasContrib(); //contribuciones a la crisis
			System.out.println(contribuciones);
			String resCrisis = user.getSala().getPartida().resultadoCrisis();
			System.out.println(resCrisis);
			String zombies = user.getSala().getPartida().pasaRonda(); //poszombieloc0|poszombieloc1|...
			if(user.getSala().getPartida().getRondasRestantes() == 0 || user.getSala().getPartida().getMoral() <= 0) {
				user.enviarALaSala("finpartida");
			} else {
				user.getSala().setContTurnos(user.getSala().getUsuarios().size());
				String muertos = user.getSala().getPartida().getMuertos(); //muertos al pasar la ronda
				System.out.println(muertos);
				if(!muertos.equals("")) { 
					int i = 0;
					String[] sp = muertos.split("\\|");
					while(i < sp.length) {
						user.enviarALaSala("rmSup|" + sp[i] + "|" + sp[i+1]);
						user.enviarALaSala("chat|[" + user.getSala().getUsuarios().get(Integer.parseInt(sp[i])).getNombre() + "] " +
												user.getSala().getPartida().getNombre(Integer.parseInt(sp[i+1])) + " ha muerto");
						i+=2;
					}
				}
				
				user.getSala().getPartida().pasaTurno(0);
				for(int i = 0; i < user.getSala().getUsuarios().size(); i++) {
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("crisisResult|" + resCrisis);
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("cartasAportadas|" + contribuciones);
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("addZombies|" + zombies + "|");
					System.out.println("addZombies|" + zombies);
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("newRound|" + user.getSala().getPartida().getRondasRestantes() + "|" + user.getSala().getPartida().getCrisisActualId() + "|" + user.getSala().getPartida().getDados(i));
					System.out.println("newRound|" + user.getSala().getPartida().getRondasRestantes() + "|" + user.getSala().getPartida().getCrisisActualId() + "|" + user.getSala().getPartida().getDados(i));
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("moral|" + user.getSala().getPartida().getMoral());
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("fichasComida|" + user.getSala().getPartida().getComida());
					user.getSala().getUsuarios().get(i).hacerPeticionAlServidor("fichasHambre|" + user.getSala().getPartida().getHambre());
					
				}
				user.getSala().getUsuarios().get(0).hacerPeticionAlServidor("tuturno");
				user.enviarALaSala("chat|Turno de " + user.getSala().getUsuarios().get(0).getNombre());
			}
	} else {
		int idSig = (idAnterior + 1)%user.getSala().getUsuarios().size();
		user.getSala().getPartida().pasaTurno(idSig);
		user.getSala().getUsuarios().get(idSig).hacerPeticionAlServidor("tuturno");
		user.enviarALaSala("chat|Turno de " + user.getSala().getUsuarios().get(idSig).getNombre());
	}
}
}