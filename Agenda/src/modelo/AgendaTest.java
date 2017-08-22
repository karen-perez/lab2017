package modelo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class AgendaTest {

	private static Agenda agenda;
	
	private static Agenda agendaInstance() {
		if(agenda == null) {
			return new Agenda();
		} else {
			return agenda;
		}
	}
	
	private PersonaDTO cargarPersona() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
        	Date parsed = format.parse("01/03/1987");
        	java.sql.Date sql = new java.sql.Date(parsed.getTime());
    		PersonaDTO persona = new PersonaDTO(0, "Prueba", "Prueba", 
    				"01146663333", "prueba@test.com.ar", sql, "Prueba", "123", "1", "B", 
    				new LocalidadDTO(1, ""), new TipoContactoDTO(1, ""));
    		return persona;
		} catch (Exception e) {
			return null;
		}
		
        
	}
	
	@Test
	public void obtenerLocalidadesTest() {
		Agenda agenda = new Agenda();
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		assertTrue(localidades.size() > 0);
	}
	
	@Test
	public void eliminarLocalidadTest() {
		Agenda agenda = new Agenda();
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		int tamanioInicial = localidades.size();
		LocalidadDTO localidad = new LocalidadDTO(0, "Test");
		for (LocalidadDTO loc : localidades) {
			if(loc.getLocalidad().equals("Moreno")) {
				localidad = loc;
			}
		}
		agenda.borrarLocalidad(localidad.getIdLocalidad());
		localidades = agenda.obtenerLocalidades();
		int tamanioFinal = localidades.size();
		assertTrue(tamanioInicial - 1 == tamanioFinal);		
	}
	
	@Test 
	public void agregarLocalidadTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno");
		Agenda agenda = new Agenda();		
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		int tamanioInicial = localidades.size();
		agenda.agregarLocalidad(localidad);
		localidades = agenda.obtenerLocalidades();
		int tamanioFinal = localidades.size();
		assertTrue(tamanioInicial + 1 == tamanioFinal);		
	}
	
	@Test 
	public void actualizarLocalidadTest() {
		
		Agenda agenda = new Agenda();
		LocalidadDTO localidad = agenda.obtenerLocalidades().get(0);
		String loc = localidad.getLocalidad();
		localidad.setLocalidad(loc + " ");
		agenda.actualizarLocalidad(localidad);		
		localidad = agenda.obtenerLocalidades().get(0);
		assertFalse(loc.equals(localidad.getLocalidad()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadVaciaTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "");
		Agenda agenda = new Agenda();		
		agenda.agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadExistenteTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno");
		Agenda agenda = new Agenda();		
		agenda.agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadExistenteConEspaciosTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "Moreno  ");
		Agenda agenda = new Agenda();		
		agenda.agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadEspaciosVaciosTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "   ");
		Agenda agenda = new Agenda();		
		agenda.agregarLocalidad(localidad);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void modificarLocalidadVaciaTest() {
				
		Agenda agenda = new Agenda();
		LocalidadDTO localidad = agenda.obtenerLocalidades().get(0);
		localidad.setLocalidad("");
		agenda.actualizarLocalidad(localidad);
	
	}
	
	@Test
	public void obtenerTipoContactoTest() {
		Agenda agenda = new Agenda();
		List<TipoContactoDTO> tiposContacto = agenda.obtenerTipoContacto();
		assertTrue(tiposContacto.size() > 0);
	}
	
	@Test
	public void eliminarTipoContactoTest() {
		Agenda agenda = new Agenda();
		List<TipoContactoDTO> tiposContacto = agenda.obtenerTipoContacto();
		int tamanioInicial = tiposContacto.size();
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "Test");
		for (TipoContactoDTO loc : tiposContacto) {
			if(loc.getTipoContacto().equals("CONOCIDO")) {
				tipoContacto = loc;
			}
		}
		agenda.borrarTipoContacto(tipoContacto.getIdTipoContacto());
		tiposContacto = agenda.obtenerTipoContacto();
		int tamanioFinal = tiposContacto.size();
		assertTrue(tamanioInicial - 1 == tamanioFinal);		
	}
	
	@Test 
	public void agregarTipoContactoTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "CONOCIDO");
		Agenda agenda = new Agenda();		
		List<TipoContactoDTO> tiposContacto = agenda.obtenerTipoContacto();
		int tamanioInicial = tiposContacto.size();
		agenda.agregarTipoContacto(tipoContacto);
		tiposContacto = agenda.obtenerTipoContacto();
		int tamanioFinal = tiposContacto.size();
		assertTrue(tamanioInicial + 1 == tamanioFinal);		
	}
	
	@Test 
	public void actualizarTipoContactoTest() {
		
		Agenda agenda = new Agenda();
		TipoContactoDTO tipoContacto = agenda.obtenerTipoContacto().get(0);
		String tipo = tipoContacto.getTipoContacto();
		tipoContacto.setTipoContacto(tipo + " ");
		agenda.actualizarTipoContacto(tipoContacto);		
		tipoContacto = agenda.obtenerTipoContacto().get(0);
		assertFalse(tipo.equals(tipoContacto.getTipoContacto()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoVacioTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "");
		Agenda agenda = new Agenda();		
		agenda.agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoExistenteTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "CONOCIDO");
		Agenda agenda = new Agenda();		
		agenda.agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoExistenteConEspaciosTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, " CONOCIDO ");
		Agenda agenda = new Agenda();		
		agenda.agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarTipoContactoEspaciosVaciosTest() {
		
		TipoContactoDTO tipoContacto = new TipoContactoDTO(0, "    ");
		Agenda agenda = new Agenda();		
		agenda.agregarTipoContacto(tipoContacto);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void modificarTipoContactoVacioTest() {
				
		Agenda agenda = new Agenda();
		TipoContactoDTO tipoContacto = agenda.obtenerTipoContacto().get(0);
		tipoContacto.setTipoContacto("");
		agenda.actualizarTipoContacto(tipoContacto);
	
	}

	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinNombreTest() {
		PersonaDTO persona = cargarPersona();
		persona.setNombre("");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinApellidoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setApellido("");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinTelefonoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setTelefono("");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinFechaNacimientoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setFechanac(null);
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinLocalidadTest() {
		PersonaDTO persona = cargarPersona();
		persona.setLocalidad(null);
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoSinTipoContactoTest() {
		PersonaDTO persona = cargarPersona();
		persona.setTipocontacto(null);
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailSinArrobaTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("pruebasinarroba");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailArrobaFinalTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("pruebaarrobafinal.com@");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailArrobaInicioTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("@pruebaarrobainicio.com");
		agendaInstance().agregarPersona(persona);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarContactoMailSinPuntoComTest() {
		PersonaDTO persona = cargarPersona();
		persona.setMail("prueba@sinpuntocom");
		agendaInstance().agregarPersona(persona);
	}	
	
	
	@Test
	public void agregarContactoTest() {
		
	}
	
	@Test
	public void eliminarContactoTest() {
		
	}
	
	@Test
	public void modificarContactoTest() {
		
	}
	
	public void obtenerContactosTest() {
		
	}
}
