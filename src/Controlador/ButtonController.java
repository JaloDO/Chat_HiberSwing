package Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Conector;
import Modelo.Mensaje;
import Modelo.Usuario;
import Vista.PanelMensajes;
import Vista.PanelModificar;
import Vista.Ventana;

public class ButtonController implements ActionListener{

	Conector conector;
	Ventana ventana;
	PanelModificar modificarFrame;
	PanelMensajes mensajesFrame;
	
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
				//Quitar JPanel de login de PanelCentral
				ventana.getPanelCentral().remove(ventana.getPanelCentral().getLoginFrame());
				//Crear nuevo PanelMensajes y asignarlo a PanelCentral
				mensajesFrame = new PanelMensajes(this);
				ventana.getPanelCentral().setMensajesFrame(mensajesFrame);
				ventana.getPanelCentral().add(ventana.getPanelCentral().getMensajesFrame(), "mensajes");
				ventana.getPanelCentral().validate();
				ventana.getPanelCentral().repaint();
				//Actualizar tabla de mensajes
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(u.getRecibidos());
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos de inicio de sesion incorrectos");
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

				JOptionPane.showMessageDialog(null, "No puede haber campos vacios");

			}
			else if(!conector.existeUsername(u)) {
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
			resetLogin();
			break;
			
		case "modificarContrasena":
			modificarFrame.getLblMessage().setText("Accion Modificar");
			String newPass = modificarFrame.getTxtPass().getText();
			String newPass2 = modificarFrame.getTxtPass2().getText();
			//comprobar 2 campos iguales
			if(!newPass.equals(newPass2)){
				JOptionPane.showMessageDialog(null, "Los campos no coinciden");
			}
			else {
				u = ventana.getPanelCentral().getUsuario();
				u.setPassword(newPass);
				if(!conector.modificarUsuario(u)) {
					JOptionPane.showMessageDialog(null, "Error al modificar la contrase�a");
				}
				else {
					JOptionPane.showMessageDialog(null, "Has cambiado la contrase�a");

				}
			}
			resetModificar();
			
			break;
			
		case "mensajes":
			ventana.getPanelCentral().remove(modificarFrame);
			ventana.getPanelCentral().add(mensajesFrame, "mensajes");
			break;
			
		case "modificar":
			modificarFrame = new PanelModificar(this, ventana.getPanelCentral().getUsuario());
			ventana.getPanelCentral().setModificarFrame(modificarFrame);
			ventana.getPanelCentral().remove(ventana.getPanelCentral().getMensajesFrame());
			ventana.getPanelCentral().add(ventana.getPanelCentral().getModificarFrame());
			ventana.getPanelCentral().validate();
			ventana.getPanelCentral().repaint();
			break;
			
		case "cambiarMensajes":
			String btn = ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().getText();
			u = ventana.getPanelCentral().getUsuario();
			if(btn.equals("Ver Enviados")) {
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(u.getEnviados());
				ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().setText("Ver Recibidos");
			}
			else {
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(u.getRecibidos());
				ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().setText("Ver Enviados");
			}
			ventana.getPanelCentral().validate();
			ventana.getPanelCentral().repaint();
			break;
			
		case "enviarMensaje":
			//obtenemos el emisor
			Usuario emisor = ventana.getPanelCentral().getUsuario();
			//creamos el mensaje
			Mensaje m = new Mensaje();
			m.setEmisor(emisor);
			//obtenemos el receptor
			Usuario receptor = new Usuario();
			receptor.setNombre(ventana.getPanelCentral().getMensajesFrame().getTxtDestino().getText());
			receptor = conector.obtenerUsuario(receptor);
			if(receptor==null) {
				JOptionPane.showMessageDialog(null, "Error, el destinatario no existe");
			}
			else {
				//rellenamos el objeto mensaje
				m.setReceptor(receptor);
				m.setContenido(ventana.getPanelCentral().getMensajesFrame().getTxtContenido().getText());
				if(!conector.enviarMensaje(m)) {
					JOptionPane.showMessageDialog(null, "Error al enviar el mensaje");
				}
				else {
					JOptionPane.showMessageDialog(null, "Mensaje enviado con �xito");
					ventana.getPanelCentral().validate();
					ventana.getPanelCentral().repaint();
				}
			}
			resetMensaje();
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
	
	public void resetMensaje() {
		ventana.getPanelCentral().getMensajesFrame().getTxtDestino().setText("");
		ventana.getPanelCentral().getMensajesFrame().getTxtContenido().setText("");
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
