package Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import Controlador.ButtonController;

public class Ventana extends JFrame{

	private PanelCentral panelCentral;
	
	public Ventana(ButtonController accion) {
		super();
		this.setTitle("Main");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//creacion jpanel y border layout
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		//container.setBackground(new Color(0.7f,0.8f,0.2f));
		this.setContentPane(container);
		/*añadir textfield de prueba
		JTextField t = new JTextField("Prueba");
		container.add(t);*/
		
		//Encabezado de la aplicación
		JLabel titulo = new JLabel("EASY CHAT");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 24));
		titulo.setBackground(Color.BLACK);
		titulo.setForeground(new Color(0.58f,0.79f,0.9f));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		container.add(titulo,BorderLayout.NORTH);
		
		//ventana central
		panelCentral = new PanelCentral(accion);
		container.add(panelCentral,BorderLayout.CENTER);
		
		//hacemos visible la ventana
		this.setVisible(true);
		
		
	}

	public PanelCentral getPanelCentral() {
		return panelCentral;
	}

	public void setPanelCentral(PanelCentral panelCentral) {
		this.panelCentral = panelCentral;
	}
	
	

}
