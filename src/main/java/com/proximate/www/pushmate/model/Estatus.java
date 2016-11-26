package com.proximate.www.pushmate.model;

import java.io.Serializable;

public class Estatus  implements Serializable{
	private int id;
	private String valor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
