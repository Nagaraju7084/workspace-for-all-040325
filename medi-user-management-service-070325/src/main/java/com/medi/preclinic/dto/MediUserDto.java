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
	
	private MediRoleDto mediRoleDto;
	
	private MediUserTypeDto mediUserTypeDto;
	
	private boolean accountLocked = true;
	private boolean accountDisabled = true;

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isAccountDisabled() {
		return accountDisabled;
	}

	public void setAccountDisabled(boolean accountDisabled) {
		this.accountDisabled = accountDisabled;
	}

	public int getMediUserId() {
		return mediUserId;
	}

	public void setMediUserId(int mediUserId) {
		this.mediUserId = mediUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getShortBiography() {
		return shortBiography;
	}

	public void setShortBiography(String shortBiography) {
		this.shortBiography = shortBiography;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MediRoleDto getMediRoleDto() {
		return mediRoleDto;
	}

	public void setMediRoleDto(MediRoleDto mediRoleDto) {
		this.mediRoleDto = mediRoleDto;
	}

	public MediUserTypeDto getMediUserTypeDto() {
		return mediUserTypeDto;
	}

	public void setMediUserTypeDto(MediUserTypeDto mediUserTypeDto) {
		this.mediUserTypeDto = mediUserTypeDto;
	}
	
}
