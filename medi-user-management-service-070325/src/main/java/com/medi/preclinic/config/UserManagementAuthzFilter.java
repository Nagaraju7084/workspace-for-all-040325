package com.medi.preclinic.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserManagementAuthzFilter extends OncePerRequestFilter {
	
	@Autowired
	private OutboundCommunicator outboundCommunicator;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headerValueWithBearer = request.getHeader("Authorization");
		if(headerValueWithBearer != null && headerValueWithBearer.startsWith("Bearer")) {
			String accessToken = headerValueWithBearer.replace("Bearer ", "");
			System.out.println("access token:\t"+accessToken);
			if(accessToken != null) {
				List<GrantedAuthority> rolesList = new ArrayList<>();
				//central authentication api to get the used details from token
				String userResp = outboundCommunicator.getUserInfo(accessToken);
				JSONObject jsonResp = new JSONObject(userResp);
				String loggedInUser = jsonResp.getString("user");
				String authorities = jsonResp.getString("groups"); //authorities are comma seperated
				
				if(authorities != null) {
					String[] rolesArr = authorities.split(",");
					for(String role : rolesArr) {
						rolesList.add(new SimpleGrantedAuthority(role));
					}
				}
				
				Authentication authnResponse = new UsernamePasswordAuthenticationToken(loggedInUser, null, rolesList);//this is dummy, it should replaced with original user details in usernameandpasswordauthenticationtoken
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
