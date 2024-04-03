package co.edu.uptc.project1.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {
    private int subjectCode;
    private int idLocation;
    private List<Schedule> schedules;

}
