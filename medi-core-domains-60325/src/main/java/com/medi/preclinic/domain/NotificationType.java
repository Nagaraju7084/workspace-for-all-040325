package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
	this class describes the type of the notification i.e. email or sms
*/

@Entity
@Table(name = "NotificationType")
@Data
public class NotificationType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int typeId;
	
	private String notificationType;
}
