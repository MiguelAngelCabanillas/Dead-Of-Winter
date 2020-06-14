package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		ajustarAPantalla();
		contentPane.setLayout(null);
		
		JLabel userName = new JLabel("Nombre de usuario: " + usuario.getNombre());
		userName.setForeground(Color.WHITE);
		userName.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		userName.setBackground(Color.WHITE);
		userName.setBounds(136, 34, 410, 26);
		contentPane.add(userName);
		
		JLabel GenericUser = new JLabel("");
		GenericUser.setBounds(10, 11, 116, 110);
		GenericUser.setIcon(new ImageIcon(img2));
		contentPane.add(GenericUser);
		
		JLabel lblIntroduzcaElCodigo = new JLabel("Introduzca la sala a la que quiera unirse:");
		lblIntroduzcaElCodigo.setForeground(Color.WHITE);
		lblIntroduzcaElCodigo.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblIntroduzcaElCodigo.setBackground(Color.WHITE);
		lblIntroduzcaElCodigo.setBounds(136, 268, 462, 27);
		contentPane.add(lblIntroduzcaElCodigo);
		
		textFieldUnirse = new JTextField();
		textFieldUnirse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						
						confirmarSala();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textFieldUnirse.setBounds(631, 262, 168, 33);
		contentPane.add(textFieldUnirse);
		textFieldUnirse.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					try {
						confirmarSala();
					} catch (IOException e) {
						e.printStackTrace();
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
					e.printStackTrace();
					System.out.println(e.getMessage());
				} 
			}
		});
		btnNewButtonVolver.setBounds(10, 719, 234, 42);
		contentPane.add(btnNewButtonVolver);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		Image img = ima.getImage().getScaledInstance(1234, 821, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 1234, 821);
		contentPane.add(label);
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(1234, 821);

	      setLocationRelativeTo(null);
	}
	private void confirmarSala() throws IOException {
		System.out.println("llega");
		String partidarequest = textFieldUnirse.getText();
		String trim = partidarequest.trim();
		System.out.println(trim);
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + trim);
		System.out.println(usuario.getNombre() + "|" + trim);
		String aceptacion = usuario.recibirMensajeDelServidor();
		
		if(aceptacion != null && aceptacion.equals("no")) {
			JOptionPane.showMessageDialog(null, "Error: La sala está llena");
		}
		else {
			FrameSala fCrear = new FrameSala(usuario);
			fCrear.setVisible(true);
			dispose();
		}
		textFieldUnirse.setText("");
		System.out.println("Sale");
	}
}
