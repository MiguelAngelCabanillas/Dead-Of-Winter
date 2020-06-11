package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameMoverse2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMoverse2 frame = new FrameMoverse2();
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
	public FrameMoverse2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1940, 1048);
		setBackground(new Color(0, 0, 0, 10));
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Comisaria");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(553, 99, 124, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tienda");
		btnNewButton_1.setBounds(553, 392, 124, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Colegio");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1_1.setBounds(553, 713, 124, 43);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Gasolinera");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1_2.setBounds(1511, 99, 124, 43);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("Hospital");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1_3.setBounds(1511, 392, 124, 43);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("Biblioteca");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1_4.setBounds(1511, 713, 124, 43);
		contentPane.add(btnNewButton_1_4);
		
		JButton btnNewButton_2 = new JButton("OK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(997, 952, 124, 43);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_5 = new JButton("Colonia");
		btnNewButton_1_5.setBounds(997, 240, 124, 43);
		contentPane.add(btnNewButton_1_5);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
	}
	
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(1940, 1048);

	      setLocationRelativeTo(null);
	}
}
