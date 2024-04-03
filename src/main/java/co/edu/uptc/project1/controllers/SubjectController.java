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

import co.edu.uptc.project1.dto.DtoSubject;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.services.SubjectService;

@RestController
@RequestMapping("/${subjectRequest}")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping()
    public ResponseEntity<Object> getSubjects() {
        List<DtoSubject> dtoSubjectList = DtoSubject.fromSubjectList(subjectService.getSubjectList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoSubjectList);
    }

    @PostMapping()
    public ResponseEntity<Object> addSubject(@RequestBody DtoSubject dtoSubject) {
        try {
            DtoSubject.checkNullFields(dtoSubject);
            subjectService.addSubject(DtoSubject.fromDtoSubject(dtoSubject));
            return ResponseEntity.status(HttpStatus.OK).body(dtoSubject);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @DeleteMapping("/${deleteSubjectMapping}")
    public ResponseEntity<Object> deleteSubject(@PathVariable int codeSubject) {
        try {
            subjectService.deleteSubject(codeSubject);
            return ResponseEntity.status(HttpStatus.OK).body(TypeMessage.DELETED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateSubject(@RequestBody DtoSubject dtoSubject) {
        try {
            DtoSubject.checkNullFields(dtoSubject);
            subjectService.updateSubject(DtoSubject.fromDtoSubject(dtoSubject));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(TypeMessage.UPDATED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @GetMapping("/${sameLocation}")
    public ResponseEntity<Object> sameLocation(@PathVariable int idLocation){
        List<DtoSubject> dtoSubjectList = DtoSubject.fromSubjectList(subjectService.subjectsWithSameLocation(idLocation));
        return ResponseEntity.status(HttpStatus.OK).body(dtoSubjectList);
    }

    @GetMapping("/${multipleGroups}")
    public ResponseEntity<Object> multipleGroups(){
        List<DtoSubject> dtoSubjectList = DtoSubject.fromSubjectList(subjectService.subjectsWithMultipleGroups());
        return ResponseEntity.status(HttpStatus.OK).body(dtoSubjectList);
    }
}
