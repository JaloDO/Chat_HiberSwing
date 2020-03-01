package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
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
		
		/*JLabel lblLogin = new JLabel("LOGIN DE USUARIOS");
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblLogin.setForeground(new Color(0.58f,0.79f,0.9f));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogin);*/
		
		//flow layout con el usuario (top)
		//FlowLayout userLayout = new FlowLayout();
		JLabel lblName = new JLabel("\nUsuario: ");
		lblName.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		//userLayout.addLayoutComponent("lblName",lblName);
		add(lblName);
		txtName = new JTextField();
		txtName.setColumns(20);
		txtName.setEnabled(true);
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		//userLayout.addLayoutComponent("txtName",txtName);
		add(txtName);
		
		//flow layout con la contraseña (middle)
		//FlowLayout passLayout = new FlowLayout();
		JLabel lblPass = new JLabel("Contraseña: ");
		lblPass.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPass);
		txtPass = new JTextField();
		txtPass.setColumns(16);
		txtPass.setEnabled(true);
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		add(txtPass);
		
		
		//flow layout con los botones (bottom)
		//boton para enviar (mandar accion al controlador y llamar al metodo de la clase conectar)
		JButton btnLogin = new JButton();
		btnLogin.setText("Entrar");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(accion);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnLogin);
		
		//boton para resetear campos del login
		JButton btnResetLogin  =new JButton();
		btnResetLogin.setText("Reset");
		btnResetLogin.setActionCommand("resetLogin");
		btnResetLogin.addActionListener(accion);
		btnResetLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnResetLogin);
		
		/*
		//label simulando link para ir a registrar
		JLabel lblSignUp = new JLabel();
		lblSignUp.setText("Todavía no tengo cuenta");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Verdana", Font.HANGING_BASELINE, 14));
		lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//aquí hay que intentar mandar la acción
				JButton btn = new JButton();
				btn.setActionCommand("crearCuenta");
				btn.addActionListener(accion);
				btn.doClick();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lblSignUp.setText("<html><a href=''>Todavía no tengo cuenta</a></html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lblSignUp.setText("Todavía no tengo cuenta");		
			}
		});
		add(lblSignUp);
		*/
	
		//boton para ir a registrar por si no sale lo del link XD
		JButton btnSignUp = new JButton();
		btnSignUp.setText("Crear una cuenta");
		btnSignUp.setActionCommand("crearCuenta");
		btnSignUp.addActionListener(accion);
		btnSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnSignUp);
		
		//Label para mensaje de error en el logueo
		lblMessage = new JLabel();
		lblMessage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMessage.setForeground(Color.RED);
		add(lblMessage);
		
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
