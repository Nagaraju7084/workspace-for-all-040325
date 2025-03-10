package com.medi.preclinic.service.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.MedilabUserBean;
import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.model.MedilabUser;
import com.medi.preclinic.model.MedilabUserType;
import com.medi.preclinic.model.UserRole;
import com.medi.preclinic.repo.MedilabUserRepository;
import com.medi.preclinic.service.UserProfileService;
import com.medi.preclinic.service.UserRoleService;
import com.medi.preclinic.service.UserTypeService;
import com.medi.preclinic.util.ServiceUtil;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private MedilabUserRepository userRepo;
	
	@Autowired
	private UserRoleService roleService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public MedilabUserBean createUser(MedilabUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public MedilabUserBean findUserById(String UserId) {
		return mapDomainToBean(userRepo.findById(Integer.valueOf(UserId)).get());

	}

	@Override
	public MedilabUserBean updateUser(MedilabUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public List<MedilabUserBean> deleteUser(MedilabUserBean userBean) {
		userRepo.delete(mapBeanToDomain(userBean));
		return findAllUsers();
	}

	@Override
	public List<MedilabUserBean> deleteUser(String userId) {
		userRepo.deleteById(Integer.valueOf(userId));
		return findAllUsers();
	}

	@Override
	public MedilabUserBean provisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() != Integer.valueOf(roleId)) {
				
				UserRoleBean promotedRoleBean = roleService.findRoleById(roleId);
				userDomain.setRole(roleService.mapBeanToDomain(promotedRoleBean));
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	/**
	 * revoking a role to a user is called deprovisioning
	 */
	@Override
	public MedilabUserBean deProvisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() == Integer.valueOf(roleId)) {
				userDomain.setRole(null);
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	@Override
	public List<MedilabUserBean> findAllUsers() {
		List<MedilabUser> userDomainList = userRepo.findAll();
		List<MedilabUserBean> userBeanList = new ArrayList<>();
		if(userDomainList != null && userDomainList.size() >0) {
			userDomainList.stream().forEach(userDomain->{
				userBeanList.add(mapDomainToBean(userDomain));
			});
		}
		
		return userBeanList;
	}
	
	private MedilabUser mapBeanToDomain(MedilabUserBean userBean) {
		
		MedilabUser userDomain = new MedilabUser();
		BeanUtils.copyProperties(userBean, userDomain);
		userDomain.setPassword(pwdEncoder.encode(userBean.getPassword()));
		String inputDob = userBean.getDob();
		try {
			userDomain.setDob(ServiceUtil.convertStringToDate(inputDob));
		} catch (ParseException e) {
			//Todo: convert to user definided exception later on time
			e.printStackTrace();
		}
		
		/**
		 * auto provisioning while creating user
		 */
		UserRoleBean userRoleBean = userBean.getRole();
		if(userRoleBean != null) {
			UserRole roleDomain = roleService.mapBeanToDomain(userRoleBean);
			userDomain.setRole(roleDomain);
		}
		
		UserTypeBean userTypeBean = userBean.getUserType();
		if(userTypeBean != null) {
			MedilabUserType userType = userTypeService.mapBeanToDomain(userTypeBean);
			userDomain.setUserType(userType);
		}
		
		return userDomain;
	}
	
	private MedilabUserBean mapDomainToBean(MedilabUser user) {
		MedilabUserBean userBean = new MedilabUserBean();
		BeanUtils.copyProperties(user, userBean);
		
		Date dobDate = user.getDob();
		if(dobDate != null) {
			String dob = dobDate.toString();
			userBean.setDob(dob);
		}
		
		
		UserRole userRoleDomain = user.getRole();
		if(userRoleDomain != null) {
			UserRoleBean roleBean = roleService.mapDomainToBean(userRoleDomain);
			userBean.setRole(roleBean);
		}
		
		MedilabUserType userTypeDomain = user.getUserType();
		if(userTypeDomain != null) {
			UserTypeBean userTypeBean = userTypeService.mapDomainToBean(userTypeDomain);
			userBean.setUserType(userTypeBean);
		}
		
		
		return userBean;
	}
	

}
