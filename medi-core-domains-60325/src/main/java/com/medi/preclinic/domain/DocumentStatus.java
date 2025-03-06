package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DocumentStatus")
@Data
public class DocumentStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "statusId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docStatusId;
	
	@Column(name = "status")
	private String statusName;

}
