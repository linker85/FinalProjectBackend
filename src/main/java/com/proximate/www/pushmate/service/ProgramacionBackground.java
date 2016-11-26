package com.proximate.www.pushmate.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.proximate.www.aws.BatchCreatePlatformEndpointSample;
import com.proximate.www.aws.PorBroadcast;
import com.proximate.www.pushmate.dao.ICanalesAppcelerator;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.ILogDAO;
import com.proximate.www.pushmate.dao.INotificacionesDAO;
import com.proximate.www.pushmate.dao.INotificacionesNegocioDAO;
import com.proximate.www.pushmate.model.CanalesAppcelerator;
import com.proximate.www.pushmate.model.CanalesTopics;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.model.Token;
import com.proximate.www.pushmate.utils.DateUtils;

@Component
public class ProgramacionBackground implements ServletContextListener, Runnable {

	private static INotificacionesDAO notiDAO;

	private static ILogDAO logDAO;

	private static IConfiguracionDAO2 configuracionDAO;
	
	private static INotificacionesNegocioDAO notificacionesNegocioDAO;
	
	private static ICanalesAppcelerator canalesAppceleratorDAO;
	 
	public synchronized void run() {
		try {
			// Vamos por las notificaciones
			try {
				List<Notificacion> lista = notiDAO.getNotificacionesPendientes();
				System.out.println("Notificaciones a enviar --> " + lista.size());
				if (!lista.isEmpty()) {
					PorBroadcast sns = new PorBroadcast();
					// Obtengo credenciales
					BasicAWSCredentials credenciales = new BasicAWSCredentials(
							configuracionDAO.getAccessKeyAWS(), configuracionDAO.getSecretKeyAWS());
					// Obtengo credenciales
					for (Notificacion not : lista) {
						boolean enviado = true;
						String canalTmp = not.getCanal();
						// Es ios o android
						if (not.getAndroid() || not.getIos()) {
							
							if (not.getAndroid()) {
								// Es para todos los canales
								if (not.getCanal() == null || not.getCanal().equals("")) {
									for (CanalesAppcelerator canal : canalesAppceleratorDAO.selectActivos()) {
										// Public para android
										CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce(canal.getNombre(), "android", canal.getId_aplicacion());
										// Tengo arns
										if (a != null && a.getTopic_arn() != null) {
											// Publicar 
											System.out.println("Publicar para todos(" + canal.getNombre() + ") android: " + a.getTopic_arn());
											if (sns.publicar(not, credenciales, a.getTopic_arn())) {
												enviado = true;
											} else {
												enviado = false;
											}											
										}	
									}
								} else {
									// Es para algun canal especifico
									CanalesAppcelerator can = null;
									try {
										can = canalesAppceleratorDAO.selectByNombre(canalTmp);
										CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce(canalTmp, "android", can.getId_aplicacion());
										// Tengo arns
										if (a != null && a.getTopic_arn() != null) {
											// Publicar 
											System.out.println("Publicar para " + canalTmp + " android: " + a.getTopic_arn());
											if (sns.publicar(not, credenciales, a.getTopic_arn())) {
												enviado = true;
											} else {
												enviado = false;
											}											
										}											
									} catch (Exception e) {
										enviado = false;
									}								
								}
							}
							
							if (not.getIos()) {
								// Es para todos los canales
								if (not.getCanal() == null || not.getCanal().equals("")) {
									for (CanalesAppcelerator canal : canalesAppceleratorDAO.selectActivos()) {
										CanalesAppcelerator can = canalesAppceleratorDAO.selectByNombre(canalTmp);
										CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce(canal.getNombre(), "ios", can.getId_aplicacion());
										// Tengo arns
										if (a != null && a.getTopic_arn() != null) {
											// Publicar  
											System.out.println("Publicar para todos(" + canal.getNombre() + ") ios: " + a.getTopic_arn());
											if (sns.publicar(not, credenciales, a.getTopic_arn())) {
												enviado = true;
											} else {
												enviado = false;
											}											
										}
									}
								} else {
									// Es para algun canal especifico
									CanalesAppcelerator can = null;
									try {
										can = canalesAppceleratorDAO.selectByNombre(canalTmp);
										CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce(canalTmp, "ios", can.getId_aplicacion());
										// Tengo arns
										if (a != null && a.getTopic_arn() != null) {
											// Publicar  
											System.out.println("Publicar para " + canalTmp + " ios: " + a.getTopic_arn());
											if (sns.publicar(not, credenciales, a.getTopic_arn())) {
												enviado = true;
											} else {
												enviado = false;
											}											
										}										
									} catch (Exception e) {
										enviado = false;
									}								
								}								
							}
							// No se pudo enviar notificacion
							if (!enviado) {
								notiDAO.actualizarError(not.getId(), DateUtils.getDate());
							} else {
								// Cambiar estatus porque si se pudo enviar
								if(!notiDAO.actualizarEnviado(not.getId(), DateUtils.getDate())){									
									logDAO.guardar("error", "Error enviando la notificación ID:" + not.getId() + ", Titulo: " + not.getTitulo(), DateUtils.getDate());
								}
							}
						} else {
							logDAO.guardar("error", "Error enviando la notificación ID:" + not.getId() + ", Titulo: " + not.getTitulo(), DateUtils.getDate());
							notiDAO.actualizarCancelado(not.getId(), DateUtils.getDate());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// Hubo un error, no pasa nada continuamos con los demas
				logDAO.guardar("error", "Error general enviando notificaciones", DateUtils.getDate());			
			}
			
			
			
			// Vamos por las notificaciones avanzadas
			try {
				List<Notificacion> listaToken = notiDAO.getNotificacionesAvanzadasPendientes();
				System.out.println("Notificaciones a enviar por forma token --> " + listaToken.size());
				if (!listaToken.isEmpty()) {
					String secret = configuracionDAO.getSecretKeyAWS();
					String access = configuracionDAO.getAccessKeyAWS();
					
					PorBroadcast sns = new PorBroadcast();
					// Obtengo credenciales
					BasicAWSCredentials credenciales = new BasicAWSCredentials(
							configuracionDAO.getAccessKeyAWS(), configuracionDAO.getSecretKeyAWS());
					// Listo notificaciones
					for (Notificacion n : listaToken) {
						try {
							boolean enviado = true;
							// 1.a No tengo filtro, inscribo al usuario + envio push + desinscribo
							if (n.getFiltro() == null) {
								Token aws = null;
								// Obtengo plataforma de la notificacion
								if (n.getToken() != null) {
									String plataforma = "android";
									// Obtengo el mapeo de canal-plataforma para obtener arns correctos
									CanalesAppcelerator can = canalesAppceleratorDAO.selectByNombre("NO_REGISTRADOS");
									CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce("NO_REGISTRADOS", plataforma, can.getId_aplicacion());
									BatchCreatePlatformEndpointSample bp = new BatchCreatePlatformEndpointSample();
									// Tengo arns
									if (a != null && a.getApp_arn() != null && a.getTopic_arn() != null) {
										// Inscribir String token, String topicARN, String appARN
										aws = bp.suscribir(n.getToken(), a.getTopic_arn(), a.getApp_arn(), access, secret);	
										// Publicar  
										if (sns.publicarPorTarget2(n, credenciales, aws.getArn_endpoint())) {
											enviado = true;
										} else {
											enviado = false;
										}	
										// Desinscribir
										System.out.println("Desinscribir: " + plataforma);
										//String suscriptionArn, String endpointArn, String appAr
										bp.desinscribir(aws.getArn_suscription(), aws.getArn_endpoint(), a.getApp_arn(), access, secret);												
									}								
								} else {
									System.out.println("No tenia token");
									enviado = false;
								}								
							} else {
								// 1.b Si tengo filtro, busco arn x token + envio push
								Token t = null;
								try {
									t = notificacionesNegocioDAO.
											existFiltroVSToken2(n.getToken(), n.getFiltro());
								} catch (Exception e) {e.printStackTrace();}
								if (t != null) {
									// Publico x  targetARN									  
									System.out.println("Publicar x target(" + t.getArn_topic());
									if (sns.publicarPorTarget2(n, credenciales, t.getArn_topic())) {
										enviado = true;
									} else {
										enviado = false;
									}
								} else {
									Token aws = null;
									
									String plataforma = "android";
									// Obtengo el mapeo de canal-plataforma para obtener arns correctos
									CanalesAppcelerator can = canalesAppceleratorDAO.selectByNombre("NO_REGISTRADOS");
									CanalesTopics a = canalesAppceleratorDAO.selectTopicxAppce("NO_REGISTRADOS", plataforma, can.getId_aplicacion());
									BatchCreatePlatformEndpointSample bp = new BatchCreatePlatformEndpointSample();

									// Tengo arns
									if (a != null && a.getApp_arn() != null && a.getTopic_arn() != null) {
										// Inscribir String token, String topicARN, String appARN
										aws = bp.suscribir(n.getToken(), a.getTopic_arn(), a.getApp_arn(), access, secret);	
										// Publicar  
										if (sns.publicarPorTarget2(n, credenciales, aws.getArn_endpoint())) {
											enviado = true;
										} else {
											enviado = false;
										}	
										// Desinscribir
										System.out.println("Desinscribir: " + plataforma);
										//String suscriptionArn, String endpointArn, String appAr
										bp.desinscribir(aws.getArn_suscription(), aws.getArn_endpoint(), a.getApp_arn(), access, secret);												
									}									
								}
							}

							// 2. Actualizo el estatus de la notificacion
							if (enviado) {
								notiDAO.actualizarEnviado(n.getId(), DateUtils.getDate());
							} else {
								notiDAO.actualizarError(n.getId(), DateUtils.getDate());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			// Hubo un error, no pasa nada continuamos con los demas
			logDAO.guardar("error", "Error general enviando notificaciones", DateUtils.getDate());			
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet){
        	if (t.getName().equals("Envio notificaciones")) {
        		t.interrupt();
        	}
        }
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(arg0.getServletContext());
			notiDAO = (INotificacionesDAO) applicationContext.getBean("notificacionesDAO");
			logDAO = (ILogDAO) applicationContext.getBean("logDAO");
			configuracionDAO = (IConfiguracionDAO2) applicationContext.getBean("configuracionDAO2");			
			canalesAppceleratorDAO = (ICanalesAppcelerator) applicationContext.getBean("canalesAppcelerator");
			notificacionesNegocioDAO = (INotificacionesNegocioDAO) applicationContext.getBean("notificacionesNegocioDAO");
			System.out.println("Seteando los valores");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
