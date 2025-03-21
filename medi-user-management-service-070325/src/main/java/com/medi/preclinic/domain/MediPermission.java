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
@Table(name = "MediPermission")
@Data
public class MediPermission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, updatable = true)
	private String mediPermissionName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMediPermissionName() {
		return mediPermissionName;
	}

	public void setMediPermissionName(String mediPermissionName) {
		this.mediPermissionName = mediPermissionName;
	}
	
}
