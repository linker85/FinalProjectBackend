package com.proximate.www.pushmate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.IEstatusDAO;
import com.proximate.www.pushmate.model.Estatus;

@Repository(value="estatusDAO")
public class EstatusDAOImpl extends BaseDAOImpl implements IEstatusDAO{

	
	public void insert(Estatus o) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Estatus o) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(Estatus o) {
		// TODO Auto-generated method stub
		
	}

	
	public List<Estatus> select() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Estatus");
	}

	
	public List<Estatus> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Estatus selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

}
