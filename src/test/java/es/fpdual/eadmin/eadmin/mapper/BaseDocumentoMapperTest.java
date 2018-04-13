package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.util.Utilidades;

@Transactional("eadminTransactionManager")
public abstract class BaseDocumentoMapperTest {

	private Documento documento;

	@Autowired
	private DocumentoMapper mapper;

	@Before
	public void inicializarEnCadaTest() {
		documento = new Documento(1, "Ejemplo1", Utilidades.asDate(LocalDate.of(2015, 1, 1)), true, EstadoDocumento.ACTIVO, Utilidades.asDate(LocalDate.of(2015, 1, 2)));
	}
	
	@Test
	public void deberiaInsertarUnDocumento() throws Exception {
		final int resultado = this.mapper.insertarDocumento(this.documento);

		assertThat(resultado, is(1));
	}

	@Test
	public void deberiaEliminarUnDocumento() throws Exception {
		mapper.insertarDocumento(documento);
		final int resultado = this.mapper.eliminarDocumento(documento.getCodigo());
		assertThat(resultado, is(1));
	}
	
	@Test
	public void deberiaActualizarUnDocumento() throws Exception {
		final Documento documentoActualizado = new Documento(1, "Ejemplo Modificado", Utilidades.asDate(LocalDate.of(2015, 2, 1)), true, EstadoDocumento.ACTIVO, Utilidades.asDate(LocalDate.of(2015, 2, 2)));
		
		this.mapper.insertarDocumento(documento);
		
		final int resultado = this.mapper.actualizarDocumento(documentoActualizado);
		
		assertThat(resultado, is(1));
		
		final Documento documentoModificado = this.mapper.consultarDocumento(1);
		assertThat(documentoModificado, is(documentoActualizado));
	}
	
	@Test
	public void deberiaDevolverDocumentos() throws Exception {
		this.mapper.insertarDocumento(documento);
		
		final Documento resultado = this.mapper.consultarDocumento(1);
		
		assertThat(resultado, is(this.documento));
	}
}
