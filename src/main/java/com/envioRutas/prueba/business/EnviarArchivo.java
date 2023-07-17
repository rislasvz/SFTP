package com.envioRutas.prueba.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.envioRutas.prueba.entities.Conexion;
import com.envioRutas.prueba.util.Constantes;
import com.envioRutas.prueba.util.SftpConnectionException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class EnviarArchivo {

	static final Logger log = LogManager.getLogger(EnviarArchivo.class);

	public void enviar(Conexion conexion) throws SftpException, IOException, SftpConnectionException {
		
		JSch jsch = new JSch();
		
		try {
			
			jsch.setKnownHosts(conexion.getConexion().get("PATH_KNOW_HOST"));
			jsch.addIdentity(conexion.getConexion().get("ID_RSA"));
			
			Session session = jsch.getSession(
					conexion.getConexion().get("USER_NAME"),
					conexion.getConexion().get("HOST"), 
					Integer.parseInt(conexion.getConexion().get("port"))
					);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(10000);
			
			
			if (session.isConnected()) {
				Channel channel = session.openChannel("sftp");
				channel.connect(5000);
				ChannelSftp channelSftp = (ChannelSftp) channel;
				if (channelSftp.isConnected()) {
					String chanString = channelSftp.getHome();
					channelSftp.put(conexion.getConexion().get("PATH_LOCAL_FILE") + "/" + conexion.getArchive(), chanString);
					System.out.println("Archivo enviado con exito..");
				} else {
                    throw new SftpConnectionException("No se pudo establecer una conexión SFTP");
				} 
				  channelSftp.disconnect();
				channelSftp.exit();
				
			} else {
                throw new SftpConnectionException("No se pudo establecer una sesión SFTP");
			}		
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
            throw new SftpConnectionException("Error al conectar con el servidor SFTP", e);

		} catch (SftpException e) {
			e.printStackTrace();
            throw new SftpConnectionException("Error al transferir el archivo a través de SFTP", e);
		}
}
	
		

	
	
	
	

}
