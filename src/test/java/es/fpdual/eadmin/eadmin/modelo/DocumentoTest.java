package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DocumentoTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	
//	@Before
//	public void inicializar() {
//		Documento ejemplo = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO);
//	}
	
	@Test
	public void testGetters() {
		Documento ejemplo = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO); 
		assertEquals(CODIGO, ejemplo.getCodigo());
		assertEquals(NOMBRE, ejemplo.getNombre());
		assertEquals(FECHA_CREACION, ejemplo.getFechaCreacion());
		assertEquals(PUBLICO, ejemplo.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, ejemplo.getEstado());
	}
	
	@Test 
	public void testEquals() {
		Documento ejemplo = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO);
		Documento ejemplo1 = new Documento(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, EstadoDocumento.ACTIVO);
		assertEquals(ejemplo, ejemplo1);
	}
}
