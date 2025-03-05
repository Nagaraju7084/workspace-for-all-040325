package com.medi.preclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MediUserBean;
import com.medi.preclinic.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MediUserResource {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/users")
	public MediUserBean createUser(@RequestBody MediUserBean userBean) {
		return userProfileService.createUser(userBean);
	}
}
