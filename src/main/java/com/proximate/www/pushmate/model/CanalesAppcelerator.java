package com.proximate.www.pushmate.model;

public class CanalesAppcelerator {
	private int id;
	private String nombre;
	private boolean activo;
	private int id_aplicacion;
	
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
	
}