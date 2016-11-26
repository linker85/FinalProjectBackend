package com.proximate.www.pushmate.wservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.proximate.www.pushmate.dao.ICanalesAppcelerator;
import com.proximate.www.pushmate.dao.IFiltrosNegocio;
import com.proximate.www.pushmate.dao.ITokensDAO;
import com.proximate.www.pushmate.model.Token;

@Component
@Path("/app")
public class TokensWebServices {

	@Autowired
	ICanalesAppcelerator canalesAppcelerator;

	@Autowired
	IFiltrosNegocio filtrosNegocioDAO;

	@Autowired
	ITokensDAO tokensDAO;

	@SuppressWarnings({ "finally" })
	@Path("/token.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String login(@QueryParam("valor") String valor) {
		BaseResponse br = new BaseResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (valor != null) {
				System.out.println("Recibiendo token --> " + valor);
				JSONObject parser = (JSONObject) new JSONParser().parse(valor);
				System.out.println("el parser " + parser.toString());
				System.out.println("el mac " + parser.get("mac").toString());
				System.out.println("el token " + parser.get("token").toString());
				System.out.println("el filtro " + parser.get("filtroKey").toString());
				System.out.println("la plataforma " + parser.get("platform").toString());
				System.out.println("la aplicacion " + parser.get("id_app").toString());
				
				Token token = new Token();
				String filtroKey = parser.get("filtroKey").toString();
				token.setToken(parser.get("token").toString());
				token.setFiltro(filtroKey);
				token.setPlataforma(parser.get("platform").toString());
				token.setMac(parser.get("mac").toString());
				token.setId_aplicacion(Integer.
						parseInt(parser.get("id_app").toString()));

				if (tokensDAO.guardar(token)) {
					br.setSuccess(true);
					br.setMensaje("");
					// Convertir response a json
					responseJSON = gson.toJson(br);				
					return responseJSON;

				} else {
					br.setSuccess(false);
					br.setMensaje("Valores vacios.");
					// Convertir response a json
					responseJSON = gson.toJson(br);				
					return responseJSON;					
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			br.setSuccess(false);
			br.setMensaje("Error json.");
			// Convertir response a json
			responseJSON = gson.toJson(br);				
			return responseJSON;
		} catch (Exception e) {
			e.printStackTrace();
			br.setSuccess(false);
			br.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(br);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@Path("/logoff.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String logoff(@QueryParam("valor") String valor){
		try {			
			if (valor != null) {
				System.out.println("Recibiendo token --> " + valor);
				JSONObject parser = (JSONObject) new JSONParser().parse(valor);
				
				System.out.println("el parser "+  parser.toString());
				System.out.println("el filtro "+ parser.get("filtroKey").toString());
				System.out.println("el mac " + parser.get("mac").toString());
				System.out.println("el token " + parser.get("token").toString());
				System.out.println("el filtro " + parser.get("filtroKey").toString());
				System.out.println("la plataforma " + parser.get("platform").toString());
				System.out.println("la aplicacion " + parser.get("id_app").toString());
								
				Token token = new Token();
				String filtroKey = parser.get("filtroKey").toString();
				token.setToken(parser.get("token").toString());
				token.setFiltro(filtroKey);
				token.setPlataforma(parser.get("platform").toString());
				token.setMac(parser.get("mac").toString());
				token.setId_aplicacion(Integer.
						parseInt(parser.get("id_app").toString()));
				
				if (tokensDAO.deslogear(token)) {
					return "ok";
				} else {
					return "token error";	
				}
			} else {
				return "parameters invalid";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error json";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
}