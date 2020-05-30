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
import java.awt.event.ActionEvent;

public class ObjetivosPrincipales extends JFrame {

	private JPanel contentPane;

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
		contentPane.setLayout(null);
		
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/NecesitamosEjemplares.png"));
		Image img2 = ima2.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon ima3 = new ImageIcon(this.getClass().getResource("/RaidingParty.png"));
		Image img3 = ima3.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		JButton SelecMasEjemplares = new JButton("");
		SelecMasEjemplares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		SelecMasEjemplares.setBounds(10, 11, 300, 420);
		SelecMasEjemplares.setIcon(new ImageIcon(img2));
		contentPane.add(SelecMasEjemplares);
		
		JButton SelecRaidingParty = new JButton("");
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
