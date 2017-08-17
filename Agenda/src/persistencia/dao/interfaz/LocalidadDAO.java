package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {
	public boolean insert(LocalidadDTO localidad);
	
	public boolean update(LocalidadDTO localidadModificar);
	
	public boolean delete(int localidad_a_eliminar);
	
	public List<LocalidadDTO> readAll();

}
