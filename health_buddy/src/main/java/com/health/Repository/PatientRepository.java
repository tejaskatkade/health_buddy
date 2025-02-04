package com.health.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.Patient;
import com.health.entity.User;

public interface PatientRepository extends JpaRepository<Patient,Long>{
	
	Patient findByEmail(String userName);

}
