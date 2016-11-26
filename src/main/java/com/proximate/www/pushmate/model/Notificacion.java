package com.proximate.www.pushmate.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.proximate.www.pushmate.constants.ActionsConstants;
import com.proximate.www.pushmate.utils.DateUtils;

public class Notificacion  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private java.sql.Timestamp  fechaCreacion;
	private java.sql.Timestamp  fechaProgramacion;
	private java.sql.Timestamp  fechaEnvio;
	private String titulo;
	private String cuerpo;
	private String canal;
	private TipoNotificacion tipoID;
	private boolean ios;
	private boolean android;
	private boolean blackberry;
	private boolean windowsPhone;
	private int usuarioId;
	private Estatus estatusId;
	private boolean avanzada;
	private String filtro;
	private String token;
	
	private String textoApp;
	private int id_aplicacion;
	
	public int getId() {
		return id;
	}
	public java.sql.Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public java.sql.Timestamp getFechaProgramacion() {
		return fechaProgramacion;
	}
	public java.sql.Timestamp getFechaEnvio() {
		return fechaEnvio;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public TipoNotificacion getTipoID() {
		return tipoID;
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
	public int getUsuarioId() {
		return usuarioId;
	}
	public Estatus getEstatusId() {
		return estatusId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public void setFechaProgramacion(java.sql.Timestamp fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public void setFechaEnvio(java.sql.Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public void setTipoID(TipoNotificacion tipoID) {
		this.tipoID = tipoID;
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
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public void setEstatusId(Estatus estatusId) {
		this.estatusId = estatusId;
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
	public void setAvanzada(boolean avanzada) {
		this.avanzada = avanzada;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String toJson(){
		StringBuffer bf = new StringBuffer();
		SimpleDateFormat fecha = new SimpleDateFormat(ActionsConstants.FORMATO_ANALYTICS);
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
		Date fechaP = DateUtils.getDate(fechaProgramacion);
		bf.append("{");
		bf.append("\"id\"").append(":").append("\""+id+"\"").append(",");
		bf.append("\"fechaCreacion\"").append(":").append("\""+fechaCreacion+"\"").append(",");
		bf.append("\"fechaProgramacion\"").append(":").append("\""+fechaProgramacion+"\"").append(",");
		bf.append("\"fecha_programacion\"").append(":").append("\""+fecha.format(fechaP)+"\"").append(",");
		bf.append("\"hora_programacion\"").append(":").append("\""+hora.format(fechaP)+"\"").append(",");
		bf.append("\"titulo\"").append(":").append("\""+titulo+"\"").append(",");
		bf.append("\"cuerpo\"").append(":").append("\""+cuerpo+"\"").append(",");
		bf.append("\"ios\"").append(":").append("\""+ios+"\"").append(",");
		bf.append("\"android\"").append(":").append("\""+android+"\"").append(",");
		bf.append("\"blackberry\"").append(":").append("\""+blackberry+"\"").append(",");
		bf.append("\"windowsPhone\"").append(":").append("\""+windowsPhone+"\"").append(",");
		bf.append("\"estatusId\"").append(":").append("\""+estatusId.getId()+"\"").append(",");
		bf.append("\"estatus\"").append(":").append("\""+estatusId.getValor()+"\"").append(",");
		bf.append("\"tipoId\"").append(":").append("\""+tipoID.getId()+"\"").append(",");
		bf.append("\"tipo\"").append(":").append("\""+tipoID.getNombre()+"\"");
		bf.append("}");
		return bf.toString();
	}
	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", fechaCreacion=" + fechaCreacion
				+ ", fechaProgramacion=" + fechaProgramacion + ", fechaEnvio="
				+ fechaEnvio + ", titulo=" + titulo + ", cuerpo=" + cuerpo
				+ ", canal=" + canal + ", tipoID=" + tipoID + ", ios=" + ios
				+ ", android=" + android + ", blackberry=" + blackberry
				+ ", windowsPhone=" + windowsPhone + ", usuarioId=" + usuarioId
				+ ", estatusId=" + estatusId + ", avanzada=" + avanzada
				+ ", filtro=" + filtro + ", token=" + token + "]";
	}
	public String getTextoApp() {
		return textoApp;
	}
	public void setTextoApp(String textoApp) {
		this.textoApp = textoApp;
	}
	public int getId_aplicacion() {
		return id_aplicacion;
	}
	public void setId_aplicacion(int id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}
	

	
}
