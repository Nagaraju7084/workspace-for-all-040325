package com.medi.preclinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceUtil {
	
	private static final String UI_USER_DOB_DATE_FORMAT = "dd/MM/yyyy";
	
	public static Date convertStringToDate(String uiDob) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UI_USER_DOB_DATE_FORMAT);
		return simpleDateFormat.parse(uiDob);
	}
}
