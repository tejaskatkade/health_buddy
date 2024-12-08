package com.health.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.AppointmentRepository;
import com.health.Repository.DoctorRepository;
import com.health.Repository.HospitalRepository;
import com.health.Repository.PatientRepository;
import com.health.Repository.TimeSlotRepository;
import com.health.custom_exception.ApiException;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.Appointment;
import com.health.entity.AppointmentStatus;
import com.health.entity.Doctor;
import com.health.entity.Hospital;
import com.health.entity.Patient;
import com.health.entity.TimeSlot;
import com.health.reqdto.AppointmentReqDto;
import com.health.resdto.AppointmentResDto;
import com.health.resdto.PatientResDto;
import com.health.service.AppointmentService;
import com.health.service.TimeSlotService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public AppointmentResDto bookAppointment(AppointmentReqDto appointmentdto) {
		System.out.println(appointmentdto);
		TimeSlot timeSlot = new TimeSlot();
		System.out.println(timeSlot.getStartTime());
		Hospital hospital = hospitalRepository.findById(appointmentdto.getHospital_id())
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", appointmentdto.getHospital_id()));
		Doctor doctor = doctorRepository.findById(appointmentdto.getDoctor_id())
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", appointmentdto.getDoctor_id()));
		Patient patient = patientRepository.findById(appointmentdto.getPatient_id())
				.orElseThrow(() -> new ResourceNotFoundException("Patient", appointmentdto.getPatient_id()));

		timeSlot.setStartTime(LocalTime.parse(appointmentdto.getTimeSlot()));
		timeSlotRepository.save(timeSlot);

		List<LocalTime> bookedTimeSlots = appointmentRepository
				.findBookedTimeSlotsByDoctorAndDate(doctor.getId(),
						LocalDate.parse(appointmentdto.getAppointmentDate()))
				.stream().map(appointment -> appointment.getTimeSlot().getStartTime()).collect(Collectors.toList());

		// List<LocalTime> availableTimeSlotsForDay =
		// timeSlotService.getAvailableTimeSlotsForDay(doctor.getId(),appointmentdto.getAppointmentDate()
		// ,LocalTime.parse("10:00:00"),LocalTime.parse("16:00:00"));

		if (bookedTimeSlots.stream().anyMatch((t) -> t.compareTo(timeSlot.getStartTime()) == 0)) {
			throw new ApiException("time slot not available");
		}

		LocalDate date = LocalDate.parse(appointmentdto.getAppointmentDate());

		Appointment appointment = mapper.map(appointmentdto, Appointment.class);
		appointment.setTimeSlot(timeSlot);
		appointment.setAppointmentDate(date);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setHospital(hospital);
		appointment.setStatus(AppointmentStatus.PENDING);

		Appointment appointment2 = appointmentRepo.save(appointment);
		System.out.println(appointment2);
		AppointmentResDto resDto = mapper.map(appointment2, AppointmentResDto.class);
		resDto.setDoctorName(appointment2.getDoctor().getName());
		resDto.setHospitalName(appointment2.getHospital().getName());
		resDto.setPatientName(appointment2.getPatient().getFirstName() + " " + appointment2.getPatient().getLastName());
		resDto.setStartTime(appointment2.getTimeSlot().getStartTime());
		return resDto;
	}

	@Override
	public List<AppointmentResDto> getAllAppointments() {
		return appointmentRepo.findAll().stream().map((a) -> {

			AppointmentResDto resDto = mapper.map(a, AppointmentResDto.class);

			resDto.setDoctorName(a.getDoctor().getName());
			resDto.setHospitalName(a.getHospital().getName());
			resDto.setPatientName(a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
			resDto.setStartTime(a.getTimeSlot().getStartTime());

			return resDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<AppointmentResDto> getAppointmentsBypatientId(Long patientId) {
		Patient patient = patientRepository.findById(patientId)
		.orElseThrow(()->new ResourceNotFoundException("Patient",patientId));
		
		return appointmentRepo.findByPatient(patient).stream().map((a) -> {
			
			AppointmentResDto resDto = mapper.map(a, AppointmentResDto.class);
			
			resDto.setDoctorName(a.getDoctor().getName());
			resDto.setHospitalName(a.getHospital().getName());
			resDto.setPatientName(a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
			resDto.setStartTime(a.getTimeSlot().getStartTime());
			
			return resDto;
		}).collect(Collectors.toList());

	}

	@Override
	public List<AppointmentResDto> getAppointmentsByHospitalId(Long hospId) {
		 Hospital hospital = hospitalRepository.findById(hospId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", hospId));
		return appointmentRepo.findByHospital(hospital).stream().map((a) -> {
			
			AppointmentResDto resDto = mapper.map(a, AppointmentResDto.class);
			
			resDto.setDoctorName(a.getDoctor().getName());
			resDto.setHospitalName(a.getHospital().getName());
			resDto.setPatientName(a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
			resDto.setStartTime(a.getTimeSlot().getStartTime());
			
			return resDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<AppointmentResDto> getAppointmentsByDoctorId(Long doctorId) {
		   Doctor doctor = doctorRepository.findById(doctorId)
					.orElseThrow(() -> new ResourceNotFoundException("Doctor", doctorId));
			return appointmentRepo.findByDoctor(doctor).stream().map((a) -> {
				
				AppointmentResDto resDto = mapper.map(a, AppointmentResDto.class);
				
				resDto.setDoctorName(a.getDoctor().getName());
				resDto.setHospitalName(a.getHospital().getName());
				resDto.setPatientName(a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
				resDto.setStartTime(a.getTimeSlot().getStartTime());
				
				return resDto;
			}).collect(Collectors.toList());
		}

	@Override
	public AppointmentResDto getAppointment(Long id) {
		Appointment appointment =  appointmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Appointment", id));
		
		AppointmentResDto resDto = mapper
			.map(appointment,AppointmentResDto.class);
		 
		 	resDto.setDoctorName(appointment.getDoctor().getName());
			resDto.setHospitalName(appointment.getHospital().getName());
			resDto.setPatientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
			resDto.setStartTime(appointment.getTimeSlot().getStartTime());
			
			return resDto;
	}

	@Override
	public String cancleAppointment(Long appointId) {
		Appointment appointment = appointmentRepo.findById(appointId)
		.orElseThrow(()-> new ResourceNotFoundException("Appointment", appointId));
		
		appointment.setStatus(AppointmentStatus.CANCELLED);
		appointmentRepo.save(appointment);
		
		return "Appointment Cancelled";
	}
	@Override
	public String completeAppointment(Long appointId) {
		Appointment appointment = appointmentRepo.findById(appointId)
				.orElseThrow(()-> new ResourceNotFoundException("Appointment", appointId));
				appointment.setStatus(AppointmentStatus.COMPLETED);
				appointmentRepo.save(appointment);
		return "Appointment Completed";
	}

	
}
