package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping
	public ResponseEntity<?> addHospital(@RequestBody @Valid HospitalReqDto hospitalReqDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addHospital(hospitalReqDto));
	}
	
	@PostMapping("/{hospId}/doctor/{doctorId}")
	public ResponseEntity<?> addDoctorInHospital(@PathVariable Long hospId, @PathVariable Long doctorId){
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.addDoctor(hospId,doctorId));
	}
}
