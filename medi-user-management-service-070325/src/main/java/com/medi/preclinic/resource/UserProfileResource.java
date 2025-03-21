package com.medi.preclinic.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.service.MediUserService;

@RestController
@RequestMapping("/api/v1/")
public class UserProfileResource {
	
	@Autowired
	private MediUserService mediUserService;
	
	//@RequestMapping(value = "/users", method = RequestMethod.GET)
	@GetMapping(value = "/users")//works on only handler method but not class level
	public List<MediUserDto> findAllUsers(){
		return mediUserService.findAllUsers();
	}
	//@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	@GetMapping(value = "/users/{userId}")
	public MediUserDto findByUserId(@PathVariable("userId") String userId) {
		return mediUserService.findUserById(userId);
	}
	
	//@RequestMapping(value = "/users", method = RequestMethod.POST)
	@PostMapping(value = "/users")
	public MediUserDto createUser(@RequestBody MediUserDto mediUserDto, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl+"/verifyUser";
		return mediUserService.createUser(mediUserDto, callbackUrl);
	}
	
	//@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@PutMapping(value = "/users")
	public MediUserDto updateUser(@RequestBody MediUserDto mediUserDto, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl+"/verifyUser";
		return mediUserService.createUser(mediUserDto, callbackUrl);
	}
	
	//@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/users")
	public List<MediUserDto> deleteUser(@RequestBody  MediUserDto mediUserDto){
		return mediUserService.deleteUser(mediUserDto);
	}
	
	//@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/users/{userId}")
	public List<MediUserDto> deleteUserByUserId(@PathVariable("userId") String userId){
		return mediUserService.deleteUserById(userId);
	}
	
	@GetMapping(value = "/users/verifyUser/{code}")
	public ResponseEntity<String> verifyUser(@PathVariable("code") String code, HttpServletRequest request) {
		System.out.println(request.getServletPath()+"\tservlet path");
		System.out.println(request.getContextPath()+"\tcontext path");
		System.out.println(request.getRequestURL().toString()+"\trequest url");
		System.out.println(request.getScheme()+"\tget scheme");
		System.out.println(request.getServerPort()+"\tserver port");
		System.out.println(request.getServerName()+"\tserver name");
		System.out.println("verify user code is:\t" + code);
		
		JSONObject userVerifiedMessage = new JSONObject();
		boolean isUserVerified = mediUserService.verifyUser(code);
		userVerifiedMessage.put("isUserVerified", isUserVerified);
		return ResponseEntity.ok(userVerifiedMessage.toString());
	}
}
