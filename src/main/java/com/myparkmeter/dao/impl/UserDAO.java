package com.myparkmeter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myparkmeter.models.User;
import com.myparmeter.dao.IUserDAO;
import com.proximate.www.dashmate.dao.impl.BaseDAOImpl;
import com.proximate.www.sam.dto.Pedido;

@Repository(value = "UserDAO")
public class UserDAO extends BaseDAOImpl implements IUserDAO {

	@Transactional
	public void insert(User o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	public void update(User o) {
		// TODO Auto-generated method stub

	}

	public void delete(User o) {
		// TODO Auto-generated method stub

	}

	public List<User> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> select(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	public User selectById(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public User userExists(User user) {
		List<User> lista = getHibernateTemplate().find("from User where email = ? AND password = ?", 
				user.getEmail(), user.getPassword());
		if ((lista!= null &&!lista.isEmpty())) {
			
			if (user.getUserid() != null) {
				try {
					Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
					StringBuilder sql = new StringBuilder("UPDATE user SET userid = :userid WHERE email = :email");
					Query query1 = session.createSQLQuery(sql.toString());
					query1.setParameter("email", user.getEmail());
					query1.setParameter("userid", user.getUserid());
					query1.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}				
			}
			return lista.get(0);
		} else {
			return new User();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User userExists2(User user) {
		List<User> lista = getHibernateTemplate().find("from User where email = ?", 
				user.getEmail());
		if ((lista!= null &&!lista.isEmpty())) {
			return lista.get(0);
		} else {
			return new User();
		}
	}
	
	@Transactional
	public boolean checkoutUser(User user) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("UPDATE user SET checked = 0, start = null, end = null, coordinates = null WHERE email = :email");
			Query query1 = session.createSQLQuery(sql.toString());
			query1.setParameter("email", user.getEmail());
			query1.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean checkinUser(User user) {
		try {
			User temp = userExists2(user);
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			if (temp != null && temp.getChecked() != 1) {				
				StringBuilder sql = new StringBuilder("UPDATE user SET checked = 1, coordinates = :coordinates, start = (SELECT NOW() AS startDate), end = (SELECT DATE_ADD(DATE_ADD(NOW(), INTERVAL :hours HOUR), INTERVAL :minutes MINUTE)) WHERE email = :email");
				Query query1 = session.createSQLQuery(sql.toString());
				query1.setParameter("coordinates", user.getCoordinates());
				query1.setParameter("hours", user.getHours());
				query1.setParameter("minutes", user.getMinutes());
				query1.setParameter("email", user.getEmail());
				query1.executeUpdate();
				return true;	
			} else {
				StringBuilder sql = new StringBuilder("UPDATE user SET end = (SELECT DATE_ADD(DATE_ADD(end, INTERVAL :hours HOUR), INTERVAL :minutes MINUTE) AS endDate) WHERE email = :email");
				Query query1 = session.createSQLQuery(sql.toString());
				query1.setParameter("hours", user.getHours());
				query1.setParameter("minutes", user.getMinutes());
				query1.setParameter("email", user.getEmail());
				query1.executeUpdate();
				return true;				
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveCard(User user) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("UPDATE user SET card = 1 WHERE email = :email");
			Query query1 = session.createSQLQuery(sql.toString());
			query1.setParameter("email", user.getEmail());
			query1.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean fineUser(User user) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("UPDATE user SET checked = 2 WHERE (TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end) < 0 AND checked = 1 WHERE email = :email ");
			Query query1 = session.createSQLQuery(sql.toString());
			query1.setParameter("email", user.getEmail());
			query1.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getUsersToSendNotifications(int perc) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

		if (perc > 0) {
	        String consulta = "SELECT end, email, userid, coordinates, ROUND((TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end),2) AS remaining FROM user WHERE (TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end) >= :timeLookUp1 AND (TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end) <= :timeLookUp2 AND checked = 1";
	        
			StringBuilder sql = new StringBuilder(consulta);
			Query query = session.createSQLQuery(sql.toString()).
					addScalar("end", Hibernate.STRING).
					addScalar("email", Hibernate.STRING).
					addScalar("userid", Hibernate.STRING).
					addScalar("coordinates", Hibernate.STRING).
					addScalar("remaining", Hibernate.FLOAT);
			
			query.setParameter("timeLookUp1", perc);
			query.setParameter("timeLookUp2", (perc + 5));
			try {
				List<User> lista = query.setResultTransformer(
						Transformers.aliasToBean(User.class)).list();
				return lista;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<User>();
			}
		} else {
	        String consulta = "SELECT end, email, userid, coordinates, ROUND((TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end),2) AS remaining FROM user WHERE (TIMESTAMPDIFF(MINUTE, NOW(), end) * 100) / TIMESTAMPDIFF(MINUTE, start, end) < 0 AND checked = 1";
	        
			StringBuilder sql = new StringBuilder(consulta);
			Query query = session.createSQLQuery(sql.toString()).
					addScalar("end", Hibernate.STRING).
					addScalar("email", Hibernate.STRING).
					addScalar("userid", Hibernate.STRING).
					addScalar("coordinates", Hibernate.STRING).
					addScalar("remaining", Hibernate.FLOAT);
			
			try {
				List<User> lista = query.setResultTransformer(
						Transformers.aliasToBean(User.class)).list();
				return lista;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<User>();
			}
		}
	}
	
}