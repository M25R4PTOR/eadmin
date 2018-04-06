package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
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
	public void altaDocumento2(Documento documento) {
		altaDocumento(documento);
		guardarDocumentoEnFichero(documento, "Alta.txt");
		exportarExcel(documento, "Alta.xls");
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
	public void modificarDocumento2(Documento documento) {
		modificarDocumento(documento);
		guardarDocumentoEnFichero(documento, "Modificar.txt");
		exportarExcel(documento, "Modificar.xls");
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		// Documento documentoEncontrado = null;
		// for(int i = 0; i < documentos.size(); i++) {
		// if(documentos.get(i).getCodigo().equals(codigo)) {
		// documentoEncontrado = documentos.get(i);
		// break;
		// }
		// }
		LOGGER.info("Entrando en el método \"eliminarDocumento\"");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		// if (Objects.nonNull(documentoEncontrado)) { // lo mismo -->
		// (documentoEncontrado != null)
		// documentos.remove(documentoEncontrado);
		// }
		if (documentoEncontrado.isPresent()) {
			documentos.remove(documentoEncontrado.get());
			LOGGER.info("Documento eliminado.");
		} else {
			LOGGER.info("Saliendo del método \"eliminarDocumento\"");
		}
	}

//	public void eliminarDocumento2(Integer codigo) {
//		eliminarDocumento(codigo);
//		Optional<Documento> aux = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo)).findFirst();
//		guardarDocumentoEnFichero(aux.get(), "Eliminar.txt");
//	}
	
	@Override
	public void eliminarDocumento2(Integer codigo) {
		LOGGER.info("Entrando en el método \"eliminarDocumento\"");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo)).findFirst();
		if (documentoEncontrado.isPresent()) {
			documentos.remove(documentoEncontrado.get());
			LOGGER.info("Documento eliminado.");
			guardarDocumentoEnFichero(documentoEncontrado.get(), "Eliminar.txt");
			exportarExcel(documentoEncontrado.get(), "Eliminar.xls");
		} else {
			LOGGER.info("Saliendo del método \"eliminarDocumento\" sin eliminar");
		}
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		LOGGER.info("Entrando en el método \"obtenerDocumentoPorCodigo\"");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		if (documentoEncontrado.isPresent()) {
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
			LOGGER.info("Código: " + d.getCodigo() + ", Nombre: " + d.getNombre() + ", Fecha Creación: "
					+ d.getFechaCreacion() + ", Público: " + d.getPublico() + " y Estado Documento: " + d.getEstado());
		}
		LOGGER.info("Saliendo del método \"obtenerTodosLosDocumentos\"");
		return this.getDocumentos();
	}

	public static void guardarDocumentosEnFichero(List<Documento> documentos, String nombreFichero) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			for (Documento d : documentos) {
				pw.println("Código: " + d.getCodigo() + ", Nombre: " + d.getNombre() + ", Fecha Creación: "
						+ d.getFechaCreacion() + ", Público: " + d.getPublico() + " y Estado Documento: "
						+ d.getEstado());
				pw.println("*************************************************************************");
			}
			pw.close();

		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			pw.close();
		}

	}

	public static void guardarDocumentoEnFichero(Documento doc, String nombreFichero) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(nombreFichero, true); // Continuando la escritura --> file = new FileWriter(nombreFichero, true); 
			pw = new PrintWriter(file);
			pw.println("Código: " + doc.getCodigo() + ", Nombre: " + doc.getNombre() + ", Fecha Creación: "
					+ doc.getFechaCreacion() + ", Público: " + doc.getPublico() + " y Estado Documento: "
					+ doc.getEstado());
			pw.println("*************************************************************************");
			pw.close();

		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			pw.close();
		}
	}
	
	public static void exportarExcel(Object o, String nombreFichero){
		LOGGER.info("Entra en exportar Excel");
		Object[][] data;
		if(o instanceof Documento) {
			data = new Object[][] {new Object[] {((Documento) o).getCodigo(), ((Documento) o).getNombre(), ((Documento) o).getFechaCreacion(), ((Documento) o).getPublico(), ((Documento) o).getEstado()}};
		}else {
			data = new Object[][] {};
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "Hoja excel");

		String[] headers = new String[] { "Codigo", "Nombre", "Fecha Creación", "Publico", "Estado Documento" };

		CellStyle headerStyle = workbook.createCellStyle();
		
		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(header);
		}

		for (int i = 0; i < data.length; ++i) {
			HSSFRow dataRow = sheet.createRow(i + 1);

			Object[] d = data[i];
			Integer codigo = (Integer) d[0];
			String nombre = (String) d[1];
			Date fechaCreacion = (Date) d[2];
			Boolean publico = (Boolean) d[3];
			EstadoDocumento estado = (EstadoDocumento) d[4];

			dataRow.createCell(0).setCellValue(codigo);
			dataRow.createCell(1).setCellValue(nombre);
			dataRow.createCell(2).setCellValue(fechaCreacion.toString());
			dataRow.createCell(3).setCellValue(publico.toString());
			dataRow.createCell(4).setCellValue(estado.toString());
			
			
			try {
				FileOutputStream file = new FileOutputStream(nombreFichero);
				workbook.write(file);
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		LOGGER.info("Saliendo de exportar Excel");
	}
}