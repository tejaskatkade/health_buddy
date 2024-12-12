package com.health.service.impl;

import java.util.List;
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
	public List<HospitalResDto> getActiveHospitals() {
		return hospitalRepository.findAll()
			.stream()
			.filter((hosp)->hosp.getIsActive() == true)
			.map((h)->modelMap.map(h,HospitalResDto.class))
			.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addHospital(HospitalReqDto hospitalReqDto) {
		Hospital save = hospitalRepository.save(modelMap.map(hospitalReqDto, Hospital.class));
		return new ApiResponse("Hospital Added id ::" + save.getId());
	}
	
	@Override
	public ApiResponse updateHospital(Long hospId, HospitalReqDto hospitalReqDto) {
		Hospital hospital = hospitalRepository.findById(hospId).orElseThrow(()->new ResourceNotFoundException("Hospital", hospId));
		//Hospital hospital2 = modelMap.map(hospitalReqDto, Hospital.class);
		//hospital.setId(hospId);
		hospital.setName(hospitalReqDto.getName());
		hospital.setLocation(hospitalReqDto.getLocation());
		hospital.setContact(hospitalReqDto.getContact());	
		
		Hospital save = hospitalRepository.save(hospital);
		return new ApiResponse("Hospital Updated id ::" + save.getId());
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
	@Override
	public String removeDoctor(Long hospId, Long doctorId) {
		Hospital hospital = hospitalRepository.findById(hospId).orElseThrow(()->new ResourceNotFoundException("Hospital", hospId));
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor", doctorId));
		//hospital.getDoctor().add(doctor);
		if(hospital.getDoctor().remove(doctor)) {
			return "Doctor removed";
		}
		return "Doctor not available in hospital to remove";
	}

	@Override
	public String activateHospital(Long hospId) {
		hospitalRepository
			.findById(hospId)
			.orElseThrow(()->new ResourceNotFoundException("Hospital", hospId))
			.setIsActive(true);
		
		return "Hospital is active";
	}
	@Override
	public String inActivateHospital(Long hospId) {
		hospitalRepository
		.findById(hospId)
		.orElseThrow(()->new ResourceNotFoundException("Hospital", hospId))
		.setIsActive(false);
		
		return "Hospital is InActive";
	}

	

	

}
