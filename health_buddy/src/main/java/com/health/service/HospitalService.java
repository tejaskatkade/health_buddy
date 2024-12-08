package com.health.service;

import java.util.List;

import com.health.reqdto.HospitalReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.HospitalResDto;

import jakarta.validation.Valid;

public interface HospitalService {

	List<HospitalResDto> getAllHospitals();

	ApiResponse addHospital(@Valid HospitalReqDto hospitalReqDto);

	String addDoctor(Long hospId, Long doctorId);

}
