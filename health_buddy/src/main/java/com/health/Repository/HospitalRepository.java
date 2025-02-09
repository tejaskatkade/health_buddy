package com.health.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.health.entity.Hospital;



@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Query("SELECT h FROM Hospital h WHERE h NOT IN (SELECT d.hospitals FROM Doctor d WHERE d.id = :doctorId)")
	List<Hospital> findHospitalsWhereDoctorNotWorking(@Param("doctorId") Long doctorId);

}
