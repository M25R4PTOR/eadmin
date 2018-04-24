package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private DocumentoMapper mapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);

	//private List<Documento> documentos = new ArrayList<>();

//	public List<Documento> getDocumentos() {
//		return documentos;
//	}
	
	@Autowired
	public RepositorioDocumentoImpl(DocumentoMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void altaDocumento(Documento documento) {
		Integer codigoMaximo = this.mapper.codigoMaximo();
		DocumentoBuilder resultado = new DocumentoBuilder().clonar(documento).conCodigo(codigoMaximo);
		this.mapper.insertarDocumento(resultado.construir());
//		LOGGER.info("Entrando en el método \"altaDocumento\"");
//		if (documentos.contains(documento)) {
//			throw new IllegalArgumentException("El documento ya existe");
//		}
//
//		documentos.add(documento);
//		LOGGER.info(documento.toString() + " creado correctamente.");
//		LOGGER.info("Saliendo del método \"altaDocumento\"");
	}

	@Override
	public void altaDocumento2(Documento documento) {
		altaDocumento(documento);
		guardarDocumentoEnFichero(documento, "Alta.txt");
		exportarExcel("Alta", documento, "ExcelDocumentos.xlsx");
	}

	@Override
	public void modificarDocumento(Documento documento) {
		int modificado = this.mapper.actualizarDocumento(documento);
		if(modificado == 0) {
			throw new IllegalArgumentException("No se encuentra el documento");
		}
//		LOGGER.info("Entrando en el método \"modificarDocumento\"");
//		if (!documentos.contains(documento)) {
//			throw new IllegalArgumentException("El documento no existe");
//		}
//		documentos.set(documentos.indexOf(documento), documento);
//		LOGGER.info("Saliendo del método \"modificarDocumento\"");
	}

	@Override
	public void modificarDocumento2(Documento documento) {
		modificarDocumento(documento);
		guardarDocumentoEnFichero(documento, "Modificar.txt");
		exportarExcel("Modificar", documento, "ExcelDocumentos.xlsx");
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
//		// Documento documentoEncontrado = null;
//		// for(int i = 0; i < documentos.size(); i++) {
//		// if(documentos.get(i).getCodigo().equals(codigo)) {
//		// documentoEncontrado = documentos.get(i);
//		// break;
//		// }
//		// }
//		LOGGER.info("Entrando en el método \"eliminarDocumento\"");
//		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
//				.findFirst();
//		// if (Objects.nonNull(documentoEncontrado)) { // lo mismo --> (documentoEncontrado != null)
//		// documentos.remove(documentoEncontrado);
//		// }
//		if (documentoEncontrado.isPresent()) {
//			documentos.remove(documentoEncontrado.get());
//			LOGGER.info("Documento eliminado.");
//		} else {
//			LOGGER.info("Saliendo del método \"eliminarDocumento\"");
//		}
	}

	@Override
	public void eliminarDocumento2(Integer codigo) {
		this.mapper.eliminarDocumento(codigo);
//		LOGGER.info("Entrando en el método \"eliminarDocumento\"");
//		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
//				.findFirst();
//		if (documentoEncontrado.isPresent()) {
//			documentos.remove(documentoEncontrado.get());
//			LOGGER.info("Documento eliminado.");
//			guardarDocumentoEnFichero(documentoEncontrado.get(), "Eliminar.txt");
//			exportarExcel("Eliminar", documentoEncontrado.get(), "ExcelDocumentos.xlsx");
//		} else {
//			LOGGER.info("Saliendo del método \"eliminarDocumento\" sin eliminar");
//		}
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		return this.mapper.consultarDocumento(codigo);
//		LOGGER.info("Entrando en el método \"obtenerDocumentoPorCodigo\"");
//		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
//				.findFirst();
//		if (documentoEncontrado.isPresent()) {
//			LOGGER.info("Saliendo del método \"obtenerDocumentoPorCodigo\" devolviendo un documento");
//			return documentoEncontrado.get();
//		}
//		LOGGER.info("Saliendo del método \"obtenerDocumentoPorCodigo\" sin devolver nada");
//		return null;
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		return this.mapper.consultarTodosLosDocumentos();
//		LOGGER.info("Entrando en el método \"obtenerTodosLosDocumentos\"");
//		for (Documento d : getDocumentos()) {
//			LOGGER.info("Código: " + d.getCodigo() + ", Nombre: " + d.getNombre() + ", Fecha Creación: "
//					+ d.getFechaCreacion() + ", Público: " + d.getPublico() + " y Estado Documento: " + d.getEstado());
//		}
//		LOGGER.info("Saliendo del método \"obtenerTodosLosDocumentos\"");
//		return this.getDocumentos();
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
				exportarExcel("Documentos", d, "ExcelDocumentos.xlsx");
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
			file = new FileWriter(nombreFichero, true); // Continuando la escritura --> file = new
														// FileWriter(nombreFichero, true);
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

	public static void exportarExcel(String nombreHoja, Documento documento, String fileName) {
		LOGGER.info("Entrando a Exporta Excel");
		try {

			FileInputStream inputStream = new FileInputStream(new File(fileName));

			Workbook workbook = WorkbookFactory.create(inputStream);

			int numeroHoja;

			if (nombreHoja.equals("Documentos")) {

				numeroHoja = 0;

			} else if (nombreHoja.equals("Alta")) {

				numeroHoja = 1;

			} else if (nombreHoja.equals("Modificar")) {

				numeroHoja = 2;

			} else {

				numeroHoja = 3;

			}

			Sheet sheet = workbook.getSheetAt(numeroHoja);

			Object[] bookData = { documento.getCodigo(), documento.getNombre(), documento.getFechaCreacion().toString(), documento.getEstado().toString() };

			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(++rowCount);

			int columnCount = 0;

			Cell cell = row.createCell(columnCount);
			//cell.setCellValue(rowCount);
			
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			CellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(IndexedColors.RED.getIndex());
			style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			
			int color = 0;
			for (Object field : bookData) {
				
				cell = row.createCell(columnCount++);

				if (field instanceof String) {

					cell.setCellValue((String) field);

				} else if (field instanceof Integer) {

					cell.setCellValue((Integer) field);

				}
				if(color%2==0) {
					cell.setCellStyle(style);
				}else {
					cell.setCellStyle(style2);
				}
				color++;
			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(fileName);

			workbook.write(outputStream);

			workbook.close();

			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {

			ex.printStackTrace();

		}
		LOGGER.info("Saliendo de Exporta Excel");
	}

}