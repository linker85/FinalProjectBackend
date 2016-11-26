package com.proximate.www.dashmate.utils;

import java.io.Serializable;
import java.util.Arrays;

public class CorreoSerializable implements Serializable {
	
	private static final long serialVersionUID = 2757961554115959305L;

	private String titulo;
	private String[] destinatarios;
	private StringBuffer texto;
	private byte[] imagenLogo;
	
	public String[] getDestinatarios() {
		return destinatarios;
	}
	public StringBuffer getTexto() {
		return texto;
	}
	public void setDestinatarios(String[] destinatarios) {
		this.destinatarios = destinatarios;
	}
	public void setTexto(StringBuffer texto) {
		this.texto = texto;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public byte[] getImagenLogo() {
		return imagenLogo;
	}
	public void setImagenLogo(byte[] imagenLogo) {
		this.imagenLogo = imagenLogo;
	}
	public String toString() {
		return "CorreoSerializable [titulo=" + titulo + ", destinatarios="
				+ (destinatarios != null ? Arrays.asList(destinatarios) : null)
				+ ", texto=" + texto + "]";
	}
	
	
	
	
	
	
}
