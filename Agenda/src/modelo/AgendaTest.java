package modelo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class AgendaTest {

	private static Agenda instancia;
	
	private static Agenda agenda() {
		if(instancia == null) {
			instancia = new Agenda();
		}
		return instancia;		
	}
	
	private PersonaDTO cargarPersona() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
        	Date parsed = format.parse("01/03/1987");
        	java.sql.Date sql = new java.sql.Date(parsed.getTime());
    		PersonaDTO persona = new PersonaDTO(0, "Prueba", "Prueba", 
    				"01146663333", "prueba@test.com.ar", sql, "Prueba", "123", "1", "B", 
    				new LocalidadDTO(1, "Prueba"), new TipoContactoDTO(1, "Prueba"));
    		return persona;
		} catch (Exception e) {
			return null;
		}
		
        
	}
	
	@Test
	public void obtenerLocalidadesTest() {
		List<LocalidadDTO> localidades = agenda().obtenerLocalidades();
		assertTrue(localidades.size() > 0);
	}
	
	@Test
	public void eliminarLocalidadTest() {
		List<LocalidadDTO> localidades = agenda().obtenerLocalidades();
		int tamanioInicial = localidades.size();
		LocalidadDTO localidad = localidades.get(localidades.size() - 1);
		agenda().borrarLocalidad(localidad.getIdLocalidad());
		localidades = agenda().obtenerLocalidades();
		int tamanioFinal = localidades.size();
		assertTrue(tamanioInicial - 1 == tamanioFinal);		
	}
	
	@Test 
	public void agregarLocalidadTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno");	
		List<LocalidadDTO> localidades = agenda().obtenerLocalidades();
		int tamanioInicial = localidades.size();
		agenda().agregarLocalidad(localidad);
		localidades = agenda().obtenerLocalidades();
		int tamanioFinal = localidades.size();
		assertTrue(tamanioInicial + 1 == tamanioFinal);		
	}
	
	@Test 
	public void actualizarLocalidadTest() {
		
		LocalidadDTO localidad = agenda().obtenerLocalidades().get(0);
		String loc = localidad.getLocalidad();
		localidad.setLocalidad(loc + " ");
		agenda().actualizarLocalidad(localidad);		
		localidad = agenda().obtenerLocalidades().get(0);
		assertFalse(loc.equals(localidad.getLocalidad()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadVaciaTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "");		
		agenda().agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadExistenteTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno");
		agenda().agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadExistenteConEspaciosTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno  ");
		agenda().agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadEspaciosVaciosTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "   ");
		agenda().agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void modificarLocalidadVaciaTest() {
				
		LocalidadDTO localidad = agenda().obtenerLocalidades().get(0);
		localidad.setLocalidad("");
		agenda().actualizarLocalidad(localidad);
	
	}
	
	@Test
	public void obtenerTipoContactoTest() {
		List<TipoContactoDTO> tiposContacto = agenda().obtenerTipoContacto();
		assertTrue(tiposContacto.size() > 0);
	}
	
	@Test
	public void eliminarTipoContactoTest() {
		List<TipoContactoDTO> tiposContacto = agenda().obtenerTipoContacto();
		int tamanioInicial = tiposContacto.size();
		TipoContactoDTO tipoContacto = tiposContacto.get(tiposContacto.size() - 1);
		agenda().borrarTipoContacto(tipoContacto.getIdTipoContacto());
		tiposContacto = agenda().obtenerTipoContacto();
		int tamanioFinal = tiposContacto.size();
		assertTrue(tamanioInicial - 1 == tamanioFinal);		
	}
	
	@Test 
	public void agregarTipoContactoTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "CONOCIDO");
		List<TipoContactoDTO> tiposContacto = agenda().obtenerTipoContacto();
		int tamanioInicial = tiposContacto.size();
		agenda().agregarTipoContacto(tipoContacto);
		tiposContacto = agenda().obtenerTipoContacto();
		int tamanioFinal = tiposContacto.size();
		assertTrue(tamanioInicial + 1 == tamanioFinal);		
	}
	
	@Test 
	public void actualizarTipoContactoTest() {
	
		TipoContactoDTO tipoContacto = agenda().obtenerTipoContacto().get(0);
		String tipo = tipoContacto.getTipoContacto();
		tipoContacto.setTipoContacto(tipo.trim() + " ");
		agenda().actualizarTipoContacto(tipoContacto);		
		tipoContacto = agenda().obtenerTipoContacto().get(0);
		assertFalse(tipo.equals(tipoContacto.getTipoContacto()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoVacioTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "");
		agenda().agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoExistenteTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "CONOCIDO");	
		agenda().agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoExistenteConEspaciosTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, " CONOCIDO ");
		agenda().agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoEspaciosVaciosTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "    ");
		agenda().agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void modificarTipoContactoVacioTest() {
				
		TipoContactoDTO tipoContacto = agenda().obtenerTipoContacto().get(0);
		tipoContacto.setTipoContacto("");
		agenda().actualizarTipoContacto(tipoContacto);
	
	}

	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinNombreTest() {
		PersonaDTO persona = cargarPersona();
		persona.setNombre("");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinApellidoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setApellido("");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinTelefonoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setTelefono("");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinFechaNacimientoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setFechanac(null);
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinLocalidadTest() {
		PersonaDTO persona = cargarPersona();
		persona.setLocalidad(null);
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinTipoContactoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setTipocontacto(null);
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailSinArrobaTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("pruebasinarroba");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailArrobaFinalTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("pruebaarrobafinal.com@");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailArrobaInicioTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("@pruebaarrobainicio.com");
		agenda().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailSinPuntoComTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("prueba@sinpuntocom");
		agenda().agregarPersona(persona);
	}	
	
	
	@Test
	public void agregarContactoTest() {
		List<PersonaDTO> personas = agenda().obtenerPersonas();
		int tamanioInicial = personas.size();
		agenda().agregarPersona(cargarPersona());
		personas = agenda().obtenerPersonas();
		int tamanioFinal = personas.size();
		assertTrue(tamanioInicial + 1 == tamanioFinal);	
	}
	
	@Test
	public void eliminarContactoTest() {
		List<PersonaDTO> personas = agenda().obtenerPersonas();
		int tamanioInicial = personas.size();
		PersonaDTO persona = personas.get(personas.size() - 1);
		agenda().borrarPersona(persona);
		personas = agenda().obtenerPersonas();
		int tamanioFinal = personas.size();
		assertTrue(tamanioInicial - 1 == tamanioFinal);		
	}
	
	@Test
	public void obtenerContactosTest() {		
		List<PersonaDTO> personas = agenda().obtenerPersonas();
		assertTrue(personas.size() > 0);
	}
}
