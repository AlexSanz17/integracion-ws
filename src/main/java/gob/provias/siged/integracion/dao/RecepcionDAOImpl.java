package gob.provias.siged.integracion.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import gob.provias.siged.integracion.request.MPVRequestArchivo;
import gob.provias.siged.integracion.request.MPVRequestDocumento;
import gob.provias.siged.integracion.util.Constantes;

public class RecepcionDAOImpl implements RecepcionDAO{
	
	private static Log log = LogFactory.getLog(RecepcionDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	final String INSERT_RECEPCION_MPV = "insert into IOTDTC_RECEPCION_MPV (SIDRECEXT, CFLGENVCAR, CFLGEST, CTIPDOCIDEREM, DFECREG, VNUMDOCIDEREM,"
			+ "VOBS, VRUCENTREM, VUNIORGREM, VUNIORGSTD, CODTIPOINSTITUCION, TIPODOCUMENTO, NUMERODOCUMENTO, FECHADOCUMENTO, ASUNTO, VNOMENTEMI) values "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String INSERT_ADJUNTO_MPV = "insert into IOTDTD_ADJUNTO_MPV (IDRECEPCION, TIPOARCHIVO, NOMBREARCHIVO, RUTAARCHIVO, FECHAREGISTRO) values "
			+ "(?, ?, ?, ?, ?)";
	
	private String pattern = "dd/MM/yyyy";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int guardarDocumentoRecepcion(MPVRequestDocumento documento) throws Exception{	
		log.info("DAO Guardando recepcion:"+INSERT_RECEPCION_MPV);
		
	 return jdbcTemplate.update(INSERT_RECEPCION_MPV, documento.getIdRecepcion(), Constantes.ESTADO_CARGO_NO, Constantes.ESTADO_DOCUMENTO_PENDIENTE, 
				documento.getTipoDocumentoIdenRemitente(), new Date(), "", documento.getObservacion(), 
				documento.getNroDocumentoIdenRemitente(), documento.getDesUnidadRemitente(), documento.getNomUnidadDestino(), 
				documento.getCodTipoInstitucion(), documento.getTipoDocumento(), documento.getNroDocumento(), 
				simpleDateFormat.parse(documento.getFechaDocumento()), documento.getAsunto(), documento.getRazonSocialRemitente());		
	}
	
	public int guardarAdjuntoRecepcion(Integer idRecepcion, MPVRequestArchivo adjunto){	
		log.info("DAO Guardando adjunto:"+INSERT_ADJUNTO_MPV);
		
	 return jdbcTemplate.update(INSERT_ADJUNTO_MPV, idRecepcion, adjunto.getTipoArchivo(), adjunto.getNombreFisico(), adjunto.getUbicacion(), new Date());		
	}

}
