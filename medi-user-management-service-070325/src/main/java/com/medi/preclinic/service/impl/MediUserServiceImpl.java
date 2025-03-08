package com.medi.preclinic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.domain.MediUser;
import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.repository.MediUserRepository;
import com.medi.preclinic.service.MediRoleService;
import com.medi.preclinic.service.MediUserService;
import com.medi.preclinic.service.MediUserTypeService;

@Service
public class MediUserServiceImpl implements MediUserService {
	
	@Autowired
	private MediUserRepository mediUserRepository;
	
	@Autowired
	private MediRoleService mediRoleService;
	
	@Autowired
	private MediUserTypeService mediUserTypeService;

	@Override
	public MediUserDto createUser(MediUserDto mediUserDto) {
		return domainToDto(mediUserRepository.save(dtoToDomain(mediUserDto)));
	}

	@Override
	public MediUserDto createUserById(String mediUserDtoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediUserDto updateUser(MediUserDto mediUserDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediUserDto deleteUser(MediUserDto mediUserDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediUserDto deleteUserById(String mediUserDtoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediUserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediUserDto provisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediUserDto deprovisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private MediUser dtoToDomain(MediUserDto mediUserDto) {
		MediUser mediUser = new MediUser();
		BeanUtils.copyProperties(mediUserDto, mediUser);
		mediUser.setDob(new Date(mediUserDto.getDob()));
		
		mediUser.setMediRole(mediRoleService.dtoToDomain(mediUserDto.getMediRoleDto()));
		mediUser.setMediUserType(mediUserTypeService.dtoToDomain(mediUserDto.getMediUserTypeDto()));
		
		return mediUser;
	}
	
	private MediUserDto domainToDto(MediUser mediUser) {
		MediUserDto mediUserDto = new MediUserDto();
		BeanUtils.copyProperties(mediUser, mediUserDto);
		mediUserDto.setDob(mediUser.getDob().toString());
		
		mediUserDto.setMediRoleDto(mediRoleService.domainToDto(mediUser.getMediRole()));
		mediUserDto.setMediUserTypeDto(mediUserTypeService.domainToDto(mediUser.getMediUserType()));
		
		return mediUserDto;
	}
}
