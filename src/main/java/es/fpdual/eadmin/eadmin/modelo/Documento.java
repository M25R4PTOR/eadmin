package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Documento extends BaseAdministracion{

	private final EstadoDocumento estado;

	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado, Date fechaUltimaActualizacion) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaActualizacion);
		this.estado = estado;
	}
	
//	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, Integer estado, Date fechaUltimaActualizacion) {
//		super(codigo, nombre, fechaCreacion, publico, fechaUltimaActualizacion);
//		switch (estado) {
//		case 1:
//			this.estado = EstadoDocumento.ACTIVO;
//			break;
//		case 2:
//			this.estado = EstadoDocumento.APROBADO;
//			break;
//		case 3:
//			this.estado = EstadoDocumento.ELIMINADO;
//			break;
//
//		default:
//			this.estado = null;
//			break;
//		} //Igual que la siguiente linea de codigo
//		this.estado = Optional.ofNullable(estado).map(EstadoDocumento::obtenerPorCodigo).orElse(null);
//	}

	public EstadoDocumento getEstado() {
		return estado;
	}

	@Override
	public int hashCode() {
		final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		
		hashCodeBuilder.appendSuper(super.hashCode());
		hashCodeBuilder.append(this.estado);
		
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Documento) {
			final Documento param = (Documento)obj;
			final EqualsBuilder equalsBuilder = new EqualsBuilder();
			
			equalsBuilder.appendSuper(super.equals(param));
			equalsBuilder.append(this.estado, param.estado);
			
			return equalsBuilder.isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
