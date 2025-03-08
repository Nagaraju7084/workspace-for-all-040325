package com.medi.preclinic.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediUserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int mediUserId;
	
	private String firstName;
	private String lastName;
	private String userId;
	private String mail;
	
	private String dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	
	private RoleDto role;
	
	private UserTypeDto userType;

}
