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
import javax.swing.table.DefaultTableModel;

import Controlador.ButtonController;
import Modelo.Mensaje;

public class PanelMensajes extends JPanel {
	
	private DefaultTableModel modeloTabla;
	private JTextField txtDestino, txtContenido;
	private JButton btnEnviar;
	private JLabel lblTitulo;
	
	public PanelMensajes (ButtonController accion) {
		
	this.setLayout(new GridLayout(0, 1));
	
	//Añadir dos subpaneles
	JPanel superior = new JPanel();
	superior.setLayout(new BorderLayout(0, 0));
	this.add(superior);
	JPanel inferior = new JPanel();
	inferior.setLayout(new FlowLayout());
	this.add(inferior);
	
	
	lblTitulo = new JLabel("MENSAJES");
	lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	superior.add(lblTitulo, BorderLayout.NORTH);
	
	//Tabla de mensajes
	JTable tablaMensajes;		
	
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
	

	JScrollPane tabla = new JScrollPane(tablaMensajes);
	superior.add(tabla, BorderLayout.CENTER);
	
	
	//enlace para modificar contraseña
	JLabel lblModificar = new JLabel();
	lblModificar.setText("Ir a modificar mi contraseña");
	lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
	lblModificar.setFont(new Font("Verdana", Font.HANGING_BASELINE, 14));
	lblModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	lblModificar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			//aquí hay que intentar mandar la acción
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
			lblModificar.setText("Ir a modificar mi contraseña");		
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
	
	JButton btnEnviar = new JButton("Enviar Mensaje");
	btnEnviar.setActionCommand("enviar");
	btnEnviar.addActionListener(accion);
	inferior.add(btnEnviar);
	

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

}
