package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class BaseAdministracionTest extends AbstractoModeloBeanTest<BaseAdministracion> {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final BaseAdministracionFake EJEMPLO = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION,
			PUBLICO, FECHA_CREACION);

	
	@Override
	public void before() {
		this.entityA1 = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, FECHA_CREACION);
		this.entityA2 = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, FECHA_CREACION);
		this.entityB = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, false, FECHA_CREACION);

	}
	
	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(CODIGO, this.entityA1.getCodigo());
		assertEquals(NOMBRE, this.entityA1.getNombre());
		assertEquals(FECHA_CREACION, this.entityA1.getFechaCreacion());
		assertEquals(FECHA_CREACION, this.entityA1.getFechaUltimaActualizacion());
		assertEquals(PUBLICO, this.entityA1.getPublico());
	}
	
	@Test
	public void testEquals() {
		BaseAdministracionFake ejemplo1 = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO, FECHA_CREACION);
		assertEquals(EJEMPLO, ejemplo1);
	}
}




class BaseAdministracionFake extends BaseAdministracion {
	public BaseAdministracionFake(Integer codigo, String nombre, Date fechaCreacion, Boolean publico,
			Date fechaUltimaActualizacion) {
		super(codigo, nombre, fechaCreacion, publico, fechaUltimaActualizacion);
	}
}