package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class FrameTuTurno extends JFrame {

	private JPanel contentPane;
	private static String imagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTuTurno frame = new FrameTuTurno(imagen);
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
	public FrameTuTurno(String image) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		setBackground(new Color(0, 0, 0, 110));
		
		JLabel lblturno = new JLabel("");
		ImageIcon ima = new ImageIcon(this.getClass().getResource(image));
		Image img = ima.getImage().getScaledInstance(912, 133, java.awt.Image.SCALE_SMOOTH); 
		lblturno.setBounds(12, 13, 912, 133);
		lblturno.setIcon(new ImageIcon(img));
		setBounds(0, 0, 912, 133);
		setLocationRelativeTo(null);		
		contentPane.add(lblturno);

	}
}
