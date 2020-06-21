package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.*;
import org.junit.*;
import Cartas.*;
import Excepciones.MoverException;
import Partida.*;

public class VerificacionYPruebas {
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////INICIALIZACIÓN DE OBJETOS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	Localizacion l;
	Jugador j;
	Carta_Supervivientes superviviente;
	Carta_Supervivientes superviviente1;
	Carta_Supervivientes superviviente2;
	Colonia c;
	Tablero t;
	Principal p;
	Objetivo_Principal op;
	List<Carta> mazoObjeto;
	
	@Before
	public void inicializarLocalizacion() {
		this.l = new Localizacion(null,4,3,1,1);
		this.superviviente = new Carta_Supervivientes(130,2,4,30,"Superviviente");
		this.op = new Objetivo_Principal(1);
		
		this.t = new Tablero(5, null,null,null,null,null,null,this.op);
		
		this.mazoObjeto = new ArrayList<Carta>();
		for (int i = 0; i < 5; i++) {
			this.mazoObjeto.add(new Carta(3));
		}
		this.j = new Jugador(1, this.mazoObjeto, this.t, this.op, new InicSupervivientes());
		this.c = new Colonia(this.op,5);
		
		this.p = new Principal(1);
		this.p.inicPartida(5);
		this.p.pasaTurno(0);
		
		//Le añadimos dos supervivientes a cada jugador.
			//HAY QUE AÑADIR LOS SUPERVIVIENTES MANUALMENTE.
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

	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: LOCALIZACIÓN RECIÉN INICIALIZADA (0 BARRICADAS Y 0 ZOMBIES).
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombie() {
		//Invocamos al método que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas las casillas siguen igual, ya que no hay ningún superviviente.
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
				
			//Añadimos un superviviente.
			l.getSupervivientes().put(0, this.superviviente);
				
			//Invocamos al método que actualiza las casillas zombie.
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
	////TERCER CASO: DOS BARRICADAS, TRES ZOMBIES Y UNA BARRICADA DESPUÉS.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieTercerCaso() throws BarricadaException {
		//Ponemos dos barricadas
		l.ponerBarricada();
		l.ponerBarricada();
				
		//Añadimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().put(i,this.superviviente);
		}
			
		//Invocamos al método que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Ponemos una barricada después.
		try {
			l.ponerBarricada();
		} catch (BarricadaException e) {
			System.err.println(e.getMessage());
		}
		
		/*Debería quedarnos un zombie en la pos. 0 y una barricada en la pos. 1 */
		for (int i = 0; i < l.getCasillasZombie().size(); ++i) {
			if (i == 0) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
			} else if (i == 1) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
			} else {
				assertEquals(false,l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////ÚLTIMO CASO: "OVERFLOW" DE ZOMBIES Y MUERTE DE SUPERVIVIENTES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieCuartoCaso() {		
		//Añadimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().put(i,this.superviviente);
		}
		
		//Actualizamos las casillas zombies, de manera que quedan las tres casillas llenas de zombies.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Actualizamos de nuevo. Al haber tres zombies y tres supervivientes, por cada excedente de zombie
		//se elimina a un superviviente, de manera que si están todas las casillas llenas de zombies y 
		//entran otros tres, se "comerán" a todos los supervivientes de la localización, que también son tres,
		//quedando todos los supervivientes muertos.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas las casillas están ocupadas.
		for (CasillasZombie c : l.getCasillasZombie()) {
			assertEquals(false, c.getHayBarricada());
			assertEquals(true, c.getHayZombie());
		}
		
		//Comprobamos que todos los supervivientes están muertos
		for (Map.Entry<Integer, Carta_Supervivientes> superviviente : l.getSupervivientes().entrySet()) {
			assertEquals(true, superviviente.getValue().estaMuerto());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST SOBRE LA CLASE JUGADOR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testJugador() {
		//Añadimos el superviviente al jugador con su respectivo dado.
		this.j.getMazoSuperviviente().add(superviviente);

		//Movemos al superviviente a la localización de prueba.
		l.llegar(this.j.getMazoSuperviviente().get(0));
		
		//Comprobamos si está en dicha localizacion.
		assertEquals(true, l.esta(this.j.getMazoSuperviviente().get(0)));
	}
	
	@Test
	public void testJugador2() {
		for (int i = 0; i < 1000; i++) {
			//No añade dados.
			this.j.addSuperviviente(this.superviviente);
		}
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
		
		//Comprobamos el número de heridas.
		assertEquals(1, this.superviviente.getHeridas());
		
		//Herimos de congelación.
		this.superviviente.recibirHerida(true);
		this.superviviente.congelamiento();
		
		assertEquals(1, this.superviviente.getCongelamiento());
		assertEquals(2, this.superviviente.getHeridas());
		
		//Comprobamos si está muerto (no debería).
		assertEquals(true, this.superviviente.estaMuerto());
		
		//Simulamos una ronda, donde la herida de congelamiento se propaga
		this.superviviente.congelamiento();
		
		//Comprobamos si está muerto (sí debería).
		assertEquals(3, this.superviviente.getHeridas());
		assertEquals(true, this.superviviente.estaMuerto());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: COLONIA
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testColoniaPrimerCaso() {
		//Añadimos tres supervivientes.
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
		//Añadimos 12 supervivientes.
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
		
		//Añadimos barricada, matamos al zombie y añadimos barricada.
		this.c.barricada(0);
		this.c.matar(0);
		this.c.barricada(0);
		
		//Debería quedar: 0) barricada 1) zombie 2) barricada
		assertEquals(true, this.c.getPuertas().get(0).get(0).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(0).getHayZombie());
		assertEquals(false, this.c.getPuertas().get(0).get(1).getHayBarricada());
		assertEquals(true, this.c.getPuertas().get(0).get(1).getHayZombie());
		assertEquals(true, this.c.getPuertas().get(0).get(2).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(2).getHayZombie());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////INICIAR JUGADORES: UNO DE MÁS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrincipalMasJugadores() {
		//No podemos iniciar una partida con más de 5 jugadores ni con menos de 1
		this.p.inicPartida(6);
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////INICIAR JUGADORES: NÚMERO NEGATIVO
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = NegativeArraySizeException.class)
	public void testPrincipalMenosJugadores() {
		this.p.inicPartida(-100);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST PRINCIPAL
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testPrincipal() throws BarricadaException, DadoException {
		//Comprobamos que todos los jugadores tienen dos supervivientes.
		for (int i = 0; i < 5; i++) {
			assertEquals(2, this.p.getJugador(i).getMazoSuperviviente().size());
		}
		
		//Comprobamos que los supervivientes se han añadido correctamente.
		assertEquals(100,this.p.getJugador(0).getMazoSuperviviente().get(0).getId());
		assertEquals(101,this.p.getJugador(0).getMazoSuperviviente().get(1).getId());

		//3 dados (+ 2 barras).
		assertEquals(5,this.p.getDados(0).length());
		
		this.p.ponerBarricada(100);
		
		assertEquals(true, this.p.getJugador(0).getTablero().getColonia().getPuertas().get(0).get(0).getHayBarricada());
		
		//2 dados (+ 1 barra).
		assertEquals(2,this.p.getJugador(0).getDados().getDados().size());
		
		this.p.ponerBarricada(100);
		
		//1 dado.
		assertEquals(1,this.p.getJugador(0).getDados().getDados().size());
		
		this.p.ponerBarricada(100);
		
		assertEquals(0,this.p.getJugador(0).getDados().getDados().size());
		
		this.p.ponerBarricada(100);
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////MOVER
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testPrincipalMover() throws MoverException {
			//Movemos e superviviente 100 a la comisaría.
			String s = this.p.mover(100, 0);
			String[] resultado = s.split("\\|");
			assertEquals(Integer.parseInt(resultado[0]), 100);
			assertEquals(Integer.parseInt(resultado[1]), 0);
			assertEquals(Integer.parseInt(resultado[2]), 0);
			//Testear tirada de riesgo
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////CRISIS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCrisis() {
			Crisis crisis = new Crisis(300,5);
			
			this.p.setCrisis(crisis);
			
			for (int i = 0; i < 5; i++) {
				this.p.getJugador(0).darCarta(3);
			}
			
			for (int i = 0; i < 5; i++) {
				this.p.aportarCrisis(3);
			}
			
			this.p.aportarCrisis(4);
			this.p.aportarCrisis(3);
			this.p.aportarCrisis(3);
			this.p.aportarCrisis(3);
			
			//Crisis pasada y de sobra ya que introducimos 8(correcto) - 1(incorrecto) = 7 -> 5 + 2
			assertEquals(true, this.p.getCrisisActual().pasada());
			
			assertEquals(true, this.p.getCrisisActual().sobra());
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////BARRICADA CON CASILLAS LLENAS DE ZOMBIES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = BarricadaException.class)
	public void ponerBarricadaCasillasZombieLlenas() throws MoverException, BarricadaException, DadoException {

		//Forzamos que no se realice tirada de riesgo.
		this.p.getJugador(0).getTablero().getColonia().irse(p.getJugador(0).getMazoSuperviviente().get(0));
		this.p.getJugador(0).getTablero().getComisaria().llegar(p.getJugador(0).getMazoSuperviviente().get(0));
			
		for (int i = 1; i < 4; i++) {
			this.p.getJugadorActual().getTablero().getComisaria().getCasillasZombie().get(i).setHayZombie(true);
		}

		this.p.ponerBarricada(100);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////NO PODEMOS MATAR DONDE NO HAY ZOMBIES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test 
	public void testMatarException() throws MatarException, DadoException {
		try {
		this.p.getJugador(0).atacar(100);
		} catch (MatarException m) {} 
		  catch (DadoException d) {}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////NO PODEMOS MATAR DONDE NO HAY ZOMBIES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test 
	public void testMatar() throws MatarException, MoverException {
		//Forzamos que no se realice tirada de riesgo.
		this.p.getJugador(0).getTablero().getColonia().irse(p.getJugador(0).getMazoSuperviviente().get(0));
		this.p.getJugador(0).getTablero().getComisaria().llegar(p.getJugador(0).getMazoSuperviviente().get(0));
		
		//Localización | Jugador | Dado
		try {
			String s = this.p.atacar(100);
			String[] resultado = s.split("\\|");
			assertEquals(Integer.parseInt(resultado[0]), 0);
			assertEquals(Integer.parseInt(resultado[1]), 0);
			assertTrue(Integer.parseInt(resultado[2]) >=  0 && Integer.parseInt(resultado[2]) <  3);
		} catch (DadoException d) {}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////NO PODEMOS HACER RUIDO MÁS DE CUATRO VECES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = BuscarException.class)
	public void testExcesoDeRuido() throws BuscarException, DadoException {
		//Añadimos un superviviente que siempre realiza la búsqueda tenga los dados que tenga.
		this.p.addSuperviviente(0, 127);
		
		//Movemos al superviviente sin realizar tirada de riesgo.
		this.p.getJugador(0).getTablero().getColonia().irse(p.getJugador(0).getMazoSuperviviente().get(2));
		this.p.getJugador(0).getTablero().getComisaria().llegar(p.getJugador(0).getMazoSuperviviente().get(2));
		
		//El límite de fichas de ruido es 4, por lo que salta excepción.
		this.p.buscar(127);
		for (int i = 0; i < 5; i++) {
			this.p.hacerRuido();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////APORTACIONES DE CRISIS
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test 
	public void testAportacionesCrisis() {
		
		//Comprobamos que el mazo de objetos se inicializa con cinco cartas.
		assertEquals(5, this.p.getJugadorActual().getMazoObjetos().size());
		
		//Aportamos todas las cartas que tiene en mano el jugador actual
		int idObjeto = 0;
		System.out.println(j);
		for (int i = 4; i > -1; i--) {
			idObjeto = this.p.getJugadorActual().getMazoObjetos().get(i).getId();
			this.p.aportarCrisis(idObjeto);
			assertEquals(i, this.p.getJugadorActual().getMazoObjetos().size());
		}
		
		//Comprobamos si se ha superado la crisis.
		if (this.p.getCrisisActual().getActuales() >= this.p.getCrisisActual().getNecesarias()) {
			assertEquals(true, this.p.getCrisisActual().pasada());
		} else {
			assertEquals(false, this.p.getCrisisActual().pasada());
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////USO DE DADOS EN ACCIÓN: BUSCAR
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testBuscarDados() throws BuscarException, DadoException {
		//Le damos el turno al segundo jugador (tiene una carta con muchos puntos de búsqueda,
		//lo cual es intersante desde el punto de vista del testeo).
		this.p.pasaTurno(2);
		
		//Movemos susodicho superviviente manualmente, esto es: sin realizar la tirada de riesgo.
		this.p.getJugador(2).getTablero().getColonia().irse(p.getJugador(2).getMazoSuperviviente().get(0));
		this.p.getJugador(2).getTablero().getComisaria().llegar(p.getJugador(2).getMazoSuperviviente().get(0));
		
		//Comprobamos que el jugador tiene tres dados.
		assertEquals(3, this.p.getJugadorActual().getDados().getDados().size());
		
		//Comprobamos si existe un valor que nos permite realizar la acción de búsqueda.
		boolean res = false;
		for (int i = 0; i < 3; i++) {
			if (this.p.getJugador(2).getDados().getValor(i) > 4) {
				res = true;
			}
		}
		
		//Si encontramos susodicho valor, realizamos la acción.
		if (res) {
			String s = this.p.buscar(104);
			String[] resultado = s.split("\\|");
			System.out.println(s);

			assertEquals(4, Integer.parseInt(resultado[1]));
			assertEquals(8, Integer.parseInt(resultado[2]));
			assertEquals(0, Integer.parseInt(resultado[3]));
			assertTrue(Integer.parseInt(resultado[4]) >=  0 && Integer.parseInt(resultado[4]) <  3);
			assertEquals(2, this.p.getJugadorActual().getDados().getDados().size());
			
		//Si no lo encontramos, salta la excepción.
		} else {
			Exception e = assertThrows(DadoException.class, () -> this.p.buscar(104));
			assertEquals("Tus dados son muy pequeños", e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////USO DE DADOS EN ACCIÓN: ATACAR
	///////////////////////////////////////////////////////////////////////////////////////////////
		
	@Test
	public void testAtacarDados() throws BuscarException, DadoException, MatarException {
		//Movemos el superviviente a la comisaría.
		this.p.getJugador(0).getTablero().getColonia().irse(p.getJugador(0).getMazoSuperviviente().get(0));
		this.p.getJugador(0).getTablero().getComisaria().llegar(p.getJugador(0).getMazoSuperviviente().get(0));
		
		//Nos aseguramos que el jugador que posee susodicho superviviente tiene 3 dados.
		assertEquals(3, this.p.getJugadorActual().getDados().getDados().size());
			
		//Comprobamos si hay algún valor de dado que nos permita realizar la acción.
		boolean res = false;
		for (int i = 0; i < 3; i++) {
			if (this.p.getJugador(0).getDados().getValor(i) > 4) {
				res = true;
			}
		}
			
		//Si se ha encontrado, será el menor valor posible y se consumirá.
		if (res) {
			String s = this.p.atacar(100);
			String[] resultado = s.split("\\|");
			assertTrue(Integer.parseInt(resultado[2]) >=  0 && Integer.parseInt(resultado[2]) <  3);
			assertEquals(2, this.p.getJugadorActual().getDados().getDados().size());
			
		//De no encontrarse el dado, salta error.
		} else {
			Exception e = assertThrows(DadoException.class, () -> this.p.atacar(100));
			assertEquals("Tus dados son muy pequeños", e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TIRADA DE RIESGO
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void tiradaDeRiesgo() throws MoverException {
		//Guardamos el número de supervivientes en colonia y comprobamos que es 10.
		int supColonia =  this.p.getJugadorActual().getTablero().getColonia().getSupervivientes().size();
		assertEquals(10, supColonia);
		
		//Movemos al superviviente realizando tirada de riesgo.
		String s = this.p.mover(100, 0);
		String[] resultado = s.split("\\|");
		
		//Comprobamos que el superviviente ha abandonado la colonia.
		
		assertEquals(supColonia - 1, this.p.getJugadorActual().getTablero().getColonia().getSupervivientes().size());
		
		//Dependiendo de la tirada de riesgo...
		switch (Integer.parseInt(resultado[3])) {
		
		//Comprobamos que el superviviente ha llegado a la lozalización de manera satisfactoria.
		case 0:
			assertEquals(1, this.p.getJugadorActual().getTablero().getComisaria().getSupervivientes().size());
			assertEquals(0, this.p.getJugadorActual().getMazoSuperviviente().get(0).getHeridas());
			break;
			
		//Comprobamos que el superviviente llega con una herida.
		case 1:
			assertEquals(1, this.p.getJugadorActual().getMazoSuperviviente().get(0).getHeridas());
			assertEquals(1, this.p.getJugadorActual().getTablero().getComisaria().getSupervivientes().size());
			break;
			
		//Comprobamos que el superviviente llega con una herida de congelamiento.
		case 2: 
			assertEquals(1, this.p.getJugadorActual().getMazoSuperviviente().get(0).getCongelamiento());
			assertEquals(1, this.p.getJugadorActual().getTablero().getComisaria().getSupervivientes().size());
			break;
		
		//Comprobamos que el superviviente muere y no llega a ninguna localización
		case 3:
			assertEquals(1, this.p.getJugadorActual().getMazoSuperviviente().size());
			assertEquals(0, this.p.getJugadorActual().getTablero().getComisaria().getSupervivientes().size());
			break;
		}
	}
}