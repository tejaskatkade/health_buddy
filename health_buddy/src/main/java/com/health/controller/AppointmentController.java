package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.health.entity.Appointment;
import com.health.reqdto.AppointmentReqDto;
import com.health.service.AppointmentService;

@RequestMapping("/appointment")
@Controller
public class AppointmentController {
	
	/*
	 * @Autowired private AppointmentService appointmentService;
	 * 
	 * ResponseEntity<?> bookAppointment(@RequestBody AppointmentReqDto
	 * appointmentDto){
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.
	 * bookAppointment(appointmentDto)); }
	 */
}
