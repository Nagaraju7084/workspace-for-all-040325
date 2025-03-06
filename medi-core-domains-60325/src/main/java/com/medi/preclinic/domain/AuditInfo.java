package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AuditInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String createdBy;
	private Date createdDate;
	
	private String modifiedBy;
	private String modifiedDate;
	
}
