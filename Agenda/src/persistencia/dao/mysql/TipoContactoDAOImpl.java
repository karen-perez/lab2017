package persistencia.dao.mysql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;
import dto.TipoContactoDTO;
public class TipoContactoDAOImpl implements TipoContactoDAO {
	private static final String insert = "INSERT INTO tipoContacto(TipoContacto) VALUES(?)";
	private static final String delete = "DELETE FROM tipoContacto WHERE idContacto = ?";
	private static final String readall = "SELECT * FROM tipoContacto";
	private static final String readByID = "SELECT * FROM tipoContacto WHERE idContacto = ?";
	private static final String update= "UPDATE tipoContacto SET TipoContacto=? WHERE idContacto= ?";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(TipoContactoDTO TipoContacto)
	{
		PreparedStatement statement;
		try 
		{
			
			
			statement = conexion.getSQLConexion().prepareStatement(insert);
			//statement.setInt(1, TipoContacto.getIdTipoContacto());
			
			statement.setString(1, TipoContacto.getTipoContacto());
			
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	
	public boolean update(TipoContactoDTO TipoContactoModificar)
	{
		PreparedStatement statement;

		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, TipoContactoModificar.getTipoContacto());
			statement.setString(2, Integer.toString(TipoContactoModificar.getIdTipoContacto()));
		
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	public boolean delete(int TipoContacto_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, TipoContacto_a_eliminar);
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	
	
	
	
	public TipoContactoDTO read(int ID)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		TipoContactoDTO contactoTipo=null;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByID);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			//tengo que modificar esto!!!
		 if( resultSet.next())
			{
				
				
				contactoTipo =new TipoContactoDTO(
						resultSet.getInt("idContacto"), 
						resultSet.getString("TipoContacto"))
						;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return contactoTipo;
	}
	  
	
	
	public List<TipoContactoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> contactoTipo = new ArrayList<TipoContactoDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			//tengo que modificar esto!!!
			while(resultSet.next())
			{
				
				
				contactoTipo.add(new TipoContactoDTO(
						resultSet.getInt("idContacto"), 
						resultSet.getString("TipoContacto"))
						);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return contactoTipo;
	}
}
