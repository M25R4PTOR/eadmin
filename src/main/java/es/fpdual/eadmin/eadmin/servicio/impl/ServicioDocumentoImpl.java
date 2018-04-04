package es.fpdual.eadmin.eadmin.servicio.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

@Service
public class ServicioDocumentoImpl implements ServicioDocumento {

	RepositorioDocumento repositorioDocumento;

	@Autowired
	public ServicioDocumentoImpl(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}

	@Override
	public Documento altaDocumento(Documento documento) {
		final Documento documentoAlta = obtenerDocumentoConFechaCorrecta(documento);

		repositorioDocumento.altaDocumento(documentoAlta);

		return documentoAlta;
	}

	@Override
	public Documento modificarDocumento(Documento documento) {
		final Documento documentoModificado = obtenerDocumentoConFechaCorrecta(documento);

		repositorioDocumento.modificarDocumento(documentoModificado);

		return documentoModificado;
	}

	public Documento obtenerDocumentoConFechaCorrecta(Documento documento) {
		// return new Documento(documento.getCodigo(), documento.getNombre(),
		// dameFechaActual(), documento.getPublico(), documento.getEstado());
		// return new
		// DocumentoBuilder().conCodigo(documento.getCodigo()).conNombre(documento.getNombre()).conFechaCreacion(dameFechaActual()).conPublico(documento.getPublico()).conEstado(documento.getEstado()).construir();
		return new DocumentoBuilder().clonar(documento).conFechaCreacion(dameFechaActual()).construir();
	}

	protected Date dameFechaActual() {
		return new Date();
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		repositorioDocumento.eliminarDocumento(codigo);
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		return repositorioDocumento.obtenerDocumentoPorCodigo(codigo);
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		return repositorioDocumento.obtenerTodosLosDocumentos();
	}

}
