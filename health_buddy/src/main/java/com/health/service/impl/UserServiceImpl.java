package com.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health.Repository.UserRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.User;
import com.health.reqdto.UpdatePasswordReqDto;
import com.health.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PasswordEncoder encoder;

	@Override
	public String updateUserPassword(UpdatePasswordReqDto dto) {
		String msg = "Password not updated";
		User user = userRepository.findByUserName(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User"));
		user.setPassword(encoder.encode(dto.getPassword()));
		msg = "Password Updated ";
		return msg;
	}

}
