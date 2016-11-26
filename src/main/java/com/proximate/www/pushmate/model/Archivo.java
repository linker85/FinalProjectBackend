package com.proximate.www.pushmate.model;

import java.sql.Blob;


public class Archivo {
	
	private int id;
	private String nombre;
	private Blob mensaje;
	
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
	public Blob getMensaje() {
		return mensaje;
	}
	public void setMensaje(Blob mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
