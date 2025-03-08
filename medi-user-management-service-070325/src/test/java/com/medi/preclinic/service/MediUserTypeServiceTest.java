package com.medi.preclinic.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.MediUserManagementService070325ApplicationTests;
import com.medi.preclinic.dto.MediUserTypeDto;

public class MediUserTypeServiceTest extends MediUserManagementService070325ApplicationTests {
	
	@Autowired
	private MediUserTypeService mediUserTypeService;
	
	//here you should use testschema in database but don't go with acutal schema
	
	//note : any test case should not return anything and also should not take any input
	
	@Test
	public void testCreateUserType() {
		MediUserTypeDto mediUserTypeDto = new MediUserTypeDto(); //assume form data / data coming from ui
		mediUserTypeDto.setMediUserType("doctor"); //doctor is created
		
		//this mediusertypedto giving to service and service will return back same mediusertypedto to the controller
		mediUserTypeDto = mediUserTypeService.createUserType(mediUserTypeDto); //service will return the response to the controller
		
		assertNotNull(mediUserTypeDto); // this bean coming from service should not be null
		assertNotNull(mediUserTypeDto.getId()); // id should be there
		
		
		
	} 
}
