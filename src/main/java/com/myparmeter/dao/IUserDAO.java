package com.myparmeter.dao;

import java.util.List;

import com.myparkmeter.models.User;
import com.proximate.www.dashmate.dao.IBaseDao;

public interface IUserDAO  extends IBaseDao<User> {
	User userExists(User user);
	User userExists2(User user);
	boolean checkoutUser(User user);
	boolean checkinUser(User user);
	boolean saveCard(User user);
	boolean fineUser(User user);
	void updateUserId(User user);
	List<User> getUsersToSendNotifications(int perc);
}