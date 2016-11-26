package com.proximate.www.pushmate.model;

public class CanalesTopics {
	private int id;
	private String nombre;
	private boolean activo;
	private int id_aplicacion;
	private String topic_arn;
	private String plataforma;
	private String app_arn;	
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public int getId_aplicacion() {
		return id_aplicacion;
	}
	public void setId_aplicacion(int id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}
	public String getTopic_arn() {
		return topic_arn;
	}
	public void setTopic_arn(String topic_arn) {
		this.topic_arn = topic_arn;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getApp_arn() {
		return app_arn;
	}
	public void setApp_arn(String app_arn) {
		this.app_arn = app_arn;
	}	
		
}