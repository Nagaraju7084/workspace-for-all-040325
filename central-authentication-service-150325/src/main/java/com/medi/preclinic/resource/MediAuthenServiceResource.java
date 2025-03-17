package com.medi.preclinic.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MediUser;
import com.medi.preclinic.service.MediAuthenService;

@RestController
@RequestMapping("/api")
public class MediAuthenServiceResource {
	
	@Autowired
	private MediAuthenService iamAuthenService;
	
	@PostMapping("/token")
	public String authenticateUser(@RequestBody MediUser mediUser) {
		return iamAuthenService.authenticate(mediUser.getUserName(), mediUser.getPassword());
	}

}
