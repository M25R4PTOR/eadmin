package es.fpdual.eadmin.eadmin.repositorio;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;

public class RepositorioDocumentoImplTest {
	
	private static final Date FECHA_CREACION = new Date();
	private RepositorioDocumentoImpl repositorioDocumento;
	private static final Documento DOCUMENTO = new Documento(1, "Ejemplo", FECHA_CREACION, true, EstadoDocumento.ACTIVO, FECHA_CREACION);
	private DocumentoMapper mapper;
	
	@Before
	public void inicializarEnCadaTest() {
		this.mapper = mock(DocumentoMapper.class);
		this.repositorioDocumento = new RepositorioDocumentoImpl(this.mapper);
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		this.repositorioDocumento.altaDocumento(this.DOCUMENTO);
		
		verify(this.mapper).insertarDocumento(this.DOCUMENTO);
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		when(this.mapper.actualizarDocumento(this.DOCUMENTO)).thenReturn(1);
		
		this.repositorioDocumento.modificarDocumento(this.DOCUMENTO);
		
		verify(this.mapper).actualizarDocumento(this.DOCUMENTO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deberiaLanzarExcepcionSiActualizamosDocumentoQueNoExiste() {
		when(this.mapper.actualizarDocumento(this.DOCUMENTO)).thenReturn(0);
		
		this.repositorioDocumento.modificarDocumento(this.DOCUMENTO);
	}
	
	@Test
	public void deberiaEliminarUnDocumento() {
		this.repositorioDocumento.eliminarDocumento2(this.DOCUMENTO.getCodigo());
		
		verify(this.mapper).eliminarDocumento(this.DOCUMENTO.getCodigo());
	}
	
	@Test
	public void testObtenerDocumentoPorCodigo() {
		when(this.mapper.consultarDocumento(this.DOCUMENTO.getCodigo())).thenReturn(this.DOCUMENTO);
		
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(this.DOCUMENTO.getCodigo());
		
		assertThat(resultado, is(this.DOCUMENTO));
	}
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() throws Exception{
		final List<Documento> todosLosDocumentos = Arrays.asList(this.DOCUMENTO);
		
		when(mapper.consultarTodosLosDocumentos()).thenReturn(todosLosDocumentos);
		
		final List<Documento> resultado = this.repositorioDocumento.obtenerTodosLosDocumentos();
		
		assertThat(resultado, hasSize(1));
		assertThat(resultado, hasItem(this.DOCUMENTO));
	}
}