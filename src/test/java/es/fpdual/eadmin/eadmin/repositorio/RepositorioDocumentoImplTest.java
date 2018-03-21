package es.fpdual.eadmin.eadmin.repositorio;

import java.util.Date;

import org.junit.Before;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;

public class RepositorioDocumentoImplTest {
	
	private static final Date FECHA_CREACION = new Date();
	
	@Before
	public void instanciar() {
		RepositorioDocumentoImpl repositorio = new RepositorioDocumentoImpl();
		Documento ejemplo = new Documento(1, "Ejemplo", FECHA_CREACION, true, EstadoDocumento.ACTIVO);
		repositorio.altaDocumento(ejemplo);
	}
	
	
}
