package com.health.resdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResDto {
	private Long id;
	private String name;
	private String specialization;
	private Integer experience;
	private String email;
}
