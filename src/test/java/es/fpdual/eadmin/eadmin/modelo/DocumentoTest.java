package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class DocumentoTest extends AbstractoModeloBeanTest<Documento> {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;

	@Override
	public void before() {
		this.entityA1 = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, FECHA_CREACION);
		this.entityA2 = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, FECHA_CREACION);
		this.entityB = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ELIMINADO, FECHA_CREACION);
	}

	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(EstadoDocumento.ACTIVO, this.entityA1.getEstado());
		
	}
}
