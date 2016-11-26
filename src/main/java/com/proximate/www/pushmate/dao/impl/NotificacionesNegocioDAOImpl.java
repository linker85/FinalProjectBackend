package com.proximate.www.pushmate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.INotificacionesNegocioDAO;
import com.proximate.www.pushmate.model.Estatus;
import com.proximate.www.pushmate.model.NotificacionNegocio;
import com.proximate.www.pushmate.model.Token;

@Repository(value="notificacionesNegocioDAO")
public class NotificacionesNegocioDAOImpl extends BaseDAOImpl implements INotificacionesNegocioDAO{

	
	public void insert(NotificacionNegocio o) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(NotificacionNegocio o) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(NotificacionNegocio o) {
		// TODO Auto-generated method stub
		
	}

	
	public List<NotificacionNegocio> select() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<NotificacionNegocio> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public NotificacionNegocio selectById(int param) {
		// TODO Auto-generated method stub
		return (NotificacionNegocio) getHibernateTemplate().find("from NotificacionNegocio where id = ?", param).get(0);
	}

	
	public boolean guardar(NotificacionNegocio notificacionNegocio) {
		// TODO Auto-generated method stub
		try{
			getHibernateTemplate().save(notificacionNegocio);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	@Transactional
	public List<NotificacionNegocio> getListaPendientes() {
		// TODO Auto-generated method stub
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select n.* from notificaciones_negocio n, estatus e where n.id_estatus = e.id and n.fecha_programacion < now() and e.valor = 'pendiente'").addEntity("notificacion", NotificacionNegocio.class);
		return query.list();
	}
	
	
	public Estatus getEstatus(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return  (Estatus) getHibernateTemplate().find("from Estatus where valor = ?" , nombre).get(0);
	}
	
	
	public boolean actualizarEnviado(int id, Timestamp fecha) {
		// TODO Auto-generated method stub
		try{
			NotificacionNegocio n = selectById(id);
			n.setEstatusId(getEstatus("enviado").getId());
			n.setFechaEnvio(fecha);
			getHibernateTemplate().update(n);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean actualizarError(int id, Timestamp fecha) {
		// TODO Auto-generated method stub
		try{
			NotificacionNegocio n = selectById(id);
			n.setEstatusId(getEstatus("error").getId());
			n.setFechaEnvio(fecha);
			getHibernateTemplate().update(n);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	@Transactional
	public int depurarRegistros() {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("delete from notificaciones_negocio where id_estatus <> 1 and fecha_envio < DATE_SUB(now(), INTERVAL 6 DAY)");
		return query.executeUpdate();
	}

	
	@Transactional
	public List<NotificacionNegocio> getListaByMatricula(String matricula) {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("select * from notificaciones_negocio where id_estatus <> 1 and fecha_envio is not null and filtro = :filtro order by fecha_envio desc").addEntity("notificaciones_negocio", NotificacionNegocio.class);
		query.setParameter("filtro", matricula);
		return query.list();
	}	
	
	
	public boolean existFiltroVSToken(String token, String filtro) {
		// TODO Auto-generated method stub
		List<Token> list = getHibernateTemplate().find("from Token where filtro = '"+filtro+"' and token = ?", token);
		if(list.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Token existFiltroVSToken2(String token, String filtro) {
		List<Token> list = getHibernateTemplate().find("from Token where filtro = '"+filtro+"' and token = ?", token);
		if(list.isEmpty()){
			return null;
		} else {
			return list.get(0);
		}
	}		

}