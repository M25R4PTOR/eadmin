package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class DocumentoPropuesta extends Documento{

	private final Integer codigoPropuesta;
	private final Integer ejercicio;
	private final String grupoPolitico;
	
	public DocumentoPropuesta(Integer codigo, String nombre, Date fechaCreacion, Boolean publico,
			EstadoDocumento estado, Integer codigoPropuesta, Integer ejercicio, String partidoPolitico) {
		super(codigo, nombre, fechaCreacion, publico, estado);
		this.codigoPropuesta = codigoPropuesta;
		this.ejercicio = ejercicio;
		this.grupoPolitico = partidoPolitico;
	}

	public Integer getCodigoPropuesta() {
		return codigoPropuesta;
	}

	public Integer getEjercicio() {
		return ejercicio;
	}

	public String getGrupoPolitico() {
		return grupoPolitico;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Codigo Propuesta: " + codigoPropuesta;
	}
}
