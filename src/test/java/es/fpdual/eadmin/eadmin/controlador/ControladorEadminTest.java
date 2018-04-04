package es.fpdual.eadmin.eadmin.controlador;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

public class ControladorEadminTest {

	private ControladorEadmin controlador;
	
	private final ServicioDocumento servicio = mock(ServicioDocumento.class);
	
	@Before
	public void inicializarEnCadaTest() {
		this.controlador = new ControladorEadmin(servicio);
	}
	
	@Test
	public void deberiaDevolverTodosLosDocumentos() {
		
		final List<Documento> documentos = new ArrayList<>();
		when(this.servicio.obtenerTodosLosDocumentos()).
		thenReturn(documentos );
		
		List<Documento> resultado = this.controlador.
				getTodosDocumentos().getBody();
		
		assertSame(resultado, documentos);
	}
}