package com.health.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity{
	
	private LocalDate appointmentDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private AppointmentStatus status;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id" , nullable = false)
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "hospital_id" , nullable = false)
	private Hospital hospital;
	
	@OneToOne
	@JoinColumn(name="time_slot_id", unique = true, nullable = false)
	private TimeSlot timeSlot;
	
	@ManyToOne
	@JoinColumn(name = "patient_id" , nullable = false)
	private Patient patient;
}
