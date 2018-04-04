package es.fpdual.eadmin.eadmin.servicio.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioExpedienteImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;

@Service
public class ServicioExpedienteImpl implements ServicioExpediente {

	RepositorioExpedienteImpl repositorioExpediente;
	
	@Autowired
	public ServicioExpedienteImpl(RepositorioExpedienteImpl repositorioExpediente) {
		this.repositorioExpediente = repositorioExpediente;
	}
	
	@Override
	public Expediente altaExpediente(Expediente expediente) {
		final Expediente expedienteAlta = obtenerExpedienteConFechaCorrecta(expediente);
		
		repositorioExpediente.altaExpediente(expedienteAlta);
		
		return expedienteAlta;
	}

	private Expediente obtenerExpedienteConFechaCorrecta(Expediente expediente) {
		return new Expediente(expediente.getCodigo(), expediente.getNombre(), dameFechaActual(), expediente.getFechaArchivado(), expediente.getPublico(), expediente.getEstado(), expediente.getDocumentos());
	}
	
	protected Date dameFechaActual() {
		return new Date();
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {
		final Expediente expedienteModificado = obtenerExpedienteConFechaCorrecta(expediente);
		
		repositorioExpediente.modificarExpediente(expedienteModificado);
		
		return expedienteModificado;
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {
		repositorioExpediente.eliminarExpediente(codigoExpediente);
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		Optional<Expediente> expedienteEncontrado = repositorioExpediente.getExpedientes().stream().filter(d -> d.getCodigo().equals(codigoExpediente)).findFirst();
		if (expedienteEncontrado.isPresent()) {
			expedienteEncontrado.get().getDocumentos().add(documento);
		}
		return expedienteEncontrado.get();
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		Optional<Expediente> expedienteEncontrado = repositorioExpediente.getExpedientes().stream().filter(d -> d.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			expedienteEncontrado.get().getDocumentos().remove(codigoDocumento);
		}
		return expedienteEncontrado.get();
	}
}