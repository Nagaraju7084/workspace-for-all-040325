package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.dto.RoleDto;

public interface RoleService {
	//what methods we can write here
	//create role, delete role, edit role, provisioning users to the role
	//deprovisioning users to the role
	
	//create role
	public RoleDto createRole(final RoleDto roleDto);
	
	//find role by id
	public RoleDto findRoleById(final String roleId);
	
	//edit or update role
	public RoleDto updateRole(final RoleDto roleDto);
	
	//delete can be happened based on the entire roledto object
	public RoleDto deleteRole(final RoleDto roleDto);
	
	//delete can be happened based on the roleid
	public RoleDto deleteRoleById(final String roleId);
	
	//provisioning and deprovisioning operations
	//provisioning operations
	//provisioning meaning assigning a user to a particular role
	//or assigning a role to a particular user
	public RoleDto provisioningUser(String userId, String roleId); //assigning a particularrole(roleId) to the particularuser(userId) 
	
	//deprovisioning operations
	//deprovisioning meaning deallocating a user from a particular role
	//or deallocating a role from a particular user
	public RoleDto deProvisioningUser(String userId, String roleId); //deallocating / removing a particularrole(roleId) to the particularuser(userId)
	
	//listing out the available roles / all roles
	public List<RoleDto> findAllRoles();
}
