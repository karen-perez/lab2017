package presentacion.controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEliminarLoc;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaModificar;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.PersonaDTO;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;
	private VentanaLocalidad ventanaLocalidad;
	private VentanaTipoContacto ventanaTipoContacto;
	private VentanaEliminarLoc ventanaEliminarLoc;
	private VentanaModificar ventanaModificarLoc;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);

		// aca se agregan los items de los menues

		// agregar
		this.vista.getMntmContacto().addActionListener(this);
		this.vista.getMntmLocalidad().addActionListener(this);
		this.vista.getMntmTipoDeContacto().addActionListener(this);

		// modificar
		this.vista.getMntmLocalidad_1().addActionListener(this);
		this.vista.getMntmTipoDeContacto_1().addActionListener(this);

		// eliminar
		this.vista.getMntmContacto_2().addActionListener(this);
		this.vista.getMntmLocalidad_2().addActionListener(this);
		this.vista.getMntmTipoDeContacto_2().addActionListener(this);

		this.agenda = agenda;
		this.personas_en_tabla = null;
	}

	public void inicializar() {
		this.llenarTabla();
	}

	private void llenarTabla() {
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(
				this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			Object[] fila = { this.personas_en_tabla.get(i).getNombre(),
					this.personas_en_tabla.get(i).getApellido(),
					this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getCalle(),
					this.personas_en_tabla.get(i).getAltura(),
					this.personas_en_tabla.get(i).getPiso(),
					this.personas_en_tabla.get(i).getDepto(),
					this.personas_en_tabla.get(i).getLocalidad(),
					this.personas_en_tabla.get(i).getMail(),
					this.personas_en_tabla.get(i).getTipocontacto(),
					this.personas_en_tabla.get(i).getFechanac(), };
			this.vista.getModelPersonas().addRow(fila);
		}
		this.vista.show();
	}

	public void actionPerformed(ActionEvent e) {
		// aca se abre el menu de agregar o presionando el boton de agregar,
		// ambos abren la ventana para agregar un nuevo contacto
		if (e.getSource() == this.vista.getBtnAgregar()
				|| e.getSource() == this.vista.getMntmContacto()) {
			DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
			localidadModel.addElement(new LocalidadDTO(0, ""));
			for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
				localidadModel.addElement(loc);
			}

			DefaultComboBoxModel<TipoContactoDTO> tipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
			tipoContactoModel.addElement(new TipoContactoDTO(0, ""));
			for (TipoContactoDTO tc : agenda.obtenerTipoContacto()) {
				tipoContactoModel.addElement(tc);
			}

			this.ventanaPersona = new VentanaPersona(this);
			this.ventanaPersona.getListaLocalidades().setModel(localidadModel);
			this.ventanaPersona.getListaTipoContacto().setModel(
					tipoContactoModel);
		}
		// elimina un contacto
		else if (e.getSource() == this.vista.getBtnBorrar()
				|| e.getSource() == this.vista.getMntmContacto_2()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas()
					.getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}

			this.llenarTabla();

		}

		else if (e.getSource() == this.vista.getBtnEditar()) {

			int[] filas_seleccionadas = this.vista.getTablaPersonas()
					.getSelectedRows();

			if (filas_seleccionadas.length > 0) {

				PersonaDTO contacto = this.personas_en_tabla
						.get(filas_seleccionadas[0]);

				DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
				localidadModel.addElement(new LocalidadDTO(0, ""));
				for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
					localidadModel.addElement(loc);
				}

				DefaultComboBoxModel<TipoContactoDTO> tipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
				tipoContactoModel.addElement(new TipoContactoDTO(0, ""));
				for (TipoContactoDTO tc : agenda.obtenerTipoContacto()) {
					tipoContactoModel.addElement(tc);
				}

				this.ventanaPersona = new VentanaPersona(this);
				this.ventanaPersona.getListaLocalidades().setModel(
						localidadModel);
				this.ventanaPersona.getListaTipoContacto().setModel(
						tipoContactoModel);
				this.ventanaPersona.getTxtApellido().setText(
						contacto.getApellido());
				this.ventanaPersona.getTxtNombre()
						.setText(contacto.getNombre());
				this.ventanaPersona.getTextTelefono().setText(
						contacto.getTelefono());
				this.ventanaPersona.getTextCalle().setText(contacto.getCalle());
				this.ventanaPersona.getTextAltura().setText(
						contacto.getAltura());
				this.ventanaPersona.getTextPiso().setText(contacto.getPiso());
				this.ventanaPersona.getTextDepto().setText(contacto.getDepto());
				this.ventanaPersona.getTextMail().setText(contacto.getMail());
				this.ventanaPersona.getDateChooser().setDate(
						contacto.getFechanac());
				this.ventanaPersona.getListaLocalidades().setSelectedItem(
						new LocalidadDTO(contacto.getLocalidad()
								.getIdLocalidad(), contacto.getLocalidad()
								.getLocalidad()));
				this.ventanaPersona.getListaTipoContacto().setSelectedItem(
						new TipoContactoDTO(contacto.getTipocontacto()
								.getIdTipoContacto(), contacto
								.getTipocontacto().getTipoContacto()));
			} else {
				JOptionPane.showMessageDialog(this.vista.getFrame(), "Seleccione un contacto.");
			}

		}
		
		//ingresar un nuevo tipo de contacto
		else if (this.ventanaTipoContacto != null && e.getSource() == this.ventanaTipoContacto.getBtnAgregar())
		{
			
			
			TipoContactoDTO nuevoContacto=new TipoContactoDTO(0,
					this.ventanaTipoContacto.getTextTipoContacto().getText());
				this.agenda.agregarTipoContacto(nuevoContacto);
				setExitoMsj("Agregado con exito!");

				this.ventanaTipoContacto.dispose();

		}
		//ingresar una nueva localidad
		else if (this.ventanaLocalidad != null && e.getSource() == this.ventanaLocalidad.getBtnAgregar())
		{
			LocalidadDTO nuevaLocalidad=new LocalidadDTO(0,
					this.ventanaLocalidad.getTextLocalidad().getText());
				this.agenda.agregarLocalidad(nuevaLocalidad);
				this.ventanaLocalidad.dispose();
				setExitoMsj("Agregada con exito!");

		}	


		// abre la ventana para agregar una nueva localidad
		else if (e.getSource() == this.vista.getMntmLocalidad()) {

			this.ventanaLocalidad = new VentanaLocalidad(this);

		}
		// modifica una localidad
		else if (e.getSource() == this.vista.getMntmLocalidad_1()) {
			DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
			for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
				localidadModel.addElement(loc);
			}

			this.ventanaModificarLoc = new VentanaModificar(this,
					localidadModel, null);

		}

		// elimina una localidad
		else if (e.getSource() == this.vista.getMntmLocalidad_2()) {
			DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
			for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
				localidadModel.addElement(loc);
			}

			this.ventanaEliminarLoc = new VentanaEliminarLoc(this,
					localidadModel, null);

		}

		// abre la ventana para agregar un nuevo tipo de contacto
		else if (e.getSource() == this.vista.getMntmTipoDeContacto()) {

			this.ventanaTipoContacto = new VentanaTipoContacto(this);
		}

		// modificar un nuevo tipo de contacto
		else if (e.getSource() == this.vista.getMntmTipoDeContacto_1()) {
			DefaultComboBoxModel<TipoContactoDTO> TipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
			for (TipoContactoDTO con : agenda.obtenerTipoContacto()) {
				TipoContactoModel.addElement(con);
			}

			this.ventanaModificarLoc = new VentanaModificar(this, null,
					TipoContactoModel);

		}

		// eliminar un tipo de contacto
		else if (e.getSource() == this.vista.getMntmTipoDeContacto_2()) {
			DefaultComboBoxModel<TipoContactoDTO> TipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
			for (TipoContactoDTO con : agenda.obtenerTipoContacto()) {
				TipoContactoModel.addElement(con);
			}

			this.ventanaEliminarLoc = new VentanaEliminarLoc(this, null,
					TipoContactoModel);

		}

		else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas()
					.getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}

			this.llenarTabla();

		}

		else if (e.getSource() == this.vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();
		}

		else if (this.ventanaLocalidad != null
				&& e.getSource() == this.ventanaLocalidad.getBtnAgregar()) {
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,
					this.ventanaLocalidad.getTextLocalidad().getText());
			this.agenda.agregarLocalidad(nuevaLocalidad);
			this.ventanaLocalidad.dispose();
		}

		// aca agrega una persona.. no lo probe todavia!
		else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {

			LocalidadDTO localidad = (LocalidadDTO) ventanaPersona
					.getListaLocalidades().getSelectedItem();
			TipoContactoDTO TipoContacto = (TipoContactoDTO) ventanaPersona
					.getListaTipoContacto().getSelectedItem();

			java.sql.Date fecha = this.ventanaPersona.getDateChooser().getDate() != null ? 
					new java.sql.Date(this.ventanaPersona.getDateChooser().getDate().getTime()) : null;
			PersonaDTO nuevaPersona = new PersonaDTO(0, this.ventanaPersona
					.getTxtNombre().getText(), this.ventanaPersona
					.getTxtApellido().getText(), this.ventanaPersona
					.getTextTelefono().getText(), this.ventanaPersona
					.getTextMail().getText(), fecha,
					this.ventanaPersona.getTextCalle().getText(),
					this.ventanaPersona.getTextAltura().getText(),
					this.ventanaPersona.getTextPiso().getText(),
					this.ventanaPersona.getTextDepto().getText(), localidad,
					TipoContacto);

			try {
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				setExitoMsj("Agregado con exito!");
				this.ventanaPersona.dispose();
			} catch (Exception excepcionAgregarPersona) {
				JOptionPane.showMessageDialog(this.ventanaPersona, excepcionAgregarPersona.getMessage());
			}			

		}
		
		
		
	}
	@SuppressWarnings("unused")
	private boolean mailValido(String email) {
		boolean status=false;
		System.out.println(email);
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		 Pattern pattern= Pattern.compile(ePattern);
		 Matcher matcher=pattern.matcher(email);
		 if(matcher.matches())
		 {
			 status=true;
		 }
		 else
		 {
			 status=false;
		 }
		return status;
	}

	public static void setWarningMsg(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Warning!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}

	
	public static void setExitoMsj(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Exito!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}


}
