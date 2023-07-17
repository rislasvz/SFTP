package com.envioRutas.prueba.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.envioRutas.prueba.entities.Conexion;

public class PropertiesBancware {

	private static Logger log = LogManager.getLogger(PropertiesBancware.class);
	private static Properties properties;

	private PropertiesBancware() {
		throw new IllegalArgumentException("Clase Util");
	}

	public static void properties(Conexion conexion) throws IOException {
		log.info("Inicio Carga Properties Bancware");
		properties = new Properties();
		Map<String, String> getProperties = new HashMap<String, String>();
		try (InputStream is = new FileInputStream(Constantes.RUTA_PROPERTIES_BANCWARE)) {
			properties.load(is);
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				getProperties.put(key, value);
			}
		} catch (IOException e) {
			log.error("Context", e);
			throw new IOException(e);
		}
		conexion.setConexion(getProperties);
		log.info("Fin carga properties");
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}

}
