package Gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameTablero extends JFrame {

	private static int objetivo;
	private JPanel contentPane;
	private JTextField txtChat;
	
	private JLabel fichMoral1;
	private JLabel fichMoral2;
	private JLabel fichMoral3;
	private JLabel fichMoral4;
	private JLabel fichMoral5;
	private JLabel fichMoral6;
	private JLabel fichMoral7;
	private JLabel fichMoral8;
	private JLabel fichMoral9;
	private JLabel fichMoral10;
	
	private JLabel fichRonda1;
	private JLabel fichRonda2;
	private JLabel fichRonda3;
	private JLabel fichRonda4;
	private JLabel fichRonda5;
	private JLabel fichRonda6;
	private JLabel fichRonda7;
	private JLabel fichRonda8;
	private JLabel fichRonda9;
	private JLabel fichRonda10;
	
	private JLabel fichZComisaria1;
	private JLabel fichZComisaria2;
	private JLabel fichZComisaria3;
	private JLabel fichZComisaria4;
	
	private JLabel fichZSuperm1;
	private JLabel fichZSuperm2;
	private JLabel fichZSuperm3;
	private JLabel fichZSuperm4;
	
	private JLabel fichZColegio1;
	private JLabel fichZColegio2;
	private JLabel fichZColegio3;
	private JLabel fichZColegio4;
	
	private JLabel fichZGasolinera1;
	private JLabel fichZGasolinera2;
	private JLabel fichZGasolinera3;
	
	private JLabel fichZHospital1;
	private JLabel fichZHospital2;
	private JLabel fichZHospital3;
	private JLabel fichZHospital4;
	
	private JLabel fichZBiblioteca1;
	private JLabel fichZBiblioteca2;
	private JLabel fichZBiblioteca3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTablero frame = new FrameTablero(objetivo);
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
	public FrameTablero(int objetivo) {
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setForeground(Color.BLACK);
		setTitle("Dead of Winter\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		
		//OBJETIVO PRINCIPAL PASADO COMO PARAMETRO AL CONSTRUCTOR
		this.objetivo = objetivo;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnFile);
		
		JMenu mnAsda = new JMenu("asda");
		mnFile.add(mnAsda);
		
		JMenuItem mntmXedwa = new JMenuItem("xedwa");
		mnAsda.add(mntmXedwa);
		
		JMenu mnView = new JMenu("View");
		mnView.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnView);
		
		JMenu mnTools = new JMenu("Tools");
		mnTools.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnTools);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnOptions);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		fichZColegio1 = new JLabel("");
		fichZColegio1.setBounds(568, 836, 36, 34);
		contentPane.add(fichZColegio1);
		
		JLabel fichZColegio3 = new JLabel("");
		fichZColegio3.setBounds(664, 836, 36, 34);
		contentPane.add(fichZColegio3);
		
		JLabel fichZColegio4 = new JLabel("");
		fichZColegio4.setBounds(712, 836, 36, 34);
		contentPane.add(fichZColegio4);
		
		JLabel fichZColegio2 = new JLabel("");
		fichZColegio2.setBounds(616, 835, 36, 34);
		contentPane.add(fichZColegio2);
		
		JLabel fichZSuperm4 = new JLabel("");
		fichZSuperm4.setBounds(712, 530, 36, 34);
		contentPane.add(fichZSuperm4);
		
		fichZSuperm1 = new JLabel("");
		fichZSuperm1.setBounds(568, 530, 36, 34);
		contentPane.add(fichZSuperm1);
		
		JLabel fichZSuperm2 = new JLabel("");
		fichZSuperm2.setBounds(616, 529, 36, 34);
		contentPane.add(fichZSuperm2);
		
		JLabel fichZSuperm3 = new JLabel("");
		fichZSuperm3.setBounds(664, 530, 36, 34);
		contentPane.add(fichZSuperm3);
		
		JButton btnSendChat = new JButton(">");
		btnSendChat.setToolTipText("Envia un mensaje al chat");
		btnSendChat.setBounds(257, 906, 41, 56);
		contentPane.add(btnSendChat);
		
		JButton btnInfoJugador = new JButton("INFO JUGADOR");
		btnInfoJugador.setToolTipText("Muestra información sobre el jugador actual");
		btnInfoJugador.setBounds(1267, 73, 140, 41);
		contentPane.add(btnInfoJugador);
		
		JButton btnInfoTablero = new JButton("INFO TABLERO");
		btnInfoTablero.setToolTipText("Muestra información sobre el estado actual del tablero");
		btnInfoTablero.setBounds(847, 73, 128, 41);
		contentPane.add(btnInfoTablero);
		
		///LABELS MORAL
		fichMoral1 = new JLabel("");
		fichMoral1.setBounds(1144, 914, 36, 33);
		fichMoral1.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral1.setVisible(false);
		contentPane.add(fichMoral1);
		
		fichMoral2 = new JLabel("");
		fichMoral2.setBounds(1187, 914, 36, 33);
		fichMoral2.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral2.setVisible(false);
		contentPane.add(fichMoral2);
		
		fichMoral3 = new JLabel("");
		fichMoral3.setBounds(1229, 914, 36, 33);
		fichMoral3.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral3.setVisible(false);
		contentPane.add(fichMoral3);
		
		fichMoral4 = new JLabel("");
		fichMoral4.setBounds(1272, 914, 36, 33);
		fichMoral4.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral4.setVisible(false);
		contentPane.add(fichMoral4);
		
		fichMoral5 = new JLabel("");
		fichMoral5.setBounds(1317, 914, 36, 33);
		fichMoral5.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral5.setVisible(false);
		contentPane.add(fichMoral5);
		
		fichMoral6 = new JLabel("");
		fichMoral6.setBounds(1357, 914, 36, 33);
		fichMoral6.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral6.setVisible(false);
		contentPane.add(fichMoral6);
		
		fichMoral7 = new JLabel("");
		fichMoral7.setBounds(1398, 914, 36, 33);
		fichMoral7.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral7.setVisible(false);
		contentPane.add(fichMoral7);
		
		fichMoral8 = new JLabel("");
		fichMoral8.setBounds(1440, 914, 36, 33);
		fichMoral8.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral8.setVisible(false);
		contentPane.add(fichMoral8);
		
		fichMoral9 = new JLabel("");
		fichMoral9.setBounds(1483, 914, 36, 33);
		fichMoral9.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral9.setVisible(false);
		contentPane.add(fichMoral9);
		
		fichMoral10 = new JLabel("");
		fichMoral10.setBounds(1526, 914, 36, 33);
		fichMoral10.setIcon(imgCircular("images/MoralDef.png",36,33));
		fichMoral10.setVisible(false);
		contentPane.add(fichMoral10);
		
		///LABELS RONDAS
		fichRonda1 = new JLabel("");
		fichRonda1.setBounds(1061, 914, 36, 33);
		fichRonda1.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda1.setVisible(false);
		contentPane.add(fichRonda1);
		
		fichRonda2 = new JLabel("");
		fichRonda2.setBounds(1018, 914, 36, 33);
		fichRonda2.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda2.setVisible(false);
		contentPane.add(fichRonda2);
		
		fichRonda3 = new JLabel("");
		fichRonda3.setBounds(977, 914, 36, 33);
		fichRonda3.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda3.setVisible(false);
		contentPane.add(fichRonda3);
		
		fichRonda4 = new JLabel("");
		fichRonda4.setBounds(934, 914, 36, 33);
		fichRonda4.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda4.setVisible(false);
		contentPane.add(fichRonda4);
		
		fichRonda5 = new JLabel("");
		fichRonda5.setBounds(892, 914, 36, 33);
		fichRonda5.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda5.setVisible(false);
		contentPane.add(fichRonda5);
		
		fichRonda6 = new JLabel("");
		fichRonda6.setBounds(849, 914, 36, 33);
		fichRonda6.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda6.setVisible(false);
		contentPane.add(fichRonda6);
		
		fichRonda7 = new JLabel("");
		fichRonda7.setBounds(808, 914, 36, 33);
		fichRonda7.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda7.setVisible(false);
		contentPane.add(fichRonda7);
		
		fichRonda8 = new JLabel("");
		fichRonda8.setBounds(765, 914, 36, 33);
		fichRonda8.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda8.setVisible(false);
		contentPane.add(fichRonda8);
		
		fichRonda9 = new JLabel("");
		fichRonda9.setBounds(723, 914, 36, 33);
		fichRonda9.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda9.setVisible(false);
		contentPane.add(fichRonda9);
		
		fichRonda10 = new JLabel("");
		fichRonda10.setBounds(680, 914, 36, 33);
		fichRonda10.setIcon(imgCircular("images/RondaDef.png",36,33));
		fichRonda10.setVisible(false);
		contentPane.add(fichRonda10);
		
		
		
		JLabel AccReqDice = new JLabel("Necesitan Dado\r\n");
		AccReqDice.setForeground(Color.WHITE);
		AccReqDice.setFont(new Font("Arial", Font.PLAIN, 18));
		AccReqDice.setBounds(12, 59, 128, 22);
		contentPane.add(AccReqDice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 37, 286, 2);
		contentPane.add(separator);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		///LABELS LOCALIZACIONES
		
		///LABELS COLONIA
		
		///LABELS SUPERVIVIENTES
		JLabel fichSColonia2 = new JLabel("");
		fichSColonia2.setBounds(1026, 339, 36, 34);
		//fichSColonia2.setIcon(imgCircular("images/fichaSupIndefenso.png",36,34));
		contentPane.add(fichSColonia2);
		
		JLabel fichSColonia1 = new JLabel("");
		fichSColonia1.setBounds(977, 339, 36, 34);
		//fichSColonia1.setIcon(imgCircular("images/fichaSupIndefenso.png",36,34));
		contentPane.add(fichSColonia1);
		
		///LABELS AUXILIARES
		JLabel fichAlimento3 = new JLabel("");
		fichAlimento3.setBounds(1180, 559, 36, 33);
		fichAlimento3.setIcon(imgCircular("images/fichaAlimento.png",36,33));
		contentPane.add(fichAlimento3);
		
		JLabel fichAlimento2 = new JLabel("");
		fichAlimento2.setBounds(1227, 596, 36, 33);
		fichAlimento2.setIcon(imgCircular("images/fichaAlimento.png",36,33));
		contentPane.add(fichAlimento2);
		
		JLabel fichAlimento1 = new JLabel("");
		fichAlimento1.setBounds(1180, 607, 36, 33);
		fichAlimento1.setIcon(imgCircular("images/fichaAlimento.png",36,33));
		contentPane.add(fichAlimento1);
		
		///LABELS COMISARIA
		
		///LABELS RUIDO
		JLabel fichRuidoComisaria2 = new JLabel("");
		fichRuidoComisaria2.setBounds(422, 224, 33, 33);
		//fichRuidoComisaria2.setIcon(imgCircular("images/fichaRuido.png",33,33));
		contentPane.add(fichRuidoComisaria2);
		
		JLabel fichRuidoComisaria1 = new JLabel("");
		fichRuidoComisaria1.setBounds(390, 224, 33, 33);
		//fichRuidoComisaria1.setIcon(imgCircular("images/fichaRuido.png",33,33));
		contentPane.add(fichRuidoComisaria1);
		
		///LABELS ZOMBIES
		fichZComisaria4 = new JLabel("");
		fichZComisaria4.setBounds(712, 224, 36, 34);
		fichZComisaria4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZComisaria4.setVisible(false);
		contentPane.add(fichZComisaria4);
		
		fichZComisaria3 = new JLabel("");
		fichZComisaria3.setBounds(664, 224, 36, 34);
		fichZComisaria3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZComisaria3.setVisible(false);
		contentPane.add(fichZComisaria3);
		
		fichZComisaria2 = new JLabel("");
		fichZComisaria2.setBounds(616, 223, 36, 34);
		fichZComisaria2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZComisaria2.setVisible(false);
		contentPane.add(fichZComisaria2);
		
		fichZComisaria1 = new JLabel("");
		fichZComisaria1.setBounds(568, 221, 36, 34);
		fichZComisaria1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZComisaria1.setVisible(false);
		contentPane.add(fichZComisaria1);
		
		///LABELS SUPERVIVIENTES
		JLabel fichSComisaria3 = new JLabel("");
		fichSComisaria3.setBounds(686, 163, 36, 34);
		contentPane.add(fichSComisaria3);
		
		JLabel fichSComisaria2 = new JLabel("");
		fichSComisaria2.setBounds(640, 164, 36, 34);
		//fichSComisaria2.setIcon(imgCircular("images/SupThomasHeart.png",36,34));
		contentPane.add(fichSComisaria2);
		
		JLabel fichSComisaria1 = new JLabel("");
		fichSComisaria1.setBounds(593, 163, 36, 34);
		//fichSComisaria1.setIcon(imgCircular("images/SupLorettaClay.png",36,34));
		contentPane.add(fichSComisaria1);
		
		//LABELS SUPERMERCADO
		
		//LABELS COLEGIO
		
		//LABELS GASOLINERA
		
		fichZGasolinera2 = new JLabel("");
		fichZGasolinera2.setBounds(1573, 222, 36, 34);
		fichZGasolinera2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZGasolinera2.setVisible(false);
		contentPane.add(fichZGasolinera2);
		
		fichZGasolinera3 = new JLabel("");
		fichZGasolinera3.setBounds(1621, 223, 36, 34);
		fichZGasolinera3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZGasolinera3.setVisible(false);
		contentPane.add(fichZGasolinera3);
		
		fichZGasolinera1 = new JLabel("");
		fichZGasolinera1.setBounds(1525, 223, 36, 34);
		fichZGasolinera1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZGasolinera1.setVisible(false);
		contentPane.add(fichZGasolinera1);
		
		//LABELS HOSPITAL
		
		fichZHospital3 = new JLabel("");
		fichZHospital3.setBounds(1596, 530, 36, 34);
		contentPane.add(fichZHospital3);
		
		fichZHospital1 = new JLabel("");
		fichZHospital1.setBounds(1500, 530, 36, 34);
		contentPane.add(fichZHospital1);
		
		fichZHospital2 = new JLabel("");
		fichZHospital2.setBounds(1548, 529, 36, 34);
		contentPane.add(fichZHospital2);
		
		fichZHospital4 = new JLabel("");
		fichZHospital4.setBounds(1644, 530, 36, 34);
		contentPane.add(fichZHospital4);

		//LABELS BIBLIOTECA
		
		fichZBiblioteca3 = new JLabel("");
		fichZBiblioteca3.setBounds(1621, 836, 36, 34);
		fichZBiblioteca3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZBiblioteca3.setVisible(false);
		contentPane.add(fichZBiblioteca3);
		
		fichZBiblioteca1 = new JLabel("");
		fichZBiblioteca1.setBounds(1525, 836, 36, 34);
		fichZBiblioteca1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZBiblioteca1.setVisible(false);
		contentPane.add(fichZBiblioteca1);
		
		fichZBiblioteca2 = new JLabel("");
		fichZBiblioteca2.setBounds(1573, 835, 36, 34);
		fichZBiblioteca2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZBiblioteca2.setVisible(false);
		contentPane.add(fichZBiblioteca2);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		///LABELS CARTAS DE BÚSQUEDA DE OBJETOS
		JLabel lblBusquedaObj_4 = new JLabel("");
		lblBusquedaObj_4.setBounds(1725, 356, 129, 158);
		lblBusquedaObj_4.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj_4);
		
		JLabel lblBusquedaObj_3 = new JLabel("");
		lblBusquedaObj_3.setBounds(1725, 664, 129, 158);
		lblBusquedaObj_3.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj_3);
		
		JLabel lblBusquedaObj_5 = new JLabel("");
		lblBusquedaObj_5.setBounds(1725, 48, 129, 158);
		lblBusquedaObj_5.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj_5);
		
		JLabel lblBusquedaObj_2 = new JLabel("");
		lblBusquedaObj_2.setBounds(390, 48, 129, 158);
		lblBusquedaObj_2.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj_2);
		
		JLabel lblBusquedaObj_1 = new JLabel("");
		lblBusquedaObj_1.setBounds(390, 356, 129, 158);
		lblBusquedaObj_1.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj_1);
		
		JLabel lblBusquedaObj = new JLabel("");
		lblBusquedaObj.setBounds(390, 664, 129, 158);
		lblBusquedaObj.setIcon(reSizeImg("images/busqueda.jpeg",129,158));
		contentPane.add(lblBusquedaObj);
		
		///LABEL CARTA ENCRUCIJADA
		JLabel lblEncrucijada = new JLabel("");
		lblEncrucijada.setBounds(1039, 39, 173, 114);
		lblEncrucijada.setIcon(reSizeImg("images/reverso_encrucijada.jpg",173,114));
		contentPane.add(lblEncrucijada);
		
		///LABELS BOTONES Y TEXTO
		JLabel lblAcciones = new JLabel("ACCIONES");
		lblAcciones.setForeground(Color.WHITE);
		lblAcciones.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblAcciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcciones.setBounds(103, 13, 105, 26);
		contentPane.add(lblAcciones);
		
		JButton btnAtacar = new JButton("ATACAR");
		btnAtacar.setBounds(12, 92, 115, 41);
		btnAtacar.setToolTipText("Atacar a un zombie o superviviente");
		contentPane.add(btnAtacar);
		
		JButton btnMoverse = new JButton("MOVERSE");
		btnMoverse.setBounds(183, 196, 115, 41);
		btnMoverse.setToolTipText("Desplazar un superviviente a otra localización");
		contentPane.add(btnMoverse);
		
		JButton btnLanzarDado = new JButton("BUSCAR");
		btnLanzarDado.setBounds(12, 144, 115, 41);
		btnLanzarDado.setToolTipText("Lanzar uno o varios dados");
		contentPane.add(btnLanzarDado);
		
		JButton btnBarricada = new JButton("BARRICADA");
		btnBarricada.setBounds(12, 196, 115, 41);
		btnBarricada.setToolTipText("Construir una barricada protegiendo un espacio para Zombies");
		contentPane.add(btnBarricada);
		
		JTextArea txtrHistorial = new JTextArea();
		txtrHistorial.setText("MENSAJES SERVIDOR AQUI:");
		txtrHistorial.setBounds(12, 497, 286, 362);
		contentPane.add(txtrHistorial);
		
		JButton btnContribuir = new JButton("CONTRIBUIR");
		btnContribuir.setBounds(183, 144, 115, 41);
		btnContribuir.setToolTipText("Aportar un objeto a la crisis");
		contentPane.add(btnContribuir);
		
		JButton btnLimpiarVertedero = new JButton("VERTEDERO");
		btnLimpiarVertedero.setBounds(12, 248, 115, 41);
		btnLimpiarVertedero.setToolTipText("Limpia 3 objetos del vertedero");
		contentPane.add(btnLimpiarVertedero);
		
		JButton btnAtraerZombie = new JButton("ATRAER Z");
		btnAtraerZombie.setBounds(12, 300, 115, 41);
		btnAtraerZombie.setToolTipText("Obtienes más objetos buscando, pero atrae Zombies");
		contentPane.add(btnAtraerZombie);
		
		JButton btnJugarCarta = new JButton("JUGAR CARTA");
		btnJugarCarta.setBounds(183, 92, 115, 41);
		btnJugarCarta.setToolTipText("Usa una carta de tu mano");
		contentPane.add(btnJugarCarta);
		
		JButton btnExilio = new JButton("VOTAR EXILIO");
		btnExilio.setBounds(183, 408, 115, 41);
		btnExilio.setToolTipText("Se inicia una votación entre todos los jugadores para exiliar a un jugador determinado");
		contentPane.add(btnExilio);
		
		JButton btnDarCarta = new JButton("DAR CARTA");
		btnDarCarta.setBounds(183, 356, 115, 41);
		btnDarCarta.setToolTipText("Ofrece una determinada carta a otro jugador");
		contentPane.add(btnDarCarta);
		
		JButton btnPedirCarta = new JButton("PEDIR CARTA");
		btnPedirCarta.setBounds(183, 300, 115, 41);
		btnPedirCarta.setToolTipText("Pide una determinada carta a otro jugador");
		contentPane.add(btnPedirCarta);
		
		JButton btnGastarComida = new JButton("GASTAR COMIDA");
		btnGastarComida.setBounds(183, 248, 115, 41);
		btnGastarComida.setToolTipText("Desecha una ficha de comida de la colonia con el objetivo de incrementar el resultado de un dado");
		contentPane.add(btnGastarComida);
		
		JLabel lblHistorial = new JLabel("HISTORIAL MENSAJES");
		lblHistorial.setForeground(Color.WHITE);
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblHistorial.setBounds(12, 460, 287, 26);
		contentPane.add(lblHistorial);
		
		JLabel lblChat = new JLabel("CHAT");
		lblChat.setForeground(Color.WHITE);
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblChat.setBounds(11, 872, 287, 26);
		contentPane.add(lblChat);
		
		txtChat = new JTextField();
		txtChat.setText("INSERTAR MENSAJES AQUI:");
		txtChat.setBounds(11, 909, 287, 53);
		contentPane.add(txtChat);
		txtChat.setColumns(10);
		
		///LABEL PRINCIPAL DEL TABLERO
		JLabel lblTablero = new JLabel("");
		lblTablero.setBounds(324, 0, 1598, 975);
		//DEJAR EL PATH ACTUAL DEL TABLERO PARA PODER VER SU VISTA PREVIA EN DESIGN
		ImageIcon tableroIcon = new ImageIcon(this.getClass().getResource("/TableroOriginal.png"));
		Image aux = tableroIcon.getImage();
		Image aux2 = aux.getScaledInstance(1598,975, java.awt.Image.SCALE_SMOOTH);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 79, 128, 2);
		contentPane.add(separator_1);
		
		JLabel AccNotRecDice = new JLabel("NO Necesitan Dado");
		AccNotRecDice.setFont(new Font("Arial", Font.PLAIN, 18));
		AccNotRecDice.setForeground(Color.WHITE);
		AccNotRecDice.setBounds(150, 59, 160, 22);
		contentPane.add(AccNotRecDice);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(150, 79, 160, 2);
		contentPane.add(separator_2);
		lblTablero.setIcon(new ImageIcon(aux2));
		contentPane.add(lblTablero);
		if(objetivo == 1) {
			weNeedMoreSamples();
		}
		setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizar pantalla inicialmente
		
	}
	
	//Método para adaptar el tamaño de una imagen a su JLabel
	//Pasamos como parámetro el String con el path, un int con el nuevo ancho y un int con la nueva altura
	//Devuelve un IconImage con el que directamente hacemos -> label.setIcon(reSizeImg(src,width,height));
	public ImageIcon reSizeImg(String src, int width, int height) {
		ImageIcon aux = new ImageIcon(src);
		Image aux2 = aux.getImage();
		Image aux3 = aux2.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(aux3);
	}
	
	//Método para crear imáenes circulares
	//Pasamos como parámetro el String con el path, un int con el nuevo ancho y un int con la nueva altura
	//Devuelve un IconImage con el que directamente hacemos -> label.setIcon(imgCircular(src,width,height));
	public ImageIcon imgCircular(String src, int width, int height) {
		BufferedImage masked;
		BufferedImage master = null;
		try {
			master = ImageIO.read(new File(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		int diameter = Math.min(master.getWidth(), master.getHeight()); 
		BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g2d = mask.createGraphics(); 
		applyQualityRenderingHints(g2d); 
		g2d.fillOval(0, 0, diameter - 1, diameter - 1); 
		g2d.dispose(); 
		masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); 
		g2d = masked.createGraphics(); 
		applyQualityRenderingHints(g2d); 
		int x = (diameter - master.getWidth()) / 2; 
		int y = (diameter - master.getHeight()) / 2; 
		g2d.drawImage(master, x, y, null); 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); 
		g2d.drawImage(mask, 0, 0, null); g2d.dispose(); 
		return new ImageIcon(masked.getScaledInstance(width, height,java.awt.Image.SCALE_SMOOTH));
	}
	
	//CONSTANTES NECESARIAS PARA EL MÉTODO imgCircular
	public static void applyQualityRenderingHints(Graphics2D g2d) {
	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}
	
	//SET UP OBJETIVOS PRINCIPALES
	public void weNeedMoreSamples() {
		fichMoral6.setVisible(true);
		fichRonda6.setVisible(true);
		fichZBiblioteca1.setVisible(true);
		fichZGasolinera1.setVisible(true);
		fichZColegio1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZHospital1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZSuperm1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZComisaria1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
	}
	
}
