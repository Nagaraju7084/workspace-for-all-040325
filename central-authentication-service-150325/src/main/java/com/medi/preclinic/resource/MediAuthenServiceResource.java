package com.medi.preclinic.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/token")
	public String getUserInfo(HttpServletRequest request) {
		String headerValueWithBearer = request.getHeader("Authorization");
		if(headerValueWithBearer != null && headerValueWithBearer.startsWith("Bearer")) {
			String accessToken = headerValueWithBearer.replace("Bearer ", "");
			System.out.println("access token:\t"+accessToken);
			return iamAuthenService.viewUserProfile(accessToken);
		}
		return headerValueWithBearer;
	}

}
