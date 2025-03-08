package com.medi.preclinic.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.domain.MediRole;
import com.medi.preclinic.domain.MediUser;
import com.medi.preclinic.domain.MediUserType;
import com.medi.preclinic.dto.MediRoleDto;
import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.dto.MediUserTypeDto;
import com.medi.preclinic.repository.MediUserRepository;
import com.medi.preclinic.service.MediRoleService;
import com.medi.preclinic.service.MediUserService;
import com.medi.preclinic.service.MediUserTypeService;
import com.medi.preclinic.util.ServiceUtil;

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

	/*
	 * there are two cases in provisioning
	 * 1.user has already role is there but increase / promotion with the role i.e.
	 * user having role = 1 called nurse, that user promoted to role = 7 i.e. manager
	 * which is coming from ui
	 * 2.user doesn't have any role, we can assign the role which coming from ui
	*/
	@Override
	public MediUserDto provisioningUser(String userId, String roleId) {
		// provisioning is applied as soon as user is creating
		MediUser databaseMediUser = mediUserRepository.findById(Integer.valueOf(userId)).get();
		//we can't assign the role to the user becuase while creating the users by admin the role will assign
//		if(databaseMediUser != null) {
//			MediRole databaseRole = databaseMediUser.getMediRole();
//			if(databaseRole == null) {
//				databaseMediUser.setMediRole(mediRoleService.dtoToDomain(mediRoleService.findRoleById(roleId)));
//			}
//		}
		if(databaseMediUser != null) {
			MediRole databaseRole = databaseMediUser.getMediRole();
			if(databaseRole != null && databaseRole.getId() != Integer.valueOf(roleId)) {
				MediRoleDto promotedRoleDto = mediRoleService.findRoleById(roleId);
				databaseMediUser.setMediRole(mediRoleService.dtoToDomain(promotedRoleDto));
			}
		}
		return domainToDto(mediUserRepository.save(databaseMediUser));
	}
	
	/*
	 * revoking a role to user called deprovisioning
	*/
	@Override
	public MediUserDto deprovisioningUser(String userId, String roleId) {
		// first get the userid specific entity from the db, so we will get the role that
		//roleid(from user entity) is equals to this parameter roleid then
		//update the user entity be deleting the role
		MediUser databaseMediUser = mediUserRepository.findById(Integer.valueOf(userId)).get();
		if(databaseMediUser != null) {
			MediRole databaseMediRole = databaseMediUser.getMediRole();
			//write some better way for checking role equality
			if(databaseMediRole != null && databaseMediRole.getId() == Integer.valueOf(roleId)) {
				databaseMediUser.setMediRole(null);
			}
		}
		return domainToDto(mediUserRepository.save(databaseMediUser));
	}
	
	private MediUser dtoToDomain(MediUserDto mediUserDto) {
		MediUser mediUser = new MediUser();
		BeanUtils.copyProperties(mediUserDto, mediUser);
		try {
			mediUser.setDob(ServiceUtil.convertStringToDate(mediUserDto.getDob()));
		} catch (ParseException e) {
			// todo : convert to user defined exception later on time
			e.printStackTrace();
		}
		/**
		 * auto provisioning while creating the user
		 */
		MediRoleDto mediRoleDto = mediUserDto.getMediRoleDto();
		if(mediRoleDto != null) {
			MediRole mediRole = mediRoleService.dtoToDomain(mediRoleDto);
			mediUser.setMediRole(mediRole); //provisioning a perticular role to a perticular user
		}
		MediUserTypeDto mediUserTypeDto = mediUserDto.getMediUserTypeDto();
		if(mediUserTypeDto != null) {
			MediUserType mediUserType = mediUserTypeService.dtoToDomain(mediUserTypeDto);
			mediUser.setMediUserType(mediUserType);
		}
		return mediUser;
	}
	
	private MediUserDto domainToDto(MediUser mediUser) {
		MediUserDto mediUserDto = new MediUserDto();
		BeanUtils.copyProperties(mediUser, mediUserDto);
		mediUserDto.setDob(mediUser.getDob().toString());
		MediRole mediRole = mediUser.getMediRole();
		if(mediRole != null) {
			MediRoleDto mediRoleDto = mediRoleService.domainToDto(mediRole);
			mediUserDto.setMediRoleDto(mediRoleDto);
		}
		MediUserType mediUserType = mediUser.getMediUserType();
		if(mediUserType != null) {
			MediUserTypeDto mediUserTypeDto = mediUserTypeService.domainToDto(mediUserType);
			mediUserDto.setMediUserTypeDto(mediUserTypeDto);
		}
		return mediUserDto;
	}
}
