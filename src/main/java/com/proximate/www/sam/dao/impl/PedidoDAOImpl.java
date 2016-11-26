package com.proximate.www.sam.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.sam.dao.IPedidoDAO;
import com.proximate.www.sam.dto.Pedido;
import com.proximate.www.sam.dto.PedidoDetalle;


/**
 * Esta interfaz contiene logica de negocio para comunicarse con la base de datos 
 * y realizar operaciones de lectura.
 *
 * @author Raul Garciaa
 * @version 1.0.9, 08/24/15
 * @since 1.7
 */
@SuppressWarnings("unused")
@Repository(value = "pedidoDAO")
public class PedidoDAOImpl extends BaseDAOImpl implements IPedidoDAO {

	private static final Logger logger = Logger.getLogger(PedidoDAOImpl.class.getName());

	public void insert(Pedido o) {
		// TODO Auto-generated method stub
		
	}

	public void update(Pedido o) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Pedido o) {
		// TODO Auto-generated method stub
		
	}

	public List<Pedido> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pedido selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Pedido> obtenerPedidosADosDias() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

        String consulta = "SELECT p.id_pedido, p.ano_fiscal, p.campana, p.numero_fullerette, p.fecha_pedido, p.fecha_termina_camp, IF(a.email IS NULL, '', a.email) AS email, CONCAT(f.nombre, ' ', f.apaterno, ' ', f.amaterno) AS nombre FROM pedido p LEFT JOIN acceso_fullerettes a ON p.numero_fullerette = a.numero_fullerette LEFT JOIN fullerettes_activas f ON p.numero_fullerette = f.numero_fullerette WHERE fecha_termina_camp = (SELECT DATE_FORMAT((NOW() + INTERVAL 2 DAY), '%Y-%m-%d')) AND folio_opi = 0 AND estatus = 0";
        
		StringBuilder sql = new StringBuilder(consulta);
		Query query = session.createSQLQuery(sql.toString()).
				addScalar("id_pedido", Hibernate.LONG).addScalar("ano_fiscal", Hibernate.INTEGER).
				addScalar("campana", Hibernate.INTEGER).addScalar("numero_fullerette", Hibernate.INTEGER).
				addScalar("fecha_pedido", Hibernate.STRING).addScalar("fecha_termina_camp", Hibernate.STRING).
				addScalar("email", Hibernate.STRING).addScalar("nombre", Hibernate.STRING);

		try {
			List<Pedido> lista = query.setResultTransformer(
					Transformers.aliasToBean(Pedido.class)).list();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return new ArrayList<Pedido>();
		} 		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<PedidoDetalle> obtenerPedidoDetalle(Pedido pedido) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

        String consulta = "SELECT clave_articulo, cantidad, descripcion, ROUND(precio, 2) AS precio, ROUND((cantidad * precio), 2) AS total FROM pedido_detalle WHERE id_pedido = :id_pedido";
        
		StringBuilder sql = new StringBuilder(consulta);
		Query query = session.createSQLQuery(sql.toString()).addScalar(
				"clave_articulo", Hibernate.STRING).addScalar("cantidad", Hibernate.INTEGER).
				addScalar("descripcion", Hibernate.STRING).addScalar("precio", Hibernate.FLOAT).
				addScalar("total", Hibernate.FLOAT);

		query.setParameter("id_pedido", pedido.getId_pedido());
		
		try {
			List<PedidoDetalle> lista = query.setResultTransformer(
					Transformers.aliasToBean(PedidoDetalle.class)).list();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return new ArrayList<PedidoDetalle>();
		} 		
	}	
	
}