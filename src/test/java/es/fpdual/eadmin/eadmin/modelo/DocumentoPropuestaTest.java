package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DocumentoPropuestaTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final Integer CODIGO_PROPUESTA = 1;
	private static final Integer EJERCICIO = 1;
	private static final String GRUPO_POLITICO = "Pa la saca";
	private static final DocumentoPropuesta EJEMPLO = new DocumentoPropuesta(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, CODIGO_PROPUESTA, EJERCICIO, GRUPO_POLITICO);

	@Test
	public void testGetters() {
		assertEquals(CODIGO_PROPUESTA, EJEMPLO.getCodigoPropuesta());
		assertEquals(EJERCICIO, EJEMPLO.getEjercicio());
		assertEquals(GRUPO_POLITICO, EJEMPLO.getGrupoPolitico());
	}
	
	@Test 
	public void testEquals() {
		DocumentoPropuesta ejemplo1 = new DocumentoPropuesta(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, CODIGO_PROPUESTA, EJERCICIO, GRUPO_POLITICO);
		assertEquals(EJEMPLO, ejemplo1);
	}
}
