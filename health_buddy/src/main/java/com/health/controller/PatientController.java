package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.reqdto.PatientReqDto;
import com.health.resdto.ApiResponse;
import com.health.service.PatientService;

import jakarta.validation.Valid;

@CrossOrigin
@Validated
@RequestMapping("/patient")
@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable Long patientId) {
		return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(patientId));
	}
	
	@PostMapping
	public ResponseEntity<?> addPatient(@RequestBody @Valid PatientReqDto patientDto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApiResponse(patientService.addPatient(patientDto)));
	}
	
	@PatchMapping("/inactivate/{patientId}")
	public ResponseEntity<?> inActivatePatient(@PathVariable Long patientId) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApiResponse(patientService.inActivatePatient(patientId)));
	}
	@PatchMapping("/activate/{patientId}")
	public ResponseEntity<?> activatePatient(@PathVariable Long patientId) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApiResponse(patientService.activatePatient(patientId)));
	}
	
}
