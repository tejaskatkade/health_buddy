package com.health.reqdto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorReqDto {
	
	@NotNull
	private String name;
	@NotNull
	private String specialization;
	@NotNull
	@Min(3)
	@Max(20)
	private Integer experience;
	
	@Email(message = "Please Enter a Valid Email")
	@NotNull
	private String email;

	 @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20}$", 
     message = "Password must be 5-20 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (#@$*).")
	 private String password;

	@NotNull
	@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}")
	private String contact;

}
