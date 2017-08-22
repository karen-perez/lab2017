package dto;

public class TipoContactoDTO {
	private int idTipoContacto;
	private String tipoContacto;
	
	
	public TipoContactoDTO(int idTipoContacto, String tipoContacto) {
		this.idTipoContacto = idTipoContacto;
		this.tipoContacto = tipoContacto;
	}


	public int getIdTipoContacto() {
		return idTipoContacto;
	}


	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}


	public String getTipoContacto() {
		return tipoContacto;
	}


	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	@Override
	public String toString() {
		
		return tipoContacto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoContacto;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoContactoDTO other = (TipoContactoDTO) obj;
		if (tipoContacto.toLowerCase().equals(other.tipoContacto.toLowerCase()))
			return true;
		if (idTipoContacto != other.idTipoContacto)
			return false;		
		return true;
	}
	
}
