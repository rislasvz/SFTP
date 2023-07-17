package com.envioRutas.prueba.util;

public class SftpConnectionException extends Exception{

	public SftpConnectionException(String message) {
        super(message);
    }
	
	public SftpConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
