package com.proximate.www.pushmate.model;

public class PushTemporal {

	private int id;
	private String filtro;
	private String mensaje;
	private int idUsuario;
	private String token;
	private String canal;
	private String titulo;
	private String plataforma;
	public int getId() {
		return id;
	}
	public String getFiltro() {
		return filtro;
	}
	public String getMensaje() {
		return mensaje;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getToken() {
		return token;
	}
	public String getCanal() {
		return canal;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	@Override
	public String toString() {
		return "PushTemporal [id=" + id + ", filtro=" + filtro + ", mensaje="
				+ mensaje + ", idUsuario=" + idUsuario + ", token=" + token
				+ ", canal=" + canal + ", titulo=" + titulo + ", plataforma="
				+ plataforma + "]";
	}
	
	
}
