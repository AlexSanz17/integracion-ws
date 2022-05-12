package gob.provias.siged.integracion.response;

public class MPVResponse {
	private String codigoRespuesta;
	private String mensajeRespuesta;
	
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	@Override
	public String toString() {
		return "MPVResponse [codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + "]";
	}
}