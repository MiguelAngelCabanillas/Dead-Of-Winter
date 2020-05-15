package seminarioCancelado;

import BD.*;
import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Login {
	private JLabel label;
	private JFrame frame;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\franbono2\\No se porque falla el puto eclipse y ahora van aqui\\Dead of Winter\\images\\icono4.png"));
		frame.setBounds(100, 100, 1234, 821);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBounds(0, 0, 1024, 728);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel.setBounds(350, 479, 223, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(350, 568, 214, 57);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Calibri", Font.PLAIN, 20));
		textFieldUN.setBounds(627, 491, 223, 37);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String user = textFieldUN.getText();
					String passw = passwordField.getText();
					
					
					boolean correcto = iS.InicioSesion(user, passw);
					if(correcto) {
						frame.dispose();
						FrameSeleccion SalaSeleccion = new FrameSeleccion();
						SalaSeleccion.setVisible(true);
					}
					
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LoginButton.setBounds(441, 663, 123, 37);
		frame.getContentPane().add(LoginButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setEchoChar('*');
		passwordField.setBounds(627, 580, 223, 37);
		frame.getContentPane().add(passwordField);
		
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
		frame.getContentPane().add(btnNewButton);
		
		label = new JLabel("");
		
		
		label.setBounds(0, 0, 1218, 782);
		frame.getContentPane().add(label);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icono2.jpg")); //Creo el icono
		Image img = icon.getImage(); //Para poner la imagen sin modificar
		Image modifiedImage = img.getScaledInstance(1218, 782, java.awt.Image.SCALE_SMOOTH); //Imagen Modificada
		label.setIcon(new ImageIcon(modifiedImage)); //Pues la meto donde quiera
	}
}
