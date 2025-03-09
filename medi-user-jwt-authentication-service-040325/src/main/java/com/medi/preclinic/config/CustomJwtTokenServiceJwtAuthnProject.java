package com.medi.preclinic.config;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.medi.preclinic.util.ServiceUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomJwtTokenServiceJwtAuthnProject {
	
	public String createToken(String loggedInUser, List<String> roles) {
		//build the claims first using jjwt library
		Date currentServerTime = new Date();
		
		Claims identities = Jwts.claims();
		
		identities.setSubject(loggedInUser);
		identities.setIssuer(ServiceUtil.JWT_TOKEN_ISSUER);
		identities.setIssuedAt(currentServerTime);
		identities.setExpiration(new Date(currentServerTime.getTime()+ServiceUtil.JWT_TOKEN_EXPIRY));
		
		identities.put("USER_ROLES", roles);
		
		return Jwts
				.builder()
				.setClaims(identities)
				.signWith(SignatureAlgorithm.HS256, ServiceUtil.JWT_API_KEY)
				.compact();
	}
}
