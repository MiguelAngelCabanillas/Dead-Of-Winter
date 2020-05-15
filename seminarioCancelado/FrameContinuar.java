package seminarioCancelado;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameContinuar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButtonVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameContinuar frame = new FrameContinuar();
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
	public FrameContinuar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(119, 88, 539, 325);
		contentPane.add(table);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(349, 25, 85, 21);
		contentPane.add(btnIniciar);
		
		btnNewButtonVolver = new JButton("Volver");
		btnNewButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameSeleccion fseleccion = new FrameSeleccion();
				fseleccion.setVisible(true);
				dispose();
			}
		});
		btnNewButtonVolver.setBounds(22, 37, 106, 33);
		contentPane.add(btnNewButtonVolver);
	}
}
