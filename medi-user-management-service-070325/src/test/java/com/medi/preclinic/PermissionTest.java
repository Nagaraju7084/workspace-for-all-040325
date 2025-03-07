package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
	public void createRole() {
	    // Step 1: Set up test data for permissions
//	    Permission readPermission = new Permission();
//	    readPermission.setPermissionName("READ");
//	    permissionRepository.save(readPermission); // Save to repository
//
//	    Permission writePermission = new Permission();
//	    writePermission.setPermissionName("WRITE");
//	    permissionRepository.save(writePermission);
//	    
//	    Permission viewPermission = new Permission();
//	    viewPermission.setPermissionName("VIEW");
//	    permissionRepository.save(viewPermission); // Save to repository
//
//	    Permission deletePermission = new Permission();
//	    deletePermission.setPermissionName("DELETE");
//	    permissionRepository.save(deletePermission); // Save to repository
//
//	    List<Permission> permissionsList = permissionRepository.findAll();
//
//	    // Step 2: Add assertions to validate permissions data
//	    assertNotNull("Permissions list is null", permissionsList.toString());
//	    assertEquals(4, permissionsList.size(), "Expected 4 permissions in the list");
		
		List<Permission> permissionsList = permissionRepository.findAll();
	    // Step 3: Create a new Role
	    Role role = new Role();
	    role.setRoleName("Doctor");

	    // Step 4: Create PermissionToRole relationships
	    List<PermissionToRole> permissionsSet = new ArrayList<>();

	    for (Permission permission : permissionsList) {
	        PermissionToRole permissionToRole = new PermissionToRole();
	        permissionToRole.setRole(role);
	        permissionToRole.setPermission(permission);
	        permissionsSet.add(permissionToRole);
	    }

	    // Associate the permissionsSet with the Role
	    role.setPermissionsSet(permissionsSet);

	    // Step 5: Save the Role with its permissions
	    roleRepository.save(role);

	    // Step 6: Validate the saved Role and its permissions
	    assertNotNull("Role ID is null", String.valueOf(role.getId()));
	    assertEquals(permissionsList.size(), role.getPermissionsSet().size(), "Permissions set size mismatch");
	}
}
