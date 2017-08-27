package dto;

public class LocalidadDTO implements Comparable<LocalidadDTO> {
private int idLocalidad;
private String localidad;


public LocalidadDTO(int idLocalidad, String localidad) {
	
	this.idLocalidad = idLocalidad;
	this.localidad = localidad;
}


public int getIdLocalidad() {
	return idLocalidad;
}


public void setIdLocalidad(int idLocalidad) {
	this.idLocalidad = idLocalidad;
}


public String getLocalidad() {
	return localidad;
}


public void setLocalidad(String localidad) {
	this.localidad = localidad;
}

@Override
	public String toString() {
		
		return localidad;
	}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + idLocalidad;
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
	LocalidadDTO other = (LocalidadDTO) obj;
	if (localidad.trim().toLowerCase().equals(other.localidad.trim().toLowerCase()))
		return true;	
	if (idLocalidad != other.idLocalidad)
		return false;	
	return true;
}


@Override
public int compareTo(LocalidadDTO other) {
	// TODO Auto-generated method stub
	int last = this.localidad.compareTo(other.localidad);
    return last == 0 ? this.localidad.compareTo(other.localidad) : last;
}



}
