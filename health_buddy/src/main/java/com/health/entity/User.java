package com.health.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@AllArgsConstructor
public class User extends BaseEntity {

	@Column(unique = true, nullable = false)
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private Boolean isActive = true;

}
