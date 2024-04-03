package co.edu.uptc.project1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Group;
import co.edu.uptc.project1.models.Location;
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
                        if(!subjectsWithSameLocation.contains(subject)){
                            subjectsWithSameLocation.add(subject);
                        }
                    }
                }
            }
        }
        return subjectsWithSameLocation;
    }
}
