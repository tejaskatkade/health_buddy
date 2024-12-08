package com.health.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.DoctorRepository;
import com.health.Repository.HospitalRepository;
import com.health.Repository.UserRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.Doctor;
import com.health.entity.Hospital;
import com.health.entity.User;
import com.health.entity.UserRole;
import com.health.reqdto.DoctorReqDto;
import com.health.resdto.ApiResponse;
import com.health.resdto.DoctorResDto;
import com.health.service.DoctorService;
import com.health.service.HospitalService;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<DoctorResDto> getDoctorsByHospital(Long hospId) {
		return doctorRepository
				.findDoctorsByHospitalId(hospId)
				.stream()
				.map((d)->mapper.map(d,DoctorResDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addDoctor(DoctorReqDto doctorReqDto) {
		Doctor doctor = mapper.map(doctorReqDto, Doctor.class);
		User user = new User(doctor.getEmail(), doctorReqDto.getPassword(), UserRole.ROLE_DOCTOR, true);
		userRepository.save(user);
		doctor.setUser(user);
		Long id = doctorRepository.save(doctor).getId();
		return new ApiResponse("Doctore is added id ::"+ id);
	}

	@Override
	public DoctorResDto getDoctorById(Long doctorID) {
		Doctor doctor = doctorRepository
				.findById(doctorID)
				.orElseThrow(()-> new ResourceNotFoundException("Doctor", doctorID));
		return mapper.map(doctor, DoctorResDto.class);
	}

	
}
