package gob.provias.siged.integracion.dao;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.core.JdbcTemplate;

import gob.provias.siged.integracion.domain.MPVRequestArchivo;
import gob.provias.siged.integracion.domain.MPVRequestDocumento;
import gob.provias.siged.integracion.util.Constantes;

public class RecepcionDAOImpl implements RecepcionDAO{
	
	private static Log log = LogFactory.getLog(RecepcionDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	final String INSERT_RECEPCION_MPV = "insert into IOTDTC_RECEPCION_MPV (SIDRECEXT, CFLGENVCAR, CFLGEST, CTIPDOCIDEREM, DFECREG, VNUMDOCIDEREM,"
			+ "VOBS, VRUCENTREM, VUNIORGREM, VUNIORGSTD, CODTIPOINSTITUCION, TIPODOCUMENTO, NUMERODOCUMENTO, FECHADOCUMENTO, ASUNTO, VNOMENTEMI,"
			+ "DESCARGOREMITENTE, DESREMITENTE) values "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String INSERT_ADJUNTO_MPV = "insert into IOTDTD_ADJUNTO_MPV (IDRECEPCION, TIPOARCHIVO, NOMBREARCHIVO, RUTAARCHIVO, NUMEROFOLIOS, FECHAREGISTRO) values "
			+ "(?, ?, ?, ?, ?, ?)";
	
	private String pattern = "dd/MM/yyyy hh:mm:ss";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String cleaned(String textForClean) {
		 StringBuilder ret =  new StringBuilder();
	    Matcher matches = Pattern.compile("([A-Za-z0-9 nÑáéíóúÁÉÍÓÚ\\:\\/\\-\\_\\.]+)").matcher(textForClean);
	    
	    while (matches.find()) {
	        ret.append(matches.group());
	    }
	
	    return ret.toString().trim();
	}
	
	public int guardarDocumentoRecepcion(MPVRequestDocumento documento) throws Exception{	
		log.info("DAO Guardando recepcion:"+INSERT_RECEPCION_MPV);
				
		// limpiar caracteres
		documento.setCodRemitente(cleaned(documento.getCodRemitente()));
		documento.setNroDocumentoIdenRemitente(cleaned(documento.getNroDocumentoIdenRemitente()));
		documento.setRazonSocialRemitente(cleaned(documento.getRazonSocialRemitente()));
		documento.setDesRemitente(cleaned(documento.getDesRemitente()));
		documento.setDesCargoRemitente(cleaned(documento.getDesCargoRemitente()));
		documento.setDesUnidadRemitente(cleaned(documento.getDesUnidadRemitente()));
		documento.setNroDocumento(cleaned(documento.getNroDocumento()));
		documento.setFechaDocumento(cleaned(documento.getFechaDocumento()));
		documento.setAsunto(cleaned(documento.getAsunto()));
		documento.setObservacion(cleaned(documento.getObservacion()));
		documento.setRecepcionado(cleaned(documento.getRecepcionado()));
		documento.setNomUnidadDestino(cleaned(documento.getNomUnidadDestino()));
		documento.setNomUsuarioDestino(cleaned(documento.getNomUsuarioDestino()));

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
		case 19:
			documento.setTipoDocumento(29); 
			break;
		case 6:
			documento.setTipoDocumento(41); 
			break;
		case 17:
			documento.setTipoDocumento(92); 
			break;
		case 70:
			documento.setTipoDocumento(233); 
			break;
		case 1052:
			documento.setTipoDocumento(343); 
			break;
		case 66:
			documento.setTipoDocumento(345); 
			break;
		case 69:
			documento.setTipoDocumento(351); 
			break;
		case 1036:
			documento.setTipoDocumento(357); 
			break;
		case 16:
			documento.setTipoDocumento(409); 
			break;
		case 23:
			documento.setTipoDocumento(438); 
			break;
		case 62:
			documento.setTipoDocumento(458); 
			break;
		case 55:
			documento.setTipoDocumento(470); 
			break;
		case 27:
			documento.setTipoDocumento(490); 
			break;
		case 45:
			documento.setTipoDocumento(513); 
			break;
		default:
			break;
		}
		
		switch (documento.getCodTipoInstitucion()) {
		case 1:
			documento.setCodTipoInstitucion(3);
			break;
		default:
			break;
		}
		
		switch (documento.getTipoDocumentoIdenRemitente()) {
		case 1:
			documento.setTipoDocumentoIdenRemitente(2);
			break;
		case 2:
			documento.setTipoDocumentoIdenRemitente(1);
			break;
		default:
			break;
		}

		// Empresa
		if (documento.getTipoDocumentoIdenRemitente().equals(1)) {
			documento.setCodRemitente(documento.getNroDocumentoIdenRemitente());
			documento.setNroDocumentoIdenRemitente("");
		}
		else { // Persona
			documento.setCodRemitente("");
		}

//		SIDRECEXT,
//		CFLGENVCAR,
//		CFLGEST,
//		CTIPDOCIDEREM,
//		DFECREG,
//		VNUMDOCIDEREM,
//		VOBS,
//		VRUCENTREM,
//		VUNIORGREM,
//		VUNIORGSTD,
//		CODTIPOINSTITUCION,
//		TIPODOCUMENTO,
//		NUMERODOCUMENTO,
//		FECHADOCUMENTO,
//		ASUNTO,
//		VNOMENTEMI,
//		DESCARGOREMITENTE,
//		DESREMITENTE
				
		return jdbcTemplate.update(INSERT_RECEPCION_MPV,
			documento.getIdRecepcion(),
			Constantes.ESTADO_CARGO_NO,
			Constantes.ESTADO_DOCUMENTO_PENDIENTE,
			documento.getTipoDocumentoIdenRemitente(),
			new Date(),
			documento.getNroDocumentoIdenRemitente(),
			documento.getObservacion(),
			documento.getCodRemitente(),
			documento.getDesUnidadRemitente(),
			documento.getNomUnidadDestino(), 
			documento.getCodTipoInstitucion(),
			documento.getTipoDocumento(),
			documento.getNroDocumento(),
			simpleDateFormat.parse(documento.getFechaDocumento()),
			documento.getAsunto(),
			documento.getRazonSocialRemitente(),
			documento.getDesCargoRemitente(),
			documento.getDesRemitente());
	}
	
	public int guardarAdjuntoRecepcion(Integer idRecepcion, MPVRequestArchivo adjunto){	
		log.info("DAO Guardando adjunto:"+INSERT_ADJUNTO_MPV);
		
	 return jdbcTemplate.update(INSERT_ADJUNTO_MPV, idRecepcion, adjunto.getTipoArchivo(), adjunto.getNombreFisico(), adjunto.getUbicacion(), adjunto.getNumeroFolios(), new Date());		
	}

}
