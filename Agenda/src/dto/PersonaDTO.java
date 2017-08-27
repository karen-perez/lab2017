package dto;

import java.sql.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String apellido;
	private String telefono;
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private LocalidadDTO localidad;
	private String mail;
	private TipoContactoDTO tipocontacto;
	private Date fechanac;
	

	public PersonaDTO(int idPersona, String nombre, String apellido, String telefono, String mail,
			Date fechanac, String calle, String altura, String piso, String depto, LocalidadDTO localidad, TipoContactoDTO tipocontacto)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.mail = mail;
		this.tipocontacto = tipocontacto;
		this.fechanac = fechanac;
	}
	
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getAltura() {
		return altura;
	}


	public void setAltura(String altura) {
		this.altura = altura;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public String getDepto() {
		return depto;
	}


	public void setDepto(String depto) {
		this.depto = depto;
	}


	public LocalidadDTO getLocalidad() {
		return localidad;
	}


	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public TipoContactoDTO getTipocontacto() {
		return tipocontacto;
	}


	public void setTipocontacto(TipoContactoDTO tipocontacto) {
		this.tipocontacto = tipocontacto;
	}


	public Date getFechanac() {
		return fechanac;
	}


	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}
	
}
