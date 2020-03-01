package Vista;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
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
	
	public PanelMensajes (ButtonController accion) {
		
	this.setLayout(new BorderLayout(0,0));
		
	JLabel titulo = new JLabel("MENSAJES");
	titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	titulo.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(titulo, BorderLayout.NORTH);

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
	add(tabla, BorderLayout.CENTER);
	

	JLabel lblDestino = new JLabel("Destinatario: ");
	this.add(lblDestino);
	txtDestino = new JTextField();
	this.add(txtDestino);
	

	
	
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
	add(lblModificar);
	

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
