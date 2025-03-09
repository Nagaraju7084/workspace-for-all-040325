package com.medi.preclinic.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medi.preclinic.model.MediUser;
import com.medi.preclinic.model.UserRoleToPermission;
import com.medi.preclinic.repository.MediUserRepository;

@Component
public class CustomUserPermissionsJwtAuthnProject implements PermissionEvaluator {
	
	@Autowired
	private MediUserRepository mediUserRepository;

	/**
	 * the user coming from ui / any client who trying to do one of the permissions
	 * such as create, view, modify, delete should have to check whether he have or not
	 * to check the permissions we want hasPermission(-,-,-) method given by PermissionEvaluator interface given by spring
	 * this method will execute automatically by using @EnableGlobalMethodSecurity(prePostEnabled = true) and @PreAuthorize
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		//loggedInUser from ui / any client(postman) / swagger ui
		String loggedInUser = authentication.getName(); //principal = name and credentials = password and authorities = permissions
		System.out.println("loggedInUser :\t" + loggedInUser);
		
		String moduleName = (String)targetDomainObject;
		System.out.println("moduleName :\t" + moduleName);
		
		String permissionName = (String)permission;
		System.out.println("permissionName :\t" + permissionName);
		
		MediUser databaseUser = mediUserRepository.findUserByEmail(loggedInUser);
		if(databaseUser != null && null != databaseUser.getUserRole()) {
			if(moduleName.equalsIgnoreCase(databaseUser.getUserRole().getName())) {
				for(UserRoleToPermission userRoleToPermission : databaseUser.getUserRole().getPermissonsSet()) {
					return permissionName.equalsIgnoreCase(userRoleToPermission.getUserPermission().getName());
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
