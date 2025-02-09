package com.health.resdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResDto {
	private Long id;
	private String name;
	private String location;
	private String contact;
	private Boolean isActive;
}
