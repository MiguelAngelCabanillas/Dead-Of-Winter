package Gui;

import java.awt.EventQueue;
import java.awt.Image;
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

public class InfoJugador extends JFrame {

	private JPanel contentPane;
	private static Asociaciones asociaciones;
	private JButton [] botonesCarta;
	private HashMap<Integer, JLabel> obj;
	private HashMap<Integer, JLabel> objetivoSecretos;
	private FrameCarta auxCarta;
	private static List<Integer> dados;
	private static int objetivoSecreto;
	private static List<Integer> supJugadores;
	private static List<Integer> cartasJugador;
	
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

	public InfoJugador(List<Integer> supJugadores, List<Integer> cartasJugador, int objetivoSecreto, Asociaciones aso, List<Integer> dados) {
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
		
		
		botonesCarta = new JButton[20]; //Tamañano de la mano del jugador
		
		//Añado el objetivo Secreto
		
		JLabel aux = objetivoSecretos.get(200);
		aux.setBounds(171, 53, aux.getWidth(), aux.getHeight());
		contentPane.add(aux);
		
		int x = 979;
		int y = 57;
		int huecoX = 195;
		int huecoY = 242;
		ImageIcon icon;
		Image img;
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				botonesCarta[j+4*i] = new JButton("2"); //+ z dependiendo las que quepan por fila //pasarle el id de carta
				botonesCarta[j+4*i].setBounds(x + j*huecoX, y, 147, 205);
				icon = (ImageIcon) obj.get(2).getIcon();
				img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
				botonesCarta[j+4*i].setIcon(new ImageIcon(img));
				contentPane.add(botonesCarta[j+4*i]);
				botonesCarta[j+4*i].addActionListener(handler);
				botonesCarta[j+4*i].setActionCommand(""+(j+4*i));
			}
			y += huecoY;
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
//				auxCarta = new FrameCarta(botonesCarta[0].getText()); 
				auxCarta = new FrameCarta(obj, botonesCarta[0].getText());
				auxCarta.setVisible(true);
				break;

			default:
				break;
			}
			
			
		}
		
	}
}
