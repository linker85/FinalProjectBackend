package com.proximate.www.pushmate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.IPushTemporalDAO;
import com.proximate.www.pushmate.model.PushTemporal;

@Repository(value = "pushTemporalDAO")
public class PushTemporalDAOImpl extends BaseDAOImpl implements IPushTemporalDAO{

	
	public void insert(PushTemporal o) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(PushTemporal o) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(PushTemporal o) {
		// TODO Auto-generated method stub
		
	}

	
	public List<PushTemporal> select() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<PushTemporal> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public PushTemporal selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean guardar(PushTemporal pushTemporal) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(pushTemporal);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	public List<PushTemporal> getListaFromUser(int idUsuario) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from PushTemporal where idUsuario = ?", idUsuario);
	}

	
	@Transactional
	public int borrarFromUser(int idUsuario) {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("delete from push_temporal where id_usuario = :idTmp");
		query.setParameter("idTmp", idUsuario);
		return query.executeUpdate();
	}

}
