package com.myparkmeter.response;

import com.myparkmeter.models.User;
import com.proximate.www.pushmate.wservices.BaseResponse;

public class LoginResponse extends BaseResponse {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}