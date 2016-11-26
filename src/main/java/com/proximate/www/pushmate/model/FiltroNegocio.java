package com.proximate.www.pushmate.model;

import java.io.Serializable;

public class FiltroNegocio  implements Serializable{
	
	private String filtro;
	private String filtro1;
	private String filtro2;
	public String getFiltro() {
		return filtro;
	}
	public String getFiltro1() {
		return filtro1;
	}
	public String getFiltro2() {
		return filtro2;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public void setFiltro1(String filtro1) {
		this.filtro1 = filtro1;
	}
	public void setFiltro2(String filtro2) {
		this.filtro2 = filtro2;
	}
	@Override
	public String toString() {
		return "FiltroNegocio [filtro=" + filtro + ", filtro1=" + filtro1
				+ ", filtro2=" + filtro2 + "]";
	}
	
}
