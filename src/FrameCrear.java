import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class FrameCrear extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCrear frame = new FrameCrear();
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
	public FrameCrear() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int codigo;
		int numJugadores;
		numJugadores =  1;
		codigo = 3342;
		
		JLabel lblNewLabel = new JLabel("El codigo de la partida es " + codigo + ", espere a que se unan los jugadores");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, -16, 406, 133);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Iniciar la partida con los jugadores conectados");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Partida.iniciar();
			}
		});
		btnNewButton.setBounds(152, 153, 322, 68);
		contentPane.add(btnNewButton);
		
		JButton btnNewButtonVolver = new JButton("Volver");
		btnNewButtonVolver.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameSeleccion fseleccion = new FrameSeleccion();
				fseleccion.setVisible(true);
				dispose();
				
			}
		});
		btnNewButtonVolver.setBounds(503, 371, 90, 21);
		contentPane.add(btnNewButtonVolver);
		
		JLabel lblNDeJugadores = new JLabel("N\u00BA de jugadores:    " + numJugadores);
		lblNDeJugadores.setBackground(new Color(255, 255, 255));
		lblNDeJugadores.setForeground(new Color(255, 255, 255));
		lblNDeJugadores.setFont(new Font("Arial Black", Font.PLAIN, 10));
		lblNDeJugadores.setBounds(447, 27, 202, 49);
		contentPane.add(lblNDeJugadores);
		
		ImageIcon ima = new ImageIcon("C:\\Users\\cgmoy\\eclipse-workspace\\GuiPartidas\\src\\imagenes\\seleccionimg.jpg");
		Image img = ima.getImage().getScaledInstance(603, 402, java.awt.Image.SCALE_SMOOTH); 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 603, 402);
		contentPane.add(label);
	}
}
