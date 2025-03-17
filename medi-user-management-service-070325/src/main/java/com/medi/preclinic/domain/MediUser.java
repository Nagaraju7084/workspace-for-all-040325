package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "MediUser")
@Data
@Getter
public class MediUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mediUserId;
	
	private String firstName;
	private String lastName;
	private String userId;
	private String mail;
	
	@Temporal(value = TemporalType.DATE)
	private Date dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	
	@OneToOne
	private MediRole mediRole;
	
	//@OneToOne
	//private Address postalAddress;
	
	@OneToOne
	private MediUserType mediUserType;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public MediRole getMediRole() {
		return mediRole;
	}

	public void setMediRole(MediRole mediRole) {
		this.mediRole = mediRole;
	}

	public MediUserType getMediUserType() {
		return mediUserType;
	}

	public void setMediUserType(MediUserType mediUserType) {
		this.mediUserType = mediUserType;
	}
	
	//user can contains n number of documents and also get many notifications
	//here third table will be generated
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascade all = when the user deleted then documents also deleted softly
	//private Set<Document> documentsList;
	
	//here also third table will be generated and mediuser having association for these tables
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Set<Notification> notificationsList;
	
	//@OneToOne
	//private Department dept;
	
}
