package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public abstract class BaseDocumentoMapperTest {

	private static final Date FECHA = new Date();
	private Documento documento;

	@Autowired
	private DocumentoMapper mapper;

	@Before
	public void inicializarEnCadaTest() {
		documento = new Documento(1, "Ejemplo1", FECHA, true, EstadoDocumento.ACTIVO, FECHA);
	}

	@Test
	public void deberiaInsertarUnDocumento() throws Exception {
		final int resultado = this.mapper.insertarDocumento(this.documento);

		assertThat(resultado, is(1));
	}

}
