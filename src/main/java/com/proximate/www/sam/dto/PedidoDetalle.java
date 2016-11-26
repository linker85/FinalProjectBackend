package com.proximate.www.sam.dto;

public class PedidoDetalle {
	private String clave_articulo;
	private String descripcion;
	private float precio;
	private int cantidad;
	private float total;
	
	public String getClave_articulo() {
		return clave_articulo;
	}
	public void setClave_articulo(String clave_articulo) {
		this.clave_articulo = clave_articulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
}