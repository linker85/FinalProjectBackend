package com.proximate.www.pushmate.dao;

import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.PushTemporal;

public interface IPushTemporalDAO extends IBaseDao<PushTemporal>{
	public boolean guardar(PushTemporal pushTemporal);
	public List<PushTemporal> getListaFromUser(int idUsuario);
	public int borrarFromUser(int idUsuario);
}
