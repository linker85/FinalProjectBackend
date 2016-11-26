package com.proximate.www.sam.dto;

public class Pedido {
	private long id_pedido;
	
	private int ano_fiscal;
	private int campana;
	private int numero_fullerette;
	
	private String fecha_pedido;
	private String fecha_termina_camp;
	private String email;
	private String nombre;
	
	public long getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(long id_pedido) {
		this.id_pedido = id_pedido;
	}
	public int getAno_fiscal() {
		return ano_fiscal;
	}
	public void setAno_fiscal(int ano_fiscal) {
		this.ano_fiscal = ano_fiscal;
	}
	public int getCampana() {
		return campana;
	}
	public void setCampana(int campana) {
		this.campana = campana;
	}
	public int getNumero_fullerette() {
		return numero_fullerette;
	}
	public void setNumero_fullerette(int numero_fullerette) {
		this.numero_fullerette = numero_fullerette;
	}
	public String getFecha_pedido() {
		return fecha_pedido;
	}
	public void setFecha_pedido(String fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	public String getFecha_termina_camp() {
		return fecha_termina_camp;
	}
	public void setFecha_termina_camp(String fecha_termina_camp) {
		this.fecha_termina_camp = fecha_termina_camp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}