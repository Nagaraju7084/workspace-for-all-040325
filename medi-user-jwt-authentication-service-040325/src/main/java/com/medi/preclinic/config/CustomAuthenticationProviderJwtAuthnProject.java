package com.medi.preclinic.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProviderJwtAuthnProject implements AuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsServiceJwtAuthnProject customUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loggedInUser = authentication.getName();
		System.out.println("loggedInUser :\t" + loggedInUser);
		UserDetails loggedInUserDetails = customUserDetailsService.loadUserByUsername(loggedInUser);
		Authentication authenticationResponse = null;
		if(loggedInUserDetails != null) {
			boolean isPasswordMatched = passwordEncoder.matches(String.valueOf(authentication.getCredentials()), loggedInUserDetails.getPassword());
			if(isPasswordMatched) {
				authenticationResponse = new UsernamePasswordAuthenticationToken(loggedInUser, null, loggedInUserDetails.getAuthorities());
			}
		}
		return authenticationResponse;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
