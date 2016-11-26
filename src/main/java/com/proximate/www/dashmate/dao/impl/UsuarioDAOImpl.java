package com.proximate.www.dashmate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proximate.www.dashmate.dao.IUsuarioDAO;
import com.proximate.www.dashmate.model.Usuario;


@Repository(value = "usuarioDAO")
public class UsuarioDAOImpl extends BaseDAOImpl implements IUsuarioDAO {

	
	@Transactional
	public void insert(Usuario o) {
		if(!o.getCveUsuario().equalsIgnoreCase("admin")){
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuilder sql = new StringBuilder("insert into usuario(cve_usuario,");			
			if (!"".equals(o.getPassword())){
				sql.append("password,");
			}
			
			sql.append("activo, nombre, id_rol, primera_vez, mail) values(:usuario,");
			
			if (!"".equals(o.getPassword())){
				sql.append("password(:pass),");
			}
			
			sql.append(":activo, :nombre, :idRol, :primeraVez, :email) ");
			Query   query   = session.createSQLQuery(sql.toString());
			
			if (!"".equals(o.getPassword())){
				query.setString("pass", o.getPassword());
			}
			query.setBoolean("activo", o.getActivo());
			query.setString("usuario", o.getCveUsuario());
			query.setString("nombre", o.getNombre());
			query.setInteger("idRol", o.getRolId());
			query.setBoolean("primeraVez", o.getPrimeraVez());
			query.setString("email", o.getEmail());
			query.executeUpdate();
		}
	}

	
	@Transactional
	public void update(Usuario o) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update usuario set ");		
		if(o.getPassword() != null && !o.getPassword().equals("")){
			sql.append("password = password(:pass), ");
		}
		sql.append("activo = :activo, ");
		sql.append("nombre = :nombre, ");
		sql.append("id_rol = :idRol, ");
		sql.append("primera_vez = :primeraVez, ");
		sql.append("mail = :email, ");
		sql.append("cve_usuario = :usuario ");
		sql.append("where id = :id ");

		Query query = session.createSQLQuery(sql.toString());
		
		if(o.getPassword() != null && !o.getPassword().equals("")){
			query.setString("pass", o.getPassword());
		}
		query.setInteger("id", o.getId());
		query.setBoolean("activo", o.getActivo());
		query.setString("usuario", o.getCveUsuario());
		query.setString("nombre", o.getNombre());
		query.setInteger("idRol", o.getRolId());
		query.setBoolean("primeraVez", o.getPrimeraVez());
		query.setString("email", o.getEmail());
		query.executeUpdate();
	}

	
	public void delete(Usuario o) {
		o.setActivo(false);
		update(o);
	}

	
	public List<Usuario> select() {
		return getHibernateTemplate().find("from Usuario where cve_usuario <> 'admin' ");
	}
	
	
	public List<Usuario> selectByDesencripted(){	
		return getHibernateTemplate().findByNamedQuery("getUsuariosSinEncriptar");
	}

	
	public Usuario selectById(int param) {
		Usuario usuario = null;
		try{
			usuario = (Usuario) getHibernateTemplate().find("from Usuario where id = ?", param).get(0);
		}
		catch(Exception e){
			usuario = null;
		}
		return usuario;
	}

	
	public List<Usuario> select(String criterio) {
		return (List<Usuario>) getHibernateTemplate().find("from Usuario where cveUsuario like ?", "%" + criterio + "%");
	}

	
	@Transactional
	public Usuario consultaUsario(String usuario, String password) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("select cve_usuario, password, activo, nombre, id_rol, id, primera_vez, mail from usuario where cve_usuario = :usuario and password = password(:pass)");
		Query query1 = session.createSQLQuery(sql.toString());
		query1.setParameter("usuario", usuario);
		query1.setParameter("pass", password);
		
		List<Object> listObject =  query1.list();
		
		if (listObject != null && !listObject.isEmpty()){
			Usuario usuarioObj = new Usuario();
			for (Object object : listObject) {
				Object[] object2 = (Object[]) object;
				usuarioObj.setCveUsuario(object2[0].toString());
				usuarioObj.setPassword(object2[1].toString());
				usuarioObj.setActivo((Boolean) object2[2]);
				usuarioObj.setNombre(object2[3].toString());
				usuarioObj.setRolId(Integer.parseInt(object2[4].toString()));
				usuarioObj.setId(Integer.parseInt(object2[5].toString()));
				usuarioObj.setPrimeraVez((Boolean)object2[6]);
				usuarioObj.setEmail((object2[7] != null) ? object2[7].toString() : "");
				break;
			}
			return usuarioObj;
		} else {
			return null;
		} 
	}

	
	public List<Usuario> selectByActive() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Usuario where cve_usuario <> 'admin' and activo = 1");
	}

	
	public Usuario selectByUser(String  username) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		try{
			usuario = (Usuario) getHibernateTemplate().find("from Usuario where cve_usuario = ?", username).get(0);
		}
		catch(Exception e){
			usuario = null;
		}
		return usuario;
	}
	
	
	@Transactional
	public boolean cambiaPrimeraVez(int idUsuario, boolean value){
		try{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update usuario set primera_vez = "+ ((value) ? "1" : "0") +" where id = :id");
		Query query1 = session.createSQLQuery(sql.toString());
		query1.setParameter("id", idUsuario);
		return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	public Usuario selectByMail(String mail) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		try{
			usuario = (Usuario) getHibernateTemplate().find("from Usuario where mail = ?", mail).get(0);
		}
		catch(Exception e){
			usuario = null;
		}
		return usuario;
	}

	
	public Usuario selectByUserOrMail(String username, String mail) {
		Usuario usuario = null;
		try {
			System.out.println("username: " + username + ", mail: " + mail);
			String query = "from Usuario where cve_usuario = ? " + ((mail == null  || mail.equals("")) ? "" : "or mail = '".concat(mail).concat("'") ); 
			usuario = (Usuario) getHibernateTemplate().find(query, username).get(0);
		} catch(Exception e) {
			usuario = null;
		}
		return usuario;
	}

	
	@Transactional
	public boolean updatePreferenceDiasPercent(int idUsuario, int dias) {
		// TODO Auto-generated method stub
		Session sesion = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = sesion.createSQLQuery("update usuario set pre_diasporcentajevistas = :dias where id = :id");
		query.setParameter("dias", dias);
		query.setParameter("id", idUsuario);
		if(query.executeUpdate() >= 1){
			return true;
		} else {
			return false;	
		}
	}
	
	@Transactional
	public int eliminarUsuario(int id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update usuario set ");		
		sql.append("activo = :activo ");
		sql.append("where id = :id ");
	
		Query query = session.createSQLQuery(sql.toString());
	
		query.setInteger("id", id);
		query.setInteger("activo", 0);
		return query.executeUpdate();
	}
	
	@Transactional
	public int actualizarPassUsuario(Usuario usuario) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuilder sql = new StringBuilder("update usuario set ");		
		sql.append("password = password(:pass)");
		sql.append("where id = :id ");
	
		Query query = session.createSQLQuery(sql.toString());
	
		query.setInteger("id", usuario.getId());
		query.setString("pass", usuario.getPassword());
		return query.executeUpdate();
	}	
	
}
