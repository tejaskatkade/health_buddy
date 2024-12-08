package com.health.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.service.TimeSlotService;

@RestController
@RequestMapping("/time_slots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/available")
    public ResponseEntity<List<LocalTime>> getAvailableTimeSlots(
            @RequestParam Long doctorId,
            @RequestParam String date,
            @RequestParam String startTime,  // doctor available time 
            @RequestParam String endTime) {

        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        List<LocalTime> availableSlots = timeSlotService.getAvailableTimeSlotsForDay(doctorId, date, start, end);

        return ResponseEntity.ok(availableSlots);
    }
}

