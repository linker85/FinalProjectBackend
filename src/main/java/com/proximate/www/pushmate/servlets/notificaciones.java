package com.proximate.www.pushmate.servlets;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.INotificacionesDAO;
import com.proximate.www.pushmate.dao.INotificacionesNegocioDAO;
import com.proximate.www.pushmate.model.NotificacionNegocio;
import com.proximate.www.pushmate.model.NotificacionPojo;

public class notificaciones extends ActionSupport implements CookiesAware, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	private INotificacionesDAO notificacionesDAO;
	@Autowired
	private IConfiguracionDAO2 configuracionDAO;
	@Autowired
	private INotificacionesNegocioDAO notificacionesNegocioDAO;

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Map<String, String> requestCookies = null;

	private String estado = null;
	private String canal = null;
	private String filtro = null;
	private boolean usarFiltro = false;
	private boolean old = true;
	private int days = 5;
	private int limit = 0;
	
	private int android = 0;
	private int ios = 0;
	private int blackberry = 0;
	private int windowsPhone = 0;
	private String fecha = null;	

	public String execute() throws Exception {

		PrintWriter out;
		try {
			Map<String, String[]> params = ServletActionContext.getRequest().getParameterMap();
			if (estado == null || estado.equals("")) {
				estado = "enviado";
			}

			if (canal == null || canal.equals("")) {
				canal = "";
			}

			List<NotificacionPojo> lista = new ArrayList<NotificacionPojo>();
			if (filtro == null || filtro.equals("")) {
				lista.addAll(notificacionesDAO.getUltimosNoAvanzados(estado, canal, days, limit, android, ios));
			} else {
				lista.addAll(notificacionesDAO.getUltimosNoAvanzados(estado, canal, days, limit, android, ios));				
				lista.addAll(notificacionesDAO.getUltimosAvanzados(estado, days, limit, filtro, android, ios));				
			}
			StringBuffer jsonOldMethod = new StringBuffer();
			jsonOldMethod.append("{\"Notificaciones\": [{");
			
			int tam = 0;
			if (lista != null && !lista.isEmpty()) {
				tam = lista.size();
				for (int i = 0; i < lista.size(); i++) {
					NotificacionPojo not = lista.get(i);
					jsonOldMethod.append(not.toJSONSMALL(String.valueOf(i)));
					if (i != (lista.size() - 1)) {
						jsonOldMethod.append(",");
					}
				}
				
			}

			jsonOldMethod.append("}],\"Total\": " + tam + ",\"API_KEY\": \""
					+ configuracionDAO.getAppceleratorApiKey() + "\"}");

			out = response.getWriter();
			out.println(jsonOldMethod.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setCookiesMap(Map<String, String> cookies) {
		this.requestCookies = cookies;
	}

	public String getEstado() {
		return estado;
	}

	public int getDays() {
		return days;
	}

	public int getLimit() {
		return limit;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public boolean isUsarFiltro() {
		return usarFiltro;
	}

	public void setUsarFiltro(boolean usarFiltro) {
		this.usarFiltro = usarFiltro;
	}

	public boolean isOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
	}

	public int getAndroid() {
		return android;
	}

	public void setAndroid(int android) {
		this.android = android;
	}

	public int getIos() {
		return ios;
	}

	public void setIos(int ios) {
		this.ios = ios;
	}

	public int getBlackberry() {
		return blackberry;
	}

	public void setBlackberry(int blackberry) {
		this.blackberry = blackberry;
	}

	public int getWindowsPhone() {
		return windowsPhone;
	}

	public void setWindowsPhone(int windowsPhone) {
		this.windowsPhone = windowsPhone;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
