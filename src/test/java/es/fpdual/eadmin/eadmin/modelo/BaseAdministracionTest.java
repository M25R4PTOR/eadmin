package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class BaseAdministracionTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE = "Ejemplo";
	private static final boolean PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final BaseAdministracionFake EJEMPLO = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO); 
	
	@Test
	public void testGetters() {
		assertEquals(CODIGO, EJEMPLO.getCodigo());
		assertEquals(NOMBRE, EJEMPLO.getNombre());
		assertEquals(FECHA_CREACION, EJEMPLO.getFechaCreacion());
		assertEquals(PUBLICO, EJEMPLO.getPublico());
	}
	
	@Test 
	public void testEquals() {
		BaseAdministracionFake ejemplo1 = new BaseAdministracionFake(CODIGO, NOMBRE, FECHA_CREACION, PUBLICO);
		assertEquals(EJEMPLO, ejemplo1);
	}
}
class BaseAdministracionFake extends BaseAdministracion{	
	public BaseAdministracionFake(Integer codigo, String nombre, Date fechaCreacion, Boolean publico) {
		super(codigo, nombre, fechaCreacion, publico);
	}
}