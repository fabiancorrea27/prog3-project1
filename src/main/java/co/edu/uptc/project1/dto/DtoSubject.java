package co.edu.uptc.project1.dto;

import java.util.List;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSubject {
    private String name;
    private int code;

    public static DtoSubject fromSubject(Subject subject) {
        DtoSubject dtoSubject = new DtoSubject();
        dtoSubject.setName(subject.getName());
        dtoSubject.setCode(subject.getCode());
        return dtoSubject;
    }

    public static List<DtoSubject> fromSubjectList(List<Subject> subjectList) {
        List<DtoSubject> dtoSubjectList = new SimpleUptcList<DtoSubject>();
        for (Subject subject : subjectList) {
            dtoSubjectList.add(fromSubject(subject));
        }
        return dtoSubjectList;
    }

    public static Subject fromDtoSubject(DtoSubject dtoSubject) {
        Subject subject = new Subject();
        subject.setName(dtoSubject.getName());
        subject.setCode(dtoSubject.getCode());
        return subject;
    }

    public static void checkNullFields(DtoSubject dtoSubject) throws ProjectExeption {
        if (dtoSubject.getCode() == 0 || dtoSubject.getName() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
