package com.health.service;

import java.util.List;

import com.health.reqdto.PatientReqDto;
import com.health.resdto.PatientResDto;

public interface PatientService {

	 List<PatientResDto>  getAllPatients();

	 PatientResDto getPatientById(Long patientId);

	String addPatient(PatientReqDto patientDto);

	String inActivatePatient(Long patientId);

	String activatePatient(Long patientId);

}
