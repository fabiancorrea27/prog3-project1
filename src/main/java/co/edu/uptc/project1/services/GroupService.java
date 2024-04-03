package co.edu.uptc.project1.services;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Group;
import co.edu.uptc.project1.models.Schedule;
import lombok.Getter;

@Service
public class GroupService {
    @Getter
    private List<Group> groupList = new SimpleUptcList<Group>();

    @Value("${maxSchedules}")
    private int MAX_SCHEDULES;

    public void addGroup(Group newGroup) throws ProjectExeption {
        if (verifySchedulesError(newGroup)) {
            throw new ProjectExeption(TypeMessage.SCHEDULES_CONFLICT);
        } else if (newGroup.getSchedules().size() > MAX_SCHEDULES) {
            throw new ProjectExeption(TypeMessage.MAX_SCHEDULES);
        } else {
            groupList.add(newGroup);
        }
    }

    public void deleteGroup(int codeSubject, int idLocation) throws ProjectExeption {
        boolean exist = false;
        for (Group group : groupList) {
            if (group.getIdLocation() == idLocation
                    && (group.getSubjectCode() == codeSubject)) {
                exist = true;
                groupList.remove(group);
            }
        }

        if (!exist) {
            throw new ProjectExeption(TypeMessage.CANT_DELETE);
        }
    }

    public Group updateGroup(Group group) throws ProjectExeption {
        Group groupToReturn = group;
        boolean exists = false;
        if (group.getSchedules().size() > MAX_SCHEDULES) {
            throw new ProjectExeption(TypeMessage.MAX_SCHEDULES);
        } else if (verifySchedulesError(group)) {
            throw new ProjectExeption(TypeMessage.SCHEDULES_CONFLICT);
        }
        for (Group listGroup : groupList) {
            if (listGroup.getIdLocation() == group.getIdLocation()
                    && (listGroup.getSubjectCode() == group.getSubjectCode())) {
                exists = true;
                updateGroupFields(listGroup, group);
            }
        }
        if (!exists) {
            throw new ProjectExeption(TypeMessage.NOT_UPDATED);
        }
        return groupToReturn;
    }

    private boolean verifySchedulesError(Group newGroup) {
        // Recorre los grupos existentes
        for (Group listGroup : groupList) {
            // Verifica si tienen la misma locacion
            if (listGroup.getIdLocation() == newGroup.getIdLocation()) {
                // Verifica algun error con los horarios del nuevo grupo
                if (verifySchedulesConflict(listGroup, newGroup)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verifySchedulesConflict(Group listGroup, Group newGroup) {
        // Recorre los horarios existentes del grupo
        for (Schedule scheduleFromListGroup : listGroup.getSchedules()) {
            // Recorre los horarios del nuevo grupo
            for (Schedule scheduleFromNewGroup : newGroup.getSchedules()) {
                // Verifica si los horarios tienen el mismo dia
                if (scheduleFromListGroup.getDay().equals(scheduleFromNewGroup.getDay())) {
                    // Verifica si los tiempos se interfieren
                    if (verifySchedulesTimeConflict(scheduleFromListGroup, scheduleFromNewGroup)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean verifySchedulesTimeConflict(Schedule schedule, Schedule newSchedule) {
        LocalTime entryTime = schedule.getEntryTime();
        LocalTime departureTime = schedule.getDepartureTime();
        LocalTime newEntryTime = newSchedule.getEntryTime();
        LocalTime newDepartureTime = newSchedule.getDepartureTime();
        // Verifica si los tiempos del nuevo horario interfieren con el otro
        boolean entryConflict = (newEntryTime.isBefore(departureTime) && newEntryTime.isAfter(entryTime)) ||
                newEntryTime.equals(entryTime);
        boolean departureConflict = (newDepartureTime.isAfter(entryTime) && newDepartureTime.isBefore(departureTime)) ||
                newDepartureTime.equals(departureTime);
        return entryConflict || departureConflict;
    }

    private void updateGroupFields(Group groupToChange, Group group) {
        groupToChange.setIdLocation(group.getIdLocation());
        groupToChange.setSchedules(group.getSchedules());
    }
}
