package Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Conector;
import Vista.Ventana;

public class ButtonController implements ActionListener{

	Conector conector;
	Ventana ventana;
	
	public ButtonController(Conector conex) {
		super();
		this.conector = conex;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String accion = arg0.getActionCommand();
		//obtenemos el layout actual (la parte central, que es la que modificaremos)
		CardLayout layout = (CardLayout) ventana.getPanelCentral().getLayout();
		
		switch(accion) {
		
		case "login":
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION LOGIN");
			break;
		case "resetLogin":
			ventana.getPanelCentral().getLoginFrame().getTxtName().setText("");
			ventana.getPanelCentral().getLoginFrame().getTxtPass().setText("");
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION RESET FUNCIONA");
			break;
		case "crearCuenta":
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION CREAR");
			break;
		}
		
	}

	
	
	public Conector getConector() {
		return conector;
	}

	public void setConector(Conector conector) {
		this.conector = conector;
	}

	public Ventana getVentana() {
		return ventana;
	}

	public void setVentana(Ventana ventana) {
		this.ventana = ventana;
	}
	
	

}
