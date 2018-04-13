package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class BaseAdministracion {

	private final Integer codigo;
	private final String nombre;
	private final Date fechaCreacion;
	private final Boolean publico;
	private final Date fechaUltimaActualizacion;
	
	public BaseAdministracion(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, Date fechaUltimaActualizacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.publico = publico;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Boolean getPublico() {
		return publico;
	}
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	
	@Override
	public int hashCode() {
		final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		
		hashCodeBuilder.append(codigo);
		hashCodeBuilder.append(nombre);
		hashCodeBuilder.append(fechaCreacion);
		hashCodeBuilder.append(publico);
		hashCodeBuilder.append(fechaUltimaActualizacion);
		
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseAdministracion) {
			final BaseAdministracion param = (BaseAdministracion)obj;
			final EqualsBuilder equalsBuilder = new EqualsBuilder();
			
			equalsBuilder.append(this.codigo, param.codigo);
			equalsBuilder.append(this.nombre, param.nombre);
			equalsBuilder.append(this.fechaCreacion, param.fechaCreacion);
			equalsBuilder.append(this.publico, param.publico);
			equalsBuilder.append(this.fechaUltimaActualizacion, param.fechaUltimaActualizacion);
			
			return equalsBuilder.isEquals();
		}
		return false;
	}
}
