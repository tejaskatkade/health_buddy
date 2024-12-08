package com.health.reqdto;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class HospitalReqDto {
	@NotNull
	private String name;
	@NotNull
	private String location;
	@NotNull
	@UniqueElements
	private String contact;
}
