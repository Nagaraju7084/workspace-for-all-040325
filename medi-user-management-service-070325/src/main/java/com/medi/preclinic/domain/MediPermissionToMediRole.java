package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "MediPermissionToMediRole")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediPermissionToMediRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private MediRole mediRole;
	
	@ManyToOne
	private MediPermission mediPermission;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MediRole getMediRole() {
		return mediRole;
	}

	public void setMediRole(MediRole mediRole) {
		this.mediRole = mediRole;
	}

	public MediPermission getMediPermission() {
		return mediPermission;
	}

	public void setMediPermission(MediPermission mediPermission) {
		this.mediPermission = mediPermission;
	}
	
}
