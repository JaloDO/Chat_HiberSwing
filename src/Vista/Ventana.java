package Vista;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame{

	public Ventana() {
		
		JPanel principal = new JPanel();
		
		this.setTitle("Principal");
		JTextField t = new JTextField("Prueba");
		principal.add(t);
		
		
		this.add(principal);
		
		
	}
	
	

}
