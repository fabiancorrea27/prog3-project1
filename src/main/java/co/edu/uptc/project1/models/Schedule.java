package co.edu.uptc.project1.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    private LocalTime entryTime;
    private LocalTime departureTime;
    private DayOfWeek day;
}