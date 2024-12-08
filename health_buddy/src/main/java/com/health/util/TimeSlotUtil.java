package com.health.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotUtil {

    public static List<LocalTime> generateTimeSlots(LocalTime startTime, LocalTime endTime) {
        List<LocalTime> timeSlots = new ArrayList<>();
        LocalTime slot = startTime;

        while (slot.isBefore(endTime)) {
            timeSlots.add(slot);
            slot = slot.plusMinutes(30); // Increment by 30 minutes
        }

        return timeSlots;
    }
}

