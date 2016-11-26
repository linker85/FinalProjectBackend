package com.proximate.www.pushmate.wservices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.INotificacionesDAO;
import com.proximate.www.pushmate.dao.INotificacionesNegocioDAO;
import com.proximate.www.pushmate.dao.ITokensDAO;
import com.proximate.www.pushmate.model.Estatus;
import com.proximate.www.pushmate.model.Notificacion;
import com.proximate.www.pushmate.model.NotificacionNegocio;
import com.proximate.www.pushmate.model.NotificacionesWS;
import com.proximate.www.pushmate.model.Token;
import com.proximate.www.pushmate.security.EncryptException;
import com.proximate.www.pushmate.utils.DateUtils;

/**
 * @author Raul
 *
 */
@Path("/getNotificaciones")
@Component
public class NotificacionesREST extends BaseRest {

	@Autowired
	private IConfiguracionDAO2 configuracionDAO;
	@Autowired
	private INotificacionesDAO notificacionesDAO;
	@Autowired
	private INotificacionesNegocioDAO notificacionesNegocioDAO;
	@Autowired
	private ITokensDAO tokenDAO;
		
	@POST
	@Path("/guardarNotificacion")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String guardarNotificacion(@FormParam("jsonRequest") String jsonRequest) throws EncryptException {
		System.out.println("enviarNotificacion: ");
		String responseJSON = null;
		Gson gson = new Gson();
		BaseResponse br = new BaseResponse();
		try {
			// Desencriptar entrada
			String json = decryptJSON(jsonRequest);
			System.out.println("json: " + json.toString());
			// Parsear json
			NotificacionesWS ws2 = gson.fromJson(json, NotificacionesWS.class);
			String parametros = null;
			// Parametros de configuracion
			if (ws2.getParametros() != null && !ws2.getParametros().isEmpty()) {
				parametros = gson.toJson(ws2.getParametros());
			}
			System.out.println("parametros->" + parametros);
			System.out.println(ws2.toString());
			int idUsuario = configuracionDAO.getPushmateUserId();
			Estatus estatus = notificacionesDAO.getEstatus("pendiente");
			if (estatus != null && estatus.getId() != 0) {
				if (ws2.getTitulo() != null && !ws2.getTitulo().equals("")) {
					if (ws2.getCuerpo() != null && !ws2.getCuerpo().equals("")) {
						if (ws2.getCanal() != null) {
							if (ws2.getFechaProgramacion() != null && ws2.getHoraProgramacion() != null) {
								if (ws2.getFiltros() != null && !ws2.getFiltros().isEmpty() &&
										ws2.getPlataformas() != null && !ws2.getPlataformas().isEmpty()) {									
									//Insertar la notificacion
									Notificacion notific = new Notificacion();
									notific.setCuerpo(ws2.getCuerpo());
									notific.setEstatusId(estatus);
									notific.setFechaCreacion(DateUtils.getDate());
									notific.setFechaProgramacion(DateUtils.getDate(
											ws2.getFechaProgramacion(), ws2.getHoraProgramacion()));
									notific.setIos(false);
									notific.setAndroid(false);
									notific.setBlackberry(false);
									notific.setWindowsPhone(false);
									notific.setCanal(ws2.getCanal());
									notific.setAvanzada(true);
									notific.setFiltro("");
									notific.setToken("");
									if (!ws2.getFechaProgramacion().equals("") &&
											!ws2.getHoraProgramacion().equals("")) {
										notific.setTipoID(notificacionesDAO.getTipo(2));
									} else {
										notific.setTipoID(notificacionesDAO.getTipo(1));
									}									
									notific.setTitulo(ws2.getTitulo());
									notific.setUsuarioId(idUsuario);
									int idTmp = notificacionesDAO.getLastIdInsert(notific);
									System.out.println("La notificacion id " + idTmp);
									
									// Insertar notificacion avanzada
									if (idTmp != 0) {
										Iterator<Entry<String,String>> iterator = ws2.getFiltros().entrySet().iterator();
										while (iterator.hasNext()) {
											Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
											System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue());
											
											List<Token> listaToken = new ArrayList<Token>();
											if (entry.getKey().equals("operador")) {
												listaToken = tokenDAO.getListTokenFiltro1(entry.getValue());
											} else {
												listaToken = tokenDAO.getListTokenFiltro2(entry.getValue());
											}
											for(Token tok : listaToken) {
												try {																																
													// Guardar notificacion avanzada
													NotificacionNegocio notificacion = new NotificacionNegocio();
													notificacion.setCuerpo(notific.getCuerpo());
													notificacion.setEstatusId(notific.getEstatusId().getId());
													notificacion.setFechaCreacion(notific.getFechaCreacion());
													notificacion.setFechaProgramacion(notific.getFechaProgramacion());
													notificacion.setCanal(notific.getCanal());
													notificacion.setToken(tok.getToken());
													notificacion.setFiltro(tok.getFiltro());
													notificacion.setTitulo(notific.getTitulo());
													String plataforma = ws2.getPlataformas().get(entry.getKey());
													if (plataforma == null) {
														plataforma = "";
													}
													if (parametros != null) {
														notificacion.setParametros(parametros); 
													}
													notificacion.setPlataforma(plataforma);
													notificacion.setNotificacionId(idTmp);
																									
													notificacionesNegocioDAO.guardar(notificacion);
													br.setSuccess(true);
												} catch(Exception e) {
													e.printStackTrace();
													br.setSuccess(false);
													br.setMensaje("Ocurrio un error al tratar de guardar la notificacion avanzada.");
												}												
											}																															
										}										
									} else {
										br.setSuccess(false);
										br.setMensaje("Ocurrio un error al tratar de guardar la notificacion.");										
									}
									
								} else {
									br.setSuccess(false);
									br.setMensaje("Es obligatorio tener al menos 1 filtro y 1 plataforma.");						
								}							
							} else {
								br.setSuccess(false);
								br.setMensaje("La fecha y hora deben de aparecer aun si estan vacios.");							
							}							
						} else {
							br.setSuccess(false);
							br.setMensaje("El canal debe de aparecer aunque sea vacio.");						
						}							
					} else {
						br.setSuccess(false);
						br.setMensaje("El cuerpo es obligatorio.");						
					}
				} else {
					br.setSuccess(false);
					br.setMensaje("El titulo es obligatorio.");						
				}
			} else {
				br.setSuccess(false);
				br.setMensaje("No se encontro el registro 'pendiente' dentro del catalogo: estatus");				
			}
		} catch (EncryptException e) {
			br.setSuccess(false);
			br.setMensaje("Ocurrio un error de seguridad.");
			e.printStackTrace();
		} catch (Exception e) {
			br.setSuccess(false);
			br.setMensaje("Ocurrio un error al tratar de guardar la notificacion.");			
			e.printStackTrace();
		}
		// Convertir response a json
		responseJSON = gson.toJson(br);
		// Encriptar json
		String responseEncJSON = encryptJSON(responseJSON);
		return responseEncJSON;
	}
		
}