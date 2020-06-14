package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;

public class FrameTiradaRiesgo extends JFrame {

	private JPanel contentPane;
	private static int resultadoDado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTiradaRiesgo frame = new FrameTiradaRiesgo(resultadoDado);
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
	
	//0 no ha pasado nada
	//1 herida normal
	//2 congelacion
	//3 mordedura
	
	public FrameTiradaRiesgo(int resDado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 514, 323);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Castellar", Font.BOLD, 17));
		btnAceptar.setBounds(188, 278, 155, 34);
		contentPane.add(btnAceptar);
		
		JLabel lblMensaje = new JLabel("Resultado de la tirada de riesgo");
		lblMensaje.setForeground(Color.WHITE);
		lblMensaje.setFont(new Font("Castellar", Font.BOLD, 17));
		lblMensaje.setBounds(62, 37, 392, 21);
		contentPane.add(lblMensaje);
		
		JLabel lblResultado = new JLabel("");
		lblResultado.setBounds(188, 126, 155, 73);
		ImageIcon ima;
		Image img;
		
		switch (resDado) {
		case 0:
			ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Alexis-Grey-FICHA.png"));
			img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
			lblResultado.setIcon(new ImageIcon(img));
			break;
		case 1:
			ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Alexis-Grey-FICHA.png"));
			img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH);
			lblResultado.setIcon(new ImageIcon(img));
			break;
		case 2:
			ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Alexis-Grey-FICHA.png"));
			img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH);
			lblResultado.setIcon(new ImageIcon(img));
			break;
		case 3:
			ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Alexis-Grey-FICHA.png"));
			img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH);
			lblResultado.setIcon(new ImageIcon(img));
			break;
		default:
			break;
		}
		contentPane.add(lblResultado);
		
	}
}
