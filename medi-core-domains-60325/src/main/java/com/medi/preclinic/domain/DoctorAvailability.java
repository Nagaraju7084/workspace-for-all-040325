package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "DoctorAvailability")
@Data
public class DoctorAvailability implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "scheduleId")
	private DoctorSchedule doctorSchedule;
	
	@ManyToOne
	@JoinColumn(name = "availableDays")
	private AvailableDays availableDays;
	
	@ManyToOne
	@JoinColumn(name = "availableTimings")
	private AvailableDays availableTimings;
	
}
