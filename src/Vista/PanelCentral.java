package Vista;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

import Controlador.ButtonController;
import Modelo.Usuario;

public class PanelCentral extends JPanel{
	
	private PanelLogin loginFrame;
	private PanelMensajes mensajesFrame;
	private Usuario usuario;
	private PanelModificar modificarFrame;
	
	//constructor
	public PanelCentral (ButtonController accion) {
		super();
		this.setLayout(new CardLayout(20,20));
		
		loginFrame = new PanelLogin(accion);
		this.add(loginFrame,"login");
		
	}
	
	
	//getter & setter
	public PanelLogin getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(PanelLogin loginFrame) {
		this.loginFrame = loginFrame;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PanelMensajes getMensajesFrame() {
		return mensajesFrame;
	}

	public void setMensajesFrame(PanelMensajes mensajesFrame) {
		this.mensajesFrame = mensajesFrame;
	}


	public PanelModificar getModificarFrame() {
		return modificarFrame;
	}


	public void setModificarFrame(PanelModificar modificarFrame) {
		this.modificarFrame = modificarFrame;
	}
	
	
}
