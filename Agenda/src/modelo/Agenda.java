package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.LocalidadDAOImpl;
import persistencia.dao.mysql.PersonaDAOImpl;
import persistencia.dao.mysql.TipoContactoDAOImpl;


public class Agenda 
{
	private PersonaDAO persona;	
	private LocalidadDAO localidad;
	private TipoContactoDAO tipoContacto;
	
	
	public Agenda()
	{
		persona = new PersonaDAOImpl();
		localidad=new LocalidadDAOImpl();
		tipoContacto=new TipoContactoDAOImpl();
		
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		validarPersona(nuevaPersona);
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		persona.delete(persona_a_eliminar);
	}
	
	public void actualizarPersona(PersonaDTO persona_a_actualizar) {
		persona.update(persona_a_actualizar);
	}
	
	public void validarPersona(PersonaDTO persona) {
		
		if(persona.getNombre().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Nombre.");
		}
		if(persona.getApellido().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Apellido.");
		}		
		if(persona.getTelefono().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Teléfono.");
		}
		if(persona.getLocalidad().getLocalidad().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener una Localidad.");
		}
		if(persona.getFechanac() == null) {
			throw new IllegalArgumentException("El contacto tiene que tener una Fecha de Nacimiento.");
		}	
	}

//	public void ActualizarContacto(PersonaDTO Persona_a_actualizar) 
//	{
//		persona.update(Persona_a_actualizar);
//	}
//	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
//	public List<PersonaDTO> obtenerPersonasXLocalidad(int id)
//	{
//		return persona.readPorLocalidad(id);
//	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		
		localidad.insert(nuevaLocalidad);
	}

//	public int cantidadTipoContacto(int id)
//	{
//	
//	return	persona.cantidadTipoContacto(id);
//
//	}
	public void ActualizarLocalidad(LocalidadDTO Localidad_a_actualizar) 
	{
		localidad.update(Localidad_a_actualizar);
	}
	public void borrarLocalidad(int Localidad_a_eliminar) 
	{
		localidad.delete(Localidad_a_eliminar);
	}
	
//	public int cantidadLocalidad(int id)
//
//	{
//		return persona.cantidadLocalidad(id);
//	}
	

	
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return localidad.readAll();		
	}
	
	public void agregarTipoContacto(TipoContactoDTO nuevoTipoContacto)
	{
		tipoContacto.insert(nuevoTipoContacto);
	}

	public void ActualizarTipoContacto(TipoContactoDTO TipoContacto_a_actualizar) 
	{
		tipoContacto.update(TipoContacto_a_actualizar);
	}
	public void borrarTipoContacto(int TipoContacto_a_eliminar) 
	{
		tipoContacto.delete(TipoContacto_a_eliminar);
	}
	
	public List<TipoContactoDTO> obtenerTipoContacto()
	{
		return tipoContacto.readAll();		
	}
		
}
