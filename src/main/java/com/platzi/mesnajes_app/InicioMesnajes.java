package com.platzi.mesnajes_app;

import java.sql.Connection;
import java.util.Scanner;

public class InicioMesnajes {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int opcion =0;
		
		do {
			System.out.println("---------------------- ");
			System.out.println("Alicacion de mesnajes ");
			System.out.println("1. crear mensaje");
			System.out.println("2. listar mensajes");
			System.out.println("3. editar mensaje");
			System.out.println("4. borrar mensaje" );
			System.out.println("5 Salir menu");
			
			//leeemos la opcion del usuario
			opcion = sc.nextInt();
			
			switch(opcion) {
		       case 1:
			        MensajesService.crearMensaje();    	
			     	break;
			    case 2:
			    	MensajesService.listaMesnaje();
			    	break;
			    case 3:
			    	MensajesService.editarMensaje();
			    	break;
			    case 4:
			    	MensajesService.borrarMensaje();
			    	break;
			    default:
			    	break;
		    
			}
			
		}while(opcion != 5);
		
		
	
		
		

	}

}
/*
class Main { //manera para conectar la  BD desde el metodo principal
	
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		try (Connection cnx = conexion.getConnection()){
			
		} catch (Exception e) {
		 System.out.println(e); 
		}
	}
}
*/