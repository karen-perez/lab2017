package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import presentacion.controlador.Controlador;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;

public class VentanaEliminarLoc extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBoxEliminar;
	private JButton btnEliminar;
	private Controlador controlador;
	private boolean eliminaLoc;

	public VentanaEliminarLoc(Controlador controlador, DefaultComboBoxModel<LocalidadDTO> localidades, DefaultComboBoxModel<TipoContactoDTO> TipoContacto ) {
		
		super();
		setTitle("Eliminar una localidad");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEliminar = new JLabel("Seleccione la localidad a eliminar:");
		lblEliminar.setBounds(10, 57, 209, 16);
		panel.add(lblEliminar);
		
		comboBoxEliminar = new JComboBox();
		comboBoxEliminar.setBounds(215, 54, 160, 22);
		panel.add(comboBoxEliminar);
		
		if(TipoContacto==null)
		{
		comboBoxEliminar.setModel(localidades);//nuevo
		eliminaLoc = true;
		}
		else
		{
			comboBoxEliminar.setModel(TipoContacto);//nuevo
		eliminaLoc=false;
		}
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this.controlador);
		btnEliminar.setBounds(157, 131, 97, 25);
		panel.add(btnEliminar);
		
		this.setVisible(true);
	}

	public JComboBox getComboBoxEliminar() {
		return comboBoxEliminar;
	}

	public void setComboBoxEliminar(JComboBox comboBoxEliminar) {
		this.comboBoxEliminar = comboBoxEliminar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public boolean isEliminaLoc() {
		return eliminaLoc;
	}

	public void setEliminaLoc(boolean eliminaLoc) {
		this.eliminaLoc = eliminaLoc;
	}

}
