package Vista;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.ButtonController;
import Modelo.Mensaje;

public class PanelMensajes extends JPanel {
	
	private DefaultTableModel modeloTabla;
	private JTextField txtDestino, txtContenido;
	private JButton btnCambiar, btnBorrar;
	private JLabel lblTitulo;

	private JScrollPane tabla;
	private JPanel superior;

	private int id_mensaje;
	
	public PanelMensajes (ButtonController accion) {
	
	this.setLayout(new GridLayout(0, 1));
	
	//Aï¿½adir dos subpaneles
	superior = new JPanel();
	superior.setLayout(new BorderLayout(0, 0));
	this.add(superior);
	JPanel inferior = new JPanel();
	inferior.setLayout(new FlowLayout());
	this.add(inferior);
	
	
	lblTitulo = new JLabel("MENSAJES");
	lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	superior.add(lblTitulo, BorderLayout.NORTH);
	
	
	//boton cambiar recibidos por enviados y viceversa
	btnCambiar = new JButton("Ver Enviados");
	btnCambiar.setActionCommand("cambiarMensajes");
	btnCambiar.addActionListener(accion);
	superior.add(btnCambiar, BorderLayout.WEST);
	//boton borrar mensaje (solo enviados)
	btnBorrar = new JButton("BORRAR");
	btnBorrar.setActionCommand("borrar");
	btnBorrar.addActionListener(accion);
	btnBorrar.setVisible(false);
	superior.add(btnBorrar, BorderLayout.EAST);
	
		
	//Tabla de mensajes
	JTable tablaMensajes;
	//distintas tablas para mensajes enviados o recibidos
	//Columnas
	String[] columnas = {"Emisor","Destinatario","Contenido", "Fecha"};
	//Celdas no editables
	modeloTabla = new DefaultTableModel(columnas, 0) {
		public boolean isCellEditable(int row, int column)

	    	{
		      return false;//This causes all cells to be not editable
		    }

	};
	tablaMensajes =  new JTable(modeloTabla);
	tablaMensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tablaMensajes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			id_mensaje = tablaMensajes.getSelectedRow();
		}
	});
	
	tabla = new JScrollPane(tablaMensajes);
	superior.add(tabla, BorderLayout.CENTER);
	
	
	//enlace para modificar contraseï¿½a
	JLabel lblModificar = new JLabel();
	lblModificar.setText("Ir a modificar mi contraseÃ±a");
	lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
	lblModificar.setFont(new Font("Verdana", Font.HANGING_BASELINE, 14));
	lblModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	lblModificar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			//aquï¿½ hay que intentar mandar la acciï¿½n
			JButton btn = new JButton();
			btn.setActionCommand("modificar");
			btn.addActionListener(accion);
			btn.doClick();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			lblModificar.setText("<html><a href=''>Ir a modificar mi contraseña</a></html>");
		}
		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			lblModificar.setText("Ir a modificar mi contraseï¿½a");		
		}
	});
	superior.add(lblModificar, BorderLayout.SOUTH);
	
	
	
	//Componentes formulario para enviar mensaje
	JLabel lblDestino = new JLabel("Destinatario: ");
	inferior.add(lblDestino);
	txtDestino = new JTextField();
	txtDestino.setColumns(30);
	inferior.add(txtDestino);
	
	JLabel lblContenido = new JLabel("Contendio del mensaje: ");
	inferior.add(lblContenido);
	txtContenido = new JTextField();
	txtContenido.setColumns(100);
	inferior.add(txtContenido);
	
	//boton enviar mensaje
	JButton btnEnviar = new JButton("Enviar Mensaje");
	btnEnviar.setActionCommand("enviarMensaje");
	btnEnviar.addActionListener(accion);
	inferior.add(btnEnviar);
	
	//boton cerrar sesion
	JButton btnCerrar = new JButton("Cerrar Sesión");
	btnCerrar.setActionCommand("cerrarSesion");
	btnCerrar.addActionListener(accion);
	inferior.add(btnCerrar, BorderLayout.SOUTH);
	

	}
	
	public void actualizarTabla(List<Mensaje> mensajes) {
		//Borramos los datos de la tabla
		int filas = modeloTabla.getRowCount();
			if(filas>0){
				for(int i=filas-1;i>=0;i--){
					modeloTabla.removeRow(i);
				}
			}
		
		//Rellenamos de nuevo la tabla				
		for(int i=0;i<mensajes.size();i++){
			Mensaje m = mensajes.get(i);
			SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy");
			String fecha = formato.format(m.getFecha());
			Object[] fila = {m.getEmisor().getNombre(),
							m.getReceptor().getNombre(),
							m.getContenido(),
							fecha};
			modeloTabla.addRow(fila);
		}
	}

	public JButton getBtnCambiar() {
		return btnCambiar;
	}

	public void setBtnCambiar(JButton btnCambiar) {
		this.btnCambiar = btnCambiar;
	}

	public JTextField getTxtDestino() {
		return txtDestino;
	}

	public void setTxtDestino(JTextField txtDestino) {
		this.txtDestino = txtDestino;
	}

	public JTextField getTxtContenido() {
		return txtContenido;
	}

	public void setTxtContenido(JTextField txtContenido) {
		this.txtContenido = txtContenido;
	}


	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public JScrollPane getTabla() {
		return tabla;
	}

	public void setTabla(JScrollPane tabla) {
		this.tabla = tabla;
	}

	public JPanel getSuperior() {
		return superior;
	}

	public void setSuperior(JPanel superior) {
		this.superior = superior;
	}
	

	public int getId_mensaje() {
		return id_mensaje;
	}

	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	
	
}
