package com.proximate.www.pushmate.dao;

import java.sql.Timestamp;
import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.Log;
import com.proximate.www.pushmate.model.LogCatalog;

public interface ILogDAO extends IBaseDao<Log>{
	
	public List<Log> getLogs(String fechaInicial, String fechaFinal, String where) throws Exception;
	public boolean guardar(String tipo, String descripcion, Timestamp fecha);
	public int getTipo(String nombre) throws Exception;
	public List<LogCatalog> getAll();

}
