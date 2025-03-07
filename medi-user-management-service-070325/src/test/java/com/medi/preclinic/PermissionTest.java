package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.domain.Permission;
import com.medi.preclinic.domain.PermissionToRole;
import com.medi.preclinic.domain.Role;
import com.medi.preclinic.repository.PermissionRepository;
import com.medi.preclinic.repository.RoleRepository;

public class PermissionTest extends MediUserManagementService070325ApplicationTests {
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void createPermission() {
		String name = "add";
		String createdBy = "admin";
		LocalDate localDate = LocalDate.now();
		Date createdDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Permission permission = new Permission(name, createdBy, createdDate, createdBy, createdDate);
		permissionRepository.save(permission);
		
		assertNotNull(permission.getId());
		
		List<Permission> permissionsList = permissionRepository.findAll();
		System.out.println(permissionsList.size());
		assertNotNull(permissionsList);
		
		Role role = new Role();
		role.setRoleName("mediAdmin");
		
		Set<PermissionToRole> permissionsSet = new HashSet<>();
		
		if(permissionsList != null && permissionsList.size() > 0) {
			permissionsList.stream().forEach(perm ->{
				PermissionToRole permissionToRole = new PermissionToRole();
				permissionToRole.setPermission(perm);
				permissionToRole.setRole(role);
				permissionsSet.add(permissionToRole);
			});
		}
		role.setPermissionsSet(permissionsSet);
		roleRepository.save(role);
		assertNotNull(role.getId());
		
	}
	
	/*
	 * @Test public void createRole() { List<Permission> permissionsList =
	 * permissionRepository.findAll(); System.out.println(permissionsList.size());
	 * assertNotNull(permissionsList);
	 * 
	 * Role role = new Role(); role.setRoleName("mediAdmin");
	 * 
	 * Set<PermissionToRole> permissionsSet = new HashSet<>();
	 * 
	 * if(permissionsList != null && permissionsList.size() > 0) {
	 * permissionsList.stream().forEach(permission ->{ PermissionToRole
	 * permissionToRole = new PermissionToRole();
	 * permissionToRole.setPermission(permission); permissionToRole.setRole(role);
	 * permissionsSet.add(permissionToRole); }); }
	 * role.setPermissionsSet(permissionsSet); roleRepository.save(role);
	 * assertNotNull(role.getId());
	 * 
	 * }
	 */
}
