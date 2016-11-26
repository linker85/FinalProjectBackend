package com.proximate.www.dashmate.dao;

import java.util.List;

public interface IBaseDao<T extends Object> {
	public void insert(T o);
	public void update(T o);
	public void delete(T o);
	public List<T> select();
	public List<T> select(String criterio);
	public T selectById(int param);
}
