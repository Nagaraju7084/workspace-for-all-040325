package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.dto.MediUserDto;

public interface MediUserService {
	
	//whatever the data coming from / captured in controller layer as it is should send to service layer
	//data should not be modify in the middle so that we taken final to the parameter of the method
	public MediUserDto createUser(final MediUserDto mediUserDto);
	public MediUserDto createUserById(final String mediUserDtoId);
	public MediUserDto updateUser(final MediUserDto mediUserDto);
	public List<MediUserDto> deleteUser(final MediUserDto mediUserDto);
	public List<MediUserDto> deleteUserById(final String mediUserDtoId);
	public MediUserDto findUserById(String userId);
	
	public List<MediUserDto> findAllUsers();
	
	//provisioning and deprovisioning operations
	public MediUserDto provisioningUser(String userId, String roleId); //allocating a particular role(roleId) to a particular user(userId)
	public MediUserDto deprovisioningUser(String userId, String roleId);//deallocating a particular role(roleId) from a particular user(userId)
}
