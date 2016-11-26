package com.proximate.www.pushmate.model;

public class TipoPlataforma {
	private int id;
	private String nombre;
	private boolean activo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "TipoPlataforma [id=" + id + ", nombre=" + nombre + ", activo="
				+ activo + "]";
	}
	
	

}
