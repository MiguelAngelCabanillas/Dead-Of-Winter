package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import Gui.FrameSala;
import Gui.FrameTablero;

public class ClientReader implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FrameSala sala;
    private FrameTablero tablero;
    private Semaphore acceso = new Semaphore(0);

	private List<String> jugadores = new ArrayList<>(); //jugadores.get(i) es el nombre del jugador de id i

    private int idJug;

    public ClientReader (Socket s) throws IOException {
        socket = s;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            while (true) {
                String msgllegada = in.readLine();
                if(msgllegada == null) {
                    continue;
                }
                String[] split = msgllegada.split("\\|");
                switch (split[0]) {
                
                case "idsala": //idsala|id
                    if(sala != null) sala.actIdSala(Integer.parseInt(split[1]));
                    break;
                    
                case "nusuarios":
                    if(sala != null) sala.actNumJugadores(split[1]);
                    break;
                    
                case "chat":
                	if(split[1] != null)
	                	if(sala != null) {
	                    sala.actualizaChat(split[1]);
	                	} else  if (tablero != null){
	                	tablero.actualizaChat(split[1]);
	                	}
                    break;
                    
                case "exit":
                	try {
						if(split[1].equals("tablero")) { // Inicializar
							sala.avanzarATablero(Integer.parseInt(split[2]));
						} else {
							sala.salirSala();
							sala = null;
						}
						acceso.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                	break;
                	
                case "host" :
                	sala.setIsHost(true);
                	break;
                	
                case "error": //Para mensajes de error: error|mensajeerror
                	FrameTablero.errorPartida(split[1]);
                	break;
                	
                case "asignar": // asignar|idJug|idSup|posValCol|idSup|posValCol|idSup|posValCol...
                	int i = 2;
                	int idSup,posValCol;
                	while(i < split.length) {
                		idJug = Integer.parseInt(split[i-1]);
                		idSup = Integer.parseInt(split[i]);
                		posValCol = Integer.parseInt(split[i+1]);
                		tablero.addSupJug(idJug,idSup); //añadimos superviviente a la lista del jugador al que se le asigna
                		tablero.anyadirSuperviviente(idSup, posValCol); //añadimos superviviente al tablero
                		i = i+2;
                	}
                	break;
                	
                case "addSupInd": //addSupInd|casilla -> casilla a partir de la cual rellenamos supervivientes hasta el final de la colonia
                	tablero.addSupInd(Integer.parseInt(split[1]));
                	break;
                	
                case "rmSup": // rmSup|idJug|idSup
                	tablero.borrarSuperviviente(Integer.parseInt(split[2]));
                	tablero.rmSupJuga(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                	break;
                	
                case "mover": // mover|superviviente|destino|posicionValida|dadoRiesgo --- Superviviente es una ID única para cada tipo de superviviente
                	System.out.println("Mover superviviente " + split[1] + " a la localizacion " + split[2] + ", posicion " + split[3]);
                	tablero.moverSuperviviente(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                	tablero.tiradaDeRiesgo(Integer.parseInt(split[4]));
                	break;
                	
                case "secreto": // secreto|id -- ID única para cada objetivo secreto
                	System.out.println("Objetivo Secreto: " + split[1]);
                	FrameTablero.setObjetivoSecreto(Integer.parseInt(split[1]));
                	break;
                	
                case "initSup": //initSup|sup1Jug1|sup2Jug1|sup1Jug2|sup2Jug2|...
                	int sup1,sup2;
                	int pos = 0;
                	FrameTablero.setId(Integer.parseInt(split[split.length-1]));
                	for(int id = 0; id < (split.length/2) - 1; id++) {
                		sup1 = Integer.parseInt(split[2*id+1]);
                		sup2 = Integer.parseInt(split[2*id+2]);
                		
                		//IMPRESION EN JFRAME
                		tablero.anyadirSuperviviente(sup1, pos);pos++;
                		tablero.anyadirSuperviviente(sup2, pos);pos++;
                		//AÑADIMOS SUPERVIVIENTES A LA LISTA
                		tablero.addSupJug(id,sup1);
                		tablero.addSupJug(id,sup2);
                	}
                	tablero.mostrarJugadorSupervivientes();
                	tablero.inicHeridas();
                	break;
                	
                //PASAR NOMBRES ASIGNADOS A IDs	
                case "ids": //ids|nombreJug1|nombreJug2|nombreJug3
                	for(int k = 1; k < split.length; k++) {
                		jugadores.add(split[k]);
                		FrameTablero.setNombreJugadores(split[k]);
                	}
                	break;	

                case "initCartas": //initCartas|idCarta|idCarta|idCarta|idCarta....|idCarta
                	int idCarta;
                    for(int j = 1; j < split.length;j++) {
                        idCarta = Integer.parseInt(split[j]);
                        tablero.addCartaJug(idCarta); //añadimos cartas a la lista del jugador al que se le asigna
                	}
                    FrameTablero.initListCartas(jugadores.size());
                	break;
                	
                case "updtCartas": //updtCartas|idJug|nCartas
                    FrameTablero.updateCartas(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
                	break;
                	
                case "addBarricada": //addBarricada|idLoc|posVal
                	System.out.println("barricada " + split[1] + " " + split[2]);
                	tablero.addBarricada(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                	break;
                	
                case "newRound": //newRound|numeroRonda|idCrisis|dado1|dado2...
                	tablero.setRonda(Integer.parseInt(split[1]));
                	tablero.setCrisis(Integer.parseInt(split[2]));
                	tablero.inicDados();
                	tablero.inicAportaciones();
                	tablero.inicRuido();
                	for(int j = 3; j<split.length;j++) {
                		tablero.tiradaDados(Integer.parseInt(split[j]));
                		System.out.println("Dado: " + Integer.parseInt(split[j]));
                	}
                	break;
                	
                case "moral": //moral|nuevoValorMoral
                	System.out.println("Nuevo valor de la moral:" + split[1]);
                	tablero.setMoral(Integer.parseInt(split[1]));
                	break;
                	
                /*case "heridas": //heridas|numHeridasNormales|numHerdiasCongelacion
                	int[] heridas = new int[2];
                	heridas[0] = Integer.parseInt(split[1]);
                	heridas[1] = Integer.parseInt(split[2]);
                	System.out.println("Entran " + split[1] + ", " + split[2]);
                	FrameTablero.setHeridas(heridas);
                	break;*/
                	
                case "heridas": //heridas|idSup|numHeridasNormales|numHSeridasCongelacion
                	tablero.setHeridasNormales(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
                	tablero.setHeridasCong(Integer.parseInt(split[1]),Integer.parseInt(split[3]));
                	break;
                	
                case "tuturno": 
                	tablero.miTurno();
                	break;
                	
                case "finpartida":
                	tablero.finPartida();
                	break;
                	
                case "vertedero": //vertedero|nuevoValor
                	System.out.println("BEGGGTEDEGGGGO DI MAMMAA: " + split[1]);
                	tablero.setVertedero(Integer.parseInt(split[1]));
                	break;
                	
                case "rmDado":	//rmDado|posDado
                	System.out.println(split[1]);
                	tablero.removeDado(Integer.parseInt(split[1]));
                	break;
                	
                case "tiradaRiesgo": //tiradaRiesgo|resultado
                	tablero.tiradaDeRiesgo(Integer.parseInt(split[1]));
                	break;
                	
                case "addCarta": //addCarta|idCarta|posDadoGastado
                	tablero.addCartaJug(Integer.parseInt(split[1]));
                	break;
                	
                case "setDado": //setDado|idDado|nuevoValor
                	tablero.setDado(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                	break;
                	
                case "addZombies": //addZombies|posValLoc0|posValLoc1|posValLoc2|posValLoc3...posValLoc11
                	for(int k = 1; k < split.length; k++){
                		 for(int j = 0; j < split[k].length(); j++){
                		   int posV =  Integer.parseInt(split[k].charAt(j)+"");
                		   if(Integer.parseInt(split[k].charAt(j)+"") < 4) {
                			   System.out.println("Añadir zombie en la pos " + posV + " de la localizacion " + (k-1));
                			   tablero.addZombie(k-1,posV);
                		   }else { //pos0barricada = 4, pos1barricada = 5,pos2barricada = 6, pos3barricada = 7
                			   System.out.println("Eliminar barricada en la pos " + (posV-4) + " de la localizacion " + (k-1));
                			   tablero.deleteBarricada(k-1, posV-4);
                		   }
                		 }
                	}
                break;
                	
                case "rmZombie": //rmZombie|loc|pos
                	tablero.deleteZombie(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                	break;
                
                case "rmCarta": //rmCarta|idCarta
                	tablero.rmCartaJug(Integer.parseInt(split[1]));
                	break;
          	
                case "numAportaciones": //numAportaciones|aporJug1|aportJug2|aportJug3...
                	if(!split[1].equals("null")) {
                		List<Integer> list = new ArrayList<>();
	                	for(int j=1; j < split.length; j++) {
	                		list.add(Integer.parseInt(split[j]));
	                	}
	                	tablero.setNumAport(list);
                	}
                	break;
                	
                case "cartasAportadas":	//cartasAportadas|idCarta1|idCarta2|idCarta3...
                	if(!split[1].equals("null")) {
	                	for(int j=1; j < split.length; j++) {
	                		tablero.cartasAportadas(Integer.parseInt(split[j]));
	                	}
                	}
                	tablero.cartasResCrisis();
                	break;
                	
                case "cartasEncont": //cartasEncont|idC1|idC2|...|nCartas	
                	FrameTablero.setNCartasABuscar(Integer.parseInt(split[split.length-1]));
                	List<Integer> cartas = new ArrayList<>();
                	if(split[1].length() != 0) {
                    	for(int m = 1; m < split.length - 1; m++) {
                    		cartas.add(Integer.parseInt(split[m]));
                    	}
                	}
                	FrameTablero.setCartasEncontradas(cartas);
                	break;
                	
                case "hacerRuido": //hacerRuido|idCarta|idCarta
                	List<Integer> c = new ArrayList<>();
                	for(int m = 1; m < split.length; m++) {
                		c.add(Integer.parseInt(split[m]));
                	}
                	FrameTablero.setCartasEncontradas(c);
                	break;
                	
                case "addFichRuido": //addFichRuido|loc|posVal
                	System.out.println("AÑADIR FICHA DE RUIDO EN LOCALIZACION " + split[1] + ", POSICION " + split[2]);
                	tablero.addFichRuido(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                	break;
                	
//                case "rmFichRuido": //rmFichRuido|loc|posVal
//                	tablero.deleteFichRuido(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
//                	break;
                	
                case "fichasComida": //fichasComida|num
                	tablero.setFichComida(Integer.parseInt(split[1]));
                	break;
                	
                case "fichasHambre": //fichasHambre|num
                	tablero.setFichHambre(Integer.parseInt(split[1]));
                	break;
                	
                case "crisisResult": //crisisResult|result
                	System.out.println("Resultado de la crisis: " + split[1]);
                	if(split[1].trim().equals("sobra")) {
                		tablero.crisisResult(2);
                	}else if(split[1].trim().equals("pasada")) {
                		tablero.crisisResult(1);
                	}else if(split[1].trim().equals("fallo")) {
                		tablero.crisisResult(0);
                	}
                	break;
                	
                case "medicina": //medicina|idSup|idSup|idSup... (todos los upervivientes y sus vecinos con heridas)
                	List<Integer> list = new ArrayList<>();
                	for(int j=1;j<split.length;j++) {
                		list.add(Integer.parseInt(split[j]));
                	}
                	tablero.seleccionSupervivientes(list, "medicina");
                	break;
                	                	
                default:
                	System.err.println("ERROR IN COMMAND-> " + split[0]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void hacerPeticionAlServidor(String peticion) throws IOException {
        out.println(peticion);
        out.flush();
    }

    public String recibirMensaje() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
    public void setSala(FrameSala sala) {
        this.sala = sala;
    }
    
    public  void setTablero(FrameTablero t) {
    	this.tablero = t;
    }
    
    public Semaphore getSemaphore() {
    	return acceso;
    }
	
	public List<String> getJugadores(){
    	return jugadores;
    }
	
	public Socket getSocket() {
		return socket;
	}
		
	
}
