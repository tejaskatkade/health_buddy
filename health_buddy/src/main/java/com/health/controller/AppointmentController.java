package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.reqdto.AppointmentReqDto;
import com.health.resdto.ApiResponse;
import com.health.service.AppointmentService;

@CrossOrigin
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	ResponseEntity<?> bookAppointment(@RequestBody AppointmentReqDto appointmentDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.bookAppointment(appointmentDto));
	}
	
	@GetMapping
	ResponseEntity<?> getAllAppointments(){
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointments());	
	}

	@GetMapping("/{id}") 
	ResponseEntity<?> getAppointment(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointment(id));	
	}

	@GetMapping("/patient/{patientId}")
	ResponseEntity<?> getAppointmentByPatient(@PathVariable Long patientId){
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentsBypatientId(patientId));	
	}
	
	@GetMapping("/doctor/{doctorId}")
	ResponseEntity<?> getAppointmentsByDoctorId(@PathVariable Long doctorId){
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentsByDoctorId(doctorId));	
	}

	@GetMapping("/hosp/{hospId}")
	ResponseEntity<?> getAppointmentsByhospitalId(@PathVariable Long hospId){
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentsByHospitalId(hospId));	
	}
	
	@PatchMapping("/cancle/{appointId}")
	ResponseEntity<?> cancleAppointment(@PathVariable Long appointId){
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(appointmentService.cancleAppointment(appointId)));	
	}

	@PatchMapping("/complete/{appointId}")
	ResponseEntity<?> completedAppointment(@PathVariable Long appointId){
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(appointmentService.completeAppointment(appointId)));	
	}

}
