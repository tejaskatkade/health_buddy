package com.health.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends BaseEntity {
	
	private String name;
	private String specialization;
	private Integer experience;
	@Column(unique = true, nullable = false)
	private String email;
	private String contact;
	
	@ManyToMany(cascade =CascadeType.ALL)
	@JoinTable(
			name = "hospital_doctor",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "hospital_id")
			)
	private Set<Hospital> hospitals;
	
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
//	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//	private Set<TimeSlot> timeSlots;
	
}
