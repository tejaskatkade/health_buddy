package com.health.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

}
