package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento extends BaseAdministracion{

	private final EstadoDocumento estado;

	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado, Date fechaUltimaActualizacion) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaActualizacion);
		this.estado = estado;
	}

	public EstadoDocumento getEstado() {
		return estado;
	}

	@Override
	public int hashCode() {
		return getCodigo().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Documento) {
			return getCodigo().equals(((Documento) obj).getCodigo());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Documento con código " + getCodigo();
	}
}
