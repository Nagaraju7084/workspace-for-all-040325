package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "AvailableDays")
@Data
public class AvailableDays implements Serializable {
	/**
	 * its kind of a master data, its loads at application bootstrap / startup, this data
	 * will be dumped into the table, to make a relation / to have this scheduling available
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String day;
	
	private String createdBy;
	
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
}
