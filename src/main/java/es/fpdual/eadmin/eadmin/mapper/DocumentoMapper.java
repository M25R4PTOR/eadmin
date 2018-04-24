package es.fpdual.eadmin.eadmin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface DocumentoMapper {

	int insertarDocumento(@Param("documento")Documento documento);
	
	int eliminarDocumento(@Param("codigo")int codigo);
	
	int actualizarDocumento(@Param("documento")Documento documento);
	
	Documento consultarDocumento(@Param("codigo")int codigo);
	
	List<Documento> consultarTodosLosDocumentos();
	
	int codigoMaximo();
}
