package presentacion.controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
		this.vista.getMntmContacto_1().addActionListener(this);
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
					/*this.personas_en_tabla.get(i).getCalle(),
					this.personas_en_tabla.get(i).getAltura(),
					this.personas_en_tabla.get(i).getPiso(),
					this.personas_en_tabla.get(i).getDepto(),
					this.personas_en_tabla.get(i).getLocalidad(),*/
					this.personas_en_tabla.get(i).getMail(),
					this.personas_en_tabla.get(i).getTipocontacto(),
					this.personas_en_tabla.get(i).getFechanac(), };
			this.vista.getModelPersonas().addRow(fila);
		}
		this.vista.show();
	}

	public void abrirVentanaPersona(PersonaDTO persona) {
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

		this.ventanaPersona = new VentanaPersona(this, localidadModel,
				tipoContactoModel, persona);
		this.ventanaPersona.getListaLocalidades().setModel(localidadModel);
		this.ventanaPersona.getListaTipoContacto().setModel(
				tipoContactoModel);
	}
	
	public void actionPerformed(ActionEvent e) {
		// aca se abre el menu de agregar o presionando el boton de agregar,
		// ambos abren la ventana para agregar un nuevo contacto
		if (e.getSource() == this.vista.getBtnAgregar()
				|| e.getSource() == this.vista.getMntmContacto()) {
			abrirVentanaPersona(null);
			
		} else if (e.getSource() == this.vista.getBtnEditar()
				|| e.getSource() == this.vista.getMntmContacto_1()) {
			
			if (this.vista.getTablaPersonas()
					.getSelectedRows().length > 0) {
				int fila_seleccionada = this.vista.getTablaPersonas()
						.getSelectedRows()[0];
				PersonaDTO persona_seleccionada = this.personas_en_tabla
						.get(fila_seleccionada);				
				
				abrirVentanaPersona(persona_seleccionada);				
				this.ventanaPersona.getListaLocalidades().setSelectedItem(new LocalidadDTO(persona_seleccionada.getLocalidad().getIdLocalidad(), persona_seleccionada.getLocalidad().getLocalidad()));
				this.ventanaPersona.getListaTipoContacto().setSelectedItem(new TipoContactoDTO(persona_seleccionada.getTipocontacto().getIdTipoContacto(), persona_seleccionada.getTipocontacto().getTipoContacto()));				
				
			} else {
				JOptionPane.showMessageDialog(this.vista.getFrame(),
						"Seleccione un contacto.");
			}						
		}
		// elimina un contacto
		else if (e.getSource() == this.vista.getBtnBorrar()
				|| e.getSource() == this.vista.getMntmContacto_2()) {
			
			if (this.vista.getTablaPersonas()
					.getSelectedRows().length > 0) {
				int[] filas_seleccionadas = this.vista.getTablaPersonas()
						.getSelectedRows();
				for (int fila : filas_seleccionadas) {
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}

				this.llenarTabla();
			} else {
				JOptionPane.showMessageDialog(this.vista.getFrame(),
						"Seleccione un contacto.");
			}
		}
		// ingresar un nuevo tipo de contacto(validacion)
		else if (this.ventanaTipoContacto != null
				&& e.getSource() == this.ventanaTipoContacto.getBtnAgregar()) {

			TipoContactoDTO nuevoContacto = new TipoContactoDTO(0,
					this.ventanaTipoContacto.getTextTipoContacto().getText());
			this.agenda.agregarTipoContacto(nuevoContacto);
			setExitoMsj("Agregado con exito!");

			this.ventanaTipoContacto.dispose();

		}
		// modificar localidad (validacion)
		else if (this.ventanaModificarLoc != null
				&& e.getSource() == this.ventanaModificarLoc.getBtnModificar()) {
			if (this.ventanaModificarLoc.getTextNuevaModificar().getText()
					.isEmpty()) {
				setWarningMsj("Por favor ingresar la nueva modificacion.");

			} else {

				if (this.ventanaModificarLoc.isModificoLoc() == true) {

					LocalidadDTO nuevaLocalidad = new LocalidadDTO(
							((LocalidadDTO) ventanaModificarLoc
									.getComboBoxLocalidades().getSelectedItem())
									.getIdLocalidad(), this.ventanaModificarLoc
									.getTextNuevoModificado().getText());
					this.agenda.ActualizarLocalidad(nuevaLocalidad);

				} else {
					TipoContactoDTO nuevoTC = new TipoContactoDTO(
							((TipoContactoDTO) ventanaModificarLoc
									.getComboBoxLocalidades().getSelectedItem())
									.getIdTipoContacto(),
							this.ventanaModificarLoc.getTextNuevoModificado()
									.getText());
					this.agenda.ActualizarTipoContacto(nuevoTC);
				}
				setExitoMsj("Modificado con exito!");
				this.llenarTabla();
				this.ventanaModificarLoc.dispose();
			}
		}

		// ingresar una nueva localidad(validacion)
		else if (this.ventanaLocalidad != null
				&& e.getSource() == this.ventanaLocalidad.getBtnAgregar()) {
			if (ventanaLocalidad.getTextLocalidad().getText().isEmpty()) {
				setWarningMsj("La localidad no puede estar vacia!");

			} else {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,
						this.ventanaLocalidad.getTextLocalidad().getText());
				this.agenda.agregarLocalidad(nuevaLocalidad);
				setExitoMsj("Agregada con exito!");
				this.ventanaLocalidad.dispose();

			}

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

			java.sql.Date fecha = this.ventanaPersona.getDateChooser()
					.getDate() != null ? new java.sql.Date(this.ventanaPersona
					.getDateChooser().getDate().getTime()) : null;
			PersonaDTO nuevaPersona = new PersonaDTO(0, this.ventanaPersona
					.getTxtNombre().getText(), this.ventanaPersona
					.getTxtApellido().getText(), this.ventanaPersona
					.getTextTelefono().getText(), this.ventanaPersona
					.getTextMail().getText(), fecha, this.ventanaPersona
					.getTextCalle().getText(), this.ventanaPersona
					.getTextAltura().getText(), this.ventanaPersona
					.getTextPiso().getText(), this.ventanaPersona
					.getTextDepto().getText(), localidad, TipoContacto);

			try {
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				setExitoMsj("Agregado con exito!");
				this.ventanaPersona.dispose();
			} catch (Exception excepcionAgregarPersona) {
				JOptionPane.showMessageDialog(this.ventanaPersona,
						excepcionAgregarPersona.getMessage());
			}

		}
		// eliminar localidad y tipo de contacto
		else if (this.ventanaEliminarLoc != null
				&& e.getSource() == this.ventanaEliminarLoc.getBtnEliminar()) {
			if (this.ventanaEliminarLoc.isEliminaLoc() == true)

			{
				int cantidad = this.agenda
						.cantidadLocalidad((int) ((LocalidadDTO) ventanaEliminarLoc
								.getComboBoxEliminar().getSelectedItem())
								.getIdLocalidad());
				if ((cantidad == 0)) {

					this.agenda
							.borrarLocalidad(((LocalidadDTO) ventanaEliminarLoc
									.getComboBoxEliminar().getSelectedItem())
									.getIdLocalidad());
					setExitoMsj((ventanaEliminarLoc.getComboBoxEliminar()
							.getSelectedItem()).toString()
							+ " fue eliminada correctamente.");
					this.llenarTabla();

				} else {
					setWarningMsj((ventanaEliminarLoc.getComboBoxEliminar()
							.getSelectedItem()).toString()
							+ " no se puede eliminar ya que se encuentra en uso.");

				}
			} else

			{
				int cantidad = this.agenda
						.cantidadTipoContacto((int) ((TipoContactoDTO) ventanaEliminarLoc
								.getComboBoxEliminar().getSelectedItem())
								.getIdTipoContacto());
				if ((cantidad == 0)) {
					this.agenda
							.borrarTipoContacto(((TipoContactoDTO) ventanaEliminarLoc
									.getComboBoxEliminar().getSelectedItem())
									.getIdTipoContacto());
					setExitoMsj((ventanaEliminarLoc.getComboBoxEliminar()
							.getSelectedItem()).toString()
							+ " fue eliminada correctamente.");
					this.llenarTabla();

				} else {
					setWarningMsj((ventanaEliminarLoc.getComboBoxEliminar()
							.getSelectedItem()).toString()
							+ " no se puede eliminar ya que se encuentra en uso.");

				}
			}
			this.ventanaEliminarLoc.dispose();
		}

		// editar un contacto + validacion
		else {
			LocalidadDTO localidad = (LocalidadDTO) ventanaPersona
					.getListaLocalidades().getSelectedItem();
			TipoContactoDTO TipoContacto = (TipoContactoDTO) ventanaPersona
					.getListaTipoContacto().getSelectedItem();

			if (this.ventanaPersona.isEditar() == true) {

				PersonaDTO persona_a_editar = ventanaPersona
						.getPersonaAEditar();

				persona_a_editar.setNombre(this.ventanaPersona.getTxtNombre()
						.getText());
				persona_a_editar.setApellido(this.ventanaPersona
						.getTxtApellido().getText());
				persona_a_editar.setTelefono(this.ventanaPersona
						.getTextTelefono().getText());
				persona_a_editar.setMail(this.ventanaPersona.getTextMail()
						.getText());
				persona_a_editar.setFechanac(new java.sql.Date(
						this.ventanaPersona.getDateChooser().getDate()
								.getTime()));
				persona_a_editar.setCalle(this.ventanaPersona.getTextCalle()
						.getText());
				persona_a_editar.setAltura(this.ventanaPersona.getTextAltura()
						.getText());
				persona_a_editar.setPiso(this.ventanaPersona.getTextPiso()
						.getText());
				persona_a_editar.setDepto(this.ventanaPersona.getTextDepto()
						.getText());
				persona_a_editar.setLocalidad(localidad);
				persona_a_editar.setTipocontacto(TipoContacto);

				
				
				try {
					this.agenda.actualizarPersona(persona_a_editar);
					setExitoMsj("Modificado con exito!");
					this.llenarTabla();
					this.ventanaPersona.dispose();
				} catch (Exception excepcionAgregarPersona) {
					JOptionPane.showMessageDialog(this.ventanaPersona,
							excepcionAgregarPersona.getMessage());
				}
				

			} else {
				JOptionPane.showMessageDialog(this.vista.getFrame(),
						"Seleccione un contacto.");
			}
		}

	}

	public static void setWarningMsj(String text) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane optionPane = new JOptionPane(text,
				JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	public static void setExitoMsj(String text) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane optionPane = new JOptionPane(text,
				JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Exito!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

}
