package gob.provias.siged.integracion.domain;

public class MPVRequestArchivo {
	private Integer idRegistro;
	private String tipoArchivo;
	private String nombreFisico;
	private String ubicacion;
	private String numeroFolios;
	
	public MPVRequestArchivo() {
	}
	
	public Integer getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public String getNombreFisico() {
		return nombreFisico;
	}
	public void setNombreFisico(String nombreFisico) {
		this.nombreFisico = nombreFisico;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getNumeroFolios() {
		return numeroFolios;
	}
	
	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	
	@Override
	public String toString() {
		return "MPVRequestArchivo [idRegistro=" + idRegistro + ", tipoArchivo=" + tipoArchivo + ", nombreFisico="
			+ nombreFisico + ", ubicacion=" + ubicacion + ", numeroFolios=" + numeroFolios + "]";
	}
}
