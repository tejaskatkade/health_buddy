package com.health.reqdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutReqDto {

	@NotNull
	private Long Id;
	@NotNull
	private String Token;
}
