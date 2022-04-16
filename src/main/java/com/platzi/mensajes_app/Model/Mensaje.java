package com.platzi.mensajes_app.Model;

public class Mensaje { // esctutura basica o POJO 

	private int idMensaje;
	private String mensaje;
	private String autorMensaje;
	private String fechaMensaje;
	
	public Mensaje() {
	}
	
	
	public Mensaje(int idMensaje, String mensaje, String autorMensaje, String fechaMensaje) {
		super();
		this.idMensaje = idMensaje;
		this.mensaje = mensaje;
		this.autorMensaje = autorMensaje;
		this.fechaMensaje = fechaMensaje;
	}


	public int getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getAutorMensaje() {
		return autorMensaje;
	}
	public void setAutorMensaje(String autorMensaje) {
		this.autorMensaje = autorMensaje;
	}
	public String getFechaMensaje() {
		return fechaMensaje;
	}
	public void setFechaMensaje(String fechaMensaje) {
		this.fechaMensaje = fechaMensaje;
	}
	
	
	
}
