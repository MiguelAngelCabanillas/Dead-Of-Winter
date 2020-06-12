package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ObjPrincipal extends JFrame {

	private JPanel contentPane;
	private static int objetivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObjPrincipal frame = new ObjPrincipal(objetivo);
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
	public ObjPrincipal(int objetivo) {
		
		this.objetivo = objetivo;
		
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
		
		
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/Objetivos-Principales/NecesitamosEjemplares.jpg"));
		Image img2 = ima2.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon ima3 = new ImageIcon(this.getClass().getResource("/Objetivos-Principales/PartidaDeSaqueo.jpg"));
		Image img3 = ima3.getImage().getScaledInstance(300, 420, java.awt.Image.SCALE_SMOOTH);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 300, 420);
		if(objetivo == 1) {
			lblNewLabel.setIcon(new ImageIcon(img2));
		}else if (objetivo == 2) {
			lblNewLabel.setIcon(new ImageIcon(img3));
		}
		
		JButton btnNewButton = new JButton("Volver\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(115, 442, 89, 34);
		contentPane.add(btnNewButton);
		
		
		contentPane.add(lblNewLabel);
		
		
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(326, 486);

	      setLocationRelativeTo(null);
	}
}
