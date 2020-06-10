package Gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Server.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Partida.*;

public class FrameTablero extends JFrame {

	private static int objetivo,objetivoSecreto;
	private static Usuario usuario;
	private JPanel contentPane;
	private JTextField txtChat;
	private JTextArea txtrHistorial;
	private JLabel aux;
	private Point p;
	private JLabel lblTablero;
	
	private JLabel fichMoral1,fichMoral2,fichMoral3,fichMoral4,fichMoral5,fichMoral6,fichMoral7,fichMoral8,fichMoral9,fichMoral10;
	private JLabel fichRonda1,fichRonda2,fichRonda3,fichRonda4,fichRonda5,fichRonda6,fichRonda7,fichRonda8,fichRonda9,fichRonda10;

	private JLabel fichZ1ColoniaZona1,fichZ2ColoniaZona1,fichZ3ColoniaZona1,fichZ1ColoniaZona2,fichZ2ColoniaZona2,fichZ3ColoniaZona2,
	fichZ1ColoniaZona3,fichZ2ColoniaZona3,fichZ3ColoniaZona3,fichZ1ColoniaZona4,fichZ2ColoniaZona4,fichZ3ColoniaZona4,
	fichZ1ColoniaZona5,fichZ2ColoniaZona5,fichZ3ColoniaZona5,fichZ1ColoniaZona6,fichZ2ColoniaZona6,fichZ3ColoniaZona6;
	
	private JLabel fichZComisaria1,fichZComisaria2,fichZComisaria3,fichZComisaria4;
	private JLabel fichZSuperm1,fichZSuperm2,fichZSuperm3,fichZSuperm4;
	private JLabel fichZColegio1,fichZColegio2,fichZColegio3,fichZColegio4;
	private JLabel fichZGasolinera1,fichZGasolinera2,fichZGasolinera3;
	private JLabel fichZHospital1,fichZHospital2,fichZHospital3,fichZHospital4;
	private JLabel fichZBiblioteca1,fichZBiblioteca2,fichZBiblioteca3;
	
	private HashMap<Integer,JLabel[]> supMap;
	private HashMap<Integer, JLabel> cartMap;
	private HashMap<Integer,JLabel[]> objMap;
	private List<Integer> dados;
	private HashMap<Integer,JLabel> supIndMap;
	private HashMap<Integer,List<Integer>> supJugadores; //mapa<jug,listaSup>
	private HashMap<Integer,List<Integer>> cartasJugador; //mapa<jug,listaCartas>
	private ObjPrincipal auxObj;
	private Asociaciones aso;
	private InfoJugador infoJug;
	private static int idJug;
	//TODO: CREAR CLASE PRINCIPAL PARA GENERAR TIRADA DADOS
	
	private Point locColonia[] = {new Point(974,340),new Point(1026,340),new Point(1080,340),new Point(1132,340),new Point(1185,340),new Point(1238,340),
									new Point(974,390),new Point(1026,390), new Point(1080,390),new Point(1132,390),new Point(1185,390),new Point(1238,390),
									new Point(974,440),new Point(1026,440), new Point(1080,440),new Point(1132,440),new Point(1185,440),new Point(1238,440),
									new Point(974,490),new Point(1026,490), new Point(1080,490),new Point(1132,490),new Point(1185,490),new Point(1238,490)};
	
	private Point locComisaria[] = {new Point(593,163),new Point(640,164),new Point(686,163)},
			locSupermercado[] = {new Point(593,471),new Point(640,471),new Point(686,471)},
			locColegio[] = {new Point(568,779),new Point(616,779),new Point(662,779),new Point(709,779)},
			locGasolinera[] = {new Point(1547,163),new Point(1594,163)},
			locHospital[] = {new Point(1500,472),new Point(1548,472),new Point(1594,472),new Point(1641,472)},
			locBiblioteca[] = {new Point(1524,779),new Point(1571,779),new Point(1617,779)};
	
	private static Thread hilo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTablero frame = new FrameTablero(objetivo, usuario);
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
	public FrameTablero(int objetivo, Usuario user) {
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setForeground(Color.BLACK);
		setTitle("Dead of Winter\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		
		//OBJETIVO PRINCIPAL PASADO COMO PARAMETRO AL CONSTRUCTOR
		
		this.objetivo = objetivo;
		this.usuario = user;
		usuario.getClientReader().setTablero(this);
		usuario.getClientReader().setSala(null);
		aso = new Asociaciones();
		supMap = aso.getSupMap();
		cartMap = aso.getCartasObjetos();
		supJugadores = new HashMap<>();
		cartasJugador = new HashMap<>();
		dados = new ArrayList<>();
		supIndMap = new HashMap<>();
		
////////////////////////////////////////////////////////////////////////////////////////////////////TODO: MENU
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInfo = new JMenu("Info");
		mnInfo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnInfo.setToolTipText("Informacion relevante sobre la partida");
		menuBar.add(mnInfo);
		
		JMenuItem mntmInfojugador = new JMenuItem("InfoJugador");
		mntmInfojugador.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmInfojugador.setToolTipText("Muestra informacion sobre el jugador y sus cartas");
		mnInfo.add(mntmInfojugador);
		
		JMenuItem mntmInfoTablero = new JMenuItem("InfoTablero");
		mntmInfoTablero.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmInfoTablero.setToolTipText("Muestra informacion sobre el estado actual del tablero");
		mnInfo.add(mntmInfoTablero);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
	
///////////////////////////////////////////////////////////////////////////////////////TODO: LABELS MORAL, RONDAS
		
		///LABELS MORAL
		fichMoral1 = new JLabel("");
		fichMoral1.setBounds(1144, 914, 36, 33);
		fichMoral1.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral1.setVisible(false);
		contentPane.add(fichMoral1);
		
		fichMoral2 = new JLabel("");
		fichMoral2.setBounds(1187, 914, 36, 33);
		fichMoral2.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral2.setVisible(false);
		contentPane.add(fichMoral2);
		
		fichMoral3 = new JLabel("");
		fichMoral3.setBounds(1229, 914, 36, 33);
		fichMoral3.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral3.setVisible(false);
		contentPane.add(fichMoral3);
		
		fichMoral4 = new JLabel("");
		fichMoral4.setBounds(1272, 914, 36, 33);
		fichMoral4.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral4.setVisible(false);
		contentPane.add(fichMoral4);
		
		fichMoral5 = new JLabel("");
		fichMoral5.setBounds(1315, 914, 36, 33);
		fichMoral5.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral5.setVisible(false);
		contentPane.add(fichMoral5);
		
		fichMoral6 = new JLabel("");
		fichMoral6.setBounds(1357, 914, 36, 33);
		fichMoral6.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral6.setVisible(false);
		contentPane.add(fichMoral6);
		
		fichMoral7 = new JLabel("");
		fichMoral7.setBounds(1398, 914, 36, 33);
		fichMoral7.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral7.setVisible(false);
		contentPane.add(fichMoral7);
		
		fichMoral8 = new JLabel("");
		fichMoral8.setBounds(1440, 914, 36, 33);
		fichMoral8.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral8.setVisible(false);
		contentPane.add(fichMoral8);
		
		fichMoral9 = new JLabel("");
		fichMoral9.setBounds(1483, 914, 36, 33);
		fichMoral9.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral9.setVisible(false);
		contentPane.add(fichMoral9);
		
		fichMoral10 = new JLabel("");
		fichMoral10.setBounds(1526, 914, 36, 33);
		fichMoral10.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral10.setVisible(false);
		contentPane.add(fichMoral10);
		
		///LABELS RONDAS
		fichRonda1 = new JLabel("");
		fichRonda1.setBounds(1061, 914, 36, 33);
		fichRonda1.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda1.setVisible(false);
		contentPane.add(fichRonda1);
		
		fichRonda2 = new JLabel("");
		fichRonda2.setBounds(1018, 914, 36, 33);
		fichRonda2.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda2.setVisible(false);
		contentPane.add(fichRonda2);
		
		fichRonda3 = new JLabel("");
		fichRonda3.setBounds(977, 914, 36, 33);
		fichRonda3.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda3.setVisible(false);
		contentPane.add(fichRonda3);
		
		fichRonda4 = new JLabel("");
		fichRonda4.setBounds(934, 914, 36, 33);
		fichRonda4.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda4.setVisible(false);
		contentPane.add(fichRonda4);
		
		fichRonda5 = new JLabel("");
		fichRonda5.setBounds(892, 914, 36, 33);
		fichRonda5.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda5.setVisible(false);
		contentPane.add(fichRonda5);
		
		fichRonda6 = new JLabel("");
		fichRonda6.setBounds(849, 914, 36, 33);
		fichRonda6.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda6.setVisible(false);
		contentPane.add(fichRonda6);
		
		fichRonda7 = new JLabel("");
		fichRonda7.setBounds(808, 914, 36, 33);
		fichRonda7.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda7.setVisible(false);
		contentPane.add(fichRonda7);
		
		fichRonda8 = new JLabel("");
		fichRonda8.setBounds(765, 914, 36, 33);
		fichRonda8.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda8.setVisible(false);
		contentPane.add(fichRonda8);
		
		fichRonda9 = new JLabel("");
		fichRonda9.setBounds(723, 914, 36, 33);
		fichRonda9.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda9.setVisible(false);
		contentPane.add(fichRonda9);
		
		fichRonda10 = new JLabel("");
		fichRonda10.setBounds(680, 914, 36, 33);
		fichRonda10.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda10.setVisible(false);
		contentPane.add(fichRonda10);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS LOCALIZACIONES
		
		//LABELS ZOMBIES
		fichZ1ColoniaZona3 = new JLabel("");
		fichZ1ColoniaZona3.setBounds(872, 592, 36, 34);
		fichZ1ColoniaZona3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona3.setVisible(false);
		contentPane.add(fichZ1ColoniaZona3);
		
		fichZ2ColoniaZona5 = new JLabel("");
		fichZ2ColoniaZona5.setBounds(1303, 460, 36, 34);
		fichZ2ColoniaZona5.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona5.setVisible(false);
		contentPane.add(fichZ2ColoniaZona5);
		
		fichZ3ColoniaZona5 = new JLabel("");
		fichZ3ColoniaZona5.setBounds(1304, 501, 36, 34);
		fichZ3ColoniaZona5.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona5.setVisible(false);
		contentPane.add(fichZ3ColoniaZona5);
		
		fichZ2ColoniaZona1 = new JLabel("");
		fichZ2ColoniaZona1.setBounds(911, 347, 36, 34);
		fichZ2ColoniaZona1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona1.setVisible(false);
		contentPane.add(fichZ2ColoniaZona1);
		
		fichZ3ColoniaZona3 = new JLabel("");
		fichZ3ColoniaZona3.setBounds(910, 612, 36, 34);
		fichZ3ColoniaZona3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona3.setVisible(false);
		contentPane.add(fichZ3ColoniaZona3);
		
		fichZ3ColoniaZona6 = new JLabel("");
		fichZ3ColoniaZona6.setBounds(1304, 388, 36, 34);
		fichZ3ColoniaZona6.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona6.setVisible(false);
		contentPane.add(fichZ3ColoniaZona6);
		
		fichZ2ColoniaZona3 = new JLabel("");
		fichZ2ColoniaZona3.setBounds(910, 571, 36, 34);
		fichZ2ColoniaZona3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona3.setVisible(false);
		contentPane.add(fichZ2ColoniaZona3);
		
		fichZ1ColoniaZona6 = new JLabel("");
		fichZ1ColoniaZona6.setBounds(1341, 368, 36, 34);
		fichZ1ColoniaZona6.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona6.setVisible(false);
		contentPane.add(fichZ1ColoniaZona6);
		
		fichZ1ColoniaZona5 = new JLabel("");
		fichZ1ColoniaZona5.setBounds(1341, 481, 36, 34);
		fichZ1ColoniaZona5.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona5.setVisible(false);
		contentPane.add(fichZ1ColoniaZona5);
		
		fichZ3ColoniaZona4 = new JLabel("");
		fichZ3ColoniaZona4.setBounds(1304, 612, 36, 34);
		fichZ3ColoniaZona4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona4.setVisible(false);
		contentPane.add(fichZ3ColoniaZona4);
		
		fichZ1ColoniaZona4 = new JLabel("");
		fichZ1ColoniaZona4.setBounds(1341, 592, 36, 34);
		fichZ1ColoniaZona4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona4.setVisible(false);
		contentPane.add(fichZ1ColoniaZona4);
		
		fichZ3ColoniaZona2 = new JLabel("");
		fichZ3ColoniaZona2.setBounds(910, 501, 36, 34);
		fichZ3ColoniaZona2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona2.setVisible(false);
		contentPane.add(fichZ3ColoniaZona2);
		
		fichZ1ColoniaZona1 = new JLabel("");
		fichZ1ColoniaZona1.setBounds(872, 367, 36, 34);
		fichZ1ColoniaZona1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona1.setVisible(false);
		contentPane.add(fichZ1ColoniaZona1);
		
		fichZ3ColoniaZona1 = new JLabel("");
		fichZ3ColoniaZona1.setBounds(910, 388, 36, 34);
		fichZ3ColoniaZona1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ3ColoniaZona1.setVisible(false);
		contentPane.add(fichZ3ColoniaZona1);
		
		fichZ1ColoniaZona2 = new JLabel("");
		fichZ1ColoniaZona2.setBounds(872, 480, 36, 34);
		fichZ1ColoniaZona2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ1ColoniaZona2.setVisible(false);
		contentPane.add(fichZ1ColoniaZona2);
		
		fichZ2ColoniaZona4 = new JLabel("");
		fichZ2ColoniaZona4.setBounds(1303, 571, 36, 34);
		fichZ2ColoniaZona4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona4.setVisible(false);
		contentPane.add(fichZ2ColoniaZona4);
		
		fichZ2ColoniaZona6 = new JLabel("");
		fichZ2ColoniaZona6.setBounds(1303, 347, 36, 34);
		fichZ2ColoniaZona6.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona6.setVisible(false);
		contentPane.add(fichZ2ColoniaZona6);
		
		fichZ2ColoniaZona2 = new JLabel("");
		fichZ2ColoniaZona2.setBounds(911, 460, 36, 34);
		fichZ2ColoniaZona2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZ2ColoniaZona2.setVisible(false);
		contentPane.add(fichZ2ColoniaZona2);
		
		///LABELS AUXILIARES
		JLabel fichAlimento3 = new JLabel("");
		fichAlimento3.setBounds(1180, 559, 36, 33);
		fichAlimento3.setIcon(imgCircular("images/Fichas/fichaAlimento.png",36,33));
		contentPane.add(fichAlimento3);
		
		JLabel fichAlimento2 = new JLabel("");
		fichAlimento2.setBounds(1227, 596, 36, 33);
		fichAlimento2.setIcon(imgCircular("images/Fichas/fichaAlimento.png",36,33));
		contentPane.add(fichAlimento2);
		
		JLabel fichAlimento1 = new JLabel("");
		fichAlimento1.setBounds(1180, 607, 36, 33);
		fichAlimento1.setIcon(imgCircular("images/Fichas/fichaAlimento.png",36,33));
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
		
		//LABELS SUPERMERCADO
		
		fichZSuperm4 = new JLabel("");
		fichZSuperm4.setBounds(712, 530, 36, 34);
		fichZSuperm4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZSuperm4.setVisible(false);
		contentPane.add(fichZSuperm4);
		
		fichZSuperm1 = new JLabel("");
		fichZSuperm1.setBounds(568, 530, 36, 34);
		fichZSuperm1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZSuperm1.setVisible(false);
		contentPane.add(fichZSuperm1);
		
		fichZSuperm2 = new JLabel("");
		fichZSuperm2.setBounds(616, 529, 36, 34);
		fichZSuperm2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZSuperm2.setVisible(false);
		contentPane.add(fichZSuperm2);
		
		fichZSuperm3 = new JLabel("");
		fichZSuperm3.setBounds(664, 530, 36, 34);
		fichZSuperm3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZSuperm3.setVisible(false);
		contentPane.add(fichZSuperm3);	
		
		//LABELS COLEGIO
		
		fichZColegio1 = new JLabel("");
		fichZColegio1.setBounds(568, 836, 36, 34);
		fichZColegio1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZColegio1.setVisible(false);
		contentPane.add(fichZColegio1);
		
		fichZColegio3 = new JLabel("");
		fichZColegio3.setBounds(664, 836, 36, 34);
		fichZColegio3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZColegio3.setVisible(false);
		contentPane.add(fichZColegio3);
		
		fichZColegio4 = new JLabel("");
		fichZColegio4.setBounds(712, 836, 36, 34);
		fichZColegio4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZColegio4.setVisible(false);
		contentPane.add(fichZColegio4);
		
		fichZColegio2 = new JLabel("");
		fichZColegio2.setBounds(616, 835, 36, 34);
		fichZColegio2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZColegio2.setVisible(false);
		contentPane.add(fichZColegio2);
		
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
		fichZHospital3.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZHospital3.setVisible(false);
		contentPane.add(fichZHospital3);
		
		fichZHospital1 = new JLabel("");
		fichZHospital1.setBounds(1500, 530, 36, 34);
		fichZHospital1.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZHospital1.setVisible(false);
		contentPane.add(fichZHospital1);
		
		fichZHospital2 = new JLabel("");
		fichZHospital2.setBounds(1548, 529, 36, 34);
		fichZHospital2.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZHospital2.setVisible(false);
		contentPane.add(fichZHospital2);
		
		fichZHospital4 = new JLabel("");
		fichZHospital4.setBounds(1644, 530, 36, 34);
		fichZHospital4.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
		fichZHospital4.setVisible(false);
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
		
//////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS AUXILIARES		
		
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
		
		///////////////////////////////////////////////////TODO: LABELS BOTONES Y TEXTO
		JLabel lblAcciones = new JLabel("ACCIONES");
		lblAcciones.setForeground(Color.WHITE);
		lblAcciones.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblAcciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcciones.setBounds(103, 13, 105, 26);
		contentPane.add(lblAcciones);
		
		JButton btnAtacar = new JButton("ATACAR");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAtacar.setBounds(12, 92, 115, 41);
		btnAtacar.setToolTipText("Atacar a un zombie o superviviente");
		contentPane.add(btnAtacar);
		
		JButton btnMoverse = new JButton("MOVERSE");
		btnMoverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});
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
		
		JButton btnInfoJugador = new JButton("INFO JUGADOR");
		btnInfoJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(infoJug != null) {
					infoJug.dispose();
				}
				infoJug = new InfoJugador(supJugadores.get(idJug), cartasJugador.get(idJug),objetivoSecreto,aso,dados);
				infoJug.setVisible(true);
			}
		});
		btnInfoJugador.setToolTipText("Muestra informacion sobre el jugador y sus cartas");
		btnInfoJugador.setBounds(840, 71, 129, 41);
		contentPane.add(btnInfoJugador);
		
		JButton btnInfoTablero = new JButton("INFO TABLERO");
//		btnInfoTablero.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if(infoTab != null) {
//					infoTab.dispose();
//				}
//				infoTab = new InfoTablero();
//				infoTab.setVisible(true);
//			}
//		});
		btnInfoTablero.setToolTipText("Muestra informacion sobre el estado actual del tablero");
		btnInfoTablero.setBounds(1270, 71, 129, 41);
		contentPane.add(btnInfoTablero);
		
		JButton ObjetivoPrin = new JButton("");
		ObjetivoPrin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(auxObj != null) {
					auxObj.dispose();
				}
				auxObj = new ObjPrincipal(objetivo);
				auxObj.setVisible(true);
				
			}
		});
		ObjetivoPrin.setBounds(911, 699, 123, 158);
		contentPane.add(ObjetivoPrin);
		
		JButton btnSendChat = new JButton(">");
		btnSendChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mandarMensaje();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnSendChat.setToolTipText("Envia un mensaje al chat");
		btnSendChat.setBounds(262, 910, 41, 33);
		contentPane.add(btnSendChat);
		
		txtChat = new JTextField();
		txtChat.addKeyListener(new KeyAdapter() {
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
		txtChat.setBounds(11, 909, 241, 33);
		contentPane.add(txtChat);
		txtChat.setColumns(10);
		
		///LABEL PRINCIPAL DEL TABLERO
		lblTablero = new JLabel("");
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
		
		JLabel AccReqDice = new JLabel("Necesitan Dado\r\n");
		AccReqDice.setForeground(Color.WHITE);
		AccReqDice.setFont(new Font("Arial", Font.PLAIN, 18));
		AccReqDice.setBounds(12, 59, 128, 22);
		contentPane.add(AccReqDice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 37, 286, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 497, 286, 362);
		contentPane.add(scrollPane);
		
		txtrHistorial = new JTextArea();
		txtrHistorial.setFont(new Font("Consolas", Font.PLAIN, 15));
		scrollPane.setViewportView(txtrHistorial);
		txtrHistorial.setText("");
		txtrHistorial.setEditable(false);
		
		JLabel lblChat = new JLabel("CHAT\r\n");
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setForeground(Color.WHITE);
		lblChat.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblChat.setBounds(103, 457, 105, 26);
		contentPane.add(lblChat);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 481, 286, 2);
		contentPane.add(separator_3);
		
		ImageIcon ima2 = new ImageIcon(this.getClass().getResource("/Objetivos-Principales/NecesitamosEjemplares.jpg"));
		Image img2 = ima2.getImage().getScaledInstance(121, 158, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon ima3 = new ImageIcon(this.getClass().getResource("/Objetivos-Principales/PartidaDeSaqueo.jpg"));
		Image img3 = ima3.getImage().getScaledInstance(121, 158, java.awt.Image.SCALE_SMOOTH);
		

		
		////////////////////////////////////////////////////TODO: OBJETIVOS PRINCIPALES
		switch(objetivo){
			case 1: weNeedMoreSamples(); ObjetivoPrin.setIcon(new ImageIcon(img2));
			break;
			case 2: raidingParty(); ObjetivoPrin.setIcon(new ImageIcon(img3));
			break;
		}

//		usuario.getClientReader().setTablero(this);
//		usuario.getClientReader().setSala(null);
		hilo = new Thread(usuario.getClientReader());
		hilo.start();
		
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
	private void weNeedMoreSamples() {
		fichMoral6.setVisible(true);
		fichRonda6.setVisible(true);
		fichZBiblioteca1.setVisible(true);
		fichZGasolinera1.setVisible(true);
		fichZColegio1.setVisible(true);
		fichZHospital1.setVisible(true);
		fichZSuperm1.setVisible(true);
		fichZComisaria1.setVisible(true);
	}
	
	private void raidingParty() {
		fichMoral5.setVisible(true);
		fichRonda6.setVisible(true);
		fichZBiblioteca1.setVisible(true);
		fichZBiblioteca2.setVisible(true);
		fichZGasolinera1.setVisible(true);
		fichZGasolinera2.setVisible(true);
		fichZColegio1.setVisible(true);
		fichZColegio2.setVisible(true);
		fichZHospital1.setVisible(true);
		fichZHospital2.setVisible(true);
		fichZSuperm1.setVisible(true);
		fichZSuperm2.setVisible(true);
		fichZComisaria1.setVisible(true);
		fichZComisaria2.setVisible(true);
		fichZ1ColoniaZona1.setVisible(true);
		fichZ1ColoniaZona2.setVisible(true);
	}
	
	public void actualizaChat(String mensaje) {
		txtrHistorial.setText(txtrHistorial.getText().trim() + "\n" + mensaje);
	}
	
	private void mandarMensaje() throws IOException {
		String msg = txtChat.getText().trim();
		if(!msg.equals("")) {
			usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + 1 + "|msgsala|" + msg);
			txtChat.setText("");
		}
	}
	
	//AÑADIMOS SUPERVIVIENTES NUEVOS AL TABLERO (COLONIA)
	//ARG0 ID SUPERVIVIENTE, ARG1 POSICION VALIDA EN LA COLONIA
	public void anyadirSuperviviente(int id,int posI) {
		p = locColonia[posI];
		aux = supMap.get(id)[0];
		aux.setBounds(p.x,p.y, 36, 34);
		contentPane.add(aux);
		contentPane.setComponentZOrder(aux, contentPane.getComponentZOrder(lblTablero)-1);
	}
	
	//AÑADIR SUUPERVIVIENTES INDEFENSOS EN POSICIONES FINALES DE LA COLONIA
	//ARG0 ID SUPIND, ARG1 POSCION VALIDA COLONIA EMPEZANDO POR FINAL
	public void anyadirSupIndef(int id,int pos) { //deberiamos de pasarle algun id que lo identifique nSupInd, podriamos crear otro diccionario
		aux = new JLabel("");
		p = locColonia[pos];
		aux.setBounds(p.x,p.y, 36, 34);
		aux.setIcon(imgCircular("images/fichaSupIndefenso.png",36,34));
		contentPane.add(aux);
		supIndMap.put(id,aux);
	}
	
	//PASAMOS ID DEL SUPERVIVIENTE A MOVER, ID DE LA LOCALIZACION A MOVER
	public void moverSuperviviente(int id, int loc, int pos) {
		aux = supMap.get(id)[0];
		switch(loc) {
			case 0 : p = locComisaria[pos];
			break;
			case 1 : p = locSupermercado[pos];
			break;
			case 2 : p = locColegio[pos];
			break;
			case 3 : p = locGasolinera[pos];
			break;
			case 4 : p = locHospital[pos];
			break;
			case 5 : p = locBiblioteca[pos];
			break;
			case 6 : p = locColonia[pos];
			break;
		}
		aux.setLocation(p);
	}
	
	public static void setObjetivoSecreto(int id) {
		objetivoSecreto = id;
	}
	
	public void addSupJug(int idJug, int idSup) {
		if(supJugadores.get(idJug) == null) {
			List<Integer> listaVacia = new ArrayList<>();
			supJugadores.put(idJug, listaVacia);
		}
		supJugadores.get(idJug).add(idSup);
	}
	
	public void addCartaJug(int idJug,int idCarta) {
		if(cartasJugador.get(idJug) == null) {
			List<Integer> listaVacia = new ArrayList<>();
			cartasJugador.put(idJug, listaVacia);
		}
		cartasJugador.get(idJug).add(idCarta);
	}
	
	public static void setId(int id) {
		idJug = id;
	}
}


