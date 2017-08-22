package modelo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		validarPersona(persona_a_actualizar);
		persona.update(persona_a_actualizar);
	}
	public int cantidadTipoContacto(int id)
	{
	
	return	persona.cantidadTipoContacto(id);

	}

	public int cantidadLocalidad(int id)

	{
		return persona.cantidadLocalidad(id);
	}

	
	void validarPersona(PersonaDTO persona) {
		
		if(persona.getNombre().trim().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Nombre.");
		}
		if(persona.getApellido().trim().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Apellido.");
		}		
		if(persona.getTelefono().trim().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Teléfono.");
		}
		if(persona.getLocalidad().getLocalidad().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener una Localidad.");
		}
		if(persona.getTipocontacto().getTipoContacto().equals("")) {
			throw new IllegalArgumentException("El contacto tiene que tener un Tipo de contacto.");
		}
		if(persona.getFechanac() == null) {
			throw new IllegalArgumentException("El contacto tiene que tener una Fecha de Nacimiento.");
		}	
		if(persona.getMail().trim().length() > 0 && !mailValido(persona.getMail())) {
			throw new IllegalArgumentException("El mail no tiene un formato valido.");
		}
	}

	boolean mailValido(String email) {
		boolean status = false;
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = Pattern.compile(ePattern);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		validarLocalidad(nuevaLocalidad);
		localidad.insert(nuevaLocalidad);
	}

	public void actualizarLocalidad(LocalidadDTO Localidad_a_actualizar) 
	{
		validarLocalidad(Localidad_a_actualizar);
		localidad.update(Localidad_a_actualizar);
	}
	public void borrarLocalidad(int Localidad_a_eliminar) 
	{
		localidad.delete(Localidad_a_eliminar);
	}
	
	private void validarLocalidad(LocalidadDTO localidad) {
		if(localidad.getLocalidad().trim().equals("")) {
			throw new IllegalArgumentException("La Localidad tiene que tener un Nombre.");
		}
		List<LocalidadDTO> localidades = obtenerLocalidades();
		if(localidad.getIdLocalidad() == 0 && localidades.contains(localidad)) {
			throw new IllegalArgumentException("La Localidad ingresada ya existe.");
		}
	}
	
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return localidad.readAll();		
	}
	
	public void agregarTipoContacto(TipoContactoDTO nuevoTipoContacto)
	{
		validarTipoContacto(nuevoTipoContacto);
		tipoContacto.insert(nuevoTipoContacto);
	}
	
	private void validarTipoContacto(TipoContactoDTO tipoContacto) {
		if(tipoContacto.getTipoContacto().trim().equals("")) {
			throw new IllegalArgumentException("El Tipo de Contacto tiene que tener un Nombre.");
		}
		List<TipoContactoDTO> tiposContacto = obtenerTipoContacto();
		if(tipoContacto.getIdTipoContacto() == 0 && tiposContacto.contains(tipoContacto)) {
			throw new IllegalArgumentException("El Tipo de Contacto ingresado ya existe.");
		}		
	}

	public void actualizarTipoContacto(TipoContactoDTO TipoContacto_a_actualizar) 
	{
		validarTipoContacto(TipoContacto_a_actualizar);
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
