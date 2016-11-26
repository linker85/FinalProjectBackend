package com.proximate.www.aws;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class PorToken {
	static String ACCESS_KEY = "AKIAJXNFZMYPVXW5ZOKA";
	static String SECRET_KEY = "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";

	public static void main(String[] args) {
		// create a new SNS client and set endpoint
		AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
		snsClient.setRegion(Region.getRegion(Regions.US_WEST_1));

		String targetArn = "arn:aws:sns:us-west-1:846787489820:endpoint/APNS/UPAEP_EGRESADO_IOS/c1772bc5-b623-3531-9972-0bd6c725f4c4";


		String msg = "x token";
		String subject = "x token";
		PublishRequest publishRequest = new PublishRequest();
		publishRequest.setTargetArn(targetArn);
		publishRequest.setMessage(msg);
		publishRequest.setSubject(subject);
		PublishResult publishResult = snsClient.publish(publishRequest);
		// print MessageId of message published to SNS topic
		System.out.println("MessageId - " + publishResult.getMessageId());
	}

}