package com.health.resdto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResDto {
	
	private String name;
	private String specialization;
	private Integer experience;
	private String email;
}
