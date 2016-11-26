package com.proximate.www.pushmate.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.IUsuarioDAO;
import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.dashmate.model.Configuracion;
import com.proximate.www.dashmate.model.Usuario;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;

@Repository(value= "configuracionDAO2")
public class ConfiguracionDAOImpl2 extends BaseDAOImpl implements IConfiguracionDAO2 {
		
	
	public String getNombreAplicacion() throws NullPointerException{
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'nameApp'",null).get(0);
		return tmp.getValor();		
	}	
	
	public String getAppceleratorApiKey() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.API_KEY'",null).get(0);
		return tmp.getValor();		
	}

	
	public String getAppceleratorUser() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.user'",null).get(0);
		return tmp.getValor();		
	}

	
	public String getAppceleratorPassword() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.pwd'",null).get(0);
		return tmp.getValor();		
	}
	
	
	public void insert(Configuracion o) {
	
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
		
		return null;
	}
	
	
	public Configuracion selectById(int param) {
		
		return null;
	}
	
	
	@Transactional
	public void actualiza(String parametro, String valor) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update configuracion set valor = :valor where parametro = :parametro");
		Query query1 = session.createSQLQuery(sql.toString());
		query1.setParameter("valor", valor);
		query1.setParameter("parametro", parametro);
		query1.executeUpdate();
	}
	
	
	public String getAppceleratorApiKeyIos() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.API_KEY_ios'",null).get(0);
		return tmp.getValor();		
	}

	
	public String getAppceleratorUserIos() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.user_ios'",null).get(0);
		return tmp.getValor();		
	}

	
	public String getAppceleratorPasswordIos() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'appCelerator.pwd_ios'",null).get(0);
		return tmp.getValor();		
	}
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	public int getPushmateUserId() {		
		Configuracion tmp = null;
		try {
			tmp = (Configuracion) getHibernateTemplate().find(
					"from Configuracion where parametro = 'puhmate.usuario'", 
					null).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tmp != null && tmp.getValor() != null) {
			Usuario usuario = usuarioDAO.selectByUser(tmp.getValor());
			if (usuario != null) {
				return usuario.getId();
			} else {
				// Si no existe el usuario; regreso al admin			
				return 1;
			}
		} else {
			// Si no existe el usuario; regreso al admin
			return 1;
		}		
	}	
	
	public String getAccessKeyAWS() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'AWS.ACCESS_KEY'",null).get(0);
		return tmp.getValor();		
	}

	
	public String getSecretKeyAWS() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'AWS.SECRET_KEY'",null).get(0);
		return tmp.getValor();		
	}
	
	@Deprecated
	public String getTopicARNAWS() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'AWS.ARN'",null).get(0);
		return tmp.getValor();		
	}	
	
	public String getIosARNAWS() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'AWS.ARN.IOS'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getAndroidARNAWS() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'AWS.ARN.ANDROID'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getLogoCorreoHead() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'LOGO_SAM'",null).get(0);
		return tmp.getValor();		
	}
	
	public String getLogoCorreoBottom() throws NullPointerException {
		
		Configuracion tmp = (Configuracion) getHibernateTemplate().find("from Configuracion where parametro = 'LOGO_PROXIMATE'",null).get(0);
		return tmp.getValor();		
	}

	
	@Transactional
	public String obtenerTemplatCorreo(int tipo) {
		Session session = getHibernateTemplateJV().getSessionFactory()
				.getCurrentSession();
		try {
			StringBuilder sql = new StringBuilder("SELECT body FROM templates_correos WHERE tipo = :tipo");
	
			Query query = session.createSQLQuery(sql.toString()).addScalar("body", Hibernate.STRING);
			query.setParameter("tipo", tipo);
			return (query.uniqueResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}