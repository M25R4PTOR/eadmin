package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class RepositorioExpedienteImpl implements RepositorioExpediente {

	private List<Expediente> expedientes = new ArrayList<>();
	
	public List<Expediente> getExpedientes() {
		return expedientes;
	}
	
	@Override
	public void altaExpediente(Expediente expediente) {
		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento ya existe");
		}

		expedientes.add(expediente);

	}

	@Override
	public void modificarExpediente(Expediente expediente) {
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		expedientes.set(expedientes.indexOf(expediente), expediente);

	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		Optional<Expediente> expedienteEncontrado = expedientes.stream().filter(d -> tieneIgualCodigo(d, codigo)).findFirst();
		if(expedienteEncontrado.isPresent()) {
			expedientes.remove(expedienteEncontrado.get());
		}
	}

	protected boolean tieneIgualCodigo(Expediente expediente, Integer codigo) {
		return expediente.getCodigo().equals(codigo);
	}
	
	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		Optional<Expediente> expedienteEncontrado = expedientes.stream().filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getDocumentos().stream().filter(d -> d.getCodigo().equals(documento.getCodigo())).findFirst();
			if (!documentoEncontrado.isPresent()) {
				expedienteEncontrado.get().getDocumentos().add(documento);
				modificarExpediente(expedienteEncontrado.get());
			}
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {

		Optional<Expediente> expedienteEncontrado = expedientes.stream().filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getDocumentos().stream().filter(d -> d.getCodigo().equals(codigoDocumento)).findFirst();
			if (documentoEncontrado.isPresent()) {
				expedienteEncontrado.get().getDocumentos().remove(documentoEncontrado.get());
				modificarExpediente(expedienteEncontrado.get());
			}
			return expedienteEncontrado.get();
		}
		return null;
	}
}
