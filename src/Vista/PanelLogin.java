package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ButtonController;

public class PanelLogin extends JPanel{
	JTextField txtName, txtPass;
	JLabel lblMessage;
	//BorderLayout loginLayout;
	
	public PanelLogin(ButtonController accion) {
		this.setLayout(new GridLayout(0, 1));
		
		JPanel superior = new JPanel();
		superior.setLayout(new FlowLayout());
		this.add(superior);
		
		JLabel lblName = new JLabel("\nUsuario: ");
		lblName.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		//userLayout.addLayoutComponent("lblName",lblName);
		superior.add(lblName);
		txtName = new JTextField();
		txtName.setColumns(20);
		txtName.setEnabled(true);
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		//userLayout.addLayoutComponent("txtName",txtName);
		superior.add(txtName);
		
		//flow layout con la contraseña (middle)
		//FlowLayout passLayout = new FlowLayout();
		JLabel lblPass = new JLabel("Contraseña: ");
		lblPass.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		superior.add(lblPass);
		txtPass = new JTextField();
		txtPass.setColumns(16);
		txtPass.setEnabled(true);
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		superior.add(txtPass);
		
		
		//flow layout con los botones (bottom)
		//boton para enviar (mandar accion al controlador y llamar al metodo de la clase conectar)
		JButton btnLogin = new JButton();
		btnLogin.setText("Entrar");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(accion);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		superior.add(btnLogin);
		
		//boton para resetear campos del login
		JButton btnResetLogin  =new JButton();
		btnResetLogin.setText("Reset");
		btnResetLogin.setActionCommand("resetLogin");
		btnResetLogin.addActionListener(accion);
		btnResetLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		superior.add(btnResetLogin);
		
	
		//boton para ir a registrar por si no sale lo del link XD
		JButton btnSignUp = new JButton();
		btnSignUp.setText("Crear una cuenta");
		btnSignUp.setActionCommand("crearCuenta");
		btnSignUp.addActionListener(accion);
		btnSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		superior.add(btnSignUp);
		
		//Label para mensaje de error en el logueo
		lblMessage = new JLabel();
		lblMessage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMessage.setForeground(Color.RED);
		superior.add(lblMessage);
		
		JPanel inferior = new JPanel();
		inferior.setLayout(new FlowLayout());
		this.add(inferior);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setActionCommand("cerrar");
		btnCerrar.addActionListener(accion);
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inferior.add(btnCerrar);
	}

	//getters & setters
	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(JTextField txtPass) {
		this.txtPass = txtPass;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
}
