package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "Appointment")
@Data
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "patient")
	private MediUser patient;
	
	@OneToOne
	@JoinColumn(name = "department")
	private Department department;
	
	@OneToOne
	@JoinColumn(name = "doctor")
	private MediUser doctor;
	
	private String date;
	
	private String time;
	
	private String message;
	
	private String status;
}
