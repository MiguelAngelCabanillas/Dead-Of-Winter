package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class IniciarSesion {
	Conexion conexion = null;
	
	public IniciarSesion(Conexion cx) {
		conexion = cx;
	}
	
	public boolean InicioSesion(String usuarioIngresado, String contrase�aIngresada) {
		boolean encontrado = false;
		try {
			Connection cn = conexion.conectar();
			PreparedStatement pstm = cn.prepareStatement("SELECT * FROM Usuarios WHERE `ID Usuarios` = ?");
			
			//PEDIR NOMBRE DE USUARIO
			Scanner sc = new Scanner(System.in);
//				System.out.print("Introduzca nombre de usuario: ");
//				String usuarioIngresado = sc.nextLine();
//				System.out.print("Introduzca contrase�a: ");
//				String contrase�aIngresada = sc.nextLine();
				pstm.setString(1,usuarioIngresado);
				ResultSet rs = pstm.executeQuery();
				String usuarioBD = "";
				if (rs.next()) {
					usuarioBD = rs.getString(1);
					String contrase�aBD = rs.getString(2);
					if (usuarioIngresado.equalsIgnoreCase(usuarioBD) && contrase�aIngresada.equalsIgnoreCase(contrase�aBD)){
							encontrado = true;
					}
				}
				
				if (!encontrado) {
					JOptionPane.showMessageDialog(null, "Error, usuario y/o contrase�a incorrecto");
				}else {
					JOptionPane.showMessageDialog(null, "Bienvenido a la Colonia " + usuarioBD);
				}
			sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encontrado;
	}
}
