package Vista;

import java.awt.CardLayout;
import javax.swing.JPanel;

import Controlador.ButtonController;

public class PanelCentral extends JPanel{
	
	private PanelLogin loginFrame;
	
	//constructor
	public PanelCentral (ButtonController accion) {
		super();
		this.setLayout(new CardLayout(20,20));
		
		loginFrame = new PanelLogin();
		this.add(loginFrame,"login");
	}
	
	
	
	//getter & setter
	public PanelLogin getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(PanelLogin loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	
}
