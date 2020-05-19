package dow.Partida;

import static org.junit.Assert.*;
import org.junit.*;
import dow.Cartas.*;
import dow.Excepciones.*;

public class VerificacionYPruebas {
	
	Localizacion l;
	Jugador j;
	Carta_Supervivientes superviviente;
	
	@Before
	public void inicializarLocalizacion() {
		this.l = new Localizacion("Prueba.", null, 3, 3);
		this.j = new Jugador("Jugador de prueba.", 0, null, new Tablero(1, null, null, null,
				null, null, null, null));
		this.superviviente = new Carta_Supervivientes(0, "Superviviente de prueba.", "Descripci�n.",
				0,0,"Habilidad.",0);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////PRIMER CASO: LOCALIZACI�N RECI�N INICIALIZADA (0 BARRICADAS Y 0 ZOMBIES).
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombie() throws barricadaException {
		//Invocamos al m�todo que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Comprobamos que todas est�n vac�as, ya que no hay ning�n superviviente en susodicha localizaci�n.
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
				
			//A�adimos un superviviente.
			l.getSupervivientes().add(this.superviviente);
				
			//Invocamos al m�todo que actualiza las casillas zombie.
			l.actualizarCasillasZombiePasoDeRonda();
			
			//Comprobamos que todas est�n vac�as, ya que el zombie ha roto la barricada.
			for (CasillasZombie c : l.getCasillasZombie()) {
				assertEquals(false, c.getHayBarricada());
				assertEquals(false, c.getHayZombie());
			}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	////TERCER CASO: DOS BARRICADAS, TRES ZOMBIES Y UNA BARRICADA DESPU�S.
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCasillasZombieTercerCaso() throws barricadaException {
		//Ponemos dos barricadas
		l.ponerBarricada();
		l.ponerBarricada();
				
		//A�adimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().add(superviviente);
		}
			
		//Invocamos al m�todo que actualiza las casillas zombie.
		l.actualizarCasillasZombiePasoDeRonda();
		
		//Ponemos una barricada despu�s.
		l.ponerBarricada();
			
		/*Deber�a quedarnos un zombie en la pos. 0 y una barricada en la pos. 1, ya que:
			1. Las barricadas se ponen en las dos primeras posiciones.
			2. Dos zombies rompen las barricadas y el �ltimo consigue entrar.
			3. Se pone una barricada en la primera pos. libre, que es la 1 (en la 0 est� el zombie).
			*/
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
	public void testCasillasZombieCuartoCaso() throws barricadaException {
		//A�adimos tres supervivientes.
		for (int i = 0; i < l.getMaximo(); i++) {
			l.getSupervivientes().add(superviviente);
		}
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
		for (Carta_Supervivientes superviviente : l.getSupervivientes()) {
			assertEquals(true, superviviente.estaMuerto());
		}
	}
	
	@Test
	public void testJugador() throws dadoException {
		//A�adimos el superviviente al jugador con su respectivo dado.
		this.j.getMazoSuperviviente().add(superviviente);
		this.j.anyadirDados();
		
		//Comprobamos que se ha a�adido en la pos. 0
		assertEquals(0, this.j.indice(superviviente));
		
		//Comprobamos si se puede hacer una acci�n que requiera m�s de 1 punto.
		//Ser� falso ya que no hemos tirado el dado.
		assertEquals(false, this.j.hayValorDisponible(1));
		
		//Tiramos el dado
		this.j.getDados().get(0).tirarDado();
		
		//Comprobamos si se puede hacer una acci�n que requiera m�s de 1 punto.
		//Debe ser verdadero.
		assertEquals(true, this.j.hayValorDisponible(1));
		
		//Movemos al superviviente a la localizaci�n de prueba.
		l.llegar(this.j.getMazoSuperviviente().get(0));
		
		//Comprobamos si est� en dicha localizacion.
		assertEquals(true, l.esta(this.j.getMazoSuperviviente().get(0)));
		
		//Herimos tres veces al superviviente
		for (int i = 0; i < 3; ++i) {
			this.j.herir(superviviente);
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
		
		//Comprobamos si est� muerto (no deber�a).
		assertEquals(false, this.superviviente.estaMuerto());
		
		//Simulamos una ronda, donde la herida de congelamiento se propaga
		this.superviviente.congelamiento();
		
		//Comprobamos si est� muerto (s� deber�a).
		assertEquals(true, this.superviviente.estaMuerto());
	}
}
