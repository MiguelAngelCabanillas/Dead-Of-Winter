package Partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Cartas.Carta;
import Cartas.Carta_Supervivientes;
import Excepciones.MoverException;

public class Jugador {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////ATRIBUTOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATOS PARA LA INTERFAZ
	private int id;
	
	//DATOS DEL JUGADOR
	private List<Carta_Supervivientes> mazoSuperviviente;
	private List<Carta> mazoObjeto;
	private Dado dados;
	private DadoDeRiesgo riesgo;
	private Objetivo_Principal objetivo;
	private List<Carta> buffer;
	
	private boolean gasolina;
	
	//DATOS DE CONTROL
	private Tablero tablero;
	private Localizacion locCartas;
	private InicSupervivientes supervivientes;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////CONSTRUCTORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Jugador(int id, List<Carta> mazoJugador, Tablero t, Objetivo_Principal o, InicSupervivientes s) {
		this.id = id;
		
		this.mazoSuperviviente = new ArrayList<Carta_Supervivientes>();
		this.mazoObjeto  = mazoJugador;
		this.dados = new Dado();
		
		this.riesgo = new DadoDeRiesgo();
		buffer = new ArrayList<>();
		
		gasolina = false;
		this.objetivo = o;
		
		tablero = t;
		supervivientes = s;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//GETTERS Y SETTERS
	public boolean getGasolina() {
		return gasolina;
	}
	
	public void setGasolina(boolean g) {
		gasolina = g;
	}
	
	public Dado getDados() {
		return this.dados;
	}
	
	public int getId () {
		return this.id;
	}
	
	public void setId (int i) {
		this.id = i;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	
	public List<Carta_Supervivientes> getMazoSuperviviente() {
		return this.mazoSuperviviente;
	}
	
	//METODOS DE RONDA
	public String tirarDados() {
		String salida = ""; int i = 0;
		dados.resetDados(this.mazoSuperviviente.size());
		
		for(int d : dados.getDados()) {
			if(i != 0) {
				salida += "|";
			}
			i++;
			salida += d;
		}
		
		return salida;
	}
	
	public void matar() {
		Carta_Supervivientes aux;
		Localizacion loc;
		int i = 0;
		
		while(i < this.mazoSuperviviente.size()) {
			 aux = this.mazoSuperviviente.get(i);
			 loc = localizacion(aux);
			if (aux.estaMuerto()) {
				this.mazoSuperviviente.remove(aux);
				loc.eliminarSuperviviente(aux);
				tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 1);
			}else {
				i++;
			}
		}
	}
	
	//METODO QUE COGE EL MENOR DE LOS DADOS
	public int valorDado(int valor) throws DadoException {
//		int actual = 100;
//        int indice = -1;
//        Iterator<Integer> iter = dados.getDados().iterator();
//        while(iter.hasNext()) {
//            int valorDado = iter.next();
//            if (valorDado >= valor) {
//                if (valorDado < actual) {
//                    actual = valorDado;
//                    indice = dados.getDados().indexOf(actual);
//                }
//            }
//        }
//		return indice;
		
		int menor = 1000;
        int indice = -1;
        for (int i = 0; i < dados.getDados().size(); i++) {
            if (dados.getDados().get(i) >= valor) {
                if (dados.getDados().get(i) < menor) {
                    indice = i;
                    menor = dados.getDados().get(i);
                }
            }
        }
        return indice;
	}
	
	//METODO PARA SABER EL INDICE DE UN SUPERVIVIENTE
	private int indice(Carta_Supervivientes carta) {
		int indice = -1;
		int i = 0;
		
		while(indice == -1 && i < mazoSuperviviente.size()) {
			if (carta.equals(mazoSuperviviente.get(i))) {
				indice = i;
			} else {
				i++;
			}
		}
		
		return indice;
	}
	
	//RESTABLECE LAS HABILIDADES YA USADAS
	public void resetHab() {
		for(Carta_Supervivientes sup : mazoSuperviviente) {
			sup.setUsado(false);
			sup.setMovido(false);
			sup.resetEquipables();
			sup.setPasivaDeAtaque(false);
			sup.setPasivaDeBusqueda(false);
		}
	}
	
	//DEVUELVE LA CARTA DE SUPERVIVIENTE CORRESPONDIENTE A LA ID
	public Carta_Supervivientes getSupConId(int id) {
		Carta_Supervivientes salida = null;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < mazoSuperviviente.size()) {
			Carta_Supervivientes aux = mazoSuperviviente.get(i);
			if(aux.getId() == id) {
				salida = aux;
				encontrado = true;
			}else {
				i++;
			}
		}
		
		return salida;
	}
	
	public int eliminarCarta(int id) { //El jugador no va a poder seleccionar una carta que no tiene
        Iterator<Carta> ite = mazoObjeto.iterator();
        int encontrado = -1;
        while(ite.hasNext() && encontrado == -1) {
            Carta carta = ite.next();
            if(carta.getId() == id) {
            	encontrado = mazoObjeto.indexOf(carta);
                mazoObjeto.remove(mazoObjeto.indexOf(carta));
            }
        }
        
       return encontrado;
    }

	public void darCarta(int id) {
	        //POR DETERMINAR (usado para test)
	        Carta aux = new Carta(id);
	        mazoObjeto.add(aux);
	    }
	public List<Carta> getMazoObjetos() {
	        return mazoObjeto;
	    }
	
	//METODO PARA APLICAR UNA HERIDA
	public void herir(Carta_Supervivientes carta, boolean hielo) {
		mazoSuperviviente.get(indice(carta)).recibirHerida(hielo);
	}
	
	//DEVUELVE LA LOCALIZACION DEL PERSONAJE
	public Localizacion localizacion(Carta_Supervivientes personaje) {
		Localizacion l = null;
		
		if (tablero.getBiblioteca().esta(personaje)) {
			l = tablero.getBiblioteca();
			
		} else if(tablero.getColegio().esta(personaje)) {
			l = tablero.getColegio();
			
		} else if(tablero.getColonia().esta(personaje)) {
			l = tablero.getColonia();
			
		} else if(tablero.getComisaria().esta(personaje)) {
			l = tablero.getComisaria();
			
		} else if(tablero.getGasolinera().esta(personaje)) {
			l = tablero.getGasolinera();
			
		} else if(tablero.getHospital().esta(personaje)) {
			l = tablero.getHospital();
			
		} else {
			l = tablero.getSupermercado();
			
		}
		
		return l;
	}
	
	//AÑADE UN SUPERVIVIENTE AL JUGADOR

	public void addSuperviviente(Carta_Supervivientes personaje) {
		mazoSuperviviente.add(personaje);
	}
	
	//DEVUELVE EL SUPERVIVIENTE QUE EL JUGADOR TIENE EN LA LOCALIZACION
	private Carta_Supervivientes comprobarLocalizacion(Localizacion l) {
		boolean esta = false;
		Carta_Supervivientes personaje = null;
		int i = 0;
		
		while (!esta && i < mazoSuperviviente.size()) {
			if (l.esta(mazoSuperviviente.get(i))) {
				personaje = mazoSuperviviente.get(i);
				esta = true;
			} else {
				i++;
			}
		}
		
		return personaje;
	}
	
	//TRADUCCIÓN DE LA ID DE LOCALIZACION
	private Localizacion getLocalizacion(int id) {
		Localizacion sitio = null;
		
		switch(id) {
		case 0 : sitio = tablero.getComisaria();
			break;
		case 1 : sitio = tablero.getSupermercado();
			break;
		case 2 : sitio = tablero.getColegio();
			break;
		case 3 : sitio = tablero.getGasolinera();
			break;
		case 4 : sitio = tablero.getHospital();
			break;
		case 5 : sitio = tablero.getBiblioteca();
			break;
		default : sitio = tablero.getColonia();
		}
		
		return sitio;
	}
	 
	//REALIZA UNA TIRADA DEL DADO DE RIESGO Y REGULA LAS ACCIONES POSTERIORES A ESTA
	private int tiradaRiesgo(Carta_Supervivientes personaje) {
		int dado = riesgo.tirarDado();
		int salida = 0;
		
		if (dado > 5 && dado <= 8) {
			personaje.recibirHerida(false);
			salida = 1;
		} else if (dado > 8 && dado <= 10){
			personaje.recibirHerida(true);
			salida = 2;
		} else if (dado == 11) {
			if(!personaje.getPerro()) {
				localizacion(personaje).eliminarSuperviviente(personaje);
				mazoSuperviviente.remove(personaje);
				salida = 3;
				tablero.getColonia().setMoral(tablero.getColonia().getMoral() - 1);
			}else {
				personaje.recibirHerida(false);
				salida = 1;
			}
		}
		
		return salida;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	////METODOS DEL TURNO DEL JUGADOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	//ESTE METODO ELIMINA UN ZOMBIE O SUPERVIVIENTE DE LA LOCALIZACION DONDE SE ENCUENTRA EL SUPERVIVIENTE
	public String atacar(int id) throws MatarException, DadoException {
		Carta_Supervivientes personaje = getSupConId(id);
		Localizacion loc = localizacion(personaje);
		int riesgo = 0;
		String salida = "";
		//EL UNICO CASO EN EL QUE RES ES NULL SE MANDA UNA MATAREXCEPTION
		String res = null;
		
		//PARA LAS HABILIDADES DE LOS SUPERVIVIENTES
		boolean madre = personaje.getId() == 105 && loc.getId() == 6 && !personaje.getUsado();
		boolean sheriff = personaje.getId() == 110 && !personaje.getUsado() && personaje.getPasivaDeAtaque();	//CONTROLAMOS QUE SE HAYA SELECCIONADO LA HABILIDAD
		boolean quimico = personaje.getId() == 112 && !personaje.getUsado() && personaje.getPasivaDeAtaque() && loc.getId() == 6;
		boolean profesora = personaje.getId() == 123 && !personaje.getUsado() && !personaje.getPasivaDeAtaque() && loc.getId() == 2;
		
		//USAMOS EL MENOR DADO POSIBLE
		int dado = -1;
		if(madre | quimico | profesora) {	//SI ES LA MADRE Y ESTA EN LA COLONIA GASTAMOS UN 1 SI ES POSIBLE
			dado = valorDado(1);
		}else if(sheriff) {
			dado = valorDado(4);
		}else {
			dado = valorDado(personaje.getAtaque());
			System.out.println("Ataque de: " + personaje.getId() + " con valor "+ personaje.getAtaque());
		}
		
		//SI EL DADO ES VÁLIDO LO USAMOS Y HACEMOS TIRADA DE RIESGO
		if(dado == -1) {
			throw new DadoException("Tus dados son muy pequeños");
		}else {
			//MANDA UN ERROR SI NO HAY ZOMBIES
			res = loc.matarZombie();
			//EN LA COLONIA SE AÑADE LA ID DE LA PUERTA EN EL MISMO METODO
			if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
				salida += Integer.toString(loc.getId()) + "|";
			}
			salida += res;
			
			
			//MIRAMOS SI EL SUPERVIVIENTE TIENE UNA ESCOPETA. NO AÑADE RUIDO SI YA HAY 4
			if(!quimico && personaje.tieneEquipado(11) && !personaje.usado(11)) {
				personaje.usar(11);
				salida += "|";
				res = loc.matarZombie();
				if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
					salida += Integer.toString(loc.getId()) + "|";
				}
				salida += res;
				int ruido = loc.getTokensDeRuido();
				if(ruido != 4) {
					loc.setTokensDeRuido(ruido + 1);
				}
			}
			
			//SI ES LA MADRE Y ESTA EN LA COLONIA MATA DOBLE. EL SHERIFF MATA DOBLE EN CUALQUIER SITIO
			if(madre || sheriff) {
				try {
					personaje.setPasivaDeAtaque(false);//RESETEAMOS LA HABILIDAD DEL SHERIFF
					personaje.setUsado(true);
					res =  loc.matarZombie();
					salida += "|";
					if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
						salida += Integer.toString(loc.getId()) + "|";
					}
					salida += res;
				}catch(MatarException e) {}
			}
			
			if(quimico) {
				try {
					personaje.setPasivaDeAtaque(false);//RESETEAMOS LA HABILIDAD DEL QUIMICO
					personaje.setUsado(true);
					res = loc.matarZombie();
					salida += "|";
					if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
						salida += Integer.toString(loc.getId()) + "|";;
					}
					salida += res;
				}catch (MatarException e) {}
				try {
					res = loc.matarZombie();
					salida += "|";
					if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
						salida += Integer.toString(loc.getId()) + "|";;
					}
					salida += res;
				}catch (MatarException e) {}
			}
			
			//SI NO HABIA ZOMBIES NO HACEMOS TIRADA DE RIESGO (DEVUELVE -1)
			if(personaje.tiraAlAtacar() && !personaje.tieneEquipado(9) && !personaje.tieneEquipado(10) && !profesora) {
				riesgo = tiradaRiesgo(personaje);
			}else if(!profesora && !quimico) {
				if(personaje.tieneEquipado(9) && !personaje.usado(9)) {
					personaje.usar(9);
				}else if(personaje.tieneEquipado(10) && !personaje.usado(10)) {
					personaje.usar(10);
				}else {
					riesgo = tiradaRiesgo(personaje);
				}
			}
			//MIRAMOS SI TIENE EQUIPADO UN FRANCOTIRADOR O SI ES LA MADRE
			if(!madre && !personaje.tieneEquipado(9) || personaje.tieneEquipado(9) && personaje.usado(9)) {
				dados.usar(dado);
			}else if(personaje.tieneEquipado(9) && !personaje.usado(9)) {	//SI TIENE EQUIPADO UN FRANCOTIRADOR Y NO LO HA USADO
				personaje.usar(9);
			}
		}
		
		if(objetivo.getId() == 0) {
			objetivo.actualizar(0);
		}
		
		salida += "|" + Integer.toString(dado) + "|" + Integer.toString(riesgo);
		
		return salida;
	}
	
	public String barricada(int id) throws BarricadaException, DadoException {
		Carta_Supervivientes personaje = getSupConId(id);
		Localizacion loc = localizacion(personaje);
		int dado = valorDado(1);
		String res = loc.ponerBarricada();
		
		//SI EL PERSONAJE TIENE EQUIPADO EL LIBRO PARA PONER BARRICADAS
		if((personaje.tieneEquipado(20) && !personaje.usado(20)) || personaje.getId() == 104) {
			personaje.usar(20);
			dado = -1;
		}else if(dado == -1) {
			res = null;
		}else{
			dados.usar(dado);
		}
		
		String salida = "";
		//EN LA COLONIA SE AÑADE LA ID DE LA PUERTA EN EL MISMO METODO
		if(!tablero.getColonia().getSupervivientes().containsValue(personaje)) {
			salida += Integer.toString(loc.getId()) + "|";
		}

		 salida += res + "|" + Integer.toString(dado);

		
		return salida;
	}
	
	public String buscar(int id) throws BuscarException, DadoException {
		boolean evento = false;
		Carta_Supervivientes personaje = getSupConId(id);
		String salida = "";
		Carta aux;
		int dado = 0;
		locCartas = localizacion(personaje);
		int cartasBuscadas = 0;
		
		//VARIABLES PARA LAS HABILIDADES DE LOS SUPERVIVIENTES. USAMOS LA SEGUNDA VARIABLE PARA COMPROBAR QUE NO SEA UNA BUSQUEDA NORMAL Y SE ESTÉ USANDO LA HABILIDAD
		boolean bombero = personaje.getId() == 114 && personaje.getPasivaDeBusqueda() && !personaje.getUsado();
		boolean camarera = personaje.getId() == 119 && !personaje.getPasivaDeBusqueda() && !personaje.getUsado();
		
		if(!bombero) {
			 dado = valorDado(personaje.getBusqueda());
		}
		
		if(getLocalizacion(6).equals(localizacion(personaje))){
			throw new BuscarException("No puedes buscar en la colonia");
		}
		
		//SI HAY DADO Y EL SUPERVIVIENTE NO ESTA EN LA COLONIA
		if(dado == -1) {
			throw new DadoException("Tus dados son muy pequeños");
		}else {
			if(locCartas.getMazo().vacio()) {
				throw new BuscarException("El mazo esta vacio");
			}else {
				//COGEMOS LA CARTA Y LA AÑADIMOS AL MAZO
				
				if(bombero) {
					int i = 0;
					boolean enc = false;
					while(!enc && i < 4) {
						aux = locCartas.cogerCarta();
						
						if(aux.getId() == 6) {
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida += encontrado.getId();
							salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 6;
							enc = true;
							
						}else if(aux.getId() == 7){
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida += encontrado.getId();
							salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 7;
							
							tablero.getColonia().anyadirInutiles();
							enc = true;
						}else if(aux.getId() == 8) {
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida += encontrado.getId();
							salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 8;
							
							tablero.getColonia().anyadirInutiles();
							tablero.getColonia().anyadirInutiles();
							enc = true;
						}else {
							buffer.add(locCartas.cogerCarta());
						}
						i++;
					}
					
					resetBuffer();
					
				}else {
					aux = locCartas.cogerCarta();
					if(aux.getId() == 6) {
						Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
						mazoSuperviviente.add(encontrado);
						tablero.getColonia().anyadirSupervivientes(encontrado);
						salida += encontrado.getId();
						salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 6;
						evento = true;
						
					}else if(aux.getId() == 7){
						Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
						mazoSuperviviente.add(encontrado);
						tablero.getColonia().anyadirSupervivientes(encontrado);
						salida += encontrado.getId();
						salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 7;
						
						tablero.getColonia().anyadirInutiles();
						evento = true;
					}else if(aux.getId() == 8) {
						Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
						mazoSuperviviente.add(encontrado);
						tablero.getColonia().anyadirSupervivientes(encontrado);
						salida += encontrado.getId();
						salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 8;
						
						tablero.getColonia().anyadirInutiles();
						tablero.getColonia().anyadirInutiles();
						evento = true;
					}else {
						//SE AÑADE LA CARTA A UN BUFFER PARA ESPERAR CONFIRAMCIÓN DEL JUGADOR
						salida += aux.getId();
						buffer.add(aux);
						cartasBuscadas++;
					}
					
					dados.usar(dado);
					
					//SI EL PERSONAJE BUSCA DOBLE EN LA LOCALIZACIÓN O TIENE EQUIPADO UN PLANO
					//NOTA: SI AL BUSCAR LA SEGUNDA CARTA EL MAZO ESTA VACÍO NO SE MANDA ERROR
					if(!evento && !locCartas.getMazo().vacio() && ((!personaje.getUsado() && 
							personaje.doble() == locCartas.getId()) || (personaje.tieneEquipado((locCartas).getId() + 12) 
							&& !personaje.usado((getLocalizacion(personaje.doble())).getId() + 12)) || personaje.getId() == 111 || camarera)) {	
						aux = locCartas.cogerCarta();
						if(aux.getId() == 6) {
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida = Integer.toString(encontrado.getId());
							salida += "|" + tablero.getColonia().getPosicion(encontrado) + "|" + 6;
							
						}else if(aux.getId() == 7){
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida = Integer.toString(encontrado.getId());
							salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 7;
							
							tablero.getColonia().anyadirInutiles();
						}else if(aux.getId() == 8) {
							Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
							mazoSuperviviente.add(encontrado);
							tablero.getColonia().anyadirSupervivientes(encontrado);
							salida = Integer.toString(encontrado.getId());
							salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 8;
							
							tablero.getColonia().anyadirInutiles();
							tablero.getColonia().anyadirInutiles();
						}else {
							salida += "|" + Integer.toString(aux.getId());
							buffer.add(aux);
							if(!(personaje.getId() == 111)) {	//PARA EL CONTABLE
								cartasBuscadas++;
							}
						}
						if(camarera) {
							if(aux.getId() == 6) {
								Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
								mazoSuperviviente.add(encontrado);
								tablero.getColonia().anyadirSupervivientes(encontrado);
								salida = Integer.toString(encontrado.getId());
								salida += "|" + tablero.getColonia().getPosicion(encontrado) + "|" + 6;
								
							}else if(aux.getId() == 7){
								Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
								mazoSuperviviente.add(encontrado);
								tablero.getColonia().anyadirSupervivientes(encontrado);
								salida = Integer.toString(encontrado.getId());
								salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 7;
								
								tablero.getColonia().anyadirInutiles();
							}else if(aux.getId() == 8) {
								Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
								mazoSuperviviente.add(encontrado);
								tablero.getColonia().anyadirSupervivientes(encontrado);
								salida = Integer.toString(encontrado.getId());
								salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 8;
								
								tablero.getColonia().anyadirInutiles();
								tablero.getColonia().anyadirInutiles();
							}else {
								salida += "|" + Integer.toString(aux.getId());
								buffer.add(aux);
								cartasBuscadas = 1;
								}
							personaje.setPasivaDeBusqueda(true);
						}
						
						//YA HA USADO SU PASIVA (HABILIDAD)
						personaje.setUsado(true);
					}
				}
			}
		}
		
		if(objetivo.getId() == 1 && localizacion(personaje).getMazo().vacio()) {
			objetivo.actualizar(localizacion(personaje).getId());
		}
		
		if(!bombero) {
			salida += "|" + Integer.toString(cartasBuscadas) + "|" + Integer.toString(dado);
		}
		
		return salida;
	}
	
	public String hacerRuido() throws BuscarException {
		String salida = "";
		if(locCartas.getTokensDeRuido() < 4) {
			Carta aux = locCartas.cogerCarta();
			locCartas.setTokensDeRuido(locCartas.getTokensDeRuido() + 1);
			
			if(aux.getId() == 6) {
				//SUPERVIVIENTE | CASILLA EN COLONIA | ID LOC | RUIDO
				Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
				mazoSuperviviente.add(encontrado);
				tablero.getColonia().anyadirSupervivientes(encontrado);
				salida = Integer.toString(encontrado.getId());
				salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 6;
				
			}else if(aux.getId() == 7){
				Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
				mazoSuperviviente.add(encontrado);
				tablero.getColonia().anyadirSupervivientes(encontrado);
				salida = Integer.toString(encontrado.getId());
				salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 7;
				
				tablero.getColonia().anyadirInutiles();
			}else if(aux.getId() == 8) {
				Carta_Supervivientes encontrado = supervivientes.getSupervienteAleatorio();
				mazoSuperviviente.add(encontrado);
				tablero.getColonia().anyadirSupervivientes(encontrado);
				salida = Integer.toString(encontrado.getId());
				salida += "|" + tablero.getColonia().getPosicion(encontrado)+ "|" + 8;
				
				tablero.getColonia().anyadirInutiles();
				tablero.getColonia().anyadirInutiles();
			}else {
				//Saca la carta seleccionada del buffer
				for(Carta c : buffer) {
					salida += c.getId() + "|";
				}
				
				salida += aux.getId();
				buffer.add(aux);
			}
			System.out.println("La salida es:" + salida);
		}else {
			throw new BuscarException("Ya no se puede hacer más ruido en esta localización");
		}
		
			//cartas|loc|casilla
		return salida + "|" + locCartas.getId() + "|" + (locCartas.getTokensDeRuido() - 1);
	}
	
	public void confirmarCarta(int idCarta) {
		Iterator<Carta> iter = buffer.iterator();
		boolean encontrado = false;
		
		while(!encontrado && iter.hasNext()) {
			Carta aux = iter.next();
			if(aux.getId() == idCarta) {
				mazoObjeto.add(aux);
				buffer.remove(aux);
				encontrado = true;
			}
		}
	}
	
	public void resetBuffer() {
		int cantidad = buffer.size();
		for(int i = 0; i < cantidad; i++) {
			locCartas.getMazo().anyadirAlFinal(buffer.remove(0));
		}
	}
	
	public String mover(int id, int l) throws MoverException {
		Carta_Supervivientes personaje = getSupConId(id);
		Localizacion lugar = getLocalizacion(l);
		int dado = 0;
		int posicion = -1;
		
		if(!personaje.getMovido()) {
			//INTENTA MOVER SI HAY CASILLAS LIBRE Y SI EL PERSONAJE NO ESTA YA EN ESE LUGAR
			if(lugar.getSupervivientes().size() < lugar.getMaximo() && !lugar.getSupervivientes().containsValue(personaje)) {
				localizacion(personaje).irse(personaje);
				posicion = lugar.llegar(personaje);
				
				if(personaje.tiraAlMoverse()) {
					if(!gasolina) {
						dado = tiradaRiesgo(personaje);
					}else {
						gasolina = false;
					}
				}
				personaje.setMovido(true);
			}
		}else {
			throw new MoverException("Solo puedes moverte una vez por ronda");
		}
		
		if(posicion == -1) {
			throw new MoverException("Ya estás en esta posición");
		}
		
		return Integer.toString(posicion) + "|" + Integer.toString(dado);
	}

}
