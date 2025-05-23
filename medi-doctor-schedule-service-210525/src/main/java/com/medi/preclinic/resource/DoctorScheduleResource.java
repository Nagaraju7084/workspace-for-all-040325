package com.medi.preclinic.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.dto.DoctorScheduleDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1.0")
@Slf4j
public class DoctorScheduleResource {
	
	@PostMapping(path = "/doctors-schedule", name = "createDoctorSchedule")
	public ResponseEntity<DoctorScheduleDto> createDoctorSchedule(
			@RequestBody DoctorScheduleDto doctorScheduleDto){
		log.info("schedule data coming to service is:\t" + doctorScheduleDto.toString());
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content geneation
		return ResponseEntity.ok(doctorScheduleDto);
	}
	
	@PutMapping(path = "/doctors-shcedule", name = "updateDoctorSchedule")
	public ResponseEntity<DoctorScheduleDto> updateDoctorSchedule(
			@RequestBody DoctorScheduleDto doctorScheduleDto){
		log.info("schedule data coming to service is:\t" + doctorScheduleDto);
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content generation
		return ResponseEntity.ok(doctorScheduleDto);
	}
	
	@GetMapping(path = "/doctors-shcedule/{shcedule-id}", name = "getDoctorSchedule")
	public ResponseEntity<DoctorScheduleDto> getDoctorSchedule(
			@PathVariable("shcedule-id") Long scheduleId){
		log.info("schedule id coming to service is:\t" + scheduleId);
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content generation
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(path = "/doctors-shcedule/{doctor-id}", name = "getDoctorScheduleByName")
	public ResponseEntity<List<DoctorScheduleDto>> getDoctorScheduleByName(
			@PathVariable("doctor-id") Long doctorId){
		log.info("doctor id coming to service is:\t" + doctorId);
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content generation
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(path = "/doctors-shcedule", name = "findAllDoctorSchedule")
	public ResponseEntity<List<DoctorScheduleDto>> findAllDoctorSchedule(){
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content generation
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 
	 * @param scheduleId
	 * @return
	 * 
	 * once after a schedule is deleted with the supplied schedule id
	 * the service provides the remaining schedules irrespective of the doctors
	 */
	@DeleteMapping(path = "/doctors-shcedule/{schedule-id}", name = "deleteDoctorSchedules")
	public ResponseEntity<List<DoctorScheduleDto>> deleteDoctorSchedules(
			@PathVariable("schedule-id") Long scheduleId){
		// todo
		// 1.data validation
		// 2.service integration
		// 3.exception handling
		// 4.response content generation
		return ResponseEntity.ok(null);
	}

}
