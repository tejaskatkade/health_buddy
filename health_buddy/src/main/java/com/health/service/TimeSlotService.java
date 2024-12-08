package com.health.service;

import java.time.LocalTime;
import java.util.List;


public interface TimeSlotService {
	public List<LocalTime> getAvailableTimeSlotsForDay(Long doctorId, String date, LocalTime startTime, LocalTime endTime);
}
