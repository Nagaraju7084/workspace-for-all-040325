package com.medi.preclinic.service;

public interface MediAuthenService {
	
	public String authenticate(String username, String password);
	public String renewToken(String refreshToken, String accessTokenToBeRenew);
	public String validateToken(String accessToken);
	public String viewUserProfile(String accessToken);

}
