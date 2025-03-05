package com.medi.preclinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.model.MediUserType;
import com.medi.preclinic.repository.MediUserTypeRepository;
import com.medi.preclinic.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	private MediUserTypeRepository userTypeRepo;
	
	@Override
	public UserTypeBean createUserType(UserTypeBean UserTypeBean) {
		MediUserType userTypeDomain = userTypeRepo.save(mapBeanToDomain(UserTypeBean));
		return mapDomainToBean(userTypeDomain);
	}

	@Override
	public UserTypeBean findUserTypeById(String UserTypeId) {
		Optional<MediUserType> userTypeOptional = userTypeRepo.findById(Integer.valueOf(UserTypeId));
		//MedilabUserType userTypeDomain = userTypeOptional.isPresent():userTypeOptional.get():userTypeOptional.orElseThrow());
		MediUserType userTypeDomain = userTypeOptional.get();
		return mapDomainToBean(userTypeDomain);
	}

	@Override
	public UserTypeBean updateUserType(UserTypeBean UserTypeBean) {
		MediUserType userTypeDomain = userTypeRepo.save(mapBeanToDomain(UserTypeBean));
		return mapDomainToBean(userTypeDomain);
	}

	@Override
	public List<UserTypeBean> deleteUserType(UserTypeBean UserTypeBean) {
		userTypeRepo.delete(mapBeanToDomain(UserTypeBean));
		return findAllUserTypes();
	}

	@Override
	public List<UserTypeBean> deleteUserType(String UserTypeId) {
		userTypeRepo.deleteById(Integer.valueOf(UserTypeId));
		return findAllUserTypes();
	}

	@Override
	public List<UserTypeBean> findAllUserTypes() {
		List<UserTypeBean> userTypeBeanList = new ArrayList<>();
		
		List<MediUserType> userTypeList = userTypeRepo.findAll();
		
		if(userTypeList != null && userTypeList.size() >0) {
			userTypeList.stream().forEach(userTypeDomain->{
				userTypeBeanList.add(mapDomainToBean(userTypeDomain));
			});
		}
		
		return userTypeBeanList;
	}
	
	/**
	 * converting bean to domain
	 * @param UserTypeBean
	 * @return
	 */
	@Override
	public MediUserType mapBeanToDomain(UserTypeBean UserTypeBean) {
		
		MediUserType userTypeDomain = new MediUserType();
		BeanUtils.copyProperties(UserTypeBean, userTypeDomain);
		return userTypeDomain;
	}
   
	/**
	 * domain back to bean
	 * @param UserTypeBean
	 * @return
	 */
	@Override
	  public UserTypeBean mapDomainToBean(MediUserType UserTypeDomain) {
		  UserTypeBean userTypeBean = new UserTypeBean();
		  BeanUtils.copyProperties(UserTypeDomain, userTypeBean);
		return userTypeBean;
	}
	  
	  

}
