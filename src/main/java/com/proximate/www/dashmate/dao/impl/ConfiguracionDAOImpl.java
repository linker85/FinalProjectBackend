package com.proximate.www.dashmate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.IConfiguracionDAO;
import com.proximate.www.dashmate.model.Configuracion;

@Repository(value="ConfiguracionDAO")
public class ConfiguracionDAOImpl extends BaseDAOImpl implements IConfiguracionDAO {
		
	
	public String getNombreAplicacion() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'nameApp'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAnalyticsAplication() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.aplication'",null).get(0);
		System.out.println("getAnalyticsAplication: " + tmp);
		return tmp.getValor();		
	}
	
	public String getAnalyticsKey() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.key'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAnalyticsUser() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.user'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAnalyticsPwd() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.password'",null).get(0);
		return tmp.getValor();		
	}
	
	
	@Transactional
	public void insert(Configuracion o) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("insert into configuracion(parametro, valor) values (:parametro, :valor)");
		System.out.println(o.toString());
		Query   query   = session.createSQLQuery(sql.toString());
		query.setString("parametro", o.getParametro());
		query.setString("valor", o.getValor());
		query.executeUpdate();
	}
	
	
	@Transactional
	public void update(Configuracion o) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update configuracion set valor = :valor where parametro = :parametro");
		Query query1 = session.createSQLQuery(sql.toString());
		query1.setParameter("valor", o.getValor());
		query1.setParameter("parametro", o.getParametro());
		query1.executeUpdate();
	}
	
	
	public void delete(Configuracion o) {
		
		
	}
	
	public List<Configuracion> select() {
		
		return null;
	}
	
	public List<Configuracion> select(String criterio) {
		
		return getHibernateTemplate().find("from Configuracion where parametro = '"+criterio+"'");
	}
	
	public Configuracion selectById(int param) {
		
		return null;
	}
	
	@Transactional
	public void actualiza(String parametro, String valor) {
		
		List<Configuracion> ant = select(parametro);
		System.out.println("valor: " + valor + ", parametro: " + parametro);
		if (ant == null || ant.size() == 0 ) {
			try {
				System.out.println("Insert " + parametro +" " + valor);
				Configuracion co = new Configuracion();
				co.setParametro(parametro);
				co.setValor(valor);
				insert(co);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("update configuracion set valor = :valor where parametro = :parametro");
			System.out.println("update configuracion set valor = :valor where parametro = :parametro => valor: " + valor + ", parametro: " + parametro);
			Query query1 = session.createSQLQuery(sql.toString());
			query1.setParameter("valor", valor);
			query1.setParameter("parametro", parametro);
			query1.executeUpdate();
		}
	}
	
	public String getMysqlHost() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'mysql.host'",null).get(0);
		return tmp.getValor();		

	}
	
	public String getMysqlPort() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'mysql.port'",null).get(0);
		return tmp.getValor();		

	}
	
	public String getMysqlDatabase() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'mysql.database'",null).get(0);
		return tmp.getValor();		

	}
	
	public String getMysqlUser() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'mysql.user'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getMysqlPwd() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'mysql.pwd'",null).get(0);
		return tmp.getValor();
	}
	
	public String getLastVersionAndroid() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'android.version.last'",null).get(0);
		return tmp.getValor();		

	}
	
	public String getLastVersionIphone() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'iphone.version.last'",null).get(0);
		return tmp.getValor();		

	}
	
	public String getAnalyticsID() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.id'",null).get(0);
		System.out.println("getAnalyticsID: "+ tmp);
		return tmp.getValor();		
	}
	
	
	public String getAppannieApiKey() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.api_key'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAppannieGoogleAccount() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.google_account'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAppannieAppleAccount() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.ios_account'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAppannieGoogleID() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.google_id'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAppannieAppleID() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.apple_id'",null).get(0);
		return tmp.getValor();		
	}
	
	public Integer getAppannieInterval() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appannie.intervalo'",null).get(0);
		return Integer.parseInt(tmp.getValor());		
	}
	
	public String getAnalyticsBegin() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.fechaInicio'",null).get(0);
		return tmp.getValor();
	}
	
	public String getSmtpServerServ() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'smtp.socket.server'",null).get(0);
		return tmp.getValor();
	}
	
	public String getSmtpPortServ() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'smtp.socket.port'",null).get(0);
		return tmp.getValor();
	}
	
	public String getFechaPublicacion() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'publicacion.fecha'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getShowDescargas() throws NullPointerException {
		
		try{
			Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'app.showDescargas'",null).get(0);
			return (tmp.getValor().equals("")) ? "No" : tmp.getValor();
		}
		catch(Exception e){
			return "No";
		}
	}
	
	
	public String getAnalyticsP12() throws NullPointerException {
		
		try{
			Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'analytics.p12'",null).get(0);
			return (tmp.getValor().equals("")) ? "/assets/Dashmate-prod.p12" : tmp.getValor();
		}
		catch(Exception e){
			return "/assets/Dashmate-prod.p12";
		}
	}
	
	
	// MDM
	public String getRutaApk() throws NullPointerException {
		try {
			Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'apk.ruta'", null).get(0);
			return tmp.getValor();
		} catch(Exception e) {
			return "C:\\Oracle\\Middleware\\user_projects\\egoba\\apk";
		}
	}
	
}
