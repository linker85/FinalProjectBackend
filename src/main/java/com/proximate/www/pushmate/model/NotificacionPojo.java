package com.proximate.www.pushmate.model;

public class NotificacionPojo {
	
	private int id;
	private String fechaCreacion;
	private String fechaProgramacion;
	private String fechaEnvio;
	private String titulo;
	private String cuerpo;
	private String tipo;
	private boolean ios;
	private boolean android;
	private boolean blackberry;
	private boolean windowsPhone;
	private String usuario;
	private String estatus;
	private String canal;
	private boolean avanzada;
	private String filtro;
	private String token;
	private String parametros;
	
	public int getId() {
		return id;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public String getTipo() {
		return tipo;
	}
	public boolean getIos() {
		return ios;
	}
	public boolean getAndroid() {
		return android;
	}
	public boolean getBlackberry() {
		return blackberry;
	}
	public boolean getWindowsPhone() {
		return windowsPhone;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setIos(boolean ios) {
		this.ios = ios;
	}
	public void setAndroid(boolean android) {
		this.android = android;
	}
	public void setBlackberry(boolean blackberry) {
		this.blackberry = blackberry;
	}
	public void setWindowsPhone(boolean windowsPhone) {
		this.windowsPhone = windowsPhone;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public boolean isAvanzada() {
		return avanzada;
	}
	public String getFiltro() {
		return filtro;
	}
	public String getToken() {
		return token;
	}
	public void setAvanzada(boolean avanzada) {
		this.avanzada = avanzada;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}	
	public String toJSONSMALL(String id){
		StringBuffer bf = new StringBuffer();
		bf.append("\""+((id == null) ? getId() : id)+"\": {");
		bf.append("\"id_notificaciones\":\""+getId()+"\"");
		bf.append(",");
		bf.append("\"tipo\":\""+getTipo()+"\"");
		bf.append(",");
		bf.append("\"titulo\":\""+getTitulo()+ "\"");
		bf.append(",");
		bf.append("\"mensaje\":\""+getCuerpo()+ "\"");
		bf.append(",");
		bf.append("\"creacion\":\""+ getFechaCreacion() + "\"");
		bf.append(",");
		bf.append("\"envio\":\"" + getFechaEnvio()+ "\"");
		bf.append(",");
		bf.append("\"programacion\":\""+ getFechaProgramacion()+ "\"");
		bf.append(",");
		bf.append("\"usuario\":\""+ getUsuario() + "\"");
		bf.append(",");
		bf.append("\"android\":\""+ getAndroid() + "\"");
		bf.append(",");
		bf.append("\"ios\":\""+ getIos() + "\"");
		bf.append(",");
		bf.append("\"blackberry\":\""+ getBlackberry() + "\"");
		bf.append(",");
		bf.append("\"windowsPhone\":\""+ getWindowsPhone() + "\"");
		bf.append(",");
		bf.append("\"canal\":\""+ getCanal() + "\"");
		bf.append(",");
		bf.append("\"estado\":\""+ getEstatus() + "\"");
		bf.append(",");
		bf.append("\"filtro\":\""+ getFiltro() + "\"");	
		bf.append(",");
		if (getParametros()== null || getParametros().equals("")) {
			bf.append("\"parametros\":{}");
		} else {
			bf.append("\"parametros\":"+ getParametros());
		}		
		bf.append("}");
		return bf.toString();
	}
	@Override
	public String toString() {
		return "NotificacionPojo [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaProgramacion="
				+ fechaProgramacion + ", fechaEnvio=" + fechaEnvio + ", titulo=" + titulo + ", cuerpo=" + cuerpo
				+ ", tipo=" + tipo + ", ios=" + ios + ", android=" + android + ", blackberry=" + blackberry
				+ ", windowsPhone=" + windowsPhone + ", usuario=" + usuario + ", estatus=" + estatus + ", canal="
				+ canal + ", avanzada=" + avanzada + ", filtro=" + filtro + ", token=" + token + ", parametros="
				+ parametros + "]";
	}	
	
}