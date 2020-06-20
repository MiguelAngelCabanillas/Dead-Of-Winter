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
import java.util.List;
import javax.swing.SwingConstants;

public class FrameSupervivienteNuevo extends JFrame {
	
	private JPanel contentPane;
	private static int idS;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSupervivienteNuevo frame = new FrameSupervivienteNuevo(idS);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrameSupervivienteNuevo(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 944, 793);
		setBackground(new Color(0, 0, 0, 10));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icono4.png")));
		
		idS = id;
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblCartaEvento = new JLabel("New label");
		lblCartaEvento.setBounds(277, 151, 406, 517);
		
		btnVolver.setBounds(405, 746, 155, 34);
		switch (idS) {
		case 6:
			lblCartaEvento.setIcon(new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente1.jpg")));
			break;
		case 7:
			lblCartaEvento.setIcon(new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente2.jpg")));			
			break;
		case 8:
			lblCartaEvento.setIcon(new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente3.jpg")));			
			break;
		default:
			break;
		}
		contentPane.add(lblCartaEvento);
		

		JLabel lblEvento = new JLabel("");
		lblEvento.setIcon(new ImageIcon(FrameSupervivienteNuevo.class.getResource("/Varios/Evento.png")));
		lblEvento.setFont(new Font("Castellar", Font.BOLD, 24));
		lblEvento.setForeground(Color.WHITE);
		lblEvento.setBounds(277, 13, 403, 103);
		contentPane.add(lblEvento);
//		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(277, 113, 390, 2);
		contentPane.add(separator);
		
	}
}
