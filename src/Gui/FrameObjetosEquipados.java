package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class FrameObjetosEquipados extends JFrame {

	private JPanel contentPane;
	private JButton botoneObjEquipados[];
	private FrameCarta auxCarta;
	private static boolean turno;
	private static List<Integer> supObjEquipados;
	private Point posCartaObj[] = {new Point(32, 11), new Point(230, 11), new Point(433, 11), new Point(643, 11), new Point(841, 11)
			, new Point(32, 251), new Point(230, 251), new Point(433, 251), new Point(643, 251), new Point(841, 251)};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameObjetosEquipados frame = new FrameObjetosEquipados(supObjEquipados, turno);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param aso 
	 * @param obj 
	 */
	public FrameObjetosEquipados(List<Integer> obj, boolean t) {
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
		
		turno = t;
		supObjEquipados = obj;
		DerHandler handler = new DerHandler();
//		comando = comand;
		botoneObjEquipados = new JButton[10];
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(433, 489, 155, 34);
		contentPane.add(btnNewButton);
		
		int tamSupervivientes = obj.size();
		ImageIcon icon;
		Image img;
		Point p;
		
		for(int z = 0; z < tamSupervivientes; z++) {
			p = posCartaObj[z];
			botoneObjEquipados[z] = new JButton(String.valueOf(obj.get(z)));
			icon = (ImageIcon) FrameTablero.getAsociaciones().getCartasObjetos().get(obj.get(z)).getIcon();
			img = icon.getImage().getScaledInstance(157, 205, java.awt.Image.SCALE_SMOOTH);
			botoneObjEquipados[z].setIcon(new ImageIcon(img));
			botoneObjEquipados[z].setBounds(p.x, p.y, 147, 205);
			botoneObjEquipados[z].addActionListener(handler);
			botoneObjEquipados[z].setActionCommand(""+z);
			contentPane.add(botoneObjEquipados[z]);
		}
			
		
	}
	
	private class DerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
				if(auxCarta != null) {
					auxCarta.dispose();
				} 
				auxCarta = new FrameCarta(FrameTablero.getAsociaciones().getCartasObjetos(), botoneObjEquipados[Integer.parseInt(e.getActionCommand())].getText(),turno);
				auxCarta.setVisible(true);
		}
		
	}
	
}
