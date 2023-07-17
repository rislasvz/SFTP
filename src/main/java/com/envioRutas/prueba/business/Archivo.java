package com.envioRutas.prueba.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.envioRutas.prueba.entities.Conexion;
import com.envioRutas.prueba.util.Constantes;

public class Archivo {

	public String getArchive() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
		String currentDateFormat = currentDate.format(formatter);
		String archive = Constantes.NAME_ARCHIVE + currentDateFormat + Constantes.EXTENCION;
		return archive;
	}

	public String getArchiveToSend(Conexion conexion) throws IOException {
		conexion.setArchive(getArchive());
		boolean fileExist = false;
		File directory = new File(conexion.getConexion().get("PATH_LOCAL_FILE"));
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files) {
				if (file.isFile() && file.getName().equals(conexion.getArchive())) {
					fileExist = true;
					break;
				}
			} 
			if (fileExist) {
				return conexion.getArchive();
			} else {
				throw new IOException(
						"El archivo '" + conexion.getArchive() + "' no existe en el directorio: " + directory);
			}
		} else {
			throw new IOException("El directorio no existe: " + directory);
		}
	}

	public void respaldo(Conexion conexion) throws IOException {
		File file = new File(conexion.getConexion().get("PATH_LOCAL_FILE") + "/" + getArchiveToSend(conexion));
		File pathRespaldo = new File(conexion.getConexion().get("PATH_RESPALDO"), file.getName());
		if (file.exists()) {
			FileUtils.copyFile(file, pathRespaldo);
			FileUtils.forceDelete(file);
			System.out.println("Copiado con exito");
		} else {
			throw new IOException("El archivo de origen no existe en la ruta especificada: " + file);
		}
	}

	public void validarRespaldo(Conexion conexion) {
		File respaldo = new File(conexion.getConexion().get("PATH_RESPALDO"));
		Date currentDate = new Date();
		long oneYearInMillis = 365 * 24 * 60 * 60 * 1000L;
		long oneYearAgoInMillis = currentDate.getTime() - oneYearInMillis;
		File[] files = respaldo.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.lastModified() < oneYearAgoInMillis) {
					try {
						FileUtils.forceDelete(file);
					} catch (IOException e) {
						System.err.println("No se pudo eliminar el archivo: " + file.getName());
						e.printStackTrace();
					}
				}
			}
		}
	}
}
