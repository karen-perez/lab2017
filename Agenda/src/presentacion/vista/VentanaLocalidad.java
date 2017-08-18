package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import presentacion.controlador.Controlador;
import java.awt.SystemColor;

public class VentanaLocalidad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textLocalidad;
	private Controlador controlador;
	private JButton btnAgregar;

	public JTextField getTextLocalidad() {
		return textLocalidad;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}


	public VentanaLocalidad(Controlador controlador) {
		
		super();
		this.controlador = controlador;
		
		setTitle("Agregar nueva localidad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.control);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textLocalidad = new JTextField();
		textLocalidad.setBounds(88, 58, 116, 22);
		panel.add(textLocalidad);
		textLocalidad.setColumns(10);
		
	    btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this.controlador);
	
		

		btnAgregar.setBounds(144, 163, 97, 25);
		panel.add(btnAgregar);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(20, 61, 56, 16);
		panel.add(lblLocalidad);
		
		this.setVisible(true);
	}
}
