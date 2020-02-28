package Controlador;

import Modelo.Conector;
import Vista.Ventana;

public class Principal {

	
	public static void main(String[] args) {
		
		//Ventana de la aplicación
		//Ventana v = new Ventana();
		//v.setVisible(true);
		
		//Componente de acceso y manipulación a la BD Biblioteca
		Conector chat = new Conector();
				
		if(chat.getEm()!=null) {
			
		}
		
		
	}
}
