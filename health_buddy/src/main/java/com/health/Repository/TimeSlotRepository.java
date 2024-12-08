package com.health.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

}
