package co.edu.uptc.project1.dto;

import java.util.List;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Location;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DtoLocation {

    private int id;
    private String name;
    private String ubication;

    public static DtoLocation fromLocation(Location location) {
        DtoLocation dtoLocation = new DtoLocation();
        dtoLocation.setId(location.getId());
        dtoLocation.setName(location.getName());
        dtoLocation.setUbication(location.getUbication());
        return dtoLocation;
    }

    public static List<DtoLocation> fromLocationList(List<Location> locationList) {
        List<DtoLocation> dtoLocationList = new SimpleUptcList<DtoLocation>();
        for (Location location : locationList) {
            dtoLocationList.add(fromLocation(location));
        }
        return dtoLocationList;
    }

    public static Location fromDtoLocation(DtoLocation dtoLocation) {
        Location location = new Location();
        location.setId(dtoLocation.getId());
        location.setName(dtoLocation.getName());
        location.setUbication(dtoLocation.getUbication());
        return location;
    }

    public static void checkNullFields(DtoLocation dtoLocation) throws ProjectExeption {
        if (dtoLocation.getId() == 0 || dtoLocation.getName() == null || dtoLocation.getUbication() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
