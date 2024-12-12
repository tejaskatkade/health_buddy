package com.health.service;

import java.util.List;

import com.health.reqdto.DoctorReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.DoctorResDto;

import jakarta.validation.Valid;

public interface DoctorService {

	ApiResponse addDoctor(DoctorReqDto doctorReqDto);

	List<DoctorResDto> getDoctorsByHospital(Long hospId);

	DoctorResDto getDoctorById(Long doctorID);

	List<DoctorResDto> getAllDoctors();

	ApiResponse updateDoctor(Long doctorId,DoctorReqDto doctorReqDto);

	ApiResponse inActivateDoctor(Long doctorId);

	List<DoctorResDto> getAllActiveDoctors();

	ApiResponse activateDoctor(Long doctorId);
	
}
