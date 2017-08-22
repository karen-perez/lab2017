package modelo;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;

public class AgendaTest {

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
	public void modificarTipoContactoVacioTest() {
				
		Agenda agenda = new Agenda();
		TipoContactoDTO tipoContacto = agenda.obtenerTipoContacto().get(0);
		tipoContacto.setTipoContacto("");
		agenda.actualizarTipoContacto(tipoContacto);
	
	}
	
}
