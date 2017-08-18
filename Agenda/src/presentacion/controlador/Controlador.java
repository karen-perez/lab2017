package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEliminarLoc;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.PersonaDTO;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		private VentanaLocalidad ventanaLocalidad;
		private VentanaTipoContacto ventanaTipoContacto;
		private VentanaEliminarLoc ventanaEliminarLoc;

		
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			
			//aca se agregan los items de los menues
			
			//agregar
			this.vista.getMntmContacto().addActionListener(this);
			this.vista.getMntmLocalidad().addActionListener(this);
			this.vista.getMntmTipoDeContacto().addActionListener(this);
			
			//modificar
//			this.vista.getMntmContacto_1().addActionListener(this);
//			this.vista.getMntmLocalidad_1().addActionListener(this);
//			this.vista.getMntmTipoDeContacto_1().addActionListener(this);
			
			//eliminar

//			this.vista.getMntmContacto_2().addActionListener(this);
			this.vista.getMntmLocalidad_2().addActionListener(this);
//			this.vista.getMntmTipoDeContacto_2().addActionListener(this);
			
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}
		
		public void inicializar()
		{
			this.llenarTabla();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(),
						this.personas_en_tabla.get(i).getApellido(),
						this.personas_en_tabla.get(i).getTelefono(),
						this.personas_en_tabla.get(i).getMail(),
						this.personas_en_tabla.get(i).getFechanac(),
						this.personas_en_tabla.get(i).getCalle(),
						this.personas_en_tabla.get(i).getAltura(),
						this.personas_en_tabla.get(i).getDepto(),
						this.personas_en_tabla.get(i).getPiso(),
						this.personas_en_tabla.get(i).getLocalidad(),
						this.personas_en_tabla.get(i).getTipocontacto(),
						};
				this.vista.getModelPersonas().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 	
		{
			//aca se abre el menu de agregar o presionando el boton de agregar, 
			//ambos abren la ventana para agregar un nuevo contacto
			if (e.getSource() == this.vista.getBtnAgregar() || e.getSource() == this.vista.getMntmContacto()) {
				DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
				for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
					localidadModel.addElement(loc);
				}
				
				DefaultComboBoxModel<TipoContactoDTO> tipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
				for (TipoContactoDTO tc : agenda.obtenerTipoContacto()) {
					tipoContactoModel.addElement(tc);
				}
				
				this.ventanaPersona = new VentanaPersona(this);
				this.ventanaPersona.getListaLocalidades().setModel(localidadModel);		
				this.ventanaPersona.getListaTipoContacto().setModel(tipoContactoModel);
				}
			
			
			
			//abre la ventana para agregar una nueva localidad
			else if (e.getSource() == this.vista.getMntmLocalidad()){		
				
				this.ventanaLocalidad= new VentanaLocalidad(this);
			}
			//abre la ventana para agregar un nuevo tipo de contacto
			else if (e.getSource() == this.vista.getMntmTipoDeContacto()){		
				
				this.ventanaTipoContacto= new VentanaTipoContacto(this);
			}
			
			//abre la ventana para eliminar una localidad
			else if (e.getSource() == this.vista.getMntmLocalidad_2())
			{
				DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
				for (LocalidadDTO loc : agenda.obtenerLocalidades()) {
					localidadModel.addElement(loc);
				}
				
				this.ventanaEliminarLoc = new VentanaEliminarLoc(this, localidadModel,null);
				
			}
	
			
			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				
				this.llenarTabla();
				
			}
			
			else if(e.getSource() == this.vista.getBtnReporte())
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			
			
			//aca agrega una persona.. no lo probe todavia!
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
								
				LocalidadDTO localidad = (LocalidadDTO)ventanaPersona.getListaLocalidades().getSelectedItem();
				TipoContactoDTO TipoContacto= (TipoContactoDTO)ventanaPersona.getListaTipoContacto().getSelectedItem();

				PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), this.ventanaPersona.getTxtApellido().getText(),
														this.ventanaPersona.getTextTelefono().getText(), this.ventanaPersona.getTextMail().getText(), 
														new java.sql.Date(this.ventanaPersona.getDateChooser().getDate().getTime()), this.ventanaPersona.getTextCalle().getText(),
														this.ventanaPersona.getTextAltura().getText(),this.ventanaPersona.getTextPiso().getText(),
														this.ventanaPersona.getTextDepto().getText(), localidad, TipoContacto);
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}
		}

}
