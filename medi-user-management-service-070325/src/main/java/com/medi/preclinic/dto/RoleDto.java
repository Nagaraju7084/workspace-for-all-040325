package com.medi.preclinic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private String roleName;

    private List<PermissionDto> permissionsSet = new ArrayList<>();

}
