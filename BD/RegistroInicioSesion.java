package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RegistroInicioSesion {
	private static Connection connect;
	
	public RegistroInicioSesion(Conexion cn) {
		connect = cn.conectar();
	}
	
	public void registrarUsuario(String usr, String pass) {
		try {
			//Comprueba que el usuario no estaba registrado
			PreparedStatement pst = connect.prepareStatement("SELECT * FROM Usuarios WHERE `ID Usuarios` = ?");
			pst.setString(1, usr);
			ResultSet rs = pst.executeQuery();
			boolean esta = false;
			if(rs.next()) {
				esta = true;
			}
			
			if (!esta) {
				PreparedStatement ps = connect.prepareStatement("INSERT INTO `Usuarios` (`ID Usuarios`, `Contraseña`) VALUES (?,?)");
				ps.setString(1, usr);
				ps.setString(2, pass);
				int res = ps.executeUpdate();
				if(res > 0) {
					JOptionPane.showMessageDialog(null, "Usuario " + usr + " registrado con exito");
				} else {
					JOptionPane.showMessageDialog(null, "Error registrando el usuario");
				}
				ps.close();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario ya se encuentra en la Colonia");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
