package com.medi.preclinic.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class NotificationAuthzFilter extends OncePerRequestFilter {
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headerValueWithBearer = request.getHeader("Authorization");
		if(headerValueWithBearer != null && headerValueWithBearer.startsWith("Bearer")) {
			String accessToken = headerValueWithBearer.replace("Bearer ", "");
			System.out.println("access token:\t"+accessToken);
			if(accessToken != null) {
				
				Authentication authnResponse = new UsernamePasswordAuthenticationToken("user", null, Arrays.asList(new SimpleGrantedAuthority("admin")));//this is dummy, it should replaced with original user details in usernameandpasswordauthenticationtoken
				SecurityContextHolder.getContext().setAuthentication(authnResponse);
				filterChain.doFilter(request, response);
			}else {
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}else {
			response.sendError(HttpStatus.FORBIDDEN.value());
		}
	}

}
