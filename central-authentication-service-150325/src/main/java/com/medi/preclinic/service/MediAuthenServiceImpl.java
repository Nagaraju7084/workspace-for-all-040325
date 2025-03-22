package com.medi.preclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.idpclient.OutboundCommunicator;

@Service
public class MediAuthenServiceImpl implements MediAuthenService {
	
	@Autowired
	private OutboundCommunicator iamClient;

	@Override
	public String authenticate(String username, String password) {
		return iamClient.authenticateId(username, password);
	}

	@Override
	public String renewToken(String refreshToken, String accessTokenToBeRenew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewUserProfile(String accessToken) {
		return iamClient.getUserInfo(accessToken);
	}

}
