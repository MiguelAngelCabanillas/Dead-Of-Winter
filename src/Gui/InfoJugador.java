package Gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import Gui.Asociaciones;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

/*
 * 		int x = 979;
		int y = 57;
		int huecoX = 195;
		int huecoY = 242;
 */

public class InfoJugador extends JFrame {

	private JPanel contentPane;
	private static Asociaciones asociaciones;
	private FrameCarta auxCarta;
	private static List<Integer> dados;
	private static int objetivoSecreto;
	private static List<Integer> supJugadores;
	private static List<Integer> cartasJugador;
	private JButton botonesCarta[];
	private Point posCarta[] = {new Point(979, 59), new Point(1174, 57), new Point(1369, 57), new Point(1564, 57), new Point(1759, 57)
			, new Point(979, 301), new Point(1174, 301), new Point(1369, 301), new Point(1564, 301), new Point(1759, 301),
			new Point(979, 543), new Point(1174, 543), new Point(1369 ,543), new Point(1564, 543), new Point(1759, 543),
			new Point(979, 785), new Point(1174, 785), new Point(1369 ,785), new Point(1564, 785), new Point(1759, 785)};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoJugador frame = new InfoJugador(supJugadores, cartasJugador, objetivoSecreto, asociaciones, dados);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InfoJugador(List<Integer> supJugadores, List<Integer> cartasJugador, int objetivoSecreto,
			Asociaciones aso, List<Integer> dados) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DerHandler handler = new DerHandler();
			
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(969, 0, 2, 1021);
		contentPane.add(separator);
		
		JLabel ManoCartas = new JLabel("Cartas en Mano");
		ManoCartas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ManoCartas.setBounds(1389, 11, 139, 25);
		contentPane.add(ManoCartas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 40, 1924, 2);
		contentPane.add(separator_1);
		
		JLabel lblObjetivoSecreto = new JLabel("Objetivo Secreto\r\n");
		lblObjetivoSecreto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblObjetivoSecreto.setBounds(379, 11, 148, 25);
		contentPane.add(lblObjetivoSecreto);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(171, 53, 567, 405);
//		contentPane.add(lblNewLabel);
		
		
		botonesCarta = new JButton[20]; //Tamañano de la mano del jugador (Todos los botones)
		
		//Añado el objetivo Secreto
		
		JLabel aux = aso.getObjSecretos().get(objetivoSecreto);
		aux.setBounds(171, 53, aux.getWidth(), aux.getHeight());
		contentPane.add(aux);
		
		//Añade los botones de las cartas en la mano del jugador
		int tam = cartasJugador.size();
		ImageIcon icon;
		Image img;
		Point p;
		for(int k = 0; k < tam; k++) {
			p = posCarta[k];
			botonesCarta[k] = new JButton(String.valueOf(cartasJugador.get(k)));
			botonesCarta[k].setBounds(p.x, p.y, 147, 205);
			icon = (ImageIcon) aso.getCartasObjetos().get(cartasJugador.get(k)).getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botonesCarta[k].setIcon(new ImageIcon(img));
			contentPane.add(botonesCarta[k]);
			botonesCarta[k].addActionListener(handler);
			botonesCarta[k].setActionCommand(""+k);
		}
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizar pantalla inicialmente
	}
	private class DerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "0":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[0].getText());
				auxCarta.setVisible(true);
				break;
			case "1":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[1].getText());
				auxCarta.setVisible(true);
				break;
			case "2":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[2].getText());
				auxCarta.setVisible(true);
				break;
			case "3":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[3].getText());
				auxCarta.setVisible(true);
				break;
			case "4":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[4].getText());
				auxCarta.setVisible(true);
				break;
			case "5":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[5].getText());
				auxCarta.setVisible(true);
				break;
			case "6":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[6].getText());
				auxCarta.setVisible(true);
				break;
			case "7":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[7].getText());
				auxCarta.setVisible(true);
				break;
			case "8":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[8].getText());
				auxCarta.setVisible(true);
				break;
			case "9":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[9].getText());
				auxCarta.setVisible(true);
				break;
			case "10":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[10].getText());
				auxCarta.setVisible(true);
				break;
			case "11":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[11].getText());
				auxCarta.setVisible(true);
				break;
			case "12":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[12].getText());
				auxCarta.setVisible(true);
				break;
			case "13":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[13].getText());
				auxCarta.setVisible(true);
				break;
			case "14":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[14].getText());
				auxCarta.setVisible(true);
				break;
			case "15":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[15].getText());
				auxCarta.setVisible(true);
				break;
			case "16":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[16].getText());
				auxCarta.setVisible(true);
				break;
			case "17":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[17].getText());
				auxCarta.setVisible(true);
				break;
			case "18":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[18].getText());
				auxCarta.setVisible(true);
				break;
			case "19":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[19].getText());
				auxCarta.setVisible(true);
				break;
			case "20":
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(asociaciones.getCartasObjetos(), botonesCarta[20].getText());
				auxCarta.setVisible(true);
				break;

			default:
				break;
			}
			
			
		}
		
	}
}
