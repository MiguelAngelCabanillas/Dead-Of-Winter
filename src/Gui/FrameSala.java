package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Usuario;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrameSala extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	private JTextField EscribirChat;
	private JTextArea ChatArea;
	private JLabel lblNDeJugadores;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					FrameSala frame;
					try {
						frame = new FrameSala(usuario);
						frame.setVisible(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
	public FrameSala(Usuario usuario) throws IOException {
		setTitle("Dead of Winter");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		this.usuario = usuario;
		
		int codigo;
		int numJugadores;
		
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|nusuarios");
		numJugadores =  Integer.parseInt(usuario.recibirMensajeDelServidor().split("\\|")[1]);
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|idsala");
		codigo = Integer.parseInt(usuario.recibirMensajeDelServidor());
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1234, 821);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/generic_user.png"));
		Image img2 = ima2.getImage().getScaledInstance(116, 110, java.awt.Image.SCALE_SMOOTH);
		
		JButton ObjetivoPrincipal = new JButton("Elegir Objetivo Principal");
		ObjetivoPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ObjetivoPrincipal.setBounds(270, 356, 313, 45);
		contentPane.add(ObjetivoPrincipal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(749, 118, 459, 585);
		contentPane.add(scrollPane);
		
		ChatArea = new JTextArea();
		scrollPane.setViewportView(ChatArea);
		
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
		
		JButton Enviar = new JButton("Enviar\r\n");
		Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msg = EscribirChat.getText().trim();
					if(!msg.equals("")) {
						usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + codigo + "|msgsala|" + msg);
						EscribirChat.setText("");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Enviar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Enviar.setBounds(1105, 714, 103, 33);
		contentPane.add(Enviar);
		
		EscribirChat = new JTextField();
		EscribirChat.setBounds(749, 712, 331, 33);
		contentPane.add(EscribirChat);
		EscribirChat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("El numero de sala es: " + codigo);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setBounds(860, 47, 229, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Iniciar la partida con los jugadores conectados");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Partida.iniciar();
			}
		});
		btnNewButton.setBounds(174, 449, 512, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButtonVolver = new JButton("Salir de la Sala\r\n");
		btnNewButtonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {
					
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|exitsala" );
					
					FrameSeleccion fseleccion;
					fseleccion = new FrameSeleccion(usuario);
					fseleccion.setVisible(true);
					dispose();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButtonVolver.setBounds(31, 702, 209, 45);
		contentPane.add(btnNewButtonVolver);
		
		lblNDeJugadores = new JLabel("N\u00BA de jugadores:    " + numJugadores);
		lblNDeJugadores.setBackground(new Color(255, 255, 255));
		lblNDeJugadores.setForeground(new Color(255, 255, 255));
		lblNDeJugadores.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNDeJugadores.setBounds(136, 71, 197, 26);
		contentPane.add(lblNDeJugadores);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		Image img = ima.getImage().getScaledInstance(1218, 782, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 1218, 782);
		contentPane.add(label);


//		usuario.getClientReader().setSala(this);
//		Thread HebraChat = new Thread(usuario.getClientReader());
//		HebraChat.start();
		
		
//		while (true) {
//			
//			String msgllegada = usuario.recibirMensajeDelServidor();
//			String[] splitedmsg = msgllegada.split("\\|");
//			switch (splitedmsg[0]) {
//			case "chat":
//				ChatArea.setText(ChatArea.getText().trim() + "\n" + splitedmsg[1]);
//				break;
//
//			default:
//				break;
//			}
//			
//		}
	}
	
	public void actualizaChat(String mensaje) {
		ChatArea.setText(ChatArea.getText().trim() + "\n" + mensaje);
	}
	public void enviarNumJugadores(int numJugadores) {
		lblNDeJugadores.setText("N\u00BA de jugadores:    " + numJugadores);
	}
}