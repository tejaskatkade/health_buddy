package com.health.Repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.entity.Doctor;
import com.health.entity.User;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT h.doctor FROM Hospital h WHERE h.id = :hospId")
    Set<Doctor> findDoctorsByHospitalId(@Param("hospId") Long hosplId);
    
    Doctor findByEmail(String userName);
}
