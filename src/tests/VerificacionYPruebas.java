package tests;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.*;
import org.junit.jupiter.api.BeforeAll;

import Cartas.*;
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
		//this.superviviente = new Carta_Supervivientes(130,2,4,30);
		//this.superviviente1 = new Carta_Supervivientes(401,3,3,40);
		//this.superviviente2 = new Carta_Supervivientes(402,3,3,40);
		this.op = new Objetivo_Principal(1);
		
		this.t = new Tablero(5, null,null,null,null,null,null,this.op);
		this.j = new Jugador(1, null, this.t, null);
		this.c = new Colonia(this.op,5);
		this.p = new Principal(1);
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
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////SEGUNDO CASO: UNA BARRICADA Y UN ZOMBIE.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieSegundoCaso() {
		//Ponemos una barricada(se ponen en la primera casilla disponible, en este caso pos. 0).
		//	l.ponerBarricada();
				
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
	public void testCasillasZombieTercerCaso() {
		//Ponemos dos barricadas
		//l.ponerBarricada();
		//l.ponerBarricada();
				
		//Añadimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().put(i,this.superviviente);
		}
			
		//Invocamos al método que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Ponemos una barricada después.
		//l.ponerBarricada();
			
		/*Debería quedarnos un zombie en la pos. 0 y una barricada en la pos. 1 */
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
		this.j.anyadirDados();
		
		//Tiramos el dado
		this.j.getDados().get(0).tirarDado();
		
		//Movemos al superviviente a la localización de prueba.
		l.llegar(this.j.getMazoSuperviviente().get(0));
		
		//Comprobamos si está en dicha localizacion.
		assertEquals(true, l.esta(this.j.getMazoSuperviviente().get(0)));
		
		//Herimos tres veces al superviviente
		for (int i = 0; i < 3; ++i) {
			this.j.herir(this.superviviente, false);
		}
		
		//Comprobamos que está muerto.
		assertEquals(true, j.getMazoSuperviviente().get(0).estaMuerto());
		
		//Matamos al personaje
		l.irse(this.j.getMazoSuperviviente().get(0));
		j.matar();
		
		//Comprobamos que la lista se ha quedado vacía
		assertEquals(true ,j.getMazoSuperviviente().isEmpty());
		
		//Comprobamos si sigue en la localización de prueba
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
		
		//Comprobamos el número de heridas.
		assertEquals(1, this.superviviente.getHeridas());
		
		//Herimos de congelación.
		this.superviviente.recibirHerida(true);
		
		//Comprobamos si está muerto (no debería).
		assertEquals(false, this.superviviente.estaMuerto());
		
		//Simulamos una ronda, donde la herida de congelamiento se propaga
		this.superviviente.congelamiento();
		
		//Comprobamos si está muerto (sí debería).
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
		
		System.out.println(this.c.getSupervivientes().size());
		
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
	////INICIAR JUGADORES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrincipalMasJugadores() {
		//No podemos iniciar una partida con más de 5 jugadores ni con menos de 1
		this.p.inicPartida(6);
		
	}
	
	@Test (expected = NegativeArraySizeException.class)
	public void testPrincipalMenosJugadores() {
		this.p.inicPartida(-100);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////INICIALIZAR TAMAÑOS
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testTamanyoMazos() {
		this.p.inicPartida(5);
		
		//El mazo de supervivientes se encuentra vacío.
		for (int i = 0; i < 5; i++) {
			assertEquals(0, this.p.getJugador(i).getMazoSuperviviente().size());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TEST PRINCIPAL
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testPrincipal() {
		//Iniciamos partida con 5 jugadores y les entregamos los dados.
		this.p.inicPartida(5);
		
		//Comprobamos que cada jugador tiene sus dados.
				for (int i = 0; i < 5; i++) {
					assertEquals(1, this.p.getJugador(i).getDados().size());
				}
		
		//No tienen dados.
		assertEquals(null,this.p.getDados(0));
		
		//Inicializamos los dados.
		this.p.inicDados();
	
		//Tienen dados.
		assertNotEquals(null, this.p.getDados(0));
		
		//Comprobamos que el número de rondas restantes es 10 (todavía no hemos pasado de turno) (carta de objetivo = 1).
		assertEquals(6, this.p.getRondasRestantes());
		
		//Comprobamos que el número de moral es 5 (carta de objetivo = 1).
		assertEquals(6, this.p.getMoral());
		
		//Comprobamos que los ids de los jugadores están correctamente distribuidos.
		for (int i = 0; i < 5; i++) {
			assertEquals(i, this.p.getJugador(i).getId());
		}
				
		//Prueba de ataque.
		//Cogemos a un superviviente que no sea del jugador actual.
		this.p.getJugador(0).addSuperviviente(this.superviviente);
		
		int idSuperviviente = this.p.getJugador(0).getMazoSuperviviente().get(0).getId();
		
		assertEquals(130, idSuperviviente);
		
		int tirada = this.p.atacar(idSuperviviente);
		
		if (tirada > 5 && tirada < 11) {
			assertEquals(1,superviviente.getHeridas());
		} else if (tirada == 11) {
			assertEquals(true,superviviente.estaMuerto());
		}
	}
}
