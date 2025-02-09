package com.health.resdto;

import com.health.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninResDto {
	private String jwt;
	private String mesg;
	private Long id;
	private UserRole role;
}
