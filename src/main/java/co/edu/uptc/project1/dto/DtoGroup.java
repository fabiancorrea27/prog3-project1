package co.edu.uptc.project1.dto;

import java.util.List;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Group;
import co.edu.uptc.project1.models.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGroup {
    private int subjectCode;
    private int idLocation;
    private List<Schedule> schedules;

    public static DtoGroup fromGroup(Group group) {
        DtoGroup dtoGroup = new DtoGroup();
        dtoGroup.setSubjectCode(group.getSubjectCode());
        dtoGroup.setIdLocation(group.getIdLocation());
        dtoGroup.setSchedules(group.getSchedules());
        return dtoGroup;
    }

    public static List<DtoGroup> fromGroupList(List<Group> groupList) {
        List<DtoGroup> dtoGroupList = new SimpleUptcList<DtoGroup>();
        for (Group group : groupList) {
            dtoGroupList.add(fromGroup(group));
        }
        return dtoGroupList;
    }

    public static Group fromDtoGroup(DtoGroup dtoGroup) {
        Group group = new Group();
        group.setSubjectCode(dtoGroup.getSubjectCode());
        group.setIdLocation(dtoGroup.getIdLocation());
        group.setSchedules(dtoGroup.getSchedules());
        return group;
    }

    public static void checkNullFields(DtoGroup dtoGroup) throws ProjectExeption {
        if (dtoGroup.getSubjectCode() == 0 || dtoGroup.getIdLocation() == 0 || dtoGroup.getSchedules() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
