package com.platzi.mensajes_app.datababe;

import java.sql.Connection;

import java.sql.DriverManager;

public class Conexion {
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			//jdbc:mysql://localhost:3306/mensajes_app","root","1234

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mesajes_app","root","");
			if(connection != null) {
				System.out.println("exit");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
		return connection;
		
	}

}
/*class Main { //manera para conectar la  BD desde el metodo principal
	
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		try (Connection cnx = conexion.getConnection()){
			
		} catch (Exception e) {
		 System.out.println(e); 
		}
	}
}
*/
