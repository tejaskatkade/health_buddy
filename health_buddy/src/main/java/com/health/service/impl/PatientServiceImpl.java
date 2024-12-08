package com.health.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.PatientRepository;
import com.health.Repository.UserRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.Patient;
import com.health.entity.User;
import com.health.entity.UserRole;
import com.health.reqdto.PatientReqDto;
import com.health.resdto.PatientResDto;
import com.health.service.PatientService;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<PatientResDto> getAllPatients() {
		return patientRepository.findAll()
			.stream()
			.map((p)->mapper.map(p,PatientResDto.class))
			.collect(Collectors.toList());
	}

	@Override
	public PatientResDto getPatientById(Long patientId) {
		return mapper
				.map(patientRepository.findById(patientId)
				.orElseThrow(()->new ResourceNotFoundException("Patient",patientId)),PatientResDto.class);
	}

	@Override
	public String addPatient(PatientReqDto patientDto) {
		
		Patient patient = mapper.map(patientDto, Patient.class);
		
		User user = new User(patient.getEmail(),patientDto.getPassword(),UserRole.ROLE_PATIENT, true);
		userRepository.save(user);
		
		patient.setUser(user);
		
		Patient patient2 = patientRepository.save(patient);	
		return "Patient Added Successfully.  Patient Id : "+ patient2.getId();
	}
	
}