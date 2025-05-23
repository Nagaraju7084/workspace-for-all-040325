package com.medi.preclinic.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DoctorAvailableDays")
public enum DaysEnum {
	
	SUNDAY(1,"Sunday"),
	MONDAY(2,"Monday"),
	TUESDAY(3,"Tuesday"),
	WEDNESDAY(4,"Wednesday"),
	THURSDAY(5,"Thursday"),
	FRIDAY(6,"Friday"),
	SATURDAY(7,"Saturday");
	
	private int id;
	private String day;
	
	DaysEnum(int id, String day){
		this.id = id;
		this.day = day;
	}
	
	public static List<DaysEnum> findAllDays(){
		return Arrays.asList(DaysEnum.values());
	}

}
