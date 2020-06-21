package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

public class FrameSupervivientes extends JFrame {

	private JPanel contentPane;
	private static HashMap<Integer, JLabel[]> carta;
	private static String id;
	//private static int[] heridas;
	private static HashMap<Integer,Integer> heridasNormales,heridasCong;
	private FrameObjetosEquipados frameObjEqu;
	private static HashMap<Integer,List<Integer>> objetosEquipadosSup;
	private FrameDados fDados;
	private FrameSeleccionSuperviviente fSup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSupervivientes frame = new FrameSupervivientes(carta, id, heridasNormales,heridasCong,objetosEquipadosSup);
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
	
	/**
	 * @wbp.parser.constructor
	 */
	public FrameSupervivientes(HashMap<Integer, JLabel[]> carta, String id, HashMap<Integer,Integer> hN,HashMap<Integer,Integer> hC,HashMap<Integer,List<Integer>> objt) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		setBackground(new Color(0, 0, 0, 10));
		ajustarAPantalla();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		heridasNormales = hN;
		heridasCong = hC;
		objetosEquipadosSup = objt;
		
		JLabel lblSuperviviente = new JLabel("");
		lblSuperviviente.setBounds(10, 12,  406, 575);
		lblSuperviviente.setIcon(carta.get(Integer.parseInt(id))[1].getIcon());

		
		JButton btnVolver = new JButton("Volver\r\n");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		JLabel lblHeridaCongelacion = new JLabel("");
		lblHeridaCongelacion.setBounds(89, 220, 100, 100);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Fichas/Herida-Congelacion.png"));
		Image heridaCongelacion = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		
		JLabel lblHerida = new JLabel("");
		lblHerida.setBounds(223, 350, 100, 100);
		icon = new ImageIcon(this.getClass().getResource("/Fichas/Herida.png"));
		Image heridaNormal = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		JLabel lblHerida3 = new JLabel("");
		lblHerida3.setBounds(223, 100, 100, 100);
		
		//Se encarga de poner las heridas manuelamente (Automatico necesito asoc con las heridas)
		switch (heridasNormales.get(Integer.parseInt(id))) {
		case 3:	lblHerida.setIcon(new ImageIcon(heridaNormal));
				lblHeridaCongelacion.setIcon(new ImageIcon(heridaNormal));
				lblHerida3.setIcon(new ImageIcon(heridaNormal));
				contentPane.add(lblHerida3);
				contentPane.add(lblHerida);	
				contentPane.add(lblHeridaCongelacion);
			break;
		case 2:
			lblHerida.setIcon(new ImageIcon(heridaNormal));
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaNormal));
			contentPane.add(lblHerida);	
			contentPane.add(lblHeridaCongelacion);
			break;
		case 1:
			lblHerida.setIcon(new ImageIcon(heridaNormal));
			contentPane.add(lblHerida);	
		default:
			break;
		}
		
		switch (heridasCong.get(Integer.parseInt(id))) {
		case 3:	lblHerida.setIcon(new ImageIcon(heridaCongelacion));
				lblHeridaCongelacion.setIcon(new ImageIcon(heridaCongelacion));
				lblHerida3.setIcon(new ImageIcon(heridaCongelacion));
				contentPane.add(lblHerida3);
				contentPane.add(lblHerida);	
				contentPane.add(lblHeridaCongelacion);
				break;
		case 2:
			lblHerida.setIcon(new ImageIcon(heridaCongelacion));
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaCongelacion));
			contentPane.add(lblHerida);	
			contentPane.add(lblHeridaCongelacion);
			break;
		case 1:
			lblHeridaCongelacion.setIcon(new ImageIcon(heridaCongelacion));
			contentPane.add(lblHeridaCongelacion);
		default:
			break;
		}
				
		
		btnVolver.setBounds(16, 600, 95, 38);
		contentPane.add(btnVolver);
		contentPane.add(lblSuperviviente);
		
		JButton btnHabilidad = new JButton("Usar Habilidad");
		btnHabilidad.setBounds(125, 600, 128, 38);
		btnHabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: HABILIDADES DE CADA SUPERVIVIENTE
				String command = FrameTablero.getUsuario().getNombre()+"|1|usarHabilidad|"+id+"|"; //usarHabilidad|idSup|objetivo|dado
				switch(id) {
				case "102": FrameTablero.enviarComando(command+"-1|-1");
					dispose();
					InfoJugador.Dispose();
					break;
				case "107": 
					if(fDados != null) {
						fDados.dispose();
					}
					fDados = new FrameDados(FrameTablero.getDados(),FrameTablero.getAsociaciones(),command);
					fDados.setVisible(true);
					dispose();
					InfoJugador.Dispose();
					break;
				case "110": FrameTablero.enviarComando(command+"-1|-1");
					dispose();
					InfoJugador.Dispose();
					break;
				case "112": FrameTablero.enviarComando(command+"-1|-1");
					dispose();
					InfoJugador.Dispose();
					break;
				case "113": FrameTablero.enviarComando(command+"-1|-1");
					dispose();
					InfoJugador.Dispose();
					break;
				case "114": FrameTablero.enviarComando(command+"-1|-1");
					dispose();
					InfoJugador.Dispose();
					break;
				case "115": 
					HashMap<Integer,List<Integer>> supJugadores = FrameTablero.getSupJugadores();
					Set<Integer> aux = supJugadores.keySet();
					List<Integer> aux2 = new ArrayList<>();
					for(int i=0;i<aux.size();i++) {
						if(i != FrameTablero.getUsuario().getJugador().getId()) {
							aux2.add(supJugadores.get(i).get(0));
						}
					}
					if(fSup != null) {
						fSup.dispose();
					}
					fSup = new FrameSeleccionSuperviviente(command,aux2,FrameTablero.getAsociaciones());
					fSup.setVisible(true);
					dispose();
					InfoJugador.Dispose();
					break;
				case "117":
					if(fDados != null) {
						fDados.dispose();
					}
					fDados = new FrameDados(FrameTablero.getDados(),FrameTablero.getAsociaciones(),command);
					fDados.setVisible(true);
					dispose();
					InfoJugador.Dispose();
					break;
		////////////////////////////////////
				case "122":
					break;
				case "123":
					break;
				case "125":
					break;
				case "127":
					break;
				}
			}
		});
		contentPane.add(btnHabilidad);
		
		JButton btnObjEquipados = new JButton("Objetos Equipados");
		btnObjEquipados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: FRAME OBJETOS EQUIPADOS
				if(objetosEquipadosSup.get(Integer.parseInt(id)) == null) {
					JOptionPane.showMessageDialog(null, "Este superviviente no tiene objetos equipados");
				}else {
					if(frameObjEqu != null) {
						frameObjEqu.dispose();
					} 
					frameObjEqu = new FrameObjetosEquipados(objetosEquipadosSup.get(Integer.parseInt(id)),false);
					frameObjEqu.setVisible(true);
					dispose();
				}	
			}
		});
		btnObjEquipados.setBounds(267, 600, 149, 38);
		contentPane.add(btnObjEquipados);
		
		if(heridasNormales.get(Integer.parseInt(id)) != null) {
		System.out.println("Heridas normales de " + Integer.parseInt(id) +": "+ heridasNormales.get(Integer.parseInt(id)));
		}
		if(heridasCong.get(Integer.parseInt(id)) != null) {
			System.out.println("Heridas de congelacion de " + Integer.parseInt(id) +": "+ heridasCong.get(Integer.parseInt(id)));
		}
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(431, 647);

	      setLocationRelativeTo(null);
	}
}
