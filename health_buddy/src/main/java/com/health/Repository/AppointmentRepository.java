package com.health.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.entity.Appointment;
import com.health.entity.Doctor;
import com.health.entity.Hospital;
import com.health.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date AND a.status != 'CANCELLED'")
    List<Appointment> findBookedTimeSlotsByDoctorAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByHospital(Hospital hospital);
    List<Appointment> findByPatient(Patient patient);
}
