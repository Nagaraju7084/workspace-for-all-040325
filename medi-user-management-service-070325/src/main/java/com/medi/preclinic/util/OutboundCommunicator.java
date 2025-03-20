package com.medi.preclinic.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author MAHA
 * this class will use a resttemplate to invoke the api call of the notification-service
 *
 */
public class OutboundCommunicator {
	
	//the method should not contain these many parameters, its not good programming practise rather
	//we can construct a json in service class that json we can trigger here
	//public static String sendAccountConfirmEmail(String from, String[] to, String[] bcc, String emailType, String callBackUrl, String verifyCode) {}
	public static String sendConfirmAccountEmail(String notificationData) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseJson = restTemplate.postForEntity("http://localhost:5011/api/v1.0/notifications", notificationData, String.class);
		System.out.println(responseJson.getBody());
		return null;
	}

}
