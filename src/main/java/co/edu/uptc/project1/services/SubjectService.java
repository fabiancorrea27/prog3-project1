package co.edu.uptc.project1.services;

import java.util.List;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Group;
import co.edu.uptc.project1.models.Subject;
import lombok.Getter;

public class SubjectService {
    @Getter
    private List<Subject> subjectList = new SimpleUptcList<Subject>();

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void deleteSubject(String codeSubject) throws ProjectExeption {
        boolean exists = false;
        for (Subject subject : subjectList) {
            if (subject.getCode() == Integer.parseInt(codeSubject)) {
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
}
