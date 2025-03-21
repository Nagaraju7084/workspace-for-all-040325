package com.medi.preclinic.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(notificationData, headers); // using this, we are sending the data
		ResponseEntity<String> responseJson = restTemplate.postForEntity(ServiceUtil.NOTIFICATION_SERVICE_URL, entity, String.class); //later on time we will make static constants into dynamic using yml file / properties file
		System.out.println(responseJson.getBody());
		return responseJson.getBody();
	}

}
