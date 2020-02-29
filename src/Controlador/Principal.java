package Controlador;

import Modelo.Conector;

import Vista.Ventana;

public class Principal {

	
	public static void main(String[] args) {
		
		//Ventana de la aplicacion
		Ventana ventana = null;
		//Acceso a BD
		Conector chat = new Conector();
				
		if(chat.getEm()!=null) {
			//Controlador de acciones de botones
			ButtonController acciones = new ButtonController(chat);
			// Apertura de la ventana principal
			ventana = new Ventana(acciones);
			acciones.setVentana(ventana);
		}
		else {
			
		}
	}
}
