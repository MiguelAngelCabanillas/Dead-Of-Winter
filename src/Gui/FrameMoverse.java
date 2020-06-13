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

public class FrameMoverse extends JFrame {

	private JPanel contentPane;
	private JButton botoneSuperviviente[];
	private static List<Integer> supJugadores;
	private static Asociaciones asociaciones;
	private Point posCartaSuperviviente[] = {new Point(32, 11), new Point(230, 11), new Point(433, 11), new Point(643, 11), new Point(841, 11)
			, new Point(32, 251), new Point(230, 251), new Point(433, 251), new Point(643, 251), new Point(841, 251)};
	private FrameMoverse2 auxMover;

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
		setBounds(100, 100, 1041, 529);
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
		btnNewButton.setBounds(433, 489, 155, 34);
		contentPane.add(btnNewButton);
		
		SupDerHandler supervivientesHandler = new SupDerHandler();
		
		asociaciones = aso;
		
		botoneSuperviviente = new JButton[10];
		
		int tamSupervivientes = supJugadores.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		for(int z = 0; z < tamSupervivientes; z++) {
			p = posCartaSuperviviente[z];
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
			
			if(auxMover != null) {
				auxMover.dispose();
			} 
			auxMover = new FrameMoverse2();
			auxMover.setVisible(true);
			dispose();
		}
		
	}
}
