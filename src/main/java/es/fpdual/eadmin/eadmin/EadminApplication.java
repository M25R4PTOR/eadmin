package es.fpdual.eadmin.eadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EadminApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EadminApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Esto es una prueba");
		
		// Debug.
		LOGGER.debug("Depuracion");

		LOGGER.trace("Traza");
		
		// Warning
		LOGGER.warn("Advertencia");
		
		// Error
		LOGGER.error("Error");
		
		LOGGER.info("Inicio run");
		SpringApplication.run(EadminApplication.class, args);
		LOGGER.info("Fin run");
	}
}