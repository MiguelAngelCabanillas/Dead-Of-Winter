package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class FrameSupervivientes extends JFrame {

	private JPanel contentPane;
	private static HashMap<Integer, JLabel[]> carta;
	private static String id;
	//private static int[] heridas;
	private static HashMap<Integer,Integer> heridasNormales,heridasCong;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSupervivientes frame = new FrameSupervivientes(carta, id, heridasNormales,heridasCong);
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
	
	public FrameSupervivientes(HashMap<Integer, JLabel[]> carta, String id) {
		this(carta, id, null,null);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public FrameSupervivientes(HashMap<Integer, JLabel[]> carta, String id, HashMap<Integer,Integer> hN,HashMap<Integer,Integer> hC) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		setBackground(new Color(0, 0, 0, 10));
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		heridasNormales = hN;
		heridasCong = hC;
		
		JLabel lblSuperviviente = new JLabel("");
		lblSuperviviente.setBounds(10, 12,  406, 575);
		lblSuperviviente.setIcon(carta.get(Integer.parseInt(id))[1].getIcon());

		
		JButton btnNewButton = new JButton("Volver\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		JLabel lblHeridaCongelacion = new JLabel("");
		lblHeridaCongelacion.setBounds(89, 184, 100, 100);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Fichas/Herida-Congelacion.png"));
		Image heridaCongelacion = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		
		JLabel lblHerida = new JLabel("");
		lblHerida.setBounds(223, 315, 100, 100);
		icon = new ImageIcon(this.getClass().getResource("/Fichas/Herida.png"));
		Image heridaNormal = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		
		
		
		//Se encarga de poner las heridas manuelamente (Automatico necesito asoc con las heridas)
		switch (heridasNormales.get(Integer.parseInt(id))) {
		case 2:
			lblHerida.setIcon(new ImageIcon(heridaNormal));
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaNormal));
			contentPane.add(lblHerida);	
			contentPane.add(lblHeridaCongelacion);
			break;
		case 1:
			lblHerida.setIcon(new ImageIcon(heridaNormal));
			contentPane.add(lblHerida);	
		default:
			break;
		}
		
		switch (heridasCong.get(Integer.parseInt(id))) {
		case 2:
			lblHerida.setIcon(new ImageIcon(heridaCongelacion));
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaCongelacion));
			contentPane.add(lblHerida);	
			contentPane.add(lblHeridaCongelacion);
			break;
		case 1:
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaCongelacion));
			contentPane.add(lblHeridaCongelacion);
		default:
			break;
		}
		
		
		
		
		
		btnNewButton.setBounds(168, 598, 95, 38);
		contentPane.add(btnNewButton);
		contentPane.add(lblSuperviviente);
		
		if(heridasNormales.get(Integer.parseInt(id)) != null) {
		System.out.println("Heridas normales de " + Integer.parseInt(id) +": "+ heridasNormales.get(Integer.parseInt(id)));
		}
		if(heridasCong.get(Integer.parseInt(id)) != null) {
			System.out.println("Heridas de congelacion de " + Integer.parseInt(id) +": "+ heridasCong.get(Integer.parseInt(id)));
		}
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(431, 647);

	      setLocationRelativeTo(null);
	}
}
