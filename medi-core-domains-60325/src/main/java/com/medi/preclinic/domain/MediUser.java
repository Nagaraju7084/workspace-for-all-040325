package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "MediUser")
@Data
public class MediUser extends AuditInfo implements Serializable {

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
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	
	@OneToOne
	private Role role;
	
	@OneToOne
	private Address postalAddress;
	
	@OneToOne
	private UserType userType;
	
	//user can contains n number of documents and also get many notifications
	//here third table will be generated
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascade all = when the user deleted then documents also deleted softly
	private Set<Document> documentsList;
	
	//here also third table will be generated and mediuser having association for these tables
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Notification> notificationsList;

}
