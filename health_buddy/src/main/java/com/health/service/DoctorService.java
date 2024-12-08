package com.health.service;

import java.util.List;

import com.health.reqdto.DoctorReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.DoctorResDto;

public interface DoctorService {

	ApiResponse addDoctor(DoctorReqDto doctorReqDto);

	List<DoctorResDto> getDoctorsByHospital(Long hospId);

	DoctorResDto getDoctorById(Long doctorID);
	
}
