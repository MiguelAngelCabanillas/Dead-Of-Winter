package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class IniciarSesion {
	Conexion conexion = null;
	
	public IniciarSesion(Conexion cx) {
		conexion = cx;
	}
	
	public void InicioSesion() {
		
		try {
			Connection cn = conexion.conectar();
			PreparedStatement pstm = cn.prepareStatement("SELECT * FROM Usuarios WHERE `ID Usuarios` = ?");
			
			//PEDIR NOMBRE DE USUARIO
			boolean encontrado = false;
			Scanner sc = new Scanner(System.in);
			do {
				System.out.print("Introduzca nombre de usuario: ");
				String usuarioIngresado = sc.nextLine();
				System.out.print("Introduzca contraseña: ");
				String contraseñaIngresada = sc.nextLine();
				pstm.setString(1,usuarioIngresado);
				ResultSet rs = pstm.executeQuery();
				if (rs.next()) {
					String usuarioBD = rs.getString(1);
					String contraseñaBD = rs.getString(2);
					if (usuarioIngresado.equalsIgnoreCase(usuarioBD) && contraseñaIngresada.equalsIgnoreCase(contraseñaBD)){
							encontrado = true;
					}
				}
				
				if (!encontrado) {
					System.out.println("Error, usuario y/o contraseña incorrecto \n");
				}
			}while(!encontrado);	
			sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
