package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameAportacionesCrisis extends JFrame {

	private JPanel contentPane;
	private static List<String> jugadores;
	private static List<Integer> aportaciones;
	private Point posCartasJug[] = {new Point(27, 29), new Point(27, 75), new Point(27, 120), new Point(27, 164), new Point(27, 209)};
	private JLabel labelJugadores[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAportacionesCrisis frame = new FrameAportacionesCrisis(jugadores, aportaciones);
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
	public FrameAportacionesCrisis(List<String> Jugadores, List<Integer> aportacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 301);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		labelJugadores = new JLabel[5];
		
		Point p;
		for(int i = 0; i < Jugadores.size(); i++) {
			p = posCartasJug[i];
			labelJugadores[i] = new JLabel(Jugadores.get(i) + " ha aportado " + aportacion.get(i) + " cartas a la crisis");
			labelJugadores[i].setForeground(Color.WHITE);
			labelJugadores[i].setFont(new Font("Castellar", Font.BOLD, 17));
			labelJugadores[i].setBounds(p.x, p.y, 677, 21);
			contentPane.add(labelJugadores[i]);
		}
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(281, 250, 121, 40);
		contentPane.add(btnNewButton);
		
		JLabel fondoAportaciones = new JLabel("");
		fondoAportaciones.setBounds(0, 0, 714, 301);
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/fondoAportaciones.jpg")); //La imagen pesa la vida
		//Image img = ima.getImage().getScaledInstance(714, 301, java.awt.Image.SCALE_SMOOTH); 
		fondoAportaciones.setIcon(ima/*new ImageIcon(img)*/);
		contentPane.add(fondoAportaciones);
		
	}
}
