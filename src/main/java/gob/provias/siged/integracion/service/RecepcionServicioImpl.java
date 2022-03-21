package gob.provias.siged.integracion.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gob.provias.siged.integracion.dao.RecepcionDAO;
import gob.provias.siged.integracion.request.MPVRequestArchivo;
import gob.provias.siged.integracion.request.MPVRequestDocumento;
import gob.provias.siged.integracion.response.MPVResponse;
import gob.provias.siged.integracion.util.Constantes;

public class RecepcionServicioImpl implements RecepcionServicio{
	
	private static Log log = LogFactory.getLog(RecepcionServicioImpl.class);
	private RecepcionDAO recepcionDAO;
	
	public MPVResponse guardarRecepcionMPV(MPVRequestDocumento documento) throws Exception{
		MPVResponse response = new MPVResponse();
		
		log.info("[Inicio] Service guardarRecepcionMPV:"+documento.toString());
		
		int respuesta = recepcionDAO.guardarDocumentoRecepcion(documento);	
		
		log.info("Respuesta guardarDocumentoRecepcion:"+respuesta);
		
		for(MPVRequestArchivo adjunto:documento.getArchivos()){
			respuesta = recepcionDAO.guardarAdjuntoRecepcion(documento.getIdRecepcion(), adjunto);
			
			log.info("Respuesta guardarAdjuntoRecepcion:"+respuesta);
		}
		
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensajeRespuesta("Registro de documento exitoso ");
		
		log.info("[Fin] Service guardarRecepcionMPV:"+response.toString());
		
		return response;		
	}

	public RecepcionDAO getRecepcionDAO() {
		return recepcionDAO;
	}

	public void setRecepcionDAO(RecepcionDAO recepcionDAO) {
		this.recepcionDAO = recepcionDAO;
	}

	
}
