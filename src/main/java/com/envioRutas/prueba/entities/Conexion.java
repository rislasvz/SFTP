package com.envioRutas.prueba.entities;

import java.util.Map;



public class Conexion {

	private String archive;
	
	private Map<String, String> conexion;

	public Map<String, String> getConexion() {
		return conexion;
	}

	public void setConexion(Map<String, String> conexion) {
		this.conexion = conexion;
	}

	
	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Conexion [archive=" + archive + ", conexion=" + conexion + "]";
	}
	
	
	
	
}

