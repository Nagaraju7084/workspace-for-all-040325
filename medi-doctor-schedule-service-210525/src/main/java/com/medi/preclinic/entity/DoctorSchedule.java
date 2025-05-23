package com.medi.preclinic.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private long scheduleId;
	
	@Column(nullable = false, unique = true, updatable = false)
	private String doctorName;
	
	@Column(nullable = false, unique = true)
	private List<String> availableDays;
	
	/**
	 * "dd/mm/yyyy HH:mm"
	 */
	@Column(nullable = false, unique = true)
	private String startTime;
	
	@Column(nullable = false, unique = true)
	private String endTime;
	private String message;
	
	/**
	 * database stores its values as
	 * 0: if the schedule status as false
	 * 1: if the schedule status as true
	 */
	private boolean scheduleStatus = true;
}
