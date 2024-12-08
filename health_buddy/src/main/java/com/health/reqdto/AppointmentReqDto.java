package com.health.reqdto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentReqDto {
	private String appointmentDate;
	//private AppointmentStatus status;
	private Long doctor_id;
	private Long hospital_id;
	private String timeSlot;
	private Long patient_id;
}
