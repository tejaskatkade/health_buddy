package com.health.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.DoctorRepository;
import com.health.Repository.HospitalRepository;
import com.health.Repository.UserRepository;
import com.health.custom_exception.ApiException;
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
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<DoctorResDto> getDoctorsByHospital(Long hospId) {
		return doctorRepository
				.findDoctorsByHospitalId(hospId)
				.stream()
				.filter((doc)->doc.getUser().getIsActive() == true)
				.map((d)->mapper.map(d,DoctorResDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addDoctor(DoctorReqDto doctorReqDto) {
		Doctor doctor = mapper.map(doctorReqDto, Doctor.class);
		User user = new User(doctor.getEmail(), encoder.encode(doctorReqDto.getPassword()), UserRole.ROLE_DOCTOR, true);
		
		if(userRepository.existsByUserName(doctor.getEmail())) {
			throw new ApiException("Email already exist");
		}
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

	@Override
	public List<DoctorResDto> getAllDoctors() {
		return doctorRepository
				.findAll()
				.stream()
				.map((d)->mapper.map(d,DoctorResDto.class))
				.collect(Collectors.toList());
	}
	@Override
	public List<DoctorResDto> getAllActiveDoctors() {
		return doctorRepository
				.findAll()
				.stream().filter((doc)->doc.getUser().getIsActive() == true)
				.map((d)->mapper.map(d,DoctorResDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse updateDoctor(Long doctorId, DoctorReqDto doctorReqDto) {
		
		Doctor doctor = doctorRepository
				.findById(doctorId)
				.orElseThrow(()-> new ResourceNotFoundException("Doctor", doctorId));
		

		doctor.setEmail(doctorReqDto.getEmail());
		doctor.setName(doctorReqDto.getName());
		doctor.setExperience(doctorReqDto.getExperience());
		doctor.setContact(doctorReqDto.getContact());
		doctor.setSpecialization(doctorReqDto.getSpecialization());
		doctor.getUser().setPassword(doctorReqDto.getPassword());
		doctorRepository.save(doctor);
		return new ApiResponse("Doctore updated successfully");
	}

	@Override
	public ApiResponse inActivateDoctor(Long doctorId) {
		Doctor doctor = doctorRepository
				.findById(doctorId)
				.orElseThrow(()-> new ResourceNotFoundException("Doctor", doctorId));
		doctor.getUser().setIsActive(false);
		return new ApiResponse("Doctor is InActive now");
	}

	@Override
	public ApiResponse activateDoctor(Long doctorId) {
		Doctor doctor = doctorRepository
				.findById(doctorId)
				.orElseThrow(()-> new ResourceNotFoundException("Doctor", doctorId));
		doctor.getUser().setIsActive(true);
		return new ApiResponse("Doctor is Active now");
	}	
}
