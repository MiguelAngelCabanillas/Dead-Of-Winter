package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ProgressBar;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class BarraProgreso extends JFrame {

	private JPanel contentPane;
	private Timer t;
	private ActionListener ac;
	private int x = 0;
	public JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarraProgreso frame = new BarraProgreso();
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
	public BarraProgreso() {
		//initComponents();
		this.setLocationRelativeTo(null);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(164, 191, 297, 30);
		contentPane.add(progressBar);
		progressBar.setStringPainted(true);
		
		JLabel lblCargando = new JLabel("Cargando...");
		lblCargando.setForeground(Color.WHITE);
		lblCargando.setFont(new Font("Castellar", Font.BOLD, 23));
		lblCargando.setBounds(224, 141, 211, 37);
		contentPane.add(lblCargando);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 607, 384);
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Varios/fondoBProgreso.jpg"));
		Image img = ima.getImage().getScaledInstance(607, 384, java.awt.Image.SCALE_SMOOTH); 
		lblFondo.setIcon(new ImageIcon(img));
		contentPane.add(lblFondo);
		
		
		
		ac = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (progressBar.getValue() == 100) {
					dispose();
					t.stop();
				}
			}
		};
		
		t = new Timer(200, ac);
		t.start();
	}
	
	
	public void setValor(int x) {
		progressBar.setValue(x);
	}
	
	public void setString(String x) {
		progressBar.setString(x);
	}
}
