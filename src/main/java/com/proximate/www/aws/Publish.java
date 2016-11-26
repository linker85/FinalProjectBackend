package com.proximate.www.aws;
import com.google.gson.annotations.SerializedName;

public class Publish {
	@SerializedName("GCM")
	private GCM gcm;
	@SerializedName("apns")
	private APNS apns;
	@SerializedName("default")
	private String default1 = "default";
	
	public GCM getGcm() {
		return gcm;
	}
	public void setGcm(GCM gcm) {
		this.gcm = gcm;
	}
	public APNS getApns() {
		return apns;
	}
	public void setApns(APNS apns) {
		this.apns = apns;
	}
	public String getDefault1() {
		return default1;
	}
	public void setDefault1(String default1) {
		this.default1 = default1;
	}
	
}