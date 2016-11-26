package com.proximate.www.pushmate.dao;

import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.pushmate.model.Token;

public interface ITokensDAO extends IBaseDao<Token>{
	
	public boolean guardar(Token token);
	public List<Token> getListTokenFiltro1(String valor);
	public List<Token> getListTokenFiltro2(String valor);
	public Token getToken(String filtro) throws NullPointerException;
	public boolean borrarNegocioByFiltro(String filtro);
	List<Token> getListTokenFiltroN(String valor);
	boolean existeFiltro(String filtro);
	Token existeFiltroToken(String filtro);
	boolean deslogear(Token token);
}
