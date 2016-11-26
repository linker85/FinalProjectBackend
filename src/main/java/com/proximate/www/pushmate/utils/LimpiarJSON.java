package com.proximate.www.pushmate.utils;

/**
 * Esta clase contiene lógica de negocio para pasar caracteres html a acentos y viceversa.
 *
 * @author Raúl García
 * @version 1.0.9, 08/24/15
 * @since 1.7
 */
public class LimpiarJSON {

	/**
	 * @param json
	 * @return json con caracteres html en vez de acentos y ñ
	 */
	public static String limpiarJSON(String json) {
		String json2 = json.replace("Ñ", "&Ntilde;").replace("ñ", "&ntilde;")
		.replace("á", "&aacute;").replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;").replace("ú", "&uacute;")
		.replace("Á", "&Aacute;").replace("É", "&Eacute;").replace("Í", "&Iacute;").replace("Ó", "&Oacute;").replace("Ú", "&Uacute;");
		return json2;
	}
	
	/**
	 * @param json
	 * @return json con acentos y ñ en vez de caracteres html
	 */
	public static String revertirLimpiarJSON(String json) {
		String json2 = json.replace("&Ntilde;", "Ñ").replace("&ntilde;", "ñ")
		.replace("&aacute;", "á").replace("&eacute;", "é").replace("&iacute;", "í").replace("&oacute;", "ó").replace("&uacute;", "ú")
		.replace("&Aacute;", "Á").replace("&Eacute;", "É").replace("&Iacute;", "Í").replace("&Oacute;", "Ó").replace("&Uacute;", "Ú");
		return json2;
	}	

}