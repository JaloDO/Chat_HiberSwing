package Controlador;

import Modelo.Conector;
import Vista.Ventana;

public class Principal {

	
	public static void main(String[] args) {
		
		//Ventana de la aplicaci�n
		//Ventana v = new Ventana();
		//v.setVisible(true);
		
		//Componente de acceso y manipulaci�n a la BD Biblioteca
		Conector chat = new Conector();
				
		if(chat.getEm()!=null) {
			
		}
		
		
	}
}
