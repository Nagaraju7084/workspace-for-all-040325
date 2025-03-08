package com.medi.preclinic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.medi.preclinic.domain.MediPermissionToMediRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediRoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private String mediRoleName;

    private List<MediPermissionDto> mediPermissionsSet = new ArrayList<>();

}
