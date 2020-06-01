package tests;
import static org.junit.Assert.*;
import org.junit.*;
import Cartas.*;
import Excepciones.*;
import Partida.*;

public class VerificacionYPruebas {
	
	Localizacion l;
	Jugador j;
	Carta_Supervivientes superviviente;
	Colonia c;
	
	@Before
	public void inicializarLocalizacion() {
		this.l = new Localizacion("Prueba.", null, 3, 3);
		this.j = new Jugador("Jugador de prueba.", 0, null, new Tablero(1, null, null, null,
				null, null, null, null));
		this.superviviente = new Carta_Supervivientes(0, "Superviviente de prueba.", "Descripción.",
				0,0,"Habilidad.",0);
		this.c = new Colonia(null, 10, 10, 3);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: LOCALIZACIÓN RECIÉN INICIALIZADA (0 BARRICADAS Y 0 ZOMBIES).
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombie() throws barricadaException {
		//Invocamos al método que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas están vacías, ya que no hay ningún superviviente en susodicha localización.
		for (CasillasZombie c : l.getCasillasZombie()) {
			assertEquals(false, c.getHayBarricada());
			assertEquals(false, c.getHayZombie());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////SEGUNDO CASO: UNA BARRICADA Y UN ZOMBIE.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieSegundoCaso() throws barricadaException {
		//Ponemos una barricada(se ponen en la primera casilla disponible, en este caso pos. 0).
			l.ponerBarricada();
				
			//Añadimos un superviviente.
			//l.getSupervivientes().add(this.superviviente);
				
			//Invocamos al método que actualiza las casillas zombie.
			l.actualizarCasillasZombiePasoDeRonda();
			
			//Comprobamos que hay una barricada seguida de un zombie.
			for (int i = 0; i < l.getCasillasZombie().size(); i++) {
				if (i == 0) {
					assertEquals(true, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
				} else if (i == 1) {
					assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(true, l.getCasillasZombie().get(i).getHayZombie());
				} else {
					assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
					assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
				}
			}
	} 
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TERCER CASO: DOS BARRICADAS, TRES ZOMBIES Y UNA BARRICADA DESPUÉS.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieTercerCaso() throws barricadaException {
		//Ponemos dos barricadas
		l.ponerBarricada();
		l.ponerBarricada();
				
		//Añadimos tres supervivientes.
		/*for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().add(superviviente);
		}*/
			
		//Invocamos al método que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Ponemos una barricada después.
		l.ponerBarricada();
			
		/*Debería quedarnos un zombie en la pos. 2 y una barricada en la pos. 0, ya que:
			1. Las barricadas se ponen en las dos primeras posiciones.
			2. El primer zombie entra en la casilla vacía y los otros dos, a falta de hueco, rompen las barricadas.
			3. Se pone una barricada en la primera pos. libre, que es la 0.
			*/
		for (int i = 0; i < l.getCasillasZombie().size(); ++i) {
			if (i == 0) {
				assertEquals(true, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
			} else if (i == 1) {
				assertEquals(false, l.getCasillasZombie().get(i).getHayBarricada());
				assertEquals(false, l.getCasillasZombie().get(i).getHayZombie());
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
	public void testCasillasZombieCuartoCaso() throws barricadaException {
		//Añadimos tres supervivientes.
		/*for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().add(superviviente);
		}*/
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
		/*for (Carta_Supervivientes superviviente : l.getSupervivientes()) {
			assertEquals(true, superviviente.estaMuerto());
		}*/
	}
	
	@Test
	public void testJugador() throws dadoException {
		//Añadimos el superviviente al jugador con su respectivo dado.
		this.j.getMazoSuperviviente().add(superviviente);
		this.j.anyadirDados();
		
		//Comprobamos que se ha añadido en la pos. 0
		assertEquals(0, this.j.indice(superviviente));
		
		//Comprobamos si se puede hacer una acción que requiera más de 1 punto.
		//Será falso ya que no hemos tirado el dado.
		assertEquals(false, this.j.hayValorDisponible(1));
		
		//Tiramos el dado
		this.j.getDados().get(0).tirarDado();
		
		//Comprobamos si se puede hacer una acción que requiera más de 1 punto.
		//Debe ser verdadero.
		assertEquals(true, this.j.hayValorDisponible(1));
		
		//Movemos al superviviente a la localización de prueba.
		l.llegar(this.j.getMazoSuperviviente().get(0));
		
		//Comprobamos si está en dicha localizacion.
		assertEquals(true, l.esta(this.j.getMazoSuperviviente().get(0)));
		
		//Herimos tres veces al superviviente
		for (int i = 0; i < 3; ++i) {
			this.j.herir(superviviente);
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
	////PRIMER CASO: TRES SUPERVIVIENTES
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test
	public void testColoniaPrimerCaso() {
		//Añadimos tres supervivientes.
		for (int i = 0; i < 3; ++i) {
			this.c.llegar(this.superviviente);
		}

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
	////SEGUNDO CASO
	///////////////////////////////////////////////////////////////////////////////////////////////

	
	@Test
	public void testColoniaSegundoCaso() throws barricadaException, matarException {
		
		//Añadimos 12 supervivientes.
		for (int i = 0; i < 12; ++i) {
			this.c.llegar(this.superviviente);
		}
		
		//Pasamos ronda
		this.c.actualizarCasillasZombiePasoDeRonda();
		
		//Combrobamos que hay un zombie en la pos. 0 de cada puerta (12/2 = 6).
		for (int i = 0; i < 6; i++) {
			assertEquals(true, this.c.getPuertas().get(i).get(0).getHayZombie());
			assertEquals(false, this.c.getPuertas().get(i).get(1).getHayZombie());
			assertEquals(false, this.c.getPuertas().get(i).get(2).getHayZombie());
		}
		
		//Añadimos dos barricadas en la puerta 0.
		this.c.barricada(0);
		this.c.matar(0);
		this.c.barricada(0);
		
		//Debería quedar: 0) nada 1) barricada 2) nada
		assertEquals(true, this.c.getPuertas().get(0).get(0).getHayBarricada());
		assertEquals(true, this.c.getPuertas().get(0).get(1).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(2).getHayBarricada());
		assertEquals(false, this.c.getPuertas().get(0).get(2).getHayZombie());
		
	}
	
}
