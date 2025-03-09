package com.medi.preclinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServiceUtil {

	//user dob constant
	private static final String UI_USER_DOB_DATE_FORMAT = "dd/MM/yyyy";
	
	//role constants
	public static final String DOCTOR_ROLE = "Doctor";
	public static final String ADMIN_ROLE = "Admin";
	
	//constants used for jwt token generation
	public static final long JWT_TOKEN_EXPIRY = 1800000L; //in seconds
	public static final String JWT_API_KEY = "token-service"; 
	public static final String JWT_TOKEN_ISSUER = "token-service";
	public static final String JWT_REQUEST_HEADER_NAME = "Authorization";
	public static final String JWT_TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_TYPE = "Bearer";
	
	public static Date convertStringToDate(String uiDob) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(UI_USER_DOB_DATE_FORMAT);
		return sdf.parse(uiDob);
	}
	
}
