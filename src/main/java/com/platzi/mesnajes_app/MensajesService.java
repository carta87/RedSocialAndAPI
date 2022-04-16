package com.platzi.mesnajes_app;

import java.util.Scanner;

import com.platzi.mensajes_app.Model.Mensaje;
import com.platzi.mensajes_app.dao.MensajeDao;

public class MensajesService {
	// metodos que va realizar el menu que se va conectar con capa dao para enviar a la DB
	
	public static void crearMensaje() {
		Scanner sc = new Scanner(System.in);
		System.out.println("escribe tu mensaje");
		String mensaje = sc.nextLine();
		
		System.out.println("escribe tu nombre");
		String autor = sc.nextLine();
		
		Mensaje registro =  new Mensaje();
		registro.setMensaje(mensaje);
		registro.setAutorMensaje(autor);
		
		MensajeDao.crearMensajeDB(registro);
	}
	public static void listaMesnaje() {
		MensajeDao.leerMensajeDB();
		
	}
	public static void editarMensaje() {
		Scanner sc =  new Scanner(System.in);
		System.out.println("escribe tu nuevo mesnaje");
		String mensaje = sc.nextLine();
		System.out.println("indica el id del mesnaje a editar");
		int id_mensaje = sc.nextInt();
		
		Mensaje actualizacion =  new Mensaje();
		actualizacion.setIdMensaje(id_mensaje);
		actualizacion.setMensaje(mensaje);
		
		MensajeDao.actualizarMensajeDB(actualizacion);
		
	}
	public static void borrarMensaje() {
		Scanner sc  = new Scanner(System.in);
		System.out.println("indica el ID del mensaje que desea borrar ");
		int id_mensaje = sc.nextInt();
		
		MensajeDao.borrarMensajeDB(id_mensaje);
	}

}
