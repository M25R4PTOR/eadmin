package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class DocumentoContableTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final BigDecimal IMPORTE = new BigDecimal(1.5);
	private static final String DNI_INTERESADO = "12345678M";
	private static final DocumentoContable EJEMPLO = new DocumentoContable(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, IMPORTE, DNI_INTERESADO, FECHA_CREACION);
	
	@Test
	public void testGetters() {
		assertEquals(IMPORTE, EJEMPLO.getImporte());
		assertEquals(DNI_INTERESADO, EJEMPLO.getDNIInteresado());
	}
	
	@Test 
	public void testEquals() {
		DocumentoContable ejemplo1 = new DocumentoContable(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, IMPORTE, DNI_INTERESADO, FECHA_CREACION); 
		assertEquals(EJEMPLO, ejemplo1);
	}
}
