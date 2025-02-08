package com.health.reqdto;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MailVerificationReqDto {
	@Email
	@NotNull
	private String email;
	
	private String otp;
}
