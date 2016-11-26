package com.proximate.www.pushmate.model;

public class Token {
	
	private String filtro;
	private String token;
	private java.sql.Timestamp fechaAcceso;
	private String plataforma;
	private String arn_topic;
	private String arn_suscription;
	private String arn_app;
	private String arn_endpoint;
	private int id_aplicacion;
	private int id_canal;
	
	private String mac;
	
	public String getFiltro() {
		return filtro;
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
	public java.sql.Timestamp getFechaAcceso() {
		return fechaAcceso;
	}
	public void setFechaAcceso(java.sql.Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getArn_topic() {
		return arn_topic;
	}
	public void setArn_topic(String arn_topic) {
		this.arn_topic = arn_topic;
	}
	public String getArn_suscription() {
		return arn_suscription;
	}
	public void setArn_suscription(String arn_suscription) {
		this.arn_suscription = arn_suscription;
	}
	public int getId_aplicacion() {
		return id_aplicacion;
	}
	public void setId_aplicacion(int id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}
	public int getId_canal() {
		return id_canal;
	}
	public void setId_canal(int id_canal) {
		this.id_canal = id_canal;
	}
	public String getArn_app() {
		return arn_app;
	}
	public void setArn_app(String arn_app) {
		this.arn_app = arn_app;
	}
	public String getArn_endpoint() {
		return arn_endpoint;
	}
	public void setArn_endpoint(String arn_endpoint) {
		this.arn_endpoint = arn_endpoint;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
}