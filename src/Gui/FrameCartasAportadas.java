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
import java.io.IOException;
import java.util.List;
import javax.swing.SwingConstants;

public class FrameCartasAportadas extends JFrame {
	
	private JPanel contentPane;
	private static int idCrisisR;
	private JLabel botoneCartas[];
	private static List<Integer> cartas;
	private static Asociaciones asociaciones;
	private Point posCartaSuperviviente[] = {new Point(32, 94), new Point(230, 94), new Point(433, 94), new Point(643, 94), new Point(841, 94)
			, new Point(32, 334), new Point(230, 334), new Point(433, 334), new Point(643, 334), new Point(841, 334),
			new Point(32, 574), new Point(230, 574), new Point(433, 574), new Point(643, 574), new Point(841, 574)};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCartasAportadas frame = new FrameCartasAportadas(cartas, asociaciones, idCrisisR);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 29 nueva lenght/height, 54
	 */
	public FrameCartasAportadas(List<Integer> cartasAportadas, Asociaciones aso, int idCrisisRes) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1041, 852);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		
		botoneCartas = new JLabel[15];
		
		int tamSupervivientes = cartasAportadas.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(435, 807, 155, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblAportacionesCrisis = new JLabel("");
		lblAportacionesCrisis.setIcon(new ImageIcon(this.getClass().getResource("/Crisis/CAportadas.png")));
		lblAportacionesCrisis.setFont(new Font("Castellar", Font.BOLD, 24));
		lblAportacionesCrisis.setForeground(Color.WHITE);
		lblAportacionesCrisis.setBounds(34, 36, 390, 28);
		contentPane.add(lblAportacionesCrisis);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 24));
		lblNewLabel.setBounds(479, 23, 446, 54);
//		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/Crisis/CPrevenida.png"))); //Metida de prueba
		switch (idCrisisRes) {
		case 0:
			lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/Crisis/CFallida.png")));
			break;
		case 1:
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/Crisis/CResuelta.png")));			
			break;
		case 2:
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/Crisis/CPrevenida.png")));			
			break;
		default:
			break;
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 64, 390, 2);
		contentPane.add(separator);
		//565 y 67
		contentPane.add(lblNewLabel);
		
		
		
		
		for(int z = 0; z < tamSupervivientes; z++) {
			p = posCartaSuperviviente[z];
			botoneCartas[z] = new JLabel(String.valueOf(cartasAportadas.get(z)));
			icon = (ImageIcon) aso.getCartasObjetos().get(cartasAportadas.get(z)).getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botoneCartas[z].setIcon(new ImageIcon(img));
			botoneCartas[z].setBounds(p.x, p.y, 147, 205);
			contentPane.add(botoneCartas[z]);
		}
	}
}
