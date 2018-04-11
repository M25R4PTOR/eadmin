package es.fpdual.eadmin.eadmin.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentoContable extends Documento{

	private final BigDecimal importe;
	private final String dniInteresado;
	
	public DocumentoContable(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado,
			BigDecimal importe, String dniInteresado, Date fechaUltimaActualizacion) {
		super(codigo, nombre, fechaCreacion, publico, estado, fechaUltimaActualizacion);
		this.importe = importe;
		this.dniInteresado = dniInteresado;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public String getDNIInteresado() {
		return dniInteresado;
	}

	@Override
	public String toString() {
		return super.toString() + ", Importe: " + importe;
	}
}