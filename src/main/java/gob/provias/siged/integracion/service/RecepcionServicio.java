package gob.provias.siged.integracion.service;

import gob.provias.siged.integracion.domain.MPVRequestDocumento;
import gob.provias.siged.integracion.response.MPVResponse;

public interface RecepcionServicio {
	
	MPVResponse guardarRecepcionMPV(MPVRequestDocumento documento) throws Exception;

}