/**
 * 
 */
package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.bean.MediUserBean;


/**
 * @author IM-LP-1763
 *
 */
public interface UserProfileService {

	public MediUserBean createUser(final MediUserBean UserBean);
	public MediUserBean findUserById(final String UserId);
	public MediUserBean updateUser(final MediUserBean UserBean);
	public List<MediUserBean> deleteUser(final MediUserBean UserBean);
	public List<MediUserBean> deleteUser(final String UserId);
	
	/**
	 * provisioning and deprovisioning operations
	 */
	public MediUserBean provisioningUser(String userId, String roleId);
	public MediUserBean deProvisioningUser(String userId, String roleId);
	
	public List<MediUserBean> findAllUsers();
	
}
