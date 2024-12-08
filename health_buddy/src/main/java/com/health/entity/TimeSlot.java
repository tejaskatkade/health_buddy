package com.health.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot extends BaseEntity{

	private LocalTime startTime;
	private LocalTime endTime;
	
//	@ManyToOne
//	private Doctor doctor;
//	@ManyToOne
//	private Hospital hospital;
	
}
