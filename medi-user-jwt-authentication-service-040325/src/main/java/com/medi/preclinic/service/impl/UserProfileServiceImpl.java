package com.medi.preclinic.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.MediUserBean;
import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.model.MediUser;
import com.medi.preclinic.model.MediUserType;
import com.medi.preclinic.model.UserRole;
import com.medi.preclinic.repository.MediUserRepository;
import com.medi.preclinic.service.UserProfileService;
import com.medi.preclinic.service.UserRoleService;
import com.medi.preclinic.service.UserTypeService;
import com.medi.preclinic.util.ServiceUtil;


@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private MediUserRepository userRepo;
	
	@Autowired
	private UserRoleService roleService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MediUserBean createUser(MediUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public MediUserBean findUserById(String UserId) {
		return mapDomainToBean(userRepo.findById(Integer.valueOf(UserId)).get());

	}

	@Override
	public MediUserBean updateUser(MediUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public List<MediUserBean> deleteUser(MediUserBean userBean) {
		userRepo.delete(mapBeanToDomain(userBean));
		return findAllUsers();
	}

	@Override
	public List<MediUserBean> deleteUser(String userId) {
		userRepo.deleteById(Integer.valueOf(userId));
		return findAllUsers();
	}

	@Override
	public MediUserBean provisioningUser(String userId, String roleId) {
		MediUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getUserRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() != Integer.valueOf(roleId)) {
				
				UserRoleBean promotedRoleBean = roleService.findRoleById(roleId);
				userDomain.setUserRole(roleService.mapBeanToDomain(promotedRoleBean));
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	/**
	 * revoking a role to a user is called deprovisioning
	 */
	@Override
	public MediUserBean deProvisioningUser(String userId, String roleId) {
		MediUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getUserRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() == Integer.valueOf(roleId)) {
				userDomain.setUserRole(null);
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	@Override
	public List<MediUserBean> findAllUsers() {
		List<MediUser> userDomainList = userRepo.findAll();
		List<MediUserBean> userBeanList = new ArrayList<>();
		if(userDomainList != null && userDomainList.size() >0) {
			userDomainList.stream().forEach(userDomain->{
				userBeanList.add(mapDomainToBean(userDomain));
			});
		}
		
		return userBeanList;
	}
	
	private MediUser mapBeanToDomain(MediUserBean userBean) {
		
		MediUser userDomain = new MediUser();
		BeanUtils.copyProperties(userBean, userDomain);
		userDomain.setPassword(passwordEncoder.encode(userBean.getPassword()));
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
			userDomain.setUserRole(roleDomain);
		}
		
		UserTypeBean userTypeBean = userBean.getUserType();
		if(userTypeBean != null) {
			MediUserType userType = userTypeService.mapBeanToDomain(userTypeBean);
			userDomain.setUserType(userType);
		}
		
		return userDomain;
	}
	
	private MediUserBean mapDomainToBean(MediUser user) {
		MediUserBean userBean = new MediUserBean();
		BeanUtils.copyProperties(user, userBean);
		
		Date dobDate = user.getDob();
		if(dobDate != null) {
			String dob = dobDate.toString();
			userBean.setDob(dob);
		}
		
		
		UserRole userRoleDomain = user.getUserRole();
		if(userRoleDomain != null) {
			UserRoleBean roleBean = roleService.mapDomainToBean(userRoleDomain);
			userBean.setRole(roleBean);
		}
		
		MediUserType userTypeDomain = user.getUserType();
		if(userTypeDomain != null) {
			UserTypeBean userTypeBean = userTypeService.mapDomainToBean(userTypeDomain);
			userBean.setUserType(userTypeBean);
		}
		
		
		return userBean;
	}
	

}
