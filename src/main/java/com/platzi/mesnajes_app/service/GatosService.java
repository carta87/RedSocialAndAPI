package com.platzi.mesnajes_app.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.platzi.mensajes_app.Model.Gatos;
import com.platzi.mensajes_app.Model.GatosFav;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class GatosService {

	public static void verGatos() throws IOException {
		// trayendo los datos de la api
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").method("GET", null)
				.build();
		Response response = client.newCall(request).execute();

		String elJson = response.body().string();

		// cortar los corchetes
		elJson = elJson.substring(1, elJson.length() - 1);
		// elJson = elJson.substring(0, elJson.length() - 1);

		// crear un objecto del tipo Gson
		Gson gson = new Gson();
		Gatos gatos = gson.fromJson(elJson, Gatos.class);

		// redimencionar la imagen en caso de necesitar
		Image image = null;
		try {
			URL url = new URL(gatos.getUrl());
			image = ImageIO.read(url);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.addRequestProperty("User-Agent", "");
			BufferedImage bufferedImage = ImageIO.read(http.getInputStream());

			ImageIcon fondoGato = new ImageIcon(image);

			if (fondoGato.getIconWidth() > 800) {
				// redimencionamos
				Image fondo = fondoGato.getImage();
				Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
				fondoGato = new ImageIcon(modificada);
			}

			String menu = "Opciones: \n 1. Ver otra imagen \n 2. Favorito \n 3. volver \n";

			String[] botones = { " ver otra imagen ", " favorito ", "volver " };
			String idGato = gatos.getId();

			String option = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE,
					fondoGato, botones, botones[0]);

			int seleccion = -1;
			// validamos la opcion que selecciona el usuario
			for (int i = 0; i < botones.length; i++) {

				if (option.equals(botones[i])) {
					seleccion = i;
				}
				switch (seleccion) {
				case 0:
					verGatos();
					break;
				case 1:
					favoritoGato(gatos);
					break;
				default:
					break;
				}
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void favoritoGato(Gatos gatos) {
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\"" + gatos.getId() + "\"\r\n}");
			Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites").method("POST", body)
					.addHeader("Content-Type", "application/json").addHeader("x-api-key", gatos.getApikey()).build();
			Response response = client.newCall(request).execute();
		} catch (IOException e) {
			System.out.println(e);
		}
	};

	public static void verFavoritos(String apikey) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites").method("GET", null)
				.addHeader("x-api-key", apikey).build();
		Response response = client.newCall(request).execute();

		// guardamos en el String la respuesta de la consulta GET
		String elJosn = response.body().string();

		// creamos el objecto Gson
		Gson gson = new Gson();

		GatosFav[] gatosArray = gson.fromJson(elJosn, GatosFav[].class);

		if (gatosArray.length > 0) {
			// se buscara una imagen de forma aleatoria para mostrarla
			int min = 1;
			int max = gatosArray.length;
			int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
			int indice = aleatorio - 1;

			GatosFav gatoFav = gatosArray[indice];

			// redimencionar la imagen en caso de necesitar
			Image image = null;
			try {
				URL url = new URL(gatoFav.getImage().getUrl());
				image = ImageIO.read(url);

				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.addRequestProperty("User-Agent", "");
				BufferedImage bufferedImage = ImageIO.read(http.getInputStream());

				ImageIcon fondoGato = new ImageIcon(image);

				if (fondoGato.getIconWidth() > 800) {
					// redimencionamos
					Image fondo = fondoGato.getImage();
					Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
					fondoGato = new ImageIcon(modificada);
				}

				String menu = "Opciones: \n 1. Ver otra imagen \n 2. eliminar favorito \n 3. volver \n";

				String[] botones = { " ver otra imagen ", " favorito ", "volver " };
				String idGato = gatoFav.getId();

				String option = (String) JOptionPane.showInputDialog(null, menu, idGato,
						JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

				int seleccion = -1;
				// validamos la opcion que selecciona el usuario
				for (int i = 0; i < botones.length; i++) {

					if (option.equals(botones[i])) {
						seleccion = i;
					}
					switch (seleccion) {
					case 0:
						verFavoritos(apikey);
						break;
					case 1:
						borrarFavorito(gatoFav);
						break;
					default:
						break;
					}
				}

			} catch (IOException e) {
				System.out.println(e);
			}

		}

	}

	public static void borrarFavorito(GatosFav gatoFav) throws IOException {
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("text/plain");
			RequestBody body = RequestBody.create(mediaType, "");
			Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites/"+gatoFav.getId()+"")
					.method("DELETE", body).addHeader("x-api-key", gatoFav.getApiKey()).build();
			Response response = client.newCall(request).execute();
	
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
