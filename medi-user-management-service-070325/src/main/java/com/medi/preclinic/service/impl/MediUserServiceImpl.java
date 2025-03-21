package com.medi.preclinic.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.domain.MediRole;
import com.medi.preclinic.domain.MediUser;
import com.medi.preclinic.domain.MediUserType;
import com.medi.preclinic.domain.VerificationCode;
import com.medi.preclinic.dto.MediRoleDto;
import com.medi.preclinic.dto.MediUserDto;
import com.medi.preclinic.dto.MediUserTypeDto;
import com.medi.preclinic.repository.MediUserRepository;
import com.medi.preclinic.repository.VerificationCodeRepository;
import com.medi.preclinic.service.MediRoleService;
import com.medi.preclinic.service.MediUserService;
import com.medi.preclinic.service.MediUserTypeService;
import com.medi.preclinic.util.OutboundCommunicator;
import com.medi.preclinic.util.ServiceUtil;

@Service
public class MediUserServiceImpl implements MediUserService {
	
	@Autowired
	private MediUserRepository mediUserRepository;
	
	@Autowired
	private MediRoleService mediRoleService;
	
	@Autowired
	private MediUserTypeService mediUserTypeService;
	
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;

	@Override
	public MediUserDto createUser(MediUserDto mediUserDto, String callbackUrl) {
		MediUser userDomain = mediUserRepository.save(dtoToDomain(mediUserDto));
		JSONObject confirmAccountJsonBody = new JSONObject();
		if(userDomain != null && userDomain.getMediUserId() >0) {
			//generate a verification code
			VerificationCode vCode = new VerificationCode();
			vCode.setCode(RandomStringUtils.randomAlphabetic(15));
			vCode.setCodeGeneratedDate(new Date());
			vCode.setMediUser(userDomain);
			verificationCodeRepository.save(vCode);
			confirmAccountJsonBody.put("userVerifyCode", vCode.getCode());
		}
		
		//we have to call the notification-service to send an email
		//who will be loggedin to the system to do this activity
		//that person(we can get from security context holder) name will come as from
		confirmAccountJsonBody.put("from", "");
		confirmAccountJsonBody.put("to", new String[] {userDomain.getMail()});
		confirmAccountJsonBody.put("emailTemplateType", "AccountConfirmation");
		confirmAccountJsonBody.put("callbackUrl", callbackUrl);
		OutboundCommunicator.sendConfirmAccountEmail(confirmAccountJsonBody.toString());
		
		
		return domainToDto(userDomain);
	}

	@Override
	public MediUserDto createUserById(String mediUserDtoId) {
		return domainToDto(mediUserRepository.findById(Integer.valueOf(mediUserDtoId)).get());
	}

	@Override
	public MediUserDto updateUser(MediUserDto mediUserDto) {
		return domainToDto(mediUserRepository.save(dtoToDomain(mediUserDto)));
	}

	@Override
	public List<MediUserDto> deleteUser(MediUserDto mediUserDto) {
		mediUserRepository.delete(dtoToDomain(mediUserDto));
		return findAllUsers();
	}

	@Override
	public List<MediUserDto> deleteUserById(String mediUserDtoId) {
		mediUserRepository.findById(Integer.valueOf(mediUserDtoId));
		return findAllUsers();
	}

	@Override
	public List<MediUserDto> findAllUsers() {
		return mediUserRepository.findAll().stream().map(user -> domainToDto(user)).collect(Collectors.toList());
	}
	
	@Override
	public MediUserDto findUserById(String userId) {
		return domainToDto(mediUserRepository.findById(Integer.valueOf(userId)).get());
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

	@Override
	public boolean verifyUser(String verificationCode) {
		boolean isCodeVerified = false;
		VerificationCode verificationDomain = verificationCodeRepository.findByVerificationCode(verificationCode); //step 1 : get the verification domain
		if(!verificationDomain.isCodeVerified()) { //setp 2 : if the given code is verified then only enter into the loop
			MediUser mediDomainUser = verificationDomain.getMediUser(); //step 3 : get the user
			if(mediDomainUser != null && mediDomainUser.getMail() != null) { //step 4 : mediDomainUser not null and that user mail also not null
				mediDomainUser.setAccountDisabled(false); //then only set the account disabled = false and
				mediDomainUser.setAccountLocked(false);//set the account locked = false
				verificationDomain.setCodeVerified(true);
				verificationDomain.setCodeActiveDate(new Date());
				verificationDomain = verificationCodeRepository.save(verificationDomain);
				if(verificationDomain.isCodeVerified()) {
					isCodeVerified = true;
				}
			}
		}else {
			//throw a user friendly exception saying that
			//isCodeVerified user is already verified
			isCodeVerified = true;
		}
		return isCodeVerified;
	}
	
	

}
