package com.medi.preclinic.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String permissionName;

}
