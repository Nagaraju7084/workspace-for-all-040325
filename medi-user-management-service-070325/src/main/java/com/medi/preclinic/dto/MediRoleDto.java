package com.medi.preclinic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediRoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private int id;

    private String mediRoleName;

    private List<MediPermissionDto> mediPermissionsSet = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMediRoleName() {
		return mediRoleName;
	}

	public void setMediRoleName(String mediRoleName) {
		this.mediRoleName = mediRoleName;
	}

	public List<MediPermissionDto> getMediPermissionsSet() {
		return mediPermissionsSet;
	}

	public void setMediPermissionsSet(List<MediPermissionDto> mediPermissionsSet) {
		this.mediPermissionsSet = mediPermissionsSet;
	}
    
}
