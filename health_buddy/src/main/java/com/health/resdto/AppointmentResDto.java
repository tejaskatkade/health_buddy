package com.health.resdto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.health.entity.AppointmentStatus;
import com.health.entity.TimeSlot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentResDto {
	private Long id;
	private LocalDate appointmentDate;
	private AppointmentStatus status;
	private String doctorName;
	private String hospitalName;
	private LocalTime startTime;
	private String patientName;
}
