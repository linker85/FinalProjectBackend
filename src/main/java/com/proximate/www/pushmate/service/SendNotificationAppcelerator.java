package com.proximate.www.pushmate.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.model.NotificacionNegocio;

import java.io.InputStream;

@SuppressWarnings("deprecation")
public class SendNotificationAppcelerator {

	public static final String URL_ACS = "http://api.cloud.appcelerator.com/v1/";
	private String API_KEY;
	private String API_USR;
	private String API_PAS;

	private static CookieStore cookieStore = null;
	private DefaultHttpClient httpclient = new DefaultHttpClient();

	SendNotificationAppcelerator(String API_KEY, String User, String PWD) {
		this.API_KEY = API_KEY;
		this.API_USR = User;
		this.API_PAS = PWD;
	}

	public CookieStore getSessionAppcelerator() {
		return (cookieStore == null) ? (iniciarSesion()) ? cookieStore : null : cookieStore;
	}

	public boolean iniciarSesion() {
		boolean flag = true;
		try {
			HttpPost httpost = new HttpPost(URL_ACS + "users/login.json?key=" + API_KEY);

			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("login", API_USR));
			nvps.add(new BasicNameValuePair("password", API_PAS));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity mentity = response.getEntity();

			if (response.getStatusLine().getStatusCode() != 200) {
				flag = false;
			}

			EntityUtils.consume(mentity);

			System.out.println("Sesion appceletator code : " + response.getStatusLine());
			cookieStore = httpclient.getCookieStore();

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public void cerrarSesion() {
		httpclient.close();
	}

	public String getSessionID() {
		String idSesion = "";
		for (org.apache.http.cookie.Cookie c : cookieStore.getCookies()) {
			idSesion = c.getValue();
		}
		return idSesion;
	}

	public boolean enviar(Notificacion notificacion) {

		boolean flag = true;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			CookieStore cookieStore = getSessionAppcelerator();
			if (cookieStore != null) {
				List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					System.out.println("SessionID -> " + getSessionID());
				}
				httpClient.setCookieStore(cookieStore);
				List<BasicNameValuePair> entity = new ArrayList<BasicNameValuePair>();

				if (notificacion.getCanal() != null) {
					if (!notificacion.getCanal().equals("") && !notificacion.getCanal().equals("%")) {
						entity.add(new BasicNameValuePair("channel", notificacion.getCanal()));
					}
				}

				// entity.add(new BasicNameValuePair("to_tokens", "everyone"));
				// entity.add(new BasicNameValuePair("type", "android"));
				entity.add(new BasicNameValuePair("to_ids", "everyone"));

				JSONObject cred = new JSONObject();
				cred.put("alert", notificacion.getCuerpo());
				cred.put("title", notificacion.getTitulo());
				cred.put("sound", "default");
				cred.put("icon", "appicon");
				if (notificacion.getAndroid()) {
					cred.put("vibrate", true);
				}
				// cred.put("badge","0");

				entity.add(new BasicNameValuePair("payload", cred.toString()));
				HttpPost httpPost = new HttpPost(URL_ACS + "push_notification/notify.json?key=" + API_KEY);
				// HttpPost httpPost = new
				// HttpPost(URL_ACS+"push_notification/notify_tokens.json?key="
				// + API_KEY);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
				httpPost.setEntity(new UrlEncodedFormEntity(entity, Consts.UTF_8));

				HttpResponse response2 = httpClient.execute(httpPost);
				System.out.println("Response code: " + response2.getStatusLine());
				if (response2.getStatusLine().getStatusCode() != 200) {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			httpClient.close();
		}
		return flag;
	}

	public boolean enviar(Notificacion notificacion, String channel) {

		boolean flag = true;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			CookieStore cookieStore = getSessionAppcelerator();
			if (cookieStore != null) {
				List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					System.out.println("SessionID -> " + getSessionID());
				}
				httpClient.setCookieStore(cookieStore);
				List<BasicNameValuePair> entity = new ArrayList<BasicNameValuePair>();

				System.out.println("channel: " + channel);
				
				entity.add(new BasicNameValuePair("channel", channel));
				// entity.add(new BasicNameValuePair("to_tokens", "everyone"));
				// entity.add(new BasicNameValuePair("type", "ios"));
				entity.add(new BasicNameValuePair("to_ids", "everyone"));

				JSONObject cred = new JSONObject();
				cred.put("alert", notificacion.getCuerpo());
				cred.put("title", notificacion.getTitulo());
				cred.put("sound", "default");
				cred.put("icon", "appicon");
				if (notificacion.getAndroid()) {
					cred.put("vibrate", true);
				}
				// cred.put("badge","0");

				entity.add(new BasicNameValuePair("payload", cred.toString()));
				HttpPost httpPost = new HttpPost(URL_ACS + "push_notification/notify.json?key=" + API_KEY);
				// HttpPost httpPost = new
				// HttpPost(URL_ACS+"push_notification/notify_tokens.json?key="
				// + API_KEY);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
				httpPost.setEntity(new UrlEncodedFormEntity(entity, Consts.UTF_8));

				System.out.println("httpPost: " + httpPost.toString());
				
				HttpResponse response2 = httpClient.execute(httpPost);
				System.out.println("Response code: " + response2.getStatusLine());
				System.out.println("response2: " + response2.toString());
				if (response2.getStatusLine().getStatusCode() != 200) {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			httpClient.close();
		}
		return flag;
	}

	public boolean enviarByToken(Notificacion notificacion, String channel, String[] tokens) {
		boolean flag = true;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			System.out.println("notificacion: " + notificacion.toString());
			System.out.println("channel: " + channel);
			System.out.println(tokens.toString());
			CookieStore cookieStore = getSessionAppcelerator();
			if (cookieStore != null) {
				List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					System.out.println("SessionID -> " + getSessionID());
				}
				httpClient.setCookieStore(cookieStore);
				List<BasicNameValuePair> entity = new ArrayList<BasicNameValuePair>();

				entity.add(new BasicNameValuePair("channel", channel));
				StringBuffer bf = new StringBuffer();
				for (int j = 0; j < tokens.length; j++) {
					bf.append(tokens[j]);
					if (j != (tokens.length - 1)) {
						bf.append(",");
					}
				}
				// Para saber si es android comienza con APA el token
				System.out.println("La lista de tokens " + bf.toString());
				System.out.println("CHANNEL " + channel);
				entity.add(new BasicNameValuePair("to_tokens", bf.toString()));
				// entity.add(new BasicNameValuePair("type", "ios"));

				JSONObject cred = new JSONObject();
				cred.put("alert", notificacion.getCuerpo());
				cred.put("title", notificacion.getTitulo());
				cred.put("sound", "default");
				cred.put("icon", "appicon");
				cred.put("vibrate", true);

				entity.add(new BasicNameValuePair("payload", cred.toString()));
				HttpPost httpPost = new HttpPost(
						URL_ACS + "push_notification/notify_tokens.json?key=" + API_KEY + "&pretty_json=true");
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
				httpPost.setEntity(new UrlEncodedFormEntity(entity, Consts.UTF_8));
				System.out.println("httpPost: " + httpPost);
				HttpResponse response2 = httpClient.execute(httpPost);
				System.out.println("Response code: " + response2.toString());
				System.out.println("Response code: " + response2.getStatusLine());
				if (response2.getStatusLine().getStatusCode() != 200) {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			httpClient.close();
		}
		return flag;
	}

	public boolean enviarByGroup(NotificacionNegocio notificacion, String channel, String[] tokens) {
		boolean flag = true;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			CookieStore cookieStore = getSessionAppcelerator();
			if (cookieStore != null) {
				List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					System.out.println("SessionID -> " + getSessionID());
				}
				httpClient.setCookieStore(cookieStore);
				List<BasicNameValuePair> entity = new ArrayList<BasicNameValuePair>();

				entity.add(new BasicNameValuePair("channel", channel));
				StringBuffer bf = new StringBuffer();
				for (int j = 0; j < tokens.length; j++) {
					bf.append(tokens[j]);
					if (j != (tokens.length - 1)) {
						bf.append(",");
					}
				}
				// Para saber si es android comienza con APA el token
				System.out.println("La lista de tokens " + bf.toString());
				System.out.println("CHANNEL " + channel);
				entity.add(new BasicNameValuePair("to_tokens", bf.toString()));
				// entity.add(new BasicNameValuePair("type", "ios"));

				JSONObject cred = new JSONObject();
				cred.put("alert", notificacion.getCuerpo());
				cred.put("title", notificacion.getTitulo());
				cred.put("sound", "default");
				cred.put("icon", "appicon");
				cred.put("vibrate", true);

				entity.add(new BasicNameValuePair("payload", cred.toString()));
				HttpPost httpPost = new HttpPost(
						URL_ACS + "push_notification/notify_tokens.json?key=" + API_KEY + "&pretty_json=true");
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
				httpPost.setEntity(new UrlEncodedFormEntity(entity, Consts.UTF_8));
				HttpResponse response2 = httpClient.execute(httpPost);
				System.out.println("Response code: " + response2.getStatusLine());
				if (response2.getStatusLine().getStatusCode() != 200) {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			httpClient.close();
		}
		return flag;
	}

	// public static void main(String[] args){
	// try {
	// SendNotificationAppcelerator cons=new
	// SendNotificationAppcelerator("wNe1GJHBbqxx5T1OVYDKPkoYyHEJ7xqe","userupaep","userupaep");
	// cons.enviaMensaje();
	// } catch (Exception e) {
	// System.out.println("IOException: " + e.getMessage());
	// e.printStackTrace();
	// }
	// }

	public String obtieneIdSession() throws Exception {
		URL url = null;
		URLConnection uc = null;
		String idSession = null;
		try {
			url = new URL(
					URL_ACS + "users/login.json?key=" + API_KEY + "&login=" + API_USR + "&password=" + API_PAS + "");
			uc = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			if (conn.getResponseCode() != 200) {
				throw new Exception(conn.getResponseMessage());
			}
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			String respuesta = sb.toString();

			if (respuesta.contains("status\": \"ok") && respuesta.contains("code\": 200")) {
				int number = sb.indexOf("session_id");
				String meta = sb.substring(number + 14, number + 60);
				int fin = meta.indexOf("\"");
				idSession = meta.substring(0, fin);
				System.out.println("Session Id :" + idSession);
			} else {
				System.out.println("No encuentra");
			}
		} catch (Exception e) {
			throw new Exception("Problema de session " + e);
		}
		return idSession;
	}

	// public boolean enviaMensaje() throws Exception{
	// boolean flag = true;
	// DefaultHttpClient httpClient = new DefaultHttpClient();
	// try {
	// CookieStore cookieStore = getSessionAppcelerator();
	// if(cookieStore != null){
	// List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
	// if (cookies.isEmpty()) {
	// System.out.println("None");
	// }
	// else{
	// System.out.println("SessionID -> " + getSessionID());
	// }
	// httpClient.setCookieStore(cookieStore);
	// List <BasicNameValuePair> entity = new ArrayList <BasicNameValuePair>();
	// entity.add(new BasicNameValuePair("channel", "ESTUDIANTE_IOS"));
	// entity.add(new BasicNameValuePair("to_tokens",
	// "80b6c10ca10b6f6819ce704a72b1d9bbcac26c27f07429de4217b6a36733dc71"));
	//// entity.add(new BasicNameValuePair("to_tokens",
	// "APA91bGsLlQ5YqWxZZpwdYs7vrWsCulEGIWO3MG7kQn_NY30c4ySVepblVCC1F-pvOQXB8Lb2r2MPlxYs5nPEnfze0goE3NdhpTN_UsC3LCvi2bNVWnIEmXcz157c207XYOH31IfiUvvrE9Ju9nKjAufN6hPdlWUa9lMp4MR3TK_p6fwkoPauDU"));
	//// entity.add(new BasicNameValuePair("to_tokens",
	// "APA91bGPBN1PPwkDX_2idaHLXhpW97-GGNDmw8GNMCiNClvibkTT0d4LAdC5B_rGgsyiFiHZhqxgy6_ZRtXl2r8H-4bOFa8F-9pPp7MmLtNQ8pX9WgI33PGMKKvQ0HSP73JCmF_3GJHf-WsKWGT0y_q3OkdRtp-NisjTKQlJieepFST-Itktgpk,APA91bGzGUniofEV-nb7X-7H1HoaBHaKdrvI-4OIP7GP_jANGBTlzxsFXxb6xxJiRB8VHKAkYMqPWmBzlVx39dKpcubmZYy0339vqs8aNF2KGFtugCix3MSbWcRH6pDvn9tXdvZL1nYvZkhRo6mGxk2IyhAcqai4Pn7RhJmBljKLnYJOPDlpmQw"));
	//// entity.add(new BasicNameValuePair("type", "android"));
	//// entity.add(new BasicNameValuePair("to_ids", "everyone"));
	// JSONObject cred = new JSONObject();
	// cred.put("alert","testing");
	// cred.put("title","pushNotifications");
	// cred.put("sound","default");
	// cred.put("icon","appicon");
	// cred.put("vibrate",true);
	//// cred.put("badge","0");
	// entity.add(new BasicNameValuePair("payload", cred.toString()));
	//// HttpPost httpPost = new
	// HttpPost(URL_ACS+"push_notification/notify_tokens.json?key=" +
	// API_KEY+"&pretty_json=true");
	// HttpPost httpPost = new
	// HttpPost(URL_ACS+"push_notification/notify_tokens.json?key=" + API_KEY);
	// httpPost.setHeader(HTTP.CONTENT_TYPE,
	// "application/x-www-form-urlencoded");
	// httpPost.setEntity(new UrlEncodedFormEntity(entity, Consts.UTF_8));
	// HttpResponse response2 = httpClient.execute(httpPost);
	// System.out.println("Response code: "+response2.getStatusLine());
	// if(response2.getStatusLine().getStatusCode() != 200){
	// flag = false;
	// }
	// }
	// else{
	// flag= false;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// flag = false;
	// }
	// finally{
	// httpClient.close();
	// }
	// return flag;
	// }
}
