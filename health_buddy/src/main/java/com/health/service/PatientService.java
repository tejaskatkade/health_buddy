package com.health.service;

import java.util.List;

import com.health.reqdto.PatientReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.PatientResDto;

import jakarta.validation.Valid;

public interface PatientService {

	 List<PatientResDto>  getAllPatients();

	 PatientResDto getPatientById(Long patientId);

	String addPatient(PatientReqDto patientDto);

	String inActivatePatient(Long patientId);

	String activatePatient(Long patientId);

	ApiResponse updatePatient(Long patientId, @Valid PatientReqDto req);

}
