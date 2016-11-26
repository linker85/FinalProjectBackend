package com.proximate.www.pushmate.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Esta clase contiene logica para generar un json de respuesta para las vistas dada una entrada.
 *
 * @author Raúl García
 * @version 1.0.9, 08/24/15
 * @since 1.7
 */
public class GenerarJSONSalida {

	private static final Logger logger = Logger.getLogger(GenerarJSONSalida.class.getName());
	
	public static String generarJSONRespuestaCantidad(int cantidad) {
		JSONObject finalObject = new JSONObject();
		JSONArray array = new JSONArray();
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();  
		data.put("exito", cantidad);
        JSONObject jsonObject = new JSONObject(data);
        array.put(jsonObject);		
        try {
			finalObject.put("respuesta", array);
		} catch (JSONException e) {
			logger.error(MensajesGenerales.ERROR_GENERAR_JSON);
		}
		return finalObject.toString();
	}
	
	public static String generarJSONRespuestaCantidad2(int cantidad, int cantidad2) {
		JSONObject finalObject = new JSONObject();
		JSONArray array = new JSONArray();
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();  
		data.put("exito", cantidad);
		data.put("exito2", cantidad2);
        JSONObject jsonObject = new JSONObject(data);
        array.put(jsonObject);		
        try {
			finalObject.put("respuesta", array);
		} catch (JSONException e) {
			logger.error(MensajesGenerales.ERROR_GENERAR_JSON);
		}
		return finalObject.toString();
	}	
	 
	public static String generarJSONRespuesta(boolean respuesta) {
		JSONObject finalObject = new JSONObject();
		JSONArray array = new JSONArray();
		Map<String, Boolean> data = new LinkedHashMap<String, Boolean>();  
		data.put("exito", respuesta);
        JSONObject jsonObject = new JSONObject(data);
        array.put(jsonObject);		
        try {
			finalObject.put("respuesta", array);
		} catch (JSONException e) {
			logger.error(MensajesGenerales.ERROR_GENERAR_JSON);
		}
		return finalObject.toString();
	}	
	
	 
	public static String generarJSONRespuesta(boolean respuesta, String mensaje) {
		JSONObject finalObject = new JSONObject();
		JSONArray array = new JSONArray();
		Map<String, String> data = new LinkedHashMap<String,String>();  
		data.put("exito", "" + respuesta);
		data.put("mensaje", "" + mensaje);
        JSONObject jsonObject = new JSONObject(data);
        array.put(jsonObject);		
        try {
			finalObject.put("respuesta", array);
		} catch (JSONException e) {
			logger.error(MensajesGenerales.ERROR_GENERAR_JSON);
		}
		return finalObject.toString();
	}	
	
	public static String generarJSONRespuesta(int respuesta, String mensaje) {
		JSONObject finalObject = new JSONObject();
		JSONArray array = new JSONArray();
		Map<String, String> data = new LinkedHashMap<String,String>();  
		data.put("exito", "" + respuesta);
		data.put("mensaje", "" + mensaje);
        JSONObject jsonObject = new JSONObject(data);
        array.put(jsonObject);		
        try {
			finalObject.put("respuesta", array);
		} catch (JSONException e) {
			logger.error(MensajesGenerales.ERROR_GENERAR_JSON);
		}
		return finalObject.toString();
	}		
	
}