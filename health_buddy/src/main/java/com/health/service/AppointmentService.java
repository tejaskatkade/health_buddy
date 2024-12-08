package com.health.service;

import java.util.List;

import com.health.reqdto.AppointmentReqDto;
import com.health.resdto.AppointmentResDto;

public interface AppointmentService {

	AppointmentResDto bookAppointment(AppointmentReqDto appointment);

	List<AppointmentResDto> getAllAppointments();

	List<AppointmentResDto> getAppointmentsByDoctorId(Long doctorId);
	List<AppointmentResDto> getAppointmentsByHospitalId(Long hospId);

	AppointmentResDto getAppointment(Long id);

	String cancleAppointment(Long appointId);

	String completeAppointment(Long appointId);

	List<AppointmentResDto> getAppointmentsBypatientId(Long patientId);

}
