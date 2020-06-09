package Gui;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Asociaciones {
	private HashMap<Integer,JLabel> CartasObjetos;
	private HashMap<Integer,JLabel> ObjetivoSecreto;
	
	public Asociaciones() {
		CartasObjetos = AsocObjetos();
		ObjetivoSecreto = AsocObjSecreto();
	}
	
	private HashMap<Integer, JLabel> AsocObjSecreto(){
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel ganasVivir = new JLabel("");
		IniciarObjetivoSecretos(ganasVivir);
		//Ganas de Vivir -> 0
		aux.put(0, ganasVivir);
		return aux;
	}
	
	private void IniciarObjetivoSecretos(JLabel ganasVivir) {
		//Ganas de Vivir
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Ganas-de-vivir.jpg"));
		Image img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		ganasVivir.setIcon(new ImageIcon(img));
		ganasVivir.setBounds(0, 0, 567, 305);	
	}

	private HashMap<Integer, JLabel> AsocObjetos() {
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel comida1 = new JLabel(""), comida2 = new JLabel(""), comida3 = new JLabel(""), medicina = new JLabel("")
				, gasolina = new JLabel(""), trastos = new JLabel("");
		IniciarLabelsObjetos(comida1, comida2, comida3, medicina, gasolina, trastos);
		//Comida 1 -> 0
		aux.put(0, comida1);
		//Comida 2 -> 6
		aux.put(6, comida2);
		//Comida 3 -> 7
		aux.put(7, comida3);
		//Medicina -> 1
		aux.put(1, medicina);
		//Trastos -> 2
		aux.put(2, trastos);
		//Gasolina -> 3
		aux.put(3, gasolina);
		return aux;
	}
	private void IniciarLabelsObjetos(JLabel comida1, JLabel comida2, JLabel comida3, JLabel medicina, JLabel gasolina,
			JLabel trastos) {
		//Comida 1
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Alimento 1.jpg"));
		Image img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		comida1.setIcon(new ImageIcon(img));
		comida1.setBounds(0, 0, 416, 528);
		//Comida 2
		
		ima = new ImageIcon(this.getClass().getResource("/Alimento 2.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
	
		comida2.setIcon(new ImageIcon(img));
		comida2.setBounds(0, 0, 416, 528);
		//Comida 3
		
		ima = new ImageIcon(this.getClass().getResource("/Alimento 3.jpg"));
		img = ima.getImage().getScaledInstance(416,528, java.awt.Image.SCALE_SMOOTH); 
		

		comida3.setIcon(new ImageIcon(img));
		comida3.setBounds(0, 0, 416, 528);
		//Medicina
		
		ima = new ImageIcon(this.getClass().getResource("/Medicina.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
	
		medicina.setIcon(new ImageIcon(img));
		medicina.setBounds(0, 0, 416, 528);
		//Gasolina
		
		ima = new ImageIcon(this.getClass().getResource("/Combustible.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		gasolina.setIcon(new ImageIcon(img));
		gasolina.setBounds(0, 0, 416, 528);
		//Trastos
		
		ima = new ImageIcon(this.getClass().getResource("/Trastos.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); //Bounds ajustados al boton
		
		
		trastos.setIcon(new ImageIcon(img));
		trastos.setBounds(0, 0, 416, 528);
/*		//Supervivientes 1
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		sup1.setIcon(new ImageIcon(img));
		sup1.setBounds(0, 0, 416,528);
		//Supervivientes 2
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		sup2.setIcon(new ImageIcon(img));
		sup2.setBounds(0, 0, 416, 528);
		//Equipables
		
		//Francotirador
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		francotirador.setIcon(new ImageIcon(img));
		francotirador.setBounds(0, 0, 416, 528);
		//Pistola
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pistola.setIcon(new ImageIcon(img));
		pistola.setBounds(0, 0, 416, 528);
		//Escopeta
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		escopeta.setIcon(new ImageIcon(img));
		escopeta.setBounds(0, 0, 416, 528);
		//Plano Supermercado
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pSuper.setIcon(new ImageIcon(img));
		pSuper.setBounds(0, 0, 416, 528);
		//Plano Comisaria
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pComisaria.setIcon(new ImageIcon(img));
		pComisaria.setBounds(0, 0, 416, 528);
		//Plano Hospital
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pHospital.setIcon(new ImageIcon(img));
		pHospital.setBounds(0, 0, 416, 528);
		//Plano Colegio
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pColegio.setIcon(new ImageIcon(img));
		pColegio.setBounds(0, 0, 416, 528);
		//Plano Biblioteca
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pBiblio.setIcon(new ImageIcon(img));
		pBiblio.setBounds(0, 0, 416, 528);
		//Plano Gasolinera
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		pGasolinera.setIcon(new ImageIcon(img));
		pGasolinera.setBounds(0, 0, 416, 528);
		//Libro (no tira al atacar)
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		libNoAta.setIcon(new ImageIcon(img));
		libNoAta.setBounds(0, 0, 416, 528);
		//Libro (aumenta dado)
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
	
		libDadoMas.setIcon(new ImageIcon(img));
		libDadoMas.setBounds(0, 0, 416, 528);
		//Libro (contruir barricada free)
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
	
		libFBarricada.setIcon(new ImageIcon(img));
		libFBarricada.setBounds(0, 0, 416, 528);
		//Libro (no tirar al moverse)
		
		ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		img = ima.getImage().getScaledInstance(416, 528, java.awt.Image.SCALE_SMOOTH); 
		
		
		libNoMov.setIcon(new ImageIcon(img));
		libNoMov.setBounds(0, 0, 416, 528);
*/		
	}
	public HashMap<Integer,JLabel> getCartasObjetos(){
		return CartasObjetos;
	}
	public HashMap<Integer,JLabel> getObjSecretos(){
		return ObjetivoSecreto;
	}
}
