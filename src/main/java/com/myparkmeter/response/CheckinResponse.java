package com.myparkmeter.response;

import com.proximate.www.pushmate.wservices.BaseResponse;

public class CheckinResponse extends BaseResponse {
	private Integer result;
	private String email;
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "CheckinResponse [result=" + result + ", email=" + email + "]";
	}
	
}