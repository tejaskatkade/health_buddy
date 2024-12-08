package com.health.resdto;

import com.health.entity.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResDto {
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	private String symptoms;
}
