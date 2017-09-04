package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaConexion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPuerto;
	private JTextField txtUsuario;
	private JButton btnAceptar;
	private JTextField textPass;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConexion frame = new VentanaConexion();
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
	
	
	public VentanaConexion() 
	{
	
		//super();
		initialize();
	}
	public void initialize() {
		setTitle("Datos de Conexion");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(177, 205, 97, 25);
		panel.add(btnAceptar);
		
		txtIP = new JTextField();
		txtIP.setToolTipText("localhost");
		txtIP.setBounds(124, 25, 176, 22);
		panel.add(txtIP);
		txtIP.setColumns(10);
		
		txtPuerto = new JTextField();
		txtPuerto.setToolTipText("3306");
		txtPuerto.setBounds(124, 60, 176, 22);
		panel.add(txtPuerto);
		txtPuerto.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("root");
		txtUsuario.setBounds(124, 95, 176, 22);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblIP = new JLabel("IP");
		lblIP.setBounds(23, 28, 56, 16);
		panel.add(lblIP);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(23, 63, 89, 16);
		panel.add(lblPuerto);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(23, 98, 56, 16);
		panel.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(23, 133, 89, 16);
		panel.add(lblContraseña);
		
		textPass = new JTextField();
		textPass.setToolTipText("root");
		textPass.setBounds(124, 130, 176, 22);
		panel.add(textPass);
		textPass.setColumns(10);
		
		
		this.setVisible(true);

	}

	public JTextField getTxtIP() {
		return txtIP;
	}

	public void setTxtServidor(JTextField txtServidor) {
		this.txtIP = txtServidor;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public void setTxtNombreBD(JTextField txtNombreBD) {
		this.txtPuerto = txtNombreBD;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}



	public JTextField getTextPass() {
		return textPass;
	}
	public void setTextPass(JTextField textPass) {
		this.textPass = textPass;
	}
	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}
}
