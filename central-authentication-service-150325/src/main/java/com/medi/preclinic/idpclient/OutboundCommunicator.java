package com.medi.preclinic.idpclient;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OutboundCommunicator {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${wso2.iam.client-id}")
	private String clientId;
	
	@Value("${wso2.iam.client-secret}")
	private String clientSecret;
	
	@Value("${wso2.iam.grant_type}")
	private String grantType;
	
	@Value("${wso2.iam.scope}")
	private String scope;
	
	@Value("${wso2.iam.token.endpoint}")
	private String tokenEndpoint;
	
	@Value("${wso2.iam.userinfo.endpoint}")
	private String userEndpoint;
	
	public String authenticateId(String username, String password) {
		String passPhrase = clientId+":"+clientSecret;
		String encodedHash = new String(Base64.getEncoder().encode(passPhrase.getBytes()));
		
		String authnBody = "grant_type="+grantType+"&username="+username+"&password="+password+"&scope="+scope;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic "+encodedHash);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		
		HttpEntity requestData = new HttpEntity(authnBody, headers);
		//call ignoreCertificates() here
		ignoreCertificates();
		//we can use any of the java client instead of resttemplate
		ResponseEntity<String> iamResponse = restTemplate.postForEntity(tokenEndpoint, requestData, String.class);
		if(iamResponse.getStatusCodeValue()==HttpStatus.OK.value()) {
			System.out.println(iamResponse.getBody()); //extract the token and return back
			String respBody = iamResponse.getBody(); //json response, to parse it, we nee org.json maven dependency

			JSONObject jsonResponseBody = new JSONObject(respBody);
			//String accessToken = jsonResponseBody.getString("access_token");

			return jsonResponseBody.toString();
		}
		
		return null;
	}
	
	//testcase will fail due to the ssl certificate while writing test case so to disable ssl, we need the below method
	//https://stackoverflow.com/questions/4072585/disabling-ssl-certificate-validation-in-spring-resttemplate
	private void ignoreCertificates() {
	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }

	        @Override
	        public void checkClientTrusted(X509Certificate[] certs, String authType) {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] certs, String authType) {
	        }
	    } };

	    try {
	        SSLContext sc = SSLContext.getInstance("TLS");
	        sc.init(null, trustAllCerts, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (Exception e) {
	     

	    }
	}    
}
