package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Permission")
@Data
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, unique = true, updatable = true)
	private String permissionName;
	
	/*
		audit information
	*/
	
	private String createdBy;
	private Date createdDate;
	
	private String modifiedBy;
	private Date modifiedDate;

}
