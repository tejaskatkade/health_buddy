package com.health.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.DoctorRepository;
import com.health.Repository.HospitalRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.Doctor;
import com.health.entity.Hospital;
import com.health.reqdto.HospitalReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.HospitalResDto;
import com.health.service.HospitalService;

import jakarta.validation.Valid;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Override
	public List<HospitalResDto> getAllHospitals() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		return hospitals.stream().map((h)->modelMap.map(h,HospitalResDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public ApiResponse addHospital(HospitalReqDto hospitalReqDto) {
		Hospital save = hospitalRepository.save(modelMap.map(hospitalReqDto, Hospital.class));
		return new ApiResponse("Hospital Added id ::" + save.getId());
	}

	@Override
	public String addDoctor(Long hospId, Long doctorId) {
		Hospital hospital = hospitalRepository.findById(hospId).orElseThrow(()->new ResourceNotFoundException("Hospital", hospId));
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor", doctorId));
		hospital.getDoctor().add(doctor);
		//doctor.getHospitals().add(hospital);
		//hospitalRepository.save(hospital);
		return "Doctor added";
	}

}
