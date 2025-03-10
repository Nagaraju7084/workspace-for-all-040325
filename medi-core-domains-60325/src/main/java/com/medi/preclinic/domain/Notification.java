package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "Notification")
@Data
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int notificationId;
	
	private String title;
	
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date notificationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	private String createdBy;
	private boolean isSpecificToUser;
	private String lastModifiedBy;
	private boolean isActive = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "typeId")
	private NotificationType notificationType;
	

}
