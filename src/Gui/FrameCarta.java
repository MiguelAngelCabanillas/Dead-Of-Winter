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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class FrameCarta extends JFrame {

	private JPanel contentPane;
	private static HashMap<Integer, JLabel> carta;
	private static String id;
//	private Asociaciones asociaciones;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					FrameCarta frame = new FrameCarta(carta);
					FrameCarta frame = new FrameCarta(carta, id);
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
	public FrameCarta(HashMap<Integer, JLabel> carta, String id) {
		
//		asociaciones = new Asociaciones();
		
		
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11,  406, 517);
//		ImageIcon icon = (ImageIcon) carta;
//		Image img = icon.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(carta.get(Integer.parseInt(id)).getIcon());
//		lblNewLabel.setIcon(new ImageIcon(img));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(32, 548, 95, 38);
		contentPane.add(btnVolver);
		contentPane.add(lblNewLabel);
		
		JButton btnUsarCarta = new JButton("Usar carta");
		btnUsarCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoJugador.Dispose();
				dispose();
			}
		});
		btnUsarCarta.setToolTipText("Se aplica el efecto de la carta seleccionada");
		btnUsarCarta.setBounds(166, 548, 95, 38);
		contentPane.add(btnUsarCarta);
		
		JButton btnAportarCrisis = new JButton("Aportar");
		btnAportarCrisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoJugador.Dispose();
				dispose();
			}
		});
		btnAportarCrisis.setToolTipText("Aporta esta carta para contribuir a la crisis");
		btnAportarCrisis.setBounds(304, 548, 95, 38);
		contentPane.add(btnAportarCrisis);
		
		
	}
	private void ajustarAPantalla() {
		  Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(431, 599);

	      setLocationRelativeTo(null);
	}
}
