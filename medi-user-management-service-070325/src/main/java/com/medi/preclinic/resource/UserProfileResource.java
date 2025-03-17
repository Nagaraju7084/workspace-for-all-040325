package com.medi.preclinic.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.service.MediUserService;

@Controller
@RequestMapping("/api/v1/")
public class UserProfileResource {
	
	@Autowired
	private MediUserService mediUserService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<MediUserDto> findAllUsers(){
		return mediUserService.findAllUsers();
	}
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public @ResponseBody MediUserDto findByUserId(@PathVariable("userId") String userId) {
		return mediUserService.findUserById(userId);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody MediUserDto createUser(@RequestBody MediUserDto mediUserDto) {
		return mediUserService.createUser(mediUserDto);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public @ResponseBody MediUserDto updateUser(@RequestBody MediUserDto mediUserDto) {
		return mediUserService.updateUser(mediUserDto);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	public @ResponseBody MediUserDto deleteUser(@RequestBody  MediUserDto mediUserDto){
		return mediUserService.deleteUser(mediUserDto);
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody MediUserDto deleteUserByUserId(@PathVariable("userId") String userId){
		return mediUserService.deleteUserById(userId);
	}
}
