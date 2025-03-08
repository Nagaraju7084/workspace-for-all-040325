package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.domain.MediRole;
import com.medi.preclinic.dto.MediRoleDto;

public interface MediRoleService {
	//what methods we can write here
	//create role, delete role, edit role, provisioning users to the role
	//deprovisioning users to the role
	
	//create role
	public MediRoleDto createRole(final MediRoleDto roleDto);
	
	//find role by id
	public MediRoleDto findRoleById(final String roleId);
	
	//edit or update role
	public MediRoleDto updateRole(final MediRoleDto roleDto);
	
	//delete can be happened based on the entire roledto object
	public List<MediRoleDto> deleteRole(final MediRoleDto roleDto);
	
	//delete can be happened based on the roleid
	public List<MediRoleDto> deleteRoleById(final String roleId);
	
	//provisioning and deprovisioning operations
	//provisioning operations
	//provisioning meaning assigning a user to a particular role
	//or assigning a role to a particular user
	public MediRoleDto provisioningUser(String userId, String roleId); //assigning a particularrole(roleId) to the particularuser(userId) 
	
	//deprovisioning operations
	//deprovisioning meaning deallocating a user from a particular role
	//or deallocating a role from a particular user
	public MediRoleDto deProvisioningUser(String userId, String roleId); //deallocating / removing a particularrole(roleId) to the particularuser(userId)
	
	//listing out the available roles / all roles
	public List<MediRoleDto> findAllRoles();
	
	MediRole dtoToDomain(MediRoleDto mediRoleDto);
	MediRoleDto domainToDto(MediRole mediRole); // these conversion methods required for other classes as well so we kept here and overrid in implementation class i.e. MediRoleServiceImpl
}
