package com.medi.preclinic.util;

/**
 * 
 * @author SRI LALITHA DEVI
 * lombok annotations are not work on the enum
 *
 */
public enum EmailTemplates {
	ACCOUNT_CONFIRMATION("AccountConfirmation", "confirmAccount.html"),
	ACCOUNT_ENABLE("AccountEnable",""),
	PASSWORD_RESET("PasswordReset","Password.html");
	
	private String templateType;
	private String templateName;
	
	EmailTemplates(String templateType, String templateName) {
		this.templateType = templateType;
		this.templateName = templateName;
	}
	
	public static String getTemplateName(String templateType) {
		for(EmailTemplates emailTemplate : EmailTemplates.values()) {
			if(emailTemplate.templateType.equals(templateType)) {
				return emailTemplate.templateName;
			}
		}
		return null;
	}
}
