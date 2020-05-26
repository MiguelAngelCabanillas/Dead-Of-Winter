import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Server.*;

public class MainCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente client = new Cliente();
		String peticion;
		try {
				ClientReader cr = new ClientReader(client.getSocket());
				Scanner sc = new Scanner(System.in);
			while(true) {
				if(sc.hasNextLine()) {
					cr.hacerPeticionAlServidor(sc.nextLine());
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
