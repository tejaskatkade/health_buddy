package com.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.AppointmentRepository;
import com.health.entity.Appointment;
import com.health.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Override
	public Appointment bookAppointment(Appointment appointment) {
	 	
		return appointmentRepo.save(appointment);
		
	}

}
