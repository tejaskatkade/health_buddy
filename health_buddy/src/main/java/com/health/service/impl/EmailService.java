package com.health.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.health.Repository.UserRepository;
import com.health.custom_exception.ResourceNotFoundException;
import com.health.entity.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service

public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	//private UserRepository userRepository;
	private Integer otpValue;
	
	public String sendEmail(String to, String subject, String body) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			javaMailSender.send(mimeMessage);
			return "OTP sent on "+to;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "failed";
	
	}
	
	public String generateOTP() {
		Random random = new Random();
		int otpVal = 100000 + random.nextInt(900000);
		return String.valueOf(otpVal);
	}
	
	public String sendVerificationMail(String email) {
		String otp = generateOTP();
		String subject = "Email Verfication";
		String body = "You verification OTP is : "+ otp;
		otpValue = Integer.valueOf(otp);
		return sendEmail(email, subject, body);
		
	}
	
	public boolean verifyOTP(String email, String otp) {
		//User user = userRepository.findByUserName(email).orElseThrow(()->new ResourceNotFoundException("User"));
		
		if( otpValue.toString().equals(otp)) {
			otpValue = null;
			return true;
		}
		return false;
	}
	
}
