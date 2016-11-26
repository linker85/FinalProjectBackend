package com.proximate.www.pushmate.utils;

/**
 * Esta clase contiene l�gica de negocio para pasar caracteres html a acentos y viceversa.
 *
 * @author Ra�l Garc�a
 * @version 1.0.9, 08/24/15
 * @since 1.7
 */
public class LimpiarJSON {

	/**
	 * @param json
	 * @return json con caracteres html en vez de acentos y �
	 */
	public static String limpiarJSON(String json) {
		String json2 = json.replace("�", "&Ntilde;").replace("�", "&ntilde;")
		.replace("�", "&aacute;").replace("�", "&eacute;").replace("�", "&iacute;").replace("�", "&oacute;").replace("�", "&uacute;")
		.replace("�", "&Aacute;").replace("�", "&Eacute;").replace("�", "&Iacute;").replace("�", "&Oacute;").replace("�", "&Uacute;");
		return json2;
	}
	
	/**
	 * @param json
	 * @return json con acentos y � en vez de caracteres html
	 */
	public static String revertirLimpiarJSON(String json) {
		String json2 = json.replace("&Ntilde;", "�").replace("&ntilde;", "�")
		.replace("&aacute;", "�").replace("&eacute;", "�").replace("&iacute;", "�").replace("&oacute;", "�").replace("&uacute;", "�")
		.replace("&Aacute;", "�").replace("&Eacute;", "�").replace("&Iacute;", "�").replace("&Oacute;", "�").replace("&Uacute;", "�");
		return json2;
	}	

}