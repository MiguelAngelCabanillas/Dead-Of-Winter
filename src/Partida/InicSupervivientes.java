package Partida;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta_Supervivientes;

public class InicSupervivientes {

	List<Carta_Supervivientes> cartas = new ArrayList<>();
	
	public InicSupervivientes() {
		inicCartas();
	}
	
	private void inicCartas() {
		
		//BIBLIOTECARIA (100)
		Carta_Supervivientes biblio = new Carta_Supervivientes(100, 5, 4, 46);
		biblio.setLoc(5);
		cartas.add(biblio);	
		
		//GRANJERO (101)
		Carta_Supervivientes granjero = new Carta_Supervivientes(101, 3, 3, 12);
		granjero.setLoc(1);
		cartas.add(granjero);
		
		//ABOGADA (102) TODO habilidad
		Carta_Supervivientes abogada = new Carta_Supervivientes(102, 2, 2, 38);
		cartas.add(abogada);
		
		//DIRECTOR (103)
		Carta_Supervivientes director = new Carta_Supervivientes(103, 4, 2, 62);
		director.setLoc(7);
		cartas.add(director);
		
		//OBRERA (104)	//TODO habilidad (intentar que la barricada se ponga en el lugar en el que está)
		Carta_Supervivientes obrera = new Carta_Supervivientes(104, 2, 5, 52);
		cartas.add(obrera);
		
		//MADRE (105)	//TODO habilidad (preguntar si hacer como pasiva)
		Carta_Supervivientes madre = new Carta_Supervivientes(105, 2, 4, 34);
		cartas.add(madre);
		
		//CONSERJE (106)	//TODO habilidad (crear una variable para saber cuantas cartas se limpian del vertedero)
		Carta_Supervivientes conserje = new Carta_Supervivientes(106, 2, 4, 26);
		cartas.add(conserje);
		
		//ALCALDE (107)	//TODO habilidad (crear un metodo para aumentar el dado)
		Carta_Supervivientes alcalde = new Carta_Supervivientes(107, 3, 4, 68);
		cartas.add(alcalde);
		
		//PREPARADOR (108)	//TODO habilidad (crear un contador de heridas?)
		Carta_Supervivientes preparador = new Carta_Supervivientes(108, 2, 4, 36);
		cartas.add(preparador);
		
		//POLICIA (109)
		Carta_Supervivientes policia = new Carta_Supervivientes(109, 4, 2, 22);
		policia.setLoc(0);
		cartas.add(policia);
		
		//SHERIFF (110)	//TODO habilidad (crear una variable para matar doble)
		Carta_Supervivientes sher = new Carta_Supervivientes(110, 2, 5, 66);
		cartas.add(sher);
		
		//CONTABLE (111) //TODO habilidad
		Carta_Supervivientes cont = new Carta_Supervivientes(111, 4, 3, 50);
		cartas.add(cont);
		
		//QUIMICO (112) //TODO habilidad
		Carta_Supervivientes quimico = new Carta_Supervivientes(112, 4, 3, 44);
		cartas.add(quimico);
		
		//NOEL (113)	//TODO va a ser horrible de hacer
		Carta_Supervivientes noel = new Carta_Supervivientes(113, 2, 5, 14);
		cartas.add(noel);
		
		//BOMBERO (114)	//TODO ni idea
		Carta_Supervivientes bombero = new Carta_Supervivientes(114, 2, 3, 60);
		cartas.add(bombero);
		
		//PIRATA (115) //TODO dificil
		Carta_Supervivientes pirata = new Carta_Supervivientes(115, 1, 4, 16);
		cartas.add(pirata);
		
		//GUARDA (116)	//TODO HABILIDAD
		Carta_Supervivientes guarda = new Carta_Supervivientes(116, 3, 3, 32);
		cartas.add(guarda);
		
		//PSIQUIATRA (117)	//TODO dificil pero se puede hacer
		Carta_Supervivientes psi = new Carta_Supervivientes(117, 6, 3, 54);
		cartas.add(psi);
		
		//ENFERMERA (118)
		Carta_Supervivientes enfermera = new Carta_Supervivientes(118, 3, 4, 42);
		enfermera.setLoc(4);
		cartas.add(enfermera);
		
		//CAMARERA (119) //TODO esta va a ser horrible
		Carta_Supervivientes camarera = new Carta_Supervivientes(119, 4, 3, 24);
		cartas.add(camarera);
		
		//ESTUDIANTE (120)//TODO ni de coña
		Carta_Supervivientes estudiante = new Carta_Supervivientes(120, 3, 3, 18);
		director.setLoc(7);
		cartas.add(estudiante);
		
		//LEÑADOR (121)	//TODO se puede hacer pero es dificil
		Carta_Supervivientes lenador = new Carta_Supervivientes(121, 2, 2, 31);
		cartas.add(lenador);
		
		//COCINERA (122) //TODO facil de hacer
		Carta_Supervivientes cocinera = new Carta_Supervivientes(122, 2, 4, 20);
		cartas.add(cocinera);
		
		//MAESTRA (123)	//TODO se puede hacer
		Carta_Supervivientes maestra = new Carta_Supervivientes(123, 4, 2, 48);
		cartas.add(maestra);
		
		//NINJA (124)
		Carta_Supervivientes ninja = new Carta_Supervivientes(124, 2, 4, 30);
		ninja.setTirarMatar(false);
		cartas.add(ninja);
		
		//DOCTORA (125)	//TODO se puede hacer facil
		Carta_Supervivientes doctora = new Carta_Supervivientes(125, 4, 3, 56);
		cartas.add(doctora);
		
		//CAMIONERO (126)
		Carta_Supervivientes camionero = new Carta_Supervivientes(126, 3, 3, 40);
		camionero.setTirarMover(false);
		cartas.add(camionero);
		
		//PILOTO (127)	//TODO 
		Carta_Supervivientes piloto = new Carta_Supervivientes(127, 4, 1, 58);
		cartas.add(piloto);
		
		//PERRO (128)	//TODO MODIFICAR LA CLASE TIRADA DE RIESGO DE JUGADOR
		Carta_Supervivientes perro = new Carta_Supervivientes(128, 2, 2, 10);
		cartas.add(perro);
		
		//ADIVINA (129) //TODO complicada
		Carta_Supervivientes adivina = new Carta_Supervivientes(129, 3, 1, 28);
		cartas.add(adivina);
		
		//SOLDADO (130)	//TODO MODIFICAR EL METODO ATACAR
		Carta_Supervivientes soldado = new Carta_Supervivientes(130, 1, 3, 64);
		cartas.add(soldado);
		
	}
	
	public Carta_Supervivientes getSuperviviente(int id) {
		return cartas.get(id -100);
	}
}
