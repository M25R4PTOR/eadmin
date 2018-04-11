package es.fpdual.eadmin.eadmin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;

@Component
public class CargarDatosIniciales implements ApplicationRunner{

	private final RepositorioDocumento repositorioDocumento;
	private final RepositorioExpediente repositorioExpediente;
	
	private static final Date FECHA = new Date();
	
	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento, RepositorioExpediente repositorioExpediente) {
		this.repositorioDocumento = repositorioDocumento;
		this.repositorioExpediente = repositorioExpediente;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.repositorioDocumento.altaDocumento2(new Documento(1, "Ejemplo1", FECHA, true, EstadoDocumento.ACTIVO, FECHA));
		this.repositorioDocumento.altaDocumento2(new Documento(2, "Ejemplo2", FECHA, false, EstadoDocumento.APROBADO, FECHA));
		this.repositorioDocumento.altaDocumento2(new Documento(3, "Ejemplo3", FECHA, true, EstadoDocumento.ELIMINADO, FECHA));
		this.repositorioDocumento.altaDocumento2(new Documento(4, "Ejemplo4", FECHA, false, EstadoDocumento.ELIMINADO, FECHA));
		this.repositorioDocumento.altaDocumento2(new Documento(5, "Ejemplo5", FECHA, false, EstadoDocumento.APROBADO, FECHA));
		RepositorioDocumentoImpl.guardarDocumentosEnFichero(this.repositorioDocumento.obtenerTodosLosDocumentos(), "salidaDeTexto.txt");
		this.repositorioDocumento.modificarDocumento2(new Documento(2, "Ejemplo5", FECHA, false, EstadoDocumento.APROBADO, FECHA));
		this.repositorioDocumento.modificarDocumento2(new Documento(4, "Ejemplo3", FECHA, true, EstadoDocumento.ELIMINADO, FECHA));
		RepositorioDocumentoImpl.guardarDocumentosEnFichero(this.repositorioDocumento.obtenerTodosLosDocumentos(), "salidaDeTexto.txt");
		this.repositorioDocumento.eliminarDocumento2(1);
		this.repositorioDocumento.eliminarDocumento2(3);
		this.repositorioDocumento.eliminarDocumento2(5);
		RepositorioDocumentoImpl.guardarDocumentosEnFichero(this.repositorioDocumento.obtenerTodosLosDocumentos(), "salidaDeTexto.txt");
		
		
		this.repositorioExpediente.altaExpediente2(new Expediente(1, "Expediente1", FECHA, FECHA, true, EstadoExpediente.ARCHIVADO, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		this.repositorioExpediente.altaExpediente2(new Expediente(2, "Expediente2", FECHA, FECHA, false, EstadoExpediente.EN_TRAMITE, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		this.repositorioExpediente.altaExpediente2(new Expediente(3, "Expediente3", FECHA, FECHA, true, EstadoExpediente.INICIADO, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		this.repositorioExpediente.altaExpediente2(new Expediente(4, "Expediente4", FECHA, FECHA, false, EstadoExpediente.EN_TRAMITE, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		this.repositorioExpediente.altaExpediente2(new Expediente(5, "Expediente5", FECHA, FECHA, true, EstadoExpediente.ARCHIVADO, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		RepositorioExpedienteImpl.guardarExpedientesEnFichero(this.repositorioExpediente.obtenerTodosLosExpedientes(), "salidaDeTextoExpediente.txt");
		this.repositorioExpediente.modificarExpediente2(new Expediente(2, "Expediente1", FECHA, FECHA, true, EstadoExpediente.ARCHIVADO, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		this.repositorioExpediente.modificarExpediente2(new Expediente(4, "Expediente3", FECHA, FECHA, true, EstadoExpediente.INICIADO, repositorioDocumento.obtenerTodosLosDocumentos(), FECHA));
		RepositorioExpedienteImpl.guardarExpedientesEnFichero(this.repositorioExpediente.obtenerTodosLosExpedientes(), "salidaDeTextoExpediente.txt");
		this.repositorioExpediente.eliminarExpediente2(1);
		this.repositorioExpediente.eliminarExpediente2(3);
		this.repositorioExpediente.eliminarExpediente2(5);
		RepositorioExpedienteImpl.guardarExpedientesEnFichero(this.repositorioExpediente.obtenerTodosLosExpedientes(), "salidaDeTextoExpediente.txt");
	}
 
}
