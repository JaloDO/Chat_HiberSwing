package Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

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
			u = new Usuario();
			u.setNombre(ventana.getPanelCentral().getLoginFrame().getTxtName().getText());
			u.setPassword(ventana.getPanelCentral().getLoginFrame().getTxtPass().getText());
			u = conector.iniciarSesion(u);
			List<Mensaje>rev = conector.mensajesRecibidos(u);
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
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(rev);
				ventana.getTitulo().setText("EASY CHAT - MENSAJES RECIBIDOS");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos de inicio de sesion incorrectos");
			}
			resetLogin();
			break;
			
		case "resetLogin":
			resetLogin();
			break;
			
		case "crearCuenta":
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
			String newPass = modificarFrame.getTxtPass().getText();
			String newPass2 = modificarFrame.getTxtPass2().getText();
			//comprobar 2 campos iguales
			if(newPass.equals("") || newPass2.equals("")) {
				JOptionPane.showMessageDialog(null, "Error, No puede haber campos vacios");
			}
			else {
				if(!newPass.equals(newPass2)){
					JOptionPane.showMessageDialog(null, "Los campos no coinciden");
				}
				else {
					u = ventana.getPanelCentral().getUsuario();
					u.setPassword(newPass);
					if(!conector.modificarUsuario(u)) {
						JOptionPane.showMessageDialog(null, "Error al modificar la contraseña");
					}
					else {
						JOptionPane.showMessageDialog(null, "Has cambiado la contraseña");

					}
				}
			}
			
			resetModificar();
			
			break;
			
		case "mensajes":
			ventana.getPanelCentral().remove(modificarFrame);
			ventana.getPanelCentral().add(mensajesFrame, "mensajes");
			ventana.getTitulo().setText("EASY CHAT - MENSAJES RECIBIDOS");
			break;
			
		case "modificar":
			modificarFrame = new PanelModificar(this, ventana.getPanelCentral().getUsuario());
			ventana.getPanelCentral().setModificarFrame(modificarFrame);
			ventana.getPanelCentral().remove(ventana.getPanelCentral().getMensajesFrame());
			ventana.getPanelCentral().add(ventana.getPanelCentral().getModificarFrame());
			ventana.getTitulo().setText("EASY CHAT - MODIFICAR");
			ventana.getPanelCentral().validate();
			ventana.getPanelCentral().repaint();
			break;
			
		case "cambiarMensajes":
			String btn = ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().getText();
			u = ventana.getPanelCentral().getUsuario();
			
			if(btn.equals("Ver Enviados")) {
				//cambiamos la tabla 
				List<Mensaje>env = conector.mensajesEnviados(u);
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(env);
				ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().setText("Ver Recibidos");
				ventana.getPanelCentral().getMensajesFrame().getBtnBorrar().setVisible(true);
				ventana.getTitulo().setText("EASY CHAT - MENSAJES ENVIADOS");
			}
			else {
				rev = conector.mensajesRecibidos(u);
				ventana.getPanelCentral().getMensajesFrame().actualizarTabla(rev);
				ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().setText("Ver Enviados");
				ventana.getPanelCentral().getMensajesFrame().getBtnBorrar().setVisible(false);
				ventana.getTitulo().setText("EASY CHAT - MENSAJES RECIBIDOS");
			}
			ventana.getPanelCentral().validate();
			ventana.getPanelCentral().repaint();
			break;
			
		case "enviarMensaje":
			String boton= ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().getText();
			//obtenemos el emisor
			Usuario emisor = ventana.getPanelCentral().getUsuario();
			//creamos el mensaje
			Mensaje m = new Mensaje();
			m.setEmisor(emisor);
			//obtenemos el receptor
			Usuario receptor = new Usuario();
			receptor.setNombre(ventana.getPanelCentral().getMensajesFrame().getTxtDestino().getText());
			receptor = conector.obtenerDestinatario(receptor);
			if(receptor==null) {
				JOptionPane.showMessageDialog(null, "Error, el destinatario no existe");
			}
			else {
				//rellenamos el objeto mensaje
				m.setReceptor(receptor);
				m.setContenido(ventana.getPanelCentral().getMensajesFrame().getTxtContenido().getText());
				m.setFecha(new Date());
				if(!conector.enviarMensaje(m)) {
					JOptionPane.showMessageDialog(null, "Error al enviar el mensaje");
				}
				else {
					u = ventana.getPanelCentral().getUsuario();
					List<Mensaje>env = null;
					if(boton.equals("Ver Recibidos")) {
						env = conector.mensajesEnviados(u);
					}
					else {
						env = conector.mensajesRecibidos(u);
					}
					JOptionPane.showMessageDialog(null, "Mensaje enviado con exito");
					ventana.getPanelCentral().getMensajesFrame().actualizarTabla(env);
					ventana.getPanelCentral().validate();
					ventana.getPanelCentral().repaint();
				}
			}
			resetMensaje();
			break;
			
		case "borrar":
			int id = ventana.getPanelCentral().getMensajesFrame().getId_mensaje();
			JOptionPane.showMessageDialog(null, "Accion borrar Mensaje, indice de la tabla: "+id);
			if(ventana.getPanelCentral().getMensajesFrame().getBtnCambiar().getText().equals("Ver Enviados")) {
				JOptionPane.showMessageDialog(null, "No se pueden borrar los mensajes recibidos");
			}
			else{

				Mensaje mns = new Mensaje();
				mns.setCodigo(id);
				mns = conector.existeMensaje(mns.getCodigo());
				JOptionPane.showMessageDialog(null, "codigo del mensaje: "+mns.getCodigo());
			
				if(mns==null) {

					JOptionPane.showMessageDialog(null, "El mensaje no existe");
				}
				else {
					if(!conector.borrarMensaje(mns)) {
						JOptionPane.showMessageDialog(null, "Error al borrar el mensaje");
					}
					else {
						u = ventana.getPanelCentral().getUsuario();
						List<Mensaje>env = conector.mensajesEnviados(u);
						JOptionPane.showMessageDialog(null, "Mensaje borrado con exito");
						ventana.getPanelCentral().getMensajesFrame().actualizarTabla(env);
						ventana.getPanelCentral().validate();
						ventana.getPanelCentral().repaint();
					}
				}
			}
			break;
			
		case "cerrarSesion":
			u = null;
			ventana.getPanelCentral().remove(ventana.getPanelCentral().getMensajesFrame());
			ventana.getPanelCentral().add(ventana.getPanelCentral().getLoginFrame());
			ventana.getTitulo().setText("EASY CHAT - LOGIN");
			break;
			
		case "cerrar":
			conector.cerrar();
			System.exit(0);
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
