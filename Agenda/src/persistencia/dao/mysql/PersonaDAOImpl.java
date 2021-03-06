package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;


public class PersonaDAOImpl implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(nombre, apellido, telefono, " 
			+ "mail, fechanac, calle, altura, piso, depto, idlocalidad, idcontacto ) " 
			+ "VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String update = "UPDATE personas SET nombre = ?, apellido = ?, telefono = ?, " 
			+ "mail = ?, fechanac = ?, calle = ?, altura = ?, piso = ?, depto = ?, idlocalidad = ?, idcontacto = ? " 
			+ "WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String localidad="SELECT * FROM personas WHERE idlocalidad=?";
	private static final String localidad2="SELECT COUNT(*) FROM personas WHERE idlocalidad=?";
	private static final String tipoContacto="SELECT COUNT(*) FROM personas WHERE IdContacto=?";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getMail());
			statement.setDate(5, persona.getFechanac());
			statement.setString(6, persona.getCalle());
			statement.setString(7, persona.getAltura());
			statement.setString(8, persona.getPiso());
			statement.setString(9, persona.getDepto());
			statement.setInt(10, persona.getLocalidad().getIdLocalidad());
			statement.setInt(11, persona.getTipocontacto().getIdTipoContacto());
			
			
			
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
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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
	
	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getMail());
			statement.setDate(5, persona.getFechanac());
			statement.setString(6, persona.getCalle());
			statement.setString(7, persona.getAltura());
			statement.setString(8, persona.getPiso());
			statement.setString(9, persona.getDepto());
			statement.setInt(10, persona.getLocalidad().getIdLocalidad());
			statement.setInt(11, persona.getTipocontacto().getIdTipoContacto());
			statement.setInt(12, persona.getIdPersona());
						
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
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), 
						resultSet.getString("Nombre"), 
						resultSet.getString("Apellido"),
						resultSet.getString("Telefono"),
						resultSet.getString("Mail"),
						resultSet.getDate("FechaNac"),
						resultSet.getString("Calle"),
						resultSet.getString("Altura"),
						resultSet.getString("Piso"),
						resultSet.getString("Depto"),
					   new LocalidadDAOImpl().read(resultSet.getInt("idLocalidad")),
						new TipoContactoDAOImpl().read(resultSet.getInt("idContacto"))
						));
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
		return personas;
	}
	
	public int cantidadLocalidad(int id)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
	    int cantidad=0;
	    try 
		{
	    statement = conexion.getSQLConexion().prepareStatement(localidad2);
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		if(resultSet.next())
			{
		 cantidad=resultSet.getInt(1);
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
	    
	    return cantidad;
	}


		public List<PersonaDTO> readPorLocalidad(int id) {
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(localidad);
				statement.setInt(1, id);
				resultSet = statement.executeQuery();
				//tengo que modificar esto!!!
			
				while(resultSet.next())
				{
					
					personas.add(new PersonaDTO(resultSet.getInt("idPersona"), 
							resultSet.getString("Nombre"), 
							resultSet.getString("Apellido"),
							resultSet.getString("Telefono"),
							resultSet.getString("Mail"),
							resultSet.getDate("FechaNac"),
							resultSet.getString("Calle"),
							resultSet.getString("Altura"),
							resultSet.getString("Piso"),
							resultSet.getString("Depto"),
						   new LocalidadDAOImpl().read(resultSet.getInt("idLocalidad")),
							new TipoContactoDAOImpl().read(resultSet.getInt("idContacto"))
							));
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
			return personas;
		}
		
		
		public int cantidadTipoContacto(int id)
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
		    int cantidad=0;
		    try 
		 	{
		     statement = conexion.getSQLConexion().prepareStatement(tipoContacto);
		 	statement.setInt(1, id);
		 	resultSet = statement.executeQuery();
		 	if(resultSet.next())
		 		{
		 	 cantidad=resultSet.getInt(1);
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
		     
		     return cantidad;   
			
		}
			
}
