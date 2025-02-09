package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.Repository.DoctorRepository;
import com.health.Repository.PatientRepository;
import com.health.Repository.UserRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.User;
import com.health.entity.UserRole;
import com.health.reqdto.LogoutReqDto;
import com.health.reqdto.MailVerificationReqDto;
import com.health.reqdto.SigninReqDto;
import com.health.resdto.SigninResDto;
import com.health.security.JwtUtils;
import com.health.service.impl.EmailService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
@Service
@Transactional
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;
	
	@Autowired
	private EmailService emailService;


	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp : In case of success : Auth Resp DTO :mesg + JWT
	 * token + SC 201 In case of failure : SC 401
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody
			@Valid SigninReqDto request) {
		System.out.println("in sign in" + request);//=> email n password : valid(P.L)
		// 1. create a token(implementation of Authentication i/f)
		// to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		//2.  invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
			// => authentication successful !
			//3. In case of successful auth,  
		//create JWT n send it to the clnt in response
		User user = userRepository.findByUserName(request.getEmail()).orElseThrow(()->new ResourceNotFoundException("User not found"));
		
		Long id = 0L;
		if(user.getRole().equals(UserRole.ROLE_DOCTOR)) {
				id=doctorRepository.findByEmail(user.getUserName()).getId();
		}else if(user.getRole().equals(UserRole.ROLE_PATIENT)) {
			id=patientRepository.findByEmail(user.getUserName()).getId();
		}else if(user.getRole().equals(UserRole.ROLE_ADMIN)) {
			id= user.getId();
	}
		SigninResDto resp = new SigninResDto
				(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!", id,user.getRole());
		
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PostMapping("/sendotp")
	public ResponseEntity<?> sendOTP(@RequestBody @Valid MailVerificationReqDto dto) {
		
		String result =  emailService.sendVerificationMail(dto.getEmail());
		if(result.contains("failed")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/verifyotp")
	public ResponseEntity<?> verifyOTP(@RequestBody @Valid MailVerificationReqDto dto) {
		boolean val = emailService.verifyOTP(dto.getEmail(), dto.getOtp());
		if(val) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(val);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val);
	}


}
