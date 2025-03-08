package com.medi.preclinic.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // to satisfy the pojo definition we taken noargconstructor annotation here
public class UserTypeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String userType;

}
