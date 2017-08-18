package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre","Apellido", "Tel�fono", "Calle",
			"Altura", "Piso","Depto", "Localidad", "Mail", "TipoContacto", "FechaNac"};
	private JMenuBar menuBar;
	private JMenu mnAgregar;
	private JMenu mnModificar;
	private JMenu mnEliminar;
	private JMenuItem mntmContacto;
	private JMenuItem mntmLocalidad;
	private JMenuItem mntmTipoDeContacto;
	private JMenuItem mntmContacto_1;
	private JMenuItem mntmLocalidad_1;
	private JMenuItem mntmTipoDeContacto_1;
	private JMenuItem mntmContacto_2;
	private JMenuItem mntmLocalidad_2;
	private JMenuItem mntmTipoDeContacto_2 ;

	

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 671, 364);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(20, 23, 628, 279);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(262, 330, 89, 23);
		panel.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(361, 330, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(460, 330, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(559, 330, 89, 23);
		panel.add(btnReporte);
		
		/*JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(20, 0, 197, 21);
		panel.add(menuBar);
		
		JMenu mnAgregar = new JMenu("Agregar");
		menuBar.add(mnAgregar);
		
		mntmContacto = new JMenuItem("Contacto");
		mnAgregar.add(mntmContacto);
		
		mntmLocalidad = new JMenuItem("Localidad");
		mnAgregar.add(mntmLocalidad);
		
		mntmTipoDeContacto = new JMenuItem("Tipo de contacto");
		mnAgregar.add(mntmTipoDeContacto);
		
		mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);
		
		mntmContacto_1 = new JMenuItem("Contacto");
		mnModificar.add(mntmContacto_1);
		
		mntmLocalidad_1 = new JMenuItem("Localidad");
		mnModificar.add(mntmLocalidad_1);
		
		mntmTipoDeContacto_1 = new JMenuItem("Tipo de contacto");
		mnModificar.add(mntmTipoDeContacto_1);
		
		mnEliminar = new JMenu("Eliminar");
		menuBar.add(mnEliminar);
		
		mntmContacto_2 = new JMenuItem("Contacto");
		mnEliminar.add(mntmContacto_2);
		
		mntmLocalidad_2 = new JMenuItem("Localidad");
		mnEliminar.add(mntmLocalidad_2);
		
		JMenuItem mntmTipoDeContacto_2 = new JMenuItem("Tipo de contacto");
		mnEliminar.add(mntmTipoDeContacto_2);*/
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("A text-only menu item",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menu.add(menuItem);

		menuItem = new JMenuItem("Both text and icon"
		                         );
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		menuItem = new JMenuItem();
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);

		
		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		//Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);
	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}


	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}


	public JMenu getMnAgregar() {
		return mnAgregar;
	}


	public void setMnAgregar(JMenu mnAgregar) {
		this.mnAgregar = mnAgregar;
	}


	public JMenu getMnModificar() {
		return mnModificar;
	}


	public void setMnModificar(JMenu mnModificar) {
		this.mnModificar = mnModificar;
	}


	public JMenu getMnEliminar() {
		return mnEliminar;
	}


	public void setMnEliminar(JMenu mnEliminar) {
		this.mnEliminar = mnEliminar;
	}


	public JMenuItem getMntmContacto() {
		return mntmContacto;
	}


	public void setMntmContacto(JMenuItem mntmContacto) {
		this.mntmContacto = mntmContacto;
	}


	public JMenuItem getMntmLocalidad() {
		return mntmLocalidad;
	}


	public void setMntmLocalidad(JMenuItem mntmLocalidad) {
		this.mntmLocalidad = mntmLocalidad;
	}


	public JMenuItem getMntmTipoDeContacto() {
		return mntmTipoDeContacto;
	}


	public void setMntmTipoDeContacto(JMenuItem mntmTipoDeContacto) {
		this.mntmTipoDeContacto = mntmTipoDeContacto;
	}


	public JMenuItem getMntmContacto_1() {
		return mntmContacto_1;
	}


	public void setMntmContacto_1(JMenuItem mntmContacto_1) {
		this.mntmContacto_1 = mntmContacto_1;
	}


	public JMenuItem getMntmLocalidad_1() {
		return mntmLocalidad_1;
	}


	public void setMntmLocalidad_1(JMenuItem mntmLocalidad_1) {
		this.mntmLocalidad_1 = mntmLocalidad_1;
	}


	public JMenuItem getMntmTipoDeContacto_1() {
		return mntmTipoDeContacto_1;
	}


	public void setMntmTipoDeContacto_1(JMenuItem mntmTipoDeContacto_1) {
		this.mntmTipoDeContacto_1 = mntmTipoDeContacto_1;
	}


	public JMenuItem getMntmContacto_2() {
		return mntmContacto_2;
	}


	public void setMntmContacto_2(JMenuItem mntmContacto_2) {
		this.mntmContacto_2 = mntmContacto_2;
	}


	public JMenuItem getMntmLocalidad_2() {
		return mntmLocalidad_2;
	}


	public void setMntmLocalidad_2(JMenuItem mntmLocalidad_2) {
		this.mntmLocalidad_2 = mntmLocalidad_2;
	}


	public JMenuItem getMntmTipoDeContacto_2() {
		return mntmTipoDeContacto_2;
	}


	public void setMntmTipoDeContacto_2(JMenuItem mntmTipoDeContacto_2) {
		this.mntmTipoDeContacto_2 = mntmTipoDeContacto_2;
	}

}
