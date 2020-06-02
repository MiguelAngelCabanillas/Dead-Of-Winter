package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ObjetivosPrincipales extends JFrame {

	private JPanel contentPane;
	private int ObjetivoElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObjetivosPrincipales frame = new ObjetivosPrincipales();
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
	public ObjetivosPrincipales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 10));
		setBounds(100, 100, 766, 441);
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		contentPane.setLayout(null);
		
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/NecesitamosEjemplares.png"));
		Image img2 = ima2.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon ima3 = new ImageIcon(this.getClass().getResource("/RaidingParty.png"));
		Image img3 = ima3.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		JButton SelecMasEjemplares = new JButton("");
		SelecMasEjemplares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ObjetivoElegido = 1;
				try {
					FrameSala.setObjetivoPrincipal(ObjetivoElegido);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		SelecMasEjemplares.setBounds(10, 11, 300, 420);
		SelecMasEjemplares.setIcon(new ImageIcon(img2));
		contentPane.add(SelecMasEjemplares);
		
		JButton SelecRaidingParty = new JButton("");
		SelecRaidingParty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ObjetivoElegido = 2;
				try {
					FrameSala.setObjetivoPrincipal(ObjetivoElegido);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		SelecRaidingParty.setBounds(364, 11, 300, 420);
		SelecRaidingParty.setIcon(new ImageIcon(img3));
		contentPane.add(SelecRaidingParty);
		
		
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(677, 445);

	      setLocationRelativeTo(null);
	}
}
