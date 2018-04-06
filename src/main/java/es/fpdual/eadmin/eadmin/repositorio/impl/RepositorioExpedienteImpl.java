package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	public void altaExpediente2(Expediente expediente) {
		altaExpediente(expediente);
		guardarExpedienteEnFichero(expediente, "AltaExpediente.txt");
	}

	@Override
	public void modificarExpediente(Expediente expediente) {
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento no existe");
		}
		expedientes.set(expedientes.indexOf(expediente), expediente);
	}

	@Override
	public void modificarExpediente2(Expediente expediente) {
		modificarExpediente(expediente);
		guardarExpedienteEnFichero(expediente, "ModificarExpediente.txt");
	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		Optional<Expediente> expedienteEncontrado = expedientes.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		if (expedienteEncontrado.isPresent()) {
			expedientes.remove(expedienteEncontrado.get());
		}
	}

	@Override
	public void eliminarExpediente2(Integer codigo) {
		Optional<Expediente> expedienteEncontrado = expedientes.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		if (expedienteEncontrado.isPresent()) {
			expedientes.remove(expedienteEncontrado.get());
			guardarExpedienteEnFichero(expedienteEncontrado.get(), "EliminarExpediente.txt");
		}
	}

	protected boolean tieneIgualCodigo(Expediente expediente, Integer codigo) {
		return expediente.getCodigo().equals(codigo);
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		Optional<Expediente> expedienteEncontrado = expedientes.stream()
				.filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getDocumentos().stream()
					.filter(d -> d.getCodigo().equals(documento.getCodigo())).findFirst();
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

		Optional<Expediente> expedienteEncontrado = expedientes.stream()
				.filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();

		if (expedienteEncontrado.isPresent()) {
			Optional<Documento> documentoEncontrado = expedienteEncontrado.get().getDocumentos().stream()
					.filter(d -> d.getCodigo().equals(codigoDocumento)).findFirst();
			if (documentoEncontrado.isPresent()) {
				expedienteEncontrado.get().getDocumentos().remove(documentoEncontrado.get());
				modificarExpediente(expedienteEncontrado.get());
			}
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public List<Expediente> obtenerTodosLosExpedientes() {
		return getExpedientes();
	}

	public static void guardarExpedientesEnFichero(List<Expediente> expedientes, String nombreFichero) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(nombreFichero, true); // Continuando la escritura --> file = new
														// FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			for (Expediente exp : expedientes) {
				pw.println("Código: " + exp.getCodigo() + ", Nombre: " + exp.getNombre() + ", Fecha Creación: "
						+ exp.getFechaCreacion() + ", Fecha Archivado: " + exp.getFechaArchivado() + ", Público: "
						+ exp.getPublico() + " y Estado Expediente: " + exp.getEstado() + ", Lista de Documentos: "
						+ exp.getDocumentos());
				pw.println("*************************************************************************");
			}
			pw.close();

		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			pw.close();
		}
	}

	public static void guardarExpedienteEnFichero(Expediente exp, String nombreFichero) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(nombreFichero, true); // Continuando la escritura --> file = new
														// FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);
			pw.println("Código: " + exp.getCodigo() + ", Nombre: " + exp.getNombre() + ", Fecha Creación: "
					+ exp.getFechaCreacion() + ", Fecha Archivado: " + exp.getFechaArchivado() + ", Público: "
					+ exp.getPublico() + " y Estado Expediente: " + exp.getEstado() + ", Lista de Documentos: "
					+ exp.getDocumentos());
			pw.println("*************************************************************************");
			pw.close();

		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			pw.close();
		}
	}
}
