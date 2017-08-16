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

	public VentanaPersona(Controlador controlador) 
	{
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 590, 489);
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
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(456, 415, 107, 46);
		panel.add(btnAgregarPersona);
		
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
		lblFechaDeNacimiento.setBounds(74, 269, 154, 14);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Amigos", "Familia", "Trabajo", "Universidad"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(395, 207, 164, 20);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "San Miguel", "Bella Vista"}));
		comboBox_1.setBounds(395, 152, 164, 20);
		panel.add(comboBox_1);
		
		JCalendar calendar = new JCalendar();
		calendar.setBorder(new LineBorder(new Color(0, 0, 0)));
		calendar.setBounds(74, 308, 184, 153);
		panel.add(calendar);
		
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtApellido;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
}

