package com.proximate.www.pushmate.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.myparkmeter.models.User;
import com.myparmeter.dao.IUserDAO;

@Component
public class GenNotifPedidosBackground implements ServletContextListener, Runnable {

	private static IUserDAO userDAO;
	
	public synchronized void run() {
		try {			
			System.out.println("Start: " + new Date());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<User> users80 = userDAO.getUsersToSendNotifications(80);
			List<User> users50 = userDAO.getUsersToSendNotifications(50);
			List<User> users20 = userDAO.getUsersToSendNotifications(20);
			List<User> users_1 = userDAO.getUsersToSendNotifications(-1);
			
			// Si tengo pedidos por caducar
			if (users80 != null && !users80.isEmpty()) {
				// Recorro lista
				System.out.println("//////////////////////// 80 - 85");
				for (User u : users80) {
					// Obtener tokens
					try {
						String dateSend = df.format(new Date());
						sendNotification(u.getUserid(), "Your time expires at " + u.getEnd() + ", click me to extend your time.", "You got left 80% of your checked time.", 
								u.getEmail(), "80", dateSend, u.getCoordinates());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (users50 != null && !users50.isEmpty()) {
				// Recorro lista
				System.out.println("//////////////////////// 50 - 55");
				for (User u : users50) {
					// Obtener tokens
					try {
						String dateSend = df.format(new Date());
						sendNotification(u.getUserid(), "Your time expires at " + u.getEnd() + ", click me to extend your time.", "You got left 50% of your checked time.", 
								u.getEmail(), "50", dateSend, u.getCoordinates());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (users20 != null && !users20.isEmpty()) {
				// Recorro lista
				for (User u : users20) {
					// Obtener tokens
					System.out.println("//////////////////////// 20 - 25");
					try {
						String dateSend = df.format(new Date());
						sendNotification(u.getUserid(), "Your time expires at " + u.getEnd() + ", click me to extend your time.", "You got left 20% of your checked time.", 
								u.getEmail(), "20", dateSend, u.getCoordinates());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (users_1 != null && !users_1.isEmpty()) {
				// Recorro lista
				for (User u : users_1) {
					// Obtener tokens
					System.out.println("//////////////////////// fine");
					try {
						String dateSend = df.format(new Date());
						userDAO.fineUser(u); 
						sendNotification(u.getUserid(), "You have been fined.", "Your time is over.", 
								u.getEmail(), "0", dateSend, u.getCoordinates());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("//////////////////////// end " + new Date());
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet){
        	if (t.getName().equals("Gen notificacionesPedido")) {
        		t.interrupt();
        	}
        }
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(arg0.getServletContext());
			userDAO = (IUserDAO) applicationContext.getBean("UserDAO");
			System.out.println("Seteando los valores");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendNotification(String playerId, String contents, String headings, String email, 
			String remaining, String dateSend, String coordinates) throws IOException {
		String notificationJSON = "{ " +
				"\"app_id\"              : \"27cf77a5-d7b9-4ac8-942e-cfbd6ad28d66\", " +
				"\"include_player_ids\"  : [\"" + playerId + "\"], " + 
				"\"contents\"            : {\"en\" : \"" + contents + "\"}, " +
				"\"headings\"            : {\"en\" : \"" + headings + "\"}, " +
				"\"data\"                : {\"email\" : \"" + email + "\", \"remaining\" : \"" + remaining + "\", \"coordinates\" : \"" + coordinates + "\", \"date_send\" : \"" + dateSend + "\"} " +
				"}";
		
		System.out.println(notificationJSON); 
				 
		String url = "https://onesignal.com/api/v1/notifications";
		String method = "POST";
		String contentType = "application/json";
		 
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)u.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", contentType); 
		conn.setRequestProperty("Authorization", "N2E0NzNmNjktZWM1YS00Nzk0LTgzYjAtNzExNTlmZmU0OTU0"); 
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		 				 
		OutputStream os = conn.getOutputStream();
		   DataOutputStream wr = new DataOutputStream(os);
		   wr.writeBytes (notificationJSON);
		   wr.flush ();
		   wr.close ();
		 
		 
		InputStream is = conn.getInputStream();
		   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		   String line;
		   StringBuffer response = new StringBuffer(); 
		   while((line = rd.readLine()) != null) {
		       response.append(line);
		       response.append('\r');
		   }
		   rd.close();
	}
	

}