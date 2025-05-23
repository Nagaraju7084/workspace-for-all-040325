package com.medi.preclinic.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DoctorScheduleDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long scheduleId;
	private String doctorName;
	private List<String> availableDays;
	
	/**
	 * "dd/mm/yyyy HH:mm"
	 */
	private String startTime;
	private String endTime;
	private String message;
	private boolean scheduleStatus = true;
	
}
