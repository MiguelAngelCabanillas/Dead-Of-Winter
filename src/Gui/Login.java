package Gui;

import BD.*;
import Server.ClientReader;
import Server.Usuario;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Login {
	private JLabel label;
	private JFrame frmDeadOfWinter;
	IniciarSesion iS;
	RegistroInicioSesion rIS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmDeadOfWinter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Conexion conexion = new Conexion();
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		iS = new IniciarSesion(conexion);
		rIS = new RegistroInicioSesion(conexion);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDeadOfWinter = new JFrame();
		frmDeadOfWinter.setTitle("Dead of Winter");
		frmDeadOfWinter.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		frmDeadOfWinter.setBounds(100, 100, 1234, 821);
		frmDeadOfWinter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDeadOfWinter.getContentPane().setBounds(0, 0, 1024, 728);
		frmDeadOfWinter.getContentPane().setLayout(null);
		frmDeadOfWinter.getContentPane().setFocusable(true);
		ajustarAPantalla();
		
		
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDeadOfWinter.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(441, 711, 309, 33);
		frmDeadOfWinter.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel.setBounds(350, 479, 223, 57);
		frmDeadOfWinter.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(350, 568, 214, 57);
		frmDeadOfWinter.getContentPane().add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Calibri", Font.PLAIN, 20));
		textFieldUN.setBounds(627, 491, 223, 37);
		frmDeadOfWinter.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						
						String user = textFieldUN.getText();
						String passw = passwordField.getText();
						
						
						boolean correcto = iS.InicioSesion(user, passw);
						if(correcto) {
							frmDeadOfWinter.dispose();
							Socket peticion = new Socket("25.66.43.164", 12975);
							ClientReader cr = new ClientReader(peticion);
							cr.hacerPeticionAlServidor(user);
							Usuario usuario = new Usuario(user, cr);
							FrameSeleccion SalaSeleccion = new FrameSeleccion(usuario);
							SalaSeleccion.setVisible(true);
						}
						
						
					} catch (IOException e2) {
						
						JOptionPane.showMessageDialog(null, "Conexion Fallida");
					}
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LoginButton.setBounds(441, 663, 123, 37);
		frmDeadOfWinter.getContentPane().add(LoginButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setEchoChar('*');
		passwordField.setBounds(627, 580, 223, 37);
		frmDeadOfWinter.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = textFieldUN.getText();
					String passw = passwordField.getText();
					
					rIS.registrarUsuario(user, passw);
					
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(627, 663, 123, 37);
		frmDeadOfWinter.getContentPane().add(btnNewButton);
		
		label = new JLabel("");
		
		
		label.setBounds(0, 0, 1218, 782);
		frmDeadOfWinter.getContentPane().add(label);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icono2.jpg")); //Creo el icono
		Image img = icon.getImage(); //Para poner la imagen sin modificar
		Image modifiedImage = img.getScaledInstance(1218, 782, java.awt.Image.SCALE_SMOOTH); //Imagen Modificada
		label.setIcon(new ImageIcon(modifiedImage)); //Pues la meto donde quiera
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      frmDeadOfWinter.setSize(1234, 821);

	      frmDeadOfWinter.setLocationRelativeTo(null);
	}
}
