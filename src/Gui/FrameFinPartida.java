package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameFinPartida extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameFinPartida frame = new FrameFinPartida();
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
	public FrameFinPartida() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1154, 736);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setTitle("Fin de la partida");
		
		JLabel lblFinPartida = new JLabel("");
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/FinPartida/FinPartida.png"));
		Image img = ima.getImage().getScaledInstance(1136, 689, java.awt.Image.SCALE_SMOOTH); 
		lblFinPartida.setBounds(0, 0, 1136, 689);
		lblFinPartida.setIcon(new ImageIcon(img));
		setLocationRelativeTo(null);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Candara", Font.BOLD, 36));
		lblInfo.setBounds(533, 246, 512, 81);
		lblInfo.setBackground(new Color(0,0,0,100));
		ima = new ImageIcon(this.getClass().getResource("/FinPartida/ElijaOpcion.png"));
		img = ima.getImage().getScaledInstance(512, 81, java.awt.Image.SCALE_SMOOTH); 
		lblInfo.setIcon(new ImageIcon(img));
		contentPane.add(lblInfo);
		
		JButton btnSalir = new JButton("Salir del juego");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		btnSalir.setBounds(848, 381, 137, 37);
		contentPane.add(btnSalir);
		
		JButton btnVolverSala = new JButton("Volver a salas");
		btnVolverSala.setToolTipText("Volver a seleccion de salas");
		btnVolverSala.setBounds(599, 381, 137, 37);
		contentPane.add(btnVolverSala);
		contentPane.add(lblFinPartida);
	}
}
