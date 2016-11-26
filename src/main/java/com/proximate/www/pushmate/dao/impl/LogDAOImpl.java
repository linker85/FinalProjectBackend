package com.proximate.www.pushmate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.ILogDAO;
import com.proximate.www.pushmate.model.Log;
import com.proximate.www.pushmate.model.LogCatalog;

@Repository(value="logDAO")
public class LogDAOImpl extends BaseDAOImpl implements ILogDAO{

	
	public void insert(Log o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(o);
	}

	
	public void update(Log o) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(Log o) {
		// TODO Auto-generated method stub
		
	}

	
	public List<Log> select() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Log> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Log selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Transactional
	public int getTipo(String nombre) throws Exception{
		try{
			LogCatalog catalog = (LogCatalog) getHibernateTemplate().find("from LogCatalog where nombre = ?", nombre).get(0);
			return catalog.getId();
		}
		catch(Exception e){
			Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("insert into log_catalog(nombre) values (:nombre) ");
			Query query = sesion.createSQLQuery(sql.toString());
			query.setString("nombre", nombre);
			if(query.executeUpdate() == 1){
				return getTipo(nombre);
			}
			else{
				return 0;
			}
		}		
	}

	
	@Transactional
	public boolean guardar(String tipo, String descripcion, Timestamp fecha) {
		try{
			// TODO Auto-generated method stub
			int idTipo = getTipo(tipo);
			Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("insert into log(catalog_id, DESCRIPCION, fecha) values (:tipo, :descripcion, :fecha) ");
			Query query = sesion.createSQLQuery(sql.toString());
			query.setInteger("tipo", idTipo);
			query.setString("descripcion", descripcion);
			query.setTimestamp("fecha", fecha);
			if(query.executeUpdate()  == 1){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
	}

	
	public List<Log> getLogs(String fechaInicial, String fechaFinal, String where)
			throws Exception {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Log where DATE(fecha) between ('"+fechaInicial+"') and ('"+fechaFinal+"')".concat((where != null) ? where : ""));
	}

	
	public List<LogCatalog> getAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from LogCatalog");
	}

}
