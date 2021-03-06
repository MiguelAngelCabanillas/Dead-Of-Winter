package Partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Cartas.Carta_Supervivientes;

public class InicSupervivientes {

	private Map<Integer, Carta_Supervivientes> personajes = new HashMap<>();
	private List<Carta_Supervivientes> cartas = new ArrayList<>();
	private Random r = new Random();
	
	public InicSupervivientes() {
		inicCartas();
	}
	
	private void inicCartas() {
		
		//BIBLIOTECARIA (100)
		Carta_Supervivientes biblio = new Carta_Supervivientes(100, 5, 4, 46, "Alexis Grey");
		biblio.setLoc(5);
		cartas.add(biblio);	
		personajes.put(100, biblio);
		
		//GRANJERO (101)
		Carta_Supervivientes granjero = new Carta_Supervivientes(101, 3, 3, 12, "Adrew Evans");
		granjero.setLoc(1);
		cartas.add(granjero);
		personajes.put(101, granjero);
		
		//ABOGADA (102)
		Carta_Supervivientes abogada = new Carta_Supervivientes(102, 2, 2, 38, "Annaleigh Chan");
		cartas.add(abogada);
		personajes.put(102, abogada);
		
		//DIRECTOR (103)
		Carta_Supervivientes director = new Carta_Supervivientes(103, 4, 2, 62, "Arthur Thurston");
		director.setLoc(2);
		cartas.add(director);
		personajes.put(103, director);
		
		//OBRERA (104)
		Carta_Supervivientes obrera = new Carta_Supervivientes(104, 2, 5, 52, "Ashley Ross");
		cartas.add(obrera);
		personajes.put(104, obrera);
		
		//MADRE (105)
		Carta_Supervivientes madre = new Carta_Supervivientes(105, 2, 4, 34, "Bev Russell");
		cartas.add(madre);
		personajes.put(105, madre);
		
		//CONSERJE (106)
		Carta_Supervivientes conserje = new Carta_Supervivientes(106, 2, 4, 26, "Bandon Kane");
		conserje.setVertedero(5);
		cartas.add(conserje);
		personajes.put(106, conserje);
		
		//ALCALDE (107)
		Carta_Supervivientes alcalde = new Carta_Supervivientes(107, 3, 4, 68, "Brian Lee");
		cartas.add(alcalde);
		personajes.put(107, alcalde);
		
		//PREPARADOR (108)
		Carta_Supervivientes preparador = new Carta_Supervivientes(108, 2, 4, 36, "Buddy Davis");
		preparador.setHeridasParaMatar(4);
		cartas.add(preparador);
		personajes.put(108, preparador);
		
		//POLICIA (109)
		Carta_Supervivientes policia = new Carta_Supervivientes(109, 4, 2, 22, "Carla Thompsom");
		policia.setLoc(0);
		cartas.add(policia);
		personajes.put(109, policia);
		
		//SHERIFF (110)
		Carta_Supervivientes sher = new Carta_Supervivientes(110, 2, 5, 66, "Daniel Smith");
		cartas.add(sher);
		personajes.put(110, sher);
		
		//CONTABLE (111)
		Carta_Supervivientes cont = new Carta_Supervivientes(111, 4, 3, 50, "David Garc�a");
		cartas.add(cont);
		personajes.put(111, cont);
		
		//QUIMICO (112)
		Carta_Supervivientes quimico = new Carta_Supervivientes(112, 4, 3, 44, "Edward White");
		cartas.add(quimico);
		personajes.put(112, quimico);
		
		//NOEL (113)
		Carta_Supervivientes noel = new Carta_Supervivientes(113, 2, 5, 14, "Forest Plum");
		cartas.add(noel);
		personajes.put(113, noel);
		
		//BOMBERO (114)
		Carta_Supervivientes bombero = new Carta_Supervivientes(114, 2, 3, 60, "Gabriel D�az");
		cartas.add(bombero);
		personajes.put(114, bombero);
		
		//PIRATA (115)
		Carta_Supervivientes pirata = new Carta_Supervivientes(115, 1, 4, 16, "Gray Beard");
		cartas.add(pirata);
		personajes.put(115, pirata);
		
		//GUARDA (116)
		Carta_Supervivientes guarda = new Carta_Supervivientes(116, 3, 3, 32, "Harman Brooks");
		guarda.setGuarda(true);
		cartas.add(guarda);
		personajes.put(116, guarda);
		
		//PSIQUIATRA (117)
		Carta_Supervivientes psi = new Carta_Supervivientes(117, 6, 3, 54, "James Meyers");
		cartas.add(psi);
		personajes.put(117, psi);
		
		//ENFERMERA (118)
		Carta_Supervivientes enfermera = new Carta_Supervivientes(118, 3, 4, 42, "Janet Taylor");
		enfermera.setLoc(4);
		cartas.add(enfermera);
		personajes.put(118, enfermera);
		
		//CAMARERA (119)
		Carta_Supervivientes camarera = new Carta_Supervivientes(119, 4, 3, 24, "Jenny Clark");
		cartas.add(camarera);
		personajes.put(119, camarera);
		
		//ESTUDIANTE (120)//TODO NO SE IMPLEMENTAR�
		Carta_Supervivientes estudiante = new Carta_Supervivientes(120, 3, 3, 18, "John Price");
		cartas.add(null);
		personajes.put(120, null);
		
		//LE�ADOR (121)	//TODO NO SE IMPLEMENTAR�
		Carta_Supervivientes lenador = new Carta_Supervivientes(121, 2, 2, 31, "Kodiak Colby");
		cartas.add(null);
		personajes.put(121, null);
		
		//COCINERA (122)
		Carta_Supervivientes cocinera = new Carta_Supervivientes(122, 2, 4, 20, "Loretta Clay");
		cartas.add(cocinera);
		personajes.put(122, cocinera);
		
		//MAESTRA (123)
		Carta_Supervivientes maestra = new Carta_Supervivientes(123, 4, 2, 48, "Mar�a L�pez");
		cartas.add(maestra);
		personajes.put(123, maestra);
		
		//NINJA (124)
		Carta_Supervivientes ninja = new Carta_Supervivientes(124, 2, 4, 30, "Mike Cho");
		ninja.setTirarMatar(false);
		cartas.add(ninja);
		personajes.put(124, ninja);
		
		//DOCTORA (125)
		Carta_Supervivientes doctora = new Carta_Supervivientes(125, 4, 3, 56, "Olivia Brown");
		cartas.add(doctora);
		personajes.put(125, doctora);
		
		//CAMIONERO (126)
		Carta_Supervivientes camionero = new Carta_Supervivientes(126, 3, 3, 40, "Rod Miller");
		camionero.setTirarMover(false);
		cartas.add(camionero);
		personajes.put(126, camionero);
		
		//PILOTO (127)
		Carta_Supervivientes piloto = new Carta_Supervivientes(127, 4, 1, 58, "Sophie Robinson");
		cartas.add(piloto);
		personajes.put(127, piloto);
		
		//PERRO (128)
		Carta_Supervivientes perro = new Carta_Supervivientes(128, 2, 2, 10, "Sparky");
		perro.setPerro(true);
		cartas.add(perro);
		personajes.put(128, perro);
		
		//ADIVINA (129) //TODO complicada
		Carta_Supervivientes adivina = new Carta_Supervivientes(129, 3, 1, 28, "Talia Jones");
		cartas.add(null);
		personajes.put(129, null);
		
		//SOLDADO (130)	//TODO MODIFICAR EL METODO ATACAR
		Carta_Supervivientes soldado = new Carta_Supervivientes(130, 1, 3, 64, "Thomas Heart");
		cartas.add(null);
		personajes.put(130, null);
		
	}
	
	public Carta_Supervivientes getSupervivienteServer(int id) {
		return cartas.get(id);
	}
	
	public Carta_Supervivientes getSuperviviente(int id) {
		return cartas.get(id-100);
	}
	
	public Carta_Supervivientes getPersonaje(int id) {
		return personajes.get(id);
	}
	
	public Map<Integer, Carta_Supervivientes> getPersonajes(){
		return personajes;
	}
	
	public List<Carta_Supervivientes> getSupervivientes(){
		return cartas;
	}
	
	public Carta_Supervivientes getSupervienteAleatorio() {
		Carta_Supervivientes aux = null;
		int id = 0;
		
		while(aux == null) {
			id = r.nextInt(31) + 100;
			aux = personajes.get(id);
		}
		
		personajes.remove(id);
		return aux;
	}
}
