package com.medi.preclinic.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.medi.preclinic.util.ServiceUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomJwtTokenServiceJwtAuthnProject {
	
	@Autowired
	private CustomUserDetailsServiceJwtAuthnProject userDetailsService;
	
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
	
	public UserDetails validateToken(String tokenWithBearer) {
		boolean isTokenValid = false;
		String tokenWithoutBearer = resolveToken(tokenWithBearer);
		if(tokenWithoutBearer != null) {
			Claims userIdentities = Jwts.parser().setSigningKey(ServiceUtil.JWT_API_KEY).parseClaimsJws(tokenWithoutBearer).getBody();
				UserDetails loggedInUserDetails = userDetailsService.loadUserByUsername(userIdentities.getSubject());
				if(loggedInUserDetails != null) {
					Date tokenEpiryDate = userIdentities.getExpiration();
					isTokenValid = new Date().before(tokenEpiryDate);
					if(isTokenValid) {
						return loggedInUserDetails;
					}
				}
		}
		
		return null;
	}
	
	private String resolveToken(String tokenWithBearer) {
		String tokenWithoutBearer = null;
		if(tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
			tokenWithoutBearer = tokenWithBearer.replace(ServiceUtil.JWT_TOKEN_PREFIX, "");
		}
		return tokenWithoutBearer;
	}
}
