package gob.provias.siged.integracion.dao;

import gob.provias.siged.integracion.domain.MPVRequestArchivo;
import gob.provias.siged.integracion.domain.MPVRequestDocumento;

public interface RecepcionDAO {
	
	int guardarDocumentoRecepcion(MPVRequestDocumento documento) throws Exception;

	int guardarAdjuntoRecepcion(Integer idRecepcion, MPVRequestArchivo adjunto);
	
}