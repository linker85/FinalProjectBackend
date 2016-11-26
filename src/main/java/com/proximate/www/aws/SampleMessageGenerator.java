package com.proximate.www.aws;


/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.proximate.www.pushmate.model.Notificacion;

public class SampleMessageGenerator {

	/*
	 * This message is delivered if a platform specific message is not specified
	 * for the end point. It must be set. It is received by the device as the
	 * value of the key "default".
	 */
	public static final String defaultMessage = "This is the default message";

	public static enum Platform {
		// Apple Push Notification Service
		APNS,
		// Sandbox version of Apple Push Notification Service
		APNS_SANDBOX,
		// Amazon Device Messaging
		ADM,
		// Google Cloud Messaging
		GCM,
		// Baidu CloudMessaging Service
		BAIDU,
		// Windows Notification Service
		WNS,
		// Microsoft Push Notificaion Service
		MPNS;
	}
	
	/*public static String test(String mensaje, String titulo) {
		String inicial = "{\"default\":\""+mensaje+"\",\"GCM\":\"{";
		String test = "\"data\":{\"payload\":{\"android\":{\"icon\":\"appicon\",\"title\":\""+titulo+"\",\"alert\":\""+mensaje+"\"}}}";
		test= test.replace("\"", "\\\"");
		inicial += test + "}\"}";
		return inicial;
	}*/
	
	public static String obtenerJsonPush(String mensaje, String titulo) {
		String inicial = "{\"default\":\""+mensaje+"\",\"APNS\":\"{";
		String apns_body = "\"aps\":{\"sound\":\"default\",\"alert\":\""+mensaje+"\"}";
		apns_body= apns_body.replace("\"", "\\\"");
		inicial += apns_body + "}\",\"GCM\":\"{";
		String gcm_body = "\"data\":{\"payload\":{\"android\":{\"sound\":\"default\",\"icon\":\"appicon\",\"title\":\""+titulo+"\",\"alert\":\""+mensaje+"\"}}}";
		gcm_body = gcm_body.replace("\"", "\\\"");
		inicial += gcm_body + "}\"";
		inicial += "}";
		return inicial;
	}
	

	public static String getMensaje(String mensaje, String titulo) throws JSONException {

		return obtenerJsonPush(mensaje, titulo);
	}		

}