package seminarioCancelado;

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
		
		this.usuario = usuario;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaElCodigo = new JLabel("Introduzca el codigo de la partida:");
		lblIntroduzcaElCodigo.setBackground(new Color(255, 255, 255));
		lblIntroduzcaElCodigo.setForeground(new Color(255, 255, 255));
		lblIntroduzcaElCodigo.setBounds(10, 49, 240, 70);
		contentPane.add(lblIntroduzcaElCodigo);
		
		textFieldUnirse = new JTextField();
		textFieldUnirse.setBounds(209, 75, 96, 19);
		contentPane.add(textFieldUnirse);
		textFieldUnirse.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 10));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int partidarequest;
					partidarequest = Integer.parseInt(textFieldUnirse.getText());
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + partidarequest);
					FrameSala fCrear = new FrameSala(usuario);
					fCrear.setVisible(true);
					dispose();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Introduzca un código de partida válido");
				}
			}
		});
		btnConfirmar.setBounds(327, 75, 96, 19);
		contentPane.add(btnConfirmar);
		
		JButton btnNewButtonVolver = new JButton("Volver");
		btnNewButtonVolver.setFont(new Font("Arial", Font.PLAIN, 10));
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
		btnNewButtonVolver.setBounds(466, 315, 90, 19);
		contentPane.add(btnNewButtonVolver);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/seleccionimg.jpg"));
		Image img = ima.getImage().getScaledInstance(566, 344, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 566, 344);
		contentPane.add(label);
	}
}
