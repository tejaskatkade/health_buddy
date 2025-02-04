package com.health.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity // - required in earlier spring sec versions -enabled by default
@Configuration // equivalent to bean config xml file
@AllArgsConstructor
public class SecurityConfig {


	private PasswordEncoder encoder;
	
	private CustomJwtAuthenticationFilter jwtFilter;

	// Configure the bean to customize spring security filter chain
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/hospital/active","/doctor/**","/time_slots/available/**","/appointment/**",
								"/patient", "/users/signin", "/v*/api-doc*/**",
								"/swagger-ui/**")
						.permitAll()
						.requestMatchers(HttpMethod.OPTIONS).permitAll()

						.requestMatchers("/appointment/patient/*","/appointment/cancle/*","/appointment/*").hasRole("PATIENT")
						.requestMatchers("/appointment/complete/*","/appointment/doctor/*","/appointment/cancel/*","/doctor","/doctor/*").hasRole("DOCTOR")
						//.requestMatchers("").hasRole("ADMIN")
						.anyRequest()
						.authenticated())
	//			.httpBasic(Customizer.withDefaults()) replacing Basic Auth by custom JWT based auth
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	// configure AuthMgr as a spring bean
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
