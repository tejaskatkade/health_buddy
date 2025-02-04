package com.health.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity{
	
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	private String address;
	private Integer age;
	private Gender gender;
	private String symptoms;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	/* mappedBy = "patient" indicate "patient" field in the Appointment entity owns the relationship.
	 * the side containing physical mapping of the association : FK*/
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
}
