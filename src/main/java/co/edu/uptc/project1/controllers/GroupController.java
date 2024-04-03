package co.edu.uptc.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.project1.dto.DtoGroup;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.services.GroupService;

@RestController
@RequestMapping("/${groupRequest}")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping()
    public ResponseEntity<Object> getGroups() {
        List<DtoGroup> dtoGroupList = DtoGroup.fromGroupList(groupService.getGroupList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoGroupList);
    }

    @PostMapping()
    public ResponseEntity<Object> addGroup(@RequestBody DtoGroup dtoGroup) {
        try {
            DtoGroup.checkNullFields(dtoGroup);
            groupService.addGroup(DtoGroup.fromDtoGroup(dtoGroup));
            return ResponseEntity.status(HttpStatus.OK).body(dtoGroup);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @DeleteMapping("/${deleteGroupMapping}")
    public ResponseEntity<Object> deleteGroup(@PathVariable int codeSubject, @PathVariable int idLocation) {
        try {
            groupService.deleteGroup(codeSubject, idLocation);
            return ResponseEntity.status(HttpStatus.OK).body(TypeMessage.DELETED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateSubject(@RequestBody DtoGroup dtoGroup) {
        try {
            DtoGroup.checkNullFields(dtoGroup);
            groupService.updateGroup(DtoGroup.fromDtoGroup(dtoGroup));
            return ResponseEntity.status(HttpStatus.OK).body(TypeMessage.UPDATED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }
}
