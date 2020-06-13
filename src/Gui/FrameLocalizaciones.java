package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class FrameLocalizaciones extends JFrame {

	private JPanel contentPane;
	private JButton Comisaria, Tienda, Colegio, Gasolinera, Hospital, Biblioteca, Colonia;
	private static String comando;
	private static String superv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLocalizaciones frame = new FrameLocalizaciones(comando, superv);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 * @param comando 
	 */
	public FrameLocalizaciones(String comand, String superviviente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1940, 1048);
		setBackground(new Color(0, 0, 0, 10));
		 setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String res = comand + superviviente;
		
		//TODO: Recortar botones y poner fotos nuevas
		
		
		Comisaria = new JButton("");
		Comisaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|0");
				dispose();
			}
		});
		Comisaria.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/PoliceStation.png")));
		Comisaria.setBounds(538, 94, 243, 171);
		contentPane.add(Comisaria);
		
		Tienda = new JButton("");
		Tienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|1");
				dispose();
			}
		});
		Tienda.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/GroceryStore.png")));
		Tienda.setBounds(538, 402, 243, 171);
		contentPane.add(Tienda);
		
		Colegio = new JButton("");
		Colegio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|2");
				dispose();
			}
		});
		Colegio.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/School.jpg")));
		Colegio.setBounds(538, 708, 243, 171);
		contentPane.add(Colegio);
		
		Gasolinera = new JButton("");
		Gasolinera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|3");
				dispose();
			}
		});
		Gasolinera.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/GasStation.jpg")));
		Gasolinera.setBounds(1469, 92, 243, 171);
		contentPane.add(Gasolinera);
		
		Hospital = new JButton("");
		Hospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|4");
				dispose();
			}
		});
		Hospital.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/GasStation.png")));
		Hospital.setBounds(1469, 402, 243, 171);
		contentPane.add(Hospital);
		
		Biblioteca = new JButton("");
		Biblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|5");
				dispose();
			}
		});
		Biblioteca.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/Library.png")));
		Biblioteca.setBounds(1469, 710, 243, 171);
		contentPane.add(Biblioteca);
		
		JButton btnNewButton_2 = new JButton("Volver");
		btnNewButton_2.setFont(new Font("Castellar", Font.BOLD, 17));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(971, 952, 305, 43);
		contentPane.add(btnNewButton_2);
		
		Colonia = new JButton("");
		Colonia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(res+"|6");
				dispose();
			}
		});
		Colonia.setIcon(new ImageIcon(FrameLocalizaciones.class.getResource("/Localizaciones/Colony.png")));
		Colonia.setBounds(971, 241, 305, 136);
		contentPane.add(Colonia);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
	}

}
