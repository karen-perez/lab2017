package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import presentacion.controlador.Controlador;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;

public class VentanaModificarLoc extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNuevaModificar;
	private JLabel lblSelecLocalidad;
	private JLabel lblModificar;
	private JComboBox  comboBoxModificar;
	private JButton btnModificar;
	private Controlador controlador;
	private boolean modificoLoc;


	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarLocalidad frame = new VentanaModificarLocalidad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public VentanaModificarLoc(Controlador controlador, DefaultComboBoxModel<LocalidadDTO> localidades, DefaultComboBoxModel<TipoContactoDTO> TipoContacto ) {
		
		super();
		setTitle("Modificar localidad");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textNuevaModificar = new JTextField();
		textNuevaModificar.setBounds(165, 105, 116, 22);
		panel.add(textNuevaModificar);
		textNuevaModificar.setColumns(10);
		
		comboBoxModificar = new JComboBox();
		comboBoxModificar.setBounds(165, 48, 116, 22);
		panel.add(comboBoxModificar);
		if(TipoContacto==null)
		{
		comboBoxModificar.setModel(localidades);//nuevo
		modificoLoc = true;
		}
		else
		{
			comboBoxModificar.setModel(TipoContacto);//nuevo
		modificoLoc=false;
		}
		
		lblSelecLocalidad = new JLabel("Seleccionar ");
		lblSelecLocalidad.setBounds(29, 51, 124, 16);
		panel.add(lblSelecLocalidad);
		
		lblModificar = new JLabel("Nuevo Nombre");
		lblModificar.setBounds(29, 108, 113, 16);
		panel.add(lblModificar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this.controlador);
		btnModificar.setBounds(179, 188, 97, 25);
		panel.add(btnModificar);
		this.setVisible(true);
	}

	public JTextField getTextNuevoModificado() {
		return textNuevaModificar;
	}

	public void setTextNuevaLocalidad(JTextField textNuevaLocalidad) {
		this.textNuevaModificar = textNuevaLocalidad;
	}

	public JLabel getLblSelecLocalidad() {
		return lblSelecLocalidad;
	}

	public void setLblSelecLocalidad(JLabel lblSelecLocalidad) {
		this.lblSelecLocalidad = lblSelecLocalidad;
	}

	public JLabel getLblModificarLocalidad() {
		return lblModificar;
	}

	public void setLblModificarLocalidad(JLabel lblModificarLocalidad) {
		this.lblModificar = lblModificarLocalidad;
	}

	public JComboBox <LocalidadDTO> getComboBoxLocalidades() {
		return comboBoxModificar;
	}
	

	public JTextField getTextNuevaModificar() {
		return textNuevaModificar;
	}

	public void setTextNuevaModificar(JTextField textNuevaModificar) {
		this.textNuevaModificar = textNuevaModificar;
	}

	public void setComboBoxModificador(JComboBox comboBoxModificador) {
		this.comboBoxModificar = comboBoxModificador;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public boolean isModificoLoc() {
		return modificoLoc;
	}

	public void setModificoLoc(boolean modificoLoc) {
		this.modificoLoc = modificoLoc;
	}
	
	
}
