package presentacion.vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JCalendar;
import javax.swing.border.LineBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnAgregarPersona;
	private Controlador controlador;
	private JTextField textTelefono;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textDepto;
	private JTextField textMail;
	private JTextField textPiso;
	private JDateChooser dateChooser;
	private PersonaDTO persona_a_editar;
	private JComboBox<LocalidadDTO> listaLocalidades;
	private JComboBox <TipoContactoDTO> listaTipoContacto;
	private boolean editar=false;
	private JButton btnActualizarC;


	public VentanaPersona(Controlador controlador, DefaultComboBoxModel<LocalidadDTO> localidades, 
			DefaultComboBoxModel<TipoContactoDTO> tipoContacto, PersonaDTO personaAmodificar) 
	{
		super();
		this.controlador = controlador;
		this.persona_a_editar = personaAmodificar;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 590, 416);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Apellido");
		lblTelfono.setBounds(290, 11, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(74, 11, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(395, 11, 164, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		if (personaAmodificar != null) { 
			/*btnAgregarPersona = new JButton("Actualizar");
			btnAgregarPersona.addActionListener(this.controlador);
			btnAgregarPersona.setBounds(453, 269, 89, 23);
			panel.add(btnAgregarPersona);
				*/
		btnActualizarC = new JButton("Actualizar");
		btnActualizarC.addActionListener(this.controlador);
		btnActualizarC.setBounds(278, 243, 97, 25);
		panel.add(btnActualizarC);
		} 
		else{
			btnAgregarPersona = new JButton("Agregar");
			btnAgregarPersona.addActionListener(this.controlador);
			btnAgregarPersona.setBounds(452, 326, 107, 46);
			panel.add(btnAgregarPersona);
			
			
		}
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(74, 59, 164, 20);
		panel.add(textTelefono);
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		textCalle.setBounds(395, 59, 164, 20);
		panel.add(textCalle);
		
		textAltura = new JTextField();
		textAltura.setColumns(10);
		textAltura.setBounds(74, 101, 164, 20);
		panel.add(textAltura);
		
		JLabel label = new JLabel("Tel\u00E9fono");
		label.setBounds(10, 62, 113, 14);
		panel.add(label);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(290, 62, 113, 14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 104, 113, 14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(290, 104, 113, 14);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(10, 152, 113, 14);
		panel.add(lblDepto);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(290, 152, 113, 14);
		panel.add(lblLocalidad);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 207, 113, 14);
		panel.add(lblMail);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(290, 207, 113, 14);
		panel.add(lblTipoDeContacto);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(84, 263, 154, 14);
		panel.add(lblFechaDeNacimiento);
		
		textDepto = new JTextField();
		textDepto.setColumns(10);
		textDepto.setBounds(74, 149, 164, 20);
		panel.add(textDepto);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(74, 204, 164, 20);
		panel.add(textMail);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(395, 104, 164, 20);
		panel.add(textPiso);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(74, 294, 154, 20);
		panel.add(dateChooser);
		
		/*JComboBox listaTipoContacto = new JComboBox();
		listaTipoContacto.setModel(new DefaultComboBoxModel(new String[] {"", "Amigos", "Familia", "Trabajo", "Universidad"}));*/
		listaTipoContacto = new JComboBox<TipoContactoDTO>();
		listaTipoContacto.setToolTipText("");
		listaTipoContacto.setBounds(395, 207, 164, 20);
		panel.add(listaTipoContacto);
		
		//JComboBox listaLocalidades = new JComboBox();
		//listaLocalidades.setModel(new DefaultComboBoxModel(new String[] {"", "San Miguel", "Bella Vista"}));
		listaLocalidades = new JComboBox<LocalidadDTO>();
		listaLocalidades.setBounds(395, 152, 164, 20);
		panel.add(listaLocalidades);		
		
		this.setVisible(true);
		if (personaAmodificar != null) {
			editar=true;
			CargarDatosPersona(personaAmodificar);
		}
		
		this.setVisible(true);
	}
	private void CargarDatosPersona(PersonaDTO personaAEditar) {
		getTxtNombre().setText(personaAEditar.getNombre());
		getTxtApellido().setText(personaAEditar.getApellido());
		getTextTelefono().setText(personaAEditar.getTelefono());
		getTextMail().setText(personaAEditar.getMail());
		getDateChooser().setDate(personaAEditar.getFechanac());
		getTextCalle().setText(personaAEditar.getCalle());
		getTextAltura().setText(personaAEditar.getAltura());
		getTextPiso().setText(personaAEditar.getPiso());
		getTextDepto().setText(personaAEditar.getDepto());
		getListaLocalidades().setSelectedItem(new LocalidadDTO(personaAEditar.getLocalidad().getIdLocalidad(), personaAEditar.getLocalidad().getLocalidad()));
		getListaTipoContacto().setSelectedItem(new TipoContactoDTO(personaAEditar.getTipocontacto().getIdTipoContacto(), personaAEditar.getTipocontacto().getTipoContacto()));
		
		
		
	}
	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	
	public JTextField getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}


	public JTextField getTxtApellido() {
		return txtApellido;
	}


	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}


	public JTextField getTextTelefono() {
		return textTelefono;
	}


	public void setTextTelefono(JTextField textTelefono) {
		this.textTelefono = textTelefono;
	}


	public JTextField getTextCalle() {
		return textCalle;
	}


	public void setTextCalle(JTextField textCalle) {
		this.textCalle = textCalle;
	}


	public JTextField getTextAltura() {
		return textAltura;
	}


	public void setTextAltura(JTextField textAltura) {
		this.textAltura = textAltura;
	}


	public JTextField getTextDepto() {
		return textDepto;
	}


	public void setTextDepto(JTextField textDepto) {
		this.textDepto = textDepto;
	}


	public JTextField getTextMail() {
		return textMail;
	}


	public void setTextMail(JTextField textMail) {
		this.textMail = textMail;
	}


	public JTextField getTextPiso() {
		return textPiso;
	}


	public void setTextPiso(JTextField textPiso) {
		this.textPiso = textPiso;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public JComboBox<LocalidadDTO> getListaLocalidades() {
		return listaLocalidades;
	}
	public void setListaLocalidades(JComboBox<LocalidadDTO> listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}
	public JComboBox<TipoContactoDTO> getListaTipoContacto() {
		return listaTipoContacto;
	}
	public void setListaTipoContacto(JComboBox<TipoContactoDTO> listaTipoContacto) {
		this.listaTipoContacto = listaTipoContacto;
	}
	
	public PersonaDTO getPersonaAEditar() {
		return persona_a_editar;
	}



	
	
	public void setBtnAgregarPersona(JButton btnAgregarPersona) {
		this.btnAgregarPersona = btnAgregarPersona;
	}
	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
}

