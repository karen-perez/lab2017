package persistencia.dao.interfaz;

import java.util.List;

import dto.PersonaDTO;

public interface PersonaDAO 
{
	
	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona_a_eliminar);
	
	public boolean update(PersonaDTO persona);
	
	public List<PersonaDTO> readAll();
	public List<PersonaDTO> readPorLocalidad(int id);

	public int cantidadLocalidad(int id);
	public int cantidadTipoContacto(int id);

}
