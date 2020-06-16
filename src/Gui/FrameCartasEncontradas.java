package Gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JCheckBox;

public class FrameCartasEncontradas extends JFrame {

	private JPanel contentPane;
	private FrameCartasEncontradas auxCartasEncontradas;
	private static int numCartasPoderBuscar;
	private static List<Integer> cartas;
	private List<Integer> cartaSeleccionada;
	private static Asociaciones asociaciones;
	private Point posCartaEncontradas[] = {new Point(32, 11), new Point(230, 11), new Point(433, 11), new Point(643, 11), new Point(841, 11)
			, new Point(32, 259), new Point(230, 259), new Point(433, 259), new Point(643, 259), new Point(841, 259)};
	private JLabel cartasEncon[];
	private JCheckBox checkB[];
	private Point  posCheckPoint[] = {new Point(57, 226), new Point(255, 226), new Point(458, 226), new Point(668, 226), new Point(866, 226)
			, new Point(57, 474), new Point(255, 474), new Point(458, 474), new Point(668, 474), new Point(866, 474)};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCartasEncontradas frame = new FrameCartasEncontradas(cartas, asociaciones, numCartasPoderBuscar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param cartasEncontradas 
	 * @param aso 
	 */
	public FrameCartasEncontradas(List<Integer> cartasEncontradas, Asociaciones aso, int cartasABuscar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1041, 575);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		numCartasPoderBuscar = cartasABuscar;
		
		cartasEncon = new JLabel[10];
		checkB = new JCheckBox[10];
		
		DerHandler Handler = new DerHandler();
		
		cartaSeleccionada = new ArrayList<Integer>();
		
		int tamCartas = cartasEncontradas.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		for(int z = 0; z < tamCartas; z++) {
			p = posCartaEncontradas[z];
			cartasEncon[z] = new JLabel(String.valueOf(cartasEncontradas.get(z)));
			icon = (ImageIcon) aso.getCartasObjetos().get(cartasEncontradas.get(z)).getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			cartasEncon[z].setIcon(new ImageIcon(img));
			cartasEncon[z].setBounds(p.x, p.y, 147, 205);
			contentPane.add(cartasEncon[z]);
			p = posCheckPoint[z];
			checkB[z] = new JCheckBox(String.valueOf(cartasEncontradas.get(z)));
			checkB[z].setText("Seleccionar");
			checkB[z].setBounds(p.x, p.y, 97, 23);
			checkB[z].addItemListener(Handler);
			checkB[z].setActionCommand(""+z);
			contentPane.add(checkB[z]);
		}
		
		JButton btnVolver = new JButton("Aceptar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				for(int i = 0; i < cartaSeleccionada.size(); i++) {
					msg += "|" + cartaSeleccionada.get(i);
				}
				FrameTablero.enviarComando(FrameTablero.getUsuario().getNombre() + "|1|confirmarCarta" + msg);
				dispose();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(278, 535, 155, 34);
		contentPane.add(btnVolver);
		
		JButton btnRuido = new JButton("Hacer Ruido");
		btnRuido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameTablero.enviarComando(FrameTablero.getUsuario().getNombre() + "|1|ruido");
				dispose();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				if(auxCartasEncontradas != null) {
					auxCartasEncontradas.dispose();
				}
				
				List<Integer> cartas = new ArrayList<>();
				cartas = FrameTablero.getCartasEncontradas();
				
				auxCartasEncontradas = new FrameCartasEncontradas(cartas, asociaciones, numCartasPoderBuscar);
				auxCartasEncontradas.setVisible(true);
			}
		});
		btnRuido.setBounds(593, 535, 155, 34);
		contentPane.add(btnRuido);
		
	}
	private class DerHandler implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox aux = (JCheckBox) e.getItem();
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(cartaSeleccionada.size() >= numCartasPoderBuscar) {
					aux.setSelected(false);
				}else {
					cartaSeleccionada.add(Integer.parseInt(cartasEncon[Integer.parseInt(aux.getActionCommand())].getText()));
				}
			}else if (cartaSeleccionada.indexOf(Integer.parseInt(cartasEncon[Integer.parseInt(aux.getActionCommand())].getText())) != -1  && e.getStateChange() == ItemEvent.DESELECTED) 
				cartaSeleccionada.remove(cartaSeleccionada.indexOf(Integer.parseInt(cartasEncon[Integer.parseInt(aux.getActionCommand())].getText())));
			}
		}	
	}
