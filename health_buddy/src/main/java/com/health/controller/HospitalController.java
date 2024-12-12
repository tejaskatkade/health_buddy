package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.reqdto.HospitalReqDto;
import com.health.service.HospitalService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/hospital")
@Validated
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping
	public ResponseEntity<?> getAllHospitals(){
		return ResponseEntity.ok(hospitalService.getAllHospitals());
	}
	@GetMapping("/active")
	public ResponseEntity<?> getActiveHospitals(){
		return ResponseEntity.ok(hospitalService.getActiveHospitals());
	}
	
	@PostMapping
	public ResponseEntity<?> addHospital(@RequestBody @Valid HospitalReqDto hospitalReqDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addHospital(hospitalReqDto));
	}
	@PutMapping("/{hospId}")
	public ResponseEntity<?> updateHospital(@PathVariable Long hospId,@RequestBody @Valid HospitalReqDto hospitalReqDto){
		
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.updateHospital(hospId, hospitalReqDto));
	}
	
	@PostMapping("/{hospId}/doctor/{doctorId}")
	public ResponseEntity<?> addDoctorInHospital(@PathVariable Long hospId, @PathVariable Long doctorId){
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.addDoctor(hospId,doctorId));
	}

	@DeleteMapping("/{hospId}/doctor/{doctorId}")
	public ResponseEntity<?> removeDoctorFromHospital(@PathVariable Long hospId, @PathVariable Long doctorId){
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.removeDoctor(hospId,doctorId));
	}
	
	@PatchMapping("/activate/{hospId}")
	public ResponseEntity<?> activateHospital(@PathVariable Long hospId){
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.activateHospital(hospId));
	}
	
	@PatchMapping("/inActivate/{hospId}")
	public ResponseEntity<?> inActivateHospital(@PathVariable Long hospId){
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.inActivateHospital(hospId));
	}
}
