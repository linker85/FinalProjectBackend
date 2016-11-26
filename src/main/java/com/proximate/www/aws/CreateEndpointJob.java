package com.proximate.www.aws;

import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesResult;
import com.amazonaws.services.sns.model.InvalidParameterException;
import com.amazonaws.services.sns.model.NotFoundException;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.proximate.www.pushmate.model.Token;
@Component
public class CreateEndpointJob {

    private AmazonSNS client;
    private String applicationArn;
    private String region = "us-west-1";
    
    private String arn;
    private String topicArn;

    public CreateEndpointJob() {
    	
    }
    
    public CreateEndpointJob(String accessKey, String secretKey) {
        try {
            client = new AmazonSNSClient(new BasicAWSCredentials(accessKey, secretKey));
        } catch (Exception e) {
        	e.printStackTrace();
            System.err
                    .println("[ERROR] Error opening file <"
                            + BatchCreatePlatformEndpointSample.AWSCREDENTIALSPROPERTIES_FILE + ">"
                            + " " + e.getMessage());
            //System.exit(BatchCreatePlatformEndpointSample.CREDENTIAL_RETRIEVAL_FAILURE_ERROR_CODE);
        }
    }
    
    @SuppressWarnings("finally")
	public String unsuscribe(String suscriptionArn, String endPointArn, String appArn) {
    	this.setApplicationArn(appArn);
    	client.setEndpoint("https://sns." + this.region + ".amazonaws.com/");
        String salida = null;
        try {
            try {
            	// Desuscribir endpoint
        		//subscribe to an SNS topic         	
            	UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest().withSubscriptionArn(suscriptionArn);
        		client.unsubscribe(unsubscribeRequest);
            	
            	// Borrar endpoint
            	DeleteEndpointRequest deleteEndpointRequest = new DeleteEndpointRequest();
            	deleteEndpointRequest.setEndpointArn(endPointArn);
            	client.deleteEndpoint(deleteEndpointRequest);
            	
            	salida = "" + client.getCachedResponseMetadata(deleteEndpointRequest);
            	System.out.println("SubscribeRequest - " + client.getCachedResponseMetadata(deleteEndpointRequest));
           
            } catch (Exception ioe) {
            	ioe.printStackTrace();
                //System.exit(BatchCreatePlatformEndpointSample.FILE_ACCESS_FAILURE_ERROR_CODE);
            }
        } catch (AmazonServiceException ase) {
            System.err
            .println("<"
                    
                    + ">"
                    + "[ERROR] The endpoint could not be created because of an AmazonServiceException. "
                    + ase.getMessage());
        } catch (AmazonClientException ace) {
            System.err
            .println("<"
                    
                    + ">"
                    + "[ERROR] The endpoint could not be created because of an AmazonClientException. "
                    + ace.getMessage());
        } finally {
        	return salida;
        }	
    }
    
    @SuppressWarnings("finally")
	public Token suscribe(String tokenP, String userDataP, String appARNP, String topicArn) {
    	this.setApplicationArn(appARNP);
    	this.topicArn       = topicArn;
    	client.setEndpoint("https://sns." + this.region + ".amazonaws.com/");
        Token token = new Token();
        try {
            CreatePlatformEndpointResult createResult = client
                    .createPlatformEndpoint(new CreatePlatformEndpointRequest()
                            .withPlatformApplicationArn(appARNP)
                            .withToken(tokenP)
                            .withCustomUserData(userDataP));
            try {
                System.out.println("<>"
                        + "[SUCCESS] The endpoint was created with Arn "
                        + createResult.getEndpointArn());

                this.arn = createResult.getEndpointArn();
        		
        		//subscribe to an SNS topic
        		SubscribeRequest subRequest = new SubscribeRequest(topicArn, "application", createResult.getEndpointArn());
        		String subscriptionArn = client.subscribe(subRequest).getSubscriptionArn();
        		
        		//get request id for SubscribeRequest from SNS metadata
        		System.out.println("SubscribeRequest - " + subscriptionArn);
        		token.setArn_suscription(subscriptionArn);
        		token.setArn_topic(this.topicArn);
        		token.setArn_endpoint(this.arn);
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        } catch (AmazonServiceException ase) {
        	ase.printStackTrace();
            System.err
            .println("<"
                    
                    + ">"
                    + "[ERROR] The endpoint could not be created because of an AmazonServiceException. "
                    + ase.getMessage());
        } catch (AmazonClientException ace) {
        	ace.printStackTrace();
            System.err
            .println("<"
                    
                    + ">"
                    + "[ERROR] The endpoint could not be created because of an AmazonClientException. "
                    + ace.getMessage());
        } finally {
        	return token;
        }   	
    }

	public String getApplicationArn() {
		return applicationArn;
	}

	public void setApplicationArn(String applicationArn) {
		this.applicationArn = applicationArn;
	}

}
