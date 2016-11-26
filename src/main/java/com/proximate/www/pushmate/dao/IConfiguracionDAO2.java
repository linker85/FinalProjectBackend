package com.proximate.www.pushmate.dao;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.dashmate.model.Configuracion;

public interface IConfiguracionDAO2 extends IBaseDao<Configuracion> {
	
	public String getNombreAplicacion() throws NullPointerException;
	
	public String getAppceleratorApiKey() throws NullPointerException;
	public String getAppceleratorUser() throws NullPointerException;
	public String getAppceleratorPassword() throws NullPointerException;
	
	public String getAppceleratorApiKeyIos() throws NullPointerException;
	public String getAppceleratorUserIos() throws NullPointerException;
	public String getAppceleratorPasswordIos() throws NullPointerException;

	public void actualiza(String parametro, String valor);
	
	// Nuevo
	int getPushmateUserId();
	
	String getAccessKeyAWS() throws NullPointerException;
	String getSecretKeyAWS() throws NullPointerException;
	String getTopicARNAWS() throws NullPointerException;
	String getIosARNAWS() throws NullPointerException;
	String getAndroidARNAWS() throws NullPointerException;

	// Obtener templates correos
	String obtenerTemplatCorreo(int tipo);
	String getLogoCorreoHead() throws NullPointerException;
	String getLogoCorreoBottom() throws NullPointerException;
}