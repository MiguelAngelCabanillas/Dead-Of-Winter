package Gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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

public class InfoTablero extends JFrame {
	
	private JButton btnVolver;
	private JPanel contentPane;
	private static Asociaciones asociaciones;
	private FrameSupervivientes auxSuper;
	private static List<String> jugadores;
	private static HashMap<Integer, List<Integer>> supervivientes;
	private static List<Integer> numCartas;
	private JLabel labelJugadores[];
	private JLabel cartasJug[];
	private JButton botoneSuperviviente[];
	private Point posCartasJug[] = {new Point(928, 594), new Point(928, 676), new Point(928, 753), new Point(928, 841), new Point(928, 924)};
	private Point posNombreJugadores[] = {new Point(10, 53), new Point(10, 288), new Point(10, 527), new Point(919, 49), new Point(919, 288)};
	private Point posSupJugador1[] = {new Point(10, 72), new Point(187, 72), new Point(365, 72), new Point(545, 72), new Point(726, 72)};
	private Point posSupJugador2[] = {new Point(10, 311), new Point(187, 311), new Point(365, 311), new Point(545, 311), new Point(726, 311)};
	private Point posSupJugador3[] = {new Point(10, 550), new Point(187, 550), new Point(365, 550), new Point(545, 550), new Point(726, 550)};
	private Point posSupJugador4[] = {new Point(919, 72), new Point(1096, 72), new Point(1274, 72), new Point(1454, 72), new Point(1635, 72)};
	private Point posSupJugador5[] = {new Point(919, 311), new Point(1096, 311), new Point(1274, 311), new Point(1454, 311), new Point(1635, 311)};
	private Point[] posiciones[] = {posSupJugador1, posSupJugador2, posSupJugador3, posSupJugador4, posSupJugador5};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoTablero frame = new InfoTablero(jugadores, supervivientes, asociaciones, numCartas);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InfoTablero(List<String> Jugadores, HashMap<Integer, List<Integer>> Supervivientes, Asociaciones aso, List<Integer> numC) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		asociaciones = aso;
		
		SupDerHandler supervivientesHandler = new SupDerHandler();
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 40, 1924, 2);
		contentPane.add(separator_1);
		
		
		
		botoneSuperviviente = new JButton[25]; //Tamaño del total de supervivientes que puede tener
		labelJugadores = new JLabel[5];
		cartasJug = new JLabel[5];
		
		ImageIcon icon;
		Image img;
		int cantJugadores = Jugadores.size();
		int cantSupervivientes;
		Point p;

		
		for(int i = 0; i < cantJugadores; i++) {
			p = posNombreJugadores[i];
			labelJugadores[i] = new JLabel(Jugadores.get(i));
			labelJugadores[i].setForeground(Color.WHITE);
			labelJugadores[i].setFont(new Font("Castellar", Font.BOLD | Font.ITALIC, 17));
			labelJugadores[i].setBounds(p.x, p.y, 200, 21);
			contentPane.add(labelJugadores[i]);
			p = posCartasJug[i];
			cartasJug[i] = new JLabel(Jugadores.get(i) + " Tiene "+numC.get(i)+" cartas en la mano\r\n");
			cartasJug[i].setForeground(Color.WHITE);
			cartasJug[i].setFont(new Font("Castellar", Font.BOLD | Font.ITALIC, 25));
			cartasJug[i].setBounds(p.x, p.y, 660, 30);
			contentPane.add(cartasJug[i]);
			cantSupervivientes = Supervivientes.get(i).size();
			for(int j = 0; j < cantSupervivientes; j++) {
				p = posiciones[i][j];
				botoneSuperviviente[j+i*cantJugadores] = new JButton(String.valueOf(Supervivientes.get(i).get(j)));
				System.out.println(Supervivientes.get(i).get(j));
				icon = (ImageIcon) aso.getSupMap().get(Supervivientes.get(i).get(j))[1].getIcon();
				img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
				botoneSuperviviente[j+i*cantJugadores].setIcon(new ImageIcon(img));
				botoneSuperviviente[j+i*cantJugadores].setBounds(p.x, p.y, 147, 205);
				botoneSuperviviente[j+i*cantJugadores].addActionListener(supervivientesHandler);
				System.out.println(""+(j+i*cantJugadores));
				botoneSuperviviente[j+i*cantJugadores].setActionCommand(""+(j+i*cantJugadores));
				contentPane.add(botoneSuperviviente[j+i*cantJugadores]);
			}
		}
		
		
		
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
			}
		});
		btnVolver.setFont(new Font("Castellar", Font.BOLD, 20));
		btnVolver.setBounds(861, 4, 218, 33);
		contentPane.add(btnVolver);
		
		JLabel lblSupervivientes_1 = new JLabel("Supervivientes");
		lblSupervivientes_1.setForeground(Color.WHITE);
		lblSupervivientes_1.setFont(new Font("Castellar", Font.BOLD, 24));
		lblSupervivientes_1.setBounds(10, 5, 232, 29);
		contentPane.add(lblSupervivientes_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setBounds(10, 766, 888, 2);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(896, 527, 1018, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.WHITE);
		separator_3.setBackground(Color.WHITE);
		separator_3.setBounds(896, 40, 2, 728);
		contentPane.add(separator_3);
		
		JLabel lblFondoInfo = new JLabel("");
		icon = new ImageIcon(this.getClass().getResource("/fondo-info.jpg"));
		img = icon.getImage().getScaledInstance(1924, 1021, java.awt.Image.SCALE_SMOOTH);
		lblFondoInfo.setBounds(0, 0, 1924, 1021);
		lblFondoInfo.setIcon(new ImageIcon(img));
		contentPane.add(lblFondoInfo);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizar pantalla inicialmente
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
			case "11":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[11].getText());
				auxSuper.setVisible(true);
				break;
			case "12":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[12].getText());
				auxSuper.setVisible(true);
				break;
			case "13":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[13].getText());
				auxSuper.setVisible(true);
				break;
			case "14":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[14].getText());
				auxSuper.setVisible(true);
				break;
			case "15":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[15].getText());
				auxSuper.setVisible(true);
				break;
			case "16":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[16].getText());
				auxSuper.setVisible(true);
				break;
			case "17":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[17].getText());
				auxSuper.setVisible(true);
				break;
			case "18":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[18].getText());
				auxSuper.setVisible(true);
				break;
			case "19":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[19].getText());
				auxSuper.setVisible(true);
				break;
			case "20":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[20].getText());
				auxSuper.setVisible(true);
				break;
			case "21":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[21].getText());
				auxSuper.setVisible(true);
				break;
			case "22":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[22].getText());
				auxSuper.setVisible(true);
				break;
			case "23":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[23].getText());
				auxSuper.setVisible(true);
				break;
			case "24":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[24].getText());
				auxSuper.setVisible(true);
				break;
			case "25":
				if(auxSuper != null) {
					auxSuper.dispose();
				} 
				auxSuper = new FrameSupervivientes(asociaciones.getSupMap(), botoneSuperviviente[25].getText());
				auxSuper.setVisible(true);
				break;

			default:
				break;
			}
		}
	}
}
