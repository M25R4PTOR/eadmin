package es.fpdual.eadmin.eadmin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Component
public class CargarDatosIniciales implements ApplicationRunner{

	private final RepositorioDocumento repositorioDocumento;
	
	private static final Date FECHA = new Date();
	
	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.repositorioDocumento.altaDocumento(new Documento(1, "Ejemplo1", FECHA, true, EstadoDocumento.ACTIVO));
		this.repositorioDocumento.altaDocumento(new Documento(2, "Ejemplo2", FECHA, false, EstadoDocumento.APROBADO));
		this.repositorioDocumento.altaDocumento(new Documento(3, "Ejemplo3", FECHA, true, EstadoDocumento.ELIMINADO));
		this.repositorioDocumento.altaDocumento(new Documento(4, "Ejemplo4", FECHA, false, EstadoDocumento.ELIMINADO));
	}

}
