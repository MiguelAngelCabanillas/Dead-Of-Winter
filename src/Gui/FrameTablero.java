package Gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

public class FrameTablero extends JFrame {
	
	private FrameCartasEncontradas auxCartasEncontradas;

	private static int objetivo,objetivoSecreto,idJug,idCrisis;
	private static Usuario usuario;
	private JPanel contentPane;
	private JTextField txtChat;
	private JTextArea txtrHistorial;
	private Point p; //punto auxiliar
	private JLabel lblTablero,fichMoral,fichRonda,aux;
	private static boolean turno; //ESPECIFICA SI EL JUGADOR POSEE EL TURNO
	private int vertedero,nFichComida,nFichHambre;
	private int crisisRes;
	private static int nCartasABuscar;

	private HashMap<Integer,JLabel[]> supMap;
	private static List<String> nombresJugadores;
	private static List<Integer> dados; //puntuación de los dado del jugador
	private List<Integer> aportacionesCrisis; //numero de cartas que ha aportado cada jugador a la crisis
	private static List<Integer> nCartasJugadores; //numero de cartas que posee cada jugador
	private HashMap<Integer,JLabel> supIndMap; //mapa supervivientes indefensos
	private static HashMap<Integer,List<Integer>> supJugadores; //mapa<jug,listaSup>
	private HashMap<Integer,JLabel> labelsSup; //mapa que relaciona los id de los supervivientes que se encuentran en partida con su respectiva ficha circular
	private HashMap<Integer,List<Integer>> objetosEquipadosSup;
	private List<Integer> cartasJugador; //mapa<jug,listaCartas>
	private List<List<JLabel>> labelsZombies; //fichas circulares zombies/barricadas
	private List<List<JLabel>> labelsFichRuido; //fichas circulares fichas de ruido
	private List<Point[]> locZombies; //localizaciones zombies/barricadas
	private List<Point[]> locFichRuido; //localizaciones fichas de ruido
	private List<Integer> cartasResolucionCrisis; //cartas aportadas a la crisis cuando termina la ronda
	private List<JLabel> labelsSupInd;
	private static List<Integer> cartEncont;
	private HashMap<Integer,Integer> heridasNormales;
	private HashMap<Integer,Integer> heridasCong;
	private ObjPrincipal auxObj;
	private Crisis crisis;
	private static Asociaciones aso;
	private InfoJugador infoJug;
	private FrameSeleccionSuperviviente frameSeleccionar;
	private InfoTablero infoTab;
	private FrameAportacionesCrisis aportCrisis;
	private FrameDados frameDados;
	private FrameTuTurno frameTuTurno;
	private FrameFinPartida frameFinPartida;
	private FrameTiradaRiesgo tirRiesgo;
	private FrameCartasAportadas frameCartasAportadas;
	private FrameSupervivienteNuevo frameSupNuevo;
	private static FrameCarta fCarta;
	
	private JButton btnAtacar,btnMoverse,btnBuscar,btnBarricada,btnContribuir,btnLimpiarVertedero,btnAtraerZombie,btnFinalizarTurno,btnDarCarta,btnPedirCarta,
					btnGastarComida,btnFichasComida,btnVertedero,btnContribucionesCrisis,btnInfoJugador,btnInfoTablero,ObjetivoPrin,btnSendChat,btnCartaCrisis;
	
	private Point locRonda[] = {new Point(1061,915),new Point(1018,915),new Point(977,915),new Point(934,915),new Point(892,915),new Point(849,915),
								new Point(808,915),new Point(765,915),new Point(723,915),new Point(680,915)};
	
	private Point locMoral[] = {new Point(1144,915),new Point(1187,915),new Point(1229,915),new Point(1272,915),new Point(1315,915),new Point(1357,915),
								new Point(1398,915),new Point(1440,915),new Point(1483,915),new Point(1526,915)};
	
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
		
	private Point locZComisaria[] = {new Point(568,222),new Point(616,222),new Point(663,222),new Point(711,222)},
			locZSuperm[] = {new Point(568,529),new Point(616,529),new Point(664,529),new Point(712,529)},
			locZColegio[] = {new Point(568,837),new Point(616,837),new Point(664,837),new Point(711,837)},
			locZGasolinera[] = {new Point(1524,221),new Point(1572,222),new Point(1620,221)},
			locZHospital[] = {new Point(1500,529),new Point(1548,529),new Point(1596,529),new Point(1644,529)},
			locZBiblioteca[] = {new Point(1524,837),new Point(1572,837),new Point(1620,837)};
	
	private Point locZColoniaZ1[] = {new Point(872,367),new Point(911,347),new Point(910,388)},
			locZColoniaZ2[] = {new Point(872,480),new Point(911,460),new Point(911,501)},
			locZColoniaZ3[] = {new Point(872,592),new Point(911,571),new Point(910,612)},
			locZColoniaZ4[] = {new Point(1341,592),new Point(1303,571),new Point(1304,611)},
			locZColoniaZ5[] = {new Point(1341,481),new Point(1303,460),new Point(1304,500)},
			locZColoniaZ6[] = {new Point(1341,368),new Point(1303,347),new Point(1304,387)};
	
	private Point locRComisaria[] = {new Point(390,225),new Point(422,225),new Point(454,225),new Point(486,225)},
			locRSuperm[] = {new Point(390,532),new Point(422,532),new Point(454,532),new Point(486,532)},
			locRColegio[] = {new Point(390,840),new Point(422,840),new Point(454,840),new Point(486,840)},
			locRGasolinera[] = {new Point(1728,224),new Point(1760,224),new Point(1792,224),new Point(1824,224)},
			locRHospital[] = {new Point(1728,532),new Point(1760,532),new Point(1792,532),new Point(1824,532)},
			locRBiblioteca[] = {new Point(1728,840),new Point(1760,840),new Point(1792,840),new Point(1824,840)};
	
	private static Thread hilo;
	private JMenu mnAyuda;
	private JMenuItem mntmManual;
	private JMenuItem mntmResumen;

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
	public FrameTablero(int objetivo, Usuario user) throws InterruptedException{
		BarraProgreso progressBar = new BarraProgreso();
		progressBar.setValor(0);////////////////////////
		progressBar.setVisible(true);
		progressBar.setString("Inicializando colonia");
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setForeground(Color.BLACK);
		setTitle("Dead of Winter\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE | JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1940, 1048);
		
		//inicializarTablero("C:/Users/xdmrg/Desktop/DoW/Dead-Of-Winter/default.dow");
		
	
		//OBJETIVO PRINCIPAL PASADO COMO PARAMETRO AL CONSTRUCTOR
		for (int i = 1; i <= 10; i++) {
			Thread.sleep(20);
			progressBar.setValor(i);  ///////////////////
		}	
		
		FrameTablero.objetivo = objetivo;
		FrameTablero.usuario = user;
		usuario.getClientReader().setTablero(this);
		usuario.getClientReader().setSala(null);
		aso = new Asociaciones();
		supMap = aso.getSupMap();
		supJugadores = new HashMap<>();
		cartasJugador = new ArrayList<>();
		supIndMap = new HashMap<>();
		nombresJugadores = new ArrayList<>();
		labelsSup = new HashMap<>();
		objetosEquipadosSup = new HashMap<>();
		turno = false;	
		
////////////////////////////////////////////////////////////////////////////////////////////////////TODO: MENU
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSonido = new JMenu("Sonido");
		mnSonido.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnSonido.setToolTipText("Ajustes de sonido");
		menuBar.add(mnSonido);
		
		JMenuItem mntmSilenciarMusica = new JMenuItem("Silenciar/Reanudar m\u00FAsica");
		mntmSilenciarMusica.setToolTipText("Silencia o reanuda la banda sonora");
		mntmSilenciarMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.pararMusica();
			}
		});
		mntmSilenciarMusica.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnSonido.add(mntmSilenciarMusica);
		
		
		for (int i = 11; i <= 25; i++) {
			Thread.sleep(10);
			progressBar.setValor(i); ////////////////
		}
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmManual = new JMenuItem("Manual");
		mntmManual.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				     File path = new File ("docs/DoWReglas.pdf");
				     Desktop.getDesktop().open(path);
				}catch (IOException ex) {
				     ex.printStackTrace();
				}
			}
		});
		mnAyuda.add(mntmManual);
		mnAyuda.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mntmResumen = new JMenuItem("Fases de la colonia");
		mntmResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameResumen fr = new FrameResumen();
				fr.setVisible(true);
			}
		});
		mntmResumen.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnAyuda.add(mntmResumen);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS ZOMBIES
		
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
		
//////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS RUIDO
		
		locFichRuido = new ArrayList<>();
		locFichRuido.add(locRComisaria);locFichRuido.add(locRSuperm);locFichRuido.add(locRColegio);locFichRuido.add(locRGasolinera);locFichRuido.add(locRHospital);locFichRuido.add(locRBiblioteca);
		
		labelsFichRuido = new ArrayList<>();
		List<JLabel> auxListR;
		for(int i=0;i<locFichRuido.size();i++) {
			auxListR = new ArrayList<>();
			for(int j=0;j<locFichRuido.get(i).length;j++) {
				aux = new JLabel(""); 
				aux.setBounds(locFichRuido.get(i)[j].x, locFichRuido.get(i)[j].y, 32, 28);
				aux.setIcon(imgCircular("images/Fichas/fichaRuido.png",32,28));
				aux.setVisible(false);
				contentPane.add(aux);
				auxListR.add(aux);
			}
			labelsFichRuido.add(auxListR);
		}
		
//////////////////////////////////////////////////////////////////////////////////////////TODO: LABELS SUPERVIVIENTES INDEFENSOS
		
		labelsSupInd = new ArrayList<>();
		for(int i=0;i<locColonia.length;i++) {
			aux = new JLabel("");
			p = locColonia[i];
			aux.setBounds(p.x,p.y, 36, 34);
			aux.setIcon(imgCircular("images/Fichas/fichaSupIndefenso.png",36,34));
			aux.setVisible(false);
			contentPane.add(aux);
			labelsSupInd.add(aux);
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
		
		btnAtacar = new JButton("");
		btnAtacar.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/Atacar.png")));
		btnAtacar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(frameSeleccionar != null) {
					frameSeleccionar.dispose();
				}
				frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|atacar|", supJugadores.get(idJug), aso);
				frameSeleccionar.setVisible(true);
			}
		});
		btnAtacar.setBounds(12, 92, 140, 41);
		btnAtacar.setToolTipText("Atacar a un zombie o superviviente");
		btnAtacar.setEnabled(false);
		contentPane.add(btnAtacar);
		
		btnMoverse = new JButton("");
		btnMoverse.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/Moverse.png")));
		btnMoverse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMoverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(frameSeleccionar != null) {
					frameSeleccionar.dispose();
				}
				frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|mover|", supJugadores.get(idJug), aso);
				frameSeleccionar.setVisible(true);
			}
		});
		btnMoverse.setBounds(181, 92, 129, 41);
		btnMoverse.setToolTipText("Desplazar un superviviente a otra localización");
		btnMoverse.setEnabled(false);
		contentPane.add(btnMoverse);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/Buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frameSeleccionar != null) {
					frameSeleccionar.dispose();
				}
				frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|buscar|", supJugadores.get(idJug), aso);
				frameSeleccionar.setVisible(true);
			}
		});
		btnBuscar.setBounds(12, 144, 115, 41);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.setBounds(12, 144, 140, 41);
		btnBuscar.setToolTipText("Lanzar uno o varios dados");
		btnBuscar.setEnabled(false);
		contentPane.add(btnBuscar);
		
		btnBarricada = new JButton("");
		btnBarricada.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/Barricada.png")));
		btnBarricada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBarricada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frameSeleccionar != null) {
					frameSeleccionar.dispose();
				}
				frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|barricada|", supJugadores.get(idJug), aso);
				frameSeleccionar.setVisible(true);
			}
		});
		btnBarricada.setBounds(12, 196, 140, 41);
		btnBarricada.setToolTipText("Construir una barricada protegiendo un espacio para Zombies");
		btnBarricada.setEnabled(false);
		contentPane.add(btnBarricada);
		
		btnContribuir = new JButton("CONTRIBUIR");
		btnContribuir.setEnabled(false);
		btnContribuir.setVisible(false);
		btnContribuir.setBounds(195, 304, 115, 41);
		btnContribuir.setToolTipText("Aportar un objeto a la crisis");
		contentPane.add(btnContribuir);
		
		btnLimpiarVertedero = new JButton("");
		btnLimpiarVertedero.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/VaciarVertedero.png")));
		btnLimpiarVertedero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(frameSeleccionar != null) {
					frameSeleccionar.dispose();
				}
				frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|vaciar|",supJugadores.get(idJug), aso);
				frameSeleccionar.setVisible(true);
			}
		});
		btnLimpiarVertedero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLimpiarVertedero.setBounds(12, 250, 140, 41);
		btnLimpiarVertedero.setToolTipText("Limpia 3 objetos del vertedero");
		btnLimpiarVertedero.setEnabled(false);
		contentPane.add(btnLimpiarVertedero);
		
		btnAtraerZombie = new JButton("ATRAER Z");
		btnAtraerZombie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtraerZombie.setBounds(12, 300, 140, 41);
		btnAtraerZombie.setToolTipText("Obtienes más objetos buscando, pero atrae Zombies");
		btnAtraerZombie.setEnabled(false);
		btnAtraerZombie.setVisible(false);
		contentPane.add(btnAtraerZombie);
		
		btnFinalizarTurno = new JButton("FINALIZAR TURNO");
		btnFinalizarTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					usuario.hacerPeticionAlServidor(nombresJugadores.get(idJug)+"|1|finturno|"+idJug);turno = false;
					btnAtacar.setEnabled(false);btnMoverse.setEnabled(false);btnBuscar.setEnabled(false);btnBarricada.setEnabled(false);btnContribuir.setEnabled(false);btnLimpiarVertedero.setEnabled(false);
					btnAtraerZombie.setEnabled(false);btnFinalizarTurno.setEnabled(false);btnDarCarta.setEnabled(false);btnPedirCarta.setEnabled(false);btnGastarComida.setEnabled(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnFinalizarTurno.setBackground(new Color(67,73,114));
		btnFinalizarTurno.setForeground(Color.WHITE);
		btnFinalizarTurno.setBounds(12, 392, 286, 41);
		btnFinalizarTurno.setToolTipText("Finaliza el turno del jugador actual e inicia el turno de otro jugador o cambia de ronda");
		btnFinalizarTurno.setEnabled(false);
		contentPane.add(btnFinalizarTurno);
		
		btnDarCarta = new JButton("DAR CARTA");
		btnDarCarta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDarCarta.setBounds(181, 250, 129, 41);
		btnDarCarta.setToolTipText("Ofrece una determinada carta a otro jugador");
		btnDarCarta.setEnabled(false);
		btnDarCarta.setVisible(false);
		contentPane.add(btnDarCarta);
		
		btnPedirCarta = new JButton("PEDIR CARTA");
		btnPedirCarta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPedirCarta.setBounds(181, 195, 129, 41);
		btnPedirCarta.setToolTipText("Pide una determinada carta a otro jugador");
		btnPedirCarta.setEnabled(false);
		btnPedirCarta.setVisible(false);
		contentPane.add(btnPedirCarta);
		
		btnGastarComida = new JButton("");
		btnGastarComida.setIcon(new ImageIcon(FrameTablero.class.getResource("/Botones/GastarComida.png")));
		btnGastarComida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGastarComida.setBounds(181, 146, 129, 41);
		btnGastarComida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(frameDados != null) {
					frameDados.dispose();
				}
				frameDados = new FrameDados(dados,aso,usuario.getNombre() + "|1|gastarComida|");
				frameDados.setVisible(true);
			}
		});
		btnGastarComida.setToolTipText("Desecha una ficha de comida de la colonia con el objetivo de incrementar el resultado de un dado");
		btnGastarComida.setEnabled(false);
		contentPane.add(btnGastarComida);
		
		btnInfoJugador = new JButton("INFO JUGADOR");
		btnInfoJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(infoJug != null) {
					infoJug.dispose();
				}
				System.out.println("DADOS "+dados.toString());
				System.out.println("Tam de la mano del jugador: " + cartasJugador.size());
				infoJug = new InfoJugador(supJugadores.get(idJug), cartasJugador,objetivoSecreto,aso,dados,turno,heridasNormales,heridasCong,objetosEquipadosSup);
				infoJug.setVisible(true);
			}
		});
		btnInfoJugador.setToolTipText("Muestra informacion sobre el jugador y sus cartas");
		btnInfoJugador.setBounds(840, 71, 129, 41);
		contentPane.add(btnInfoJugador);
		
		 /////////////////////////////////////////////
		progressBar.setString("Reclutando supervivientes");
		for (int i = 26; i <= 60; i++) {
			Thread.sleep(10);
			progressBar.setValor(i);
		}
		 /////////////////////////////////////////////
		Thread.sleep(400);
		
		btnInfoTablero = new JButton("INFO TABLERO");
		btnInfoTablero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(infoTab != null) {
					infoTab.dispose();
				}
				infoTab = new InfoTablero(nombresJugadores,supJugadores,aso,nCartasJugadores,heridasNormales,heridasCong,objetosEquipadosSup);
				infoTab.setVisible(true);
			}
		});
		btnInfoTablero.setToolTipText("Muestra informacion sobre el estado actual del tablero");
		btnInfoTablero.setBounds(1270, 71, 129, 41);
		contentPane.add(btnInfoTablero);
		
		ObjetivoPrin = new JButton("");
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
		
		btnSendChat = new JButton(">");
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
		
////////////////////////////////////////////////////////////
		progressBar.setString("Limpiando sangre de zombi");
		for (int i = 61; i <= 75; i++) {
			Thread.sleep(10);
			progressBar.setValor(i);
		}
////////////////////////////////////////////////////////////
		
		Thread.sleep(400);
		
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
				JOptionPane.showMessageDialog(null, "Numero de fichas de comida en la Colonia: " + nFichComida + "\n" +
						"Numero de fichas de hambre en la Colonia: " + nFichHambre); 
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
				JOptionPane.showMessageDialog(null, "Numero de cartas en vertedero: " + vertedero);
			}
		});
		btnVertedero.setOpaque(false);
		btnVertedero.setContentAreaFilled(false);
		contentPane.add(btnVertedero);
		contentPane.setComponentZOrder(btnVertedero, contentPane.getComponentZOrder(lblTablero)-1);
		
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
		btnCartaCrisis = new JButton("");
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
		
//////////////////////////////////////////////////////////////////////
		
		progressBar.setString("Llenando coches de gasolina");
		for (int i = 76; i <= 90; i++) {
			Thread.sleep(10);
			progressBar.setValor(i);
		}
//////////////////////////////////////////////////////////////////////
		
		Thread.sleep(400);
		
		btnCartaCrisis.setBounds(1062, 699, 123, 158);
		System.out.println("IDCRISIS: " +idCrisis);
		ImageIcon ima = (ImageIcon) aso.getCartasCrisis().get(idCrisis).getIcon();
		Image img = ima.getImage().getScaledInstance(123, 158, java.awt.Image.SCALE_SMOOTH);
		btnCartaCrisis.setIcon(new ImageIcon(img));
		contentPane.add(btnCartaCrisis);
		contentPane.setComponentZOrder(btnCartaCrisis, contentPane.getComponentZOrder(lblTablero)-1);
		
		for(int i=0;i<nombresJugadores.size();i++) {
			aportacionesCrisis.add(0);
		}
		
		btnContribucionesCrisis = new JButton("");
		btnContribucionesCrisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(aportCrisis != null) {
					aportCrisis.dispose();
				}
				aportCrisis = new FrameAportacionesCrisis(nombresJugadores,aportacionesCrisis);
				aportCrisis.setVisible(true);
			}
		});
		btnContribucionesCrisis.setBounds(1210, 699, 129, 158);
		btnContribucionesCrisis.setOpaque(false);
		btnContribucionesCrisis.setContentAreaFilled(false);
		contentPane.add(btnContribucionesCrisis);
		contentPane.setComponentZOrder(btnContribucionesCrisis, contentPane.getComponentZOrder(lblTablero)-1);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //maximizar pantalla inicialmente
		
//////////////////////////////////////////////////////////////////////
		progressBar.setString("Comienza la aventura...");
		for (int i = 91; i <= 100; i++) {
			Thread.sleep(10);
			progressBar.setValor(i);
		}
		Thread.sleep(100);
		progressBar.dispose();
//////////////////////////////////////////////////////////////////////			
		
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
	
	/////////////////////////////////////////////////////////TODO: METODOS FRAMETABLERO
	
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
	public void anyadirSuperviviente(int idSup,int posI) {
		p = locColonia[posI];
		aux = supMap.get(idSup)[0];
		aux.setBounds(p.x,p.y, 36, 34);
		contentPane.add(aux);
		contentPane.setComponentZOrder(aux, contentPane.getComponentZOrder(lblTablero)-1);
		labelsSup.put(idSup, aux);
	}
	
	public void borrarSuperviviente(int idSup) {
		aux = labelsSup.get(idSup);
		aux.setVisible(false);
		contentPane.remove(aux);
		labelsSup.put(idSup,null);
		
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
	
	public void addSupInd(int posDePartida) {
		for(int i=posDePartida;i<locColonia.length;i++) {
			aux = labelsSupInd.get(i);
			aux.setVisible(true);
			contentPane.setComponentZOrder(aux, contentPane.getComponentZOrder(lblTablero)-1);
		}
	}
	
	//PASAMOS ID DEL SUPERVIVIENTE A MOVER, ID DE LA LOCALIZACION A MOVER, POS DENTRO DE LA LOCALIZACION
	public void moverSuperviviente(int id, int loc, int pos) {
		aux = supMap.get(id)[0];
		if(aux != null) {
		if(pos != -1) {
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
		}else {
			JOptionPane.showMessageDialog(null, "La accion no se puede realizar");
		}
		}
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
	
	public void rmSupJuga(int idJug, int idSup) {
		System.out.println("ID Sup: " + idSup + ", Jugador: " + idJug);

		int s = supJugadores.get(idJug).indexOf(idSup);
		System.out.println(s);
		supJugadores.get(idJug).remove(s);
	}
	
	public void removeDado(int pos) {
		dados.remove(pos);
	}
	
	public void setDado(int pos, int valor) {
		dados.set(pos, valor);
	}
	
	public void addCartaJug(int idCarta) {
		if(cartasJugador == null) {
			cartasJugador = new ArrayList<>();
		}
		cartasJugador.add(idCarta);

	}
	
	public void rmCartaJug(int idCarta) {
		int c = cartasJugador.indexOf(idCarta);
		cartasJugador.remove(c);
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
	
	public void inicDados() {
		dados = new ArrayList<>();
	}
	
	public void tiradaDados(int res) {
		dados.add(res);
	}
	
	public void setRonda(int ronda) {
		fichRonda.setLocation(locRonda[ronda-1]);
	}
	
	public void setMoral(int moral) {
		if (moral != 0) {
			fichMoral.setLocation(locMoral[moral-1]);
		}
	}
	
	public void setCrisis(int crisis) {
		idCrisis = crisis;
		try {
			Thread.sleep(400); //TODO: JUGAR CON EL TIEMPO SI SE RALLA EL BOTON
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ImageIcon ima = (ImageIcon) aso.getCartasCrisis().get(idCrisis).getIcon();
		Image img = ima.getImage().getScaledInstance(123, 158, java.awt.Image.SCALE_SMOOTH);
		btnCartaCrisis.setIcon(new ImageIcon(img));
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
	
	public void addFichRuido(int loc, int pos) {
		labelsFichRuido.get(loc).get(pos).setVisible(true);
	}
	
	public void deleteFichRuido(int loc, int pos) {
		labelsFichRuido.get(loc).get(pos).setVisible(false);
	}

	public void addBarricada(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setIcon(imgCircular("images/Fichas/barricada.png",36,34));
		labelsZombies.get(loc).get(pos).setVisible(true);
	}
	
	public void deleteBarricada(int loc, int pos) {
		labelsZombies.get(loc).get(pos).setVisible(false);
		labelsZombies.get(loc).get(pos).setIcon(imgCircular("images/fichaZombieReal.png",36,34));
	}
	
	/*public static void pedirHeridas(int id) throws IOException{
		usuario.hacerPeticionAlServidor(usuario.getNombre() + "|" + 1 + "|heridas|" + id);
	}
	
	public static void setHeridas(int[] h) {
		heridas = h.clone();
	}
	
	public static int[] getHeridas() {
		return heridas;
	}*/
	
	public void setHeridasNormales(int idSup, int numHeridas) {
		heridasNormales.put(idSup, numHeridas);
	}
	
	public void setHeridasCong(int idSup, int numHeridas) {
		heridasCong.put(idSup, numHeridas);
	}
	
	public static void inicCartasEncontradas() {
		cartEncont = new ArrayList<>();
	}
	
	public static void setCartasEncontradas(List<Integer> l){
		cartEncont = new ArrayList<>();
		cartEncont.addAll(l);
		System.out.println("Cartas Encontradas: " + cartEncont.toString()); 
	}
	
	public static List<Integer> getCartasEncontradas(){
		return cartEncont;
	}
	
	public static void setNCartasABuscar(int n) {
		nCartasABuscar = n;
	}
	
	public static int getNCartasABuscar() {
		return nCartasABuscar;
	}
	
	public void setVertedero(int valor) {
		vertedero = valor;
	}
	
	public void setFichComida(int valor) {
		nFichComida = valor;
	}
	
	public void setFichHambre(int valor) {
		nFichHambre = valor;
	}
	
	public void inicAportaciones() {
		aportacionesCrisis = new ArrayList<>();
		cartasResolucionCrisis = new ArrayList<>();
		for(int i=0;i<nombresJugadores.size();i++) {
			aportacionesCrisis.add(0);
		}
	}
	
	public void inicHeridas() {
		heridasNormales = new HashMap<>();
		heridasCong = new HashMap<>();
		List<Integer> aux2 = new ArrayList<>();
		aux2.addAll(labelsSup.keySet());
		for (Integer i : aux2) {
			heridasNormales.put(i, 0);
			heridasCong.put(i, 0);
		}
	}
	
	public void inicRuido() {
		for(int i=0;i < labelsFichRuido.size();i++) {
			for(int j=0;j < labelsFichRuido.get(i).size();j++) {
				labelsFichRuido.get(i).get(j).setVisible(false);
			}
		}
	}
	
	public void cartasAportadas(int idCarta) {
		cartasResolucionCrisis.add(idCarta);
	}
	
	public void setNumAport(List<Integer> list) {
		aportacionesCrisis = list;
	}
	
	public void cartasResCrisis() {
		frameCartasAportadas = new FrameCartasAportadas(cartasResolucionCrisis,aso,crisisRes);
		frameCartasAportadas.setVisible(true);
	}
	
	public void crisisResult(int s) {
		crisisRes = s;
	}
	
	public void seleccionSupervivientes(List<Integer> list, String comando) {
		//TODO: FRAME GENERICO PARA MOSTRAR UNA LISTA DE SUPERVIVIENTES SELECCIONABLES
		if(frameSeleccionar != null) {
			frameSeleccionar.dispose();
		}
		frameSeleccionar = new FrameSeleccionSuperviviente(usuario.getNombre() + "|1|"+comando+"|", list, aso);
		frameSeleccionar.setVisible(true);
	}
	
	public void evento(int idEvento) {
		if(frameSupNuevo != null) {
			frameSupNuevo.dispose();
		}
		frameSupNuevo = new FrameSupervivienteNuevo(idEvento);
		frameSupNuevo.setVisible(true);
	}
	
	public void refresh() {
		repaint();
	}
	
	public static Asociaciones getAsociaciones() {
		return aso;
	}
	
	public static boolean getTurno() {
		return turno;
	}
	
	public static int getid() {
		return idJug;
	}
	
	public static List<Integer> getDados(){
		return dados;
	}
	
	public void equiparObj(int idSup, int idObj) {
		if(objetosEquipadosSup.get(idSup) == null) {
			objetosEquipadosSup.put(idSup, new ArrayList<>());
		}
		objetosEquipadosSup.get(idSup).add(idObj);
		System.out.println("AÑADIMOS OBJ "+ idObj);
		System.out.println("LISTA DE OBJETOS EQUIPADOS DE " + idSup + ": " + objetosEquipadosSup.get(idSup).toString());
	}
	
	public static List<Integer> getSupJug() {
		return supJugadores.get(idJug);
	}
	
	public void verCarta(String idCarta) {
		if(fCarta != null) {
			fCarta.dispose();
		}
		fCarta = new FrameCarta(aso.getCartasObjetos(),idCarta,false);
		fCarta.setVisible(true);
	}
	
	public static HashMap<Integer,List<Integer>> getSupJugadores(){
		return supJugadores;
	}
	
	public static void enviarComando(String command) {
		try {
			usuario.hacerPeticionAlServidor(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tiradaDeRiesgo(int idRiesgo) {
		tirRiesgo = new FrameTiradaRiesgo(idRiesgo);
		tirRiesgo.setVisible(true);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tirRiesgo.dispose();
	}
	
	public static void errorPartida(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void miTurno() {
		try {
			Thread.sleep(700);
			turno = true;
			btnAtacar.setEnabled(true);btnMoverse.setEnabled(true);btnBuscar.setEnabled(true);btnBarricada.setEnabled(true);btnContribuir.setEnabled(true);btnLimpiarVertedero.setEnabled(true);
			btnAtraerZombie.setEnabled(true);btnFinalizarTurno.setEnabled(true);btnDarCarta.setEnabled(true);btnPedirCarta.setEnabled(true);btnGastarComida.setEnabled(true);
	
			Thread.sleep(700);
			frameTuTurno = new FrameTuTurno("/TuTurno.png");
			frameTuTurno.setVisible(true);
				
			AudioInputStream audioInputStream;
			audioInputStream = AudioSystem.getAudioInputStream(new File("music/Cerrojo.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
			
			Thread.sleep(1700);
			frameTuTurno.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/*public void goToURL(String URL){
		URL url=null;
		try {
		    url = new URL(URL);
		    try {
		        Desktop.getDesktop().browse(url.toURI());
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (URISyntaxException e) {
		        e.printStackTrace();
		    }
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		}
	}*/
	
	public void finPartida() {
		frameFinPartida = new FrameFinPartida();
		frameFinPartida.setVisible(true);
		dispose();
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
	
	public void mostrarJugadorSupervivientes() {
		for(int i = 0; i < supJugadores.size(); i++) {
			System.out.println("Jugador: " + nombresJugadores.get(i) + ", Supervivientes: " + supJugadores.get(i).toString());
			
		}
	}
	
	private void inicializarTablero(String ruta) {
		List<List<List<Point>>> puntosFichero = PruebaFile.leerFicheroMapa(ruta);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////			Supervivientes			/////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
		
		locComisaria = (Point[]) puntosFichero.get(0).get(0).toArray();
		locSupermercado = (Point[]) puntosFichero.get(0).get(1).toArray();
		locColegio = (Point[]) puntosFichero.get(0).get(2).toArray();
		locGasolinera  = (Point[]) puntosFichero.get(0).get(3).toArray();
		locHospital = (Point[]) puntosFichero.get(0).get(4).toArray();
		locBiblioteca= (Point[]) puntosFichero.get(0).get(5).toArray();
		locColonia= (Point[]) puntosFichero.get(0).get(6).toArray();
		
/////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////			Zombies					/////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		
		
	}
}



