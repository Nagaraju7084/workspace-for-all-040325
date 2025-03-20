package com.medi.preclinic.notification.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int notificationId;  
	
	private String from;
	private String[] to;
	private String[] bcc;
	private String body;
	
	private String emailTemplateType;
	
	private String callbackUrl;
	private String userVerificationCode;
	

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getUserVerificationCode() {
		return userVerificationCode;
	}

	public void setUserVerificationCode(String userVerificationCode) {
		this.userVerificationCode = userVerificationCode;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEmailTemplateType() {
		return emailTemplateType;
	}

	public void setEmailTemplateType(String emailTemplateType) {
		this.emailTemplateType = emailTemplateType;
	}
	
}
