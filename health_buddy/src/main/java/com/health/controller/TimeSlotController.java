package com.health.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.service.TimeSlotService;

@CrossOrigin
@RestController
@RequestMapping("/time_slots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/available/doctor/{doctorId}/date/{date}")
    public ResponseEntity<List<LocalTime>> getAvailableTimeSlots(
            @PathVariable Long doctorId,
            @PathVariable String date)
           // @RequestParam String startTime,  // doctor available time 
           // @RequestParam String endTime) 
       {

        LocalTime start = LocalTime.parse("10:00:00");
        LocalTime end = LocalTime.parse("16:00:00");

        List<LocalTime> availableSlots = timeSlotService.getAvailableTimeSlotsForDay(doctorId, date, start, end);

        return ResponseEntity.ok(availableSlots);
    }
}

