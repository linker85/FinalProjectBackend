package com.proximate.www.dashmate.model;

public class Configuracion {

	private String parametro;
	private String valor;
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		return "Configuracion [parametro=" + parametro + ", valor="
				+ valor + "]";
	}
	
	
}
