package com.medi.preclinic.model;

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

@Entity
@Table(name = "MediUser")
@Data
public class MediUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dob;
	
	private String gender;
	private String shortBiography;
	private String password;
	private boolean status;
	
	@OneToOne
	private UserRole userRole;
	
	@OneToOne
	private MediUserType userType;

}
