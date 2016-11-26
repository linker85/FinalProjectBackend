package com.proximate.www.dashmate.dao;

import java.util.List;

import com.proximate.www.dashmate.model.Usuario;


public interface IUsuarioDAO extends IBaseDao<Usuario> {
	
	Usuario consultaUsario(String usuario, String password);
	List<Usuario> selectByDesencripted();
	List<Usuario> selectByActive();
	Usuario selectByUser(String username);
	Usuario selectByUserOrMail(String username, String mail);
	boolean cambiaPrimeraVez(int idUsuario, boolean value);
	Usuario selectByMail(String mail);
	
	boolean updatePreferenceDiasPercent(int idUsuario, int dias);
	
	int eliminarUsuario(int id);
	int actualizarPassUsuario(Usuario usuario);
}
