package gob.provias.siged.integracion.request;

import java.util.List;

public class MPVRequestDocumento {

	private Integer idRegistro;
	private Integer idRecepcion;
	private Integer codRemitente;
	private Integer codTipoInstitucion;
	private Integer tipoDocumentoIdenRemitente;
	private String nroDocumentoIdenRemitente;
	private String razonSocialRemitente;
	private String desRemitente;
	private String desCargoRemitente;
	private String desUnidadRemitente;
	private Integer tipoDocumento;
	private String nroDocumento;
	private String fechaDocumento;
	private String asunto;
	private String observacion;
	private String recepcionado;
	private Integer idUnidadDestino;
	private String nomUnidadDestino;
	private Integer idUsuarioDestino;
	private String nomUsuarioDestino;
	private List<MPVRequestArchivo> archivos;

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Integer getIdRecepcion() {
		return idRecepcion;
	}

	public void setIdRecepcion(Integer idRecepcion) {
		this.idRecepcion = idRecepcion;
	}

	public Integer getCodRemitente() {
		return codRemitente;
	}

	public void setCodRemitente(Integer codRemitente) {
		this.codRemitente = codRemitente;
	}

	public Integer getCodTipoInstitucion() {
		return codTipoInstitucion;
	}

	public void setCodTipoInstitucion(Integer codTipoInstitucion) {
		this.codTipoInstitucion = codTipoInstitucion;
	}

	public Integer getTipoDocumentoIdenRemitente() {
		return tipoDocumentoIdenRemitente;
	}

	public void setTipoDocumentoIdenRemitente(Integer tipoDocumentoIdenRemitente) {
		this.tipoDocumentoIdenRemitente = tipoDocumentoIdenRemitente;
	}

	public String getNroDocumentoIdenRemitente() {
		return nroDocumentoIdenRemitente;
	}

	public void setNroDocumentoIdenRemitente(String nroDocumentoIdenRemitente) {
		this.nroDocumentoIdenRemitente = nroDocumentoIdenRemitente;
	}

	public String getRazonSocialRemitente() {
		return razonSocialRemitente;
	}

	public void setRazonSocialRemitente(String razonSocialRemitente) {
		this.razonSocialRemitente = razonSocialRemitente;
	}

	public String getDesRemitente() {
		return desRemitente;
	}

	public void setDesRemitente(String desRemitente) {
		this.desRemitente = desRemitente;
	}

	public String getDesCargoRemitente() {
		return desCargoRemitente;
	}

	public void setDesCargoRemitente(String desCargoRemitente) {
		this.desCargoRemitente = desCargoRemitente;
	}

	public String getDesUnidadRemitente() {
		return desUnidadRemitente;
	}

	public void setDesUnidadRemitente(String desUnidadRemitente) {
		this.desUnidadRemitente = desUnidadRemitente;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getRecepcionado() {
		return recepcionado;
	}

	public void setRecepcionado(String recepcionado) {
		this.recepcionado = recepcionado;
	}

	public Integer getIdUnidadDestino() {
		return idUnidadDestino;
	}

	public void setIdUnidadDestino(Integer idUnidadDestino) {
		this.idUnidadDestino = idUnidadDestino;
	}

	public String getNomUnidadDestino() {
		return nomUnidadDestino;
	}

	public void setNomUnidadDestino(String nomUnidadDestino) {
		this.nomUnidadDestino = nomUnidadDestino;
	}

	public Integer getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	public void setIdUsuarioDestino(Integer idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
	}

	public String getNomUsuarioDestino() {
		return nomUsuarioDestino;
	}

	public void setNomUsuarioDestino(String nomUsuarioDestino) {
		this.nomUsuarioDestino = nomUsuarioDestino;
	}

	public List<MPVRequestArchivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<MPVRequestArchivo> archivos) {
		this.archivos = archivos;
	}

	@Override
	public String toString() {
		return "MPVRequestDocumento [idRegistro=" + idRegistro + ", idRecepcion=" + idRecepcion + ", codRemitente="
				+ codRemitente + ", codTipoInstitucion=" + codTipoInstitucion + ", tipoDocumentoIdenRemitente="
				+ tipoDocumentoIdenRemitente + ", nroDocumentoIdenRemitente=" + nroDocumentoIdenRemitente
				+ ", razonSocialRemitente=" + razonSocialRemitente + ", desRemitente=" + desRemitente
				+ ", desCargoRemitente=" + desCargoRemitente + ", desUnidadRemitente=" + desUnidadRemitente
				+ ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento + ", fechaDocumento="
				+ fechaDocumento + ", asunto=" + asunto + ", observacion=" + observacion + ", recepcionado="
				+ recepcionado + ", idUnidadDestino=" + idUnidadDestino + ", nomUnidadDestino=" + nomUnidadDestino
				+ ", idUsuarioDestino=" + idUsuarioDestino + ", nomUsuarioDestino=" + nomUsuarioDestino + ", archivos="
				+ archivos + "]";
	}
	
	

}
