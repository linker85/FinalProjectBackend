package com.proximate.www.pushmate.model;

import java.io.Serializable;

public class NotificacionNegocio  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private java.sql.Timestamp  fechaCreacion;
	private java.sql.Timestamp  fechaProgramacion;
	private java.sql.Timestamp  fechaEnvio;
	private String titulo;
	private String cuerpo;
	private String canal;
	private int notificacionId;
	private int estatusId;
	private String filtro;
	private String token;
	private String plataforma;
	private String parametros;
	
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
	public String getCanal() {
		return canal;
	}
	public int getNotificacionId() {
		return notificacionId;
	}
	public int getEstatusId() {
		return estatusId;
	}
	public String getToken() {
		return token;
	}
	public String getPlataforma() {
		return plataforma;
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
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public void setNotificacionId(int notificacionId) {
		this.notificacionId = notificacionId;
	}
	public void setEstatusId(int estatusId) {
		this.estatusId = estatusId;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	@Override
	public String toString() {
		return "NotificacionNegocio [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaProgramacion="
				+ fechaProgramacion + ", fechaEnvio=" + fechaEnvio + ", titulo=" + titulo + ", cuerpo=" + cuerpo
				+ ", canal=" + canal + ", notificacionId=" + notificacionId + ", estatusId=" + estatusId + ", filtro="
				+ filtro + ", token=" + token + ", plataforma=" + plataforma + ", parametros=" + parametros + "]";
	}
}