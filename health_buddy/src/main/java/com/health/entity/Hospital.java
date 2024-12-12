package com.health.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Hospital extends BaseEntity  {
	
	private String name;
	private String location;
	private String contact;
	private Boolean isActive = true;
	
	@ManyToMany
	private Set<Doctor> doctor;
	
//	@OneToMany
//	private Set<TimeSlot> timeSlots;
}
