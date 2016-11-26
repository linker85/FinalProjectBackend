package com.proximate.www.pushmate.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.pushmate.dao.INotificacionesDAO;
import com.proximate.www.pushmate.model.Estatus;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.model.NotificacionPojo;
import com.proximate.www.pushmate.model.TipoNotificacion;
import com.proximate.www.pushmate.model.TipoPlataforma;

@Repository(value = "notificacionesDAO")
public class NotificacionesDAOImpl extends BaseDAOImpl implements INotificacionesDAO {
	
	private static final Logger logger = Logger.getLogger(NotificacionesDAOImpl.class.getName());

	public void insert(Notificacion o) {

		getHibernateTemplate().save(o);
	}

	public boolean actualizar(Notificacion notificacion) {

		try {
			Notificacion n = getById(notificacion.getId());
			if (notificacion.getFechaCreacion() == null) {
				notificacion.setFechaCreacion(n.getFechaCreacion());
			}
			if (notificacion.getFechaEnvio() == null) {
				notificacion.setFechaEnvio(n.getFechaEnvio());
			}
			if (notificacion.getFechaProgramacion() == null) {
				notificacion.setFechaProgramacion(n.getFechaProgramacion());
			}
			getHibernateTemplate().update(notificacion);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardar(Notificacion notificacion) {
		try {
			logger.info("DAO: " + notificacion.toString());
			getHibernateTemplate().save(notificacion);
			return true;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return false;
		}
	}

	public void update(Notificacion o) {

		try {
			getHibernateTemplate().update(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Notificacion o) {

	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> select() {

		return getHibernateTemplate().find("from Notificacion");
	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> select(String criterio) {

		return getHibernateTemplate().find("from Notificacion");
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Notificacion selectById(int id) {

		Query empQuery = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createQuery("from Notificacion where id = :id ");
		empQuery.setInteger("id", id);
		List<Notificacion> employees = (List<Notificacion>) empQuery.list();
		for (Notificacion emp : employees) {
			Hibernate.initialize(emp.getEstatusId());
			Hibernate.initialize(emp.getTipoID());
		}
		return employees.get(0);
	}

	public Notificacion getById(int id) {
		return (Notificacion) getHibernateTemplate().find("from Notificacion where id = ? ", id).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> getNotificaciones() throws Exception {

		return getHibernateTemplate().find("from Notificacion");
	}

	@SuppressWarnings("unchecked")
	public List<NotificacionPojo> getNotificacionesReport() throws Exception {
		return getHibernateTemplate().find("from NotificacionPojo");
	}

	@SuppressWarnings("unchecked")
	public List<TipoNotificacion> getListaTipos() throws Exception {

		return getHibernateTemplate().find("from TipoNotificacion");
	}

	@SuppressWarnings("unchecked")
	public List<TipoPlataforma> getLstaPlataformas() throws Exception {

		return getHibernateTemplate().find("from TipoPlataforma");
	}

	public Estatus getEstatus(String nombre) throws Exception {

		return (Estatus) getHibernateTemplate().find("from Estatus where valor = ?", nombre).get(0);
	}

	public TipoPlataforma getPlataforma(int id) throws Exception {

		return (TipoPlataforma) getHibernateTemplate().find("from TipoPlataforma where id = ?", id).get(0);
	}

	@SuppressWarnings("unchecked")
	public TipoNotificacion getTipo(int id) throws Exception {
		try {
			List<TipoNotificacion> list = getHibernateTemplate().find("from TipoNotificacion");
			System.out.println("tam: " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (TipoNotificacion) getHibernateTemplate().find("from TipoNotificacion where id = ?", id).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<NotificacionPojo> getNotificacionesReport(String fechaInicial, String fechaFinal) throws Exception {

		return getHibernateTemplate().find("from NotificacionPojo where DATE(fecha_creacion) between ('" + fechaInicial
				+ "') and ('" + fechaFinal + "')");
	}

	@SuppressWarnings("unchecked")
	public List<NotificacionPojo> getNotificacionesReport(String fechaInicial, String fechaFinal, String where)
			throws Exception {

		return getHibernateTemplate().find("from NotificacionPojo where DATE(fecha_creacion) between ('" + fechaInicial
				+ "') and ('" + fechaFinal + "')".concat(" ").concat(where));
	}

	@SuppressWarnings("unchecked")
	public List<NotificacionPojo> getNotificacionesReportByProgramacion(String fechaInicial, String fechaFinal)
			throws Exception {

		return getHibernateTemplate().find("from NotificacionPojo where DATE(fecha_programacion) between ('"
				+ fechaInicial + "') and ('" + fechaFinal + "')");
	}

	@SuppressWarnings("unchecked")
	public List<NotificacionPojo> getNotificacionesReportByProgramacion(String fechaInicial, String fechaFinal,
			String where) throws Exception {

		return getHibernateTemplate().find("from NotificacionPojo where DATE(fecha_programacion) between ('"
				+ fechaInicial + "') and ('" + fechaFinal + "')".concat(" ").concat(where));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Notificacion> getNotificacionesPendientes() throws Exception {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(
						"select n.* from notificaciones n, estatus e where n.enProceso=0 AND n.id_estatus = e.id and n.fecha_programacion < now() and e.valor = 'pendiente_prueba' and n.avanzada = 0")
				.addEntity("notificacion", Notificacion.class);
		
		List<Notificacion> salida = query.list();
		
		if (salida != null && !salida.isEmpty()) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	        Query query1 = session.
	                createSQLQuery("UPDATE notificaciones SET enProceso = 1 WHERE enProceso=0 AND id_estatus = 5 and fecha_programacion < now() and avanzada = 0");
	        query1.executeUpdate();		
		}
		return salida;
	}

	public boolean actualizarEnviado(int id, Timestamp fecha) {

		try {
			Notificacion n = getById(id);
			n.setEstatusId(getEstatus("enviado"));
			n.setFechaEnvio(fecha);
			getHibernateTemplate().update(n);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<NotificacionPojo> buscar(int id, String texto) throws Exception {

		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery("select * from reporte1 where id=? or titulo like ? or cuerpo like ? ")
				.addEntity("notificacion", NotificacionPojo.class);
		query.setParameter(0, id);
		query.setParameter(1, "%" + texto + "%");
		query.setParameter(2, "%" + texto + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<NotificacionPojo> buscar(int id, String texto, String where) throws Exception {

		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(
						"select * from reporte1 where (token is null AND filtro is null) AND (id=? or (titulo like ? or cuerpo like ? )"
								.concat(" ").concat(where + ")"))
				.addEntity("notificacion", NotificacionPojo.class);
		query.setParameter(0, id);
		query.setParameter(1, "%" + texto + "%");
		query.setParameter(2, "%" + texto + "%");
		return query.list();
	}

	public boolean actualizarCancelado(int id, Timestamp fecha) {

		try {
			Notificacion n = getById(id);
			n.setEstatusId(getEstatus("cancelado"));
			n.setFechaEnvio(fecha);
			getHibernateTemplate().update(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean actualizarError(int id, Timestamp fecha) {

		try {
			Notificacion n = getById(id);
			n.setEstatusId(getEstatus("error"));
			n.setFechaEnvio(fecha);
			getHibernateTemplate().update(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Integer getCountPendientes() throws Exception {

		Integer total = 0;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder(
				"select count(n.id) as total from notificaciones n, estatus e where n.id_estatus = e.id and e.valor = 'pendiente'");
		Query query1 = session.createSQLQuery(sql.toString());
		List<Object> listObject = query1.list();
		if (listObject != null && !listObject.isEmpty()) {
			for (Object object : listObject) {
				total = Integer.parseInt(object.toString());
				break;
			}
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Integer getCountEnviado() throws Exception {

		Integer total = 0;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder(
				"select count(n.id) as total from notificaciones n, estatus e where n.id_estatus = e.id and e.valor = 'enviado'");
		Query query1 = session.createSQLQuery(sql.toString());
		List<Object> listObject = query1.list();
		if (listObject != null && !listObject.isEmpty()) {
			for (Object object : listObject) {
				total = Integer.parseInt(object.toString());
				break;
			}
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Integer getCountCancelado() throws Exception {

		Integer total = 0;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder(
				"select count(n.id) as total from notificaciones n, estatus e where n.id_estatus = e.id and e.valor = 'cancelado'");
		Query query1 = session.createSQLQuery(sql.toString());
		List<Object> listObject = query1.list();
		if (listObject != null && !listObject.isEmpty()) {
			for (Object object : listObject) {
				total = Integer.parseInt(object.toString());
				break;
			}
		}
		return total;
	}

	
	 @SuppressWarnings("unchecked")
	 @Transactional
	 public List<NotificacionPojo> getUltimosNoAvanzados(String estado, String canal, int
			 days, int limit, int android, int ios) throws Exception {
		 Session sesion = getHibernateTemplate().
				 getSessionFactory().getCurrentSession();
		 //Select
		 StringBuilder sql = new StringBuilder(
				 "SELECT n.* FROM reporte1 n WHERE n.fecha_programacion < NOW() AND n.fecha_programacion >= DATE_SUB(NOW(), INTERVAL :days DAY) AND n.estatus = :estado ");
		 //Where
		 if (canal != null && !canal.equals("")) {
			 sql.append(" AND (n.canal = :canal  OR n.canal = '') ");
		 }
		 
		 if (android == 1) {
			sql.append(" AND n.android = 1 ");
		 }
		 if (ios == 1) {
			sql.append(" AND n.ios = 1 ");
		 }
		 
		 sql.append(" AND n.avanzada = 0");
		 
		 //Order
		 sql.append(" ORDER BY n.fecha_envio desc");
		 //Limit
		 if (limit != 0) {
			 sql.append(" LIMIT ".concat(String.valueOf(limit)));
		 }
		
		 Query query = sesion.createSQLQuery(sql.toString()).
				 addEntity("notificacion", NotificacionPojo.class);
		 query.setInteger("days", days);
		 
		 if (canal != null && !canal.equals("")) {
			 query.setString("canal", canal);
		 }
		
		 query.setString("estado", estado);
		 return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
	@Transactional
	 public List<NotificacionPojo> getUltimosAvanzados(String estado, int days, 
			 int limit, String filtro, int android, int ios) throws Exception {
		 Session sesion = getHibernateTemplate().
				 getSessionFactory().getCurrentSession();
		 //Select
		 StringBuilder sql = new StringBuilder(
				 "SELECT n.* FROM reporte1 n WHERE n.fecha_programacion < NOW() AND n.fecha_programacion >= DATE_SUB(NOW(), INTERVAL :days DAY) AND n.estatus = :estado ");
		 
		 if (android == 1) {
			sql.append(" AND n.android = 1 ");
		 }
		 if (ios == 1) {
			sql.append(" AND n.ios = 1 ");
		 }		 
		 
		 sql.append(" AND n.filtro = :filtro");
		 
		 sql.append(" AND n.avanzada = 1");
		 
		 //Order
		 sql.append(" ORDER BY n.fecha_envio DESC");
		 //Limit
		 if (limit != 0) {
			 sql.append(" LIMIT ".concat(String.valueOf(limit)));
		 }
		
		 Query query = sesion.createSQLQuery(sql.toString()).
				 addEntity("notificacion", NotificacionPojo.class);
		 query.setInteger("days", days);
		
		 query.setString("filtro", filtro);
		
		 query.setString("estado", estado);
		 return query.list();
	 }	 

	@SuppressWarnings("unchecked")
	@Transactional
	public List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit, int android, int ios,
			int blackberry, int windowsPhone, String fecha) throws Exception {
		System.out.println("OLD");
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// Select
		StringBuilder sql = new StringBuilder(
				" select n.* from reporte1 n where (n.fecha_programacion < (NOW() + INTERVAL 15 MINUTE)) and (n.fecha_programacion >= DATE_SUB((NOW() + INTERVAL 15 MINUTE), INTERVAL :days DAY)) and (n.estatus = :estado) ");
		// Where
		if (canal != null && !canal.equals("")) {
			sql.append(" and (n.canal = :canal or n.canal = '') ");
		}
		if (android == 1) {
			sql.append(" and (n.android = 1) ");
		}
		if (ios == 1) {
			sql.append(" and (n.ios = 1) ");
		}
		if (blackberry == 1) {
			sql.append(" and (n.blackberry = 1) ");
		}
		if (windowsPhone == 1) {
			sql.append(" and (n.windows_phone = 1) ");
		}
		if (fecha != null) {
			sql.append(" and (DATE_FORMAT(n.fecha_envio, '%Y-%m-%d') >= DATE_FORMAT(:fecha_envio, '%Y-%m-%d')) ");
		}
		// Order
		sql.append(" order by n.fecha_envio desc ");
		// Limit
		if (limit != 0) {
			sql.append(" limit ".concat(String.valueOf(limit)));
		}
		Query query = sesion.createSQLQuery(sql.toString()).addEntity("notificacion", NotificacionPojo.class);
		if (limit != 0) {
			query.setFirstResult(limit);
			query.setMaxResults(limit);
		}
		query.setInteger("days", days);
		if (canal != null && !canal.equals("")) {
			query.setString("canal", canal);
		}
		if (fecha != null) {
			query.setString("fecha_envio", fecha);
		}
		query.setString("estado", estado);
		return query.list();
	}

	@Transactional
	public List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit, String filtro, 
			int android, int ios, int blackberry, int windowsPhone)
			throws Exception {
		System.out.println("USAR FILTRO");
		boolean contieneFiltro = (filtro != null && !filtro.equals("")) ? true : false;
		StringBuilder sql = new StringBuilder();
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// Select

		if (contieneFiltro) {
			sql.append("(");
		}

		sql.append(
				"select n.id, n.tipo, n.titulo, n.cuerpo, n.fechaCreacion, n.fechaenvio, n.fechaProgramacion, n.usuario, n.android, n.ios, n.blackberry, n.windowsPhone, n.canal, n.estatus, '' AS parametros, n.filtro from reporte1 n where n.fecha_programacion < now() and n.fecha_programacion >= DATE_SUB(now(), INTERVAL :days DAY) and n.estatus = :estado ");
		// Where
		if (canal != null && !canal.equals("")) {
			sql.append(" and n.canal = :canal");
		}
		if (contieneFiltro) {
			sql.append(" and n.filtro = :filtro");
		} else {
			sql.append(" and n.avanzada = 0");
		}
		
		if (android == 1) {
			sql.append(" and (n.android = 1) ");
		}
		if (ios == 1) {
			sql.append(" and (n.ios = 1) ");
		}
		if (blackberry == 1) {
			sql.append(" and (n.blackberry = 1) ");
		}
		if (windowsPhone == 1) {
			sql.append(" and (n.windows_phone = 1) ");
		}	
		
		// Limit
		if (limit != 0) {
			sql.append(" limit ".concat(String.valueOf(limit)));
		}

		if (contieneFiltro) {
			sql.append(") UNION (");
			sql.append(
					"select id, 'automatico' as tipo, titulo, cuerpo, DATE_FORMAT(fecha_envio,'%d/%m/%Y %T') as fechaCreacion, DATE_FORMAT(fecha_envio,'%d/%m/%Y %T') as fechaenvio, DATE_FORMAT(fecha_programacion,'%d/%m/%Y %T') as fechaProgramacion, 'admin' as usuario, 'true' as android, 'true' as ios, 'true' as blackberry, 'true' as windowsPhone, canal, 'enviado' as estatus, parametros, filtro from notificaciones_negocio where id_estatus <> 1 and fecha_envio is not null and filtro = :filtro and fecha_programacion >= DATE_SUB(now(), INTERVAL :days DAY) order by fecha_envio desc");
			sql.append(") order by fechaenvio desc");
		}

		Query query = sesion.createSQLQuery(sql.toString());
		query.setInteger("days", days);

		if (canal != null && !canal.equals("")) {
			query.setString("canal", canal);
		}

		if (contieneFiltro) {
			query.setString("filtro", filtro);
			query.setString("filtro", filtro);
		}

		query.setString("estado", estado);
		List<NotificacionPojo> lista = new ArrayList<NotificacionPojo>();
		List<Object> listObject = query.list();

		for (Object object : listObject) {
			try {
				Object[] object2 = (Object[]) object;
				NotificacionPojo pojo = new NotificacionPojo();
				pojo.setTipo(object2[1].toString());
				pojo.setTitulo(object2[2].toString());
				pojo.setCuerpo(object2[3].toString());
				pojo.setFechaCreacion(object2[4].toString());
				pojo.setFechaEnvio(object2[5].toString());
				pojo.setFechaProgramacion(object2[6].toString());
				pojo.setUsuario(object2[7].toString());
				pojo.setAndroid((object2[8].toString().equals("true")) ? true : false);
				pojo.setIos((object2[9].toString().equals("true")) ? true : false);
				pojo.setBlackberry((object2[10].toString().equals("true")) ? true : false);
				pojo.setWindowsPhone((object2[11].toString().equals("true")) ? true : false);
				pojo.setCanal(object2[12].toString());
				pojo.setEstatus(object2[13].toString());
				if (object2[14] != null) {
					pojo.setParametros(object2[14].toString());
				} else {
					pojo.setParametros("");
				}
				if (object2[15] != null) {
					pojo.setFiltro(object2[15].toString());
				} else {
					pojo.setFiltro("");
				}
				lista.add(pojo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public int getLastIdInsert(Notificacion notificacion) {

		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		int notificacionId = notificacion.getId();
		try {
			Transaction trans = sesion.beginTransaction();
			trans.begin();
			sesion.save(notificacion);
			if (trans.wasCommitted()) {
				trans.commit();
			}
			// sesion.beginTransaction().commit();
			sesion.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Vamos por el id
			Query query = sesion.createSQLQuery("select * from notificaciones where id = last_insert_id()")
					.addEntity("notificaciones", Notificacion.class);
			List<Notificacion> list = query.list();
			if (!list.isEmpty()) {
				notificacionId = list.get(0).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificacionId;
	}

	@Transactional
	public int existeNotificacion(String titulo, String mensaje, int ios, int android) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("SELECT COUNT(id) FROM notificaciones WHERE titulo = :titulo AND cuerpo = :mensaje AND ios = :ios AND android = :android");
		query.setParameter("titulo", titulo);
		query.setParameter("mensaje", mensaje);
		query.setParameter("ios", ios);
		query.setParameter("android", android);
		try {
			return Integer.parseInt(query.uniqueResult().toString()); 	
		} catch (Exception e) {
			return 0;
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit,
			Map<String, String[]> params, int android, int ios, int blackberry, int windowsPhone) throws Exception {
		// boolean contieneFiltro = (params != null && !params.isEmpty()) ? true
		// : false;
		System.out.println("NO USAR FILTRO");
		boolean contieneFiltro = false;
		StringBuilder sql = new StringBuilder();
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		// Select
		if (contieneFiltro) {
			sql.append("(");
		} else {
			sql.append(
					"select n.id, n.tipo, n.titulo, n.cuerpo, n.fechaCreacion, n.fechaenvio, n.fechaProgramacion, n.usuario, n.android, n.ios, n.blackberry, n.windowsPhone, n.canal, n.estatus, '{}' AS parametros, n.filtro from reporte1 n where n.fecha_programacion < now() and n.fecha_programacion >= DATE_SUB(now(), INTERVAL :days DAY) and n.estatus = :estado ");
			// Where
			if (canal != null && !canal.equals("")) {
				sql.append(" and n.canal = :canal");
			}
			sql.append(" and n.avanzada = 0");

			if (android == 1) {
				sql.append(" and (n.android = 1) ");
			}
			if (ios == 1) {
				sql.append(" and (n.ios = 1) ");
			}
			if (blackberry == 1) {
				sql.append(" and (n.blackberry = 1) ");
			}
			if (windowsPhone == 1) {
				sql.append(" and (n.windows_phone = 1) ");
			}

			// Limit
			if (limit != 0) {
				sql.append(" limit ".concat(String.valueOf(limit)));
			}
		}

		if (contieneFiltro) {
			Iterator<?> iter = params.keySet().iterator();
			int v = 1;
			StringBuilder avanzado = new StringBuilder();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = ((String[]) params.get(key))[0];
				if (key.contains("Filtro")) {
					avanzado.append(" and f.filtro = :filtro");
				} else {
					avanzado.append(" and f.filtro_" + v + " = :filtro_" + v);
					v++;
				}
				System.out.println("key: " + key + ", value: " + value);
			}

			sql.append(
					"select n.id, 'automatico' as tipo, n.titulo, n.cuerpo, DATE_FORMAT(n.fecha_envio,'%d/%m/%Y %T') as fechaCreacion, DATE_FORMAT(n.fecha_envio,'%d/%m/%Y %T') as fechaenvio, DATE_FORMAT(n.fecha_programacion,'%d/%m/%Y %T') as fechaProgramacion, 'admin' as usuario, 'true' as android, 'true' as ios, 'true' as blackberry, 'true' as windowsPhone, canal, 'enviado' as estatus, n.parametros, n.filtro from notificaciones_negocio n inner join filtro_negocio f ON n.filtro = f.filtro where n.id_estatus <> 1 and fecha_programacion >= DATE_SUB(now(), INTERVAL :days DAY) and n.fecha_envio is not null "
							+ avanzado + " order by n.fecha_envio desc");
			sql.append(") order by fechaenvio desc");
		}

		Query query = sesion.createSQLQuery(sql.toString());

		if (contieneFiltro) {
			Iterator<?> iter = params.keySet().iterator();
			int v = 1;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = ((String[]) params.get(key))[0];
				if (key.contains("Filtro")) {
					query.setString("filtro", value);
				} else {
					query.setString("filtro_" + v, value);
					v++;
				}
				System.out.println("key: " + key + ", value: " + value);
			}
		} else {
			query.setInteger("days", days);

			if (canal != null && !canal.equals("")) {
				query.setString("canal", canal);
			}
			query.setString("estado", estado);
		}

		List<NotificacionPojo> lista = new ArrayList<NotificacionPojo>();
		List<Object> listObject = query.list();

		for (Object object : listObject) {
			try {
				Object[] object2 = (Object[]) object;
				NotificacionPojo pojo = new NotificacionPojo();
				pojo.setTipo(object2[1].toString());
				pojo.setTitulo(object2[2].toString());
				pojo.setCuerpo(object2[3].toString());
				pojo.setFechaCreacion(object2[4].toString());
				pojo.setFechaEnvio(object2[5].toString());
				pojo.setFechaProgramacion(object2[6].toString());
				pojo.setUsuario(object2[7].toString());
				pojo.setAndroid((object2[8].toString().equals("true")) ? true : false);
				pojo.setIos((object2[9].toString().equals("true")) ? true : false);
				pojo.setBlackberry((object2[10].toString().equals("true")) ? true : false);
				pojo.setWindowsPhone((object2[11].toString().equals("true")) ? true : false);
				pojo.setCanal(object2[12].toString());
				pojo.setEstatus(object2[13].toString());
				if (object2[14] != null) {
					if (object2[14].toString().equals("")) {
						pojo.setParametros("{}");
					} else {
						pojo.setParametros(object2[14].toString());
					}
				} else {
					pojo.setParametros("{}");
				}
				if (object2[15] != null) {
					pojo.setFiltro(object2[15].toString());
				} else {
					pojo.setFiltro("{}");
				}
				lista.add(pojo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Notificacion> getNotificacionesAvanzadasPendientes() {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(
						"SELECT n.* from notificaciones n, estatus e WHERE n.id_estatus = e.id AND n.fecha_programacion < NOW() AND e.valor = 'pendiente_prueba' AND n.avanzada = 1 AND enProceso = 0")
				.addEntity("notificacion", Notificacion.class);		
		
		List<Notificacion> salida = query.list();
		
		if (salida != null && !salida.isEmpty()) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	        Query query1 = session.
	                createSQLQuery("UPDATE notificaciones SET enProceso = 1 WHERE token IS NOT NULL AND enProceso=0 AND id_estatus = 5 and fecha_programacion < now()");
	        query1.executeUpdate();		
		}
		return salida;
	}
		
	
}