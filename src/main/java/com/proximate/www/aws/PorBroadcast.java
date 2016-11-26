package com.proximate.www.aws;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.proximate.www.aws.PorBroadcast.Platform;
import com.proximate.www.pushmate.model.Notificacion;

public class PorBroadcast {
	public static final Map<Platform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<Platform, Map<String, MessageAttributeValue>>();
	static {
		attributesMap.put(Platform.GCM, null);
		attributesMap.put(Platform.APNS, null);
	}
	
	public static enum Platform {
		// Apple Push Notification Service
		APNS,
		// Google Cloud Messaging
		GCM
	}	
       
    public boolean publicar(Notificacion not, BasicAWSCredentials credenciales, String endpointArn) {
    	Platform plataform = Platform.GCM;
		AmazonSNSClient snsClient = new AmazonSNSClient(credenciales);		                           
		snsClient.setRegion(Region.getRegion(Regions.US_WEST_1));
    	PublishRequest publishRequest = new PublishRequest();  
    	
		Map<String, MessageAttributeValue> notificationAttributes = getValidNotificationAttributes(attributesMap
				.get(plataform));
		if (notificationAttributes != null && !notificationAttributes.isEmpty()) {
			publishRequest.setMessageAttributes(notificationAttributes);
		}
		publishRequest.setMessageStructure("json");  
		// If the message attributes are not set in the requisite method,
		// notification is sent with default attributes		
		String message = getMensaje(not);	
		System.out.println(message);
		publishRequest.setTopicArn(endpointArn);
		publishRequest.setMessage(message.toString());
		PublishResult publishResult = snsClient.publish(publishRequest);	
		return (publishResult.getMessageId() != null);
    }
    
    public boolean publicarPorTarget2(Notificacion not, BasicAWSCredentials credenciales, String targetArn) {
    	Platform plataform = Platform.GCM;
		AmazonSNSClient snsClient = new AmazonSNSClient(credenciales);		                           
		snsClient.setRegion(Region.getRegion(Regions.US_WEST_1));
    	PublishRequest publishRequest = new PublishRequest();  
    	
		Map<String, MessageAttributeValue> notificationAttributes = getValidNotificationAttributes(attributesMap
				.get(plataform));
		if (notificationAttributes != null && !notificationAttributes.isEmpty()) {
			publishRequest.setMessageAttributes(notificationAttributes);
		}
		publishRequest.setMessageStructure("json");  
		// If the message attributes are not set in the requisite method,
		// notification is sent with default attributes		
		String message = getMensaje(not);	
		System.out.println(message);
		publishRequest.setTargetArn(targetArn);
		publishRequest.setMessage(message.toString());
		PublishResult publishResult = snsClient.publish(publishRequest);	
		return (publishResult.getMessageId() != null);
    }          
    
	private static Map<String, MessageAttributeValue> getValidNotificationAttributes(
			Map<String, MessageAttributeValue> notificationAttributes) {
		Map<String, MessageAttributeValue> validAttributes = new HashMap<String, MessageAttributeValue>();

		if (notificationAttributes == null) return validAttributes;

		for (Map.Entry<String, MessageAttributeValue> entry : notificationAttributes
				.entrySet()) {
			if (!StringUtils.isBlank(entry.getValue().getStringValue())) {
				validAttributes.put(entry.getKey(), entry.getValue());
			}
		}
		return validAttributes;
	} 
	
	private static String getMensaje(Notificacion not) {
		try {
			return SampleMessageGenerator.getMensaje(not.getCuerpo(), not.getTitulo());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}