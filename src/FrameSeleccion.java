import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameSeleccion extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSeleccion frame = new FrameSeleccion();
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
	public FrameSeleccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.getBackground();
		
		JLabel lblNewLabel = new JLabel("Eleccción de Sala");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel.setBounds(110, 10, 624, 57);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButtonCrear = new JButton("Crear Sala");
		btnNewButtonCrear.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrameCrear fCrear = new FrameCrear();
					fCrear.setVisible(true);
					dispose();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButtonCrear.setBounds(283, 110, 212, 93);
		contentPane.add(btnNewButtonCrear);
		
		JButton btnNewButtonUnirse = new JButton("Unirse a una Sala");
		btnNewButtonUnirse.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButtonUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrameUnirse fUnirse = new FrameUnirse();
					fUnirse.setVisible(true);
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButtonUnirse.setBounds(283, 251, 212, 93);
		contentPane.add(btnNewButtonUnirse);
		
		ImageIcon ima = new ImageIcon("C:\\Users\\cgmoy\\eclipse-workspace\\GuiPartidas\\src\\imagenes\\seleccionimg.jpg");
		Image img = ima.getImage().getScaledInstance(786, 573, java.awt.Image.SCALE_SMOOTH); 
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 786, 573);
		contentPane.add(lblNewLabel_1);
		
		
		
		/*JButton btnNewButtonContinuar = new JButton("Continuar Partida");
		btnNewButtonContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrameContinuar fContinuar = new FrameContinuar();
					fContinuar.setVisible(true);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButtonContinuar.setBounds(173, 240, 130, 38);
		contentPane.add(btnNewButtonContinuar);*/
	}

}
