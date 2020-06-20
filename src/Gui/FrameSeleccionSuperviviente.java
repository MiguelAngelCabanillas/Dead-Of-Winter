package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;

public class FrameSeleccionSuperviviente extends JFrame {
	
	private static String comando;
	private List<Integer> cartas = new ArrayList<>();
	private FrameCartasEncontradas auxCartasEncontradas;
	private JPanel contentPane;
	private JButton botoneSuperviviente[];
	private static List<Integer> supJugadores;
	private static Asociaciones asociaciones;
	private Point posCartaSuperviviente[] = {new Point(32, 11), new Point(230, 11), new Point(433, 11), new Point(643, 11), new Point(841, 11)
			, new Point(32, 251), new Point(230, 251), new Point(433, 251), new Point(643, 251), new Point(841, 251)};
	private FrameLocalizaciones auxMover;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSeleccionSuperviviente frame = new FrameSeleccionSuperviviente(comando, supJugadores, asociaciones);
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
	public FrameSeleccionSuperviviente(String comand, List<Integer> supJugadores, Asociaciones aso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1041, 529);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		asociaciones = aso;
		
		comando = comand;
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(433, 489, 155, 34);
		contentPane.add(btnNewButton);
		
		SupDerHandler supervivientesHandler = new SupDerHandler();
		
		asociaciones = aso;
		
		botoneSuperviviente = new JButton[10];
		
		int tamSupervivientes = supJugadores.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		for(int z = 0; z < tamSupervivientes; z++) {
			p = posCartaSuperviviente[z];
			botoneSuperviviente[z] = new JButton(String.valueOf(supJugadores.get(z)));
			icon = (ImageIcon) aso.getSupMap().get(supJugadores.get(z))[1].getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botoneSuperviviente[z].setIcon(new ImageIcon(img));
			botoneSuperviviente[z].setBounds(p.x, p.y, 147, 205);
			botoneSuperviviente[z].addActionListener(supervivientesHandler);
			botoneSuperviviente[z].setActionCommand(""+z);
			contentPane.add(botoneSuperviviente[z]);
		}
			

	}
	
	private class SupDerHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] aux = comando.split("\\|");
			switch (aux[2]) {
			
			case "mover":
				if(auxMover != null) {
					auxMover.dispose();
				} 
				auxMover = new FrameLocalizaciones(comando, botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				auxMover.setVisible(true);
				dispose();
				break;
				
			case "barricada":
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				dispose();
				break;
			
			case "medicina":
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				dispose();
				break;
				
			case "vaciar":
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				dispose();
				break;
				
			case "buscar":
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				if(auxCartasEncontradas != null) {
					auxCartasEncontradas.dispose();
				}
				
				cartas = FrameTablero.getCartasEncontradas();
				
				if(cartas != null) {
				if(cartas.size() != 0) {
					
					try {
						auxCartasEncontradas = new FrameCartasEncontradas(cartas, asociaciones, FrameTablero.getNCartasABuscar());
						auxCartasEncontradas.setVisible(true);
					}catch(Exception e1) {
						System.err.println(e1.getMessage());
					}
					
				}}
				
				dispose();
				FrameTablero.inicCartasEncontradas();
				break;
			//en principio atacamos a un zombie
			case "atacar":
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				dispose();
				break;
			
			case "usarCarta":
				System.out.println("USAR CARTA "+ aux[3] + " EN EL SUPERVIVIENTE " + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText());
				FrameTablero.enviarComando(comando + botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText()); //usuario|1|usarCarta|idCarta|idSup
				dispose();
				break;
				
			case "usarHabilidad":
				FrameTablero.enviarComando(comando+ botoneSuperviviente[Integer.parseInt(e.getActionCommand())].getText()+"|-1|");
				break;
				
			default:
				break;
			}
		}	
	}
}
