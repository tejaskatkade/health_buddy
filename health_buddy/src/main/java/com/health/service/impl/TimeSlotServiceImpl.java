package com.health.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.health.Repository.AppointmentRepository;
import com.health.service.TimeSlotService;
import com.health.util.TimeSlotUtil;


@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService{
	 @Autowired
	    private AppointmentRepository appointmentRepository;

	    public List<LocalTime> getAvailableTimeSlotsForDay(Long doctorId, String date, LocalTime startTime, LocalTime endTime) {
	        
	    	// Step 1: Generate all time slots for the given range
	        List<LocalTime> allTimeSlots = TimeSlotUtil.generateTimeSlots(startTime, endTime);

	        // Step 2: Get all booked time slots for the doctor on the specified date
	        List<LocalTime> bookedTimeSlots = appointmentRepository
	            .findBookedTimeSlotsByDoctorAndDate(doctorId,LocalDate.parse(date))
	            .stream()
	            .map(appointment -> appointment.getTimeSlot().getStartTime())
	            .collect(Collectors.toList());

	        // Step 3: Filter out booked slots from all time slots
	        System.out.println(allTimeSlots);
	        return allTimeSlots.stream()
	                .filter(slot -> !bookedTimeSlots.contains(slot))
	                .collect(Collectors.toList());
	    }
}
