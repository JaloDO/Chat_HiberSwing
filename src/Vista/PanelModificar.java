package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ButtonController;
import Modelo.Usuario;

public class PanelModificar extends JPanel{

	JTextField txtPass, txtPass2;
	JLabel lblMessage, lblName;
	
	public PanelModificar(ButtonController accion, Usuario user) {
		
		this.setLayout(new FlowLayout());
		
		
		lblName = new JLabel(user.getNombre());
		lblName.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblName.setForeground(Color.BLUE.brighter());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblName);
		
		JLabel lblPass = new JLabel("Nueva Contraseña: ");
		lblPass.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblPass);
		txtPass = new JTextField();
		txtPass.setColumns(16);
		txtPass.setEnabled(true);
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(txtPass);
		
		JLabel lblPass2 = new JLabel("Confirmar Contraseña: ");
		lblPass2.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPass2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblPass2);
		txtPass2 = new JTextField();
		txtPass2.setColumns(16);
		txtPass2.setEnabled(true);
		txtPass2.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(txtPass2);
		
		JButton btnModificar = new JButton();
		btnModificar.setText("Modificar");
		btnModificar.setActionCommand("modificarContrasena");
		btnModificar.addActionListener(accion);
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(btnModificar);
		
		
		lblMessage = new JLabel();
		lblMessage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMessage.setForeground(Color.RED);
		this.add(lblMessage);
		
		
		JButton btnVolverMensajes = new JButton();
		btnVolverMensajes.setText("Volver");
		btnVolverMensajes.setActionCommand("mensajes");
		btnVolverMensajes.addActionListener(accion);
		btnVolverMensajes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(btnVolverMensajes);
		
	}

	public JTextField getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(JTextField txtPass) {
		this.txtPass = txtPass;
	}

	public JTextField getTxtPass2() {
		return txtPass2;
	}

	public void setTxtPass2(JTextField txtPass2) {
		this.txtPass2 = txtPass2;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}
	
}
