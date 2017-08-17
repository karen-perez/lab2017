package persistencia.dao.interfaz;
import dto.TipoContactoDTO;
import java.util.List;


public interface TipoContactoDAO {
	
	public boolean insert(TipoContactoDTO TipoContacto);

	public boolean delete(int tipoContacto_a_eliminar);
	
	public boolean update(TipoContactoDTO tipoContacto_a_actualizar);

	
	public List<TipoContactoDTO> readAll();
}




