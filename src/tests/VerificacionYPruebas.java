package tests;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.*;
import org.junit.jupiter.api.BeforeAll;

import Cartas.*;
import Excepciones.MoverException;
import Partida.*;

public class VerificacionYPruebas {
	
	Localizacion l;
	Jugador j;
	Carta_Supervivientes superviviente;
	Carta_Supervivientes superviviente1;
	Carta_Supervivientes superviviente2;
	Colonia c;
	Tablero t;
	Principal p;
	Objetivo_Principal op;
	
	@Before
	public void inicializarLocalizacion() {
		this.l = new Localizacion(null,4,3,1,1);
		this.superviviente = new Carta_Supervivientes(130,2,4,30,"Superviviente");
		this.op = new Objetivo_Principal(1);
		
		this.t = new Tablero(5, null,null,null,null,null,null,this.op);
		this.j = new Jugador(1, null, this.t, null);
		this.c = new Colonia(this.op,5);
		this.p = new Principal(1);
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: LOCALIZACI�N RECI�N INICIALIZADA (0 BARRICADAS Y 0 ZOMBIES).
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombie() {
		//Invocamos al m�todo que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas las casillas siguen igual, ya que no hay ning�n superviviente.
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
			} else if (i == 1) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
			} else {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
			}
		}
		
		l.anyadirZombie();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////SEGUNDO CASO: UNA BARRICADA Y UN ZOMBIE.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieSegundoCaso() throws BarricadaException {
		//Ponemos una barricada(se ponen en la primera casilla disponible, en este caso pos. 0).
		l.ponerBarricada();
				
			//A�adimos un superviviente.
			l.getSupervivientes().put(0, this.superviviente);
				
			//Invocamos al m�todo que actualiza las casillas zombie.
			l.actualizarCasillasZombiePasoDeRonda();
			
			//Comprobamos que hay una barricada seguida de un zombie.
			for (int i = 0; i < l.getCasillasZombie().size(); i++) {
				if (i == 0) {
					assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
				} else if (i == 1) {
					assertEquals(true, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
				} else {
					assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
				}
			}
	} 
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TERCER CASO: DOS BARRICADAS, TRES ZOMBIES Y UNA BARRICADA DESPU�S.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieTercerCaso() throws BarricadaException {
		//Ponemos dos barricadas
		l.ponerBarricada();
		l.ponerBarricada();
				
		//A�adimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().put(i,this.superviviente);
		}
			
		//Invocamos al m�todo que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Ponemos una barricada despu�s.
		try {
			l.ponerBarricada();
		}catch(BarricadaException e) {
			System.err.println(e.getMessage());
		}
		
			
		/*Deber�a quedarnos un zombie en la pos. 0 y una barricada en la pos. 1 */
		for (int i = 0; i < l.getCasillasZombie().size(); ++i) {
			if (i == 0) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
			} else if (i == 1) {
				assertEquals(true, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
			} else {
				assertEquals(false,l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////�LTIMO CASO: "OVERFLOW" DE ZOMBIES Y MUERTE DE SUPERVIVIENTES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieCuartoCaso() {		
		//A�adimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().put(i,this.superviviente);
		}
		
		System.out.println("este");
		//Actualizamos las casillas zombies, de manera que quedan las tres casillas llenas de zombies.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Actualizamos de nuevo. Al haber tres zombies y tres supervivientes, por cada excedente de zombie
		//se elimina a un superviviente, de manera que si est�n todas las casillas llenas de zombies y 
		//entran otros tres, se "comer�n" a todos los supervivientes de la localizaci�n, que tambi�n son tres,
		//quedando todos los supervivientes muertos.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas las casillas est�n ocupadas.
		for (CasillasZombie c : l.getCasillasZombie()) {
			assertEquals(false, c.getHayBarricada());
			assertEquals(true, c.getHayZombie());
		}
		
		//Comprobamos que todos los supervivientes est�n muertos
		for (Map.Entry<Integer, Carta_Supervivientes> superviviente : l.getSupervivientes().entrySet()) {
			assertEquals(true, superviviente.getValue().estaMuerto());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST SOBRE LA CLASE JUGADOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testJugador() {
		//A�adimos el superviviente al jugador con su respectivo dado.
<<<<<<< HEAD
		this.j.getMazoSuperviviente().add(superviviente);
=======
//		this.j.getMazoSuperviviente().add(superviviente);
//		this.j.anyadirDados();
//		
//		//Tiramos el dado
//		this.j.getDados().get(0).tirarDado();
>>>>>>> da732fcdd28e28b5341c57ad629e42ea95c721a1
		
		//Movemos al superviviente a la localizaci�n de prueba.
		l.llegar(this.j.getMazoSuperviviente().get(0));
		
		//Comprobamos si est� en dicha localizacion.
		assertEquals(true, l.esta(this.j.getMazoSuperviviente().get(0)));
		
		//Herimos tres veces al superviviente
		for (int i = 0; i < 3; ++i) {
			this.j.herir(this.superviviente, false);
		}
		
		//Comprobamos que est� muerto.
		assertEquals(true, j.getMazoSuperviviente().get(0).estaMuerto());
		
		//Matamos al personaje
		l.irse(this.j.getMazoSuperviviente().get(0));
		j.matar();
		
		//Comprobamos que la lista se ha quedado vac�a
		assertEquals(true ,j.getMazoSuperviviente().isEmpty());
		
		//Comprobamos si sigue en la localizaci�n de prueba
		assertEquals(false, l.esta(superviviente));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST SOBRE LA CLASE SUPERVIVIENTE
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testSuperviviente() {
		//Comprobamos las heridas
		assertEquals(0, this.superviviente.getHeridas());
		
		//Herimos una vez.
		this.superviviente.recibirHerida(false);
		
		//Comprobamos el n�mero de heridas.
		assertEquals(1, this.superviviente.getHeridas());
		
		//Herimos de congelaci�n.
		this.superviviente.recibirHerida(true);
		this.superviviente.congelamiento();
		
		assertEquals(2, this.superviviente.getHeridas());
		
		//Comprobamos si est� muerto (no deber�a).
		assertEquals(false, this.superviviente.estaMuerto());
		
		//Simulamos una ronda, donde la herida de congelamiento se propaga
		this.superviviente.congelamiento();
		
		//Comprobamos si est� muerto (s� deber�a).
		assertEquals(3, this.superviviente.getHeridas());
		assertEquals(true, this.superviviente.estaMuerto());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: COLONIA
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testColoniaPrimerCaso() {
		//A�adimos tres supervivientes.
			this.c.llegar(this.superviviente);
			this.c.llegar(this.superviviente1);
			this.c.llegar(this.superviviente2);
		
		//Pasamos ronda.
		this.c.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que hay un zombie en las tres primeras puertas
		for (int i = 0; i < 6; i++) {
			if (i < 2) {
				assertEquals(true, this.c.getPuertas().get(i).get(0).getHayZombie());
			} else {
				assertEquals(false, this.c.getPuertas().get(i).get(0).getHayZombie());
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////SEGUNDO CASO: COLONIA
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testColoniaSegundoCaso() {
		
		//A�adimos 12 supervivientes.
		for (int i = 0; i < 12; ++i) {
			this.c.llegar(this.superviviente);
		}
		
		
		//Pasamos ronda
		this.c.actualizarCasillasZombiePasoDeRonda();
		
		//Combrobamos que hay un zombie en la pos. 0 de cada puerta (12/2 = 6).
		for (int i = 0; i < 6; i++) {
			assertEquals(true, this.c.getPuertas().get(i).get(0).getHayZombie());
			if (i < 2) {
				assertEquals(true, this.c.getPuertas().get(i).get(1).getHayZombie());
			} else  {
				assertEquals(false, this.c.getPuertas().get(i).get(1).getHayZombie());
			}
			assertEquals(false, this.c.getPuertas().get(i).get(2).getHayZombie());
		}
		
		//A�adimos barricada, matamos al zombie y a�adimos barricada.
		this.c.barricada(0);
		this.c.matar(0);
		this.c.barricada(0);
		
		//Deber�a quedar: 0) barricada 1) zombie 2) barricada
		assertEquals(true, this.c.getPuertas().get(0).get(0).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(0).getHayZombie());
		assertEquals(false, this.c.getPuertas().get(0).get(1).getHayBarricada());
		assertEquals(true, this.c.getPuertas().get(0).get(1).getHayZombie());
		assertEquals(true, this.c.getPuertas().get(0).get(2).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(2).getHayZombie());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////INICIAR JUGADORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrincipalMasJugadores() {
		//No podemos iniciar una partida con m�s de 5 jugadores ni con menos de 1
		this.p.inicPartida(6);
		
	}
	
	@Test (expected = NegativeArraySizeException.class)
	public void testPrincipalMenosJugadores() {
		this.p.inicPartida(-100);
	}
	
	@Test 
	public void testExcepcionAnyadirSupervivientes() {
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST PRINCIPAL
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testPrincipal() throws BarricadaException {
		//Iniciamos partida con 5 jugadores e iniciamos el turno con el jugador de id 0.
		this.p.inicPartida(5);
		this.p.inicTurno();
		this.p.pasaTurno(0);

		//Le a�adimos dos supervivientes a cada jugador.
		//HAY QUE A�ADIR LOS SUPERVIVIENTES MANUALMENTE.
		int k = 100;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				this.p.addSuperviviente(i, k);
				k++;
			}
		}
		
<<<<<<< HEAD
		//Movemos todos los supervivientes a la colonia.
		this.p.inicSupervivientesEnColonia();
		
		//Comprobamos que todos los jugadores tienen dos supervivientes.
		for (int i = 0; i < 5; i++) {
			assertEquals(2, this.p.getJugador(i).getMazoSuperviviente().size());
		}
		
		//Comprobamos que los supervivientes se han a�adido correctamente.
		assertEquals(100,this.p.getJugador(0).getMazoSuperviviente().get(0).getId());
		assertEquals(101,this.p.getJugador(0).getMazoSuperviviente().get(1).getId());
=======
		//Comprobamos que cada jugador tiene sus dados.
//				for (int i = 0; i < 5; i++) {
//					assertEquals(1, this.p.getJugador(i).getDados().size());
//				}
//		
		//No tienen dados.
		assertEquals(null,this.p.getDados(0));
>>>>>>> da732fcdd28e28b5341c57ad629e42ea95c721a1
		
		//A�ADIR DADOS DESPU�S DE LOS SUPERVIVIENTES.
		//A�adimos los dados
		this.p.inicDados();
		
		//3 dados (+ 2 barras).
		assertEquals(5,this.p.getDados(0).length());
		
		this.p.ponerBarricada(100);
		
		//2 dados (+ 1 barra).
		assertEquals(3,this.p.getDados(0).length());
		
		this.p.ponerBarricada(100);
		
		//1 dado.
		assertEquals(1,this.p.getDados(0).length());
		
		//Pasamos la ronda, reiniciando los dados.
		this.p.pasaTurno(1);
		
		this.p.pasaRonda();
		
		this.p.pasaTurno(0);
		
		/*Comprobamos que el n�mero de dados se ha reseteado.
		assertEquals(5,this.p.getDados(0).length());
		System.out.println(this.p.getDados(0));
		
		//Valores correctos.
		for (int i = 0; i < 5; i++) {
			assertTrue(Integer.parseInt(this.p.getDados(i)) > 0);
			assertTrue(Integer.parseInt(this.p.getDados(i)) < 7);
		}
		
		//Comprobamos que el n�mero de rondas restantes es 6 (todav�a no hemos pasado de turno) (carta de objetivo = 1).
		assertEquals(6, this.p.getRondasRestantes());
		
		//Comprobamos que el n�mero de moral es 5 (carta de objetivo = 1).
		assertEquals(6, this.p.getMoral());
		
		//Comprobamos que los ids de los jugadores est�n correctamente distribuidos.
		for (int i = 0; i < 5; i++) {
			assertEquals(i, this.p.getJugador(i).getId());
		}
				
		/*Prueba de ataque.
		//Cogemos a un superviviente que no sea del jugador actual.
		this.p.getJugador(0).addSuperviviente(this.superviviente);
		
		int idSuperviviente = this.p.getJugador(0).getMazoSuperviviente().get(0).getId();
		
		assertEquals(130, idSuperviviente);
		
		/*assertEquals(1, this.p.getJugador(0).getMazoSuperviviente().size());
		
		int tirada = this.p.atacar(idSuperviviente);
		
		if (tirada > 5 && tirada < 11) {
			assertEquals(1,superviviente.getHeridas());
		} else if (tirada == 11) {
			assertEquals(true,superviviente.estaMuerto());
		}*/
	}
	
	@Test
	public void TestPrincipalMover() throws MoverException {
		this.p.inicPartida(5);
		this.p.inicTurno();
		this.p.pasaTurno(0);
		
		//Le a�adimos dos supervivientes a cada jugador.
			//HAY QUE A�ADIR LOS SUPERVIVIENTES MANUALMENTE.
			int k = 100;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 2; j++) {
					this.p.addSuperviviente(i, k);
					k++;
				}
			}
				
			//Movemos todos los supervivientes a la colonia.
			this.p.inicSupervivientesEnColonia();
				
			this.p.inicDados();
		
			//Movemos e superviviente 100 a la comisar�a.
			String s = this.p.mover(100, 0);
			String[] resultado = s.split("\\|");
			assertEquals(Integer.parseInt(resultado[0]), 100);
			assertEquals(Integer.parseInt(resultado[1]), 0);
			assertEquals(Integer.parseInt(resultado[2]), 0);
			//Testear tirada de riesgo
	}
	
	@Test
	public void TestPrincipalDos() throws MoverException {
		this.p.inicPartida(5);
		this.p.inicTurno();
		this.p.pasaTurno(0);
		
		//Le a�adimos dos supervivientes a cada jugador.
			//HAY QUE A�ADIR LOS SUPERVIVIENTES MANUALMENTE.
			int k = 100;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 2; j++) {
					this.p.addSuperviviente(i, k);
					k++;
				}
			}
				
			//Movemos todos los supervivientes a la colonia.
			this.p.inicSupervivientesEnColonia();
				
			this.p.inicDados();
	}
	
	@Test
	public void TestCrisis() {
		this.p.inicPartida(5);
		this.p.inicTurno();
		this.p.pasaTurno(0);
		
		//Le a�adimos dos supervivientes a cada jugador.
			//HAY QUE A�ADIR LOS SUPERVIVIENTES MANUALMENTE.
			int k = 100;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 2; j++) {
					this.p.addSuperviviente(i, k);
					k++;
				}
			}
				
			//Movemos todos los supervivientes a la colonia.
			this.p.inicSupervivientesEnColonia();
				
			this.p.inicDados();
			System.out.println(this.p.getJugador(0).getMazoObjetos().toString());
		
			Crisis crisis = new Crisis(300,5);
			
			this.p.setCrisis(crisis);
			
//			this.p.getJugador(0).darCarta(3);
//			assertEquals(true, this.p.getJugador(0).get);
			
			for (int i = 0; i < 5; i++) {
				this.p.getJugador(0).darCarta(3);
			}
			
			System.out.println(this.p.getJugador(0).getMazoObjetos().toString());
			//this.p.getCrisisActual().pasada();
			
			for (int i = 0; i < 5; i++) {
				this.p.aportarCrisis(3);
			}
			System.out.println(this.p.getJugador(0).getMazoObjetos().toString());
			
			this.p.aportarCrisis(4);
			this.p.aportarCrisis(3);
			this.p.aportarCrisis(3);
			this.p.aportarCrisis(3);
			
			for (int i = 0; i < 15; i++) {
				System.out.println(this.p.getCrisisActual().getDonaciones()[i]);
			}
			
			//Crisis pasada y de sobra ya que introducimos 8(correcto) - 1(incorrecto) = 7 -> 5 + 2
			assertEquals(true, this.p.getCrisisActual().pasada());
			
			assertEquals(true, this.p.getCrisisActual().sobra());
		}
	}
