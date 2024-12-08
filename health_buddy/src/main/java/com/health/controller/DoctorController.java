package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.reqdto.DoctorReqDto;
import com.health.resdto.ApiResponse;
import com.health.service.DoctorService;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/hosp/{hospId}")
	public ResponseEntity<?> getDoctorsByHospId(@PathVariable Long hospId) {

		return ResponseEntity.ok(doctorService.getDoctorsByHospital(hospId));
	}

	@PostMapping
	public ResponseEntity<ApiResponse> addDoctor(@RequestBody DoctorReqDto doctorReqDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addDoctor(doctorReqDto));
	}

	@GetMapping("/{doctorID}")
	public ResponseEntity<?> getDoctorById(@PathVariable Long doctorID) {
		return ResponseEntity.ok(doctorService.getDoctorById(doctorID));
	}

}
