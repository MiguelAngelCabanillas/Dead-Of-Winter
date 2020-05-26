package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class FrameUnirse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUnirse;
	private static Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUnirse frame = new FrameUnirse(usuario);
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
	 */
	public FrameUnirse(Usuario usuario) {
		setTitle("Dead of Winter");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		this.usuario = usuario;
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/generic_user.png"));
		Image img2 = ima2.getImage().getScaledInstance(116, 110, java.awt.Image.SCALE_SMOOTH);

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1234, 821);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblIntroduzcaElCodigo = new JLabel("Introduzca la sala a la que quiera unirse:");
		lblIntroduzcaElCodigo.setForeground(Color.WHITE);
		lblIntroduzcaElCodigo.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblIntroduzcaElCodigo.setBackground(Color.WHITE);
		lblIntroduzcaElCodigo.setBounds(173, 240, 448, 70);
		contentPane.add(lblIntroduzcaElCodigo);
		
		textFieldUnirse = new JTextField();
		textFieldUnirse.setBounds(631, 262, 168, 33);
		contentPane.add(textFieldUnirse);
		textFieldUnirse.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String partidarequest;
					partidarequest = textFieldUnirse.getText();
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + partidarequest);
					FrameSala fCrear = new FrameSala(usuario);
					fCrear.setVisible(true);
					dispose();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Introduzca un código de partida válido");
				}
			}
		});
		btnConfirmar.setBounds(858, 262, 130, 33);
		contentPane.add(btnConfirmar);
		
		JButton btnNewButtonVolver = new JButton("Volver a inicio");
		btnNewButtonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					FrameSeleccion fseleccion;
					fseleccion = new FrameSeleccion(usuario);
					dispose();
					fseleccion.setVisible(true);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnNewButtonVolver.setBounds(10, 732, 234, 42);
		contentPane.add(btnNewButtonVolver);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		Image img = ima.getImage().getScaledInstance(1234, 821, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 1234, 821);
		contentPane.add(label);
	}
}
