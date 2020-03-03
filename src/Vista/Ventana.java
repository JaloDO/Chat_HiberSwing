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
	private JLabel titulo;
	
	public Ventana(ButtonController accion) {
		super();
		this.setTitle("Main");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//creacion jpanel y border layout
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		//container.setBackground(new Color(0.7f,0.8f,0.2f));
		this.setContentPane(container);
		/*a�adir textfield de prueba
		JTextField t = new JTextField("Prueba");
		container.add(t);*/
		
		//Encabezado de la aplicaci�n
		titulo = new JLabel("EASY CHAT - LOGIN");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 24));
		titulo.setBackground(Color.BLACK);
		titulo.setForeground(Color.BLUE.brighter());
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

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}
	
	

}
