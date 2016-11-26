package com.proximate.www.pushmate.model;

import java.io.Serializable;

public class TipoNotificacion implements Serializable{
	private int id;
	private String nombre;
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
	
	@Override
	public String toString() {
		return "TipoNotificacion [id=" + id + ", nombre=" + nombre + "]";
	}
	

}
