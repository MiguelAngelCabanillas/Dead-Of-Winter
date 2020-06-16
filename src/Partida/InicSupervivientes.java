package Partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Cartas.Carta_Supervivientes;

public class InicSupervivientes {

	private Map<Integer, Carta_Supervivientes> cartas = new HashMap<>();
	private Random r = new Random();
	
	public InicSupervivientes() {
		inicCartas();
	}
	
	private void inicCartas() {
		
		//BIBLIOTECARIA (100)
		Carta_Supervivientes biblio = new Carta_Supervivientes(100, 5, 4, 46, "Alexis Grey");
		biblio.setLoc(5);
		cartas.put(100, biblio);	
		
		//GRANJERO (101)
		Carta_Supervivientes granjero = new Carta_Supervivientes(101, 3, 3, 12, "Adrew Evans");
		granjero.setLoc(1);
		cartas.put(101, granjero);
		
		//ABOGADA (102) TODO habilidad
		Carta_Supervivientes abogada = new Carta_Supervivientes(102, 2, 2, 38, "Annaleigh Chan");
		cartas.put(102, abogada);
		
		//DIRECTOR (103)
		Carta_Supervivientes director = new Carta_Supervivientes(103, 4, 2, 62, "Arthur Thurston");
		director.setLoc(2);
		cartas.put(103, director);
		
		//OBRERA (104)	//TODO habilidad (intentar que la barricada se ponga en el lugar en el que está)
		Carta_Supervivientes obrera = new Carta_Supervivientes(104, 2, 5, 52, "Ashley Ross");
		cartas.put(104, obrera);
		
		//MADRE (105)	//TODO habilidad (preguntar si hacer como pasiva)
		Carta_Supervivientes madre = new Carta_Supervivientes(105, 2, 4, 34, "Bev Russell");
		cartas.put(105, madre);
		
		//CONSERJE (106)	//TODO habilidad (crear una variable para saber cuantas cartas se limpian del vertedero)
		Carta_Supervivientes conserje = new Carta_Supervivientes(106, 2, 4, 26, "Bandon Kane");
		conserje.setVertedero(5);
		cartas.put(106, conserje);
		
		//ALCALDE (107)	//TODO habilidad (crear un metodo para aumentar el dado)
		Carta_Supervivientes alcalde = new Carta_Supervivientes(107, 3, 4, 68, "Brian Lee");
		cartas.put(107, alcalde);
		
		//PREPARADOR (108)	//TODO habilidad (crear un contador de heridas?)
		Carta_Supervivientes preparador = new Carta_Supervivientes(108, 2, 4, 36, "Buddy Davis");
		cartas.put(108, preparador);
		
		//POLICIA (109)
		Carta_Supervivientes policia = new Carta_Supervivientes(109, 4, 2, 22, "Carla Thompsom");
		policia.setLoc(0);
		cartas.put(109, policia);
		
		//SHERIFF (110)	//TODO habilidad (crear una variable para matar doble)
		Carta_Supervivientes sher = new Carta_Supervivientes(110, 2, 5, 66, "Daniel Smith");
		cartas.put(110, sher);
		
		//CONTABLE (111) //TODO habilidad
		Carta_Supervivientes cont = new Carta_Supervivientes(111, 4, 3, 50, "David García");
		cartas.put(111, cont);
		
		//QUIMICO (112) //TODO habilidad
		Carta_Supervivientes quimico = new Carta_Supervivientes(112, 4, 3, 44, "Edward White");
		cartas.put(112, quimico);
		
		//NOEL (113)	//TODO va a ser horrible de hacer
		Carta_Supervivientes noel = new Carta_Supervivientes(113, 2, 5, 14, "Forest Plum");
		cartas.put(113, noel);
		
		//BOMBERO (114)	//TODO ni idea
		Carta_Supervivientes bombero = new Carta_Supervivientes(114, 2, 3, 60, "Gabriel Díaz");
		cartas.put(114, bombero);
		
		//PIRATA (115) //TODO dificil
		Carta_Supervivientes pirata = new Carta_Supervivientes(115, 1, 4, 16, "Gray Beard");
		cartas.put(115, pirata);
		
		//GUARDA (116)	//TODO HABILIDAD
		Carta_Supervivientes guarda = new Carta_Supervivientes(116, 3, 3, 32, "Harman Brooks");
		cartas.put(116, guarda);
		
		//PSIQUIATRA (117)	//TODO dificil pero se puede hacer
		Carta_Supervivientes psi = new Carta_Supervivientes(117, 6, 3, 54, "James Meyers");
		cartas.put(117, psi);
		
		//ENFERMERA (118)
		Carta_Supervivientes enfermera = new Carta_Supervivientes(118, 3, 4, 42, "Janet Taylor");
		enfermera.setLoc(4);
		cartas.put(118, enfermera);
		
		//CAMARERA (119) //TODO esta va a ser horrible
		Carta_Supervivientes camarera = new Carta_Supervivientes(119, 4, 3, 24, "Jenny Clark");
		cartas.put(119, camarera);
		
		//ESTUDIANTE (120)//TODO ni de coña
		Carta_Supervivientes estudiante = new Carta_Supervivientes(120, 3, 3, 18, "John Price");
		director.setLoc(7);
		cartas.put(120, estudiante);
		
		//LEÑADOR (121)	//TODO se puede hacer pero es dificil
		Carta_Supervivientes lenador = new Carta_Supervivientes(121, 2, 2, 31, "Kodiak Colby");
		cartas.put(121, lenador);
		
		//COCINERA (122) //TODO facil de hacer
		Carta_Supervivientes cocinera = new Carta_Supervivientes(122, 2, 4, 20, "Loretta Clay");
		cartas.put(122, cocinera);
		
		//MAESTRA (123)	//TODO se puede hacer
		Carta_Supervivientes maestra = new Carta_Supervivientes(123, 4, 2, 48, "María López");
		cartas.put(123, maestra);
		
		//NINJA (124)
		Carta_Supervivientes ninja = new Carta_Supervivientes(124, 2, 4, 30, "Mike Cho");
		ninja.setTirarMatar(false);
		cartas.put(124, ninja);
		
		//DOCTORA (125)	//TODO se puede hacer facil
		Carta_Supervivientes doctora = new Carta_Supervivientes(125, 4, 3, 56, "Olivia Brown");
		cartas.put(125, doctora);
		
		//CAMIONERO (126)
		Carta_Supervivientes camionero = new Carta_Supervivientes(126, 3, 3, 40, "Rod Miller");
		camionero.setTirarMover(false);
		cartas.put(126, camionero);
		
		//PILOTO (127)	//TODO 
		Carta_Supervivientes piloto = new Carta_Supervivientes(127, 4, 1, 58, "Sophie Robinson");
		cartas.put(127, piloto);
		
		//PERRO (128)	//TODO MODIFICAR LA CLASE TIRADA DE RIESGO DE JUGADOR
		Carta_Supervivientes perro = new Carta_Supervivientes(128, 2, 2, 10, "Sparky");
		cartas.put(128, perro);
		
		//ADIVINA (129) //TODO complicada
		Carta_Supervivientes adivina = new Carta_Supervivientes(129, 3, 1, 28, "Talia Jones");
		cartas.put(129, adivina);
		
		//SOLDADO (130)	//TODO MODIFICAR EL METODO ATACAR
		Carta_Supervivientes soldado = new Carta_Supervivientes(130, 1, 3, 64, "Thomas Heart");
		cartas.put(130, soldado);
		
	}
	
	public Carta_Supervivientes getSuperviviente(int id) {
		return cartas.get(id - 100);
	}
	
	public Map<Integer, Carta_Supervivientes> getSupervivientes(){
		return cartas;
	}
	
	public Carta_Supervivientes getSupervienteAleatorio() {
		Carta_Supervivientes aux = null;
		int id = 0;
		
		while(aux == null) {
			id = r.nextInt(31) + 99;
			aux = cartas.get(id);
		}
		
		cartas.remove(id);
		return aux;
	}
}
