package Gui;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PruebaFile {

	public static List<List<List<Point>>> leerFicheroMapa(String ruta) {
		try {
			
			List<List<Point>> sup = new ArrayList<>();
			List<List<Point>> zomb = new ArrayList<>();
			List<Point> moral = new ArrayList<>();
			List<Point> ronda = new ArrayList<>();
			List<List<Point>> ruido = new ArrayList<>();
			
			List<List<List<Point>>> locFinales = new ArrayList<>();
			int i = 0;
			
			Scanner sc = new Scanner(new File(ruta));
			String mensaje;
			mensaje = sc.nextLine();
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////			Supervivientes			/////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////
			mensaje = sc.nextLine();
			mensaje = sc.nextLine();
			while(!mensaje.equals("loc_zomb")) {

				 List<Point> ubicaciones = new ArrayList<Point>();
				 
				 while(mensaje.length() >= 5) {
					 String[] split = mensaje.split(",");
					 System.out.println(split[0]);
					 System.out.println(split[1]);
					 ubicaciones.add(new Point(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim())));
					 if(sc.hasNextLine()) {
					 mensaje = sc.nextLine();
					 }
				 }
				 mensaje = sc.nextLine();
				 sup.add(ubicaciones);
			 }
			System.out.println(sup);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////			Zombies					/////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////			
				
			mensaje = sc.nextLine();
			mensaje = sc.nextLine();
			while(!mensaje.equals("loc_moral")) {

				 List<Point> ubicaciones = new ArrayList<Point>();
				 
				 while(mensaje.length() >= 5) {
					 String[] split = mensaje.split(",");
					 System.out.println(split[0]);
					 System.out.println(split[1]);
					 ubicaciones.add(new Point(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim())));
					 if(sc.hasNextLine()) {
					 mensaje = sc.nextLine();
					 }
				 }
				 mensaje = sc.nextLine();
				 zomb.add(ubicaciones);
			 }
			System.out.println(zomb);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////			Moral					/////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////			
				
			mensaje = sc.nextLine();
			while(!mensaje.equals("loc_ronda")) {
				 
				 while(mensaje.length() >= 5) {
					 String[] split = mensaje.split(",");
					 System.out.println(split[0]);
					 System.out.println(split[1]);
					 moral.add(new Point(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim())));
					 if(sc.hasNextLine()) {
					 mensaje = sc.nextLine();
					 }
				 }
				 mensaje = sc.nextLine();
			 }
			System.out.println(moral);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////			Ronda					/////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////	
			
			mensaje = sc.nextLine();
			while(!mensaje.equals("loc_ruido")) {
				 
				 while(mensaje.length() >= 5) {
					 String[] split = mensaje.split(",");
					 System.out.println(split[0]);
					 System.out.println(split[1]);
					 ronda.add(new Point(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim())));
					 if(sc.hasNextLine()) {
					 mensaje = sc.nextLine();
					 }
				 }
				 mensaje = sc.nextLine();
			 }
			System.out.println(ronda);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////			Ruido					/////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			mensaje = sc.nextLine();
			mensaje = sc.nextLine();
			while(!mensaje.equals("END")) {

				 List<Point> ubicaciones = new ArrayList<Point>();
				 
				 while(mensaje.length() >= 5) {
					 System.out.println(mensaje);
					 String[] split = mensaje.split(",");
					 ubicaciones.add(new Point(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim())));
					 if(sc.hasNextLine()) {
					 mensaje = sc.nextLine();
					 }
				 }
				 if(sc.hasNextLine()) {
				 mensaje = sc.nextLine();
				 }

				 ruido.add(ubicaciones);
			 }
			System.out.println(ruido);
			
			List<List<Point>> listaProp = new ArrayList<>();
			listaProp.add(moral);
			listaProp.add(ronda);
			
			locFinales.add(sup);
			locFinales.add(zomb);
			locFinales.add(listaProp);
			locFinales.add(ruido);
			
			return locFinales;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
