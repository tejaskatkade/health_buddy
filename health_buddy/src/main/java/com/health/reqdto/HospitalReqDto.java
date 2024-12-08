package com.health.reqdto;

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
	private String contact;
}
