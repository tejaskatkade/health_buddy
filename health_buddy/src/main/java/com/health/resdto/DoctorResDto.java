package com.health.resdto;

import com.health.entity.AppointmentStatus;
import com.health.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResDto {
	private Long id;
	private String name;
	private User user;
	//private Boolean isActive;
	private String specialization;
	private Integer experience;
	private String email;
	private String contact;
}
