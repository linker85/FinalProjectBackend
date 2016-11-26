package com.proximate.www.pushmate.model;

import java.util.LinkedHashMap;

/**
 * @author Raul
 *
 */
public class NotificacionesWS {
	private String fechaProgramacion;
	private String horaProgramacion;
	private String titulo;
	private String cuerpo;
	private String canal;
	private String token;
	private LinkedHashMap<String, String> plataformas;
	private LinkedHashMap<String, String> filtros;
	private LinkedHashMap<String, String> parametros;
	
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public LinkedHashMap<String, String> getFiltros() {
		return filtros;
	}
	public void setFiltros(LinkedHashMap<String, String> filtros) {
		this.filtros = filtros;
	}
	public String getHoraProgramacion() {
		return horaProgramacion;
	}
	public void setHoraProgramacion(String horaProgramacion) {
		this.horaProgramacion = horaProgramacion;
	}
	public LinkedHashMap<String, String> getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(LinkedHashMap<String, String> plataformas) {
		this.plataformas = plataformas;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LinkedHashMap<String, String> getParametros() {
		return parametros;
	}
	public void setParametros(LinkedHashMap<String, String> parametros) {
		this.parametros = parametros;
	}
	@Override
	public String toString() {
		return "NotificacionesWS [fechaProgramacion=" + fechaProgramacion + ", horaProgramacion=" + horaProgramacion
				+ ", titulo=" + titulo + ", cuerpo=" + cuerpo + ", canal=" + canal + ", token=" + token
				+ ", plataformas=" + plataformas + ", filtros=" + filtros + ", parametros=" + parametros + "]";
	}
}