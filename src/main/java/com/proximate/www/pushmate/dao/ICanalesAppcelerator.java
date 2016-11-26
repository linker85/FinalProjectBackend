package com.proximate.www.pushmate.dao;

import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.CanalesAppcelerator;
import com.proximate.www.pushmate.model.CanalesTopics;

public interface ICanalesAppcelerator extends IBaseDao<CanalesAppcelerator>{
	public void truncate();
	public void guardar(String nombre) throws Exception;
	public List<CanalesAppcelerator> selectActivos();
	CanalesTopics selectTopicxAppce(String canal, String plataforma, int idApp);
	CanalesAppcelerator selectByNombre(String param);
	List<CanalesAppcelerator> selectActivosAppId(int idApp);
}
