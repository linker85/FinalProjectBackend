package com.proximate.www.pushmate.dao;

import java.sql.Timestamp;
import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.Estatus;
import com.proximate.www.pushmate.model.NotificacionNegocio;
import com.proximate.www.pushmate.model.Token;

public interface INotificacionesNegocioDAO extends IBaseDao<NotificacionNegocio>{

	public boolean guardar(NotificacionNegocio notificacionNegocio);
	public List<NotificacionNegocio> getListaPendientes();
	public Estatus getEstatus(String nombre) throws Exception;
	public boolean actualizarEnviado(int id, Timestamp fecha);
	public boolean actualizarError(int id, Timestamp fecha);
	public int depurarRegistros();
	public List<NotificacionNegocio> getListaByMatricula(String matricula);
	public boolean existFiltroVSToken(String token, String filtro);
	Token existFiltroVSToken2(String token, String filtro);
}
