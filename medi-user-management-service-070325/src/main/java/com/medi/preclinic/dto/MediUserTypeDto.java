package com.medi.preclinic.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // to satisfy the pojo definition we taken noargconstructor annotation here
public class MediUserTypeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String mediUserType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMediUserType() {
		return mediUserType;
	}

	public void setMediUserType(String mediUserType) {
		this.mediUserType = mediUserType;
	}
	
}
