package gob.provias.siged.integracion.dao;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			+ "VOBS, VRUCENTREM, VUNIORGREM, VUNIORGSTD, CODTIPOINSTITUCION, TIPODOCUMENTO, NUMERODOCUMENTO, FECHADOCUMENTO, ASUNTO, VNOMENTEMI,"
			+ "DESCARGOREMITENTE, DESREMITENTE) values "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String INSERT_ADJUNTO_MPV = "insert into IOTDTD_ADJUNTO_MPV (IDRECEPCION, TIPOARCHIVO, NOMBREARCHIVO, RUTAARCHIVO, FECHAREGISTRO) values "
			+ "(?, ?, ?, ?, ?)";
	
	private String pattern = "dd/MM/yyyy";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String cleaned(String textForClean) {
		 StringBuilder ret =  new StringBuilder();
	    Matcher matches = Pattern.compile("([A-Za-z0-9 \\-\\_\\.]+)").matcher(textForClean);
	    
	    while (matches.find()) {
	        ret.append(matches.group());
	    }
	
	    return ret.toString();
	}
	
	public int guardarDocumentoRecepcion(MPVRequestDocumento documento) throws Exception{	
		log.info("DAO Guardando recepcion:"+INSERT_RECEPCION_MPV);
		
		// Limpieza de datos
		documento.setDesUnidadRemitente("");
		documento.setNomUnidadDestino("");
		documento.setObservacion("");
				
		// limpiar caracteres
		documento.setObservacion(cleaned(documento.getObservacion()));
		documento.setDesUnidadRemitente(cleaned(documento.getDesUnidadRemitente()));
		documento.setNomUnidadDestino(cleaned(documento.getNomUnidadDestino()));
		documento.setAsunto(cleaned(documento.getAsunto()));
		documento.setRazonSocialRemitente(cleaned(documento.getRazonSocialRemitente()));
		documento.setDesCargoRemitente(cleaned(documento.getDesCargoRemitente()));
		documento.setDesRemitente(cleaned(documento.getDesRemitente()));
		
		log.info("LIMPIEZA DE CAMPOS");
		log.info(documento);
		
		switch (documento.getTipoDocumento()) {
		case 5:
			documento.setTipoDocumento(1); 
			break;
		case 8:
			documento.setTipoDocumento(2); 
			break;
		case 2:
			documento.setTipoDocumento(3); 
			break;
		case 1004:
			documento.setTipoDocumento(8); 
			break;
		case 14:
			documento.setTipoDocumento(11); 
			break;
		case 3:
			documento.setTipoDocumento(12); 
			break;
		case 4:
			documento.setTipoDocumento(13); 
			break;
		case 1:
			documento.setTipoDocumento(15); 
			break;
		case 18:
			documento.setTipoDocumento(17); 
			break;
		case 1051:
			documento.setTipoDocumento(18); 
			break;
		case 50:
			documento.setTipoDocumento(28); 
			break;		
		default:			
			break;
		}		
		
		log.info("TIPO DE DOCUMENTO");
		log.info(documento.getTipoDocumento());
		
		// Match tipo documento
		String tipoDoc = "select idtipodocumento from tipodocumento where idtipodocumento_std = " + documento.getTipoDocumento();
//		jdbcTemplate.execute(tipoDoc);
		
		return jdbcTemplate.update(INSERT_RECEPCION_MPV, documento.getIdRecepcion(), Constantes.ESTADO_CARGO_NO, Constantes.ESTADO_DOCUMENTO_PENDIENTE, 
			documento.getTipoDocumentoIdenRemitente(), new Date(), documento.getNroDocumentoIdenRemitente(), documento.getObservacion(), 
			documento.getCodRemitente(), documento.getDesUnidadRemitente(), documento.getNomUnidadDestino(), 
			documento.getCodTipoInstitucion(), documento.getTipoDocumento(), documento.getNroDocumento(), 
			simpleDateFormat.parse(documento.getFechaDocumento()), documento.getAsunto(), documento.getRazonSocialRemitente(),
			documento.getDesCargoRemitente(), documento.getDesRemitente());
	}
	
	public int guardarAdjuntoRecepcion(Integer idRecepcion, MPVRequestArchivo adjunto){	
		log.info("DAO Guardando adjunto:"+INSERT_ADJUNTO_MPV);
		
	 return jdbcTemplate.update(INSERT_ADJUNTO_MPV, idRecepcion, adjunto.getTipoArchivo(), adjunto.getNombreFisico(), adjunto.getUbicacion(), new Date());		
	}

}
