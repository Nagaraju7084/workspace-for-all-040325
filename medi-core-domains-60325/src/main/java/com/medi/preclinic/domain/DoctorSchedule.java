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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Table(name = "DoctorSchedule")
@Data
public class DoctorSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "doctorId")
	private MediUser doctor;
	
	//if we won't written mappedby, then third table will be created, its not correct as per our
	//design, the relation will be our own created third table i.e. doctor availability
	//to see the pictures, go to the ms word document
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
			mappedBy = "doctorSchedule") //lazy because first doctorschedule should create later doctorsavailability
	private Set<DoctorAvailability> doctorsAvailability;
	
	private String startTime;
	
	private String endTime;
	
	private String message;
	
	private String scheduledStatus;
	
	private String createdBy;
	
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
}
