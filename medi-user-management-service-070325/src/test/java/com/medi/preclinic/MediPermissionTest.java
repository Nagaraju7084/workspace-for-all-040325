package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.domain.MediPermission;
import com.medi.preclinic.domain.MediPermissionToMediRole;
import com.medi.preclinic.domain.MediRole;
import com.medi.preclinic.repository.MediPermissionRepository;
import com.medi.preclinic.repository.MediRoleRepository;

public class MediPermissionTest extends MediUserManagementService070325ApplicationTests {
	
	@Autowired
	private MediPermissionRepository permissionRepository;
	
	@Autowired
	private MediRoleRepository roleRepository;
	
	@Test
	public void createRole() {
		//initial time the below permissions should be save, once saved all 4 permissions no need to save again
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
		List<MediPermission> permissionsList = permissionRepository.findAll();
	    // Step 3: Create a new Role
	    MediRole nurseRole = new MediRole();
	    nurseRole.setMediRoleName("Nurse");
	    // Step 4: Create PermissionToRole relationships
	    List<MediPermissionToMediRole> permissionsSet = new ArrayList<>();
	    permissionsList.stream()
        .filter(permission -> permission.getMediPermissionName().equals("VIEW")
        		||
        		permission.getMediPermissionName().equals("WRITE"))
        .forEach(permission -> {
            MediPermissionToMediRole permissionToRole = new MediPermissionToMediRole();
            permissionToRole.setMediRole(nurseRole);
            permissionToRole.setMediPermission(permission);
            permissionsSet.add(permissionToRole);
        });
	    // Associate the permissionsSet with the Role
	    nurseRole.setMediPermissionsSet(permissionsSet);
	    // Step 5: Save the Role with its permissions
	    roleRepository.save(nurseRole);
	    // Step 6: Validate the saved Role and its permissions
	    assertNotNull("Role ID is null", String.valueOf(nurseRole.getId()));
	    assertEquals(2, nurseRole.getMediPermissionsSet().size(), "Permissions set size mismatch");
	}
}
