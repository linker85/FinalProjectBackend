package com.proximate.www.dashmate.model;

public class Usuario {
	//Cambio el primary key ahora es id y no cveUsuario
	private int id;
	private String cveUsuario;
	private String password;
	private boolean activo;
	private Integer rolId;
	private String nombre;
	//Agregados solo para poder parsear desde el web
	private String perfil;
	private String comando;
	// end
	private boolean primeraVez;
	private String email; 
	//Preferencias del usuario
	private int prediasporcentajevistas;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCveUsuario() {
		return cveUsuario;
	}
	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getComando() {
		return comando;
	}
	
	public void setComando(String comando) {
		this.comando = comando;
	}

	public boolean getPrimeraVez() {
		return primeraVez;
	}
	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Usuario [id=" +id+ ", cveUsuario=" + cveUsuario + ", password=" + password
				+ ", activo=" + activo + ", id_rol=" + rolId + ", nombre="
				+ nombre + ", perfil=" + perfil + "]";
	}
	public int getPrediasporcentajevistas() {
		return prediasporcentajevistas;
	}
	public void setPrediasporcentajevistas(int prediasporcentajevistas) {
		this.prediasporcentajevistas = prediasporcentajevistas;
	}
	

	
	
}
