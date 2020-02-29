package Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Conector;
import Modelo.Usuario;
import Vista.PanelModificar;
import Vista.Ventana;

public class ButtonController implements ActionListener{

	Conector conector;
	Ventana ventana;
	PanelModificar modificarFrame;
	
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
				modificarFrame = new PanelModificar(this,u);
				ventana.getPanelCentral().setUsuario(u);
				ventana.getPanelCentral().remove(ventana.getPanelCentral().getLoginFrame());
				ventana.getPanelCentral().add(modificarFrame, "modificar");
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos de inicio de sesión incorrectos");
			}
			resetLogin();
			break;
			
		case "resetLogin":
			resetLogin();
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION RESET FUNCIONA");
			break;
			
		case "crearCuenta":
			ventana.getPanelCentral().getLoginFrame().getLblMessage().setText("ACCION CREAR");
			u = new Usuario();
			u.setNombre(ventana.getPanelCentral().getLoginFrame().getTxtName().getText());
			u.setPassword(ventana.getPanelCentral().getLoginFrame().getTxtPass().getText());
			
			if(u.getPassword().equals("") || u.getNombre().equals("")) {
				JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
			}
			else if(!conector.existeUsername(u)) {
				if(conector.registrarUsuario(u)) {
					JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
					resetLogin();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al registrar usuario");
					resetLogin();
				}
			} 
			else {
				JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe, introduce otro");
			}
			resetLogin();
			break;
			
		case "modificar":
			modificarFrame.getLblMessage().setText("Accion Modificar");
			String newPass = modificarFrame.getTxtPass().getText();
			String newPass2 = modificarFrame.getTxtPass2().getText();
			//comprobar 2 campos iguales
			if(!newPass.equals(newPass2)){
				JOptionPane.showMessageDialog(null, "Los campos no coinciden");
			}
			else {
				u = new Usuario();
				u.setNombre(modificarFrame.getLblName().getText());
				u.setPassword(newPass);
				if(!conector.modificarUsuario(u)) {
					JOptionPane.showMessageDialog(null, "Error al modificar la contraseña");
				}
				else {
					JOptionPane.showMessageDialog(null, "Has cambiado la contraseña");
					
				}
			}
			resetModificar();
			
			break;
		}
		
	}
	
	//encapsulacion metodo limpiar login
	public void resetLogin() {
		ventana.getPanelCentral().getLoginFrame().getTxtName().setText("");
		ventana.getPanelCentral().getLoginFrame().getTxtPass().setText("");
	}
	
	public void resetModificar() {
		modificarFrame.getTxtPass().setText("");
		modificarFrame.getTxtPass2().setText("");
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
