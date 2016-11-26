package com.proximate.www.pushmate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.ICanalesAppcelerator;
import com.proximate.www.pushmate.model.CanalesAppcelerator;
import com.proximate.www.pushmate.model.CanalesTopics;

@Repository(value = "canalesAppcelerator")
public class CanalesAppceleratorDAOImpl extends BaseDAOImpl implements ICanalesAppcelerator {

	
	public void insert(CanalesAppcelerator o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(o);
	}

	
	public void update(CanalesAppcelerator o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(o);
	}

	
	public void delete(CanalesAppcelerator o) {
		// TODO Auto-generated method stub
		o.setActivo(false);
		update(o);
	}

	
	public List<CanalesAppcelerator> select() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CanalesAppcelerator");
	}
	
	
	public List<CanalesAppcelerator> selectActivos() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CanalesAppcelerator where activo = 1");
	}
	
	public List<CanalesAppcelerator> selectActivosAppId(int idApp) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CanalesAppcelerator where activo = 1 AND id_aplicacion=?", idApp);
	}	
	
	@SuppressWarnings("unchecked")
	public CanalesTopics selectTopicxAppce(String canal, String plataforma, int idApp) {
		List<CanalesTopics> lista = getHibernateTemplate().find("from CanalesTopics where activo = 1 AND nombre=? AND plataforma=? AND id_aplicacion=? ", canal, plataforma, idApp);
		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}	
	
	public List<CanalesAppcelerator> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CanalesAppcelerator selectById(int param) {
		// TODO Auto-generated method stub
		return (CanalesAppcelerator) getHibernateTemplate().find("from CanalesAppcelerator where id = ?", param).get(0);
	}
	
	public CanalesAppcelerator selectByNombre(String param) {
		// TODO Auto-generated method stub
		return (CanalesAppcelerator) getHibernateTemplate().find("from CanalesAppcelerator where activo = 1 AND nombre = ?", param).get(0);
	}	

	
	@Transactional
	public void truncate() {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("truncate table canales_appcelerator");
		Query query1 = sesion.createSQLQuery(sql.toString());
		query1.executeUpdate();
	}

	
	@Transactional
	public void guardar(String nombre) throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("insert into canales_appcelerator(nombre, activo) values (:nombre, 1)");
		Query query1 = sesion.createSQLQuery(sql.toString());
		query1.setString("nombre", nombre);
		query1.executeUpdate();
	}
}
