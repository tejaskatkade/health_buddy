package com.health.reqdto;

import com.health.entity.Gender;
import com.health.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientReqDto {
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email
	private String email;
	 @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20}$", 
             message = "Password must be 5-20 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (#@$*).")
    private String password;
	private String address;
	@Min(0)
	@Max(100)
	private Integer age;
	private Gender gender;
	private String symptoms;
}
