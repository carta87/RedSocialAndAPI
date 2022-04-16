package com.platzi.mensajes_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platzi.mensajes_app.Model.Mensaje;
import com.platzi.mensajes_app.datababe.Conexion;

public class MensajeDao {
	// Data Access Object: los metodos que nos permitiran el acceso a la base de
	// datos

	public static void crearMensajeDB(Mensaje mensaje) {
		Conexion dbConection = new Conexion();

		try (Connection conexion = dbConection.getConnection()) {
			PreparedStatement ps = null;

			// segundo try para generar la insersion de los datos
			try {
				String query = "INSERT INTO `mesnajes` (`mensaje`, `autor_mensaje`) VALUES (?,?)";
				ps = conexion.prepareStatement(query);// preparar sentencia o consulta
				ps.setString(1, mensaje.getMensaje());
				ps.setString(2, mensaje.getAutorMensaje());
				ps.executeUpdate();// se encarga de ejecutar
				System.out.println("mensaje Creado");

			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);

		}

	}

	public static void leerMensajeDB() {
		Conexion dbConexion = new Conexion();

		PreparedStatement ps = null;
		ResultSet rs = null;// permite traer los datos en filas para poder porcesarlos

		try (Connection connection = dbConexion.getConnection()) {

			String query = "SELECT * FROM  mesnajes";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();// no tienen transacion solo solicita datos

			while (rs.next()) {// mientras tenga datos va recorrer: siguiente
				System.out.println("ID: " + rs.getInt("id_mesnaje"));
				System.out.println("Mensaje: " + rs.getString("mensaje"));
				System.out.println("Autor " + rs.getString("autor_mensaje"));
				System.out.println("Fecha Mensaje" + rs.getString("fecha_mensaje"));
				System.out.println("--------------------------");

			}
		} catch (SQLException e) {
			System.out.println("no se pudieron recuperar los mensajes");
			System.out.println(e);
		}

	}

	public static void borrarMensajeDB(int idMensaje) {

		Conexion dbConexion = new Conexion();

		try (Connection connection = dbConexion.getConnection()) {
			PreparedStatement ps = null;
			try {
				String query = "DELETE FROM mesnajes WHERE id_mesnaje =?";
				ps = connection.prepareStatement(query);
				ps.setInt(1, idMensaje);// parametro que se envia en la consulta es idMensaje
				ps.executeUpdate();
				System.out.println("el mensaje ha sido borrado ");

			} catch (SQLException e) {

				System.out.println(e);
				System.out.println("no se pudo lograr borrar la execpion");
			}

		} catch (SQLException e) {
			System.out.println(e);

		}

	}

	public static void actualizarMensajeDB(Mensaje mensaje) {
		Conexion db_conexion = new Conexion();
		PreparedStatement ps = null;
		try (Connection connection = db_conexion.getConnection()){
			try {

				String query ="UPDATE mesnajes SET mensaje = ? WHERE id_mesnaje = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1, mensaje.getMensaje());
				ps.setInt(2, mensaje.getIdMensaje());
				ps.executeUpdate();
				System.out.println("se ha realizado la actualizacion ");
			
			} catch (SQLException e) {
			System.out.println(e);
			System.out.println("no se pudo ectualizar el mesnaje");
			}

		}catch(

	SQLException e)
	{
		System.out.println(e);

	}
}

}
