package com.proximate.www.pushmate.model;

import java.sql.Timestamp;

public class Log {
	private int id;
	private String descripcion;
	private int catalogId;
	private Timestamp fecha;
	
	public int getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getCatalogId() {
		return catalogId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
	
}
