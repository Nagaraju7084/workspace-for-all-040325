package com.medi.preclinic;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medi.preclinic.domain.Permission;
import com.medi.preclinic.domain.PermissionToRole;
import com.medi.preclinic.domain.Role;
import com.medi.preclinic.repository.PermissionRepository;
import com.medi.preclinic.repository.RoleRepository;

@SpringBootApplication
public class MediCoreDomains60325Application implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MediCoreDomains60325Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Permission permission = new Permission();
		permission.setPermissionName("view");
		permission.setId(100);
		
		permissionRepository.save(permission);
		
		//added the data into the table manually, so below code is not required
		
		/*
		 * Role role = new Role(); role.setRoleName("admin");
		 * 
		 * PermissionToRole permissionToRole = new PermissionToRole();
		 * permissionToRole.setPermission(permission); permissionToRole.setRole(role);
		 * 
		 * Set<PermissionToRole> permissionToRoleSet = new HashSet<PermissionToRole>();
		 * permissionToRoleSet.add(permissionToRole);
		 * 
		 * role.setPermissionsSet(permissionToRoleSet);
		 * 
		 * roleRepository.save(role);
		 */
		
	}

}
