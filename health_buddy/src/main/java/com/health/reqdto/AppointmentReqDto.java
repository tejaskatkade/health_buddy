package com.health.reqdto;

import java.time.LocalDate;

import com.health.entity.TimeSlot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentReqDto {
	private LocalDate appointementDate;
	//private AppointmentStatus status;
	private Long doctor_id;
	private Long hospital_id;
	private TimeSlot timeSlot;
	private Long patient_id;
}
