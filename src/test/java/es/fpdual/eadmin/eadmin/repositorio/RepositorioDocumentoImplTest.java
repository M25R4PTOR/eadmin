package es.fpdual.eadmin.eadmin.repositorio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;

public class RepositorioDocumentoImplTest {
	
	private static final Date FECHA_CREACION = new Date();
	private RepositorioDocumentoImpl repositorioDocumento;
	private static final Documento DOCUMENTO = new Documento(1, "Ejemplo", FECHA_CREACION, true, EstadoDocumento.ACTIVO);
	
	@Before
	public void inicializarEnCadaTest() {
		this.repositorioDocumento = new RepositorioDocumentoImpl();
	}
	
	@Test
	public void deberiaEliminarUnDocumento() {
		this.repositorioDocumento.getDocumentos().add(DOCUMENTO);
		this.repositorioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());
		assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
	}
	
	@Test
	public void eliminarUnDocumentoInexistente() {
		this.repositorioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());
		assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
	}
	
}