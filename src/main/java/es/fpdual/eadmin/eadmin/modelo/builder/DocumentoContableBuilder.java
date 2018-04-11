package es.fpdual.eadmin.eadmin.modelo.builder;

import java.math.BigDecimal;

import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;

public class DocumentoContableBuilder extends DocumentoBuilder{

	private BigDecimal importe;
	private String dniInteresado;
	
	public DocumentoContable contruir() {
		return new DocumentoContable(this.codigo, this.nombre, this.fechaCreacion, this.publico, this.estado, this.importe, this.dniInteresado, this.fechaUltimaActualizacion);
	}
	
	public DocumentoContableBuilder conImporte(BigDecimal importe) {
		this.importe = importe;
		return this;
	}
	
	public DocumentoContableBuilder conDNIInteresado(String dniInteresado) {
		this.dniInteresado = dniInteresado;
		return this;
	}
	
	public DocumentoContableBuilder clonar(DocumentoContable documentoContable) {
		super.clonar(documentoContable);
		this.importe = documentoContable.getImporte();
		this.dniInteresado = documentoContable.getDNIInteresado();
		return this;
	}
}
