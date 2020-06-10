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
		int huecoXS = 184;
		int huecoYS = 216;
 */

public class InfoJugador extends JFrame {

	private JPanel contentPane;
	private static Asociaciones asociaciones;
	private FrameCarta auxCarta;
	private FrameSupervivientes auxSuper;
	private static List<Integer> dados;
	private static int objetivoSecreto;
	private static List<Integer> supJugadores;
	private static List<Integer> cartasJugador;
	private JButton botonesCarta[];
	private JButton botoneSuperviviente[];
	private JLabel labelsDados[];
	private Point posCarta[] = {new Point(979, 59), new Point(1174, 57), new Point(1369, 57), new Point(1564, 57), new Point(1759, 57)
			, new Point(979, 301), new Point(1174, 301), new Point(1369, 301), new Point(1564, 301), new Point(1759, 301),
			new Point(979, 543), new Point(1174, 543), new Point(1369 ,543), new Point(1564, 543), new Point(1759, 543),
			new Point(979, 785), new Point(1174, 785), new Point(1369 ,785), new Point(1564, 785), new Point(1759, 785)};
	private Point posCartaSuperV[] = {new Point(30, 430), new Point(214, 430), new Point(398, 430), new Point(582, 430), new Point(766, 430)
			, new Point(30, 646), new Point(214, 646), new Point(398, 646), new Point(582, 646), new Point(766, 646)};
	private Point posDado[] = {new Point(138, 914), new Point(240, 914), new Point(344, 914), new Point(439, 914), new Point(533, 914), new Point(627, 914), new Point(722, 914), new Point(814, 914)};
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
		
		asociaciones = aso;
		
		DerHandler handler = new DerHandler();
		SupDerHandler supervivientesHandler = new SupDerHandler();
		
		JLabel lblSupervivientes = new JLabel("Supervivientes");
		lblSupervivientes.setForeground(Color.WHITE);
		lblSupervivientes.setFont(new Font("Castellar", Font.BOLD, 24));
		lblSupervivientes.setBounds(344, 388, 232, 29);
		contentPane.add(lblSupervivientes);
			
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(969, 40, 2, 981);
		contentPane.add(separator);
		
		JLabel ManoCartas = new JLabel("Cartas en Mano");
		ManoCartas.setForeground(Color.WHITE);
		ManoCartas.setFont(new Font("Castellar", Font.BOLD, 24));
		ManoCartas.setBounds(1355, 11, 263, 29);
		contentPane.add(ManoCartas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 40, 1924, 2);
		contentPane.add(separator_1);
		
		JLabel lblObjetivoSecreto = new JLabel("Objetivo Secreto\r\n");
		lblObjetivoSecreto.setForeground(Color.WHITE);
		lblObjetivoSecreto.setFont(new Font("Castellar", Font.BOLD, 24));
		lblObjetivoSecreto.setBounds(334, 11, 272, 29);
		contentPane.add(lblObjetivoSecreto);
		
		
		botonesCarta = new JButton[20]; //Tamañano de la mano del jugador (Todos los botones)
		botoneSuperviviente = new JButton[10]; //Tamaño del total de supervivientes que puede tener
		labelsDados = new JLabel[8]; //Tamaño del total de dados que puede tener el jugador
		//Añado el objetivo Secreto
		
		JLabel aux = aso.getObjSecretos().get(objetivoSecreto);
		aux.setBounds(171, 53, aux.getWidth(), aux.getHeight());
		contentPane.add(aux);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.WHITE);
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(0, 375, 971, 2);
		contentPane.add(separator_2);
		
		//Añade los botones de las cartas en la mano del jugador
		int tamSupervivientes = supJugadores.size();
		int tam = cartasJugador.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		for(int k = 0; k < tam; k++) {
			p = posCarta[k];
			botonesCarta[k] = new JButton(String.valueOf(cartasJugador.get(k)));
			icon = (ImageIcon) aso.getCartasObjetos().get(cartasJugador.get(k)).getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botonesCarta[k].setIcon(new ImageIcon(img));
			botonesCarta[k].setBounds(p.x, p.y, 147, 205);
			botonesCarta[k].addActionListener(handler);
			botonesCarta[k].setActionCommand(""+k);
			contentPane.add(botonesCarta[k]);
		}
		
		for(int z = 0; z < tamSupervivientes; z++) {
			p = posCartaSuperV[z];
			botoneSuperviviente[z] = new JButton(String.valueOf(supJugadores.get(z)));
			icon = (ImageIcon) aso.getSupMap().get(supJugadores.get(z))[1].getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botoneSuperviviente[z].setIcon(new ImageIcon(img));
			botoneSuperviviente[z].setBounds(p.x, p.y, 147, 205);
			botoneSuperviviente[z].addActionListener(supervivientesHandler);
			botoneSuperviviente[z].setActionCommand(""+z);
			contentPane.add(botoneSuperviviente[z]);
		}
		
		for (int d = 0; d < dados.size(); d++) {
			p = posDado[d];
			labelsDados[d] = new JLabel(String.valueOf(dados.get(d)));
			icon = (ImageIcon) aso.getDados().get(dados.get(d)).getIcon();
			img = icon.getImage().getScaledInstance(73, 68, java.awt.Image.SCALE_SMOOTH);
			labelsDados[d].setIcon(new ImageIcon(img));
			labelsDados[d].setBounds(p.x, p.y, 73, 68);
			contentPane.add(labelsDados[d]);
		}
		
		JLabel lblDados = new JLabel("Dados");
		lblDados.setForeground(Color.WHITE);
		lblDados.setFont(new Font("Castellar", Font.BOLD, 24));
		lblDados.setBounds(10, 931, 100, 29);
		contentPane.add(lblDados);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.WHITE);
		separator_2_1.setBackground(Color.WHITE);
		separator_2_1.setBounds(0, 862, 971, 2);
		contentPane.add(separator_2_1);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Castellar", Font.BOLD, 20));
		btnNewButton.setBounds(861, 4, 218, 33);
		contentPane.add(btnNewButton);
		JLabel lblFondoInfo = new JLabel("");
		icon = new ImageIcon(this.getClass().getResource("/fondo-info.jpg"));
		img = icon.getImage().getScaledInstance(1924, 1021, java.awt.Image.SCALE_SMOOTH);
		lblFondoInfo.setBounds(0, 0, 1924, 1021);
		lblFondoInfo.setIcon(new ImageIcon(img));
		contentPane.add(lblFondoInfo);
		
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
	private class SupDerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "0":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[0].getText());
				auxSuper.setVisible(true);
				break;
			case "1":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[1].getText());
				auxSuper.setVisible(true);
				break;
			case "2":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[2].getText());
				auxSuper.setVisible(true);
				break;
			case "3":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[3].getText());
				auxSuper.setVisible(true);
				break;
			case "4":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[4].getText());
				auxSuper.setVisible(true);
				break;
			case "5":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[5].getText());
				auxSuper.setVisible(true);
				break;
			case "6":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[6].getText());
				auxSuper.setVisible(true);
				break;
			case "7":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[7].getText());
				auxSuper.setVisible(true);
				break;
			case "8":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[8].getText());
				auxSuper.setVisible(true);
				break;
			case "9":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[9].getText());
				auxSuper.setVisible(true);
				break;
			case "10":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[10].getText());
				auxSuper.setVisible(true);
				break;

			default:
				break;
			}
		}
		
	}
}
