package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.ClientReader;
import Server.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class FrameSeleccion extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSeleccion frame = new FrameSeleccion(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario 
	 * @throws IOException 
	 */
	public FrameSeleccion(Usuario usuario) throws IOException {
		
		this.usuario = usuario;
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/generic_user.png"));
		Image img2 = ima2.getImage().getScaledInstance(116, 110, java.awt.Image.SCALE_SMOOTH);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1234, 821);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.getBackground();
		
		JLabel lblNewLabel = new JLabel("Eleccción de Sala");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel.setBounds(391, 107, 624, 57);
		contentPane.add(lblNewLabel);
		
		JLabel userName = new JLabel("Nombre de usuario: " + usuario.getNombre());
		userName.setForeground(Color.WHITE);
		userName.setFont(new Font("Arial Black", Font.PLAIN, 18));
		userName.setBackground(Color.WHITE);
		userName.setBounds(136, 34, 410, 26);
		contentPane.add(userName);
		
		JLabel GenericUser = new JLabel("");
		GenericUser.setBounds(10, 11, 116, 110);
		GenericUser.setIcon(new ImageIcon(img2));
		contentPane.add(GenericUser);
		
		JButton btnNewButtonCrear = new JButton("Crear Sala");
		btnNewButtonCrear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|-1");
					FrameSala fCrear = new FrameSala(usuario);
					fCrear.setVisible(true);
					dispose();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonCrear.setBounds(449, 263, 212, 93);
		contentPane.add(btnNewButtonCrear);
		
		JButton btnNewButtonUnirse = new JButton("Unirse a una Sala");
		btnNewButtonUnirse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					FrameUnirse fUnirse = new FrameUnirse(usuario);
					fUnirse.setVisible(true);
					dispose();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButtonUnirse.setBounds(449, 476, 212, 93);
		contentPane.add(btnNewButtonUnirse);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		Image img = ima.getImage().getScaledInstance(1218, 782, java.awt.Image.SCALE_SMOOTH); 
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 1218, 782);
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
