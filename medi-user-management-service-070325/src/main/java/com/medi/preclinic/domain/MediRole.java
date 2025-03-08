package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "MediRole")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mediRoleName;

    @OneToMany(mappedBy = "mediRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediPermissionToMediRole> mediPermissionsSet = new ArrayList<>();

}
