package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;

public class FrameMoverse extends JFrame {

	private JPanel contentPane;
	private JButton botoneSuperviviente[];
	private static List<Integer> supJugadores;
	private static Asociaciones asociaciones;
	private Point posCartaSuperV[] = {new Point(10, 72), new Point(187, 72), new Point(365, 72), new Point(545, 72), new Point(726, 72)};
	private FrameSupervivientes auxSuper;
	private FrameMoverse2 invisible;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMoverse frame = new FrameMoverse(supJugadores, asociaciones);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMoverse(List<Integer> supJugadores, Asociaciones aso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1323, 791);
		setBackground(new Color(0, 0, 0, 10));
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		JButton btnNewButton = new JButton("OK\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(276, 657, 89, 34);
		contentPane.add(btnNewButton);
		
		SupDerHandler supervivientesHandler = new SupDerHandler();
		
		asociaciones = aso;
		
		botoneSuperviviente = new JButton[10];
		
		int tamSupervivientes = supJugadores.size();
		ImageIcon icon;
		Image img;
		Point p;
		
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
		
	}
	
	private class SupDerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "0":
				invisible = new FrameMoverse2();
				invisible.setVisible(true);
				dispose();
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
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(578, 742);

	      setLocationRelativeTo(null);
	}
}
