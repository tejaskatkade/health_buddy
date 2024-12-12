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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.reqdto.DoctorReqDto;
import com.health.resdto.ApiResponse;
import com.health.service.DoctorService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
@Validated
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping
	public ResponseEntity<?> getAllDoctors() {

		return ResponseEntity.ok(doctorService.getAllDoctors());
	}
	@GetMapping("/active")
	public ResponseEntity<?> getActiveDoctors() {
		
		return ResponseEntity.ok(doctorService.getAllActiveDoctors());
	}
	@GetMapping("/hosp/{hospId}")
	public ResponseEntity<?> getDoctorsByHospId(@PathVariable Long hospId) {
		
		return ResponseEntity.ok(doctorService.getDoctorsByHospital(hospId));
	}


	@GetMapping("/{doctorID}")
	public ResponseEntity<?> getDoctorById(@PathVariable Long doctorID) {
		return ResponseEntity.ok(doctorService.getDoctorById(doctorID));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addDoctor(@RequestBody @Valid DoctorReqDto doctorReqDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addDoctor(doctorReqDto));
	}
	
	@PutMapping("/{doctorId}")
	public ResponseEntity<ApiResponse> updateDoctor(@PathVariable Long doctorId, @RequestBody @Valid DoctorReqDto doctorReqDto) {
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(doctorId, doctorReqDto));
	}
	
	// inactivate doctor
	@PatchMapping("/inactivate/{doctorId}")
	public ResponseEntity<ApiResponse> inActivateDoctor(@PathVariable Long doctorId) {
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.inActivateDoctor(doctorId));
	}
	
	@PatchMapping("/activate/{doctorId}")
	public ResponseEntity<ApiResponse> activateDoctor(@PathVariable Long doctorId) {
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.activateDoctor(doctorId));
	}
	

}
