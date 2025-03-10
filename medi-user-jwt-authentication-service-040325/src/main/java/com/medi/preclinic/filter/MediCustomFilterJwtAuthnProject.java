package com.medi.preclinic.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medi.preclinic.config.CustomJwtTokenServiceJwtAuthnProject;
import com.medi.preclinic.util.ServiceUtil;

public class MediCustomFilterJwtAuthnProject extends OncePerRequestFilter {
	
	@Autowired
	private CustomJwtTokenServiceJwtAuthnProject tokenService;
	
	public MediCustomFilterJwtAuthnProject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenWithBearer = request.getHeader(ServiceUtil.JWT_REQUEST_HEADER_NAME);
		if(tokenWithBearer != null && tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
			UserDetails userDetails = tokenService.validateToken(tokenWithBearer);
			if(userDetails != null) { //user already existed
				//if token is valid, prepare authentication response object and set it into this security context holder
				//so that usernameandpasswordauthenticationtoken will take it and validate it
				//prepare / construct authentication response object
				Authentication authenticationResponse = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
						userDetails.getPassword(), userDetails.getAuthorities());
				//set authenticationresponse object into the securitycontextholder
				SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
				//then call the dofilter
				filterChain.doFilter(request, response);
			}else { //if userDetails null then construct jsonobject and put the error message and
				//send it to the constructor of badcredentialsexception
				JSONObject jsonBuilder = new JSONObject();
				jsonBuilder.put("error_message :", "token is invalid, please get the new token");
				throw new BadCredentialsException(jsonBuilder.toString());
			}
		}else {
			filterChain.doFilter(request, response);
		}
	}

}
