package com.proximate.www.dashmate.model;

public class UsuariosXDispositivo {
	private String dispositivo;
	private String deviceName;
	
	private int usuarios;
	public String getDispositivo() {
		return dispositivo;
	}
	public int getUsuarios() {
		return usuarios;
	}
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	public void setUsuarios(int usuarios) {
		this.usuarios = usuarios;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
	
}
