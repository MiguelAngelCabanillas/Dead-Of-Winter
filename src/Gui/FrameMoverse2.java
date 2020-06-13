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

public class FrameMoverse2 extends JFrame {

	private JPanel contentPane;
	private JButton Comisaria, Tienda, Colegio, Gasolinera, Hospital, Biblioteca, Colonia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMoverse2 frame = new FrameMoverse2();
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
	public FrameMoverse2() {
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
		
		//new Color(0, 0, 0, 100)
		
		Comisaria = new JButton("");
		Comisaria.setContentAreaFilled(false);
		Comisaria.setBounds(538, 94, 243, 171);
		contentPane.add(Comisaria);
		
		Tienda = new JButton("");
		Tienda.setBounds(538, 402, 243, 171);
		Tienda.setContentAreaFilled(false);
		contentPane.add(Tienda);
		
		Colegio = new JButton("");
		Colegio.setContentAreaFilled(false);
		Colegio.setBounds(538, 708, 243, 171);
		contentPane.add(Colegio);
		
		Gasolinera = new JButton("");
		Gasolinera.setContentAreaFilled(false);
		Gasolinera.setBounds(1469, 92, 243, 171);
		contentPane.add(Gasolinera);
		
		Hospital = new JButton("");
		Hospital.setContentAreaFilled(false);
		Hospital.setBounds(1469, 402, 243, 171);
		contentPane.add(Hospital);
		
		Biblioteca = new JButton("");
		Biblioteca.setContentAreaFilled(false);
		Biblioteca.setBounds(1469, 710, 243, 171);
		contentPane.add(Biblioteca);
		
		JButton btnNewButton_2 = new JButton("Volver");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Castellar", Font.BOLD, 17));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(971, 952, 305, 43);
		contentPane.add(btnNewButton_2);
		
		Colonia = new JButton("");
		Colonia.setBounds(971, 241, 305, 136);
		Colonia.setContentAreaFilled(false);
		contentPane.add(Colonia);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
	}

}
