package com.proximate.www.pushmate.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.IFiltrosNegocio;
import com.proximate.www.pushmate.model.FiltroNegocio;

@Repository(value = "filtrosNegocio")
public class FiltrosNegocioDAOImpl extends BaseDAOImpl implements IFiltrosNegocio{

	
	public void insert(FiltroNegocio o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(o);
	}

	
	public void update(FiltroNegocio o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(o);
	}

	
	public void delete(FiltroNegocio o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(o);
	}

	
	public List<FiltroNegocio> select() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from FiltroNegocio");
	}

	
	public List<FiltroNegocio> select(String criterio) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from FiltroNegocio");
	}

	
	public FiltroNegocio selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Transactional
	public HashMap<String, Integer> getCatalogo1() throws Exception {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Integer> mapa = new LinkedHashMap<String, Integer>();
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			Query query = sesion.createSQLQuery("select count(filtro) as total, filtro_1 from filtro_negocio group by filtro_1");
			List<?> lista = query.list();
			for(int i = 0; i < lista.size(); i++){
				Object[] obj = (Object[]) lista.get(i);
				mapa.put(String.valueOf(obj[1]), Integer.parseInt(obj[0].toString()));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return mapa;
		}
	}

	
	@Transactional
	public HashMap<String, Integer> getCatalogo2() throws Exception {
		// TODO Auto-generated method stub
//		return (ArrayList<String>) getHibernateTemplate().find("select distinct filtro2 from FiltroNegocio");
		LinkedHashMap<String, Integer> mapa = new LinkedHashMap<String, Integer>();
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			Query query = sesion.createSQLQuery("select count(filtro) as total, filtro_2 from filtro_negocio group by filtro_2");
			List<?> lista = query.list();
			for(int i = 0; i < lista.size(); i++){
				Object[] obj = (Object[]) lista.get(i);
				mapa.put(String.valueOf(obj[1]), Integer.parseInt(obj[0].toString()));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return mapa;
		}
	}

	
	@Transactional
	public boolean guardar(FiltroNegocio filtros) throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("insert into filtro_negocio(filtro, filtro_1, filtro_2) values (:filtro, :filtro1, :filtro2)");
		query.setParameter("filtro", filtros.getFiltro());
		query.setParameter("filtro1", filtros.getFiltro1());
		query.setParameter("filtro2", filtros.getFiltro2());
		if(query.executeUpdate() >= 1){
			return true;
		}
		else{
			return false;
		}
	}

	
	@Transactional
	public int limpiar(String filtro) throws Exception {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("delete from filtro_negocio where filtro = :filtro");
		query.setParameter("filtro", filtro);
		return query.executeUpdate();
	}

}
