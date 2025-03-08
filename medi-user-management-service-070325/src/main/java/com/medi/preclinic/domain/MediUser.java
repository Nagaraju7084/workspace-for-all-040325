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
