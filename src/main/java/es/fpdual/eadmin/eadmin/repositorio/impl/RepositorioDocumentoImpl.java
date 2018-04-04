package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;


@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private static final Logger LOGGER = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);
	
	private List<Documento> documentos = new ArrayList<>();
	
	public List<Documento> getDocumentos() {
		return documentos;
	}

	@Override
	public void altaDocumento(Documento documento) {
		LOGGER.info("Entrando en el método \"altaDocumento\"");
		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}

		documentos.add(documento);
		LOGGER.info(documento.toString() + " creado correctamente.");
		LOGGER.info("Saliendo del método \"altaDocumento\"");
	}

	@Override
	public void modificarDocumento(Documento documento) {
		LOGGER.info("Entrando en el método \"modificarDocumento\"");
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		documentos.set(documentos.indexOf(documento), documento);
		LOGGER.info("Saliendo del método \"modificarDocumento\"");
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
//		Documento documentoEncontrado = null;
//		for(int i = 0; i < documentos.size(); i++) {
//			if(documentos.get(i).getCodigo().equals(codigo)) {
//				documentoEncontrado = documentos.get(i);
//				break;
//			}
//		}
		LOGGER.info("Entrando en el método \"eliminarDocumento\"");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo)).findFirst();
//		if (Objects.nonNull(documentoEncontrado)) {    // lo mismo -->  (documentoEncontrado != null) 
//			documentos.remove(documentoEncontrado);
//		}
		if(documentoEncontrado.isPresent()) {
			documentos.remove(documentoEncontrado.get());
			LOGGER.info("Documento eliminado.");
		}else {
			LOGGER.info("Saliendo del método \"eliminarDocumento\"");
		}
	}
	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		LOGGER.info("Entrando en el método \"obtenerDocumentoPorCodigo\"");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo)).findFirst();
		if(documentoEncontrado.isPresent()) {
			LOGGER.info("Saliendo del método \"obtenerDocumentoPorCodigo\" devolviendo un documento");
			return documentoEncontrado.get();
		}
		LOGGER.info("Saliendo del método \"obtenerDocumentoPorCodigo\" sin devolver nada");
		return null;
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		LOGGER.info("Entrando en el método \"obtenerTodosLosDocumentos\"");
		for (Documento d : getDocumentos()) {
			LOGGER.info("Código: " + d.getCodigo() + ", Nombre: " + d.getNombre() + ", Fecha Creación: " + d.getFechaCreacion() + ", Público: " + d.getPublico() + " y Estado Documento: " + d.getEstado());
		}
		LOGGER.info("Saliendo del método \"obtenerTodosLosDocumentos\"");
		return this.getDocumentos();
	}

}