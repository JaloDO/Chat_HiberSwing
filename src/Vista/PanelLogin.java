package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelLogin extends JPanel{
	
	public PanelLogin() {
		JLabel lblLogin = new JLabel("LOGIN DE USUARIOS");
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblLogin.setForeground(new Color(0.58f,0.79f,0.9f));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogin);
	}
}
