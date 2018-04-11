package es.fpdual.eadmin.eadmin.modelo;

public enum EstadoDocumento {
	ACTIVO(1), APROBADO(2), ELIMINADO(3);
	//public static final EstadoDocumento ACTIVO = new EstadoDocumento(1)
	
	private final int codigo;
	
	private EstadoDocumento(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
}
