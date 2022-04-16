package com.platzi.mesnajes_app;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.platzi.mensajes_app.Model.Gatos;
import com.platzi.mesnajes_app.service.GatosService;

public class InicioGatos {
	public static void main(String[] args) throws IOException {

		int opcionMenu = -1;
		String[] botones = { "1. ver gatos", "2. ver Favoritos ", "3. salir" };

		do {
			String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu Principal",
					JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

			// validamos que opcion seleciona el usuario
			for (int i = 0; i < botones.length; i++) {
				if (opcion.equals(botones[i])) {
					opcionMenu = i;
				}
				
			switch (opcionMenu) {

				case 0:
					GatosService.verGatos();
					break;
				case 1:
					Gatos gatos =  new Gatos();
					
					GatosService.verFavoritos(gatos.getApikey());
				default:
					break;
				}

			}
		} while (opcionMenu != 1);

	}
}
