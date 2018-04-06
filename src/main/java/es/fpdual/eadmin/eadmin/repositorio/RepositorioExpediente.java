package es.fpdual.eadmin.eadmin.repositorio;

import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {

	public abstract void altaExpediente(Expediente expediente);
	
	public abstract void modificarExpediente(Expediente expediente);
	
	public abstract void eliminarExpediente(Integer codigo);
	
	Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);

	Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento);
	
	List<Expediente> obtenerTodosLosExpedientes();
	
	public abstract void altaExpediente2(Expediente expediente);
	
	public abstract void modificarExpediente2(Expediente expediente);
	
	public abstract void eliminarExpediente2(Integer codigo);
}
