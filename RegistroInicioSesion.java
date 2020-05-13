import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroInicioSesion {
	private static Connection connect;
	
	public RegistroInicioSesion(Connection cn) {
		connect = cn;
	}
	
	public void registrarUsuario(String usr, String pass) {
		Statement stm;
		try {
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Usuarios");
			
			//Comprueba que el usuario no estaba registrado
			boolean esta = false;
			String us;
			while(rs.next() && !esta){
				us = rs.getString(1);
				if(us.equalsIgnoreCase(usr)) {
					esta = true;
				}
			}
			rs.close();
			stm.close();
			
			if (!esta) {
				PreparedStatement ps = connect.prepareStatement("INSERT INTO `Usuarios` (`ID Usuarios`, `Contraseña`) VALUES (?,?)");
				ps.setString(1, usr);
				ps.setString(2, pass);
				int res = ps.executeUpdate();
				if(res > 0) {
					System.out.println("Usuario " + usr + " registrado con éxito");
				} else {
					System.out.println("Error registrando el usuario en la base de datos");
				}
				ps.close();
			} else {
				System.out.println("ERROR: El usuario " + usr + " ya estaba registrado.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
