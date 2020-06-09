package Gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

public class Prueba extends JFrame {

	private JPanel contentPane;
	private Asociaciones asociaciones;
	private JButton [] botonesCarta;
	private FrameCarta auxCarta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
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
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		asociaciones = new Asociaciones(); //Asociaciones de las cartas
		
		
		
		HashMap<Integer, JLabel> obj = asociaciones.getCartasObjetos();
		DerHandler handler = new DerHandler();
		
		contentPane.add(obj.get(1));
		
		
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
		
//		JSeparator separator_1 = new JSeparator();
//		separator_1.setBounds(969, 47, 955, 2);
//		contentPane.add(separator_1);
//		
//		JButton btnNewButton_1 = new JButton("New button");
//		btnNewButton_1.setBounds(1167, 11, 157, 205);
//		contentPane.add(btnNewButton_1);
//		
//		JButton btnNewButton_2 = new JButton("New button");
//		btnNewButton_2.setBounds(1360, 11, 157, 205);
//		contentPane.add(btnNewButton_2);
//		
//		JButton btnNewButton_3 = new JButton("New button");
//		btnNewButton_3.setBounds(1549, 11, 157, 205);
//		contentPane.add(btnNewButton_3);
//		
//		JButton btnNewButton_4 = new JButton("New button");
//		btnNewButton_4.setBounds(1743, 11, 157, 205);
//		contentPane.add(btnNewButton_4);
//		
//		JButton btnNewButton_5 = new JButton("New button");
//		btnNewButton_5.setBounds(981, 253, 157, 205);
//		contentPane.add(btnNewButton_5);
//		
//		JButton btnNewButton_5_1 = new JButton("New button");
//		btnNewButton_5_1.setBounds(981, 504, 157, 205);
//		contentPane.add(btnNewButton_5_1);
//		
//		JButton btnNewButton_5_2 = new JButton("New button");
//		btnNewButton_5_2.setBounds(981, 765, 157, 205);
//		contentPane.add(btnNewButton_5_2);
//		
//		JButton btnNewButton_5_3 = new JButton("New button");
//		btnNewButton_5_3.setBounds(1167, 253, 157, 205);
//		contentPane.add(btnNewButton_5_3);
//		
//		JButton btnNewButton_5_4 = new JButton("New button");
//		btnNewButton_5_4.setBounds(1360, 253, 157, 205);
//		contentPane.add(btnNewButton_5_4);
//		
//		JButton btnNewButton_5_5 = new JButton("New button");
//		btnNewButton_5_5.setBounds(1549, 253, 157, 205);
//		contentPane.add(btnNewButton_5_5);
//		
//		JButton btnNewButton_5_6 = new JButton("New button");
//		btnNewButton_5_6.setBounds(1743, 253, 157, 205);
//		contentPane.add(btnNewButton_5_6);
//		
//		JButton btnNewButton_5_7 = new JButton("New button");
//		btnNewButton_5_7.setBounds(1167, 504, 157, 205);
//		contentPane.add(btnNewButton_5_7);
//		
//		JButton btnNewButton_5_8 = new JButton("New button");
//		btnNewButton_5_8.setBounds(1360, 504, 157, 205);
//		contentPane.add(btnNewButton_5_8);
//		
//		JButton btnNewButton_5_9 = new JButton("New button");
//		btnNewButton_5_9.setBounds(1549, 504, 157, 205);
//		contentPane.add(btnNewButton_5_9);
//		
//		JButton btnNewButton_5_10 = new JButton("New button");
//		btnNewButton_5_10.setBounds(1743, 504, 157, 205);
//		contentPane.add(btnNewButton_5_10);
//		
//		JButton btnNewButton_5_11 = new JButton("New button");
//		btnNewButton_5_11.setBounds(1167, 765, 157, 205);
//		contentPane.add(btnNewButton_5_11);
//		
//		JButton btnNewButton_5_12 = new JButton("New button");
//		btnNewButton_5_12.setBounds(1360, 765, 157, 205);
//		contentPane.add(btnNewButton_5_12);
//		
//		JButton btnNewButton_5_13 = new JButton("New button");
//		btnNewButton_5_13.setBounds(1549, 765, 157, 205);
//		contentPane.add(btnNewButton_5_13);
//		
//		JButton btnNewButton_5_14 = new JButton("New button");
//		btnNewButton_5_14.setBounds(1743, 765, 157, 205);
//		contentPane.add(btnNewButton_5_14);
		
		
		botonesCarta = new JButton[20]; //Tamañano de la mano del jugador
		
		
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
				auxCarta = new FrameCarta(botonesCarta[0].getText()); 
				auxCarta.setVisible(true);
				break;

			default:
				break;
			}
			
			
		}
		
	}
}
