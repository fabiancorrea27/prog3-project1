package co.edu.uptc.project1.services;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Group;
import co.edu.uptc.project1.models.Location;
import co.edu.uptc.project1.models.Schedule;
import co.edu.uptc.project1.models.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SubjectService {
    @Getter
    private List<Subject> subjectList = new SimpleUptcList<Subject>();

    private List<Group> groupList;
    private List<Location> locationList;

    @Autowired
    public SubjectService(GroupService groupService, LocationService locationService) {
        this.groupList = groupService.getGroupList();
        this.locationList = locationService.getLocationList();
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void deleteSubject(int codeSubject) throws ProjectExeption {
        boolean exists = false;
        for (Subject subject : subjectList) {
            if (subject.getCode() == codeSubject) {
                exists = true;
                subjectList.remove(subject);
            }
        }
        if (!exists) {
            throw new ProjectExeption(TypeMessage.CANT_DELETE);
        }
    }

    public Subject updateSubject(Subject subject) throws ProjectExeption {
        Subject subjectToReturn = subject;
        boolean exists = false;
        for (Subject listSubject : subjectList) {
            if (listSubject.getCode() == subject.getCode()) {
                exists = true;
                listSubject.setName(subject.getName());
            }
        }
        if (!exists) {
            throw new ProjectExeption(TypeMessage.NOT_UPDATED);
        }
        return subjectToReturn;
    }

    public List<Subject> subjectsWithSameLocation(int idLocation) {
        List<Subject> subjectsWithSameLocation = new SimpleUptcList<>();
        for (Group group : groupList) {
            // Verifica si tiene el mismo lugar
            if (group.getIdLocation() == idLocation) {
                for (Subject subject : subjectList) {
                    // Encuentra la asignatura del grupo y la agrega a la lista
                    if (subject.getCode() == group.getSubjectCode()) {
                        // Verifica si ya esta agregada la Asignatura a la lista
                        if (!subjectsWithSameLocation.contains(subject)) {
                            subjectsWithSameLocation.add(subject);
                        }
                    }
                }
            }
        }
        return subjectsWithSameLocation;
    }

    public List<Subject> subjectsWithMultipleGroups() {
        List<Subject> subjectsWithMultipleGroups = new SimpleUptcList<>();
        for (Subject subject : subjectList) {
            // Cuenta la cantidad de veces que aparece una asignatura
            int subjectCounter = 0;
            for (Group group : groupList) {
                if (subject.getCode() == group.getSubjectCode()) {
                    subjectCounter++;
                }
            }
            // Agrega la asignatura a la lista si esta tiene varias apariciones en distintos
            // grupos
            if (subjectCounter > 1) {
                subjectsWithMultipleGroups.add(subject);
            }
        }
        return subjectsWithMultipleGroups;
    }

    public List<Subject> subjectsWithSameSchedule(String dayOfWeek) {
        List<Subject> subjectsWithSameSchedule = new SimpleUptcList<>();
        // Recorre los grupos
        for (Group group1 : groupList) {
            // Recorre los horarios de ese grupo
            for (Schedule scheduleGroup1 : group1.getSchedules()) {
                // Verifica si el horario es del dia indicado
                if (scheduleGroup1.getDay().equals(DayOfWeek.valueOf(dayOfWeek))) {
                    // Recorre nuevamente los grupos
                    for (Group group2 : groupList) {
                        // Recorre los horarios de ese grupo
                        for (Schedule scheduleGroup2 : group2.getSchedules()) {
                            // Verifica nuevamente si el horario pertenece al dia indicado
                            if (scheduleGroup2.getDay().equals(DayOfWeek.valueOf(dayOfWeek))) {
                                // Verifica si los horarios son iguales
                                if (ScheduleService.isSchedulesEquals(scheduleGroup1, scheduleGroup2)) {
                                addSubjectToList(group1, group2, subjectsWithSameSchedule);
                                }
                            }
                        }
                    }
                }
            }

        }
        return subjectsWithSameSchedule;
    }

    private void addSubjectToList(Group group1, Group group2, List<Subject> subjectsWithSameSchedule){
            // Agrega las asignaturas 
            Subject subjectGroup1 = subjectFromCode(group1.getSubjectCode());
            Subject subjectGroup2 = subjectFromCode(group2.getSubjectCode());
            if (!subjectsWithSameSchedule.contains(subjectGroup1)) {
                subjectsWithSameSchedule.add(subjectGroup1);
            }
            if (!subjectsWithSameSchedule.contains(subjectGroup2)) {
                subjectsWithSameSchedule.add(subjectGroup2);
            }
    }

    private Subject subjectFromCode(int subjectCode) {
        for (Subject subject : subjectList) {
            if (subject.getCode() == subjectCode) {
                return subject;
            }
        }
        return null;
    }
}
