/**
 * 
 */
package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.model.MedilabUserType;

/**
 * @author IM-LP-1763
 *
 */
public interface UserTypeService {

	public UserTypeBean createUserType(final UserTypeBean UserTypeBean);
	public UserTypeBean findUserTypeById(final String UserTypeId);
	public UserTypeBean updateUserType(final UserTypeBean UserTypeBean);
	public List<UserTypeBean> deleteUserType(final UserTypeBean UserTypeBean);
	public List<UserTypeBean> deleteUserType(final String UserTypeId);
	
	public List<UserTypeBean> findAllUserTypes();
	MedilabUserType mapBeanToDomain(UserTypeBean UserTypeBean);
	UserTypeBean mapDomainToBean(MedilabUserType UserTypeDomain);
}
