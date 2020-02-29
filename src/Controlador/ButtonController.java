package Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Conector;
import Modelo.Usuario;
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
		
		Usuario u;
		
		switch(accion) {
		
		case "login":
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION LOGIN");
			u = new Usuario();
			u.setNombre(ventana.getPanelCentral().getLoginFrame().getTxtName().getText());
			u.setPassword(ventana.getPanelCentral().getLoginFrame().getTxtPass().getText());
			u = conector.iniciarSesion(u);
			if(u!=null) {
				ventana.getPanelCentral().setUsuario(u);
				ventana.getPanelCentral().remove(ventana.getPanelCentral().getLoginFrame());
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos de inicio de sesión incorrectos");
			}
			
			break;
		case "resetLogin":
			ventana.getPanelCentral().getLoginFrame().getTxtName().setText("");
			ventana.getPanelCentral().getLoginFrame().getTxtPass().setText("");
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION RESET FUNCIONA");
			break;
		case "crearCuenta":
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION CREAR");
			u = new Usuario();
			u.setNombre(ventana.getPanelCentral().getLoginFrame().getTxtName().getText());
			u.setPassword(ventana.getPanelCentral().getLoginFrame().getTxtPass().getText());
			if(!conector.existeUsername(u)) {
				if(conector.registrarUsuario(u)) {
					JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al registrar usuario");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe, introduce otro");
			}
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
