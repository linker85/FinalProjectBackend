package com.proximate.www.pushmate.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.proximate.www.pushmate.dao.ICanalesAppcelerator;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.ILogDAO;
import com.proximate.www.pushmate.dao.INotificacionesDAO;
import com.proximate.www.pushmate.model.CanalesAppcelerator;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.utils.DateUtils;

@Component
public class ListenerEnvios implements ServletContextListener{
//			
//	@Autowired
//	private INotificacionesDAO notiDAO;
//	
//	@Autowired
//	private ICanalesAppcelerator canalesAppceleratorDAO;
//
//	@Autowired
//	private ILogDAO logDAO;
//		
//	@Autowired
//	private IConfiguracionDAO2 configuracionDAO;
//	
//	@Autowired
//	private ApplicationContext aplicacionContext;	
//	
//	
//	public static final int SERVERPORT = 8082;
//	private static ServerSocket socketServer = null;
//	private static boolean correr = true;
//	public Thread hilo = new Thread(new Runnable() {
//		
//		public void run() {
//			// TODO Auto-generated method stub
//			System.out.println("Se esta inciando el listener");
//			while (correr) {
//				Socket clientSocket = null;
//				try {
//					clientSocket = socketServer.accept();
//					new Thread(new Envio(clientSocket)).start();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			System.out.println("Hilo listener dado de baja");
//		}
//	});	
//	
//	public class Envio implements Runnable{
//		private Socket cliente;
//		
//		public Envio(Socket socket){
//			this.cliente = socket;
//		}
//
//		
//		public synchronized void run() {
//			// TODO Auto-generated method stub
//			try {
//				List<Notificacion> lista = notiDAO.getNotificacionesPendientes();
//				System.out.println("estos hay que mandar --> " + lista.size());				
//				if(lista.size() >= 1){
//					SendNotificationAppcelerator sendNotificationAppcelerator = new SendNotificationAppcelerator(configuracionDAO.getAppceleratorApiKey(), configuracionDAO.getAppceleratorUser(), configuracionDAO.getAppceleratorPassword());
//					for(Notificacion not : lista){
//						String canalTmp = not.getCanal();
//						if(not.getAndroid() || not.getIos()){
//							boolean enviado = true;
//							if(not.getAndroid()){
//								if(not.getCanal() == null || not.getCanal().equals("")){
//									//va a todos los canales
//									for(CanalesAppcelerator canal : canalesAppceleratorDAO.selectActivos()){
//										if(!sendNotificationAppcelerator.enviar(not, canal.getNombre())){
//											enviado = false;
//											logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + "(Android) ("+canal.getNombre()+") Titulo: " + not.getTitulo(), DateUtils.getDate());
//										}
//										else{
//											logDAO.guardar("notificación enviada", "Se envio la notificación ID:"+ not.getId() + " para equipos android ("+canal.getNombre()+")", DateUtils.getDate());
//										}
//									}
//								}
//								else{
//									if(!sendNotificationAppcelerator.enviar(not, canalTmp)){
//										enviado = false;
//										logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + "(Android) ("+canalTmp+") Titulo: " + not.getTitulo(), DateUtils.getDate());
//									}
//									else{
//										logDAO.guardar("notificación enviada", "Se envio la notificación ID:"+ not.getId() + " ("+canalTmp+") para equipos android", DateUtils.getDate());
//									}									
//								}
//							}
//							
//							if(not.getIos()){
//								if(not.getCanal() == null || not.getCanal().equals("")){
//									//va a todos los canales
//									for(CanalesAppcelerator canal : canalesAppceleratorDAO.selectActivos()){
//										if(!sendNotificationAppcelerator.enviar(not, canal.getNombre().concat("_IOS"))){
//											enviado = false;
//											logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + "(IOS) ("+canal.getNombre()+") Titulo: " + not.getTitulo(), DateUtils.getDate());
//										}
//										else{
//											logDAO.guardar("notificación enviada", "Se envio la notificación ID:"+ not.getId() + " para equipos IOS ("+canal.getNombre()+")", DateUtils.getDate());
//										}
//									}
//								}
//								else{
//									if(!sendNotificationAppcelerator.enviar(not, canalTmp.concat("_IOS"))){
//										enviado = false;
//										logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + "(IOS) ("+canalTmp+")Titulo: " + not.getTitulo(), DateUtils.getDate());
//									}
//									else{
//										logDAO.guardar("notificación enviada", "Se envio la notificación ID:"+ not.getId() + " para equipos IOS ("+canalTmp+")", DateUtils.getDate());
//									}
//								}								
//							}
//							
//							if(!enviado){
//								notiDAO.actualizarError(not.getId(), DateUtils.getDate());
//								sendNotificationAppcelerator.iniciarSesion();
//							}
//							else{
//								if(!notiDAO.actualizarEnviado(not.getId(), DateUtils.getDate())){									
//									logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + " Titulo: " + not.getTitulo(), DateUtils.getDate());
//								}
//							}
//							
//						}
//						else{
//							logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + " Titulo: " + not.getTitulo() +" no tiene plataforma de envio", DateUtils.getDate());
//							notiDAO.actualizarCancelado(not.getId(), DateUtils.getDate());
//						}
//					}
//				}				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			finally{
//				try {
//					cliente.close();
//					cliente = null;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}
//
//	public void sendTo(SendNotificationAppcelerator sendNotificationAppcelerator, Notificacion not){
//		if(sendNotificationAppcelerator.enviar(not)){
//			if(notiDAO.actualizarEnviado(not.getId(), DateUtils.getDate())){
//				logDAO.guardar("notificación enviada", "Se envio la notificación ID:"+ not.getId(), DateUtils.getDate());
//			}
//			else{
//				logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + " Titulo: " + not.getTitulo(), DateUtils.getDate());
//			}
//		}
//		else{
//			logDAO.guardar("error", "Error enviando la notificación ID:"+ not.getId() + " Titulo: " + not.getTitulo(), DateUtils.getDate());
//			notiDAO.actualizarCancelado(not.getId(), DateUtils.getDate());
//			sendNotificationAppcelerator.iniciarSesion();
//		}
//
//	}

	
	public void contextDestroyed(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//		correr = false;
//		System.out.println("Mata el socket");
//		try {
//			socketServer.close();
//			new ServerSocket(socketServer.getLocalPort()).close();
//			socketServer = null;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Error matando");
//			e.printStackTrace();
//		}
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//		try {
//			if(socketServer == null){
//				socketServer = new ServerSocket(SERVERPORT);
//			}			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try{
//			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
//			notiDAO = (INotificacionesDAO) applicationContext.getBean("notificacionesDAO");
//			logDAO = (ILogDAO) applicationContext.getBean("logDAO");
//			configuracionDAO = (IConfiguracionDAO2) applicationContext.getBean("ConfiguracionDAO");
//			canalesAppceleratorDAO = (ICanalesAppcelerator) applicationContext.getBean("canalesAppcelerator");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//
//		correr = true;
//		hilo.start();
	}

}
