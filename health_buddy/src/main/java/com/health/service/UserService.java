package com.health.service;

import com.health.reqdto.UpdatePasswordReqDto;

public interface UserService {
	String updateUserPassword(UpdatePasswordReqDto dto);
}
