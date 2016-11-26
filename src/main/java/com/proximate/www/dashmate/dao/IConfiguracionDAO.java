package com.proximate.www.dashmate.dao;

import com.proximate.www.dashmate.model.Configuracion;

public interface IConfiguracionDAO extends IBaseDao<Configuracion>{
	
	public String getNombreAplicacion() throws NullPointerException;
	public String getAnalyticsAplication() throws NullPointerException;
	public String getAnalyticsKey() throws NullPointerException;
	public String getAnalyticsID() throws NullPointerException;
	public String getAnalyticsUser() throws NullPointerException;
	public String getAnalyticsPwd() throws NullPointerException;
	public String getAnalyticsP12() throws NullPointerException;
	

	public String getMysqlHost() throws NullPointerException;
	public String getMysqlPort() throws NullPointerException;
	public String getMysqlDatabase() throws NullPointerException;
	public String getMysqlUser() throws NullPointerException;
	public String getMysqlPwd() throws NullPointerException;
	
	public String getLastVersionAndroid() throws NullPointerException;
	public String getLastVersionIphone() throws NullPointerException;	
	public void actualiza(String parametro, String valor);
	
	public Integer getAppannieInterval() throws NullPointerException;
	public String getAppannieApiKey() throws NullPointerException;
	public String getAppannieGoogleAccount() throws NullPointerException;
	public String getAppannieAppleAccount() throws NullPointerException;
	public String getAppannieGoogleID() throws NullPointerException;
	public String getAppannieAppleID() throws NullPointerException;

	public String getAnalyticsBegin() throws NullPointerException;
	
	public String getSmtpServerServ() throws NullPointerException;
	public String getSmtpPortServ() throws NullPointerException;
	
	public String getFechaPublicacion() throws NullPointerException;
	
	public String getShowDescargas() throws NullPointerException;

	// MDM
	public String getRutaApk() throws NullPointerException;
	
}
