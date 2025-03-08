package com.medi.preclinic.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.MediUserManagementService070325ApplicationTests;
import com.medi.preclinic.dto.MediRoleDto;
import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.dto.MediUserTypeDto;

public class MediUserServiceTest extends MediUserManagementService070325ApplicationTests {
	
	@Autowired
	private MediUserService mediUserService;
	
	MediUserDto mediUserDto = null;
	
	static String	FIRSTNAME = "charles";
	static String	LASTNAME = "james";
	static String	USERID = "charlesJ";
	static String	MAIL = "charles@gmail.com";
	static String	DOB = "09/10/1974";
	static String	GENDER = "Male";
	static String	SHORTBIOGRAPHY = "founder of it";
	static boolean	STATUS = false;
	static String	MEDI_ROLE = "";
	static String 	MEDI_TYPE = "";
	
	@BeforeEach
	public void init() {
		mediUserDto = new MediUserDto();
		mediUserDto.setFirstName(FIRSTNAME);
		mediUserDto.setLastName(LASTNAME);
		mediUserDto.setMail(MAIL);
		mediUserDto.setUserId(USERID);
		mediUserDto.setGender(GENDER);
		mediUserDto.setShortBiography(SHORTBIOGRAPHY);
		mediUserDto.setStatus(STATUS);
	}
	
	public void createMediUser() {
		MediUserDto meUserDto = mediUserService.createUser(mediUserDto);
		assertNotNull(meUserDto);
		assertNotNull(meUserDto.getMediUserId());
	}
}
