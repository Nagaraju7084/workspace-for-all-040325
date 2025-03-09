package com.medi.preclinic.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MediUserBean;
import com.medi.preclinic.config.CustomJwtTokenServiceJwtAuthnProject;
import com.medi.preclinic.service.UserProfileService;
import com.medi.preclinic.util.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MediUserResource {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private CustomJwtTokenServiceJwtAuthnProject tokenService;
	
	@PostMapping("/users")
	public MediUserBean createUser(@RequestBody MediUserBean userBean) {
		System.out.println("user is creating");
		return userProfileService.createUser(userBean);
	}
	
	@GetMapping("/user/authn")
	public String authenticate() {
		//user has already authenticated by spring security so we are getting that authenticated user
		//by using authenticated username(email) and password, we are calling the createtoken method of jwttokenservice
		//after generating the token as string, we are represinting the string to json by using jsonobject given by org.json library
		Authentication authenticationedUser = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserName = authenticationedUser.getName();
		
		Collection<? extends GrantedAuthority> rolesList = authenticationedUser.getAuthorities();
		List<String> authorities = new ArrayList<>();
		if(rolesList != null && rolesList.size() > 0) {
			for(GrantedAuthority ga : rolesList) {
				authorities.add(ga.getAuthority());
			}
		}
		
		String token = tokenService.createToken(loggedInUserName, authorities);
		
		JSONObject jsonBuilder = new JSONObject();
		jsonBuilder.put("access_key", token);
		jsonBuilder.putOnce("type", ServiceUtil.JWT_TOKEN_TYPE);
		
		return jsonBuilder.toString();
	}
}
