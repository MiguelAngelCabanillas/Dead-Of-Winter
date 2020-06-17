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
	private JLabel botoneCartas[];
	private static List<Integer> cartas;
	private static Asociaciones asociaciones;
	private Point posCartaSuperviviente[] = {new Point(32, 40), new Point(230, 40), new Point(433, 40), new Point(643, 40), new Point(841, 40)
			, new Point(32, 280), new Point(230, 280), new Point(433, 280), new Point(643, 280), new Point(841, 280),
			new Point(32, 520), new Point(230, 520), new Point(433, 520), new Point(643, 520), new Point(841, 520)};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCartasAportadas frame = new FrameCartasAportadas(cartas, asociaciones);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 29 nueva lenght/height
	 */
	public FrameCartasAportadas(List<Integer> cartasAportadas, Asociaciones aso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1041, 798);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(433, 758, 155, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblAportacionesCrisis = new JLabel("Cartas Aportadas a la Crisis:");
		lblAportacionesCrisis.setFont(new Font("Castellar", Font.BOLD, 24));
		lblAportacionesCrisis.setForeground(Color.WHITE);
		lblAportacionesCrisis.setBounds(10, 11, 487, 29);
		contentPane.add(lblAportacionesCrisis);
		
		JLabel lblNewLabel = new JLabel("Crisis Fallida");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 24));
		lblNewLabel.setBounds(634, 11, 217, 29);
		contentPane.add(lblNewLabel);
		
		
		botoneCartas = new JLabel[15];
		
		int tamSupervivientes = cartasAportadas.size();
		ImageIcon icon;
		Image img;
		Point p;
		
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
