package com.medi.preclinic.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediPermissionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String mediPermissionName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMediPermissionName() {
		return mediPermissionName;
	}

	public void setMediPermissionName(String mediPermissionName) {
		this.mediPermissionName = mediPermissionName;
	}
	
}
