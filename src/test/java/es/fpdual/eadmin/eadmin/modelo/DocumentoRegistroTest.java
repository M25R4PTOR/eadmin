package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DocumentoRegistroTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final String DNI_INTERESADO = "12345678M";
	private static final String CODIGO_REGISTRO = "0000";
	private static final DocumentoRegistro EJEMPLO = new DocumentoRegistro(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, DNI_INTERESADO, CODIGO_REGISTRO);

	@Test
	public void testGetters() {
		assertEquals(CODIGO_REGISTRO, EJEMPLO.getCodigoRegistro());
		assertEquals(DNI_INTERESADO, EJEMPLO.getDniInteresado());
	}
	
	@Test 
	public void testEquals() {
		DocumentoRegistro ejemplo1 = new DocumentoRegistro(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, DNI_INTERESADO, CODIGO_REGISTRO);
		assertEquals(EJEMPLO, ejemplo1);
	}
}
