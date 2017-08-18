package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Panel;
import java.awt.Color;

import javax.swing.JTextField;

import presentacion.controlador.Controlador;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.SystemColor;

public class VentanaTipoContacto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTipoContacto;
	private  Controlador controlador;
	private Button btnAgregar; 

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTipoContacto frame = new VentanaTipoContacto(controlador);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaTipoContacto(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setTitle("Agregar nuevo tipo de contacto");
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
		
		textTipoContacto = new JTextField();
		textTipoContacto.setBounds(143, 68, 116, 22);
		panel.add(textTipoContacto);
		textTipoContacto.setColumns(10);
		
		btnAgregar = new Button("Agregar");
		btnAgregar.addActionListener(this.controlador);
		btnAgregar.setBounds(233, 185, 79, 24);
		panel.add(btnAgregar);
		
		Label lblTipoContacto = new Label("Tipo de Contacto:");
		lblTipoContacto.setBounds(21, 66, 116, 24);
		panel.add(lblTipoContacto);
		this.setVisible(true);
	}

	public JTextField getTextTipoContacto() {
		return textTipoContacto;
	}

	public void setTextTipoContacto(JTextField textTipoContacto) {
		this.textTipoContacto = textTipoContacto;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}
	
}
