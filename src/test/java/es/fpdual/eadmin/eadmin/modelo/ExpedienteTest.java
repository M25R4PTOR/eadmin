package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class ExpedienteTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ARCHIVADO = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final List<Documento> DOCUMENTOS = new ArrayList<Documento>();
	
	@Test
	public void testGetters() {
		Expediente ejemplo = new Expediente(CODIGO, NOMBRE, FECHA_CREACION, FECHA_ARCHIVADO, PUBLICO, EstadoExpediente.ARCHIVADO, DOCUMENTOS, FECHA_CREACION); 
		assertEquals(CODIGO, ejemplo.getCodigo());
		assertEquals(NOMBRE, ejemplo.getNombre());
		assertEquals(FECHA_CREACION, ejemplo.getFechaCreacion());
		assertEquals(FECHA_ARCHIVADO, ejemplo.getFechaArchivado());
		assertEquals(PUBLICO, ejemplo.getPublico());
		assertEquals(EstadoExpediente.ARCHIVADO, ejemplo.getEstado());
		assertEquals(DOCUMENTOS, ejemplo.getDocumentos());
	}
	
	@Test 
	public void testEquals() {
		Expediente ejemplo = new Expediente(CODIGO, NOMBRE, FECHA_CREACION, FECHA_ARCHIVADO, PUBLICO, EstadoExpediente.ARCHIVADO, DOCUMENTOS, FECHA_CREACION); 
		Expediente ejemplo1 = new Expediente(CODIGO, NOMBRE, FECHA_CREACION, FECHA_ARCHIVADO, PUBLICO, EstadoExpediente.ARCHIVADO, DOCUMENTOS, FECHA_CREACION); 
		assertEquals(ejemplo, ejemplo1);
	}
}
