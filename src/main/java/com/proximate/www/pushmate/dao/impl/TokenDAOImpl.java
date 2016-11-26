package com.proximate.www.pushmate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.aws.BatchCreatePlatformEndpointSample;
import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.ICanalesAppcelerator;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.ITokensDAO;
import com.proximate.www.pushmate.model.CanalesAppcelerator;
import com.proximate.www.pushmate.model.CanalesTopics;
import com.proximate.www.pushmate.model.FiltroNegocio;
import com.proximate.www.pushmate.model.Token;

@Repository(value="tokensDAO")
public class TokenDAOImpl extends BaseDAOImpl implements ITokensDAO {

	@Autowired
	private ICanalesAppcelerator canales;
	@Autowired
	private IConfiguracionDAO2 configuracion;
	
	public void insert(Token o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(o);
	}

	
	public void update(Token o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(o);
	}

	
	public void delete(Token o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(o);
	}

	
	@SuppressWarnings("unchecked")
	public List<Token> select() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Token");
	}

	
	@SuppressWarnings("unchecked")
	public List<Token> select(String criterio) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Token");
	}

	
	public Token selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean guardar(Token token) {
		//Primero validar que no existe
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try {
			String secret = configuracion.getSecretKeyAWS();
			String access = configuracion.getAccessKeyAWS();
			
			List<Token> lista = getHibernateTemplate().find("from Token where filtro = ?", token.getFiltro());
			BatchCreatePlatformEndpointSample bps = new BatchCreatePlatformEndpointSample();
			if (!lista.isEmpty()) {
				// Borro los tokens x filtro y desinscribo por filtro			
				for (Token t : lista) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}			
					//Alguien ya tenia este token debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where filtro = :filtro");
						querydel.setParameter("filtro", token.getFiltro());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
			//Vamos a ver si ya existe el token que nos mandan, seguramente entro otra matricula en ese dispositivo
			List<Token> listaTok = getHibernateTemplate().find("from Token where token = ?", token.getToken());
			if (listaTok != null && !listaTok.isEmpty()) { // Existe token
				for (Token t : listaTok) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}
					//Alguien ya tenia este token debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where token = :token");
						querydel.setParameter("token", token.getToken());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}				
					
			// Busco tokens por mac: borro y desinscribo
			List<Token> listaMac = getHibernateTemplate().find("from Token where mac = ?", token.getMac());
			if (listaMac != null && !listaMac.isEmpty()) { // Existe mac
				for (Token t : listaTok) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}
					//Alguien ya tenia esta mac debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where mac = :mac");
						querydel.setParameter("mac", token.getMac());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}			
			
			// Obtener topics a los que debo inscribirme
			List<CanalesAppcelerator> listActivos = canales.selectActivosAppId(token.getId_aplicacion());
			// Inscribir aws
			boolean exito = false;
			if (listActivos != null && !listActivos.isEmpty()) {
				for (CanalesAppcelerator c : listActivos) {
					CanalesTopics a = null;
					try {
						a = canales.selectTopicxAppce(c.getNombre(), token.getPlataforma(), token.getId_aplicacion());
					} catch (Exception e) {
						e.printStackTrace();
					}
					Token aws = null;
					if (a != null && a.getApp_arn() != null && a.getTopic_arn() != null && a.getId() == 1) {
						// Inscribir String token, String topicARN, String appARN
						System.out.printf("Inscribir: token:" + token.getToken() + ", topic: "  + a.getTopic_arn() + ", app: " + a.getApp_arn());
						aws = bps.suscribir(token.getToken(), a.getTopic_arn(), a.getApp_arn(), access, secret);
						
						System.out.println("Insertando token");
						StringBuilder sql = new StringBuilder("insert into tokens (filtro, token, fecha_acceso, plataforma, arn_topic, arn_suscription, arn_app, arn_endpoint, id_aplicacion, id_canal, mac) values (:filtro, :token, NOW(), :plataforma, :arn_topic, :arn_suscription, :arn_app, :arn_endpoint, :id_aplicacion, :id_canal, :mac)");
						Query query = sesion.createSQLQuery(sql.toString());
						query.setParameter("filtro", token.getFiltro());
						query.setParameter("token", token.getToken());
						query.setParameter("plataforma", token.getPlataforma());
						query.setParameter("arn_topic", aws.getArn_topic());
						query.setParameter("arn_suscription", aws.getArn_suscription());
						query.setParameter("arn_app", a.getApp_arn());
						query.setParameter("arn_endpoint", aws.getArn_endpoint());
						query.setParameter("id_aplicacion", token.getId_aplicacion());
						query.setParameter("id_canal", c.getId());
						query.setParameter("mac", token.getMac());
						if (query.executeUpdate() >= 1) {
							exito = true;
						} else {
							exito = false;
						}						
					}
				}
			}
			return exito;			
		} catch(Exception e) {
			e.printStackTrace();
			return false;	
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean deslogear(Token token) {
		//Primero validar que no existe
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try {
			String secret = configuracion.getSecretKeyAWS();
			String access = configuracion.getAccessKeyAWS();
			
			List<Token> lista = getHibernateTemplate().find("from Token where filtro = ?", token.getFiltro());
			BatchCreatePlatformEndpointSample bps = new BatchCreatePlatformEndpointSample();
			if (!lista.isEmpty()) {
				// Borro los tokens x filtro y desinscribo por filtro			
				for (Token t : lista) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}			
					//Alguien ya tenia este token debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where filtro = :filtro");
						querydel.setParameter("filtro", token.getFiltro());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
			//Vamos a ver si ya existe el token que nos mandan, seguramente entro otra matricula en ese dispositivo
			List<Token> listaTok = getHibernateTemplate().find("from Token where token = ?", token.getToken());
			if (listaTok != null && !listaTok.isEmpty()) { // Existe token
				for (Token t : listaTok) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}
					//Alguien ya tenia este token debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where token = :token");
						querydel.setParameter("token", token.getToken());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}				
					
			// Busco tokens por mac: borro y desinscribo
			List<Token> listaMac = getHibernateTemplate().find("from Token where mac = ?", token.getMac());
			if (listaMac != null && !listaMac.isEmpty()) { // Existe mac
				for (Token t : listaTok) {
					if (t.getArn_suscription() != null && t.getArn_topic() != null && t.getArn_app() != null) {
						// Desinscribir aws: String token, String suscriptionArn, String endpointArn
						//String suscriptionArn, String endpointArn, String appAr
						bps.desinscribir(t.getArn_suscription(), t.getArn_endpoint(), t.getArn_app(), access, secret);	
					}
					//Alguien ya tenia esta mac debemos eliminar sus datos de negocio
					try {
						Transaction trans2 = sesion.beginTransaction();
						trans2.begin();
						Query querydel = sesion.createSQLQuery("delete from tokens where mac = :mac");
						querydel.setParameter("mac", token.getMac());
						querydel.executeUpdate();
						if (trans2.wasCommitted()){
							trans2.commit();
						}
						sesion.flush();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			// Inscribir canal no registrados
			// Obtener topics a los que debo inscribirme
			List<CanalesAppcelerator> listActivos = canales.selectActivosAppId(token.getId_aplicacion());
			// Inscribir aws
			boolean exito = false;
			if (listActivos != null && !listActivos.isEmpty()) {		
				for (CanalesAppcelerator c : listActivos) {
					CanalesTopics a = null;
					try {
						a = canales.selectTopicxAppce(c.getNombre(), token.getPlataforma(), token.getId_aplicacion());
					} catch (Exception e) {
						e.printStackTrace();
					}
					Token aws = null;
					if (a != null && a.getApp_arn() != null && a.getTopic_arn() != null && a.getId() == 2) {
						// Inscribir String token, String topicARN, String appARN
						System.out.printf("Inscribir: token:" + token.getToken() + ", topic: "  + a.getTopic_arn() + ", app: " + a.getApp_arn());
						aws = bps.suscribir(token.getToken(), a.getTopic_arn(), a.getApp_arn(), access, secret);
						
						System.out.println("Insertando token");
						StringBuilder sql = new StringBuilder("insert into tokens (filtro, token, fecha_acceso, plataforma, arn_topic, arn_suscription, arn_app, arn_endpoint, id_aplicacion, id_canal, mac) values (:filtro, :token, NOW(), :plataforma, :arn_topic, :arn_suscription, :arn_app, :arn_endpoint, :id_aplicacion, :id_canal, :mac)");
						Query query = sesion.createSQLQuery(sql.toString());
						query.setParameter("filtro", token.getFiltro());
						query.setParameter("token", token.getToken());
						query.setParameter("plataforma", token.getPlataforma());
						query.setParameter("arn_topic", aws.getArn_topic());
						query.setParameter("arn_suscription", aws.getArn_suscription());
						query.setParameter("arn_app", a.getApp_arn());
						query.setParameter("arn_endpoint", aws.getArn_endpoint());
						query.setParameter("id_aplicacion", token.getId_aplicacion());
						query.setParameter("id_canal", c.getId());
						query.setParameter("mac", token.getMac());
						if (query.executeUpdate() >= 1) {
							exito = true;
						} else {
							exito = false;
						}						
					}
				}
			}
			return exito;
		} catch(Exception e) {
			e.printStackTrace();
			return false;	
		}
	}		

	@Transactional
	public boolean existeFiltro(String filtro) {
		List<Token> lista = getHibernateTemplate().find("from Token where filtro = ?", filtro);
		return !lista.isEmpty();
	}
	
	@Transactional
	public Token existeFiltroToken(String filtro) {
		List<Token> lista = getHibernateTemplate().find("from Token where filtro = ?", filtro);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return new Token();
		}
	}	

	@Transactional
	public List<Token> getListTokenFiltroN(String valor) {
		// TODO Auto-generated method stub
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select tok.* from filtro_negocio fil, tokens tok where tok.filtro = fil.filtro and fil.filtro = ?").addEntity("tokens", Token.class);
		query.setParameter(0, valor);
		return query.list();
	}	
	
	@Transactional
	public List<Token> getListTokenFiltro1(String valor) {
		// TODO Auto-generated method stub
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select tok.* from filtro_negocio fil, tokens tok where tok.filtro = fil.filtro and filtro_1 = ?").addEntity("tokens", Token.class);
		query.setParameter(0, valor);
		return query.list();
	}

	
	@Transactional
	public List<Token> getListTokenFiltro2(String valor) {
		// TODO Auto-generated method stub
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select tok.* from filtro_negocio fil, tokens tok where tok.filtro = fil.filtro and filtro_2 = ?").addEntity("tokens", Token.class);
		query.setParameter(0, valor);
		return query.list();
	}

	
	public Token getToken(String filtro) throws NullPointerException{
		// TODO Auto-generated method stub
		
		List<Token> list = getHibernateTemplate().find("from Token where filtro = ?", filtro);
		if(list.isEmpty()){
			return null;
		}
		else{
			return list.get(0);	
		}
	}

	
	@Transactional
	public boolean borrarNegocioByFiltro(String filtro) {
		// TODO Auto-generated method stub
		try{
			List<FiltroNegocio> lista = getHibernateTemplate().find("from FiltroNegocio where filtro = ?", filtro);
			Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
			for(FiltroNegocio fil : lista){
				try{
					Transaction trans = sesion.beginTransaction();
					trans.begin();
					sesion.delete(fil);
					if(trans.wasCommitted()){
						trans.commit();
					}
					sesion.flush();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
