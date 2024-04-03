package co.edu.uptc.project1.services;

import co.edu.uptc.project1.models.Schedule;

public class ScheduleService {

    public static boolean isSchedulesEquals(Schedule schedule1, Schedule schedule2) {
        if (schedule1.getDay().equals(schedule2.getDay()) && schedule1.getEntryTime().equals(
                schedule2.getEntryTime()) && schedule1.getDepartureTime().equals(schedule2.getDepartureTime())) {
            return true;
        }
        return false;
    }
}
