package com.proximate.www.pushmate.dao;

import java.util.HashMap;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.FiltroNegocio;

public interface IFiltrosNegocio extends IBaseDao<FiltroNegocio>{

	public HashMap<String, Integer> getCatalogo1() throws Exception;
	public HashMap<String, Integer> getCatalogo2() throws Exception;
	public boolean guardar(FiltroNegocio filtros) throws Exception;
	public int limpiar(String filtro) throws Exception;
	
}
