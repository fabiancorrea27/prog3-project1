package co.edu.uptc.project1.controllers;

import java.util.List;

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

import co.edu.uptc.project1.dto.DtoLocation;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.services.LocationService;

@RestController
@RequestMapping("/${locationRequest}")
public class LocationController {

    LocationService locationService = new LocationService();

    @GetMapping
    public ResponseEntity<Object> getLocations() {
        List<DtoLocation> dtoLocationList = DtoLocation.fromLocationList(locationService.getLocationList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoLocationList);
    }

    @PostMapping()
    public ResponseEntity<Object> addLocation(@RequestBody DtoLocation dtoLocation) {
        try {
            DtoLocation.checkNullFields(dtoLocation);
            locationService.addSubject(DtoLocation.fromDtoLocation(dtoLocation));
            return ResponseEntity.status(HttpStatus.OK).body(dtoLocation);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @DeleteMapping("/${deleteLocationMapping}")
    public ResponseEntity<Object> deleteLocacion(@PathVariable String idLocation) {
        try {
            locationService.deleteSubject(idLocation);
            return ResponseEntity.status(HttpStatus.OK).body(TypeMessage.DELETED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateLocation(@RequestBody DtoLocation dtoLocation) {
        try {
            DtoLocation.checkNullFields(dtoLocation);
            locationService.updateLocation(DtoLocation.fromDtoLocation(dtoLocation));
            return ResponseEntity.status(HttpStatus.OK).body(TypeMessage.UPDATED);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getExceptionMessage().getCodeHttp()).body(e.getExceptionMessage());
        }
    }

}
