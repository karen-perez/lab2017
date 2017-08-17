package persistencia.dao.mysql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOImpl implements LocalidadDAO 
{
	private static final String insert = "INSERT INTO localidades (Localidad) VALUES(?)";
	private static final String delete = "DELETE FROM localidades WHERE idlocalidad = ?";
	private static final String readall = "SELECT * FROM localidades";
	private static final String readByID = "SELECT * FROM localidades WHERE idLocalidad = ?";
	private static final String update= "UPDATE localidades SET Localidad=? WHERE idlocalidad= ?";
	private static final Conexion conexion = Conexion.getConexion();
	

	public boolean insert(LocalidadDTO localidad)
	{
		PreparedStatement statement;
		try 
		{
			
			
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getLocalidad());
			
			
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
	public boolean update(LocalidadDTO LocalidadModificar)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, LocalidadModificar.getLocalidad());
			statement.setInt(2, LocalidadModificar.getIdLocalidad());
			//System.out.println(statement.toString());
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
	
	
	public boolean delete(int Localidad_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, Localidad_a_eliminar);
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
	
	
	public LocalidadDTO read(int ID)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		LocalidadDTO localidad=null;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByID);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			//tengo que modificar esto!!!
		 if( resultSet.next())
			{
				
				
				localidad =new LocalidadDTO(
						resultSet.getInt("idLocalidad"), 
						resultSet.getString("localidad"))
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
		return localidad;
	}
	public List<LocalidadDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			//tengo que modificar esto!!!
			while(resultSet.next())
			{
				
				localidades.add(new LocalidadDTO(
						resultSet.getInt("idLocalidad"), 
						resultSet.getString("Localidad")
						)
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
		return localidades;
	
	}
	
	
}
