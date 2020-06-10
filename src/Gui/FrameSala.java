package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Partida.Principal;

public class FrameSala extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	private static Thread HebraChat;
	private static int ObjetivoElegido;
	private JTextField EscribirChat;
	private JTextArea ChatArea;
	private JLabel lblNDeJugadores = new JLabel();
	private JLabel lblNewLabel = new JLabel();
	private boolean host = false;
	private static String[] objetivos = {"Más ejemplares", "Raiding Party"};
	private int numJugadores;
	private ObjetivosPrincipales obj;

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
		
//		int codigo;
//		int numJugadores;
		
		
//		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|nusuarios");
//		numJugadores =  Integer.parseInt(usuario.recibirMensajeDelServidor().split("\\|")[1]);
//		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|idsala");
//		codigo = Integer.parseInt(usuario.recibirMensajeDelServidor());
		
		
		usuario.getClientReader().setSala(this);
		usuario.getClientReader().setTablero(null);
		if(HebraChat != null) {
			usuario.getClientReader().getSemaphore().release();
		}else {
			HebraChat = new Thread(usuario.getClientReader());
			usuario.getClientReader().setSala(this);
			HebraChat.start();
		}
		
		
		
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|nusuarios");
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|idsala");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1234, 821);
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/generic_user.png"));
		Image img2 = ima2.getImage().getScaledInstance(116, 110, java.awt.Image.SCALE_SMOOTH);
		
		JButton ObjetivoPrincipal = new JButton("Elegir Objetivo Principal");
		ObjetivoPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|host");
					Thread.sleep(50);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(host) {
					if(obj != null) {
						obj.dispose();
					}
					obj = new ObjetivosPrincipales();
					obj.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Solo el host puede seleccionar el objetivo principal");
				}
				
			}
		});
		ObjetivoPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ObjetivoPrincipal.setBounds(270, 356, 313, 45);
		contentPane.add(ObjetivoPrincipal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(749, 118, 459, 585);
		contentPane.add(scrollPane);
		
		ChatArea = new JTextArea();
		ChatArea.setFont(new Font("Consolas", Font.PLAIN, 17));
		scrollPane.setViewportView(ChatArea);
		ChatArea.setEditable(false);
		
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
		
		JButton Enviar = new JButton("Enviar\r\n");
		Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					mandarMensaje();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		Enviar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Enviar.setBounds(1105, 714, 103, 33);
		contentPane.add(Enviar);
		
		EscribirChat = new JTextField();
		EscribirChat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						
						mandarMensaje();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		EscribirChat.setBounds(749, 712, 331, 33);
		contentPane.add(EscribirChat);
		EscribirChat.setColumns(10);
		
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblNewLabel.setBounds(860, 47, 348, 26);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Iniciar la partida con los jugadores conectados");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Partida.iniciar();
				try {
					usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|host");
					Thread.sleep(50);
					if(host) { //TODO: PROGRESS BAR, DISPOSE SALA
						usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|exit|tablero|" + ObjetivoElegido );
						btnNewButton.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Solo el host puede comenzar la partida");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(174, 449, 512, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButtonVolver = new JButton("Salir de la Sala\r\n");
		btnNewButtonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {
					
					salirSala();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButtonVolver.setBounds(31, 702, 209, 45);
		contentPane.add(btnNewButtonVolver);
		
		
		lblNDeJugadores.setBackground(new Color(255, 255, 255));
		lblNDeJugadores.setForeground(new Color(255, 255, 255));
		lblNDeJugadores.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblNDeJugadores.setBounds(136, 71, 313, 26);
		contentPane.add(lblNDeJugadores);
		
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/sala.jpg"));
		Image img = ima.getImage().getScaledInstance(1218, 782, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 1218, 782);
		contentPane.add(label);

	}
	
	public void actualizaChat(String mensaje) {
		if(ChatArea != null) {
		ChatArea.setText(ChatArea.getText().trim() + "\n" + mensaje);
		}
	}
	public void actNumJugadores(int numJugadores) {
		lblNDeJugadores.setText("N\u00BA de jugadores:    " + numJugadores);
		this.numJugadores = numJugadores;
	}
	public void actIdSala(int idSala) {
		lblNewLabel.setText("El numero de sala es: " + idSala);
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(1238, 806);

	      setLocationRelativeTo(null);
	}
	public static void setObjetivoPrincipal(int obj) throws IOException {
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|msgsala|Objetivo seleccionado -> " + objetivos[obj-1]);
		ObjetivoElegido = obj;
	}
	private void mandarMensaje() throws IOException {
		String msg = EscribirChat.getText().trim();
		if(!msg.equals("")) {
			usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + 1 + "|msgsala|" + msg);
			EscribirChat.setText("");
		}
	}
	public void avanzarATablero(int objetivo) throws IOException {
		FrameTablero tablero = new FrameTablero(objetivo, usuario);
		tablero.setVisible(true);
		dispose();
	}
	
	public void setIsHost(boolean h) {
		this.host = h;
	}
	
	public void salirSala() throws IOException {
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|1|exit|sala" );
		//
		FrameSeleccion fseleccion;
		fseleccion = new FrameSeleccion(usuario);
		fseleccion.setVisible(true);
		dispose();
	}
}
