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

	private static int objetivo,objetivoSecreto,idJug,idCrisis;
	private static Usuario usuario;
	private JPanel contentPane;
	private JTextField txtChat;
	private JTextArea txtrHistorial;
	private Point p;
	private JLabel lblTablero,fichMoral,fichRonda,aux;

	private HashMap<Integer,JLabel[]> supMap,objMap;
	private static List<String> nombresJugadores;
	private HashMap<Integer, JLabel> cartMap;
	private List<Integer> dados;
	private static List<Integer> nCartasJugadores;
	private HashMap<Integer,JLabel> supIndMap;
	private HashMap<Integer,List<Integer>> supJugadores; //mapa<jug,listaSup>
	private List<Integer> cartasJugador; //mapa<jug,listaCartas>
	private List<List<JLabel>> labelsZombies;
	private List<Point[]> locZombies;
	private ObjPrincipal auxObj;
	private Crisis crisis;
	private Asociaciones aso;
	private InfoJugador infoJug;
	private FrameMoverse frameMoverse;
	private InfoTablero infoTab;


	private static int[] heridas = new int[2];
	//TODO: CREAR CLASE PRINCIPAL PARA GENERAR TIRADA DADOS

	
	private Point locRonda[] = {new Point(1061,914),new Point(1018,914),new Point(977,914),new Point(934,914),new Point(892,914),new Point(849,914),
								new Point(808,914),new Point(765,914),new Point(723,914),new Point(680,914)};
	
	private Point locMoral[] = {new Point(1144,914),new Point(1187,914),new Point(1229,914),new Point(1272,914),new Point(1315,914),new Point(1357,914),
								new Point(1398,914),new Point(1440,914),new Point(1483,914),new Point(1526,914)};
	
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
		
	private Point locZComisaria[] = {new Point(568,223),new Point(616,223),new Point(664,222),new Point(712,222)},
			locZSuperm[] = {new Point(568,530),new Point(616,530),new Point(664,530),new Point(712,530)},
			locZColegio[] = {new Point(568,836),new Point(616,836),new Point(664,836),new Point(712,836)},
			locZGasolinera[] = {new Point(1525,223),new Point(1573,223),new Point(1621,223)},
			locZHospital[] = {new Point(1500,530),new Point(1548,530),new Point(1596,530),new Point(1644,530)},
			locZBiblioteca[] = {new Point(1525,836),new Point(1573,836),new Point(1621,836)};
	
	private Point locZColoniaZ1[] = {new Point(872,367),new Point(911,347),new Point(910,388)},
			locZColoniaZ2[] = {new Point(872,480),new Point(911,460),new Point(910,501)},
			locZColoniaZ3[] = {new Point(872,592),new Point(911,571),new Point(910,612)},
			locZColoniaZ4[] = {new Point(1341,592),new Point(1303,571),new Point(1304,612)},
			locZColoniaZ5[] = {new Point(1341,481),new Point(1303,460),new Point(1304,501)},
			locZColoniaZ6[] = {new Point(1341,368),new Point(1303,347),new Point(1304,388)};
	
	private static Thread hilo;
	private JButton btnFichasComida;
	private JButton btnVertedero;
	private JButton btnContribucionesCrisis;

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
	 * @throws InterruptedException 
	 */
	public FrameTablero(int objetivo, Usuario user){
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
		cartasJugador = new ArrayList<>();
		supIndMap = new HashMap<>();
		nombresJugadores = new ArrayList<>();
		
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
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS LOCALIZACIONES
		
		//LABELS ZOMBIES
		locZombies = new ArrayList<>();
		locZombies.add(locZComisaria);locZombies.add(locZSuperm);locZombies.add(locZColegio);locZombies.add(locZGasolinera);locZombies.add(locZHospital);locZombies.add(locZBiblioteca);
		locZombies.add(locZColoniaZ1);locZombies.add(locZColoniaZ2);locZombies.add(locZColoniaZ3);locZombies.add(locZColoniaZ4);locZombies.add(locZColoniaZ5);locZombies.add(locZColoniaZ6);
		
		labelsZombies = new ArrayList<>();
		List<JLabel> auxList;
		for(int i=0;i<locZombies.size();i++) {
			auxList = new ArrayList<>();
			for(int j=0;j<locZombies.get(i).length;j++) {
				aux = new JLabel(""); 
				aux.setBounds(locZombies.get(i)[j].x, locZombies.get(i)[j].y, 36, 34);
				aux.setIcon(imgCircular("images/fichaZombieReal.png",36,34));
				aux.setVisible(false);
				contentPane.add(aux);
				auxList.add(aux);
			}
			labelsZombies.add(auxList);
		}
		
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
				frameMoverse = new FrameMoverse(supJugadores.get(idJug), aso);
				frameMoverse.setVisible(true);
			}
		});
		btnMoverse.setBounds(183, 196, 115, 41);
		btnMoverse.setToolTipText("Desplazar un superviviente a otra localización");
		contentPane.add(btnMoverse);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(12, 144, 115, 41);
		btnBuscar.setToolTipText("Lanzar uno o varios dados");
		contentPane.add(btnBuscar);
		
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
		
		JButton btnFinalizarTurno = new JButton("FINALIZAR TURNO");
		btnFinalizarTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFinalizarTurno.setBackground(new Color(67,73,114));
		btnFinalizarTurno.setForeground(Color.WHITE);
		btnFinalizarTurno.setBounds(12, 408, 286, 41);
		btnFinalizarTurno.setToolTipText("Finaliza el turno del jugador actual e inicia el turno de otro jugador o cambia de ronda");
		contentPane.add(btnFinalizarTurno);
		
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
				System.out.println("DADOS "+dados.toString());
				infoJug = new InfoJugador(supJugadores.get(idJug), cartasJugador,objetivoSecreto,aso,dados);
				infoJug.setVisible(true);
			}
		});
		btnInfoJugador.setToolTipText("Muestra informacion sobre el jugador y sus cartas");
		btnInfoJugador.setBounds(840, 71, 129, 41);
		contentPane.add(btnInfoJugador);
		
		JButton btnInfoTablero = new JButton("INFO TABLERO");
		btnInfoTablero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(infoTab != null) {
					infoTab.dispose();
				}
				infoTab = new InfoTablero(nombresJugadores,supJugadores,aso,nCartasJugadores);
				infoTab.setVisible(true);
			}
		});
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
		
		btnFichasComida = new JButton("");
		btnFichasComida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Numero de fichas de comida en la Colonia: " + 5); //TODO: CAMBIAR
			}
		});
		btnFichasComida.setBounds(1165, 538, 107, 113);
		btnFichasComida.setOpaque(false);
		btnFichasComida.setContentAreaFilled(false);
		contentPane.add(btnFichasComida);
		contentPane.setComponentZOrder(btnFichasComida, contentPane.getComponentZOrder(lblTablero)-1);
		
		btnVertedero = new JButton("");
		btnVertedero.setBounds(973, 539, 173, 112);
		btnVertedero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Numero de cartas en vertedero: " + 3); //TODO: CAMBIAR
			}
		});
		btnVertedero.setOpaque(false);
		btnVertedero.setContentAreaFilled(false);
		contentPane.add(btnVertedero);
		contentPane.setComponentZOrder(btnVertedero, contentPane.getComponentZOrder(lblTablero)-1);
		
		btnContribucionesCrisis = new JButton("");
		btnContribucionesCrisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Contribuciones de FranBono al proyecto: " + "-500"); //TODO: CAMBIAR
			}
		});
		btnContribucionesCrisis.setBounds(1210, 699, 129, 158);
		btnContribucionesCrisis.setOpaque(false);
		btnContribucionesCrisis.setContentAreaFilled(false);
		contentPane.add(btnContribucionesCrisis);
		contentPane.setComponentZOrder(btnContribucionesCrisis, contentPane.getComponentZOrder(lblTablero)-1);
		
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
		
		try {
			Thread.sleep(100); //PARA QUE LE DE TIEMPO A PILLAR EL idCrisis
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//HAY QUE PONERLO DESPUES DE INICIAR EL HILO
		JButton btnCartaCrisis = new JButton("");
		btnCartaCrisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(crisis != null) {
					crisis.dispose();
				}
				System.out.println("IDCRISIS: " +idCrisis);

				crisis = new Crisis(idCrisis, aso);
				crisis.setVisible(true);
			}
		});
		btnCartaCrisis.setBounds(1062, 699, 123, 158);
		System.out.println("IDCRISIS: " +idCrisis);
		ImageIcon ima = (ImageIcon) aso.getCartasCrisis().get(idCrisis).getIcon();
		Image img = ima.getImage().getScaledInstance(123, 158, java.awt.Image.SCALE_SMOOTH);
		btnCartaCrisis.setIcon(new ImageIcon(img));
		contentPane.add(btnCartaCrisis);
		contentPane.setComponentZOrder(btnCartaCrisis, contentPane.getComponentZOrder(lblTablero)-1);
		
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
	
	//TODO: SET UP OBJETIVOS PRINCIPALES
	private void weNeedMoreSamples() {
		p = locMoral[5];//Moral 6
		fichMoral = new JLabel("");
		fichMoral.setBounds(p.x, p.y, 36, 33);
		fichMoral.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral.setVisible(true);
		contentPane.add(fichMoral);
		contentPane.setComponentZOrder(fichMoral, contentPane.getComponentZOrder(lblTablero)-1);
		
		p = locRonda[5];//Ronda 6
		fichRonda = new JLabel("");
		fichRonda.setBounds(p.x, p.y, 36, 33);
		fichRonda.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda.setVisible(true);
		contentPane.add(fichRonda);
		contentPane.setComponentZOrder(fichRonda, contentPane.getComponentZOrder(lblTablero)-1);
		
		for(int i=0;i<6;i++) {
			labelsZombies.get(i).get(0).setVisible(true);
		}
	}
	
	private void raidingParty() {
		p = locMoral[4];//Moral 5
		fichMoral = new JLabel("");
		fichMoral.setBounds(p.x, p.y, 36, 33);
		fichMoral.setIcon(imgCircular("images/Fichas/MoralDef.png",36,33));
		fichMoral.setVisible(true);
		contentPane.add(fichMoral);
		contentPane.setComponentZOrder(fichMoral, contentPane.getComponentZOrder(lblTablero)-1);
		
		p = locRonda[5];//Ronda 6
		fichRonda = new JLabel("");
		fichRonda.setBounds(p.x, p.y, 36, 33);
		fichRonda.setIcon(imgCircular("images/Fichas/RondaDef.png",36,33));
		fichRonda.setVisible(true);
		contentPane.add(fichRonda);
		contentPane.setComponentZOrder(fichRonda, contentPane.getComponentZOrder(lblTablero)-1);
		
		for(int i=0;i<6;i++) {
			labelsZombies.get(i).get(0).setVisible(true);
			labelsZombies.get(i).get(1).setVisible(true);
		}
		labelsZombies.get(6).get(0).setVisible(true);
		labelsZombies.get(7).get(0).setVisible(true);
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
	
	//AÑADIR SUPERVIVIENTES INDEFENSOS EN POSICIONES FINALES DE LA COLONIA
	//ARG0 ID SUPIND, ARG1 POSCION VALIDA COLONIA EMPEZANDO POR FINAL
	public void anyadirSupIndef(int id,int pos) { //deberiamos de pasarle algun id que lo identifique nSupInd, podriamos crear otro diccionario
		aux = new JLabel("");
		p = locColonia[pos];
		aux.setBounds(p.x,p.y, 36, 34);
		aux.setIcon(imgCircular("images/fichaSupIndefenso.png",36,34));
		contentPane.add(aux);
		contentPane.setComponentZOrder(aux, contentPane.getComponentZOrder(lblTablero)-1);
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
		List<Integer> aux = new ArrayList<>();
		if(supJugadores.get(idJug) == null) {
			List<Integer> listaVacia = new ArrayList<>();
			supJugadores.put(idJug, listaVacia);
		}
		aux = supJugadores.get(idJug);
		aux.add(idSup);
		supJugadores.put(idJug, aux);
	}
	
	public void addCartaJug(int idCarta) {
		if(cartasJugador == null) {
			cartasJugador = new ArrayList<>();
		}
		cartasJugador.add(idCarta);

	}
	
	public static void setId(int id) {
		idJug = id;
	}
	
	public static void initListCartas(int nJug) {
		nCartasJugadores = new ArrayList<>();
		for(int i=0;i<nJug;i++) {
			nCartasJugadores.add(5);
		}
	}
	
	public static void updateCartas(int idJug, int nCart) {
		nCartasJugadores.set(idJug, nCartasJugadores.get(idJug)+nCart);
	}
	
	public void tiradaDados(int res) {
		if(dados == null) {
			dados = new ArrayList<>();
		}
		dados.add(res);
	}
	
	public void setRonda(int ronda) {
		fichRonda.setLocation(locRonda[ronda-1]);
	}
	
	public void setMoral(int moral) {
		fichMoral.setLocation(locMoral[moral-1]);
	}
	
	public static void setCrisis(int crisis) {
		idCrisis = crisis;
	}
	
	public static void setNombreJugadores(String n) {
		nombresJugadores.add(n);
	}
	
	public void addZombie(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setVisible(true);
	}
	
	public void deleteZombie(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setVisible(false);
	}

	public void addBarricada(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setVisible(true);
		labelsZombies.get(loc).get(pos).setIcon(imgCircular("images/Fichas/barricada.png",36,34));
	}
	
	public void deleteBarricada(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setVisible(false);
		labelsZombies.get(loc).get(pos).setIcon(imgCircular("images/fichaZombieReal.png",36,34));
	}
	
	public static void pedirHeridas(int id) throws IOException{
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + 1 + "|heridas|" + id);
	}
	
	public static void setHeridas(int[] h) {
		heridas = h.clone();
	}
	
	public static int[] getHeridas() {
		return heridas;
	}
}


