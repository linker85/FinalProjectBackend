package com.proximate.www.pushmate.model;

import java.util.List;

public class NotificacionesTemp {
	
	private List<NotificacionPojo> Notificaciones;
	private int Total;
	private String API_KEY;
	
	public List<NotificacionPojo> getNotificaciones() {
		return Notificaciones;
	}
	public void setNotificaciones(List<NotificacionPojo> notificaciones) {
		Notificaciones = notificaciones;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	public String getAPI_KEY() {
		return API_KEY;
	}
	public void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}
	@Override
	public String toString() {
		return "NotificacionesTemp [Notificaciones=" + Notificaciones + ", Total=" + Total + ", API_KEY=" + API_KEY
				+ "]";
	}
	
}