package com.health.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	
}
