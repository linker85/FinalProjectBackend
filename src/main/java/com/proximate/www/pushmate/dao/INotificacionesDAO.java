package com.proximate.www.pushmate.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.Estatus;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.model.NotificacionPojo;
import com.proximate.www.pushmate.model.TipoNotificacion;
import com.proximate.www.pushmate.model.TipoPlataforma;

public interface INotificacionesDAO extends IBaseDao<Notificacion>{
	
	public List<Notificacion> getNotificaciones() throws Exception;
	public List<Notificacion> getNotificacionesPendientes() throws Exception;
	public List<TipoNotificacion> getListaTipos() throws Exception;
	public List<TipoPlataforma> getLstaPlataformas() throws Exception;
	public Estatus getEstatus(String nombre) throws Exception;
	public TipoPlataforma getPlataforma(int id) throws Exception;
	public TipoNotificacion getTipo(int id) throws Exception;
	public boolean guardar(Notificacion notificacion);
	public boolean actualizar(Notificacion notificacion);
	public boolean actualizarEnviado(int id, Timestamp fecha);
	public boolean actualizarCancelado(int id, Timestamp fecha);
	public boolean actualizarError(int id, Timestamp fecha);
	public List<NotificacionPojo> getNotificacionesReport() throws Exception;
	public List<NotificacionPojo> getNotificacionesReport(String fechaInicial, String fechaFinal) throws Exception;
	public List<NotificacionPojo> getNotificacionesReport(String fechaInicial, String fechaFinal, String where) throws Exception;
	public List<NotificacionPojo> getNotificacionesReportByProgramacion(String fechaInicial, String fechaFinal) throws Exception;
	public List<NotificacionPojo> getNotificacionesReportByProgramacion(String fechaInicial, String fechaFinal, String where) throws Exception;

	
	public List<NotificacionPojo> buscar(int id, String texto) throws Exception;
	public List<NotificacionPojo> buscar(int id, String texto, String were) throws Exception;
	public Notificacion getById(int id);
	
	
	public Integer getCountPendientes() throws Exception;
	public Integer getCountEnviado() throws Exception;
	public Integer getCountCancelado() throws Exception;
	//Cambiado para las notificaciones personalizadas
	public List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit, String filtro, int android, int ios, int blackberry, int windowsPhone) throws Exception;
	public List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit, Map<String, String[]> filtro, int android, int ios, int blackberry, int windowsPhone) throws Exception;
	
	/*Para las notificaciones avanzadas*/
	public int getLastIdInsert(Notificacion notificacion);
	List<NotificacionPojo> getUltimos(String estado, String canal, int days, int limit, int android, int ios,
			int blackberry, int windowsPhone, String fecha) throws Exception;
	
	int existeNotificacion(String titulo, String mensaje, int ios, int android);
	
	// AWS SNS
	List<Notificacion> getNotificacionesAvanzadasPendientes() throws Exception;
	
	List<NotificacionPojo> getUltimosAvanzados(String estado, int days, 
			int limit, String filtro, int android, int ios) throws Exception;
	List<NotificacionPojo> getUltimosNoAvanzados(String estado, String canal, int
			 days, int limit, int android, int ios) throws Exception;	
}
