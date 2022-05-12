package gob.provias.siged.integracion.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gob.provias.siged.integracion.domain.MPVRequestArchivo;
import gob.provias.siged.integracion.domain.MPVRequestDocumento;
import gob.provias.siged.integracion.response.MPVResponse;
import gob.provias.siged.integracion.service.RecepcionServicio;
import gob.provias.siged.integracion.util.Constantes;
 
@WebService(serviceName="MPVService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class MPVService {
	private static Log log = LogFactory.getLog(MPVService.class);
	
	RecepcionServicio recepcionServicio;
	
	@WebMethod(exclude=true)
    public void setRecepcionServicio(RecepcionServicio recepcionServicio) {
		this.recepcionServicio = recepcionServicio;
	}

	@WebMethod
    public MPVResponse recepcionTramiteVirtual(MPVRequestDocumento request){
		MPVResponse response = new MPVResponse();
		log.info("[Inicio] recepcionTramiteVirtual(request):"+request.toString());
		
		try{
			
			String validacion = validacionRequest(request);
			
			if(!validacion.equals("")){
				response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_VALIDACION);
				response.setMensajeRespuesta(validacion);
				
			}else{
				response = recepcionServicio.guardarRecepcionMPV(request);
			}
						
		}catch(Exception e){
			e.printStackTrace();
			
			response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_ERROR);
			response.setMensajeRespuesta("Error al recepcionar tramite virtual:"+e.getMessage());
		}
		
		log.info("[FIn] recepcionTramiteVirtual(response):"+response.toString());
		
		return response;
	}
	
	private String validacionRequest(MPVRequestDocumento request){
		if(request.getIdRecepcion() == null || request.getIdRecepcion().toString().equals("")){
			return "El campo idRecepcion es obligatorio";
		}
		
		try{ 			
			Integer.parseInt(request.getIdRecepcion().toString()); 		
		}catch (NumberFormatException e){ 			
			return "El campo idRecepcion debe ser Integer";
		} 
		
				
		if(request.getCodTipoInstitucion() == null || request.getCodTipoInstitucion().toString().equals("")){
			return "El campo codTipoInstitucion es obligatorio";
		}
		
		try{ 			
			Integer.parseInt(request.getCodTipoInstitucion().toString()); 		
		}catch (NumberFormatException e){ 			
			return "El campo codTipoInstitucion debe ser Integer";
		}
		
		if(!request.getCodTipoInstitucion().toString().equals("1") && !request.getCodTipoInstitucion().toString().equals("2")
				&& !request.getCodTipoInstitucion().toString().equals("3")){
			return "El campo codTipoInstitucion debe ser 1:Entidad, 2:Empresa, 3:Persona Natural";
		}
		
		if(request.getTipoDocumentoIdenRemitente() == null || request.getTipoDocumentoIdenRemitente().toString().equals("")){
			return "El campo tipoDocumentoIdenRemitente es obligatorio";
		}
		
		if(!request.getTipoDocumentoIdenRemitente().toString().equals("1") && !request.getTipoDocumentoIdenRemitente().toString().equals("2")
				&& !request.getTipoDocumentoIdenRemitente().toString().equals("3")){
			return "El campo tipoDocumentoIdenRemitente debe ser 1:RUC, 2:DNI, 3:Otro";
		}
		
		if(request.getNroDocumentoIdenRemitente() == null || request.getNroDocumentoIdenRemitente().equals("")){
			return "El campo nroDocumentoIdenRemitente es obligatorio";
		}
		
		if(request.getRazonSocialRemitente() == null || request.getRazonSocialRemitente().equals("")){
			return "El campo razonSocialRemitente es obligatorio";
		}
		
		if(request.getTipoDocumento() == null || request.getTipoDocumento().toString().equals("")){
			return "El campo tipoDocumento es obligatorio";
		}
		
		if(request.getNroDocumento() == null || request.getNroDocumento().equals("")){
			return "El campo nroDocumento es obligatorio";
		}
		
		if(request.getFechaDocumento() == null || request.getFechaDocumento().equals("")){
			return "El campo fechaDocumento es obligatorio";
		}
		
		if(request.getAsunto() == null || request.getAsunto().equals("")){
			return "El campo asunto es obligatorio";
		}
		
		if(request.getRecepcionado() == null || request.getRecepcionado().equals("")){
			return "El campo recepcionado es obligatorio";
		}
		
		if(!request.getRecepcionado().equals("R")){
			return "El campo recepcionado debe tener el estado R";
		}
				
		if(request.getArchivos() == null || request.getArchivos().size() == 0){
			return "Debe agregar al menos un archivo";
		}
		
		int index = 1;
		for(MPVRequestArchivo archivo:request.getArchivos()){
			
			if(archivo.getTipoArchivo() == null || archivo.getTipoArchivo().equals("")){
				return "El campo tipoArchivo es obligatorio para el archivo "+index;
			}
			
			if(!archivo.getTipoArchivo().equals("1") && !archivo.getTipoArchivo().equals("2")){
				return "El campo tipoArchivo debe ser 1:Principal, 2:Anexo para el archivo "+index;
			}
			
			if(archivo.getNombreFisico() == null || archivo.getNombreFisico().equals("")){
				return "El campo nombreFisico es obligatorio para el archivo "+index;
			}
			
			if(archivo.getUbicacion() == null || archivo.getUbicacion().equals("")){
				return "El campo ubicacion es obligatorio para el archivo "+index;
			}
			
			index++;
		}
		
		return "";
	}
}