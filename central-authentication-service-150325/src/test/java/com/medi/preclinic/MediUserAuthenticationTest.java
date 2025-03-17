package com.medi.preclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.idpclient.OutboundCommunicator;

public class MediUserAuthenticationTest extends CentralAuthenticationService150325ApplicationTests {
	
	@Autowired
	private OutboundCommunicator outboundCommunicator;
	
	String username = null;
	String password = null;
	
	@BeforeEach
	public void init() {
		username = "charles";
		password="charles";
	}
	
	@Test
	public void iamAuthentication() {
		outboundCommunicator.authenticateId(username, password);
	}

}
