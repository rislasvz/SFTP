package com.envioRutas.prueba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.envioRutas.prueba.business.Archivo;
import com.envioRutas.prueba.business.EnviarArchivo;
import com.envioRutas.prueba.entities.Conexion;
import com.envioRutas.prueba.util.Constantes;
import com.envioRutas.prueba.util.PropertiesBancware;

public class EnvioRutas {

	private static Logger log = LogManager.getLogger(EnvioRutas.class);
	
	public static void main(String[] args) {
		
		EnvioRutas envioRutas = new EnvioRutas();
		
		try {
			//envioRutas.enviarRutas();
			envioRutas.enviarRutas();
			Constantes.exit = 0;
			log.info("Codigo de Salida: " + Constantes.exit);
		} catch (Exception e) {
			log.error("Context", e);
			Constantes.exit = 1;
		} finally {
			log.info("Codigo de Salida: " + Constantes.exit);
			System.exit(Constantes.exit);
		}
		
	}
	
	
	
	public void enviarRutas() throws Exception {
		log.info("INICIO Conexio");
		Conexion conexion = new Conexion();
		PropertiesBancware.properties(conexion);
		Archivo archivo = new Archivo();
		EnviarArchivo enviarArchivo = new EnviarArchivo();
		archivo.getArchiveToSend(conexion);
		enviarArchivo.enviar(conexion);
		archivo.validarRespaldo(conexion);
		archivo.respaldo(conexion);
		System.out.println(conexion);
	}
	
	
	
}