package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.dto.UserTypeDto;

public interface UserType {
	
	public UserTypeDto createUserType(final UserTypeDto userTypeDto);
	public UserTypeDto findUserTypeById(final String userTypeId);
	public UserTypeDto updateUserType(final UserTypeDto userTypeDto);
	public UserTypeDto deleteUserType(final UserTypeDto userTypeDto);
	public UserTypeDto deleteUserTypeById(final String userTypeId);
	
	public List<UserTypeDto> findAllUserTypes();
}
