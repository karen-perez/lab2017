package modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dto.LocalidadDTO;

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
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarLocalidadVaciaTest() {
		
		LocalidadDTO localidad = new LocalidadDTO(0, "");
		Agenda agenda = new Agenda();		
		agenda.agregarLocalidad(localidad);
	
	}
	
}
