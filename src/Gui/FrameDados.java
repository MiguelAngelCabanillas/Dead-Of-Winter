package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class FrameDados extends JFrame {

	private JPanel contentPane;
	private static List<Integer> dados;
	private static Asociaciones asociaciones;
	private List<JButton> labelsDados;
	private Point posDado[] = {new Point(10, 57), new Point(136, 57), new Point(262, 57), new Point(399, 57), new Point(545, 57)
			, new Point(685, 57), new Point(818, 57)};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDados frame = new FrameDados(dados, asociaciones);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param numDados 
	 * @param aso 
	 */
	public FrameDados(List<Integer> numDados, Asociaciones aso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 901, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		setContentPane(contentPane);
		
		DerHandler handler = new DerHandler();
		labelsDados = new ArrayList<>();
		
		Point p;
		ImageIcon icon;
		Image img;
		
		
		for (int d = 0; d < numDados.size(); d++) {
			p = posDado[d];
			labelsDados.add(new JButton(String.valueOf(numDados.get(d))));
			icon = (ImageIcon) aso.getDados().get(numDados.get(d)+399).getIcon();
			img = icon.getImage().getScaledInstance(78, 70, java.awt.Image.SCALE_SMOOTH);
			labelsDados.get(d).setIcon(new ImageIcon(img));
			labelsDados.get(d).setBounds(p.x, p.y, 70, 68);
			labelsDados.get(d).addActionListener(handler);
			contentPane.add(labelsDados.get(d));
		}
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Castellar", Font.BOLD, 20));
		btnNewButton.setBounds(750, 4, 145, 33);
		contentPane.add(btnNewButton);
		
		
		JLabel lblFondoInfo = new JLabel("");
		icon = new ImageIcon(this.getClass().getResource("/fondo-info.jpg"));
		img = icon.getImage().getScaledInstance(1924, 1021, java.awt.Image.SCALE_SMOOTH);
		lblFondoInfo.setBounds(0, 0, 901, 189);
		lblFondoInfo.setIcon(new ImageIcon(img));
		contentPane.add(lblFondoInfo);
		
		
		
	}
	private class DerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ACTION COMAND: "+e.getActionCommand());
			FrameTablero.enviarComando(FrameTablero.getUsuario().getNombre() + "|1|gastarComida|" + labelsDados.indexOf(Integer.parseInt(e.getActionCommand())));
			dispose();
		}
		
	}
}
