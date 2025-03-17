package com.medi.preclinic.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VerificationCode")
public class VerificationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date codeGeneratedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date codeActiveDate;
	
	/**
	 * confirmation code validity is 4320 minutes by default i.e. 3 days
	 */
	private long codeValidity = 4320;
	
	private boolean codeVerified = false;
	
	public boolean isCodeVerified() {
		return codeVerified;
	}

	public void setCodeVerified(boolean codeVerified) {
		this.codeVerified = codeVerified;
	}

	@OneToOne
	private MediUser mediUserId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCodeGeneratedDate() {
		return codeGeneratedDate;
	}

	public void setCodeGeneratedDate(Date codeGeneratedDate) {
		this.codeGeneratedDate = codeGeneratedDate;
	}

	public Date getCodeActiveDate() {
		return codeActiveDate;
	}

	public void setCodeActiveDate(Date codeActiveDate) {
		this.codeActiveDate = codeActiveDate;
	}

	public long getCodeValidity() {
		return codeValidity;
	}

	public void setCodeValidity(long codeValidity) {
		this.codeValidity = codeValidity;
	}

	public MediUser getMediUserId() {
		return mediUserId;
	}

	public void setMediUserId(MediUser mediUserId) {
		this.mediUserId = mediUserId;
	}

}
