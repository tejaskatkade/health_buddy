package com.health.resdto;

import jakarta.validation.constraints.NotNull;

public class HospitalDto {

	@NotNull
	private String name;
	@NotNull
	private String location;
	@NotNull
	private String Contact;
}
