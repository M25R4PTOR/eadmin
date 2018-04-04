package es.fpdual.eadmin.eadmin.servicio;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.impl.ServicioDocumentoImpl;

public class ServicioDocumentoImplTest {

	private ServicioDocumentoImpl servicioDocumento;
	private static final Documento DOCUMENTO = mock(Documento.class);
	private RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);

	@Before
	public void inicializarEnCadaTest() {
		this.servicioDocumento = spy(new ServicioDocumentoImpl(repositorioDocumento));
	}

	@Test
	public void deberiaAlmacenarUnDocumento() {

		final Documento documentoModificado = mock(Documento.class);

		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaCorrecta(DOCUMENTO);

		final Documento resultado = this.servicioDocumento.altaDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).altaDocumento(documentoModificado);
		assertSame(resultado, documentoModificado);
	}

	@Test
	public void deberiaModificarDocumento() {
		final Documento documentoModificado = mock(Documento.class);

		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaCorrecta(DOCUMENTO);

		final Documento resultado = this.servicioDocumento.modificarDocumento(DOCUMENTO);

		verify(this.repositorioDocumento).modificarDocumento(documentoModificado);
		assertSame(resultado, documentoModificado);
		// when(DOCUMENTO.getCodigo()).thenReturn(1);
		// when(DOCUMENTO.getFechaCreacion()).thenReturn(new Date(1/1/2018));
		// when(DOCUMENTO.getNombre()).thenReturn("nombre");
		//
		// final Documento resultado =
		// this.servicioDocumento.modificarDocumento(DOCUMENTO);
		//
		// verify(this.repositorioDocumento).modificarDocumento(any());
		// assertEquals(Integer.valueOf(1), resultado.getCodigo());
		// assertEquals("nombre", resultado.getNombre());
		// assertNotEquals(resultado.getFechaCreacion(), DOCUMENTO.getFechaCreacion());
	}

	@Test
	public void deberiaEliminarDocumento() {
		when(DOCUMENTO.getCodigo()).thenReturn(1);

		this.servicioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());

		verify(this.repositorioDocumento).eliminarDocumento(1);
	}

	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		when(this.repositorioDocumento.obtenerDocumentoPorCodigo(1)).thenReturn(DOCUMENTO);

		final Documento RESULTADO = this.servicioDocumento.obtenerDocumentoPorCodigo(1);

		assertSame(RESULTADO, DOCUMENTO);
	}

	@Test
	public void deberiaDevolverTodosLosDocumentos() {
		final List<Documento> documentos = new ArrayList<>();

		when(repositorioDocumento.obtenerTodosLosDocumentos()).thenReturn(documentos);

		final List<Documento> resultado = this.servicioDocumento.obtenerTodosLosDocumentos();

		assertSame(resultado, documentos);
	}
}