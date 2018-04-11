package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DocumentoTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final Documento EJEMPLO = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, FECHA_CREACION); 
	
//	@Before
//	public void inicializar() {
//		Documento ejemplo = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO);
//	}
	
	@Test
	public void testGetters() {
		assertEquals(CODIGO, EJEMPLO.getCodigo());
		assertEquals(NOMBRE, EJEMPLO.getNombre());
		assertEquals(FECHA_CREACION, EJEMPLO.getFechaCreacion());
		assertEquals(PUBLICO, EJEMPLO.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, EJEMPLO.getEstado());
	}
	
	@Test 
	public void testEquals() {
		Documento ejemplo1 = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO, FECHA_CREACION);
		assertEquals(EJEMPLO, ejemplo1);
	}
	
	@Test
	public void testToString() {
		assertEquals("Documento con código 1", EJEMPLO.toString());
	}
}
