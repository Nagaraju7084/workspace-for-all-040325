package com.medi.preclinic.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.MediUserManagementService070325ApplicationTests;
import com.medi.preclinic.dto.MediUserDto;

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
	
	/*
	 * for provisioning and deprovisioning
	*/
	static String   MEDI_USER_ROLE_ID = "1";
	static String   MEDI_USER_ID_PRIMARY = "1";
	
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
		mediUserDto.setDob(DOB);
	}
	
	@Disabled
	@Test
	public void createMediUser() {
		MediUserDto meUserDto = mediUserService.createUser(mediUserDto, null);
		assertNotNull(meUserDto);
		assertNotNull(meUserDto.getMediUserId());
	}
	
	@Disabled
	@Test
	public void testDeprovisioning() {
		MediUserDto mediUserDto = mediUserService.deprovisioningUser(MEDI_USER_ID_PRIMARY, MEDI_USER_ROLE_ID);
		assertNotNull(mediUserDto);
		assertNull(mediUserDto.getMediRoleDto());
	}
	
	//@Disabled
	@Test
	public void testProvisioning() {
		MediUserDto mediUserDto = mediUserService.provisioningUser(MEDI_USER_ID_PRIMARY, MEDI_USER_ROLE_ID);
		assertNotNull(mediUserDto);
		assertNotNull(mediUserDto.getMediRoleDto());
	}
}
