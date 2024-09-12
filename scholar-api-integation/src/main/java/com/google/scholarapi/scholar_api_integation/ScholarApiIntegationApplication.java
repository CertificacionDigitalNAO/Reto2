package com.google.scholarapi.scholar_api_integation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para la aplicación ScholarApiIntegration.
 * Esta clase inicializa y ejecuta la aplicación Spring Boot.
 */
@SpringBootApplication
public class ScholarApiIntegationApplication {

	/**
	 * Método principal que sirve como punto de entrada para la aplicación.
	 * 
	 * @param args los argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(ScholarApiIntegationApplication.class, args);
	}

}